package com.example.Training.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Training.entity.Club;
import com.example.Training.entity.Comment;
import com.example.Training.entity.User;
import com.example.Training.model.SearchResult;
import com.example.Training.repository.ClubRepository;
import com.example.Training.repository.UserRepository;
import com.example.Training.service.CommentService;

@RestController
@Validated
public class SearchController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ClubRepository clubRepository;
	
	@GetMapping(path="/search",produces = "application/json")
	public List<SearchResult> searchAll( @RequestParam("query") String query){	
			List<User> fnUsers = userRepository.findByFirstNameContainingOrLastNameContaining(query, query);
			List<Club> clubs = clubRepository.findByNameContaining(query);
			List<SearchResult> srList = new ArrayList<SearchResult>();
			for(User user : fnUsers) {
				SearchResult sr = new SearchResult(user.getId(), "user", user.getFirstName() + " " + user.getLastName(), user.getProfileImage());
				srList.add(sr);
			}
			for(Club club : clubs) {
				SearchResult sr = new SearchResult(club.getId(), "club", club.getName(), club.getProfileImage());
				srList.add(sr);
			}
			return srList;
	}
	
	@GetMapping(path="/searchusers",produces = "application/json")
	public List<SearchResult> searchUsers( @RequestParam("query") String  query){	
		List<User> fnUsers = userRepository.findByFirstNameContainingOrLastNameContaining(query, query);
		List<SearchResult> srList = new ArrayList<SearchResult>();
		for(User user : fnUsers) {
			SearchResult sr = new SearchResult(user.getId(), "user", user.getFirstName() + " " + user.getLastName(), user.getProfileImage());
			srList.add(sr);
		}
		return srList;
	}
	
	
}
