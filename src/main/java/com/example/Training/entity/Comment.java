package com.example.Training.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="comment") 
public class Comment {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="text")
	private String text;
	@Column(name="creationDateTime")
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Date creationDateTime;
	
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user_owner_comment ;
	
	/*@ManyToMany
	@JoinColumn(name="userid")
	private User user ;*/
	
	@ManyToOne
	@JoinColumn(name="postid")
	private Post post ;
	
	@ManyToMany
	 @JsonIgnore
	 public Set<User> user_likecomment;

	
	
	
	
	
	
	
	public Comment() {
		super();
	}

	public Comment(int id, String text, Date creationDateTime, User user_owner_comment, Post post,
			Set<User> user_likecomment) {
		super();
		this.id = id;
		this.text = text;
		this.creationDateTime = creationDateTime;
		this.user_owner_comment = user_owner_comment;
		this.post = post;
		this.user_likecomment = user_likecomment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public User getUser_owner_comment() {
		return user_owner_comment;
	}

	public void setUser_owner_comment(User user_owner_comment) {
		this.user_owner_comment = user_owner_comment;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Set<User> getUser_likecomment() {
		return user_likecomment;
	}

	public void setUser_likecomment(Set<User> user_likecomment) {
		this.user_likecomment = user_likecomment;
	}

	
	
	
	
	
	
}
