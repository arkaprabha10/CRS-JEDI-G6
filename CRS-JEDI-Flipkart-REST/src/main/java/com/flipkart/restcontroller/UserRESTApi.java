/**
 * 
 */
package com.flipkart.restcontroller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.Email;

import com.flipkart.bean.User;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.UserOperation;

/**
 * @author rutwi
 *
 */
@Path("/user")
public class UserRESTApi {
	
	// TODO:
	/*
	 * - Login a user - POST
	 * - Register a user - POST
	 */
	
	@GET
	@Path("/customerdetails")
	@Produces(MediaType.APPLICATION_JSON)
	public User getCustomerDetails() {
		
		// Call the service class here which will
		// return the Java object and convert into JSON
		// using REST API
   
		User customer = new User();
		customer.setName("Nagpur");
		customer.setUserID("1101");
		customer.setPassword("Rutwij");
		
	   return customer;
	}
	
	@POST
	@Path("/login")
	public Response verifyCredentials(
			@NotNull
			@QueryParam("userId") String userId,
			@NotNull
			@QueryParam("password") String password,
			@NotNull
			@QueryParam("role") String role) {
		
		try 
		{
			UserOperation uo = new UserOperation();
			boolean loggedIn = uo.loginUser(userId, password, role);
			
			if(loggedIn)
			{
//				if(role.equals("student"))
//				
//				case STUDENT:
//					int studentId=studentInterface.getStudentId(userId);
//					boolean isApproved=studentInterface.isApproved(studentId);
//					if(!isApproved)	
//					{
//						return Response.status(200).entity("Login unsuccessful! Student "+userId+" has not been approved by the administration!" ).build();
//					}
//					break;
//					
//				}
				return Response.status(200).entity("Login successful").build();
				
			}
			else
			{
				return Response.status(500).entity("Invalid credentials!").build();
			}
		}
		catch (UserNotFoundException e) 
		{
			return Response.status(500).entity(e.getMessage()).build();
		}		
		
	}

}
