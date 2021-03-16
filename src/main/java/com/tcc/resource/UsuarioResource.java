package com.tcc.resource;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.DTO.EnderecoDTO;
import com.tcc.DTO.UpdatePasswordFormDTO;
import com.tcc.DTO.UserDTO;
import com.tcc.domain.Usuario;
import com.tcc.security.JwtUtil;
import com.tcc.service.UsuarioService;

@RestController
@RequestMapping("/user")
public class UsuarioResource {

	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@PostMapping
	public ResponseEntity<Usuario> saveUser(@Valid @RequestBody UserDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.saveUser(dto));
	}
	
	@PutMapping
	public ResponseEntity<UserDTO>updateUser(@Valid @RequestBody UserDTO dto){
		Usuario user = this.userService.updateUser(dto);
		UserDTO userAtualizado = new UserDTO(user.getNome(), String.valueOf(user.getSexo()), 
					user.getTelefone(), user.getDtNascimento(), user.getEmail(), new EnderecoDTO(user.getEndereco()));
		return ResponseEntity.ok(userAtualizado);
	}
	
	@GetMapping("/refresh")
	public ResponseEntity<?> refreshToken(HttpServletResponse response) {
		Usuario user = this.userService.getUserLogado();
		String token = this.jwtUtil.generateToken(user);
		response.addHeader("Authorization", "Bearer " + token);
		Map<String,String> retorno = new HashMap<>();
		retorno.put("token", token);
		return ResponseEntity.ok(retorno);
	}
	
	@PatchMapping("/password")
	public ResponseEntity<Void> updatePassword(@RequestBody UpdatePasswordFormDTO dto){
		this.userService.updatePassword(dto);
		return ResponseEntity.noContent().build();
	}
}
