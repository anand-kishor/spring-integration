package com.integration.config;

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
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;
import org.springframework.messaging.MessageChannel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integration.model.Address;
import com.integration.model.Student;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationConfig {
	
	@Bean
	public MessageChannel requestChannel()
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
		router.setChannelMapping(Student.class.getName(), "student.channel");
		router.setChannelMapping(Address.class.getName(), "address.channel");
		return router;
	}
	/*
	 * @Bean
	 * 
	 * @Transformer(inputChannel="integration.student.gateway.channel",
	 * outputChannel="integration.student.toConvertObject.channel") public
	 * HeaderEnricher enricherHeader() {
	 * Map<String,HeaderValueMessageProcessor<String>> headerToAdd=new HashMap<>();
	 * headerToAdd.put("header1", new
	 * StaticHeaderValueMessageProcessor<String>("test header 1"));
	 * headerToAdd.put("header2", new
	 * StaticHeaderValueMessageProcessor<String>("test header 2")); HeaderEnricher
	 * enricher=new HeaderEnricher(headerToAdd); return enricher; }
	 * 
	 * @Bean
	 * 
	 * @Transformer(inputChannel="integration.student.toConvertObject.channel",
	 * outputChannel="integration.student.objectToJson.channel") public
	 * ObjectToJsonTransformer jsonToObjectTransformer() { return new
	 * ObjectToJsonTransformer(getMapper()); }
	 * 
	 * @Bean public Jackson2JsonObjectMapper getMapper() { ObjectMapper mapper=new
	 * ObjectMapper(); return new Jackson2JsonObjectMapper(mapper) ; }
	 */
}
