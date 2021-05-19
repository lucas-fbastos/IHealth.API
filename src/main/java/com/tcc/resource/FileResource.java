package com.tcc.resource;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tcc.DTO.ResponseFileDTO;
import com.tcc.domain.DocumentoMedico;
import com.tcc.enums.TipoArquivoEnum;
import com.tcc.service.FileUploadService;

@RestController
@RequestMapping("/files")
public class FileResource {

	@Autowired
	private FileUploadService storageService;

	@PostMapping("/upload/{tipo}/{idProntuario}")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,@PathVariable Integer tipo,@PathVariable Long idProntuario) {
		try {
			storageService.store(file,tipo,idProntuario);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}

	@GetMapping("/prontuario/{idProntuario}")
	public ResponseEntity<List<ResponseFileDTO>> getListFiles(@PathVariable Long idProntuario) {
		List<ResponseFileDTO> files = storageService.getAllFiles(idProntuario).map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/download/")
					.path(dbFile.getId().toString()).toUriString();

			return new ResponseFileDTO(dbFile.getNomeArquivo(), fileDownloadUri, dbFile.getFormato(), 1l, TipoArquivoEnum.toEnum(dbFile.getTipo()).getDescricao());
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/download/{id}")
	public ResponseEntity<Object> getFile(@PathVariable Long id) {
		
		try {
			DocumentoMedico fileDB = storageService.getFile(id);

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getNomeArquivo() + "\"")
					.body(fileDB.getData());
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletFileById(@PathVariable Long id) {
		storageService.deletFileById(id);
		return ResponseEntity.noContent().build();
		
	}
}