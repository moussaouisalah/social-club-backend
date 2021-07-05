package com.example.Training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.Training.entity.Post;


public interface PostRepositoryPag extends PagingAndSortingRepository<Post, Integer> {
	List<Post> findByClubId(Optional<Integer> ids,Pageable pg);
	List<Post> findByUserId(Optional<Integer> ids,Pageable pg);
	//user_owner UserOwner
}
