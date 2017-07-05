package com.sqs;

import javax.jms.JMSException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ApplicationStarter {

	public static void main(String[] args) {
		ConfigurableApplicationContext con=SpringApplication.run(ApplicationStarter.class, args);
		QueueService conf=con.getBean(QueueService.class);
		
		try {
			conf.createClient();
			conf.createQueue();
			conf.createProducer();
			conf.sendMessage();
			conf.createConsumer();
			conf.receiveMessage();
			//conf.closeConnections();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
