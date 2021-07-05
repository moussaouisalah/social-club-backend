package com.example.Training.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Training.entity.Club;
import com.example.Training.entity.User;
import com.example.Training.model.JwtResponse;
import com.example.Training.service.UserService;
import com.example.Training.utility.JWTUtility;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class UserController  {
	@Autowired
	private UserService service;
	@Autowired
	private JWTUtility jwtUtility;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	/*@PostMapping("/addUser")
	public User addUser(@Valid @RequestBody User user) {
		
		return service.saveUser(user);
	}*/
	

	@Value("${file.upload-dir}")
	String FILE_DIRECTORY;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(path = "/auth/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Valid
	public User addPost(@RequestParam("user") String user, @RequestParam("profileImage") MultipartFile profileImage,@RequestParam("coverImage") MultipartFile coverImage)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		User postobj = mapper.readValue(user, User.class);
		
		String photoPath = "src/main/resources/uploads/" + UUID.randomUUID()
				+ profileImage.getOriginalFilename().replaceAll(" ", "").trim();
		
		String coverImageclub = "src/main/resources/uploads/" + UUID.randomUUID()
		+ coverImage.getOriginalFilename().replaceAll(" ", "").trim();
		
		postobj.setProfileImage(photoPath);
		postobj.setCoverImage(coverImageclub);

		byte[] bytes = profileImage.getBytes();
		File myFile = new File(photoPath);
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(bytes);
		
		byte[] bytescover = coverImage.getBytes();
		File filecover = new File(coverImageclub);
		FileOutputStream foscover = new FileOutputStream(filecover);
		foscover.write(bytescover);
		return service.saveUser(postobj);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@PostMapping("/addUsers")
	public List<User> addUsers(@RequestBody List<User> users) {
		return service.saveUsers(users);
	}*/
	
	
	
	@GetMapping(path="/users",produces = "application/json")
	public List<User> findAllUser(@AuthenticationPrincipal User connectedUser, @RequestParam("ids")   Optional <List<Integer>> ids){
		if(ids.isPresent()) {
			return service.getUsersList(ids);
		}else {
			System.out.print(connectedUser);
			return service.getUsers();
		}
		
	}
	
	
	@GetMapping(path="/auth/identity",produces = "application/json")
	public User findIdentity(@AuthenticationPrincipal User connectedUser){	
		return connectedUser;	
	}
	
	
	
	@GetMapping("/users/{id}")
	public User findUserById(@PathVariable int id) {
		return service.getUserById(id);
	}
	
	@GetMapping("/userByEmail/{email}")
	public User findUserByEmail(@PathVariable String email) {
		return service.getUserByEmail(email);
	}
	
	@PutMapping(value="/users/{id}")
	  public ResponseEntity<User> updateUser(@PathVariable(value = "id") int id,
              @RequestBody User user){
		return new ResponseEntity<>(service.updateUser(id, user), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable int id) {
		return service.removeUser(id);
	}
	

	
	@PostMapping(path = "/auth", consumes ="application/json" ,produces = "application/json")
	public JwtResponse authent(@RequestBody User user) {
		final 	Authentication authenticate = authenticationManager.authenticate(
				  new UsernamePasswordAuthenticationToken( user.getEmail(), user.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		UserDetails userDetails = service.loadUserByUsername(user.getEmail());
		String token = jwtUtility.generateToken(userDetails);
		JwtResponse response = new JwtResponse(token);
		return response;
	}
	
}
