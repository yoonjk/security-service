package com.demo.microservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class UserInfoRole {
	private String username;
	private String password;
	private String roleName;
}
