package com.example.integration.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.integration.support.json.JsonObjectMapper;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;
import org.springframework.messaging.MessageChannel;

import com.example.integration.model.Address;
import com.example.integration.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;



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
	public PayloadTypeRouter router()
	{
		PayloadTypeRouter router=new PayloadTypeRouter();
		router.setChannelMapping(Student.class.getName(),"student.enrich.header.channel");
		router.setChannelMapping(Address.class.getName(), "address.enrich.header.channel");
		return router;
	}
	@Bean
	@Transformer(inputChannel="student.enrich.header.channel",outputChannel="header.payload.router.channel")
	public HeaderEnricher enrichHeaderForStudent()
	{
		Map<String,HeaderValueMessageProcessor<String>> headersToAdd=new HashMap<>();
		headersToAdd.put("testHeader", new StaticHeaderValueMessageProcessor<String>("student"));
		HeaderEnricher enricher=new HeaderEnricher(headersToAdd);
		return enricher;
		
	}
	@Bean
	@Transformer(inputChannel="address.enrich.header.channel",outputChannel="header.payload.router.channel")
	public HeaderEnricher enrichHeaderForAddress()
	{
		Map<String,HeaderValueMessageProcessor<String>> headersToAdd=new HashMap<>();
		headersToAdd.put("testHeader", new StaticHeaderValueMessageProcessor<String>("address"));
		HeaderEnricher enricher=new HeaderEnricher(headersToAdd);
		return enricher;
		
	}
	@ServiceActivator(inputChannel="header.payload.router.channel")
	@Bean
	public HeaderValueRouter headerValue()
	{
		HeaderValueRouter router=new HeaderValueRouter("testHeader");
		router.setChannelMapping("student","student.channel");
		router.setChannelMapping("address", "address.channel");
		return router;
	}
}
