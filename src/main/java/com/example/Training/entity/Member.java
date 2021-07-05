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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="members") 
public class Member {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user ;
	
	@ManyToOne
	@JoinColumn(name="clubId")
	private Club club ;
	
	@Column(name="memberType")
	private String  MemberType ;
	
	@ManyToOne
	@JoinColumn(name="roleId")
	private Role role ;

	
	
	
	public Member() {
		super();
	}

	public Member( User user, Club club, String memberType, Role role) {
		super();
		this.user = user;
		this.club = club;
		MemberType = memberType;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getMemberType() {
		return MemberType;
	}

	public void setMemberType(String memberType) {
		MemberType = memberType;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
