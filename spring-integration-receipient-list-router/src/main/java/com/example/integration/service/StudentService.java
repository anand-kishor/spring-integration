package com.example.integration.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
@Component
public class StudentService {
	@ServiceActivator(inputChannel="student.channel.1")
	public Message<?> receiveMessage(Message<?> message) throws MessagingException
	{
		System.out.println("...........student channel one..............");
		System.out.println(message);
		
		System.out.println(message.getPayload());
		return message;
	}
	
	@ServiceActivator(inputChannel="student.channel.2")
	public Message<?> receiveMessage1(Message<?> message) throws MessagingException
	{
		System.out.println("...........student channel two..............");
		System.out.println(message);
		
		System.out.println(message.getPayload());
		return message;
	}
	

}
