package br.com.fernandaviana.apirestful.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.fernandaviana.apirestful.entities.Phone;
import br.com.fernandaviana.apirestful.entities.User;

public class UserAuthenticateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String email;

	private String password;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
	private Date created;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
	private Date modified;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
	private Date last_login;

	private String token;

	private List<Phone> phones = new ArrayList<>();

	public UserAuthenticateDTO() {
	}
	
	public UserAuthenticateDTO(Long id, String name, String email, String password, Date created, Date modified,
			Date last_login, String token, List<Phone> phones) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.created = created;
		this.modified = modified;
		this.last_login = last_login;
		this.token = token;
		this.phones = phones;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public static UserAuthenticateDTO toDTO(User user, Date last_login) {
		return new UserAuthenticateDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getCreated(),
				user.getModified(), last_login, user.getToken(), user.getPhones());
	}

}
