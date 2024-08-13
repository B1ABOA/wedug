package com.b1aboa.wedug.dto;

import com.b1aboa.wedug.entity.NationInfo;
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
	private String confirmPassword;
	private char gender;
	private int birthYear;
	private NationInfo nationInfo;
}
