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

import com.srccodes.service.AddressService;

@RestController
@RequestMapping(value = "/address/api", produces = "application/json", consumes = "application/json")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/{id}")
	public String doGet(@PathVariable("id") String id) {	
		return addressService.select(id);
	}
	
	@PostMapping
	public String doPost(@RequestBody String addressModelString) {
		return "{ \"id\": " + Integer.parseInt(addressService.insert(addressModelString)) + "}" ;
	}
	
	@PutMapping("/{id}")
	public String doPut(@PathVariable("id") String id,@RequestBody String addressModelString) {
		return addressService.update(id, addressModelString);
	}

	@DeleteMapping("/{id}")
	public String doDelete(@PathVariable("id") String id) {	
		return addressService.delete(id);
	}
}
