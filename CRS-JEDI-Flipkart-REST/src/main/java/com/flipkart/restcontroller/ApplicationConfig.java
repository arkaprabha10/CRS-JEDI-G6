<<<<<<< HEAD
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

	}

}
=======
/**
 * 
 */
package com.flipkart.restcontroller;

/**
 * @author rutwi
 *
 */
public class ApplicationConfig {

}
>>>>>>> 9e508291de1122f97e41e2d0216bd4efe682ab4a
