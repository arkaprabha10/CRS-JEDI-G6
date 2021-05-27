package com.flipkart.restcontroller;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author rutwi
 * 
 * All API configured inside this ApplicationConfig Class
 *
 */

public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {

		register(UserRESTApi.class);
		register(AdminRESTApi.class);

	}

}