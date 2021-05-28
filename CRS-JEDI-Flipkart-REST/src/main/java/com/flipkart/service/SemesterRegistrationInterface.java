/**
 * 
 */
package com.flipkart.service;

import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Payment;
import com.flipkart.bean.RegisteredCourses;
import com.flipkart.exception.CourseAlreadyRegisteredException;
import com.flipkart.exception.CourseExistsInCartException;
import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.CourseNotAssignedException;
import com.flipkart.exception.CourseNotDeletedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.CourseNotInCart;
import com.flipkart.exception.CourseSeatsUnavailableException;
import com.flipkart.exception.InvalidSemesterRegistration;
import com.flipkart.exception.StudentNotRegisteredException;

/**
 * @author Asus
 *
 */
public interface SemesterRegistrationInterface {
	
	/**
	 * Method to add Course selected by student 
	 * @param studentId
	 * @param semesterId
	 * @param courseId 
	 * @return the course if it is added successfully, else null
	 * @throws CourseExistsInCartException 
	 * @throws CourseSeatsUnavailableException 
	 * @throws CourseNotFoundException 
	 */
	public boolean addCourse(int studentId, int semesterId, String courseId, boolean isPrimary) throws CourseNotFoundException, CourseSeatsUnavailableException, CourseExistsInCartException;
	
	/**
	 * Method to drop Course selected by student 
	 * @param studentId
	 * @param semesterId
	 * @param courseId 
	 * @return Boolean value indicating if it is was dropped successfully
	 * @throws CourseNotInCart 
	 * @throws CourseNotFoundException 
	 */

	public boolean dropCourse(int studentId, int semesterId, String courseId) throws CourseNotFoundException, CourseNotInCart;
	
	/**
	 * Method to view all courses available
	 * @return list of all courses with availbale seats
	 * @throws Exception 
	 */

	public ArrayList<Course> viewAvailableCourses() throws Exception;

	/**
	 * @param studentId
	 * @param semesterId
	 * @return
	 * @throws InvalidSemesterRegistration 
	 */
	public boolean finishRegistration(int studentId, int semesterId) throws InvalidSemesterRegistration;
}
