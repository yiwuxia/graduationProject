package com.lewky.bean;

import java.io.Serializable;

public class Major implements Serializable{

	private String majorNum;

	private String deptNum;
	
	private String majorName;
	
	private String majorAssistant;
	
	private String telephone;
	
	private String description;

	public String getMajorNum() {
		return majorNum;
	}

	public void setMajorNum(String majorNum) {
		this.majorNum = majorNum;
	}

	public String getDeptNum() {
		return deptNum;
	}

	public void setDeptNum(String deptNum) {
		this.deptNum = deptNum;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getMajorAssistant() {
		return majorAssistant;
	}

	public void setMajorAssistant(String majorAssistant) {
		this.majorAssistant = majorAssistant;
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
