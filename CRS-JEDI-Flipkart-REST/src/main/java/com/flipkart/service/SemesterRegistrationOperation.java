/**
 * 
 */
package com.flipkart.service;


import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Payment;
import com.flipkart.bean.RegisteredCourses;
import com.flipkart.bean.SemesterRegistration;
import com.flipkart.dao.SemesterRegistrationDaoInterface;
import com.flipkart.dao.SemesterRegistrationDaoOperation;
import com.flipkart.exception.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * @author Asus
 *
 */
public class SemesterRegistrationOperation implements SemesterRegistrationInterface{
	
	private static volatile SemesterRegistrationOperation instance = null;
	private static final Logger logger = LogManager.getLogger(SemesterRegistration.class);
	SemesterRegistrationDaoInterface srdo = SemesterRegistrationDaoOperation.getInstance();

	private SemesterRegistrationOperation() {
	}

	/**
	 * Method to make Registration Operation Singleton
	 * 
	 * @return
	 */
	public static SemesterRegistrationOperation getInstance() {
		if (instance == null) {
			synchronized (SemesterRegistrationOperation.class) {
				instance = new SemesterRegistrationOperation();
			}
		}
		return instance;
	}

	@Override
	public boolean addCourse(int studentId, int semesterId, String courseId, boolean isPrimary) throws CourseNotFoundException, CourseSeatsUnavailableException, CourseExistsInCartException {

//		try {

			return srdo.addCourse(studentId, semesterId, courseId, isPrimary);

//		} catch (CourseNotFoundException | CourseSeatsUnavailableException | CourseExistsInCartException e) {
//			logger.error(e.getMessage());
//		}
//		return false;
	}

	@Override
	public boolean dropCourse(int studentId, int semesterId, String courseId) throws CourseNotFoundException, CourseNotInCart {

//		try {

			return srdo.dropCourse(studentId, semesterId, courseId);

//		} catch (CourseNotFoundException | CourseNotInCart e) {
//			logger.error(e.getMessage());
//		}
//		return false;
	}

	@Override
	public ArrayList<Course> viewAvailableCourses() throws Exception {

//		try {

			ArrayList<Course> courseCatalog = srdo.viewAvailableCourses();
			if(courseCatalog == null) {
				throw new Exception("Error encountered while retrieving course catalog");
			}

			return srdo.viewAvailableCourses();

//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}
//		return null;
	}

	@Override
	public boolean finishRegistration(int studentId, int semesterId) throws InvalidSemesterRegistration {

//		try {

			return srdo.finishRegistration(studentId, semesterId);

//		} catch (InvalidSemesterRegistration e) {
//			logger.error(e.getMessage());
//		}
//		return false;
	}
}
