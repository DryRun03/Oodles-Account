package com.oodles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.oodles.util.MessageUtility;


/**
 * The Class Development.
 */
@Configuration
@Profile("localDevelopment")
public class LocalDevelopment {

	/**
	 * Gets the development config.
	 *
	 * @return the development config
	 */
	@Bean
	public EnvConfiguration getDevelopmentConfig() {
		return new DevelopmentEnv();
	}

	/**
	 * To create bean of message utility class that reads messages from properties
	 * file
	 * 
	 * @return
	 */
	@Bean
	public MessageUtility getMessageUtility() {
		MessageUtility messageUtility = new MessageUtility();
		messageUtility.initialze();
		return messageUtility;
	}

}
