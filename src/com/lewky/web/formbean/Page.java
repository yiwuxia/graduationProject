package com.lewky.web.formbean;

import java.io.Serializable;
import java.util.List;

import com.lewky.bean.Course;
import com.lewky.bean.CourseSelection;
import com.lewky.bean.Student;
import com.lewky.bean.Teacher;

public class Page implements Serializable{
	
	private int currentPageIndex = 1;	//当前页的索引
	
	private int pageCount;	//总共有多少页
	
	private int count ;	//每页要显示多少数据
	
	private int totalDataCount;	//表中有多少条数据
	
	private int startIndex;	//开始页码
	
	private int endIndex;	//结束页码
	
	private List<Course> coursesList;	//页面要显示的所有课程数据的集合

	private List<Student> studentsList;	//页面要显示的所有学生数据的集合
	
	private List<Teacher> teachersList;	//页面要显示的所有教师数据的集合
	
	private List<CourseSelection> courseSelectionsList;	//页面要显示的所有课程详细信息的集合
	
	public Page(int totalDataCount, int count){
		this.totalDataCount = totalDataCount;
		this.count = count;
		
		//计算共有多少页
		pageCount = (totalDataCount + count - 1)/count;
		
		if (pageCount <= 5) {
			//如果总页数小于5，结束页码为总页码数
			endIndex = pageCount;
			//计算开始页码
			if (currentPageIndex < 3) {
				startIndex = 1;
				endIndex = pageCount;
			} else {
				endIndex = pageCount;
				startIndex = endIndex + 1 - pageCount;
			}
		} else {
			if (currentPageIndex <= 3) {
				endIndex = 5;
				startIndex = 1;
			} else {
				if (currentPageIndex + 2 > pageCount) {
					endIndex = pageCount;
				}else {
					endIndex = currentPageIndex + 2;
				}
				startIndex = endIndex - 4;
			}
		}
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
		
		if (pageCount <= 5) {
			//如果总页数小于5，结束页码为总页码数
			endIndex = pageCount;
			//计算开始页码
			if (currentPageIndex < 3) {
				startIndex = 1;
				endIndex = pageCount;
			} else {
				endIndex = pageCount;
				startIndex = endIndex + 1 - pageCount;
			}
		} else {
			if (currentPageIndex <= 3) {
				endIndex = 5;
				startIndex = 1;
			} else {
				if (currentPageIndex + 2 > pageCount) {
					endIndex = pageCount;
				}else {
					endIndex = currentPageIndex + 2;
				}
				startIndex = endIndex - 4;
			}
		}
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalDataCount() {
		return totalDataCount;
	}

	public void setTotalDataCount(int totalDataCount) {
		this.totalDataCount = totalDataCount;
	}

	public List<Course> getCoursesList() {
		return coursesList;
	}

	public void setCoursesList(List<Course> coursesList) {
		this.coursesList = coursesList;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public List<Student> getStudentsList() {
		return studentsList;
	}

	public void setStudentsList(List<Student> studentsList) {
		this.studentsList = studentsList;
	}

	public List<Teacher> getTeachersList() {
		return teachersList;
	}

	public void setTeachersList(List<Teacher> teachersList) {
		this.teachersList = teachersList;
	}

	public List<CourseSelection> getCourseSelectionsList() {
		return courseSelectionsList;
	}

	public void setCourseSelectionsList(List<CourseSelection> courseSelectionsList) {
		this.courseSelectionsList = courseSelectionsList;
	}
	
}
