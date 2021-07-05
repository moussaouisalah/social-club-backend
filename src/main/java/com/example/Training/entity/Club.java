package com.example.Training.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Club {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="profileImage")
	 private String profileImage;
	@Column(name="coverImage")
	 private String coverImage;
	@Column(name="primaryColor")
	 private String primaryColor;
	
	@OneToMany(mappedBy = "club",cascade = CascadeType.ALL, orphanRemoval = true)
	 @JsonIgnore
 private List<Member> club_members;
	
	@OneToMany(mappedBy = "club",cascade = CascadeType.ALL, orphanRemoval = true)
	 @JsonIgnore
	 private List<Post> club_posts;
	
	/*@ManyToOne
	@JoinColumn(name="clubId")
	private Member club ;*/
	
	@OneToMany(mappedBy = "club",cascade = CascadeType.ALL, orphanRemoval = true)
	 @JsonIgnore
	 private List<Role> club_roles;
	
	
	

	public Club() {
		super();
	}

	public Club(int id, String name, String profileImage, String coverImage, String primaryColor,
			List<Member> club_members, List<Post> club_posts, List<Role> club_roles) {
		super();
		this.id = id;
		this.name = name;
		this.profileImage = profileImage;
		this.coverImage = coverImage;
		this.primaryColor = primaryColor;
		this.club_members = club_members;
		this.club_posts = club_posts;
		this.club_roles = club_roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getPrimaryColor() {
		return primaryColor;
	}

	public void setPrimaryColor(String primaryColor) {
		this.primaryColor = primaryColor;
	}

	public List<Member> getClub_members() {
		return club_members;
	}

	public void setClub_members(List<Member> club_members) {
		this.club_members = club_members;
	}

	public List<Post> getClub_posts() {
		return club_posts;
	}

	public void setClub_posts(List<Post> club_posts) {
		this.club_posts = club_posts;
	}

	public List<Role> getClub_roles() {
		return club_roles;
	}

	public void setClub_roles(List<Role> club_roles) {
		this.club_roles = club_roles;
	}


	
	
	
	

}
