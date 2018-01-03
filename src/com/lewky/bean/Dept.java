package com.lewky.bean;

import java.io.Serializable;

public class Dept implements Serializable{

	private String deptNum;
	
	private String deptName;
	
	private String deptChairman;
	
	private String telephone;
	
	private String description;
	
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

	public String getDeptChairman() {
		return deptChairman;
	}

	public void setDeptChairman(String deptChairman) {
		this.deptChairman = deptChairman;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
