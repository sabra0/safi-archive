package com.safi.demoArchive.entities;

import java.util.Collection;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "user",uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String email;
	
	private String password;
	
	 @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinTable(
	       name="user_role",
	       joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="id")},
	       inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="id")})
	    private Collection<Role> roles;
	
	/*
	 * @OneToMany(targetEntity = Picture.class, cascade =
	 * CascadeType.ALL,mappedBy="user",fetch = FetchType.EAGER ) private
	 * List<Picture> pictures = new ArrayList<Picture>();
	 */
	
	/*
	 * //function to mapping public void addPictureToUser(Picture p) {
	 * this.pictures.add(p); if(p.getUser() != this) p.setUser(this); }
	 */
	
	
	public User() {
		
	}
	public User( String email, String password,Collection < Role > roles) {
		super();
		this.email = email;
		this.password = password;
		this.roles=roles;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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

	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection	<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", roles=" + roles + "]";
	}
	
}
