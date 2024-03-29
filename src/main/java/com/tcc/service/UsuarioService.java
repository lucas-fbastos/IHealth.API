package com.tcc.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.DTO.MedicoDTO;
import com.tcc.DTO.PacienteDTO;
import com.tcc.DTO.UpdatePasswordFormDTO;
import com.tcc.DTO.UserDTO;
import com.tcc.DTO.filter.ColaboradorFilter;
import com.tcc.domain.DadosMedicos;
import com.tcc.domain.Endereco;
import com.tcc.domain.Paciente;
import com.tcc.domain.Usuario;
import com.tcc.enums.PerfilEnum;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.EnderecoRepository;
import com.tcc.repository.PacienteRepository;
import com.tcc.repository.UserRepository;
import com.tcc.security.UserSecurity;
import com.tcc.service.exceptions.DataIntegrityException;
import com.tcc.service.exceptions.NoElementException;
import com.tcc.service.exceptions.ObjetoInvalidoException;
import com.tcc.service.exceptions.UserDeslogadoException;

@Service
public class UsuarioService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private DadosMedicosRepository dadosMedicosRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public Usuario saveUser(UserDTO dto) {
		
		
		try {
			if(dto.getPerfil()==null)
				throw new ObjetoInvalidoException("O campo perfil deve ser preenchido");
			
			Usuario user = new Usuario(dto);
			
			user.setId(null);
			user.setSexo(dto.getSexo().charAt(0));
			user.setNome(dto.getNome());
			user.setDtNascimento(dto.getDtNascimento());
			user.setPassword(encoder.encode("12345678"));
			user.addPerfil(PerfilEnum.PRIMEIRO_ACESSO);
			user.addPerfil(PerfilEnum.values()[dto.getPerfil()]);
			user.setDtCadastro(new Date());			
			user.setCpf(dto.getCpf());
			this.userRepository.save(user);
			
			if(user.getPerfis().contains(PerfilEnum.PACIENTE)) {
				Paciente p = new Paciente();
				p.setUsuario(user);
				this.pacienteRepository.save(p);
				DadosMedicos dados = new DadosMedicos();
				dados.setPaciente(p);
				dados.setDtAtualizacao(LocalDateTime.now());
				this.dadosMedicosRepository.save(dados);
			}
			if(dto.getEndereco()!=null) {
				Endereco e = new Endereco(dto.getEndereco());
				e.setUsuarioEndereco(user);
				this.enderecoRepository.save(e);
				user.setEndereco(e);
			}
			
			return user;
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao cadastrar usuário, violação de regra");
		}
	}
	
	public Usuario updateUser(UserDTO dto) {
		try {
			Usuario userSalvo = getUserLogado();
			userSalvo.setNome(dto.getNome());
			userSalvo.setTelefone(dto.getTelefone());
			userSalvo.setDtNascimento(dto.getDtNascimento());
			userSalvo.setCpf(dto.getCpf());
			
			this.userRepository.save(userSalvo);
			Endereco endereco = userSalvo.getEndereco();
			if(endereco!=null) {
				
				endereco.setDescBairro(dto.getEndereco().getDescBairro());
				endereco.setDescComplemento(dto.getEndereco().getDescComplemento());
				endereco.setDescRua(dto.getEndereco().getDescRua());
				endereco.setNoCidade(dto.getEndereco().getNoCidade());
				endereco.setNoEstado(dto.getEndereco().getNoEstado());
				endereco.setNuCep(dto.getEndereco().getNuCep());
				endereco.setNumero(dto.getEndereco().getNumero());
				
				this.enderecoRepository.save(endereco);
			}
			return userSalvo;
			
		}catch(NoSuchElementException e) {
			throw new NoElementException("Usuário não encontrado");
		}
	}
	
	public void updatePassword(UpdatePasswordFormDTO form) {
		Usuario u = this.getUserLogado();
		if(u.getPerfis().contains(PerfilEnum.PRIMEIRO_ACESSO)) {
			u.removePerfil(PerfilEnum.PRIMEIRO_ACESSO);
			if(!u.getPerfis().contains(PerfilEnum.ATIVO) && !u.getPerfis().contains(PerfilEnum.PENDENTE))
				u.addPerfil(PerfilEnum.PENDENTE);
		}
		if(form.getPassword().equals(form.getPasswordConfirmation())) {
			u.setPassword(this.encoder.encode(form.getPassword()));
			this.userRepository.save(u);
		}
			
		
	}
	
	public static UserSecurity authenticated() {
		UserSecurity userLogado = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();			
		if(userLogado!=null && userLogado.isCredentialsNonExpired()) 
			return userLogado;
		else
			throw new UserDeslogadoException("Usuário inválido, tente logar novamente");
	}
	
	public Usuario getUserLogado() {
		UserSecurity logado = authenticated();
		try {
			return this.userRepository.findById(logado.getId()).orElseThrow();			
		}catch (NoSuchElementException e) {
			throw new NoElementException("Usuário não encontrado");
		}
	}

	public Usuario getById(Long userId) {
		try {
			return this.userRepository.findById(userId).orElseThrow();			
		}catch (NoSuchElementException e) {
			throw new NoElementException("Usuário não encontrado");
		}
	}
	
	public UserDTO getDadosUser() {
		Usuario u = getUserLogado();
		UserDTO dto = new UserDTO(u);
		for(PerfilEnum p : u.getPerfis()) {
			switch(p) {
				case PACIENTE:
					if(u.getPaciente()!=null)
						dto.setPaciente(new PacienteDTO(u.getPaciente()));
					break;
				case MEDICO:
					if(u.getMedico()!=null)
						dto.setMedico(new MedicoDTO(u.getMedico()));
					break;
			default:
				break;
			}
		}
		return dto;
	}

	public Usuario removePendente(Usuario u) {
		u.removePerfil(PerfilEnum.PENDENTE);
		u.addPerfil(PerfilEnum.ATIVO);
		this.userRepository.save(u);
		return u;
	}
	
	public Usuario adicionaPendente(Usuario u) {
		u.removePerfil(PerfilEnum.ATIVO);
		u.addPerfil(PerfilEnum.PENDENTE);
		this.userRepository.save(u);
		return u;
	}

	public Page<UserDTO> getAllAuxiliares(Pageable p) {
		Set<Integer> perfils = new HashSet<>();
		perfils.add(PerfilEnum.AUXILIAR.getId());
		perfils.add(PerfilEnum.ADMINISTRADOR.getId());
		Page<Usuario> usuarios = this.userRepository.findAllByPerfisIn(p,perfils);
		 if(!usuarios.isEmpty()) {
		    return new PageImpl<UserDTO>(
		    		usuarios.getContent().stream().map(u -> new UserDTO(u)).collect(Collectors.toList()), p, usuarios.getTotalElements());
		}else {
		  	throw new NoElementException("Não existem usuários cadastrados para os parametros informados");
		}
	}

	public Page<UserDTO> filtrarColaborador(ColaboradorFilter filter, Pageable p) {
		Page<Usuario> usuarios = this.userRepository.filterColaborador(filter,p);
		 if(!usuarios.isEmpty()) {
		    return new PageImpl<UserDTO>(
		    		usuarios.getContent().stream().map(u -> new UserDTO(u)).collect(Collectors.toList()), p, usuarios.getTotalElements());
		}else {
		  	throw new NoElementException("Não existem usuários cadastrados para os parametros informados");
		}
	}
}
