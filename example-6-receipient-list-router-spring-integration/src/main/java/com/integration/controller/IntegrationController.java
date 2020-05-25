package com.integration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integration.model.Address;
import com.integration.model.Student;
import com.integration.service.IntegrationGateway;

@RestController
@RequestMapping(value="/integrate")
public class IntegrationController {
	
	@Autowired
	private IntegrationGateway integrationGateway;
	@GetMapping(value="{name}")
	public String getMessage(@PathVariable("name") String name )
	{
		return integrationGateway.sendMessage(name);
	}
	@PostMapping("/student")
	public void processStudentDetails(@RequestBody Student student)
	{
		 integrationGateway.process(student);
	}
	@PostMapping("/address")
	public void processAddressDetails(@RequestBody Address address)
	{
		 integrationGateway.process(address);
	}

}
