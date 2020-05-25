package com.example.stream.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.stream.kafka.model.Employee;
import com.example.stream.kafka.synchronouslly.SynSink;

@SpringBootApplication
@EnableBinding({Source.class,SynSink.class})
@EnableScheduling
public class SpringCloudStreamApacheKafkaApplication {
	
	@Autowired
	private PollableMessageSource pollSource;

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamApacheKafkaApplication.class, args);
	}
	
	/*
	 * @StreamListener("input") public void consumeMessage(Employee emp) {
	 * System.out.println(emp); }
	 */
	/*
	 * @Bean public ApplicationRunner getRunner() { return args -> { try {
	 * 
	 * 
	 * while(true) { if(!pollSource.poll(m -> System.out.println(m.getPayload()),new
	 * ParameterizedTypeReference<Employee>() {})) { Thread.sleep(5000); } } }
	 * catch(Exception e) { System.out.println("error : :"+e.getMessage()); } }; }
	 */
	@Scheduled(fixedRate=5000)
	public void getMessage()
	{
		pollSource.poll(m -> System.out.println(m.getPayload()),new ParameterizedTypeReference<Employee>() {});
		
	}

}
