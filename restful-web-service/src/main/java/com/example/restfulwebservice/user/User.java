package com.example.restfulwebservice.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	
	private Integer id;
	@Size(min = 2)
	private String name;
	@Past
	private Date joinDate;
}
