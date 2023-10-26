package com.codingdojo.bookClub.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	// Class Attributes / Validations:
	// Attributes that are automatically created:
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
	
	// Connect the one-to-many relationship
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Book> books;
	
	// Attributes the user determines from the form entry:
	@NotEmpty(message = "Name / Username is required.")
	@Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters.")
	private String name;
	
	@NotEmpty(message = "Email address is required.")
	@Email(message = "Please enter a valid email format.")
	private String email;
	
	@NotEmpty(message = "Password is required.")
	@Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters.")
	private String password;
	
	@Transient
	@NotEmpty(message = "You must confirm your password.")
	@Size(min = 8, max = 128, message = "Password confirmation must be between 8 and 128 characters.")
	private String confirmPass;
	
	
	// Empty Constructor
	public User() {}
	
	// Constructor
	public User(
			@NotEmpty(message = "Name / Username is required.") @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters.") String name,
			@NotEmpty(message = "Email address is required.") @Email(message = "Please enter a valid email format.") String email,
			@NotEmpty(message = "Password is required.") @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters.") String password,
			@NotEmpty(message = "You must confirm your password.") @Size(min = 8, max = 128, message = "Password confirmation must be between 8 and 128 characters.") String confirmPass) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmPass = confirmPass;
	}

	// Getters & Setters:
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	
}

