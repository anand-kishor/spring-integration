package com.integration.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.integration.model.Student;

@MessagingGateway
public interface IntegrationGateway {

	@Gateway(requestChannel="integration.request.channel")
	public String sendMessage(String message);
	@Gateway(requestChannel="integration.student.gateway.channel")
	public String processStudentDetails(Student student);

}
