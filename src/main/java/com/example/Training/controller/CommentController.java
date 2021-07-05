package com.example.Training.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Training.entity.Comment;
import com.example.Training.entity.Role;
import com.example.Training.service.CommentService;

@RestController
@Validated
public class CommentController {
	@Autowired
	private CommentService service;
	
	@PostMapping("/comments")
	public Comment addComment(@RequestBody Comment comment) {
		
		return service.saveComment(comment);
	}
	
	@GetMapping(path="/comments",produces = "application/json")
	public List<Comment> findAllComment( @RequestParam("postId") Optional<Integer>  postId){	
			return service.getAllComment(postId);
	}
	
}
