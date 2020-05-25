package com.integration;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
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
	public Converter convert()
	{
		return new Converter();
	}
	@Transformer(inputChannel="messageChannel")
	@Bean
	public MessageHandler handler()
	{
		MethodInvokingTransformer transformer=new MethodInvokingTransformer(convert(), "convert");
		MessageTransformingHandler mit=new MessageTransformingHandler(transformer);
		mit.setOutputChannel(messageOutChannel());
		return mit;
	}
	
	private MessageChannel messageOutChannel() {
		// TODO Auto-generated method stub
		return new DirectChannel();
	}

	 @Bean public MessageChannel messageChannel() { return new DirectChannel(); }
	 
	@Bean
	@ServiceActivator(inputChannel="messageChannel")
	public MessageHandler msgHandler()
	{
		return new MessageHandler() {

			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				// TODO Auto-generated method stub
				System.out.println(message);
				
			}};
	}
 class Converter{
		public String convert(String msg)
		{
			return msg+"modified msg";
		}
	}

}
