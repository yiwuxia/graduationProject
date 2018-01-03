package com.lewky.bean;

import java.io.Serializable;
import java.util.Date;

public class Manager implements Serializable{

	private String managerNum;

	private String name;
	
	private String gender;
	
	private Date birthday;
	
	private String password;
	
	private String email;

	private String cellphone;

	public String getManagerNum() {
		return managerNum;
	}

	public void setManagerNum(String managerNum) {
		this.managerNum = managerNum;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
