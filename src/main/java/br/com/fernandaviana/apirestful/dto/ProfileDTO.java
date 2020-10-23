package br.com.fernandaviana.apirestful.dto;

import java.io.Serializable;
import java.util.Date;

public class ProfileDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date created;
	private Date modified;
	private Date last_login;
	private String token;

	public ProfileDTO() {

	}

	public ProfileDTO(Date created, Date modified, Date last_login, String token) {
		super();
		this.created = created;
		this.modified = modified;
		this.last_login = last_login;
		this.token = token;
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

}
