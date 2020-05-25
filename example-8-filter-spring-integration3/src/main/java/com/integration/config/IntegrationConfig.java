package com.integration.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSelector;
import org.springframework.integration.filter.MessageFilter;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;
import org.springframework.messaging.Message;
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
	@Filter(inputChannel="router.channel")
	@Bean
	public MessageFilter filter()
	{
		MessageFilter filter=new MessageFilter(new MessageSelector() {

			@Override
			public boolean accept(Message<?> message) {
				// TODO Auto-generated method stub
				return message.getPayload() instanceof Student;
			}});
		filter.setOutputChannelName("student.channel");
		return filter;
	}
	/*
	 * @Bean public MessageChannel replyChannel() { return new DirectChannel(); }
	 * 
	 * @ServiceActivator(inputChannel="router.channel")
	 * 
	 * @Bean public PayloadTypeRouter routerPayload() { PayloadTypeRouter router=new
	 * PayloadTypeRouter(); router.setChannelMapping(Student.class.getName(),
	 * "student.enrich.header.channel");
	 * router.setChannelMapping(Address.class.getName(),
	 * "address.enrich.header.channel"); return router; }
	 * 
	 * @Transformer(inputChannel="student.enrich.header.channel",outputChannel=
	 * "header.payload.router.channel")
	 * 
	 * @Bean public HeaderEnricher routerHeaderEnricher() {
	 * Map<String,HeaderValueMessageProcessor<String>> headersToAdd=new HashMap<>();
	 * headersToAdd.put("testHeader", new
	 * StaticHeaderValueMessageProcessor<String>("student")); HeaderEnricher
	 * enricher=new HeaderEnricher(headersToAdd); return enricher; }
	 * 
	 * @Transformer(inputChannel="address.enrich.header.channel",outputChannel=
	 * "header.payload.router.channel")
	 * 
	 * @Bean public HeaderEnricher routerHeaderEnricherAddress() {
	 * Map<String,HeaderValueMessageProcessor<String>> headersToAdd=new HashMap<>();
	 * headersToAdd.put("testHeader", new
	 * StaticHeaderValueMessageProcessor<String>("address")); HeaderEnricher
	 * enricher=new HeaderEnricher(headersToAdd); return enricher; }
	 * 
	 * @ServiceActivator(inputChannel="header.payload.router.channel")
	 * 
	 * @Bean public HeaderValueRouter routerHeader() { HeaderValueRouter router=new
	 * HeaderValueRouter("testHeader"); router.setChannelMapping("student",
	 * "student.channel"); router.setChannelMapping("address", "address.channel");
	 * return router;
	 * 
	 * }
	 */
	
	/*public RecipientListRouter router()
	{
		RecipientListRouter router=new RecipientListRouter();
		router.addRecipient("student.channel.1");
		router.addRecipient("student.channel.2");
		return router;
	}*/
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
