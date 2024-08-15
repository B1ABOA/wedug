package com.b1aboa.wedug.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Table(name="wedug_user")
@Entity
public class User{
	
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

	@ManyToOne
	@JoinColumn(name = "nation_code")
	private NationInfo nationCode;
}
