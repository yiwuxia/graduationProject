package com.lewky.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.lewky.bean.Course;
import com.lewky.bean.CourseSelection;
import com.lewky.bean.Teacher;
import com.lewky.dao.TeacherDao;
import com.lewky.dao.impl.TeacherDaoImpl;
import com.lewky.service.TeacherService;
import com.lewky.web.formbean.Page;

public class TeacherServiceImpl implements TeacherService {

	TeacherDao dao = new TeacherDaoImpl();
	
	@Override
	public Teacher login(String teacherNum, String password) {

		return dao.login(teacherNum, password);
	}

	@Override
	public boolean addCourse(Course course) {

		return dao.addCourse(course);
	}

	@Override
	public List<Course> listCourses(String teacherNum, String courseNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Teacher showInfo(String teacherNum) {

		return dao.showInfo(teacherNum);
	}

	@Override
	public Page showCourse(String courseNum, int currentPageIndex,
			int count) {
		
		//查询表中的记录数
		int totalDataCount = dao.showCourseSelectionCount(courseNum);
		//创建Page对象
		Page page = new Page(totalDataCount, count);
		//设置当前页面索引
		page.setCurrentPageIndex(currentPageIndex);
		//设定页面要显示的数据的集合
		page.setCourseSelectionsList(dao.showCourse(courseNum, currentPageIndex, count));
		
		return page;
	}
	
	@Override
	public boolean changePassword(String teacherNum, String newPassword) {

		return dao.changePassword(teacherNum, newPassword);
	}

	@Override
	public boolean inputGrade(CourseSelection courseSelection) {

		return dao.inputGrade(courseSelection);
	}
	@Override
	public boolean showStatus() {

		return dao.showStatus();
	}
	
	@Override
	public String showSemester() {

		return dao.showSemester();
	}
	
	@Override
	public String showSchoolYear() {

		return dao.showSchoolYear();
	}
	
	@Override
	public Page listPageCourses(String teacherNum, int currentPageIndex, int count, String schoolYear, String semester) {
		
		//查询表中的记录数
		int totalDataCount = showTotalDataCount(schoolYear, semester, teacherNum);
		//创建Page对象
		Page page = new Page(totalDataCount, count);
		//设置当前页面索引
		page.setCurrentPageIndex(currentPageIndex);
		//设定页面要显示的数据的集合
		page.setCoursesList(dao.listPageCourses(teacherNum, currentPageIndex, count, schoolYear, semester));
		
		return page;
	}
	
	@Override
	public int showTotalDataCount(String schoolYear, String semester, String teacherNum) {
	
		return dao.showTotalDataCount(schoolYear, semester, teacherNum);
	}
	
	@Override
	public CourseSelection showGrade(String courseNum, String studentNum) {

		return dao.showGrade(courseNum, studentNum);
	}

	@Override
	public List<Course> showTimetable(String teacherNum, String schoolYear,
			String semester) {

		return dao.showTimetable(teacherNum, schoolYear, semester);
	}

	@Override
	public List<List<String>> showCourseWeek(String teacherNum,
			String schoolYear, String semester) {

		List<Course> courses = showTimetable(teacherNum, schoolYear, semester);
		List<List<String>> list = new ArrayList<List<String>>();
		//循环取出课程的week
		for (int i = 0; i < courses.size(); i++) {
			List<String> subList = new ArrayList<String>();
			String week = courses.get(i).getWeek();
			if (!"".equals(week)) {
				//如果week不为空字符串
				//字符串后边多了个"}"，先减去
				week = week.substring(0, week.length()-1);
				// 拆分字符串
				String[] weeks = week.split("}");
				//将每一个week处理后分别放入到subList中
				for (int j = 0; j < weeks.length; j++) {
					String subWeek = weeks[j];
					subWeek = subWeek.substring(1);
					subList.add(subWeek);
				}
				list.add(subList);
			}
		}
		return list;
	}

	@Override
	public List<List<String>> showCourseWeekday(String teacherNum,
			String schoolYear, String semester) {

		List<Course> courses = showTimetable(teacherNum, schoolYear, semester);
		List<List<String>> list = new ArrayList<List<String>>();
		//循环取出课程的weekday
		for (int i = 0; i < courses.size(); i++) {
			List<String> subList = new ArrayList<String>();
			String weekday = courses.get(i).getWeekday();
			if (!"".equals(weekday)) {
				//如果weekday不为空字符串
				// 拆分字符串
				String[] weekdays = weekday.split(",");
				//将每一个weekday分别放入到list中
				for (int j = 0; j < weekdays.length; j++) {
					subList.add(weekdays[j]);
				}
				list.add(subList);
			}
		}
		return list;
	}

	@Override
	public List<List<String>> showCourseTime(String teacherNum,
			String schoolYear, String semester) {

		List<Course> courses = showTimetable(teacherNum, schoolYear, semester);
		List<List<String>> list = new ArrayList<List<String>>();
		//循环取出课程的time
		for (int i = 0; i < courses.size(); i++) {
			List<String> subList = new ArrayList<String>();
			String time = courses.get(i).getTime();
			if (!"".equals(time)) {
				//如果time不为空字符串
				//字符串后边多了个"}"，先减去
				time = time.substring(0, time.length()-1);
				// 拆分字符串
				String[] times = time.split("}");
				//将每一个time处理后分别放入到subList中
				for (int j = 0; j < times.length; j++) {
					String subTime = times[j];
					subTime = subTime.substring(1);
					subList.add(subTime);
				}
				list.add(subList);
			}
		}
		return list;
	}

	@Override
	public List<List<String>> showCoursePlace(String teacherNum,
			String schoolYear, String semester) {

		List<Course> courses = showTimetable(teacherNum, schoolYear, semester);
		List<List<String>> list = new ArrayList<List<String>>();
		//循环取出课程的place
		for (int i = 0; i < courses.size(); i++) {
			List<String> subList = new ArrayList<String>();
			String place = courses.get(i).getPlace();
			if (!"".equals(place)) {
				//如果place不为空字符串
				//字符串后边多了个"}"，先减去
				place = place.substring(0, place.length()-1);
				// 拆分字符串
				String[] places = place.split("}");
				//将每一个place处理后分别放入到subList中
				for (int j = 0; j < places.length; j++) {
					String subPlace = places[j];
					subPlace = subPlace.substring(1);
					subList.add(subPlace);
				}
				list.add(subList);
			}
		}
		return list;
	}
	
}
