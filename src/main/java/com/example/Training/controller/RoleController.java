package com.example.Training.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Training.entity.Member;
import com.example.Training.entity.Role;
import com.example.Training.entity.User;
import com.example.Training.repository.RoleRepository;
import com.example.Training.service.RoleService;

@RestController
@Validated
public class RoleController {
	@Autowired
	private RoleService service;
	
	@PostMapping("/roles")
	public Role addRole(@Valid @RequestBody Role role) {
		
		return service.saveRole(role);
	}
	
	@GetMapping("/roles/{id}")
	public Role findRoleById(@PathVariable int id) {
		return service.getRoleById(id);
	}
	
	@GetMapping(path="/roles",produces = "application/json")
	public List<Role> findAllUser( @RequestParam("clubId") Optional<Integer>  clubId){	
			return service.getAllRoles(clubId);
	}
	
}
