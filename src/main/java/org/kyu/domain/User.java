package org.kyu.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, length=20)
	private String userId;

	private String password;
	private String name;
	private String email;
	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", password=" + password + ", name=" + name + ", email="
				+ email + "]";
	}
	public void update(User user) {
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.name = user.getName();
	}
	public String getUserId() {
		return userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
}
