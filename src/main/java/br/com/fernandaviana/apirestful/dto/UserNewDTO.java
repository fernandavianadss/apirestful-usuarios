package br.com.fernandaviana.apirestful.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import br.com.fernandaviana.apirestful.services.validation.UserInsert;

@UserInsert
public class UserNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Informação obrigatória")
	private String name;

	@NotEmpty(message = "Informação obrigatória")
	@Email(message = "Email Inválido")
	private String email;
	
	@NotEmpty(message = "Informação obrigatória")
	private String password;

	private String number;
	private String ddd;

	public UserNewDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	
}
