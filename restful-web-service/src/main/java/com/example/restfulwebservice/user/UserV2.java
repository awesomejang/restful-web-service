package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("UserInfoV2") // Filter 적용시에 사용할 객체명 
public class UserV2 extends User{
	private String grade;
}
