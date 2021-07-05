package com.example.Training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Training.entity.User;


public interface UserRepository extends JpaRepository<User,Integer> {

	

	/*User  findByEmail(String email);*/
	public Optional<User> findByEmail(String email);
	  List<User> findByIdIn(Optional<List<Integer>> ids);

	
	
	User findByFirstName(String first_name);
}
