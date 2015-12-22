package tmh.cache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import tmh.cache.model.User;

@Service
public class UserService {
	
	@Cacheable(value = "userCache", key="#name")
	public User getPwd(String name){
		User user = new User();
		System.out.println("数据来自数据库......");
		return user;
	}
}
