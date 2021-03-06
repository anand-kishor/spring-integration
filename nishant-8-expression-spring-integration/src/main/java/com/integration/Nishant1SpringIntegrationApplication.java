package com.integration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
import org.springframework.integration.router.ExpressionEvaluatingRouter;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.MessageTransformingHandler;
import org.springframework.integration.transformer.MethodInvokingTransformer;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.core.DestinationResolutionException;
import org.springframework.messaging.core.DestinationResolver;

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
		Account act=new Account();
		act.setAccountNo("123456");
		act.setAccountType(msgToSend);
		sender.send(act);
	}
	scanner.close();
	}
	
	@MessagingGateway(defaultRequestChannel="messageChannel")
	public interface Sender{
		public void send(Account msg);
	}
	
	 @Bean 
	 public MessageChannel messageChannel()
	{ 
		return new DirectChannel(); 
		}
	 
	 
	 
	 @Router(inputChannel="messageChannel")
	 @Bean
	 public ExpressionEvaluatingRouter router()
	 {
		 ExpressionEvaluatingRouter evaluator=new ExpressionEvaluatingRouter("payload.accountType");
		 evaluator.setChannelResolver(name -> name.equalsIgnoreCase("saving")?savingChannel():defaultChannel());
		 return evaluator;

			/*@Override
			public MessageChannel resolveDestination(String name) throws DestinationResolutionException {
				// TODO Auto-generated method stub
				return name.equalsIgnoreCase("saving")?savingChannel():defaultChannel();
			} });
		 return evaluator;*/
	 }
	 @Bean 
	 public MessageChannel defaultChannel()
	{ 
		return new DirectChannel(); 
		}
	
	@Bean
		@ServiceActivator(inputChannel="defaultChannel")
		public MessageHandler defaultChannelHandler()
		{
			return new MessageHandler() {

				@Override
				public void handleMessage(Message<?> message) throws MessagingException {
					// TODO Auto-generated method stub
					System.out.println("defaultchannel  "+message);
					
				}};
		}
	 
	 @Bean 
	 public MessageChannel savingChannel()
	{ 
		return new DirectChannel(); 
		}
	
	@Bean
		@ServiceActivator(inputChannel="savingChannel")
		public MessageHandler defaultHandler()
		{
			return new MessageHandler() {

				@Override
				public void handleMessage(Message<?> message) throws MessagingException {
					// TODO Auto-generated method stub
					System.out.println("savingchannel  "+message);
					
				}};
		}
	
	
	
}
