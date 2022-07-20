package com.example.restfulwebservice.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Post {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String description;
	
	private String delivery;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "USER_ID")
	private User user;
}
