package com.example.Training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Training.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	List<Role> findByClubId(Optional<Integer> ids);
	Role findByClubIdAndIsDefault(int clubId, Boolean dflt);
}
