package com.lewky.dao;

import java.util.List;

import com.lewky.bean.Course;
import com.lewky.bean.CourseSelection;
import com.lewky.bean.Teacher;

public interface TeacherDao {

	/**
	 * ͨ����ʦ���ź�������е�½
	 * 
	 * @param teacherNum
	 *            ��ʦ����
	 * @param password
	 *            ����
	 * @return ��½�ɹ����ظý�ʦ�����򷵻�null
	 */
	public Teacher login(String teacherNum, String password);

	/**
	 * �����γ���Ϣ
	 * 
	 * @param course
	 *            �γ�����
	 * @return �����ɹ�����true�����򷵻�false
	 */
	public boolean addCourse(Course course);

	/**
	 * ��ʾ������Ϣ
	 * 
	 * @param studentNum
	 *            Ҫ��ʾ�Ľ�ʦ�Ĺ���
	 * @return
	 */
	public Teacher showInfo(String teacherNum);
	
	/**
	 * ��ѯĳһҳ�Ŀγ���ϸ��Ϣ
	 * @param courseNum	Ҫ��ѯ�Ŀγ̺���
	 * @param currentPageIndex	Ҫ��ѯ��ҳ������
	 * @param count	ÿһҳҪ��ʾ����������
	 * @return	���ص�ǰҳ�������Ŀγ���ϸ��Ϣ
	 */
	public List<CourseSelection> showCourse(String courseNum, int currentPageIndex, int count);

	/**
	 * �޸�����
	 * 
	 * @param teacherNum
	 *            Ҫ�޸ĵĽ�ʦ�Ĺ���
	 * @param newPassword
	 *            ������
	 * @return �޸ĳɹ�����true�����򷵻�false
	 */
	public boolean changePassword(String teacherNum, String newPassword);

	/**
	 * ¼��ɼ�
	 * 
	 * @param courseSelection
	 *            Ҫ¼��Ķ���
	 * @return ¼��ɹ�����true�����򷵻�false
	 */
	public boolean inputGrade(CourseSelection courseSelection);

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
	 * @param teacherNum
	 *            Ҫ��ѯ�Ľ�ʦ�Ĺ���
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
	public List<Course> listPageCourses(String teacherNum,
			int currentPageIndex, int count, String schoolYear, String semester);

	/**
	 * ��ȡ�������ݵ�����
	 * 
	 * @param schoolYear
	 *            ��ǰѧ��
	 * @param semester
	 *            ��ǰѧ��
	 * @return
	 */
	public int showTotalDataCount(String schoolYear, String semester, String teacherNum);
	
	/**
	 * ��ȡ�γ�ѡ����е�ĳһ�ſγ����ݵ�����
	 * @param courseNum	Ҫ��ѯ�Ŀγ̺���
	 * @return
	 */
	public int showCourseSelectionCount(String courseNum);
	
	/**
	 * ��ѯѧ��ĳ�ſγ̵ĳɼ�
	 * @param courseNum	Ҫ��ѯ��ѧ��ѧ��
	 * @param studentNum	Ҫ��ѯ�Ŀγ̺���
	 * @return
	 */
	public CourseSelection showGrade(String courseNum, String studentNum);

	/**
	 * ��ѯ���˿α�
	 * 
	 * @param teacherNum
	 *            ��ʦ����
	 * @param schoolYear
	 *            ѧ��
	 * @param semester
	 *            ѧ��
	 * @return
	 */
	public List<Course> showTimetable(String teacherNum, String schoolYear,
			String semester);
}
