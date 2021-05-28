/**
 * 
 */
package com.flipkart.restcontroller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constants.constants;
import com.flipkart.exception.UserAlreadyInUseException;
import com.flipkart.service.AdminOperation;
import com.flipkart.service.StudentOperation;

/**
 * @author rutwi
 *
 */
@Path("/admin")
public class AdminRESTApi {
	
	AdminOperation ao = AdminOperation.getInstance();
	
	// TODO
	/*
	 * - Add Course - POST .
	 * - Remove Course - DELETE (or POST?) .
	 * - Generate Report Card - POST .
	 * - Approve Student Registration - POST .
	 * - Add Professor - POST .
	 * - Remove PRofesseor - POST .
	 * - View Coursewise list - GET
	 * - approve pending
	 */
	
	@POST
	@Path("/addcourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(Course course) {
		
		
			try {
				ao.addCourse(course.getCoursename(), course.getCourseID(), course.getOfferedSemester());
			} 
			catch(Exception ex)
			{
				return Response.status(500).entity(ex.getMessage()).build();
			}
			return Response.status(201).entity( "Course : "+course.getCourseID()+"\t "+course.getCoursename()+" Added ").build();
			
		   
	}
	
	@POST
	@Path("/removecourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeCourse(Course course) {
		
		try {
			ao.removeCourse(course.getCourseID());
		}
		catch(Exception e)
		{
			return Response.status(500).entity(e.getMessage()).build();
		}
		
         
		
        String result = "Removed course : " + course.getCourseID();
		return Response.status(201).entity(result).build();
	}
	
	@POST
	@Path("/addprofessor")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProfessor(Professor prof) {
		
		try {
			ao.addProfessor(prof);
		}
		catch(Exception e)
		{
			return Response.status(500).entity(e.getMessage()).build();
		}
		
        String result = "Added Professor : " + prof.getName();
		return Response.status(201).entity(result).build();
	}
	
	@POST
	@Path("/removeprofessor")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeProfessor(Professor prof) {
		
		try {
			ao.removeProfessor(prof.getInstructorID());
		}
		catch(Exception e)
		{
			return Response.status(500).entity(e.getMessage()).build();
		}
		
        String result = "Removed ProfessorID :" + prof.getInstructorID();
		return Response.status(201).entity(result).build();
	}

	@POST
	@Path("/generatereportcard")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response generateReportCard(@NotNull @QueryParam("studentId") int studentID ) {
		
		try {
			ao.generateReportCard(studentID);
		}
		catch(Exception e)
		{
			return Response.status(500).entity(e.getMessage()).build();
		}
		
        String result = "Report card generated for StudentID :" + studentID;
		return Response.status(201).entity(result).build();
	}
	
	@POST
	@Path("/approvestudentregistration")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response approveStudentRegistration(@NotNull @QueryParam("studentId") int studentID) {
		
		try {
			ao.approveStudentRegistration(studentID,constants.SemesterID);
		}
		catch(Exception e)
		{
			return Response.status(500).entity(e.getMessage()).build();
		}
		
        String result = "Registration approved for StudentID :" + studentID;
		return Response.status(201).entity(result).build();
	}
	
	@GET
	@Path("/viewpendingstudentaccounts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> viewPendingStudentAccountApprovals() {
		
		try {
			List<Student> pendingStudents = ao.getPendingStudentAccountsList();
			return pendingStudents;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	@POST
	@Path("/approvependingstudentaccount")
	@Produces(MediaType.APPLICATION_JSON)
	public Response approvePendingStudentAccount(@NotNull @QueryParam("studentID") Integer studentID) {
		
		try {
			ao.approveStudentAccount(studentID);
		}
		catch(Exception e)
		{
			return Response.status(500).entity(e.getMessage()).build();
		}
		
        String result = "Account approved for StudentID :" + studentID;
		return Response.status(201).entity(result).build();
	}
	
	@POST
	@Path("/approvestudentregistration")
	@Produces(MediaType.APPLICATION_JSON)
	public Response approveStudentRegistration(@NotNull @QueryParam("studentID") Integer studentID) {
		
		try {
			ao.approveStudentRegistration(studentID,constants.SemesterID);
		}
		catch(Exception e)
		{
			return Response.status(500).entity(e.getMessage()).build();
		}
		
        String result = "Registration approved for StudentID :" + studentID;
		return Response.status(201).entity(result).build();
	}
	
	@POST
	@Path("/viewallcourses")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String,ArrayList<Integer>> viewAllCourses() {
		
		try {
			HashMap<String,ArrayList<Integer>> CourseStudentList = ao.viewCourseStudentList ("",constants.SemesterID,true);
			return CourseStudentList;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	@POST
	@Path("/viewcoursebyid")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String,ArrayList<Integer>> viewCourseByID(@NotNull @QueryParam("courseID") String courseID) {
		
		try {
			HashMap<String,ArrayList<Integer>> CourseStudentList = ao.viewCourseStudentList (courseID,constants.SemesterID,false);
			return CourseStudentList;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}
