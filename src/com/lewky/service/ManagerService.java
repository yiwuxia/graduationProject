package com.lewky.service;

import java.util.List;

import com.lewky.bean.Course;
import com.lewky.bean.CourseSelection;
import com.lewky.bean.Dept;
import com.lewky.bean.Major;
import com.lewky.bean.Manager;
import com.lewky.bean.Student;
import com.lewky.bean.Teacher;
import com.lewky.web.formbean.Page;

public interface ManagerService {

	/**
	 * 通过管理员工号和密码进行登录
	 * 
	 * @param managerNum
	 *            管理员工号
	 * @param password
	 *            密码
	 * @return 登录成功返回该管理员，否则返回null
	 */
	public Manager login(String managerNum, String password);

	/**
	 * 列出当前页面索引的学生
	 * @param currentPageIndex	当前页面的索引
	 * @param count	每页要显示的数据数量
	 * @param deptName	要查询的学生的专业号码
	 * @return	查询学生则返回当前页面学生的页面，否则返回null
	 */
	public Page listPageStudents(int currentPageIndex, int count,
			String majorName);

	/**
	 * 列出当前页面索引的教师
	 * @param currentPageIndex	当前页面的索引
	 * @param count	每页要显示的数据数量
	 * @param deptName	要查询的教师的系号码
	 * @return	查询到教师则返回当前页面教师的页面，否则返回null
	 */
	public Page listPageTeachers(int currentPageIndex, int count,
			String deptName);

	/**
	 * 列出所有课程
	 * 
	 * @return
	 */
	public List<Course> listCourses();

	/**
	 * 添加一个学生
	 * 
	 * @param student
	 *            要添加的学生
	 * @return
	 */
	public boolean addStudent(Student student);

	/**
	 * 添加一个教师
	 * 
	 * @param teacher
	 *            要添加的教师
	 * @return
	 */
	public boolean addTeacher(Teacher teacher);

	/**
	 * 审核课程
	 * 
	 * @param courseNums
	 *            要审核的课程号码
	 * @param op
	 *            要进行的操作
	 * @return
	 */
	public boolean examineCourses(String[] courseNums, String op);

	/**
	 * 删除学生
	 * 
	 * @param studentNums
	 *            要删除的学生学号
	 * @return
	 */
	public boolean deleteStudents(String[] studentNums);

	/**
	 * 删除教师
	 * 
	 * @param teacherNums
	 *            要删除的教师工号
	 * @return
	 */
	public boolean deleteTeachers(String[] teacherNums);

	/**
	 * 删除课程
	 * 
	 * @param courseNums
	 *            要删除的课程号码
	 * @return
	 */
	public boolean deleteCourses(String[] courseNums);

	/**
	 * 通过课程号码找到课程
	 * 
	 * @param courseNum
	 *            要找的课程的号码
	 * @return 找到则返回该课程，否则返回null
	 */
	public Course findCourseByCourseNum(String courseNum);
	
	/**
	 * 通过学号找到学生
	 * 
	 * @param courseNum
	 *            要找的学生的学号
	 * @return 找到则返回该学生，否则返回null
	 */
	public Student findStudentByStudentNum(String studentNum);
	
	/**
	 * 通过教师工号找到教师
	 * 
	 * @param courseNum
	 *            要找的教师的工号
	 * @return 找到则返回该教师，否则返回null
	 */
	public Teacher findTeacherByTeacherNum(String teacherNum);
	
	/**
	 * 修改课程
	 * 
	 * @param courseNum
	 *            要修改的课程
	 * @return
	 */
	public boolean updateCourse(Course course);
	
	/**
	 * 修改学生
	 * 
	 * @param course
	 *            要修改的学生
	 * @return
	 */
	public boolean updateStudent(Student student);
	
	/**
	 * 修改教师
	 * 
	 * @param course
	 *            要修改的教师
	 * @return
	 */
	public boolean updateTeacher(Teacher teacher);
	
	/**
	 * 列出所有需要审核的课程
	 * 
	 * @return
	 */
	public List<Course> listExaminedCourses();

	/**
	 * 修改密码
	 * 
	 * @param managerNum
	 *            要修改的管理员的号码
	 * @param newPassword
	 *            新密码
	 * @return 修改成功返回true，否则返回false
	 */
	public boolean changePassword(String managerNum, String newPassword);

	/**
	 * 查询当前选课状态
	 * 
	 * @return
	 */
	public boolean showStatus();

	/**
	 * 设置选课状态和学年学期
	 * 
	 * @param schoolYear
	 *            学年
	 * @param semester
	 *            学期
	 * @param status
	 *            选课状态
	 * @return
	 */
	public Boolean permitCourseSelection(String schoolYear, String semester,
			String status);
	
	/**
	 * 查询当前的学期
	 * @return
	 */
	public String showSemester();

	/**
	 * 查询当前的学年
	 * @return
	 */
	public String showSchoolYear();
	
	/**
	 * 查询学院信息
	 * @return
	 */
	public List<Dept> showDept();
	
	/**
	 * 查询专业信息
	 * @return
	 */
	public List<Major> showMajor();
	
	/**
	 * 查询个人信息
	 * @return
	 */
	public Manager showInfo(String managerNum);
	
	/**
	 * 根据当前页面索引查询此页面要显示的数据
	 * 
	 * @param currentPageIndex
	 *            当前页面的索引
	 * @param count
	 *            页面要显示的数据数量
	 * @param schoolYear
	 *            当前学年
	 * @param semester
	 *            当前学期
	 * @return 返回此页面对象
	 */
	public Page listPageCourses(int currentPageIndex, int count,
			String schoolYear, String semester);
	
	/**
	 * 获取表中数据的总数
	 * 
	 * @param schoolYear
	 *            当前学期
	 * @param semester
	 *            当前学年
	 * @return
	 */
	public int showTotalDataCount(String schoolYear, String semester);
	
	/**
	 * 获取新的教师工号
	 * @return
	 */
	public String showNewTeacherNum();
	
	/**
	 * 获取新的学生学号
	 * 
	 * @return
	 */
	public String showNewStudentNum();
	
	/**
	 * 列出当前页面需要审核的课程
	 * 
	 * @param currentPageIndex
	 *            当前页面的索引
	 * @param count
	 *            页面要显示的数据数量
	 * @return	返回此页面对象
	 */
	public Page listPageExaminedCourses(int currentPageIndex, int count);

}
