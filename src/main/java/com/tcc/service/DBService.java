package com.tcc.service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.domain.Alergia;
import com.tcc.domain.DadosMedicos;
import com.tcc.domain.Medicamento;
import com.tcc.domain.TipoAlergia;
import com.tcc.domain.TipoSanguineo;
import com.tcc.domain.User;
import com.tcc.enums.PerfilEnum;
import com.tcc.repository.AlergiaRepository;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.MedicamentoRepository;
import com.tcc.repository.TipoAlergiaRepository;
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
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private TipoAlergiaRepository tipoAlergiaRepository;
	
	@Autowired
	private AlergiaRepository alergiaRepository;
	
	
	public void instantiateTestDatabase() throws ParseException {
		
		User u1 = new User();
		u1.setEmail("felixbastos.lucas@gmail.com");
		u1.setNome("lucas felix bastos");
		u1.setPassword(pe.encode("12345678"));
		u1.setSexo('M');
		u1.addPerfil(PerfilEnum.PENDENTE);
		u1.setTelefone("61996253833");
		u1.setDtNascimento(new java.sql.Date(0L));
		u1.setDtCadastro(new Date());
		u1 = this.usuarioRepository.save(u1);
		
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
		dm1.setDt_atualizacao(new Date());
		dm1.setTipoSanguineo(this.tipoSanguineoRepository.findById(1).get());
		dm1.setUser(u1);
		dm1 = this.dadosMedicosRepository.save(dm1);
		
		Medicamento m2 = new Medicamento();
		m2.setDadosMedicos(dm1);
		m2.setDescMedicamento("loratadina");
		m2 = this.medicamentoRepository.save(m2);
		
		Medicamento m3 = new Medicamento();
		m3.setDadosMedicos(dm1);
		m3.setDescMedicamento("paracetamol");
		m3 = this.medicamentoRepository.save(m3);
		
		Medicamento m1 = new Medicamento();
		m1.setDadosMedicos(dm1);
		m1.setDescMedicamento("doralgina");
		m1 = this.medicamentoRepository.save(m1);
		
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
		
		Alergia a1 = new Alergia();
		a1.setDadosMedicos(dm1);
		a1.setDescAlergia("Alergia a amoxilina");
		a1.setTipoAlergia(ta1);
		a1 = this.alergiaRepository.save(a1);
		
	}
}
