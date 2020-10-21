package com.verizontraining.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verizontraining.myapp.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	 public UserAccount findByEmail(String email);

}
