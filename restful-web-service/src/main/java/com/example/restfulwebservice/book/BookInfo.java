package com.example.restfulwebservice.book;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@ApiModel(description = "책의 정보 도메인 객체")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookInfo {

	@Id
	@GeneratedValue
	private Long id;
	
	
	private String name; // 책이름
	private String publisher; // 출판사
	private String author;  // 저자
	private String pubDate; // 출판일
	
}
