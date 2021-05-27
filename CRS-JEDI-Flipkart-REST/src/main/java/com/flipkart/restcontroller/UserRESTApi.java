/**
 * 
 */
package com.flipkart.restcontroller;

<<<<<<< HEAD
import java.sql.SQLException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.Email;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.UserAlreadyInUseException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.StudentOperation;
import com.flipkart.service.UserOperation;

=======
>>>>>>> 9e508291de1122f97e41e2d0216bd4efe682ab4a
/**
 * @author rutwi
 *
 */
<<<<<<< HEAD
@Path("/user")
=======
>>>>>>> 9e508291de1122f97e41e2d0216bd4efe682ab4a
public class UserRESTApi {
	
	// TODO:
	/*
	 * - Login a user - POST
	 * - Register a user - POST
	 */
<<<<<<< HEAD
	
	StudentOperation so = new StudentOperation();
	
	
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
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}		
		
	}

	@POST
	@Path("/register")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response RegisterUser(Student student) {
		
		 try {
			so.addStudent(student.getUserID(), student.getName(), student.getPassword(), student.getDepartment(), student.getContactNumber(), student.getJoiningYear());
		} catch (UserAlreadyInUseException e) {
			return Response.status(500).entity(e.getMessage()).build();//			e.printStackTrace();
		} catch (SQLException e) {
			return Response.status(500).entity(e.getMessage()).build();//			e.printStackTrace();
		}
         
		
        String result = "Added student : " + student;
		
		
		return Response.status(201).entity(result).build();
		
	}  
	
=======

>>>>>>> 9e508291de1122f97e41e2d0216bd4efe682ab4a
}
