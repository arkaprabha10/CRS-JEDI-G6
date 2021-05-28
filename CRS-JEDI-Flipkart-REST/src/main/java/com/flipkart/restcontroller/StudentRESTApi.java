/**
 * 
 */
package com.flipkart.restcontroller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.bean.Payment;
import com.flipkart.bean.ReportCard;
import com.flipkart.constants.constants;
//import com.flipkart.bean.StudentGrade;
//import com.flipkart.constant.ModeOfPayment;
import com.flipkart.exception.CourseAlreadyRegisteredException;
import com.flipkart.exception.CourseExistsInCartException;
import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.CourseNotAssignedException;
import com.flipkart.exception.CourseNotDeletedException;
//import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.CourseNotInCart;
import com.flipkart.exception.CourseSeatsUnavailableException;
import com.flipkart.exception.FeesPendingException;
import com.flipkart.exception.GradeNotAddedException;
import com.flipkart.exception.InvalidSemesterRegistration;
import com.flipkart.exception.PaymentFailedException;
import com.flipkart.exception.ReportCardNotGeneratedException;
import com.flipkart.exception.StudentNotApprovedException;
import com.flipkart.exception.StudentNotRegisteredException;
import com.flipkart.service.NotificationInterface;
import com.flipkart.service.NotificationOperation;
import com.flipkart.service.PaymentOperation;
//import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorOperation;
//import com.flipkart.service.RegistrationInterface;
//import com.flipkart.service.RegistrationOperation;
import com.flipkart.service.SemesterRegistrationInterface;
import com.flipkart.service.SemesterRegistrationOperation;
//import com.flipkart.validator.StudentValidator;
import com.flipkart.service.StudentOperation;

/**
 * @author rutwi
 *
 */

@Path("/student")
public class StudentRESTApi {
	
	// TODO:
	/*
	 * - done view report card - GET 
	 * - done view registered courses - GET
	 * - done add course - GET
	 * - drop course - GET
	 * - done finish registration - ???
	 * - done  make payment - POST
	 * - no need logout - GET
	 */

	
	SemesterRegistrationInterface registrationInterface = SemesterRegistrationOperation.getInstance(); 
	ProfessorInterface professorInterface = ProfessorOperation.getInstance();
	StudentOperation so = new StudentOperation();
	
	
	/**
	 * Method to handle API request for course registration
	 * @param courseList
	 * @param studentId
	 * @throws ValidationException
	 * @return
	 */
	
	@POST
	@Path("/addCourses")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerCourses(List<String> courseList, 
			@NotNull
			@Min(value = 1, message = "Student ID should not be less than 1")
			@Max(value = 9999, message = "Student ID should be less than 1000")
			@QueryParam("studentId") int studentId,
			@NotNull
			@QueryParam("courseId") String courseId,
			@NotNull
			@QueryParam("primary") Boolean primary){
						
		
			try {
				registrationInterface.addCourse(studentId, constants.SemesterID, courseId, primary);
			} catch (CourseNotFoundException | CourseSeatsUnavailableException | CourseExistsInCartException e) {

				return Response.status(500).entity(e.getMessage()).build();

			} 
			
		return Response.status(201).entity( "Course Added").build();
		
	}
	


	/**
	 * Handles API request to drop a course
	 * @param courseCode
	 * @param studentId
	 * @return
	 * @throws ValidationException
	 */
	@DELETE
	@Path("/dropCourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dropCourse(
			@NotNull 
			@QueryParam("courseId") String courseId,
			@NotNull
			@Min(value = 1, message = "Student ID should not be less than 1")
			@Max(value = 9999, message = "Student ID should be less than 1000")
			@QueryParam("studentId") int studentId) { 
		
			try {
				registrationInterface.dropCourse(studentId, constants.SemesterID, courseId);
			} catch (CourseNotFoundException | CourseNotInCart e) {
				return Response.status(500).entity( e.getMessage()).build();				
			}
			return Response.status(201).entity( "Course Dropped").build();
		
	}
	
	
	/**
	 * Method handles API request to view the list of available courses for a student
	 * @param studentId
	 * @return
	 * @throws ValidationException
	 */
	@GET
	@Path("/viewAvailableCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewCourse(){
		
		try {
			return registrationInterface.viewAvailableCourses();
		} catch (Exception e) {
			Response.status(500).entity( e.getMessage()).build();
			return null;
		}
		
	}
	
	/**
	 * Method handles API request to view the list of registered courses for a student
	 * @return
	 * @throws ValidationException
	 */
	@GET
	@Path("/viewRegisteredCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewRegisteredCourse(
			@NotNull
			@Min(value = 1, message = "Student ID should not be less than 1")
			@Max(value = 9999, message = "Student ID should be less than 1000")
			@QueryParam("studentId") int studentId) throws ValidationException{
		
			try {
				return so.viewRegisteredCourses(studentId,constants.SemesterID);
			} catch (StudentNotRegisteredException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	
	@GET
	@Path("/finishRegistration")
	@Produces(MediaType.APPLICATION_JSON)
	public Response finishRegistration(
			@NotNull
			@Min(value = 1, message = "Student ID should not be less than 1")
			@Max(value = 9999, message = "Student ID should be less than 1000")
			@QueryParam("studentId") Integer studentId) {		
		
			try {
				registrationInterface.finishRegistration(studentId, constants.SemesterID);
			} catch (InvalidSemesterRegistration e) {
				return Response.status(500).entity(e.getMessage()).build();
			}
			
			return Response.status(201).entity("Your Registration is Complete. It has been forwarded for Approval").build();
		
		
	}
	
	/**
	 * Method handles API request to make payment for registered courses
	 * @param studentId
	 * @param paymentMode
	 * @return
	 * @throws ValidationException
	 */
	@POST
	@Path("/make-payment")
	@Produces(MediaType.APPLICATION_JSON)
	public Response makePayment(
			@NotNull
			@Min(value = 1, message = "Student ID should not be less than 1")
			@Max(value = 9999, message = "Student ID should be less than 1000")
			@QueryParam("studentId") int studentId , 
			@NotNull
			@QueryParam("paymentMode") String paymentMode) {
		
			PaymentOperation po = new PaymentOperation();
			Payment payment= new Payment();
			payment.setAmount(constants.SemesterID);
			payment.setPaymentMode(paymentMode);
			payment.setStudentID(studentId);

			try {
				po.makePayment(payment);
			} catch (PaymentFailedException e) {
				return Response.status(500).entity(e.getMessage()).build();
			}
			return Response.status(201).entity("Processing Complete").build();
		
	}

	
	/**
	 * Method handles request to display the grade card for student
	 * @param studentId
	 * @return
	 * @throws ValidationException
	 */
	
	@POST
	@Path("/viewReportCard")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String,Integer> viewReportCard(
			@NotNull
			@Min(value = 1, message = "Student ID should not be less than 1")
			@Max(value = 9999, message = "Student ID should be less than 1000")
			@QueryParam("studentId") Integer studentId) {		
		
			ReportCard R = null;
			try {
				R = so.viewReportCard(studentId,constants.SemesterID);
			} catch (GradeNotAddedException | StudentNotApprovedException | FeesPendingException
					| ReportCardNotGeneratedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return R.getGrades();//Response.status(201).entity(null).build();
		
		
	}
	
//}
	
	
}
