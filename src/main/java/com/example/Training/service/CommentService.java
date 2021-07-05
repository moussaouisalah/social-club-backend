package com.example.Training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Training.entity.Comment;
import com.example.Training.entity.Member;
import com.example.Training.entity.Role;
import com.example.Training.repository.CommentRepository;
import com.example.Training.repository.MemberRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository repository;
	
	
	public Comment saveComment(Comment comment) {
		return repository.save(comment);
	}
	
	public List<Comment> getAllComment(Optional<Integer> postid ){
		return  repository.findByPostId(postid);
	}
}
