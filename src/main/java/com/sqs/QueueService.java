package com.sqs;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;

@Component
public class QueueService {

	@Autowired
	SQSConfiguration sqsConfiguration;

	static Queue queue;
	static MessageProducer producer;
	static MessageConsumer consumer;
	static AmazonSQSMessagingClientWrapper client;

	public void createClient() throws JMSException {
		client = sqsConfiguration.amazonSQSMessagingClientWrapper();

		if (!client.queueExists("TestQueue")) {
			client.createQueue("TestQueue");
		}

	}

	public void createQueue() {
		try {
			queue = SQSConfiguration.createSession().createQueue("TestQueue");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createProducer() {
		try {
			producer = SQSConfiguration.createSession().createProducer(queue);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessage() {
		try {
			TextMessage message = SQSConfiguration.createSession().createTextMessage("Hi this is my second messgae");
			producer.send(message);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeConnections() {
		SQSConfiguration.closeSession();
		SQSConfiguration.closeConnection();

	}

	public void createConsumer() {
		try {
			consumer = SQSConfiguration.createSession().createConsumer(queue);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void receiveMessage() {
		try {
			System.out.println("receiving message");
			
			consumer.setMessageListener(new CustomMessageListener());
			SQSConfiguration.connection.start();

//			if (receivedMessage != null) {
//				System.out.println("Received: " + ((TextMessage) receivedMessage).getText());
//			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			//closeConnections();
		}
	}
}
