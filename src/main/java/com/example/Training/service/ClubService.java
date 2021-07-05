package com.example.Training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Training.entity.Club;
import com.example.Training.entity.Member;
import com.example.Training.entity.Role;
import com.example.Training.entity.User;
import com.example.Training.repository.ClubRepository;
import com.example.Training.repository.MemberRepository;
import com.example.Training.repository.RoleRepository;

	@Service
	public class ClubService {
		@Autowired
		private ClubRepository repository;
		@Autowired
		private RoleRepository repositoryRole;
		@Autowired
		private MemberRepository repositoryMember;
		
		
	public Club saveClub(Club club,User connectedUser) {	
		club = repository.save(club);
			Role role = new Role( "Admin", false, true, true, true, true, club);
			Role role_member = new Role( "Membre", true, true, false, true, false, club);
			role = repositoryRole.save(role);
			 repositoryRole.save(role_member);
			
			Member member = new Member(connectedUser,club,"member",role);
			repositoryMember.save(member);
			return club;
		}
	
	public List<Club> getAllClubs(){
		return repository.findAll();
	}
	
	public Club getClubById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Club> getClubListid(Optional<List<Integer>> ids){
		return repository.findByIdIn(ids);
	}
	
	public Club updateClub( int id , Club club)
	{
		

		Club clubexist = repository.findById(id).orElse(null);
		if(repository.findById(id).isPresent()) {
			clubexist.setName(club.getName());
			clubexist.setPrimaryColor(club.getPrimaryColor());
			return repository.save(clubexist);
		}
		else {
			return null;
		}
		
	}
	
	public String removeClub(int id) {
		repository.deleteById(id);
		return "Club has removed !!"+id;
	}
	
	
	
}
