package com.UserAuthSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.UserAuthSystem.entity.ResetPasswordOtp;

@Repository
public interface ResetPasswordOtpRepository extends JpaRepository<ResetPasswordOtp, Long>{
	ResetPasswordOtp findByEmail(String email);
	ResetPasswordOtp findByEmailAndOtp(String email,String otp);
}
