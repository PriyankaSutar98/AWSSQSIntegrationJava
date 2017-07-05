package com.sqs;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

@Component
public class AWSConfiguration {

	@Bean
	public AWSCredentialsProvider awsCredentialsProvider() {
		return new ProfileCredentialsProvider();
	}

	@Bean
	public SQSConnectionFactory connectionFactory() {
		return SQSConnectionFactory.builder().withRegion(Region.getRegion(Regions.US_WEST_2))
				.withAWSCredentialsProvider(awsCredentialsProvider()).build();
	}
}
