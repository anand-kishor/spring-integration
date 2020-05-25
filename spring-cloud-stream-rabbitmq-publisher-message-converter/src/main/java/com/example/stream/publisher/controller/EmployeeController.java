package com.example.stream.publisher.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stream.publisher.model.Employee;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	private Source source;

	@PostMapping
	public void publishMessage(@RequestBody Employee emp)
	{
		source.output().send(MessageBuilder.withPayload(emp).setHeader("x-correlationId", "123456").build());
	}

}
