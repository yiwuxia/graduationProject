package com.lewky.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.lewky.bean.Course;
import com.lewky.bean.CourseSelection;
import com.lewky.bean.Student;
import com.lewky.dao.StudentDao;
import com.lewky.dao.impl.StudentDaoImpl;
import com.lewky.service.StudentService;
import com.lewky.web.formbean.Page;

public class StudentServiceImpl implements StudentService {

	StudentDao dao = new StudentDaoImpl();

	@Override
	public Student login(String studentNum, String password) {

		return dao.login(studentNum, password);
	}

	@Override
	public List<Course> listAllCourses() {

		return dao.listAllCourses();
	}

	@Override
	public List<Course> listSelectedCourses(String studentNum) {

		return dao.listSelectedCourses(studentNum);
	}

	@Override
	public boolean selectCourses(String studentNum, String[] courseNums) {

		return dao.selectCourses(studentNum, courseNums);
	}

	@Override
	public boolean quitCourses(String studentNum, String[] courseNums) {

		return dao.quitCourses(studentNum, courseNums);
	}

	@Override
	public Student showInfo(String studentNum) {

		return dao.showInfo(studentNum);
	}

	@Override
	public boolean changePassword(String studentNum, String newPassword) {

		return dao.changePassword(studentNum, newPassword);
	}

	@Override
	public Page showGrade(String studentNum, int currentPageIndex, int count) {
		
		//��ѯ���еļ�¼��
		int totalDataCount = dao.showCourseCount(studentNum);
		//����Page����
		Page page = new Page(totalDataCount, count);
		//���õ�ǰҳ������
		page.setCurrentPageIndex(currentPageIndex);
		//�趨ҳ��Ҫ��ʾ�����ݵļ���
		page.setCourseSelectionsList(dao.showGrade(studentNum, currentPageIndex, count));
		
		return page;
	}
	
	@Override
	public Page showGrade(String studentNum, String schoolYear,
			String semester, int currentPageIndex, int count) {
		
		//��ѯ���еļ�¼��
		int totalDataCount = dao.showCourseCount(studentNum, schoolYear, semester);
		//����Page����
		Page page = new Page(totalDataCount, count);
		//���õ�ǰҳ������
		page.setCurrentPageIndex(currentPageIndex);
		//�趨ҳ��Ҫ��ʾ�����ݵļ���
		page.setCourseSelectionsList(dao.showGrade(studentNum, schoolYear, semester, currentPageIndex, count));
		
		return page;
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
	public Page listPageCourses(int currentPageIndex, int count, String schoolYear, String semester) {
		
		//��ѯ���еļ�¼��
		int totalDataCount = showTotalDataCount(schoolYear, semester);
		//����Page����
		Page page = new Page(totalDataCount, count);
		//���õ�ǰҳ������
		page.setCurrentPageIndex(currentPageIndex);
		//�趨ҳ��Ҫ��ʾ�����ݵļ���
		page.setCoursesList(dao.listPageCourses(currentPageIndex, count, schoolYear, semester));
		
		return page;
	}
	
	@Override
	public int showTotalDataCount(String schoolYear, String semester) {
	
		return dao.showTotalDataCount(schoolYear, semester);
	}
	
	@Override
	public Page listPageSelectedCourses(String studentNum,
			int currentPageIndex, int count) {
		
		//��ѯ��ǰѧ���ѧ��
		String schoolYear = dao.showSchoolYear();
		String semester = dao.showSemester();
		//��ѯ���еļ�¼��
		int totalDataCount = showTotalDataCount(schoolYear, semester);
		//����Page����
		Page page = new Page(totalDataCount, count);
		//���õ�ǰҳ������
		page.setCurrentPageIndex(currentPageIndex);
		//�趨ҳ��Ҫ��ʾ�����ݵļ���
		page.setCoursesList(dao.listPageSelectedCourses(studentNum, currentPageIndex, count));
		
		return page;
	}

	@Override
	public List<List<String>> showCourseWeek(String studentNum,
			String schoolYear, String semester) {

		List<Course> courses = showTimetable(studentNum, schoolYear, semester);
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
	public List<List<String>> showCourseWeekday(String studentNum,
			String schoolYear, String semester) {

		List<Course> courses = showTimetable(studentNum, schoolYear, semester);
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
	public List<List<String>> showCourseTime(String studentNum,
			String schoolYear, String semester) {

		List<Course> courses = showTimetable(studentNum, schoolYear, semester);
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
	public List<List<String>> showCoursePlace(String studentNum,
			String schoolYear, String semester) {

		List<Course> courses = showTimetable(studentNum, schoolYear, semester);
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

	@Override
	public List<Course> showTimetable(String studentNum, String schoolYear,
			String semester) {

		return dao.showTimetable(studentNum, schoolYear, semester);
	}
}
