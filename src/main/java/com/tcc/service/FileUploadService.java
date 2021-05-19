package com.tcc.service;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tcc.domain.DocumentoMedico;
import com.tcc.domain.Prontuario;
import com.tcc.repository.DocumentoMedicoRepository;
import com.tcc.service.exceptions.NoElementException;


@Service
@Transactional
public class FileUploadService {

	@Autowired
	private DocumentoMedicoRepository documentoMedicoRepository;

	@Autowired
	private ProntuarioService prontuarioService;
	
	public DocumentoMedico store(MultipartFile file, Integer tipo,Long idProntuario) throws IOException {
		Prontuario p = this.prontuarioService.getById(idProntuario);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		DocumentoMedico doc = new DocumentoMedico();
		doc.setData(file.getBytes());
		doc.setNomeArquivo(fileName);
		doc.setTipo(tipo);
		doc.setProntuario(p);
		doc.setFormato(file.getContentType());
		return documentoMedicoRepository.save(doc);
	}

	public DocumentoMedico getFile(Long id) {
		try {
			return documentoMedicoRepository.findById(id).orElseThrow();
		}catch(NoSuchElementException e) {
			throw new NoElementException("Arquivo não encontrado");
		}
	}

	public Stream<DocumentoMedico> getAllFiles(Long idProntuario) {
		Prontuario p = this.prontuarioService.getById(idProntuario);
		return p.getDocumentos().stream();
	}

	public void deletFileById(Long id) {
		if (documentoMedicoRepository.existsById((id)))
			documentoMedicoRepository.deleteById(id);
		throw new NoElementException("Arquivo não encontrado");
	}
}