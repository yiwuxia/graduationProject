package com.lewky.service.impl;

import java.util.List;

import com.lewky.bean.Course;
import com.lewky.bean.Dept;
import com.lewky.bean.Major;
import com.lewky.bean.Manager;
import com.lewky.bean.Student;
import com.lewky.bean.Teacher;
import com.lewky.dao.ManagerDao;
import com.lewky.dao.impl.ManagerDaoImpl;
import com.lewky.service.ManagerService;
import com.lewky.web.formbean.Page;

public class ManagerServiceImpl implements ManagerService {

	ManagerDao dao = new ManagerDaoImpl();
	
	@Override
	public Manager login(String managerNum, String password) {

		return dao.login(managerNum, password);
	}
	
	@Override
	public Page listPageStudents(int currentPageIndex, int count,
			String majorName) {
		
		//查询表中的记录数
		int totalDataCount = dao.showStudentsCount(majorName);
		//创建Page对象
		Page page = new Page(totalDataCount, count);
		//设置当前页面索引
		page.setCurrentPageIndex(currentPageIndex);
		//设定页面要显示的数据的集合
		page.setStudentsList(dao.listPageStudents(currentPageIndex, count, majorName));
		
		return page;
	}
	
	@Override
	public Page listPageTeachers(int currentPageIndex, int count,
			String deptName) {
		
		//查询表中的记录数
		int totalDataCount = dao.showTeachersCount(deptName);
		//创建Page对象
		Page page = new Page(totalDataCount, count);
		//设置当前页面索引
		page.setCurrentPageIndex(currentPageIndex);
		//设定页面要显示的数据的集合
		page.setTeachersList(dao.listPageTeachers(currentPageIndex, count, deptName));
		
		return page;
	}
	
	@Override
	public List<Course> listCourses() {

		return dao.listCourses();
	}

	@Override
	public List<Course> listExaminedCourses() {

		return dao.listExaminedCourses();
	}
	
	@Override
	public boolean addStudent(Student student) {

		return dao.addStudent(student);
	}

	@Override
	public boolean addTeacher(Teacher teacher) {

		return dao.addTeacher(teacher);
	}

	@Override
	public boolean examineCourses(String[] courseNums, String op) {

		return dao.examineCourses(courseNums, op);
	}

	@Override
	public boolean deleteStudents(String[] studentNums) {

		return dao.deleteStudents(studentNums);
	}

	@Override
	public boolean deleteTeachers(String[] teacherNums) {

		return dao.deleteTeachers(teacherNums);
	}

	@Override
	public boolean deleteCourses(String[] courseNums) {

		return dao.deleteCourses(courseNums);
	}

	@Override
	public boolean updateCourse(Course course) {

		return dao.updateCourse(course);
	}

	@Override
	public boolean updateStudent(Student student) {

		return dao.updateStudent(student);
	}
	
	@Override
	public boolean updateTeacher(Teacher teacher) {

		return dao.updateTeacher(teacher);
	}
	
	@Override
	public Course findCourseByCourseNum(String courseNum) {

		return dao.findCourseByCourseNum(courseNum);
	}
	
	@Override
	public Student findStudentByStudentNum(String studentNum) {

		return dao.findStudentByStudentNum(studentNum);
	}
	
	@Override
	public Teacher findTeacherByTeacherNum(String teacherNum) {

		return dao.findTeacherByTeacherNum(teacherNum);
	}
	
	@Override
	public boolean changePassword(String managerNum, String newPassword) {

		return dao.changePassword(managerNum, newPassword);
	}
	
	@Override
	public boolean showStatus() {

		return dao.showStatus();
	}
	
	@Override
	public Boolean permitCourseSelection(String schoolYear, String semester,
			String status) {

		return dao.permitCourseSelection(schoolYear, semester, status);
	}
	
	@Override
	public String showSchoolYear() {

		return dao.showSchoolYear();
	}
	
	@Override
	public String showSemester() {

		return dao.showSemester();
	}
	
	@Override
	public List<Dept> showDept() {

		return dao.showDept();
	}
	
	@Override
	public List<Major> showMajor() {

		return dao.showMajor();
	}
	
	@Override
	public Manager showInfo(String managerNum) {

		return dao.showInfo(managerNum);
	}
	
	@Override
	public Page listPageCourses(int currentPageIndex, int count, String schoolYear, String semester) {
		
		//查询表中的记录数
		int totalDataCount = showTotalDataCount(schoolYear, semester);
		//创建Page对象
		Page page = new Page(totalDataCount, count);
		//设置当前页面索引
		page.setCurrentPageIndex(currentPageIndex);
		//设定页面要显示的数据的集合
		page.setCoursesList(dao.listPageCourses(currentPageIndex, count, schoolYear, semester));
		
		return page;
	}
	
	@Override
	public int showTotalDataCount(String schoolYear, String semester) {
	
		return dao.showTotalDataCount(schoolYear, semester);
	}
	
	@Override
	public String showNewTeacherNum() {

		return dao.showNewTeacherNum();
	}
	
	@Override
	public String showNewStudentNum() {

		return dao.showNewStudentNum();
	}
	
	@Override
	public Page listPageExaminedCourses(int currentPageIndex, int count) {
		
		//查询表中的记录数
		int totalDataCount = dao.showExaminedCoursesCount();
		//创建Page对象
		Page page = new Page(totalDataCount, count);
		//设置当前页面索引
		page.setCurrentPageIndex(currentPageIndex);
		//设定页面要显示的数据的集合
		page.setCoursesList(dao.listPageExaminedCourses(currentPageIndex, count));
		
		return page;
	}
}
