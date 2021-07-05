package com.example.Training.entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class User implements UserDetails {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="firstName")	
	@NotEmpty
	 private String firstName;
	@Column(name="lastName")	
	@NotEmpty
	 private String lastName;
	@Column(name="email")
	@Email(message ="Email format not valid")
	private String email;
	@Column(name="password")
	@NotEmpty(message=" password shold not empty")
	private String password;
	@Column(name="profileImage")
	 private String profileImage;
	@Column(name="coverImage")
	 private String coverImage;
	
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
	 @JsonIgnore
  private List<Member> user_members;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
	 @JsonIgnore
    private List<Post> posts;

	@OneToMany(mappedBy = "user_owner_comment",cascade = CascadeType.ALL, orphanRemoval = true)
	 @JsonIgnore
    private List<Comment> user_comments;
	
	
	
	@ManyToMany
	 @JsonIgnore
	 private Set<Post> like_posts;
	
	@ManyToMany
	 @JsonIgnore
	 private Set<Comment> like_comments;
	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}

	public User() {
		super();
	}

	public User(int id, @NotEmpty String firstName, @NotEmpty String lastName,
			@Email(message = "Email format not valid") String email,
			@NotEmpty(message = " password shold not empty") String password, String profileImage, String coverImage,
			List<Member> user_members, List<Post> posts, List<Comment> user_comments, Set<Post> like_posts,
			Set<Comment> like_comments) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.profileImage = profileImage;
		this.coverImage = coverImage;
		this.user_members = user_members;
		this.posts = posts;
		this.user_comments = user_comments;
		this.like_posts = like_posts;
		this.like_comments = like_comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public List<Member> getUser_members() {
		return user_members;
	}

	public void setUser_members(List<Member> user_members) {
		this.user_members = user_members;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Comment> getUser_comments() {
		return user_comments;
	}

	public void setUser_comments(List<Comment> user_comments) {
		this.user_comments = user_comments;
	}

	public Set<Post> getLike_posts() {
		return like_posts;
	}

	public void setLike_posts(Set<Post> like_posts) {
		this.like_posts = like_posts;
	}

	public Set<Comment> getLike_comments() {
		return like_comments;
	}

	public void setLike_comments(Set<Comment> like_comments) {
		this.like_comments = like_comments;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
	
	


	


	
	
}
