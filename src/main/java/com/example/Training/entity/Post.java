package com.example.Training.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id")	
	 private int id;
	@Column(name="text")	
	 private String text;
	@Column(name="file")
	 private String file;
	@Column(name="creationdate")
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Date creationdate;
	

	@ManyToOne
	@JoinColumn(name="userId")
	private User user ;
	
	@ManyToOne
	@JoinColumn(name="clubId")
	private Club club ;
	
	@ManyToMany
	 @JsonIgnore
	 private Set<User> users;
	
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval = true)
	 @JsonIgnore
   private List<Comment> post_comment;
	
	
	
	
	
	

	public Post() {
		super();
	}
	
	

	



	public Post(int id, String text, String file, Date creationdate, User user, Club club, Set<User> users,
			 List<Comment> post_comment) {
		super();
		this.id = id;
		this.text = text;
		this.file = file;
		this.creationdate = creationdate;
		this.user = user;
		this.club = club;
		this.users = users;
		this.post_comment = post_comment;
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

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	

	

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	

	public List<Comment> getPost_comment() {
		return post_comment;
	}

	public void setPost_comment(List<Comment> post_comment) {
		this.post_comment = post_comment;
	}

	
	
	
	
}
