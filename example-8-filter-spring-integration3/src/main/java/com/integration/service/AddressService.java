package com.integration.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class AddressService {
	@ServiceActivator(inputChannel="address.channel")
	public void receiveAddress(Message<?> message) throws MessagingException
	{
		System.out.println("Address message channel");
		System.out.println(message);
		System.out.println("................");
		System.out.println(message.getPayload());
		
	}

}