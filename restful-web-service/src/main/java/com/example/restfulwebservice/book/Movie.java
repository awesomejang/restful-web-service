package com.example.restfulwebservice.book;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@ApiModel(description = "영화 정보 도메인 객체")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {

	@Id
	@GeneratedValue
	private Long id;
	
	private String movieName; // 영화이름
	private String director; // 감독
	private String filmRating;  // 관람등급
	private LocalDate openDate; // 개봉일
	private LocalDate endDate;  //종영일
	//private int 
	
}
