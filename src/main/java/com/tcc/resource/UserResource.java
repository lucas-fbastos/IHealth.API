package com.tcc.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tcc.DTO.UserDTO;
import com.tcc.domain.User;
import com.tcc.service.UserService;

@RestController
@RequestMapping("/user")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Void> saveUser(@Valid @RequestBody UserDTO dto){
		User user = this.userService.saveUser(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest
					().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<UserDTO>updateUser(@Valid @RequestBody UserDTO dto){
		User user = this.userService.updateUser(dto);
		UserDTO userAtualizado = new UserDTO(user.getNome(), String.valueOf(user.getSexo()), 
					user.getTelefone(), user.getDtNascimento(), user.getEmail(), "");
		return ResponseEntity.ok(userAtualizado);
	}
}
