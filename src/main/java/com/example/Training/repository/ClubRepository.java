package com.example.Training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Training.entity.Club;
import com.example.Training.entity.User;

public interface ClubRepository extends JpaRepository<Club, Integer>{

	  List<Club> findByIdIn(Optional<List<Integer>> ids);
	  List<Club> findByNameContaining(String name);

}
