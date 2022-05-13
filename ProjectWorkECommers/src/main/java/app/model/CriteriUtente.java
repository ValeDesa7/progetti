package app.model;

import javax.validation.constraints.NotBlank;

public class CriteriUtente {
	
	@NotBlank(message="username obbligatoria")
	private String username;
	@NotBlank(message="password obbligatoria")
	private String password;
	
	public CriteriUtente() {
		super();
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
