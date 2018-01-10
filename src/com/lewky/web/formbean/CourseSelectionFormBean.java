package com.lewky.web.formbean;

import java.io.Serializable;

public class CourseSelectionFormBean implements Serializable{

	private String studentNum;

	private String studentName;
	
	private String courseNum;

	private String courseName;
	
	private String teacherNum;

	private String teacherName;
	
	private String regularGrade;	//平时成绩

	private String midtermGrade;	//期中成绩
	
	private String finalExamGrade;	//期末成绩

	private String grade;	//综合成绩

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTeacherNum() {
		return teacherNum;
	}

	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getRegularGrade() {
		return regularGrade;
	}

	public void setRegularGrade(String regularGrade) {
		this.regularGrade = regularGrade;
	}

	public String getMidtermGrade() {
		return midtermGrade;
	}

	public void setMidtermGrade(String midtermGrade) {
		this.midtermGrade = midtermGrade;
	}

	public String getFinalExamGrade() {
		return finalExamGrade;
	}

	public void setFinalExamGrade(String finalExamGrade) {
		this.finalExamGrade = finalExamGrade;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
