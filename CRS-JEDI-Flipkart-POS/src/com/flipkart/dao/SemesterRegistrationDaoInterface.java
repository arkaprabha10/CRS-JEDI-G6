/**
 * 
 */
package com.flipkart.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Payment;
import com.flipkart.bean.RegisteredCourses;
import com.flipkart.exception.*;

/**
 * @author Asus
 *
 */
public interface SemesterRegistrationDaoInterface {
	
	/**
	 * Method to add Course selected by student 
	 * @param studentId
	 * @param semesterId
	 * @param courseId
	 * @return the course if it is added successfully, else null
	 */
	public boolean addCourse(int studentId, int semesterId, String courseId, boolean isPrimary) throws SQLException, CourseNotFoundException, CourseSeatsUnavailableException, CourseExistsInCartException;
	
	/**
	 * Method to drop Course selected by student 
	 * @param studentId
	 * @param semesterId
	 * @param courseId 
	 * @return Boolean value indicating if it is was dropped successfully
	 */
	public boolean dropCourse(int studentId, int semesterId, String courseId) throws SQLException, CourseNotInCart, CourseNotFoundException;

	/**
	 * @param studentId
	 * @param semesterId
	 * @return
	 * @throws SQLException
	 */
	public boolean finishRegistration(int studentId, int semesterId) throws SQLException, InvalidSemesterRegistration;
	
	/**
	 * Method to view all courses available
	 * @return list of all courses with availbale seats
	 */
	public ArrayList<Course> viewAvailableCourses() throws SQLException;
	
	
	/**
	 * Method to calculate student Fees
	 * @param studentId
	 * @param semesterId
	 * @return total fees to be paid by the student
	 */
	public int calculateFees(int studentId, int semesterId) ;
	
	
	/**
	 * Method to pay student Fees
	 * @param studentId
	 * @param semesterId
	 * @param paymentMode 
	 * @return Boolean value indicating if payment was successful
	 */
	public Payment payFees(int studentId, int semesterId, String paymentMode) throws SQLException ;
	
	
}
