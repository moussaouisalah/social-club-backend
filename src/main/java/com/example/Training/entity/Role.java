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
public class Role {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="roleId")
	private int roleId;
	@Column(name="name")
	private String name;
	@Column(name="isDefault")
	 private Boolean isDefault;
	@Column(name="canInvite")
	 private Boolean canInvite;
	@Column(name="canRemove")
	 private Boolean canRemove;
	@Column(name="canPost")
	 private Boolean canPost;
	@Column(name="canEdit")
	 private Boolean canEdit;
	
	@OneToMany(mappedBy = "role",cascade = CascadeType.ALL, orphanRemoval = true)
	 @JsonIgnore
   private List<Member> role_members;
	
	@ManyToOne
	@JoinColumn(name="clubId")
	private Club club ;

	
	public Role() {
		super();
	}

	public Role( String name, Boolean isDefault, Boolean canInvite, Boolean canRemove, Boolean canPost,
			Boolean canEdit, Club club) {
		super();
		this.name = name;
		this.isDefault = isDefault;
		this.canInvite = canInvite;
		this.canRemove = canRemove;
		this.canPost = canPost;
		this.canEdit = canEdit;
		this.club = club;
	}

	public int getUserId() {
		return roleId;
	}

	public void setUserId(int userId) {
		this.roleId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Boolean getCanInvite() {
		return canInvite;
	}

	public void setCanInvite(Boolean canInvite) {
		this.canInvite = canInvite;
	}

	public Boolean getCanRemove() {
		return canRemove;
	}

	public void setCanRemove(Boolean canRemove) {
		this.canRemove = canRemove;
	}

	public Boolean getCanPost() {
		return canPost;
	}

	public void setCanPost(Boolean canPost) {
		this.canPost = canPost;
	}

	public Boolean getCanEdit() {
		return canEdit;
	}

	public void setCanEdit(Boolean canEdit) {
		this.canEdit = canEdit;
	}

	public List<Member> getRole_members() {
		return role_members;
	}

	public void setRole_members(List<Member> role_members) {
		this.role_members = role_members;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
	
	
	
}
