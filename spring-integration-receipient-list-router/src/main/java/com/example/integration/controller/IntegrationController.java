package com.example.integration.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.integration.model.Student;
import com.example.integration.service.IntegrationGateway;

@RestController
@RequestMapping("/integration")
public class IntegrationController {
	@Autowired
	private IntegrationGateway integrationGateway;
	
	@PostMapping("/student")
	public void processStudentDetails(@RequestBody Student student)
	{
	 integrationGateway.process(student);
	}
}