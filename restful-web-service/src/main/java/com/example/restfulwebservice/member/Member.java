package com.example.restfulwebservice.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Member {
	
	@Id
	@GeneratedValue
	private Long id;
	private String regNumber;
	private String name;
	private String address;
	private String email;
}
