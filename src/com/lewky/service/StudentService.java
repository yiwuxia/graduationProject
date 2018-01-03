package com.lewky.service;

import java.util.List;

import com.lewky.bean.Course;
import com.lewky.bean.CourseSelection;
import com.lewky.bean.Student;
import com.lewky.web.formbean.Page;

public interface StudentService {

	/**
	 * ͨ��ѧ�ź�������е�½
	 * 
	 * @param studentNum
	 *            ѧ��
	 * @param password
	 *            ����
	 * @return ��½�ɹ����ظ�ѧ�������򷵻�null
	 */
	public Student login(String studentNum, String password);

	/**
	 * �г����пγ���Ϣ
	 * 
	 * @return ���ҵ��򷵻����пγ̵ļ��ϣ����򷵻�null
	 */
	public List<Course> listAllCourses();

	/**
	 * �г���ѡ�Ŀγ���Ϣ
	 * 
	 * @param studentNum
	 *            ѧ��ѧ��
	 * @return ������ѡ�Ŀγ���Ϣ
	 */
	public List<Course> listSelectedCourses(String studentNum);

	/**
	 * ѡ��
	 * 
	 * @param studentNum
	 *            ѧ��ѧ��
	 * @param courseNums
	 *            Ҫѡ��Ŀγ̺���
	 * @return ѡ�γɹ��򷵻�true�����򷵻�false
	 */
	public boolean selectCourses(String studentNum, String[] courseNums);

	/**
	 * �˿�
	 * 
	 * @param studentNum
	 *            ѧ��ѧ��
	 * @param courseNums
	 *            Ҫ��ѡ�Ŀγ̺���
	 * @return �˿γɹ��򷵻�true�����򷵻�false
	 */
	public boolean quitCourses(String studentNum, String[] courseNums);

	/**
	 * ��ʾ������Ϣ
	 * 
	 * @param studentNum
	 *            Ҫ��ʾ��ѧ����ѧ��
	 * @return
	 */
	public Student showInfo(String studentNum);

	/**
	 * �޸�����
	 * 
	 * @param studentNum
	 *            Ҫ�޸ĵ�ѧ����ѧ��
	 * @param newPassword
	 *            ������
	 * @return �޸ĳɹ�����true�����򷵻�false
	 */
	public boolean changePassword(String studentNum, String newPassword);

	/**
	 * �鿴ȫ���ĳɼ�
	 * 
	 * @param studentNum
	 *            Ҫ��ѯ��ѧ��ѧ��
	 * @return ��ѯ���򷵻ؿγ̳ɼ���ҳ�棬���򷵻�null
	 */
	public Page showGrade(String studentNum, int currentPageIndex, int count);

	/**
	 * �鿴ĳѧ��ѧ�ڵĳɼ�
	 * 
	 * @param studentNum
	 *            Ҫ��ѯ��ѧ��ѧ��
	 * @param schoolYear
	 *            Ҫ��ѯ��ѧ��
	 * @param semester
	 *            Ҫ��ѯ��ѧ��
	 * @return ��ѯ���򷵻ؿγ̳ɼ���ҳ�棬���򷵻�null
	 */
	public Page showGrade(String studentNum, String schoolYear,
			String semester, int currentPageIndex, int count);

	/**
	 * ��ѯ��ǰ��ѡ��״̬
	 * 
	 * @return
	 */
	public boolean showStatus();

	/**
	 * ��ѯ��ǰ��ѧ��
	 * 
	 * @return
	 */
	public String showSemester();

	/**
	 * ��ѯ��ǰ��ѧ��
	 * 
	 * @return
	 */
	public String showSchoolYear();

	/**
	 * ���ݵ�ǰҳ��������ѯ���пγ�
	 * 
	 * @param currentPageIndex
	 *            ��ǰҳ�������
	 * @param count
	 *            ҳ��Ҫ��ʾ����������
	 * @param schoolYear
	 *            ��ǰѧ��
	 * @param semester
	 *            ��ǰѧ��
	 * @return ���ش�ҳ�����
	 */
	public Page listPageCourses(int currentPageIndex, int count,
			String schoolYear, String semester);

	/**
	 * ��ȡ�������ݵ�����
	 * 
	 * @param schoolYear
	 *            ��ǰѧ��
	 * @param semester
	 *            ��ǰѧ��
	 * @return
	 */
	public int showTotalDataCount(String schoolYear, String semester);

	/**
	 * ���ݵ�ǰҳ��������ѯ��ѡ�γ�
	 * 
	 * @param studentNum
	 *            ѧ��ѧ��
	 * @param currentPageIndex
	 *            ��ǰҳ�������
	 * @param count
	 *            ҳ��Ҫ��ʾ����������
	 * @return ���ش�ҳ�����
	 */
	public Page listPageSelectedCourses(String studentNum,
			int currentPageIndex, int count);

	/**
	 * ��ѯ�γ̱���Ͽ��ܴ�
	 * 
	 * @param studentNum
	 *            ѧ��ѧ��
	 * @param schoolYear
	 *            ѧ��
	 * @param semester
	 *            ѧ��
	 * @return
	 */
	public List<List<String>> showCourseWeek(String studentNum, String schoolYear,
			String semester);
	
	/**
	 * ��ѯ�γ̱���Ͽ��մ�
	 * 
	 * @param studentNum
	 *            ѧ��ѧ��
	 * @param schoolYear
	 *            ѧ��
	 * @param semester
	 *            ѧ��
	 * @return
	 */
	public List<List<String>> showCourseWeekday(String studentNum, String schoolYear,
			String semester);
	
	/**
	 * ��ѯ�γ̱���Ͽνڴ�
	 * 
	 * @param studentNum
	 *            ѧ��ѧ��
	 * @param schoolYear
	 *            ѧ��
	 * @param semester
	 *            ѧ��
	 * @return
	 */
	public List<List<String>> showCourseTime(String studentNum, String schoolYear,
			String semester);
	
	/**
	 * ��ѯ�γ̱���Ͽεص�
	 * 
	 * @param studentNum
	 *            ѧ��ѧ��
	 * @param schoolYear
	 *            ѧ��
	 * @param semester
	 *            ѧ��
	 * @return
	 */
	public List<List<String>> showCoursePlace(String studentNum, String schoolYear,
			String semester);

	/**
	 * ��ѯ���˿α�
	 * 
	 * @param studentNum
	 *            ѧ��ѧ��
	 * @param schoolYear
	 *            ѧ��
	 * @param semester
	 *            ѧ��
	 * @return
	 */
	public List<Course> showTimetable(String studentNum, String schoolYear,
			String semester);
}
