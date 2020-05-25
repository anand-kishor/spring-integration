package com.integration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.integration.transformer.MessageTransformingHandler;
import org.springframework.integration.transformer.MethodInvokingTransformer;
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
	
	@MessagingGateway(defaultRequestChannel="messageChannel")
	public interface Sender{
		public void send(String msg);
	}
	 @Bean 
	 public MessageChannel messageChannel()
	{ 
		return new DirectChannel(); 
		}
	 @Router(inputChannel="messageChannel")
	 @Bean
	 public AbstractMessageRouter msgRouter()
	 {
		 return new AbstractMessageRouter() {

			@Override
			protected Collection<MessageChannel> determineTargetChannels(Message<?> message) {
				// TODO Auto-generated method stub
				Collection<MessageChannel> col=new ArrayList<>();
				if(message.getPayload().toString().equalsIgnoreCase("saving"))
				{
					col.add(savingChannel());
				}
				else
				{
					col.add(defaultChannel());
				}
				return col;
			}

		 };
	 }
	 @Bean
	 public MessageChannel defaultChannel()
	 {
		 return new DirectChannel();
		 
	 }
	 @Bean
		@ServiceActivator(inputChannel="defaultChannel")
		public MessageHandler msgDefaultHandler()
		{
			return new MessageHandler() {

				@Override
				public void handleMessage(Message<?> message) throws MessagingException {
					// TODO Auto-generated method stub
					System.out.println("defaultchannel  "+message);
					
				}};
		}
	 @Bean
	 public MessageChannel savingChannel() {
			// TODO Auto-generated method stub
			return new DirectChannel();
		}
	 
	@Bean
	@ServiceActivator(inputChannel="savingChannel")
	public MessageHandler msgHandler()
	{
		return new MessageHandler() {

			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				// TODO Auto-generated method stub
				System.out.println("savingchannel  "+message);
				
			}};
	}


}
