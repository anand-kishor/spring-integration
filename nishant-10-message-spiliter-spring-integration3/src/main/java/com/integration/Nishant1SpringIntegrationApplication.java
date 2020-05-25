package com.integration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.integration.router.ExpressionEvaluatingRouter;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.integration.splitter.MethodInvokingSplitter;
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
		AccountHolder holder1=new AccountHolder();
		holder1.setName("anand");
		holder1.setAddress("india");
		AccountHolder holder2=new AccountHolder();
		holder2.setName("anand");
		holder2.setAddress("india");
		List<AccountHolder> list=new ArrayList<>();
		list.add(holder1);
		list.add(holder2);
		act.setAccountHolder(list);
		
		sender.send(act);
		
	}
	scanner.close();
	}
	
	@MessagingGateway(defaultRequestChannel="messageChannel")
	public interface Sender{
		public void send(Object msg);
	}
	
	 @Bean 
	 public MessageChannel messageChannel()
	{ 
		return new DirectChannel(); 
		}
	 @Bean 
	 public MessageModifier messageModifier()
	{ 
		return new MessageModifier(); 
		}
	 
	 
	 
	 @Splitter(inputChannel="messageChannel")
	 @Bean
	 public AbstractMessageSplitter splitter()
	 {
		 MethodInvokingSplitter splitter=new MethodInvokingSplitter(messageModifier(), "calledBySplitter");
		 splitter.setOutputChannel(outputChannel());
		 return splitter;
	 }
	
	 
	 @Bean 
	 public MessageChannel outputChannel()
	{ 
		return new DirectChannel(); 
		}
	
	   @Bean
		@ServiceActivator(inputChannel="outputChannel")
		public MessageHandler outputChannelHandler()
		{
			return new MessageHandler() {

				@Override
				public void handleMessage(Message<?> message) throws MessagingException {
					// TODO Auto-generated method stub
					System.out.println("defaultchannel  "+ "message is splitter "+message.getPayload().getClass());
					
				}};
		}
	 
	 
	
	class MessageModifier{
		public Collection<Object> splitter(Account act)
		{
			Collection<Object> col=new ArrayList<>();
			col.add(act);
			col.addAll(act.getAccountHolder());
			return col;
		}
	}
	
}
