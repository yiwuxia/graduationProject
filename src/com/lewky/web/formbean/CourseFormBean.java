package com.lewky.web.formbean;

import java.io.Serializable;

public class CourseFormBean implements Serializable {

	private String courseNum; // �γ̺���

	private String courseName; // �γ�����

	private String teacherNum; // ��ʦ����

	private String teacherName; // ��ʦ����

	private String schoolYear; // ѧ��

	private String semester; // ѧ��

	private String coursePeriod; // ѧʱ

	private String courseCredit; // ѧ��
	
	private String week; // �Ͽ��ܴ�

	private String weekday; // �Ͽ��մ�
	
	private String time; // �Ͽνڴ�
	
	private String place; // �Ͽεص�

	private String description; // �γ̽���

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

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getCoursePeriod() {
		return coursePeriod;
	}

	public void setCoursePeriod(String coursePeriod) {
		this.coursePeriod = coursePeriod;
	}

	public String getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(String courseCredit) {
		this.courseCredit = courseCredit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "CourseFormBean [courseNum=" + courseNum + ", courseName="
				+ courseName + ", teacherNum=" + teacherNum + ", teacherName="
				+ teacherName + ", schoolYear=" + schoolYear + ", semester="
				+ semester + ", coursePeriod=" + coursePeriod
				+ ", courseCredit=" + courseCredit + ", week=" + week
				+ ", weekday=" + weekday + ", time=" + time + ", place="
				+ place + ", description=" + description + "]";
	}

}
