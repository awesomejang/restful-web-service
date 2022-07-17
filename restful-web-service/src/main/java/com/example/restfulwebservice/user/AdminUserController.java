package com.example.restfulwebservice.user;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
	private UserDaoService service;
	
	public AdminUserController(UserDaoService service) {
		this.service = service; 
	}

	@GetMapping("/users")
	//@ApiResponse()
	public MappingJacksonValue retrieveAllUsers() {
		List<User> users = service.findAll();
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(users);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	// GET /admin/users/1 -> /admin/v1/users/1
	//@GetMapping("/v1/users/{id}")
	//@GetMapping(value = "/v1/users/{id}", params = "version=1")
	//@GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1")
	@GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json")
	public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
		User user = service.findOne(id);
		 
		if(user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not fount", id));
		}
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
										  .filterOutAllExcept("id", "name", "joinDate", "ssn"); //포함시키고자 하는 필더값?
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter); //대상빈, 필터
		
		MappingJacksonValue mapping = new MappingJacksonValue(user);
		mapping.setFilters(filters);
		return mapping;
	}
	 
	//@GetMapping("/v2/users/{id}")
	//@GetMapping(value = "/users/{id}", params = "version=2")
	//@GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")
	@GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json")
	public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
		User user = service.findOne(id);
		 
		if(user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not fount", id));
		}
		
		// User -> UserV2
		UserV2 userV2 = new UserV2();
		BeanUtils.copyProperties(user, userV2); // 복사
		userV2.setGrade("VIP");
		
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
										  .filterOutAllExcept("id", "name", "joinDate", "grade"); //포함시키고자 하는 필더값?
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter); //대상빈, 필터
		
		MappingJacksonValue mapping = new MappingJacksonValue(userV2);
		mapping.setFilters(filters);
		return mapping;
	}
	
}
