package com.example.stream.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Source;

import com.example.stream.publisher.messageChannel.Orders;
import com.example.stream.publisher.model.Employee;

@SpringBootApplication
@EnableBinding({Source.class,Orders.class})
public class SpringCloudStreamRabbitmqPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamRabbitmqPublisherApplication.class, args);
	}
	
	@StreamListener("payments")
	public void handleMessage(Employee emp)
	{
		System.out.println("Employee : "+emp.toString());
	}

}
