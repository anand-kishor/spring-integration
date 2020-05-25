package com.integration.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class IntegrationService {
	
	@ServiceActivator(inputChannel="integration.request.channel")
	public void anotherMessage(Message<String> message) throws MessagingException
	{
		MessageChannel replyChannel=(MessageChannel)message.getHeaders().getReplyChannel();
		MessageBuilder.fromMessage(message);
		Message<String> newMessage=MessageBuilder.withPayload("welcome to spring integration by "+message.getPayload()+" spring family").build();
		replyChannel.send(newMessage);
	}

}
