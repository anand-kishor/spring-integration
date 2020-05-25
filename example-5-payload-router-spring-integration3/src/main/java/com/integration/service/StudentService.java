package com.integration.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
@Component
public class StudentService {
	
	@ServiceActivator(inputChannel="student.channel")
	public void receiveStudentMessage(Message<?> message) throws MessagingException
	{
		System.out.println("student message channel");
		System.out.println(message);
		System.out.println("................");
		System.out.println(message.getPayload());
		
	}
}
