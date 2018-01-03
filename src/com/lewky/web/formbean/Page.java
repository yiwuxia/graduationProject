package com.lewky.web.formbean;

import java.io.Serializable;
import java.util.List;

import com.lewky.bean.Course;
import com.lewky.bean.CourseSelection;
import com.lewky.bean.Student;
import com.lewky.bean.Teacher;

public class Page implements Serializable{
	
	private int currentPageIndex = 1;	//��ǰҳ������
	
	private int pageCount;	//�ܹ��ж���ҳ
	
	private int count ;	//ÿҳҪ��ʾ��������
	
	private int totalDataCount;	//�����ж���������
	
	private int startIndex;	//��ʼҳ��
	
	private int endIndex;	//����ҳ��
	
	private List<Course> coursesList;	//ҳ��Ҫ��ʾ�����пγ����ݵļ���

	private List<Student> studentsList;	//ҳ��Ҫ��ʾ������ѧ�����ݵļ���
	
	private List<Teacher> teachersList;	//ҳ��Ҫ��ʾ�����н�ʦ���ݵļ���
	
	private List<CourseSelection> courseSelectionsList;	//ҳ��Ҫ��ʾ�����пγ���ϸ��Ϣ�ļ���
	
	public Page(int totalDataCount, int count){
		this.totalDataCount = totalDataCount;
		this.count = count;
		
		//���㹲�ж���ҳ
		pageCount = (totalDataCount + count - 1)/count;
		
		if (pageCount <= 5) {
			//�����ҳ��С��5������ҳ��Ϊ��ҳ����
			endIndex = pageCount;
			//���㿪ʼҳ��
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
			//�����ҳ��С��5������ҳ��Ϊ��ҳ����
			endIndex = pageCount;
			//���㿪ʼҳ��
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
