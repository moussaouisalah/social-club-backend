package com.example.Training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Training.config.PasswordConfig;
import com.example.Training.entity.User;
import com.example.Training.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordConfig passwordConfig;
	
	public User saveUser(User user) {
		
		Optional<User> userByEmail = this.repository.findByEmail(user.getEmail());
		if (userByEmail.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists, try sign in");
		}

		user.setPassword(this.passwordConfig.passwordEncoder().encode(user.getPassword()));
		return repository.save(user);
	}
	
	public List <User> saveUsers(List <User> users) {
		return repository.saveAll(users);
	}
	
	public List<User> getUsers(){
		return repository.findAll();
	}
	
	public List<User> getUsersList(Optional<List<Integer>> ids){
		return repository.findByIdIn(ids);
	}
	
	
	public User getUserById(int id) {
		return repository.findById(id).orElse(null);
	}
	public User getUserByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
	}
	
	public User updateUser( int id , User user)
	{
		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		User existeUser = repository.findById(id).orElse(null);
		if(repository.findById(id).isPresent()) {
			existeUser.setFirstName(user.getFirstName());
			existeUser.setLastName(user.getLastName());
			existeUser.setEmail(user.getEmail());
			/*existeUser.setPassword(encoder.encode(user.getPassword()));*/
			/*User updateuser = repository.save(existeUser);*/
			return repository.save(existeUser);
		}
		else {
			return null;
		}
		
	}
	
	public String removeUser(int id) {
		repository.deleteById(id);
		return "User has removed !!"+id;
	}

	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.repository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
}
