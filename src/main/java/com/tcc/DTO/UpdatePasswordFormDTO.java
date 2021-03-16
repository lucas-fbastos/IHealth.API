package com.tcc.DTO;

public class UpdatePasswordFormDTO {

	private String password;
	private String passwordConfirmation;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	public UpdatePasswordFormDTO() {} 
	
	
}
