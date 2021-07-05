package com.example.Training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Training.entity.Club;
import com.example.Training.entity.Member;
import com.example.Training.entity.Role;
import com.example.Training.entity.User;
import com.example.Training.repository.MemberRepository;
import com.example.Training.repository.RoleRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository repository;
	
	@Autowired
	private RoleRepository repositoryRole;
	
	
	@Autowired
	private UserService userService;
	@Autowired
	private ClubService clubService;
	
	
	
	public Member saveMember(Member member) {
		return repository.save(member);
	}
	
	public List<Member> getAllMembers(){
		return repository.findAll();
	}
	
	public List<Member> getMemberById( Optional<Integer>  clubid , Optional<Integer> userid) {
		if(clubid.isPresent()) {
			return  repository.findByClubId(clubid);
		}
		
		else {
		
			return  repository.findByUserId(userid);
			
		}		 	
	}
	
	
	public Member updateMember( Member member) {
		Member existingMemberbyId =	repository.findById(member.getId()).orElse(null);
		int userId = member.getUser().getId();
		int clubId = member.getClub().getId();
		String membertype = member.getMemberType();
		
		
		
		if(membertype == null) {
			Role role =  member.getRole();
			if(role != null){
				int roleid =  role.getRoleId();
				Member existingMember =	repository.findByClubIdAndUserId(clubId,userId);
				//existingMember.setRole(roleid);
				return repository.save(existingMember);
			}
		}
		
		else if(membertype.equals("member")) {
		Member existingMember =	repository.findByClubIdAndUserId(clubId,userId);
		existingMember.setMemberType(membertype);
		return repository.save(existingMember);
		}
		else if(membertype.equals("requested"))
		{
			Role role = repositoryRole.findByClubIdAndIsDefault(clubId,true);
			member.setRole(role);
			return repository.save(member);
		}
		
		else if(membertype.equals("invited"))
		{
			Role role = repositoryRole.findByClubIdAndIsDefault(clubId,true);
			member.setRole(role);
			return repository.save(member);
		}
		else if(membertype.equals("refused"))
		{
			Member memberDelete = repository.findByClubIdAndUserId(clubId,userId);
			 repository.deleteById(memberDelete.getId());
			 return memberDelete;
		}
		
		
			return member;
		
		 
		
		
		
	}
	
}
