package com.lewky.dao;

import java.util.List;

import com.lewky.bean.Course;
import com.lewky.bean.CourseSelection;
import com.lewky.bean.Student;

public interface StudentDao {

	/**
	 * 通过学号和密码进行登陆
	 * 
	 * @param studentNum
	 *            学号
	 * @param password
	 *            密码
	 * @return 登陆成功返回该学生，否则返回null
	 */
	public Student login(String studentNum, String password);

	/**
	 * 列出所有课程信息
	 * 
	 * @return 查找到则返回所有课程的集合，否则返回null
	 */
	public List<Course> listAllCourses();

	/**
	 * 列出已选的课程信息
	 * 
	 * @param studentNum
	 *            学生学号
	 * @return 返回已选的课程信息
	 */
	public List<Course> listSelectedCourses(String studentNum);

	/**
	 * 选课
	 * 
	 * @param studentNum
	 *            学生学号
	 * @param courseNums
	 *            要选择的课程号码
	 * @return 选课成功则返回true，否则返回false
	 */
	public boolean selectCourses(String studentNum, String[] courseNums);

	/**
	 * 退课
	 * 
	 * @param studentNum
	 *            学生学号
	 * @param courseNums
	 *            要退选的课程号码
	 * @return 退课成功则返回true，否则返回false
	 */
	public boolean quitCourses(String studentNum, String[] courseNums);

	/**
	 * 显示个人信息
	 * 
	 * @param studentNum
	 *            要显示的学生的学号
	 * @return
	 */
	public Student showInfo(String studentNum);

	/**
	 * 修改密码
	 * 
	 * @param studentNum
	 *            要修改的学生的学号
	 * @param newPassword
	 *            新密码
	 * @return 修改成功返回true，否则返回false
	 */
	public boolean changePassword(String studentNum, String newPassword);

	/**
	 * 查看全部的成绩
	 * 
	 * @param studentNum
	 *            要查询的学生学号
	 * @return 查询到则返回课程成绩的集合，否则返回null
	 */
	public List<CourseSelection> showGrade(String studentNum,
			int currentPageIndex, int count);

	/**
	 * 查看某学年学期的成绩
	 * 
	 * @param studentNum
	 *            要查询的学生学号
	 * @param schoolYear
	 *            要查询的学年
	 * @param semester
	 *            要查询的学期
	 * @return 查询到则返回课程成绩的集合，否则返回null
	 */
	public List<CourseSelection> showGrade(String studentNum,
			String schoolYear, String semester, int currentPageIndex, int count);

	/**
	 * 查询当前的选课状态
	 * 
	 * @return
	 */
	public boolean showStatus();

	/**
	 * 查询当前的学期
	 * 
	 * @return
	 */
	public String showSemester();

	/**
	 * 查询当前的学年
	 * 
	 * @return
	 */
	public String showSchoolYear();

	/**
	 * 根据当前页面索引查询所有课程
	 * 
	 * @param currentPageIndex
	 *            当前页面的索引
	 * @param count
	 *            页面要显示的数据数量
	 * @param schoolYear
	 *            当前学年
	 * @param semester
	 *            当前学期
	 * @return 返回此页数据的一个集合
	 */
	public List<Course> listPageCourses(int currentPageIndex, int count,
			String schoolYear, String semester);

	/**
	 * 获取课程表中数据的总数
	 * 
	 * @param schoolYear
	 *            学期
	 * @param semester
	 *            学年
	 * @return
	 */
	public int showTotalDataCount(String schoolYear, String semester);

	/**
	 * 获取课程选择表中某学年学期的选修课程总数
	 * 
	 * @param studentNum
	 *            学生学号
	 * @param schoolYear
	 *            学期
	 * @param semester
	 *            学年
	 * @return
	 */
	public int showCourseCount(String studentNum, String schoolYear,
			String semester);

	/**
	 * 获取课程选择表中的选修课程总数
	 * 
	 * @param studentNum
	 *            学生学号
	 * @return
	 */
	public int showCourseCount(String studentNum);

	/**
	 * 根据当前页面索引查询已选课程
	 * 
	 * @param studentNum
	 *            学生学号
	 * @param currentPageIndex
	 *            当前页面的索引
	 * @param count
	 *            页面要显示的数据数量
	 * @return 返回此页数据的一个集合
	 */
	public List<Course> listPageSelectedCourses(String studentNum,
			int currentPageIndex, int count);

	/**
	 * 查询个人课表
	 * 
	 * @param studentNum
	 *            学生学号
	 * @param schoolYear
	 *            学年
	 * @param semester
	 *            学期
	 * @return
	 */
	public List<Course> showTimetable(String studentNum, String schoolYear,
			String semester);
}
