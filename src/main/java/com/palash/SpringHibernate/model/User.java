/**
 * 
 */
package com.palash.SpringHibernate.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.sun.istack.internal.NotNull;

@Entity
@Table(
		name = "user",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "Email"),
				@UniqueConstraint(columnNames = "UserName")
		}
)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {
	@Id
	@Column(name = "UserID")
	@GeneratedValue
	private int UserID;
	@NotNull
	private String FullName;
	@NotNull
	private String Email;
	@NotNull
	private String UserName;
	@NotNull
	private String Password;
	private boolean IsActive;
	private String Role;
	
	public User() {
		
	}
	public User(String FullName,String UserName,String Password) {
		super();
		this.FullName= FullName;
		this.UserName= UserName;
		this.Password= Password;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public boolean isIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	
}
