package com.tcc.resource;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.DTO.UserDTO;
import com.tcc.domain.User;
import com.tcc.security.JwtUtil;
import com.tcc.service.UserService;

@RestController
@RequestMapping("/user")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@PostMapping
	public ResponseEntity<Void> saveUser(@Valid @RequestBody UserDTO dto){
		this.userService.saveUser(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<UserDTO>updateUser(@Valid @RequestBody UserDTO dto){
		User user = this.userService.updateUser(dto);
		UserDTO userAtualizado = new UserDTO(user.getNome(), String.valueOf(user.getSexo()), 
					user.getTelefone(), user.getDtNascimento(), user.getEmail(), "");
		return ResponseEntity.ok(userAtualizado);
	}
	
	@GetMapping("/refresh")
	public ResponseEntity<?> refreshToken(HttpServletResponse response) {
		User user = this.userService.getUserLogado();
		String token = this.jwtUtil.generateToken(user);
		response.addHeader("Authorization", "Bearer " + token);
		Map<String,String> retorno = new HashMap<>();
		retorno.put("token", token);
		return ResponseEntity.ok(retorno);
	}
}
