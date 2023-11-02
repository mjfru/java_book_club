package com.codingdojo.bookClub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.bookClub.models.LoginUser;
import com.codingdojo.bookClub.models.User;
import com.codingdojo.bookClub.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	// Finding a User by ID
	public User findById(Long id) {
		Optional<User> confirmedUser = userRepository.findById(id);
		if (confirmedUser.isPresent()) {
			return confirmedUser.get();
		} else {
			return null;
		}
	}
	
	// Registering a new user:
	// Checking if an email is already entered into the database
	public User register(User newUser, BindingResult result) {
		// Reject if it's taken...
		Optional<User> confirmedUser = userRepository.findByEmail(newUser.getEmail());
		if (confirmedUser.isPresent()) {
			result.rejectValue("email", "Matches", "This email has already been registered.");
		}
		
		// Reject if passwords do not match...
		if(!newUser.getPassword().equals(newUser.getConfirmPass())) {
			result.rejectValue("confirmPass", "Matches", "Passwords do not match. Please try again.");
		}
		
		// If this process has any errors, return null...
		if (result.hasErrors()) {
			return null;
		}
		
		// Else, hash and set the password, save it all the the database...
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		return userRepository.save(newUser);
	}
	
	// Logging in a user...
	public User login(LoginUser newLogin, BindingResult result) {
		Optional<User> confirmedUser = userRepository.findByEmail(newLogin.getEmail());
		if(!confirmedUser.isPresent()) {
			result.rejectValue("email", "Matches", "Invalid Email or Password.");
			return null;
		}
		
		User user = confirmedUser.get();
		if (!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid Email or Password.");
		}
		
		if (result.hasErrors()) {
			return null;
		} else {
			return user;
		}
	}
}
