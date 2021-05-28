/**
 * 
 */
package com.flipkart.restcontroller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourses;
import com.flipkart.bean.Student;
import com.flipkart.constants.constants;
import com.flipkart.exception.CourseAlreadyRegisteredException;
import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.CourseNotAssignedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.NoStudentInCourseException;
import com.flipkart.exception.StudentNotRegisteredException;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorOperation;

/**
 * @author rutwi
 *
 */@Path("/professor")
 public class ProfessorRESTApi {
		ProfessorInterface pI=ProfessorOperation.getInstance();
		
		@GET
		@Path("/getRegisteredStudents")
		@Produces(MediaType.APPLICATION_JSON)
		public ArrayList<RegisteredCourses> viewRegisteredStudents(
				@NotNull
				@QueryParam("courseId") String courseId){
			
			try {
				return pI.viewCourseStudents(courseId, constants.SemesterID);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return null;
			}
			
		}
		
		@GET
		@Path("/getCourses")
		@Produces(MediaType.APPLICATION_JSON)
		public ArrayList<Course> getCourses(
				@NotNull
				@QueryParam("profId") Integer profId){
			try {
				return pI.viewCourseProf(profId);
			}
			catch (Exception e) {
				return null;
			}
		
		}
		
		@POST
		@Path("/addGrade")
		@Produces(MediaType.APPLICATION_JSON)
		public Response addGrade(
				@NotNull
				@Min(value = 1, message = "Student ID should not be less than 1")
				@Max(value = 9999, message = "Student ID should be less than 10000")
				@QueryParam("studentId") int studentId,				
				@NotNull
				@QueryParam("courseId") String courseId,
				@QueryParam("grade") Integer grade) {
			
			try
			{
				pI.addGrade(studentId, constants.SemesterID, courseId, grade);
				
			}
			catch(Exception ex)
			{
				return Response.status(500).entity(ex.getMessage()).build();
			}
			return Response.status(200).entity( "Grade updated for student: "+studentId).build();
			
		}
		
		@POST
		@Path("/registerCourse")
		@Produces(MediaType.APPLICATION_JSON)
		public Response registerCourse(
				@NotNull
				@QueryParam("professorId") Integer profId,
				@NotNull
				@QueryParam("courseId") String courseId) {
			
			try
			{
				pI.registerCourse(profId, constants.SemesterID ,courseId);
				
			}
			catch(Exception ex)
			{
				return Response.status(500).entity(ex.getMessage()).build();
			}
			return Response.status(200).entity( "You've been Registered !").build();
			
		}
		
		
	}