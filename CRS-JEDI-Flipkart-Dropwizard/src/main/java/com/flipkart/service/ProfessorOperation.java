/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import com.flipkart.bean.Course;
//import com.flipkart.bean.Grade;
import com.flipkart.bean.RegisteredCourses;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.GradeNotAddedException;
import com.flipkart.exception.NoStudentInCourseException;
import com.flipkart.exception.ProfessorCourseRegistrationException;
import com.flipkart.exception.ProfessorNotAssignedException;
import com.flipkart.exception.StudentNotRegisteredException;

/**
 * @author rutwi
 *
 */

public class ProfessorOperation implements ProfessorInterface {

	private static Logger logger = Logger.getLogger(ProfessorOperation.class);
	
	private static volatile ProfessorOperation instance=null;
	private ProfessorOperation()
	{

	}
	
	/**
	 * Method to make ProfessorOperation Singleton
	 * @return
	 */
	public static ProfessorOperation getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(ProfessorOperation.class){
				instance=new ProfessorOperation();
			}
		}
		return instance;
	}

	//Add grade
	public void addGrade(Integer studentID, Integer semesterID, String courseID, Integer grade) throws SQLException, GradeNotAddedException, StudentNotRegisteredException  
	{
		
		ProfessorDaoInterface profObj= ProfessorDaoOperation.getInstance();
		profObj.addGrade(studentID, semesterID,courseID, grade);
		System.out.println("Grade added successfully");
	}

	//view student details who have registered for a particular course

	public ArrayList<RegisteredCourses> viewCourseStudents(String courseID, Integer semesterID) throws SQLException, NoStudentInCourseException {
		
		ArrayList<RegisteredCourses>ans = new ArrayList<RegisteredCourses>();
		
		ProfessorDaoInterface profObj= ProfessorDaoOperation.getInstance();
		
		ans = profObj.viewCourseStudents(courseID, semesterID);
		for (RegisteredCourses r:ans) {
			System.out.println("studentID = " + r.getStudentID()+ " Semester ID = "+r.getSemesterID());
		}
		
		return ans;
		
			
	}

	//view course details which the professor is associated with
	public ArrayList<Course> viewCourseProf(int instructorID) throws SQLException, ProfessorNotAssignedException {
	
		ArrayList<Course>ans = new ArrayList<Course>();
		ProfessorDaoInterface profObj= ProfessorDaoOperation.getInstance();
		ans = profObj.viewCourseProf(instructorID);
		for (Course c: ans) {
			System.out.println("CourseID = " + c.getCourseID()+ " Course Name = " + c.getCoursename());
		}
		return ans;
		
	}


	public void registerCourse(int instructorID, Integer semesterID, String courseID) throws Exception {
		ProfessorDaoInterface profObj= ProfessorDaoOperation.getInstance();
		Boolean ans = profObj.registerCourse(instructorID, semesterID, courseID);
	}

}
