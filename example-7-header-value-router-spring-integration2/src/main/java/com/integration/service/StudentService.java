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
		System.out.println("student message channel one");
		System.out.println(message);
		System.out.println("................");
		System.out.println(message.getPayload());
		
	}
	/*
	 * @ServiceActivator(inputChannel="student.channel.2") public void
	 * receiveStudentMessage1(Message<?> message) throws MessagingException {
	 * System.out.println("student message channel two");
	 * System.out.println(message); System.out.println("................");
	 * System.out.println(message.getPayload());
	 * 
	 * }
	 */
}
