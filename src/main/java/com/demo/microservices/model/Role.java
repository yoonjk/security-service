package com.demo.microservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
public class Role {
	private String roleName;
	private String roleDesc;
	private String state;
}
