package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.ReportCard;
import com.flipkart.bean.Student;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exception.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StudentOperation implements StudentInterface {

	private static final Logger logger = LogManager.getLogger(StudentOperation.class);
	private static volatile StudentOperation instance=null;
	StudentDaoInterface SDO =StudentDaoOperation.getInstance();

	public StudentOperation()
	{
		
	}
	/**
	 * Method to make StudentOperation Singleton
	 * @return
	 */
	public static StudentOperation getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(StudentOperation.class){
				instance=new StudentOperation();
			}
		}
		return instance;
	}

	@Override
	public ReportCard viewReportCard(int StudentID, int semesterId) throws GradeNotAddedException, StudentNotApprovedException, FeesPendingException, ReportCardNotGeneratedException  {

		ReportCard R = new ReportCard();

//		try {
			R = SDO.viewReportCard(StudentID,semesterId);
			System.out.println("StudentID : "+R.getStudentID()+"\t SemesterID : "+R.getSemesterID());
	    	System.out.println("Course  Grade");
	    	R.getGrades().forEach((key, value) -> {
	    		System.out.println(key + "    " + value);
	    		});

//		} catch (ReportCardNotGeneratedException | GradeNotAddedException | StudentNotApprovedException | FeesPendingException e) {
//			logger.error(e.getMessage());
//		}

		ReportCardOperation report = new ReportCardOperation();
		R.setSpi(report.getSPI(R));
		return R;
	}

	@Override
	public List<Course> viewRegisteredCourses(int studentID, int semesterId) throws StudentNotRegisteredException {

//		try {
			List<Course> courses = SDO.viewRegisteredCourses(studentID,semesterId);

			System.out.println("=======================================");
			System.out.println("Registered courses :");
			System.out.println("---------------------------------------");
			System.out.println("Primary courses :");
			for(Course c: courses) {
				if(c.getPrimary()) {
					System.out.println("Course ID : "+c.getCourseID()+" \t Course Name : "+ c.getCoursename()+"\t Instructor : "+c.getInstructorID());
				}
			}
			System.out.println("---------------------------------------");
			System.out.println("Alternate courses :");
			for(Course c: courses) {
				if(!c.getPrimary()) {
					System.out.println("Course ID : "+c.getCourseID()+" \t Course Name : "+ c.getCoursename()+"\t Instructor : "+c.getInstructorID());
				}
			}
			System.out.println("=======================================");
//
//		} catch (StudentNotRegisteredException e) {
//			logger.error(e.getMessage());
//		}
			return courses;
	}

	@Override
	public Student addStudent(String userName, String name, String password,String department ,String contactNumber, Integer joiningYear) throws UserAlreadyInUseException {

		Student newStudent = new Student();

//		try {
			newStudent.setUserID(userName);
			newStudent.setName(name);
			newStudent.setPassword(password);
			newStudent.setDepartment(department);
			newStudent.setContactNumber(contactNumber);
			newStudent.setJoiningYear(joiningYear);
			SDO.addStudent(newStudent);

			return newStudent;

//		} catch (UserAlreadyInUseException e) {
//			logger.error(e.getMessage());
//		}

//		return null;
	}

	public int getStudentIDFromUserName(String username) throws StudentNotRegisteredException {

//		try {
			return SDO.getStudentIDFromUserName(username);
//		} catch (StudentNotRegisteredException e) {
//			logger.error(e.getMessage());
//		}

//		return -1;
	}
	
	public static void main(String[] args) {
		System.out.println("Hey There!");
		StudentOperation so = new StudentOperation();

//			so.addStudent("09.Charlie", "Drake", "6273","EE", "2538389027", 2021);
//			List<Course> l=so.viewRegisteredCourses(1, 1);
//			for (Course el: l ) {
//				System.out.println(el.getCourseID());
//			}
//			so.viewReportCard(1, 1);
	}
	
}
