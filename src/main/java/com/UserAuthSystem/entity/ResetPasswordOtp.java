package com.UserAuthSystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(indexes = {

		@Index(name = "idx_resetotp_email", columnList = "email"),

		@Index(name = "idx_resetotp_email_otp", columnList = "email,otp") })
@Data
public class ResetPasswordOtp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String mobile;

	private String otp;

	private LocalDateTime expiryTime;

	private boolean verified;
}
