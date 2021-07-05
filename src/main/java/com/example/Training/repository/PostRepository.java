package com.example.Training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Training.entity.Post;
import com.example.Training.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
       // List<Post> findByUsers(int id);
	 		Post findByUsers(int id);

}
