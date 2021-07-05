package com.example.Training.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Training.entity.Post;
import com.example.Training.entity.User;
import com.example.Training.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@Validated

//@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class PostController {
	@Autowired
	private PostService service;
	ServletContext context;
	Date date;

	@Value("${file.upload-dir}")
	String FILE_DIRECTORY;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(path = "/addPost", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Valid
	public Post addPost(@RequestParam("post") String post, @RequestParam("file") MultipartFile file)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Post postobj = mapper.readValue(post, Post.class);
		String photoPath = "src/main/resources/uploads/" + UUID.randomUUID()
				+ file.getOriginalFilename().replaceAll(" ", "").trim();
		postobj.setFile(photoPath);
		byte[] bytes = file.getBytes();
		File myFile = new File(photoPath);
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(bytes);
		return service.savePost(postobj);
		
	}

	@PostMapping("/posts")
	public List<Post> addPosts(@RequestBody List<Post> posts) throws IOException {
		return service.savePosts(posts);
	}
	@PostMapping("/posts/{id}/like")
	public Boolean addPosts(@PathVariable int id,@AuthenticationPrincipal User connectedUser) throws IOException {
		return service.savePostLike(id,connectedUser);
	}
	
	
	/*@GetMapping(path = "/posts")
	public ResponseEntity<List<Post>> findAllPost() {
		List<Post> posts = service.getPosts();
		return new ResponseEntity<>(posts, HttpStatus.OK);

	}
*/
	@GetMapping("/posts/{id}")
	public Boolean findPostById(@PathVariable int id ,@RequestParam("isLikedBy") int isLikedBy) {
		return service.getPostById(id,isLikedBy);
	}

	@DeleteMapping("/deletePost/{id}")
	public String deletePost(@PathVariable int id) {
		return service.removePost(id);
	}
	
	@GetMapping("/posts")
	public List<Post> getPaginated(@RequestParam("pageNo") int pageNo ,@RequestParam("pageSize") int pageSize ,
			@RequestParam("clubId") Optional<Integer>  clubId, @RequestParam("userId") Optional<Integer> userId){
		return service.findPaginationPost(pageNo, pageSize,clubId,userId);
	}
	
	
	/*@GetMapping("/posts/{pageNo}/{pageSize}")
	public List<Post> getPaginated(@PathVariable int pageNo , @PathVariable int pageSize ){
		return service.findPaginationPost(pageNo, pageSize);
	}*/
	
	

}
