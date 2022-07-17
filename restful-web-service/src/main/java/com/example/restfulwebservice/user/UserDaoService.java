package com.example.restfulwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDaoService {
	private static List<User> users = new ArrayList<User>();
	
	private static int usersCount = 3;
	static {
		users.add(new User(1, "Kenneth", new Date(), "pass1", "701010-1111111"));
		users.add(new User(2, "Alice", new Date(), "pass2", "801010-2222222"));
		users.add(new User(3, "Elena", new Date(), "pass3", "901010-3333333"));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++usersCount);
			log.info("new User = {}", user);
		}
		
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for (User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deletebyId(int id) {
		Iterator<User> iterator =  users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
	public User updateById(int id, String name) {
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			if(user.getId() == id) {
				user.setName(name);
				user.setJoinDate(new Date());
				return user;
			}
		}
		return null;
	}
}
