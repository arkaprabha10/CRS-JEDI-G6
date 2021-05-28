/**
 * 
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.RegisteredCourses;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotAssignedException;
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
public interface ProfessorInterface {
	
	/**
	 * @param studentID
	 * @param courseID
	 * @param grade
	 * @throws StudentNotRegisteredException 
	 * @throws GradeNotAddedException 
	 * @throws SQLException 
	 */

	public void addGrade(Integer studentID, Integer semesterID,String courseID, Integer grade) throws SQLException, GradeNotAddedException, StudentNotRegisteredException ;


	/**
	 * @param courseID
	 * @param semesterID
	 * @return 
	 * @throws NoStudentInCourseException 
	 * @throws SQLException 
	 * @throws CourseNotFoundException
	 */

	public ArrayList<RegisteredCourses> viewCourseStudents(String courseID, Integer semesterID) throws SQLException, NoStudentInCourseException ;
	public ArrayList<Course> viewCourseProf(int instructorID) throws SQLException, ProfessorNotAssignedException ;
	public void registerCourse(int instructorID, Integer semesterID, String courseID) throws SQLException, ProfessorCourseRegistrationException, Exception ;
}
