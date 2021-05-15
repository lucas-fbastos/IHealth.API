package com.tcc.service;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.domain.Clinica;
import com.tcc.domain.DadosMedicos;
import com.tcc.domain.Endereco;
import com.tcc.domain.Especializacao;
import com.tcc.domain.Medico;
import com.tcc.domain.Paciente;
import com.tcc.domain.TipoAlergia;
import com.tcc.domain.TipoProcedimento;
import com.tcc.domain.TipoSanguineo;
import com.tcc.domain.Usuario;
import com.tcc.enums.PerfilEnum;
import com.tcc.repository.ClinicaRepository;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.EnderecoRepository;
import com.tcc.repository.EspecializacaoRepository;
import com.tcc.repository.MedicoRepository;
import com.tcc.repository.PacienteRepository;
import com.tcc.repository.TipoAlergiaRepository;
import com.tcc.repository.TipoProcedimentoRepository;
import com.tcc.repository.TipoSanguineoRepository;
import com.tcc.repository.UserRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UserRepository usuarioRepository;
	
	@Autowired
	private DadosMedicosRepository dadosMedicosRepository;
	
	@Autowired
	private TipoSanguineoRepository tipoSanguineoRepository;
	
	@Autowired
	private TipoAlergiaRepository tipoAlergiaRepository;
	
	@Autowired
	private TipoProcedimentoRepository tipoProcedimentoRepository;
	
	
	@Autowired
	private EspecializacaoRepository especializacaoRepository;

	@Autowired
	private ClinicaRepository clinicaRepository;
	

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	
	
	public void instantiateTestDatabase() throws ParseException {
		
		deleteUsersIfExists();
		
		Especializacao e1 = new Especializacao();
		e1.setDescEspecializacao("Alergista");
		this.especializacaoRepository.save(e1);
		
		Especializacao e2 = new Especializacao();
		e2.setDescEspecializacao("Dermatologista");
		this.especializacaoRepository.save(e2);
		
		Especializacao e3 = new Especializacao();
		e3.setDescEspecializacao("Cardiologista");
		this.especializacaoRepository.save(e3);
		
		Especializacao e4 = new Especializacao();
		e4.setDescEspecializacao("Pneumologista");
		this.especializacaoRepository.save(e4);
		
		Especializacao e5 = new Especializacao();
		e5.setDescEspecializacao("Pediatra");
		this.especializacaoRepository.save(e5);
		
		Especializacao e6 = new Especializacao();
		e6.setDescEspecializacao("Ortopedista");
		this.especializacaoRepository.save(e6);
		
		Especializacao e7 = new Especializacao();
		e7.setDescEspecializacao("Neurologista");
		this.especializacaoRepository.save(e7);
		
		Especializacao e8 = new Especializacao();
		e8.setDescEspecializacao("Oftamologista");
		this.especializacaoRepository.save(e8);
		
		
		Usuario u1 = new Usuario();
		u1.setEmail("felixbastos.lucas@gmail.com");
		u1.setNome("lucas felix bastos");
		u1.setPassword(pe.encode("12345678"));
		u1.setSexo('M');
		u1.addPerfil(PerfilEnum.PRIMEIRO_ACESSO);
		u1.setTelefone("61996253833");
		u1.setDtNascimento(LocalDate.of(1998, 02, 17));
		u1.setDtCadastro(new Date());
		u1.addPerfil(PerfilEnum.ADMINISTRADOR);
		u1 = this.usuarioRepository.save(u1);
		
		Endereco endereco = new Endereco();
		endereco.setDescBairro("cruzeiro");
		endereco.setDescComplemento("ap 101");
		endereco.setDescRua("rua 12");
		endereco.setNumero(501);
		endereco.setNoCidade("Brasília");
		endereco.setUsuarioEndereco(u1);
		endereco.setNoEstado("DF");
		endereco = this.enderecoRepository.save(endereco);
		
		Usuario u2 = new Usuario();
		u2.setEmail("medico@teste.com");
		u2.setNome("João");
		u2.setPassword(pe.encode("12345678"));
		u2.setSexo('M');
		u2.addPerfil(PerfilEnum.PRIMEIRO_ACESSO);
		u2.setTelefone("619999999999");
		u2.setDtNascimento(LocalDate.of(1990, 01, 14));
		u2.setDtCadastro(new Date());
		u2.addPerfil(PerfilEnum.MEDICO);
		u2 = this.usuarioRepository.save(u2);
		
		Endereco enderecoMedico = new Endereco();
		enderecoMedico.setDescBairro("cruzeiro");
		enderecoMedico.setDescComplemento("ap 211");
		enderecoMedico.setDescRua("rua 20");
		enderecoMedico.setNumero(901);
		enderecoMedico.setNoCidade("Brasília");
		enderecoMedico.setUsuarioEndereco(u2);
		enderecoMedico.setNoEstado("DF");
		enderecoMedico = this.enderecoRepository.save(enderecoMedico);
		
		Medico m = new Medico();
		m.setCrm("1010101010101-DF");
		m.setUsuario(u2);
		Set<Especializacao> especializacoes = new HashSet<>();
		especializacoes.add(e1);
		m.setEspecializacoes(especializacoes);
		m.setHrEntrada(LocalTime.of(9, 0));
		m.setHrSaida(LocalTime.of(18, 0));
		m = this.medicoRepository.save(m);
		
		Usuario u3 = new Usuario();
		u3.setEmail("paciente@teste.com");
		u3.setNome("Letícia");
		u3.setPassword(pe.encode("12345678"));
		u3.setSexo('F');
		u3.addPerfil(PerfilEnum.PRIMEIRO_ACESSO);
		u3.setTelefone("61988888888");
		u3.setDtNascimento(LocalDate.of(1999, 11, 8));
		u3.setDtCadastro(new Date());
		u3.addPerfil(PerfilEnum.PACIENTE);
		u3 = this.usuarioRepository.save(u3);
		
		Endereco enderecoPaciente = new Endereco();
		enderecoPaciente.setDescBairro("taguatinga");
		enderecoMedico.setDescComplemento("casa 1");
		enderecoPaciente.setDescRua("rua 2");
		enderecoPaciente.setNumero(108);
		enderecoPaciente.setNoCidade("Brasília");
		enderecoPaciente.setUsuarioEndereco(u3);
		enderecoPaciente.setNoEstado("DF");
		enderecoPaciente = this.enderecoRepository.save(enderecoPaciente);
		
		Paciente p = new Paciente();
		p.setCompartilhaDados(true);
		p.setDescConvenio("AMIL");
		p.setNuInscricaoConvenio("1414214214");
		p.setNuTelefone("6194166452");
		p.setUsuario(u3);
		p = this.pacienteRepository.save(p);
		
		TipoSanguineo ts1 = new TipoSanguineo();
		ts1.setDescricaoTipoSanguineo("A+");
		TipoSanguineo ts2 = new TipoSanguineo();
		ts2.setDescricaoTipoSanguineo("A-");
		TipoSanguineo ts3 = new TipoSanguineo();
		ts3.setDescricaoTipoSanguineo("B+");
		TipoSanguineo ts4 = new TipoSanguineo();
		ts4.setDescricaoTipoSanguineo("B-");
		TipoSanguineo ts5 = new TipoSanguineo();
		ts5.setDescricaoTipoSanguineo("AB+");
		TipoSanguineo ts6 = new TipoSanguineo();
		ts6.setDescricaoTipoSanguineo("AB-");
		TipoSanguineo ts7 = new TipoSanguineo();
		ts7.setDescricaoTipoSanguineo("O+");
		TipoSanguineo ts8 = new TipoSanguineo();
		ts8.setDescricaoTipoSanguineo("O-");
		this.tipoSanguineoRepository.saveAll(Arrays.asList(ts1,ts2,ts3,ts4,ts5,ts6,ts7,ts8));
		
		DadosMedicos dm1 = new DadosMedicos();
		dm1.setDtAtualizacao(LocalDateTime.now());
		dm1.setTipoSanguineo(this.tipoSanguineoRepository.findById(1).get());
		dm1.setPaciente(p);
		dm1 = this.dadosMedicosRepository.save(dm1);
		
		TipoAlergia ta1 = new TipoAlergia();
		ta1.setDescTipo("Medicamento");
		ta1 = this.tipoAlergiaRepository.save(ta1);
		
		TipoAlergia ta2 = new TipoAlergia();
		ta2.setDescTipo("Alimento");
		ta2 = this.tipoAlergiaRepository.save(ta2);
		
		TipoAlergia ta3 = new TipoAlergia();
		ta3.setDescTipo("Animais");
		ta3 = this.tipoAlergiaRepository.save(ta3);
		
		TipoAlergia ta4 = new TipoAlergia();
		ta4.setDescTipo("Outros");
		ta4 = this.tipoAlergiaRepository.save(ta4);
		
		TipoProcedimento tp1 = new TipoProcedimento(null,"Consulta médica", Duration.ofMinutes(40));
		tp1 = this.tipoProcedimentoRepository.save(tp1);
		
		TipoProcedimento tp2 = new TipoProcedimento(null,"Cirurgia", Duration.ofMinutes(270));
		tp2.setDescTipoProcedimento("Cirurgia");
		tp2 = this.tipoProcedimentoRepository.save(tp2);
		
		TipoProcedimento tp3 = new TipoProcedimento(null,"Exame", Duration.ofMinutes(30));
		tp3 = this.tipoProcedimentoRepository.save(tp3);
		
		TipoProcedimento tp4 = new TipoProcedimento(null,"Emergência", Duration.ofHours(1));
		tp4 = this.tipoProcedimentoRepository.save(tp4);
	
		TipoProcedimento tp5 = new TipoProcedimento(null,"Sessão de fisioterapia", Duration.ofMinutes(40));
		tp5 = this.tipoProcedimentoRepository.save(tp5);
				
		Clinica c = new Clinica();
		c.setDtAbertura(LocalTime.of(9, 0));
		c.setDtEncerramento(LocalTime.of(18, 0));
		c.setNome("Clinica de Brasília");
		this.clinicaRepository.save(c);
		Endereco endereco1 = new Endereco();
		endereco1.setDescBairro("cruzeiro");
		endereco1.setDescComplemento("sala 10");
		endereco1.setDescRua("rua 1");
		endereco1.setNumero(105);
		endereco1.setNoCidade("Brasília");
		endereco1.setClinicaEndereco(c);
		endereco1.setNoEstado("DF");
		endereco1 = this.enderecoRepository.save(endereco1);
		
	}

	private void deleteUsersIfExists() {
		List<Usuario> users = this.usuarioRepository.findAll();
		if(users!=null) {
			for(Usuario u : users) {
				if(u.getEndereco()!=null)
					this.enderecoRepository.delete(u.getEndereco());
				if(u.getDadosmedicos()!=null)
					this.dadosMedicosRepository.delete(u.getDadosmedicos());
				if(u.getMedico()!=null)
					this.medicoRepository.delete(u.getMedico());
				if(u.getPaciente()!=null)
					this.pacienteRepository.delete(u.getPaciente());
				this.usuarioRepository.delete(u);
			}
			
		}
	}
}
