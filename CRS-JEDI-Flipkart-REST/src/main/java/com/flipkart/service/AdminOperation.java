package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.flipkart.bean.*;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.exception.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author rutwi
 *
 */

public class AdminOperation implements AdminInterface {

	private static volatile AdminOperation instance = null;
	private static final Logger logger = LogManager.getLogger(AdminOperation.class);
	
	private AdminOperation()
	{
		
	}
	
	/**
	 * Method to make AdminOperation Singleton
	 */
	public static AdminOperation getInstance()
	{
		if(instance == null)
		{
			synchronized(AdminOperation.class){
				instance = new AdminOperation();
			}
		}
		return instance;
	}

	AdminDaoInterface ado  = AdminDaoOperation.getInstance();

	@Override
	public void approveStudentRegistration(int studentId,int semesterId) throws FeesPendingException, StudentNotApprovedException {

//		try {
			ado.approveStudentRegistration(studentId,semesterId);
//		} catch (FeesPendingException | StudentNotApprovedException e) {
//			logger.error(e.getMessage());
//		}
	}

	@Override
	public void addProfessor(Professor professor) {

		ado.addProfessor(professor);
	}

	@Override
	public void removeProfessor(int professorID) throws ProfessorNotRegisteredException {
//		try {
		
			ado.removeProfessor(professorID);
//		}
//		catch(ProfessorNotRegisteredException e) {
//			logger.error(e.getMessage());
//		}
	}

	@Override
	public void removeCourse(String courseID) throws CourseNotFoundException {

//		try {

			ado.removeCourse(courseID);

//		}

//		catch(CourseNotFoundException e) {
//			logger.error(e.getMessage());
//		}
	}
	

	@Override
	public void addCourse(String course_name, String courseID, int semester) {
		// TODO Auto-generated method stub
		Course newCourse = new Course();
		newCourse.setCoursename(course_name);
		newCourse.setCourseID(courseID);
		newCourse.setOfferedSemester(semester);
		newCourse.setAvailableSeats(10);

		ado.addCourse(newCourse);
	}

	
	@Override
	public HashMap<String,ArrayList<Integer> > viewCourseStudentList(String courseID, int semester, Boolean viewAll) {

		return ado.viewCourseStudentList(courseID,semester,viewAll);
	}

	@Override
	public ReportCard generateReportCard(int studentID) throws StudentNotApprovedException, GradeNotAddedException, FeesPendingException, ReportCardNotGeneratedException {

		ReportCard R = new ReportCard();
//		try {

			R= ado.generateReportCard(studentID);

//		} catch (StudentNotApprovedException e) {
//			logger.error(e.getMessage());
//		}
		return R;
	}

	
	@Override
	public List<Student> getPendingStudentAccountsList() {

		return ado.getPendingStudentAccountsList();
		
	}

	@Override
	public void approveStudentAccount(Integer studentID) {
		ado.approveStudentAccount(studentID);
	}
}