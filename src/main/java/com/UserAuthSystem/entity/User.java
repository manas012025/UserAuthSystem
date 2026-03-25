package com.UserAuthSystem.entity;

import java.io.Serializable;

import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Audited(withModifiedFlag = true)
@Entity
@Table(name = "USERS")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User extends AuditableEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="username", unique = true,nullable = false)
	private String username;
	
	@Column(name="email", unique = true,nullable = true)
	private String email;
	
	@Column(name="mobile", unique = true,nullable = true)
	private String mobile;
	
	@Column(name="password",nullable = false)
	private String password;
	
	private String role;
	
	@Column(name = "session_id")
	private String sessionId;
	
}
