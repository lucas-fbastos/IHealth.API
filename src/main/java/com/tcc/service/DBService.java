package com.tcc.service;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.domain.Alergia;
import com.tcc.domain.Clinica;
import com.tcc.domain.DadosMedicos;
import com.tcc.domain.Especializacao;
import com.tcc.domain.Medicamento;
import com.tcc.domain.ProcedimentoMedico;
import com.tcc.domain.TipoAlergia;
import com.tcc.domain.TipoProcedimento;
import com.tcc.domain.TipoSanguineo;
import com.tcc.domain.Usuario;
import com.tcc.enums.PerfilEnum;
import com.tcc.repository.AlergiaRepository;
import com.tcc.repository.ClinicaRepository;
import com.tcc.repository.DadosMedicosRepository;
import com.tcc.repository.EspecializacaoRepository;
import com.tcc.repository.MedicamentoRepository;
import com.tcc.repository.ProcedimentoMedicoRepository;
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
	private MedicamentoRepository medicamentoRepository;
	
	@Autowired
	private TipoAlergiaRepository tipoAlergiaRepository;
	
	@Autowired
	private AlergiaRepository alergiaRepository;
	
	@Autowired
	private TipoProcedimentoRepository tipoProcedimentoRepository;
	
	@Autowired
	private ProcedimentoMedicoRepository procedimentoMedicoRepository;
	
	@Autowired
	private EspecializacaoRepository especializacaoRepository;

	@Autowired
	private ClinicaRepository clinicaRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
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
		
		ProcedimentoMedico pm1 = new ProcedimentoMedico();
		pm1.setDescricao("Consulta no oftalmologista, paga pelo plano de saúde, atestado de 4 dias");
		pm1.setDtRegistro(LocalDate.now());
		pm1.setDtProcedimento(LocalDate.now());
		pm1.setTipoProcedimento(tp1);
		pm1.setUser(u1);
		this.procedimentoMedicoRepository.save(pm1);
		
		ProcedimentoMedico pm2 = new ProcedimentoMedico();
		pm2.setDescricao("Exame de sangue, glicose e trigliceridios. O exame foi solicitado pelo médico na última consulta");
		pm2.setDtRegistro(LocalDate.now());
		pm2.setDtProcedimento(LocalDate.now());
		pm2.setTipoProcedimento(tp3);
		pm2.setUser(u1);
		this.procedimentoMedicoRepository.save(pm2);
		
		ProcedimentoMedico pm3 = new ProcedimentoMedico();
		pm3.setDescricao("Cirurgia de remoção de amidala, paga pelo plano de saúde com cooparticipação, 5 dias de atestado");
		pm3.setDtRegistro(LocalDate.now());
		
		pm3.setDtProcedimento(LocalDate.of(2010, 12, 11));
		pm3.setTipoProcedimento(tp2);
		pm3.setUser(u1);
		this.procedimentoMedicoRepository.save(pm3);
		
		ProcedimentoMedico pm4 = new ProcedimentoMedico();
		pm4.setDescricao(null);
		pm4.setDtRegistro(LocalDate.now());
		pm4.setDtProcedimento(LocalDate.of(2000, 02, 15));
		pm4.setTipoProcedimento(tp5);
		pm4.setUser(u1);
		this.procedimentoMedicoRepository.save(pm4);
		
		Especializacao e1 = new Especializacao();
		e1.setDescEspecializacao("Alergista");
		this.especializacaoRepository.save(e1);
		
		Especializacao e2 = new Especializacao();
		e2.setDescEspecializacao("Dermatologista");
		this.especializacaoRepository.save(e2);
		
		Especializacao e3 = new Especializacao();
		e3.setDescEspecializacao("Cardiologista");
		this.especializacaoRepository.save(e3);
		
		Clinica c = new Clinica();
		c.setDtAbertura(LocalTime.of(9, 0));
		c.setDtEncerramento(LocalTime.of(18, 0));
		c.setNome("Clinica de Brasília");
		this.clinicaRepository.save(c);
		
	}
}
