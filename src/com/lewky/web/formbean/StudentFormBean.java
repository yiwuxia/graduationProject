package com.lewky.web.formbean;

import java.io.Serializable;

public class StudentFormBean implements Serializable {

	private String studentNum;

	private String deptNum;
	
	private String deptName;

	private String majorNum;

	private String majorName;

	private String gradeNum;
	
	private String classNum;

	private String name;

	private String gender;

	private String formBirthday;

	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFormBirthday() {
		return formBirthday;
	}

	public void setFormBirthday(String formBirthday) {
		this.formBirthday = formBirthday;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGradeNum() {
		return gradeNum;
	}

	public void setGradeNum(String gradeNum) {
		this.gradeNum = gradeNum;
	}

	public String getDeptNum() {
		return deptNum;
	}

	public void setDeptNum(String deptNum) {
		this.deptNum = deptNum;
	}

	public String getMajorNum() {
		return majorNum;
	}

	public void setMajorNum(String majorNum) {
		this.majorNum = majorNum;
	}

}
