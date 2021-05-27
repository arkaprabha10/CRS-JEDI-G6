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
import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.CourseNotAssignedException;
import com.flipkart.exception.CourseNotDeletedException;
import com.flipkart.exception.CourseNotFoundException;
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
	 */
	public boolean addCourse(int studentId, int semesterId, String courseId, boolean isPrimary);
	
	/**
	 * Method to drop Course selected by student 
	 * @param studentId
	 * @param semesterId
	 * @param courseId 
	 * @return Boolean value indicating if it is was dropped successfully
	 */

	public boolean dropCourse(int studentId, int semesterId, String courseId);
	
	/**
	 * Method to view all courses available
	 * @return list of all courses with availbale seats
	 */

	public ArrayList<Course> viewAvailableCourses();

	/**
	 * @param studentId
	 * @param semesterId
	 * @return
	 */
	public boolean finishRegistration(int studentId, int semesterId);
}
