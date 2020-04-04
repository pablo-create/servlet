package com.srccodes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srccodes.service.UserService;

@RestController
@RequestMapping(value = "/user/api", produces = "application/json", consumes = "application/json")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public String doGet(@PathVariable("id") String id) {
		return userService.select(id);
	}
	
	@PostMapping
	public String doPost(@RequestBody String userModelString) {	
		return "{ \"id\": " + Integer.parseInt(userService.insert(userModelString)) + "}" ;
	}
	
	@PutMapping("/{id}")
	public String doPut(@PathVariable("id") String id,@RequestBody String userModelString) {
		return userService.update(id,userModelString);
	}
	
	@DeleteMapping("/{id}")
	public String doDelete(@PathVariable("id") String id) {
		return userService.delete(id);
	}
}
