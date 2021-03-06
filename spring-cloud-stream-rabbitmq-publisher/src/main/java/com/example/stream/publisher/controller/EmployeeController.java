package com.example.stream.publisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stream.publisher.model.Employee;

@RestController
@RequestMapping(value="/emp")
public class EmployeeController {
	@Autowired
	private Source source;
	@Autowired
	@Qualifier("orders")
	private MessageChannel orders;
	@PostMapping
	public void publishMessage(@RequestBody Employee emp)
	{
		source.output().send(MessageBuilder.withPayload(emp).setHeader("x-correlationId", "123456").build());
	}

	@PostMapping("/orders")
	public void publishMessageOrders(@RequestBody Employee emp)
	{
		orders.send(MessageBuilder.withPayload(emp).setHeader("x-correlationId", "999999").build());
	}

}
