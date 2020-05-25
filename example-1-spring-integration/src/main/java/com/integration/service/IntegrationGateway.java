package com.integration.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface IntegrationGateway {

	@Gateway(requestChannel="integration.request.channel")
	public String sendMessage(String message);

}
