package com.example.Training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Training.entity.Club;
import com.example.Training.entity.Member;
import com.example.Training.entity.User;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	 
	List<Member> findByClubId(Optional<Integer> ids);
	List<Member> findByUserId(Optional<Integer> ids);
	//Member findByRoleId(int id);
	Member findByClubIdAndUserId(int clubId , int userId);
	/*List<Member> findByRoleId(Optional<Integer> ids);*/	
}
