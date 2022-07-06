package com.example.restfulwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDaoService {
	private static List<User> users = new ArrayList<User>();
	
	private static int usersCount = 3;
	static {
		users.add(new User(1, "Kenneth", new Date()));
		users.add(new User(2, "Alice", new Date()));
		users.add(new User(3, "Elena", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public Map<String, Object> findMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", new User(1, "Kenneth", new Date()));
		map.put("user1", new User(2, "Alice", new Date()));
		return map;
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
}
