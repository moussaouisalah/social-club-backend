package com.example.Training.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Training.entity.Member;
import com.example.Training.entity.User;
import com.example.Training.service.ClubService;
import com.example.Training.service.MemberService;
import com.example.Training.service.UserService;

@RestController
@Validated
public class MemberController {

	@Autowired
	private MemberService service;
	@Autowired
	private UserService serviceUser;
	@Autowired
	private ClubService serviceClub;
	
	@PostMapping("/members")
	public Member addMember(@Valid @RequestBody Member member) {
		return service.saveMember(member);	
	}
	
	/*
	@GetMapping("/members")
	public List <Member> findMembers() {
		return service.getAllMembers();
	}*/
	
	@GetMapping(path="/members",produces = "application/json")
	public List<Member> findAllUser( @RequestParam("clubId") Optional<Integer>  clubId, @RequestParam("userId") Optional<Integer> userId){	
			return service.getMemberById(clubId, userId);
	}
	 
	@PutMapping(path="/members")
	  public Member updateMember(@RequestBody Member membe){
		return service.updateMember(membe);
	}
}
