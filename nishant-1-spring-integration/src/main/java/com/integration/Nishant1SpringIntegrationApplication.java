package com.integration;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootApplication
public class Nishant1SpringIntegrationApplication {

	public static void main(String[] args) throws JsonProcessingException {
	ConfigurableApplicationContext ctx=SpringApplication.run(Nishant1SpringIntegrationApplication.class, args);
	Sender sender=ctx.getBean(Sender.class);
	Scanner scanner=new Scanner(System.in);
	while(scanner.hasNext())
	{
		String msgToSend=scanner.next();
		sender.send(msgToSend);
	}
	scanner.close();
	}
	
	@MessagingGateway(defaultRequestChannel="sender.channel")
	public interface Sender{
		public void send(String msg);
	}
	@Bean
	public MessageChannel messageChannel()
	{
		return new DirectChannel();
	}
	@Bean
	@ServiceActivator(inputChannel="sender.channel")
	public MessageHandler msgHandler()
	{
		return new MessageHandler() {

			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				// TODO Auto-generated method stub
				System.out.println(message);
				
			}};
	}

}
