package com.example.integration.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
@Component
public class StudentService {
	@ServiceActivator(inputChannel="student.channel")
	public Message<?> receiveMessage(Message<?> message) throws MessagingException
	{
		System.out.println(".........................");
		System.out.println(message);
		System.out.println("............................");
		System.out.println("object to json "+message.getPayload());
		return message;
	}
	

}
