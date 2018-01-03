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
		
		//��ѯ���еļ�¼��
		int totalDataCount = dao.showCourseSelectionCount(courseNum);
		//����Page����
		Page page = new Page(totalDataCount, count);
		//���õ�ǰҳ������
		page.setCurrentPageIndex(currentPageIndex);
		//�趨ҳ��Ҫ��ʾ�����ݵļ���
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
		
		//��ѯ���еļ�¼��
		int totalDataCount = showTotalDataCount(schoolYear, semester, teacherNum);
		//����Page����
		Page page = new Page(totalDataCount, count);
		//���õ�ǰҳ������
		page.setCurrentPageIndex(currentPageIndex);
		//�趨ҳ��Ҫ��ʾ�����ݵļ���
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
		//ѭ��ȡ���γ̵�week
		for (int i = 0; i < courses.size(); i++) {
			List<String> subList = new ArrayList<String>();
			String week = courses.get(i).getWeek();
			if (!"".equals(week)) {
				//���week��Ϊ���ַ���
				//�ַ�����߶��˸�"}"���ȼ�ȥ
				week = week.substring(0, week.length()-1);
				// ����ַ���
				String[] weeks = week.split("}");
				//��ÿһ��week�����ֱ���뵽subList��
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
		//ѭ��ȡ���γ̵�weekday
		for (int i = 0; i < courses.size(); i++) {
			List<String> subList = new ArrayList<String>();
			String weekday = courses.get(i).getWeekday();
			if (!"".equals(weekday)) {
				//���weekday��Ϊ���ַ���
				// ����ַ���
				String[] weekdays = weekday.split(",");
				//��ÿһ��weekday�ֱ���뵽list��
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
		//ѭ��ȡ���γ̵�time
		for (int i = 0; i < courses.size(); i++) {
			List<String> subList = new ArrayList<String>();
			String time = courses.get(i).getTime();
			if (!"".equals(time)) {
				//���time��Ϊ���ַ���
				//�ַ�����߶��˸�"}"���ȼ�ȥ
				time = time.substring(0, time.length()-1);
				// ����ַ���
				String[] times = time.split("}");
				//��ÿһ��time�����ֱ���뵽subList��
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
		//ѭ��ȡ���γ̵�place
		for (int i = 0; i < courses.size(); i++) {
			List<String> subList = new ArrayList<String>();
			String place = courses.get(i).getPlace();
			if (!"".equals(place)) {
				//���place��Ϊ���ַ���
				//�ַ�����߶��˸�"}"���ȼ�ȥ
				place = place.substring(0, place.length()-1);
				// ����ַ���
				String[] places = place.split("}");
				//��ÿһ��place�����ֱ���뵽subList��
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
