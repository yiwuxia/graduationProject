package com.lewky.web.formbean;

import java.io.Serializable;

public class TeacherFormBean implements Serializable {

	private String teacherNum;

	private String deptNum;
	
	private String deptName;
	
	private String name;
	
	private String gender;
	
	private String formBirthday;
	
	private String password;
	
	private String teacherTitle;
	
	private String email; // 邮箱地址
	
	private String cellphone; // 联系电话

	public String getTeacherNum() {
		return teacherNum;
	}

	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}

	public String getDeptNum() {
		return deptNum;
	}

	public void setDeptNum(String deptNum) {
		this.deptNum = deptNum;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTeacherTitle() {
		return teacherTitle;
	}

	public void setTeacherTitle(String teacherTitle) {
		this.teacherTitle = teacherTitle;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

}
