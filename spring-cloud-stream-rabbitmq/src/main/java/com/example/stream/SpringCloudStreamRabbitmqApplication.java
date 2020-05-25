package com.example.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

import com.example.stream.model.Employee;

@SpringBootApplication
@EnableBinding(Sink.class)
public class SpringCloudStreamRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamRabbitmqApplication.class, args);
	}

	@StreamListener(Sink.INPUT)
	public void handle(@Payload Employee emp)
	{
		System.out.println(emp.toString());
	}
}
