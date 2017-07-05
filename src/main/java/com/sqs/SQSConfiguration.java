package com.sqs;

import javax.jms.JMSException;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;

@Component
public class SQSConfiguration {

	@Autowired
	SQSConnectionFactory connectionFactory;

	public static SQSConnection connection;

	public static Session session;

	public AmazonSQSMessagingClientWrapper amazonSQSMessagingClientWrapper() {
		try {
			connection = connectionFactory.createConnection();
			return connection.getWrappedAmazonSQSClient();
		} catch (JMSException e) {
			e.printStackTrace();
			return null;
		}

	}

	public SQSConnection getConnection() {
		return connection;
	}

	public static Session createSession() {
		try {
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			return session;
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static void closeSession() {
		try {
			session.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeConnection() {
		try {
			connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
