package com.lewky.dao;

import java.util.List;

import com.lewky.bean.Course;
import com.lewky.bean.CourseSelection;
import com.lewky.bean.Teacher;

public interface TeacherDao {

	/**
	 * 通过教师工号和密码进行登陆
	 * 
	 * @param teacherNum
	 *            教师工号
	 * @param password
	 *            密码
	 * @return 登陆成功返回该教师，否则返回null
	 */
	public Teacher login(String teacherNum, String password);

	/**
	 * 发布课程信息
	 * 
	 * @param course
	 *            课程名称
	 * @return 发布成功返回true，否则返回false
	 */
	public boolean addCourse(Course course);

	/**
	 * 显示个人信息
	 * 
	 * @param studentNum
	 *            要显示的教师的工号
	 * @return
	 */
	public Teacher showInfo(String teacherNum);
	
	/**
	 * 查询某一页的课程详细信息
	 * @param courseNum	要查询的课程号码
	 * @param currentPageIndex	要查询的页面索引
	 * @param count	每一页要显示的数据数量
	 * @return	返回当前页面索引的课程详细信息
	 */
	public List<CourseSelection> showCourse(String courseNum, int currentPageIndex, int count);

	/**
	 * 修改密码
	 * 
	 * @param teacherNum
	 *            要修改的教师的工号
	 * @param newPassword
	 *            新密码
	 * @return 修改成功返回true，否则返回false
	 */
	public boolean changePassword(String teacherNum, String newPassword);

	/**
	 * 录入成绩
	 * 
	 * @param courseSelection
	 *            要录入的对象
	 * @return 录入成功返回true，否则返回false
	 */
	public boolean inputGrade(CourseSelection courseSelection);

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
	 * @param teacherNum
	 *            要查询的教师的工号
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
	public List<Course> listPageCourses(String teacherNum,
			int currentPageIndex, int count, String schoolYear, String semester);

	/**
	 * 获取表中数据的总数
	 * 
	 * @param schoolYear
	 *            当前学期
	 * @param semester
	 *            当前学年
	 * @return
	 */
	public int showTotalDataCount(String schoolYear, String semester, String teacherNum);
	
	/**
	 * 获取课程选择表中的某一门课程数据的总数
	 * @param courseNum	要查询的课程号码
	 * @return
	 */
	public int showCourseSelectionCount(String courseNum);
	
	/**
	 * 查询学生某门课程的成绩
	 * @param courseNum	要查询的学生学号
	 * @param studentNum	要查询的课程号码
	 * @return
	 */
	public CourseSelection showGrade(String courseNum, String studentNum);

	/**
	 * 查询个人课表
	 * 
	 * @param teacherNum
	 *            教师工号
	 * @param schoolYear
	 *            学年
	 * @param semester
	 *            学期
	 * @return
	 */
	public List<Course> showTimetable(String teacherNum, String schoolYear,
			String semester);
}
