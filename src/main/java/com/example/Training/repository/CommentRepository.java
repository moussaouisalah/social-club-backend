package com.example.Training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Training.entity.Comment;
import com.example.Training.entity.Member;


public interface CommentRepository extends JpaRepository<Comment, Integer>{

	List<Comment> findByPostId(Optional<Integer> ids);

}
