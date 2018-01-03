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
	 * ͨ������Ա���ź�������е�¼
	 * 
	 * @param managerNum
	 *            ����Ա����
	 * @param password
	 *            ����
	 * @return ��¼�ɹ����ظù���Ա�����򷵻�null
	 */
	public Manager login(String managerNum, String password);

	/**
	 * �г���ǰҳ��������ѧ��
	 * @param currentPageIndex	��ǰҳ�������
	 * @param count	ÿҳҪ��ʾ����������
	 * @param deptName	Ҫ��ѯ��ѧ����רҵ����
	 * @return	��ѯѧ���򷵻ص�ǰҳ��ѧ����ҳ�棬���򷵻�null
	 */
	public Page listPageStudents(int currentPageIndex, int count,
			String majorName);

	/**
	 * �г���ǰҳ�������Ľ�ʦ
	 * @param currentPageIndex	��ǰҳ�������
	 * @param count	ÿҳҪ��ʾ����������
	 * @param deptName	Ҫ��ѯ�Ľ�ʦ��ϵ����
	 * @return	��ѯ����ʦ�򷵻ص�ǰҳ���ʦ��ҳ�棬���򷵻�null
	 */
	public Page listPageTeachers(int currentPageIndex, int count,
			String deptName);

	/**
	 * �г����пγ�
	 * 
	 * @return
	 */
	public List<Course> listCourses();

	/**
	 * ���һ��ѧ��
	 * 
	 * @param student
	 *            Ҫ��ӵ�ѧ��
	 * @return
	 */
	public boolean addStudent(Student student);

	/**
	 * ���һ����ʦ
	 * 
	 * @param teacher
	 *            Ҫ��ӵĽ�ʦ
	 * @return
	 */
	public boolean addTeacher(Teacher teacher);

	/**
	 * ��˿γ�
	 * 
	 * @param courseNums
	 *            Ҫ��˵Ŀγ̺���
	 * @param op
	 *            Ҫ���еĲ���
	 * @return
	 */
	public boolean examineCourses(String[] courseNums, String op);

	/**
	 * ɾ��ѧ��
	 * 
	 * @param studentNums
	 *            Ҫɾ����ѧ��ѧ��
	 * @return
	 */
	public boolean deleteStudents(String[] studentNums);

	/**
	 * ɾ����ʦ
	 * 
	 * @param teacherNums
	 *            Ҫɾ���Ľ�ʦ����
	 * @return
	 */
	public boolean deleteTeachers(String[] teacherNums);

	/**
	 * ɾ���γ�
	 * 
	 * @param courseNums
	 *            Ҫɾ���Ŀγ̺���
	 * @return
	 */
	public boolean deleteCourses(String[] courseNums);

	/**
	 * ͨ���γ̺����ҵ��γ�
	 * 
	 * @param courseNum
	 *            Ҫ�ҵĿγ̵ĺ���
	 * @return �ҵ��򷵻ظÿγ̣����򷵻�null
	 */
	public Course findCourseByCourseNum(String courseNum);
	
	/**
	 * ͨ��ѧ���ҵ�ѧ��
	 * 
	 * @param courseNum
	 *            Ҫ�ҵ�ѧ����ѧ��
	 * @return �ҵ��򷵻ظ�ѧ�������򷵻�null
	 */
	public Student findStudentByStudentNum(String studentNum);
	
	/**
	 * ͨ����ʦ�����ҵ���ʦ
	 * 
	 * @param courseNum
	 *            Ҫ�ҵĽ�ʦ�Ĺ���
	 * @return �ҵ��򷵻ظý�ʦ�����򷵻�null
	 */
	public Teacher findTeacherByTeacherNum(String teacherNum);
	
	/**
	 * �޸Ŀγ�
	 * 
	 * @param courseNum
	 *            Ҫ�޸ĵĿγ�
	 * @return
	 */
	public boolean updateCourse(Course course);
	
	/**
	 * �޸�ѧ��
	 * 
	 * @param course
	 *            Ҫ�޸ĵ�ѧ��
	 * @return
	 */
	public boolean updateStudent(Student student);
	
	/**
	 * �޸Ľ�ʦ
	 * 
	 * @param course
	 *            Ҫ�޸ĵĽ�ʦ
	 * @return
	 */
	public boolean updateTeacher(Teacher teacher);
	
	/**
	 * �г�������Ҫ��˵Ŀγ�
	 * 
	 * @return
	 */
	public List<Course> listExaminedCourses();

	/**
	 * �޸�����
	 * 
	 * @param managerNum
	 *            Ҫ�޸ĵĹ���Ա�ĺ���
	 * @param newPassword
	 *            ������
	 * @return �޸ĳɹ�����true�����򷵻�false
	 */
	public boolean changePassword(String managerNum, String newPassword);

	/**
	 * ��ѯ��ǰѡ��״̬
	 * 
	 * @return
	 */
	public boolean showStatus();

	/**
	 * ����ѡ��״̬��ѧ��ѧ��
	 * 
	 * @param schoolYear
	 *            ѧ��
	 * @param semester
	 *            ѧ��
	 * @param status
	 *            ѡ��״̬
	 * @return
	 */
	public Boolean permitCourseSelection(String schoolYear, String semester,
			String status);
	
	/**
	 * ��ѯ��ǰ��ѧ��
	 * @return
	 */
	public String showSemester();

	/**
	 * ��ѯ��ǰ��ѧ��
	 * @return
	 */
	public String showSchoolYear();
	
	/**
	 * ��ѯѧԺ��Ϣ
	 * @return
	 */
	public List<Dept> showDept();
	
	/**
	 * ��ѯרҵ��Ϣ
	 * @return
	 */
	public List<Major> showMajor();
	
	/**
	 * ��ѯ������Ϣ
	 * @return
	 */
	public Manager showInfo(String managerNum);
	
	/**
	 * ���ݵ�ǰҳ��������ѯ��ҳ��Ҫ��ʾ������
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
	 * ��ȡ�µĽ�ʦ����
	 * @return
	 */
	public String showNewTeacherNum();
	
	/**
	 * ��ȡ�µ�ѧ��ѧ��
	 * 
	 * @return
	 */
	public String showNewStudentNum();
	
	/**
	 * �г���ǰҳ����Ҫ��˵Ŀγ�
	 * 
	 * @param currentPageIndex
	 *            ��ǰҳ�������
	 * @param count
	 *            ҳ��Ҫ��ʾ����������
	 * @return	���ش�ҳ�����
	 */
	public Page listPageExaminedCourses(int currentPageIndex, int count);

}
