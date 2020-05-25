package com.example.stream.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

import com.example.stream.kafka.model.Employee;

@SpringBootApplication
@EnableBinding({Source.class,Sink.class})
public class SpringCloudStreamApacheKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamApacheKafkaApplication.class, args);
	}
	
	@StreamListener("input")
	public void consumeMessage(Employee emp)
	{
		System.out.println(emp);
	}

}
