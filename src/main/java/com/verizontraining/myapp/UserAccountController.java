package com.verizontraining.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.verizontraining.myapp.model.UserAccount;
import com.verizontraining.myapp.repository.UserAccountRepository;
import com.verizontraining.myapp.request.UserRequest;

@RestController
public class UserAccountController {
	@Autowired
	UserAccountRepository userRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping("/register")
	public String getNewUserDetails(@RequestBody UserRequest userReq) {
		UserAccount userAccount = new UserAccount();
		if(userRepo.findByEmail(userReq.getEmail())!=null) {
			return "Existing User";
		}else {
			userAccount.setFirstName(userReq.getFirstName());
			userAccount.setLastName(userReq.getLastName());
			userAccount.setEmail(userReq.getEmail());
			userAccount.setPassword(userReq.getPassword());
			userRepo.save(userAccount);
			ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9090/notification", userReq.getEmail(), String.class);
			
			return "Successfully registered and "+response.getBody().toString();
		}
	}

}
