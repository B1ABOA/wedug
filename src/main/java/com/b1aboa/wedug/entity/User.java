package com.b1aboa.wedug.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
public class User {
	
	@Id
	@Column(length=20)
	private String userId;
	
	@Column(length=20,nullable= false, unique=true)
	private String nickname;
	
	@Column(length=30,nullable= false)
	private String password;
	
	@Column(length=1,nullable= false)
	private char gender;
	
	@Column(length=4,nullable= false)
	private int birthYear;
	
	@Column(nullable= false)
	private int nation;
	
}
