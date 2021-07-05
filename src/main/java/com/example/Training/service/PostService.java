package com.example.Training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Training.entity.Post;
import com.example.Training.entity.User;
import com.example.Training.repository.PostRepository;
import com.example.Training.repository.PostRepositoryPag;
import com.example.Training.repository.UserRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	@Autowired
	private UserRepository userrepository;
	@Autowired
	private PostRepositoryPag postRepositoryPag;
	
	public  Post savePost(Post post) {
		return repository.save(post);			
	}
	
	public  Boolean savePostLike( int id ,User connectedUser) {
		Boolean isliked ;
		Post postid=repository.findById(id).orElse(null);
		if(getPostById(id, connectedUser.getId())) {
			
			postid.getUsers().removeIf(user -> user.getId()==connectedUser.getId());
			isliked=false;
		}
		else {
			postid.getUsers().add(connectedUser);
			isliked=true;
		}
		
		repository.save(postid);
		return isliked;			
	}
	
	
	public List<Post> savePosts(List<Post> posts){
		return repository.saveAll(posts);
	}
	
	public List<Post> getPosts(){
		return repository.findAll();
	}
	
	/*** likeby **************/
	
	public Boolean getPostById(int id , int isLikedBy) {
		Post post_id = repository.findById(id).orElse(null);
		User user = userrepository.findById(isLikedBy).orElse(null);
		System.out.print(post_id.getUsers().contains(user));
		for(User userid : post_id.getUsers()  ) {
			if(userid.getId() == user.getId()) {
				return true;
			}
		}
		return false;
		
	}
	
	
	
	public String removePost(int id) {
		repository.deleteById(id);
		return "Post has removed !!"+id;
	}
	

	
	public List<Post> findPaginationPost(int pageNo , int pageSize,Optional<Integer>  clubid , Optional<Integer> userid){
		if(clubid.isPresent()) {
			Pageable paging1 = PageRequest.of(pageNo, pageSize,Sort.by(Sort.Direction.DESC,"creationdate"));
			List<Post> pagedResult = postRepositoryPag.findByClubId(clubid,paging1);
			return pagedResult;
		}
		
		else {
			
			Pageable paging1 = PageRequest.of(pageNo, pageSize,Sort.by(Sort.Direction.DESC,"creationdate"));
			List<Post> pagedResult = postRepositoryPag.findByUserId(userid,paging1);
			return pagedResult;
			
		}	
		
	}
	
	
	
	/*public List<Post> findPaginationPost(int pageNo , int pageSize){
		
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(Sort.Direction.DESC,"creationdate"));
		Page<Post> pagedResult = postRepositoryPag.findAll(paging);
		return pagedResult.toList();
	}*/
	
	
	
}
