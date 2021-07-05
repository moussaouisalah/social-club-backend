package com.example.Training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Training.entity.Club;
import com.example.Training.entity.Role;
import com.example.Training.entity.User;
import com.example.Training.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository repository;
	
	
	public Role saveRole(Role role) {
		return repository.save(role);
	}
	
	public List<Role> getAllRoles(Optional<Integer> clubid ){
		return  repository.findByClubId(clubid);
	}
	
	public Role getRoleById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	
}
