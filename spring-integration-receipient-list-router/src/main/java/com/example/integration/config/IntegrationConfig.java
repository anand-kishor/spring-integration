package com.example.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.messaging.MessageChannel;



@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationConfig {
	
	@Bean
	public MessageChannel receiverChannel()
	{
		return new DirectChannel();
	}
	@Bean
	public MessageChannel replyChannel()
	{
		return new DirectChannel();
	}
	@ServiceActivator(inputChannel="router.channel")
	@Bean
	public RecipientListRouter router()
	{
		RecipientListRouter router=new RecipientListRouter();
		router.addRecipient("student.channel.1");
		router.addRecipient("student.channel.2");
		return router;
	}
}
