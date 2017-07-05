package com.sqs;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class CustomMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {

		try {
			System.out.println("Received message as : " + ((TextMessage) message).getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
