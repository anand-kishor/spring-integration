package com.example.stream.kafka.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stream.kafka.model.Employee;

@RestController
@RequestMapping("/emp")
public class EmployeePublisher {
	@Autowired
	private MessageChannel output;
	@PostMapping()
	public Employee sendEmp(@RequestBody Employee emp)
	{
		output.send(MessageBuilder.withPayload(emp).build());
		return emp;
	}

}
