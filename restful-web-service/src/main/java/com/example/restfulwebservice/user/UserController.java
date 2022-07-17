package com.example.restfulwebservice.user;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
	private UserDaoService service;
	
	public UserController(UserDaoService service) {
		this.service = service; 
	}

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	@GetMapping("users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		 
		if(user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not fount", id));
		}
		
		//HATEOAS
		// "all-users", SERVER_PATH+"/users"
		//retieveAllUsers
		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkTo.withRel("all-users"));
		
		return model;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				                                  .path("/{id}")
				                                  .buildAndExpand(savedUser.getId())
				                                  .toUri();
		return ResponseEntity.created(location).build(); //created = 201 코드 Header에 Location속성의 value
		//ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		 User user = service.deletebyId(id);
		 
		 if(user == null) {
			 throw new UserNotFoundException(String.format("ID[%s] not found", id));
		 }
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
		User updateUser = service.updateById(id, user.getName());
		
		if(updateUser == null) {
			 throw new UserNotFoundException(String.format("ID[%s] not found", id));
		 }
		
		//return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		return ResponseEntity.noContent().build();
	}
}
