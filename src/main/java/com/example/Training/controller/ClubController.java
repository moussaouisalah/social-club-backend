package com.example.Training.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Training.entity.Club;
import com.example.Training.entity.Post;
import com.example.Training.entity.User;
import com.example.Training.service.ClubService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@Validated
public class ClubController {
	@Autowired
	private ClubService service;
	
	

	@Value("${file.upload-dir}")
	String FILE_DIRECTORY;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(path = "/clubs", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Valid
	public Club addCreat(@AuthenticationPrincipal User connectedUser,@RequestParam("club") String club, @RequestParam("profileImage") MultipartFile profileImage,@RequestParam("coverImage") MultipartFile coverImage)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Club postobj = mapper.readValue(club, Club.class);
		
		String photoPath = "src/main/resources/uploads/" + UUID.randomUUID()
				+ profileImage.getOriginalFilename().replaceAll(" ", "").trim();
		
		String coverImageclub = "src/main/resources/uploads/" + UUID.randomUUID()
		+ coverImage.getOriginalFilename().replaceAll(" ", "").trim();
		
		postobj.setProfileImage(photoPath);
		postobj.setCoverImage(coverImageclub);

		byte[] bytes = profileImage.getBytes();
		File myFile = new File(photoPath);
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(bytes);
		
		byte[] bytescover = coverImage.getBytes();
		File filecover = new File(coverImageclub);
		FileOutputStream foscover = new FileOutputStream(filecover);
		foscover.write(bytescover);
		return service.saveClub(postobj,connectedUser);
		
	}

	
	
	@GetMapping(path="/clubs",produces = "application/json")
	public List<Club> findAllUser( @RequestParam("ids")   Optional <List<Integer>> ids){
		if(ids.isPresent()) {
			return service.getClubListid(ids);
		}else {
			return service.getAllClubs();
		}
		
	}
	
	@GetMapping("/clubs/{id}")
	public Club findUserById(@PathVariable int id) {
		return service.getClubById(id);
	}
	
	@PutMapping(value="/clubs/{id}")
	  public ResponseEntity<Club> updateUser(@PathVariable(value = "id") int id,
            @RequestBody Club club){
		return new ResponseEntity<>(service.updateClub(id, club), HttpStatus.OK);
	}
	
}
