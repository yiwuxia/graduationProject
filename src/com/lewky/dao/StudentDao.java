package com.lewky.dao;

import java.util.List;

import com.lewky.bean.Course;
import com.lewky.bean.CourseSelection;
import com.lewky.bean.Student;

public interface StudentDao {

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
	 * @return ��ѯ���򷵻ؿγ̳ɼ��ļ��ϣ����򷵻�null
	 */
	public List<CourseSelection> showGrade(String studentNum,
			int currentPageIndex, int count);

	/**
	 * �鿴ĳѧ��ѧ�ڵĳɼ�
	 * 
	 * @param studentNum
	 *            Ҫ��ѯ��ѧ��ѧ��
	 * @param schoolYear
	 *            Ҫ��ѯ��ѧ��
	 * @param semester
	 *            Ҫ��ѯ��ѧ��
	 * @return ��ѯ���򷵻ؿγ̳ɼ��ļ��ϣ����򷵻�null
	 */
	public List<CourseSelection> showGrade(String studentNum,
			String schoolYear, String semester, int currentPageIndex, int count);

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
	 * @return ���ش�ҳ���ݵ�һ������
	 */
	public List<Course> listPageCourses(int currentPageIndex, int count,
			String schoolYear, String semester);

	/**
	 * ��ȡ�γ̱������ݵ�����
	 * 
	 * @param schoolYear
	 *            ѧ��
	 * @param semester
	 *            ѧ��
	 * @return
	 */
	public int showTotalDataCount(String schoolYear, String semester);

	/**
	 * ��ȡ�γ�ѡ�����ĳѧ��ѧ�ڵ�ѡ�޿γ�����
	 * 
	 * @param studentNum
	 *            ѧ��ѧ��
	 * @param schoolYear
	 *            ѧ��
	 * @param semester
	 *            ѧ��
	 * @return
	 */
	public int showCourseCount(String studentNum, String schoolYear,
			String semester);

	/**
	 * ��ȡ�γ�ѡ����е�ѡ�޿γ�����
	 * 
	 * @param studentNum
	 *            ѧ��ѧ��
	 * @return
	 */
	public int showCourseCount(String studentNum);

	/**
	 * ���ݵ�ǰҳ��������ѯ��ѡ�γ�
	 * 
	 * @param studentNum
	 *            ѧ��ѧ��
	 * @param currentPageIndex
	 *            ��ǰҳ�������
	 * @param count
	 *            ҳ��Ҫ��ʾ����������
	 * @return ���ش�ҳ���ݵ�һ������
	 */
	public List<Course> listPageSelectedCourses(String studentNum,
			int currentPageIndex, int count);

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
