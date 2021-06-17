package com.app.projectmanagement.entities;

import javax.persistence.*;


@Entity
@Table(name = "user_accounts")
public class UserAccount {

	@Id
	@SequenceGenerator(name="user_accounts_seq", sequenceName="user_accounts_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_accounts_seq")
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "username")
	private String userName;
	
	private String email;
	
	private String password;

	private boolean enabled = true;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private String role;

	public UserAccount() {

	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
