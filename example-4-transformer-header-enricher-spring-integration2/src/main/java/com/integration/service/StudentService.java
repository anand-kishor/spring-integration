package com.integration.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.integration.model.Student;

@Component
public class StudentService {
	
	@ServiceActivator(inputChannel="integration.student.objectToJson.channel",outputChannel="integration.student.jsonToObject.channel")
	public Message<?> receiveMessage(Message<?> message) throws MessagingException
	{
		System.out.println("##############################");
		System.out.println(message);
		System.out.println(".......................");
		System.out.println("object to json "+message.getPayload());
		return message;
	}
	@ServiceActivator(inputChannel="integration.student.jsonToObject.fromTranformer.channel")
	public void processObjectToJson(Message<?> message) throws MessagingException
	{
		MessageChannel replyChannel=(MessageChannel)message.getHeaders().getReplyChannel();
		MessageBuilder.fromMessage(message);
		System.out.println("#############################");
		System.out.println("json to object "+message.getPayload());
		Student student=(Student)message.getPayload();
		Message<?> newMessage=MessageBuilder.withPayload(student.toString()).build();
		replyChannel.send(newMessage);
	}

}
