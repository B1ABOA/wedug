package com.b1aboa.wedug.dto;

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

public class UserDTO {
	private String userId;
	private String nickname;
	private String password;
	private char gender;
	private int birthYear;
	private int nation;
}
