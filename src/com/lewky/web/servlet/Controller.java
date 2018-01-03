package com.lewky.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.lewky.bean.Course;
import com.lewky.bean.CourseSelection;
import com.lewky.bean.Dept;
import com.lewky.bean.Manager;
import com.lewky.bean.Student;
import com.lewky.bean.Teacher;
import com.lewky.service.ManagerService;
import com.lewky.service.StudentService;
import com.lewky.service.TeacherService;
import com.lewky.service.impl.ManagerServiceImpl;
import com.lewky.service.impl.StudentServiceImpl;
import com.lewky.service.impl.TeacherServiceImpl;
import com.lewky.utils.WebTools;
import com.lewky.utils.WebUtils;
import com.lewky.web.formbean.CourseFormBean;
import com.lewky.web.formbean.CourseSelectionFormBean;
import com.lewky.web.formbean.Page;
import com.lewky.web.formbean.StudentFormBean;
import com.lewky.web.formbean.TeacherFormBean;

/**
 * @author 123 E-mail: lewkyliu@gmail.com
 * @version ����ʱ�䣺2017-4-1 ����9:54:00
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	StudentService ss = new StudentServiceImpl();
	TeacherService ts = new TeacherServiceImpl();
	ManagerService ms = new ManagerServiceImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		// �õ�ҳ�洫�ݵ���Ϣ
		String op = request.getParameter("op");

		// �жϽ�����һ�ֲ���
		if ("login".equals(op)) {
			login(request, response);
		} else if ("quitLogin".equals(op)) {
			quitLogin(request, response);
		} else if ("listCourses".equals(op)) {
			listCourses(request, response);
		} else if ("selectCourse".equals(op)) {
			selectCourse(request, response);
		} else if ("selectCourses".equals(op)) {
			selectCourses(request, response);
		} else if ("listSelectedCourses".equals(op)) {
			listSelectedCourses(request, response);
		} else if ("quit".equals(op)) {
			quit(request, response);
		} else if ("quitCourses".equals(op)) {
			quitCourses(request, response);
		} else if ("deleteCourse".equals(op)) {
			deleteCourse(request, response);
		} else if ("deleteCourses".equals(op)) {
			deleteCourses(request, response);
		} else if ("deleteStudent".equals(op)) {
			deleteStudent(request, response);
		} else if ("deleteStudents".equals(op)) {
			deleteStudents(request, response);
		} else if ("deleteTeacher".equals(op)) {
			deleteTeacher(request, response);
		} else if ("deleteTeachers".equals(op)) {
			deleteTeachers(request, response);
		} else if ("toAddStudent".equals(op)) {
			toAddStudent(request, response);
		} else if ("toAddTeacher".equals(op)) {
			toAddTeacher(request, response);
		} else if ("toAddCourse".equals(op)) {
			toAddCourse(request, response);
		} else if ("addStudent".equals(op)) {
			addStudent(request, response);
		} else if ("addTeacher".equals(op)) {
			addTeacher(request, response);
		} else if ("addCourse".equals(op)) {
			addCourse(request, response);
		} else if ("toUpdateCourse".equals(op)) {
			toUpdateCourse(request, response);
		} else if ("updateCourse".equals(op)) {
			updateCourse(request, response);
		} else if ("toUpdateStudent".equals(op)) {
			toUpdateStudent(request, response);
		} else if ("updateStudent".equals(op)) {
			updateStudent(request, response);
		} else if ("toUpdateTeacher".equals(op)) {
			toUpdateTeacher(request, response);
		} else if ("updateTeacher".equals(op)) {
			updateTeacher(request, response);
		} else if ("toExamineCourses".equals(op)) {
			toExamineCourses(request, response);
		} else if ("examineCourse".equals(op)) {
			examineCourse(request, response);
		} else if ("examineCourses".equals(op)) {
			examineCourses(request, response);
		} else if ("changePassword".equals(op)) {
			changePassword(request, response);
		} else if ("toPermitCourseSelection".equals(op)) {
			toPermitCourseSelection(request, response);
		} else if ("permitCourseSelection".equals(op)) {
			permitCourseSelection(request, response);
		} else if ("showInfo".equals(op)) {
			showInfo(request, response);
		} else if ("toListPageStudents".equals(op)) {
			toListPageStudents(request, response);
		} else if ("toListPageTeachers".equals(op)) {
			toListPageTeachers(request, response);
		} else if ("showCourse".equals(op)) {
			showCourse(request, response);
		} else if ("currentCourses".equals(op)) {
			currentCourses(request, response);
		} else if ("showGrade".equals(op)) {
			showGrade(request, response);
		} else if ("toInputGrade".equals(op)) {
			toInputGrade(request, response);
		} else if ("inputGrade".equals(op)) {
			inputGrade(request, response);
		} else if ("showTimetable".equals(op)) {
			showTimetable(request, response);
		}
	}

	//��ѯ���˿α�
	private void showTimetable(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�session�����Ϣ
		String userType = (String) request.getSession()
				.getAttribute("userType");
		// �õ�ҳ�洫�ݵ���Ϣ
		String schoolYear = request.getParameter("schoolYear");
		String semester = request.getParameter("semester");

		// �ж�ѧ��ѧ���Ƿ�Ϊ��
		if (schoolYear == null || semester == null) {
			// Ĭ��Ϊ��ǰѧ��͵�ǰѧ��
			schoolYear = ms.showSchoolYear();
			semester = ms.showSemester();
		}

		// ��schoolYear,semester����session������
		request.getSession().setAttribute("schoolYear", schoolYear);
		request.getSession().setAttribute("semester", semester);

		// �ж��û�������
		if ("student".equals(userType)) {
			// �û�������ѧ��
			//�õ�ѧ��ѧ��
			Student student = (Student) request.getSession().getAttribute("loginUser");
			String studentNum = student.getStudentNum();
			
			//����service���ҵ���߼�
			List<Course> list = ss.showTimetable(studentNum, schoolYear, semester);
			List<List<String>> week = ss.showCourseWeek(studentNum, schoolYear, semester);
			List<List<String>> time = ss.showCourseTime(studentNum, schoolYear, semester);
			List<List<String>> weekday = ss.showCourseWeekday(studentNum, schoolYear, semester);
			List<List<String>> place = ss.showCoursePlace(studentNum, schoolYear, semester);
			//��list,week,time,weekday,place����session������
			request.getSession().setAttribute("list", list);
			request.getSession().setAttribute("week", week);
			request.getSession().setAttribute("time", time);
			request.getSession().setAttribute("weekday", weekday);
			request.getSession().setAttribute("place", place);
			//��ת����˿α�ҳ��
			response.sendRedirect(request.getContextPath() + "/showTimetable.jsp");
			
		}else if ("teacher".equals(userType)) {
			// �û������ǽ�ʦ
			//�õ���ʦ����
			Teacher teacher = (Teacher) request.getSession().getAttribute("loginUser");
			String teacherNum = teacher.getTeacherNum();
			
			//����service���ҵ���߼�
			List<Course> list = ts.showTimetable(teacherNum, schoolYear, semester);
			List<List<String>> week = ts.showCourseWeek(teacherNum, schoolYear, semester);
			List<List<String>> time = ts.showCourseTime(teacherNum, schoolYear, semester);
			List<List<String>> weekday = ts.showCourseWeekday(teacherNum, schoolYear, semester);
			List<List<String>> place = ts.showCoursePlace(teacherNum, schoolYear, semester);
			//��list,week,time,weekday,place����session������
			request.getSession().setAttribute("list", list);
			request.getSession().setAttribute("week", week);
			request.getSession().setAttribute("time", time);
			request.getSession().setAttribute("weekday", weekday);
			request.getSession().setAttribute("place", place);
			//��ת����˿α�ҳ��
			response.sendRedirect(request.getContextPath() + "/showTimetable.jsp");
		}
	}

	// ¼��ɼ�
	private void inputGrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��װҳ�������
		CourseSelectionFormBean csfb = WebUtils.fillFormBean(
				CourseSelectionFormBean.class, request);

		// ��������
		CourseSelection courseSelection = new CourseSelection();
		try {
			BeanUtils.copyProperties(courseSelection, csfb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ����service�����ҵ���߼�
		boolean flag = ts.inputGrade(courseSelection);
		showGrade(request, response);
	}

	// ��ת��¼��ɼ�ҳ��
	private void toInputGrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ���Ϣ
		String courseNum = request.getParameter("courseNum");
		String studentNum = request.getParameter("studentNum");
		// ����service���ҵ���߼�
		CourseSelection courseSelection = ts.showGrade(courseNum, studentNum);
		// ��courseSelection����session������
		request.getSession().setAttribute("courseSelection", courseSelection);
		// ��ת��¼��ɼ�ҳ��
		response.sendRedirect(request.getContextPath() + "/inputGrade.jsp");
	}

	// ��ʾ��ʦ��ǰѧ�ڵĿγ�
	private void currentCourses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// �õ�ҳ�洫�ݵ���Ϣ
		String currentPageIndex = request.getParameter("currentPageIndex");
		Page page = (Page) request.getSession().getAttribute("page");

		// ��ȡ��ǰѧ��ѧ��
		String schoolYear = ms.showSchoolYear();
		String semester = ms.showSemester();

		if (page != null & currentPageIndex != null) {
			int pageCount = page.getPageCount();
			// �ж�ҳ���Ƿ������ҳ��
			if (Integer.parseInt(currentPageIndex) > pageCount) {
				currentPageIndex = pageCount + "";
			}
		}

		// �жϵ�ǰҳ���Ƿ�Ϊ�ջ�Ϊ��
		if (currentPageIndex == null) {
			// Ĭ��Ϊ��ǰҳ��Ϊ��һҳ
			currentPageIndex = "1";
		} else if ("0".equals(currentPageIndex)) {
			currentPageIndex = "1";
		}

		// ��schoolYear,semester����session������
		request.getSession().setAttribute("schoolYear", schoolYear);
		request.getSession().setAttribute("semester", semester);

		// ��session�л�ȡ��ʦ����
		Teacher teacher = (Teacher) request.getSession().getAttribute(
				"loginUser");
		String teacherNum = teacher.getTeacherNum();

		// ����service�����ҵ���߼�
		page = ts.listPageCourses(teacherNum,
				Integer.parseInt(currentPageIndex), 7, schoolYear, semester);
		boolean status = ts.showStatus();
		// ��status,page���뵽session������
		request.getSession().setAttribute("status", status);
		request.getSession().setAttribute("page", page);
		response.sendRedirect(request.getContextPath() + "/currentCourses.jsp");
	}

	// �˳���¼���ص���¼����
	private void quitLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��session�����е�error�������
		request.getSession().setAttribute("error", "");
		// ��ת����½����
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	// ��ʾ�γ�����
	private void showCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String courseNum = request.getParameter("courseNum");
		String currentPageIndex = request.getParameter("currentPageIndex");
		Page page = (Page) request.getSession().getAttribute("page");

		if (page != null & currentPageIndex != null) {
			int pageCount = page.getPageCount();
			// �ж�ҳ���Ƿ������ҳ��
			if (Integer.parseInt(currentPageIndex) > pageCount) {
				currentPageIndex = pageCount + "";
			}
		}

		// �жϵ�ǰҳ���Ƿ�Ϊ�ջ�Ϊ��
		if (currentPageIndex == null) {
			// Ĭ��Ϊ��ǰҳ��Ϊ��һҳ
			currentPageIndex = "1";
		} else if ("0".equals(currentPageIndex)) {
			currentPageIndex = "1";
		}

		// ����service�����ҵ���߼�
		page = ts.showCourse(courseNum, Integer.parseInt(currentPageIndex), 10);
		// ��page���뵽session������
		request.getSession().setAttribute("page", page);
		response.sendRedirect(request.getContextPath() + "/showCourse.jsp");
	}

	// ��ת��鿴�ɼ�ҳ��
	private void showGrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�session�����Ϣ
		String userType = (String) request.getSession()
				.getAttribute("userType");

		// �õ�ҳ�洫�ݵ�����
		String courseNum = request.getParameter("courseNum");
		String schoolYear = request.getParameter("schoolYear");
		String semester = request.getParameter("semester");
		String currentPageIndex = request.getParameter("currentPageIndex");
		Page page = (Page) request.getSession().getAttribute("page");

		if (page != null & currentPageIndex != null) {
			int pageCount = page.getPageCount();
			// �ж�ҳ���Ƿ������ҳ��
			if (Integer.parseInt(currentPageIndex) > pageCount) {
				currentPageIndex = pageCount + "";
			}
		}

		// �ж�ѧ��ѧ���Ƿ�Ϊ��
		if (schoolYear == null || semester == null) {
			// Ĭ��Ϊ��ǰѧ��͵�ǰѧ��
			schoolYear = ms.showSchoolYear();
			semester = ms.showSemester();
		}
		
		// �жϵ�ǰҳ���Ƿ�Ϊ�ջ�Ϊ��
		if (currentPageIndex == null) {
			// Ĭ��Ϊ��ǰҳ��Ϊ��һҳ
			currentPageIndex = "1";
		} else if ("0".equals(currentPageIndex)) {
			currentPageIndex = "1";
		}

		// �ж��û�������
		if ("teacher".equals(userType)) {
			// �û������ǽ�ʦ
			// ����service�����ҵ���߼�
			page = ts.showCourse(courseNum, Integer.parseInt(currentPageIndex),
					10);

			// ��page���뵽session������
			request.getSession().setAttribute("page", page);
			response.sendRedirect(request.getContextPath() + "/showGrade.jsp");
		} else if ("student".equals(userType)) {
			// �û�������ѧ��
			// ��session�л�ȡѧ��ѧ��
			Student student = (Student) request.getSession().getAttribute(
					"loginUser");
			String studentNum = student.getStudentNum();

			// �ж�ѧ���Ƿ�Ϊall
			if ("all".equals(schoolYear)) {
				// ��ѯ��ѧ��������ѡ�޿γ̳ɼ�
				// ����service�����ҵ���߼�
				page = ss.showGrade(studentNum,
						Integer.parseInt(currentPageIndex), 10);

				// ��page,schoolYear,semester���뵽session������
				request.getSession().setAttribute("page", page);
				request.getSession().setAttribute("schoolYear", "");
				request.getSession().setAttribute("semester", "");
				response.sendRedirect(request.getContextPath()
						+ "/showStudentGrade.jsp");
			} else {
				// ��ѯĳѧ��ѧ�ڵ�ѡ�޿γ̳ɼ�
				// ����service�����ҵ���߼�
				page = ss.showGrade(studentNum, schoolYear, semester,
						Integer.parseInt(currentPageIndex), 10);

				// ��page,schoolYear,semester���뵽session������
				request.getSession().setAttribute("page", page);
				request.getSession().setAttribute("schoolYear", schoolYear);
				request.getSession().setAttribute("semester", semester);
				response.sendRedirect(request.getContextPath()
						+ "/showStudentGrade.jsp");
			}
		}
	}

	// ���½�ʦ
	private void updateTeacher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��װҳ�������
		TeacherFormBean tfb = WebUtils.fillFormBean(TeacherFormBean.class,
				request);
		// �������ݵ�һ��javabean��
		Teacher teacher = new Teacher();
		if (tfb.getFormBirthday() != "") {
			// ���ղ�Ϊ��
			// ����ʱ����date���ͣ���������ע��һ��ʱ��ת����
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			try {
				BeanUtils.copyProperty(teacher, "birthday",
						tfb.getFormBirthday());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// �������Ϊ����teacher���birthday����Ϊnull
			teacher.setBirthday(null);
		}

		// ��������
		try {
			BeanUtils.copyProperties(teacher, tfb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ����service�����ҵ���߼�
		boolean flag = ms.updateTeacher(teacher);
		toUpdateTeacher(request, response);
	}

	// ��ת����½�ʦҳ��
	private void toUpdateTeacher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String teacherNum = request.getParameter("teacherNum");
		// ͨ��teacherNum�ҵ�Ҫ�޸ĵĽ�ʦ
		Teacher teacher = ms.findTeacherByTeacherNum(teacherNum);
		// ��teacher����session�����У�����תҳ��
		request.getSession().setAttribute("teacher", teacher);

		// ��ȡѧԺ��Ϣ������dept����session������
		List<Dept> dept = ms.showDept();
		request.getSession().setAttribute("dept", dept);
		response.sendRedirect(request.getContextPath() + "/updateTeacher.jsp");
	}

	// ����ѧ��
	private void updateStudent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��װҳ�������
		StudentFormBean sfb = WebUtils.fillFormBean(StudentFormBean.class,
				request);
		// �������ݵ�һ��javabean��
		Student student = new Student();
		if (sfb.getFormBirthday() != "") {
			// ���ղ�Ϊ��
			// ����ʱ����date���ͣ���������ע��һ��ʱ��ת����
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			try {
				BeanUtils.copyProperty(student, "birthday",
						sfb.getFormBirthday());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// �������Ϊ����student���birthday����Ϊnull
			student.setBirthday(null);
		}

		// ��������
		try {
			BeanUtils.copyProperties(student, sfb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ����service�����ҵ���߼�
		boolean flag = ms.updateStudent(student);
		toUpdateStudent(request, response);
	}

	// ��ת�����ѧ��ҳ��
	private void toUpdateStudent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String studentNum = request.getParameter("studentNum");
		// ͨ��studentNum�ҵ�Ҫ�޸ĵ�ѧ��
		Student student = ms.findStudentByStudentNum(studentNum);
		// ��student����session�����У�����תҳ��
		request.getSession().setAttribute("student", student);

		// ��ȡѧԺ��Ϣ������dept����session������
		List<Dept> dept = ms.showDept();
		request.getSession().setAttribute("dept", dept);
		response.sendRedirect(request.getContextPath() + "/updateStudent.jsp");
	}

	// ɾ�����ѧ��
	private void deleteStudents(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String ids = request.getParameter("ids");
		// ����ids��߶���һ�����ţ�Ҫȥ��
		ids = ids.substring(0, ids.length() - 1);
		// ����ַ���
		String[] studentNums = ids.split(",");

		// ����service�����ҵ���߼�
		Boolean flag = ms.deleteStudents(studentNums);
		toListPageStudents(request, response);
	}

	// ɾ��һ��ѧ��
	private void deleteStudent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String studentNum = request.getParameter("id");
		// �ŵ�String������
		String[] studentNums = new String[] { studentNum };

		// ����service�����ҵ���߼�
		Boolean flag = ms.deleteStudents(studentNums);
		toListPageStudents(request, response);
	}

	// ɾ�������ʦ
	private void deleteTeachers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String ids = request.getParameter("ids");
		// ����ids��߶���һ�����ţ�Ҫȥ��
		ids = ids.substring(0, ids.length() - 1);
		// ����ַ���
		String[] teacherNums = ids.split(",");

		// ����service�����ҵ���߼�
		Boolean flag = ms.deleteTeachers(teacherNums);
		toListPageTeachers(request, response);
	}

	// ɾ��һ����ʦ
	private void deleteTeacher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String teacherNum = request.getParameter("id");
		// �ŵ�String������
		String[] teacherNums = new String[] { teacherNum };

		// ����service�����ҵ���߼�
		Boolean flag = ms.deleteTeachers(teacherNums);
		toListPageTeachers(request, response);
	}

	// ��ת��ɾ��ѧ��ҳ��
	private void toListPageStudents(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ���Ϣ
		String deptName = request.getParameter("deptName");
		String majorName = request.getParameter("majorName");
		String currentPageIndex = request.getParameter("currentPageIndex");
		Page page = (Page) request.getSession().getAttribute("page");

		// �ж�ҳ��������ֵ
		if (page != null & currentPageIndex != null) {
			int pageCount = page.getPageCount();

			// �ж�ҳ�������Ƿ������ҳ��
			if (Integer.parseInt(currentPageIndex) > pageCount) {
				currentPageIndex = pageCount + "";
			}
		}

		// �ж�ϵ�����Ƿ�Ϊ��
		if (deptName == null) {
			// Ĭ��Ϊ�Զ���ѧԺ
			deptName = "�Զ���ѧԺ";
		} else {
			deptName = new String(deptName.getBytes("iso8859-1"), "UTF-8");
		}

		// �ж�רҵ�����Ƿ�Ϊ��
		if (majorName == null) {
			// Ĭ��Ϊ����������רҵ
			majorName = "����������רҵ";
		} else {
			majorName = new String(majorName.getBytes("iso8859-1"), "UTF-8");
		}

		// �жϵ�ǰҳ���Ƿ�Ϊ�ջ�Ϊ��
		if (currentPageIndex == null) {
			// Ĭ��Ϊ��ǰҳ��Ϊ��һҳ
			currentPageIndex = "1";
		} else if ("0".equals(currentPageIndex)) {
			currentPageIndex = "1";
		}

		// ��deptName,majorName����session������
		request.getSession().setAttribute("deptName", deptName);
		request.getSession().setAttribute("majorName", majorName);

		// ��ȡѧԺ��Ϣ������dept����session������
		List<Dept> dept = ms.showDept();
		request.getSession().setAttribute("dept", dept);

		// ����service�����ҵ���߼�
		page = ms.listPageStudents(Integer.parseInt(currentPageIndex), 7,
				majorName);
		// ��page���뵽session������
		request.getSession().setAttribute("page", page);
		response.sendRedirect(request.getContextPath()
				+ "/listPageStudents.jsp");
	}

	// ��ת��ɾ�Ľ�ʦҳ��
	private void toListPageTeachers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ���Ϣ
		String deptName = request.getParameter("deptName");
		String currentPageIndex = request.getParameter("currentPageIndex");
		Page page = (Page) request.getSession().getAttribute("page");

		if (page != null & currentPageIndex != null) {
			int pageCount = page.getPageCount();
			// �ж�ҳ���Ƿ������ҳ��
			if (Integer.parseInt(currentPageIndex) > pageCount) {
				currentPageIndex = pageCount + "";
			}
		}

		// �ж�ϵ�����Ƿ�Ϊ��
		if (deptName == null) {
			// Ĭ��Ϊ�Զ���ѧԺ
			deptName = "�Զ���ѧԺ";
		} else {
			deptName = new String(deptName.getBytes("iso8859-1"), "UTF-8");
		}

		// �жϵ�ǰҳ���Ƿ�Ϊ�ջ�Ϊ��
		if (currentPageIndex == null) {
			// Ĭ��Ϊ��ǰҳ��Ϊ��һҳ
			currentPageIndex = "1";
		} else if ("0".equals(currentPageIndex)) {
			currentPageIndex = "1";
		}

		// ��deptName����session������
		request.getSession().setAttribute("deptName", deptName);

		// ��ȡѧԺ��Ϣ������dept����session������
		List<Dept> dept = ms.showDept();
		request.getSession().setAttribute("dept", dept);

		// ����service�����ҵ���߼�
		page = ms.listPageTeachers(Integer.parseInt(currentPageIndex), 7,
				deptName);
		// ��page���뵽session������
		request.getSession().setAttribute("page", page);
		response.sendRedirect(request.getContextPath()
				+ "/listPageTeachers.jsp");
	}

	// ��ת�򷢲��γ�ҳ��
	private void toAddCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ���õ�ǰ��ѧ��ѧ��
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String schoolYear = null;
		String semester = null;
		if (now.get(Calendar.MONTH) <= 8 & now.get(Calendar.MONTH) >= 1) {
			// ��ǰΪ�ڶ�ѧ��
			semester = 2 + "";
			schoolYear = (year - 1) + "-" + year;
		} else {
			// ��ǰΪ��һѧ��
			semester = 1 + "";
			schoolYear = year + "-" + (year + 1);
		}
		// ��schoolYear,semester����session������
		request.getSession().setAttribute("schoolYear", schoolYear);
		request.getSession().setAttribute("semester", semester);

		// ��ѯ��ǰѡ��״̬
		boolean status = ss.showStatus();
		// ��status���뵽session������
		request.getSession().setAttribute("status", status);

		// ��ת�򷢲��γ�ҳ��
		response.sendRedirect(request.getContextPath() + "/addCourse.jsp");
	}

	// ��ʾ������Ϣ
	private void showInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�session�����е���Ϣ
		String userType = (String) request.getSession()
				.getAttribute("userType");

		if ("student".equals(userType)) {
			// �û�������ѧ�����õ�session�����е�ѧ��ѧ��
			Student student = (Student) request.getSession().getAttribute(
					"loginUser");
			String studentNum = student.getStudentNum();
			// ����service�����ҵ���߼�,��student����session������
			student = ss.showInfo(studentNum);
			request.getSession().setAttribute("loginUser", student);
			response.sendRedirect(request.getContextPath() + "/showInfo.jsp");
		}
		if ("teacher".equals(userType)) {
			// �û������ǽ�ʦ���õ�session�����еĽ�ʦ����
			Teacher teacher = (Teacher) request.getSession().getAttribute(
					"loginUser");
			String teacherNum = teacher.getTeacherNum();
			// ����service�����ҵ���߼�,��teacher����session������
			teacher = ts.showInfo(teacherNum);
			request.getSession().setAttribute("loginUser", teacher);
			response.sendRedirect(request.getContextPath() + "/showInfo.jsp");
		}
		if ("manager".equals(userType)) {
			// �û������ǹ���Ա���õ�session�����еĹ���Ա����
			Manager manager = (Manager) request.getSession().getAttribute(
					"loginUser");
			String managerNum = manager.getManagerNum();
			// ����service�����ҵ���߼�,��manager����session������
			manager = ms.showInfo(managerNum);
			request.getSession().setAttribute("loginUser", manager);
			response.sendRedirect(request.getContextPath() + "/showInfo.jsp");
		}
	}

	// ��ת����ӽ�ʦҳ��
	private void toAddTeacher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��ȡѧԺ��Ϣ������dept����session������
		List<Dept> dept = ms.showDept();
		request.getSession().setAttribute("dept", dept);
		// ��ȡ�µĽ�ʦ���ţ�������session������
		String newTeacherNum = ms.showNewTeacherNum();
		request.getSession().setAttribute("newTeacherNum", newTeacherNum);
		// ��ת�����ҳ��
		response.sendRedirect(request.getContextPath() + "/addTeacher.jsp");
	}

	// ��ת�����ѧ��ҳ��
	private void toAddStudent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��ȡѧԺ��Ϣ������dept����session������
		List<Dept> dept = ms.showDept();
		request.getSession().setAttribute("dept", dept);
		// ��ȡ�µ�ѧ��ѧ�ţ�������session������
		String newStudentNum = ms.showNewStudentNum();
		request.getSession().setAttribute("newStudentNum", newStudentNum);
		// ��ת�����ҳ��
		response.sendRedirect(request.getContextPath() + "/addStudent.jsp");
	}

	// ǰ������ѡ��ҳ��
	private void toPermitCourseSelection(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ���õ�ǰ��ѧ��ѧ��
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String schoolYear = null;
		String semester = null;
		if (now.get(Calendar.MONTH) <= 8 & now.get(Calendar.MONTH) >= 1) {
			// ��ǰΪ�ڶ�ѧ��
			semester = 2 + "";
			schoolYear = (year - 1) + "-" + year;
		} else {
			// ��ǰΪ��һѧ��
			semester = 1 + "";
			schoolYear = year + "-" + (year + 1);
		}
		// ��schoolYear,semester����session������
		request.getSession().setAttribute("schoolYear", schoolYear);
		request.getSession().setAttribute("semester", semester);

		// ��ѯ��ǰѡ��״̬
		boolean status = ms.showStatus();
		// ��status����session������
		request.getSession().setAttribute("status", status);

		// ��ת�򿪷�ѡ��ҳ��
		response.sendRedirect(request.getContextPath()
				+ "/permitCourseSelection.jsp");
	}

	// ����ѡ��
	private void permitCourseSelection(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String schoolYear = request.getParameter("schoolYear");
		String semester = request.getParameter("semester");
		String status = request.getParameter("status");

		// ����service�����ҵ���߼�
		Boolean flag = ms.permitCourseSelection(schoolYear, semester, status);
		// ��ת�򿪷�ѡ��ҳ��
		toPermitCourseSelection(request, response);
	}

	// �޸�����
	private void changePassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String newPassword = request.getParameter("newPassword");
		// �õ�session�����е���Ϣ
		String userType = (String) request.getSession()
				.getAttribute("userType");

		if ("student".equals(userType)) {
			// �û�������ѧ�����õ�session�����е�ѧ��ѧ��
			Student student = (Student) request.getSession().getAttribute(
					"loginUser");
			String studentNum = student.getStudentNum();
			// ����service�����ҵ���߼�
			Boolean flag = ss.changePassword(studentNum, newPassword);
			// ��Ϊ�ı������룬��Ҫ��session�����е�loginUser���¸�ֵ
			student = ss.login(studentNum, newPassword);
			request.getSession().setAttribute("loginUser", student);
			response.sendRedirect(request.getContextPath()
					+ "/changePassword.jsp");
		}
		if ("teacher".equals(userType)) {
			// �û������ǽ�ʦ���õ�session�����еĽ�ʦ����
			Teacher teacher = (Teacher) request.getSession().getAttribute(
					"loginUser");
			String teacherNum = teacher.getTeacherNum();
			// ����service�����ҵ���߼�
			Boolean flag = ts.changePassword(teacherNum, newPassword);
			// ��Ϊ�ı������룬��Ҫ��session�����е�loginUser���¸�ֵ
			teacher = ts.login(teacherNum, newPassword);
			request.getSession().setAttribute("loginUser", teacher);
			response.sendRedirect(request.getContextPath()
					+ "/changePassword.jsp");
		}
		if ("manager".equals(userType)) {
			// �û������ǹ���Ա���õ�session�����еĹ���Ա����
			Manager manager = (Manager) request.getSession().getAttribute(
					"loginUser");
			String managerNum = manager.getManagerNum();
			// ����service�����ҵ���߼�
			Boolean flag = ms.changePassword(managerNum, newPassword);
			// ��Ϊ�ı������룬��Ҫ��session�����е�loginUser���¸�ֵ
			manager = ms.login(managerNum, newPassword);
			request.getSession().setAttribute("loginUser", manager);
			response.sendRedirect(request.getContextPath()
					+ "/changePassword.jsp");
		}
	}

	// �г�����˵Ŀγ̣�����ת����˿γ�ҳ��
	private void toExamineCourses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ���Ϣ
		String currentPageIndex = request.getParameter("currentPageIndex");
		Page page = (Page) request.getSession().getAttribute("page");

		if (page != null & currentPageIndex != null) {
			int pageCount = page.getPageCount();
			// �ж�ҳ���Ƿ������ҳ��
			if (Integer.parseInt(currentPageIndex) > pageCount) {
				currentPageIndex = pageCount + "";
			}
		}

		// �жϵ�ǰҳ���Ƿ�Ϊ�ջ�Ϊ��
		if (currentPageIndex == null) {
			// Ĭ��Ϊ��ǰҳ��Ϊ��һҳ
			currentPageIndex = "1";
		} else if ("0".equals(currentPageIndex)) {
			currentPageIndex = "1";
		}
		// ����service�����ҵ���߼�
		page = ms
				.listPageExaminedCourses(Integer.parseInt(currentPageIndex), 7);

		// ��page����session�����У�����תҳ��
		request.getSession().setAttribute("page", page);
		response.sendRedirect(request.getContextPath() + "/examineCourses.jsp");
	}

	// ���һ���γ�
	private void examineCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String _op = request.getParameter("_op");
		String courseNum = request.getParameter("id");
		// �ŵ�String������
		String[] courseNums = new String[] { courseNum };

		// ����service�����ҵ���߼�
		Boolean flag = ms.examineCourses(courseNums, _op);
		toExamineCourses(request, response);
	}

	// ��˶���γ�
	private void examineCourses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String _op = request.getParameter("_op");
		String ids = request.getParameter("ids");
		// ����ids��߶���һ�����ţ�Ҫȥ��
		ids = ids.substring(0, ids.length() - 1);
		// System.out.println(ids);
		// ����ַ���
		String[] courseNums = ids.split(",");

		// ����service�����ҵ���߼�
		Boolean flag = ms.examineCourses(courseNums, _op);
		toExamineCourses(request, response);
	}

	// �޸Ŀγ���Ϣ
	private void updateCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��װҳ�������
		CourseFormBean cfb = WebUtils.fillFormBean(CourseFormBean.class,
				request);
		// �������ݵ�һ��javabean��
		Course course = new Course();
		// ��������
		try {
			BeanUtils.copyProperties(course, cfb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ����service�����ҵ���߼�
		boolean flag = ms.updateCourse(course);
		toUpdateCourse(request, response);
	}

	// ��ת���޸Ŀγ���Ϣҳ��
	private void toUpdateCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String courseNum = request.getParameter("courseNum");
		// ͨ��courseNum�ҵ�Ҫ�޸ĵĿγ�
		Course course = ms.findCourseByCourseNum(courseNum);
		// ��course����session�����У�����תҳ��
		request.getSession().setAttribute("course", course);
		response.sendRedirect(request.getContextPath() + "/updateCourse.jsp");
	}

	// ����һ�ſγ�
	private void addCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��װҳ�������
		CourseFormBean cfb = WebUtils.fillFormBean(CourseFormBean.class,
				request);

		// cfb�Ŀγ̺���Ϊ�գ�����һ�����id����Ϊ�γ̺���
		cfb.setCourseNum(WebTools.createNewId());

		// �������ݵ�һ��javabean��
		Course course = new Course();
		// ��������
		try {
			BeanUtils.copyProperties(course, cfb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ����service�����ҵ���߼�
		boolean flag = ts.addCourse(course);
		toAddCourse(request, response);
	}

	// ���һ����ʦ
	private void addTeacher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��װҳ�������
		TeacherFormBean tfb = WebUtils.fillFormBean(TeacherFormBean.class,
				request);

		// ��ʦ����Ĭ�Ϻ͹�����ͬ
		tfb.setPassword(tfb.getTeacherNum());

		// �������ݵ�һ��javabean��
		Teacher teacher = new Teacher();
		if (tfb.getFormBirthday() != "") {
			// ���ղ�Ϊ��
			// ����ʱ����date���ͣ���������ע��һ��ʱ��ת����
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			try {
				BeanUtils.copyProperty(teacher, "birthday",
						tfb.getFormBirthday());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// �������Ϊ����teacher���birthday����Ϊnull
			teacher.setBirthday(null);
		}
		// ��������
		try {
			BeanUtils.copyProperties(teacher, tfb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ����service�����ҵ���߼�
		boolean flag = ms.addTeacher(teacher);
		toAddTeacher(request, response);
	}

	// ���һ��ѧ��
	private void addStudent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��װҳ�������
		StudentFormBean sfb = WebUtils.fillFormBean(StudentFormBean.class,
				request);

		// ѧ������Ĭ�Ϻ�ѧ����ͬ
		sfb.setPassword(sfb.getStudentNum());

		// �������ݵ�һ��javabean��
		Student student = new Student();
		if (sfb.getFormBirthday() != "") {
			// ���ղ�Ϊ��
			// ����ʱ����date���ͣ���������ע��һ��ʱ��ת����
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			try {
				BeanUtils.copyProperty(student, "birthday",
						sfb.getFormBirthday());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// �������Ϊ����student���birthday����Ϊnull
			student.setBirthday(null);
		}
		// ��������
		try {
			BeanUtils.copyProperties(student, sfb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ����service�����ҵ���߼�
		boolean flag = ms.addStudent(student);
		toAddStudent(request, response);
	}

	// ��ѯ��ѡ�γ�
	private void listSelectedCourses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ���Ϣ�����ж��û�������
		String userType = (String) request.getSession()
				.getAttribute("userType");
		String schoolYear = request.getParameter("schoolYear");
		String semester = request.getParameter("semester");
		String currentPageIndex = request.getParameter("currentPageIndex");
		Page page = (Page) request.getSession().getAttribute("page");

		if (page != null & currentPageIndex != null) {
			int pageCount = page.getPageCount();
			// �ж�ҳ���Ƿ������ҳ��
			if (Integer.parseInt(currentPageIndex) > pageCount) {
				currentPageIndex = pageCount + "";
			}
		}

		// �ж�ѧ��ѧ���Ƿ�Ϊ��
		if (schoolYear == null || semester == null) {
			// Ĭ��Ϊ��ǰѧ��͵�ǰѧ��
			schoolYear = ms.showSchoolYear();
			semester = ms.showSemester();
		}

		// �жϵ�ǰҳ���Ƿ�Ϊ��Ϊ��
		if (currentPageIndex == null) {
			// Ĭ��Ϊ��ǰҳ��Ϊ��һҳ
			currentPageIndex = "1";
		} else if ("0".equals(currentPageIndex)) {
			currentPageIndex = "1";
		}

		// ��schoolYear,semester����session������
		request.getSession().setAttribute("schoolYear", schoolYear);
		request.getSession().setAttribute("semester", semester);

		if ("student".equals(userType)) {
			// �û�������ѧ��
			// �õ�session�����е�ѧ��
			String studentNum = (String) request.getSession().getAttribute(
					"username");
			// ����service�����ҵ���߼�
			page = ss.listPageSelectedCourses(studentNum, 1, 7);
			boolean status = ss.showStatus();
			// ��status,page���뵽session������
			request.getSession().setAttribute("status", status);
			// ��list���뵽session������
			request.getSession().setAttribute("page", page);
			response.sendRedirect(request.getContextPath()
					+ "/listSelectedCourses.jsp");
		}
	}

	// ��ѡһ�ſγ�
	private void quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String courseNum = request.getParameter("id");
		// �ŵ�String������
		String[] courseNums = new String[] { courseNum };
		// �õ�session�����е�ѧ��
		String studentNum = (String) request.getSession().getAttribute(
				"username");

		// ����service�����ҵ���߼�
		Boolean flag = ss.quitCourses(studentNum, courseNums);
		listSelectedCourses(request, response);
	}

	// ��ѡ���ſγ�
	private void quitCourses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String ids = request.getParameter("ids");
		// ����ids��߶���һ�����ţ�Ҫȥ��
		ids = ids.substring(0, ids.length() - 1);
		// ����ַ���
		String[] courseNums = ids.split(",");
		// �õ�session�����е�ѧ��
		String studentNum = (String) request.getSession().getAttribute(
				"username");

		// ����service�����ҵ���߼�
		Boolean flag = ss.quitCourses(studentNum, courseNums);
		listSelectedCourses(request, response);
	}

	// ѡ��һ�ſγ�
	private void selectCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String courseNum = request.getParameter("id");
		// �ŵ�String������
		String[] courseNums = new String[] { courseNum };
		// �õ�session�����е�ѧ��
		String studentNum = (String) request.getSession().getAttribute(
				"username");

		// ����service�����ҵ���߼�
		Boolean flag = ss.selectCourses(studentNum, courseNums);
		listCourses(request, response);
	}

	// ѡ�޶��ſγ�
	private void selectCourses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String ids = request.getParameter("ids");
		// ����ids��߶���һ�����ţ�Ҫȥ��
		ids = ids.substring(0, ids.length() - 1);
		// ����ַ���
		String[] courseNums = ids.split(",");
		// �õ�session�����е�ѧ��
		String studentNum = (String) request.getSession().getAttribute(
				"username");

		// ����service�����ҵ���߼�
		Boolean flag = ss.selectCourses(studentNum, courseNums);
		listCourses(request, response);
	}

	// ɾ��һ�ſγ�
	private void deleteCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String courseNum = request.getParameter("id");
		// �ŵ�String������
		String[] courseNums = new String[] { courseNum };

		// ����service�����ҵ���߼�
		Boolean flag = ms.deleteCourses(courseNums);
		listCourses(request, response);
	}

	// ɾ�����ſγ�
	private void deleteCourses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫�ݵ�����
		String ids = request.getParameter("ids");
		// ����ids��߶���һ�����ţ�Ҫȥ��
		ids = ids.substring(0, ids.length() - 1);
		// ����ַ���
		String[] courseNums = ids.split(",");

		// ����service�����ҵ���߼�
		Boolean flag = ms.deleteCourses(courseNums);
		listCourses(request, response);
	}

	// ��ѯ�γ���Ϣ
	private void listCourses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �õ�session�����Ϣ
		String userType = (String) request.getSession()
				.getAttribute("userType");
		// �õ�ҳ�洫�ݵ���Ϣ
		String schoolYear = request.getParameter("schoolYear");
		String semester = request.getParameter("semester");
		String currentPageIndex = request.getParameter("currentPageIndex");
		Page page = (Page) request.getSession().getAttribute("page");

		if (page != null & currentPageIndex != null) {
			int pageCount = page.getPageCount();
			// �ж�ҳ���Ƿ������ҳ��
			if (Integer.parseInt(currentPageIndex) > pageCount) {
				currentPageIndex = pageCount + "";
			}
		}

		// �ж�ѧ��ѧ���Ƿ�Ϊ��
		if (schoolYear == null || semester == null) {
			// Ĭ��Ϊ��ǰѧ��͵�ǰѧ��
			schoolYear = ms.showSchoolYear();
			semester = ms.showSemester();
		}

		// �жϵ�ǰҳ���Ƿ�Ϊ�ջ�Ϊ��
		if (currentPageIndex == null) {
			// Ĭ��Ϊ��ǰҳ��Ϊ��һҳ
			currentPageIndex = "1";
		} else if ("0".equals(currentPageIndex)) {
			currentPageIndex = "1";
		}

		// ��schoolYear,semester����session������
		request.getSession().setAttribute("schoolYear", schoolYear);
		request.getSession().setAttribute("semester", semester);

		// �ж��û�������
		if ("student".equals(userType)) {
			// �û�������ѧ��

			// ����service�����ҵ���߼�
			page = ss.listPageCourses(Integer.parseInt(currentPageIndex), 7,
					schoolYear, semester);
			boolean status = ss.showStatus();
			// ��status,page���뵽session������
			request.getSession().setAttribute("status", status);
			request.getSession().setAttribute("page", page);
			response.sendRedirect(request.getContextPath()
					+ "/listAllCourses.jsp");
		} else if ("teacher".equals(userType)) {
			// �û������ǽ�ʦ
			// ��session�л�ȡ��ʦ����
			Teacher teacher = (Teacher) request.getSession().getAttribute(
					"loginUser");
			String teacherNum = teacher.getTeacherNum();

			// ����service�����ҵ���߼�
			page = ts
					.listPageCourses(teacherNum,
							Integer.parseInt(currentPageIndex), 7, schoolYear,
							semester);
			boolean status = ts.showStatus();
			// ��status,page���뵽session������
			request.getSession().setAttribute("status", status);
			request.getSession().setAttribute("page", page);
			response.sendRedirect(request.getContextPath()
					+ "/listAllCourses.jsp");
		} else if ("manager".equals(userType)) {
			// �û������ǹ���Ա

			// ����service�����ҵ���߼�
			page = ms.listPageCourses(Integer.parseInt(currentPageIndex), 7,
					schoolYear, semester);
			// ��page���뵽session������
			request.getSession().setAttribute("page", page);
			response.sendRedirect(request.getContextPath()
					+ "/listAllCourses.jsp");
		}
	}

	// �����û���½
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����ǰѧ��ѧ�ڴ���session������
		String schoolYear = ms.showSchoolYear();
		String semester = ms.showSemester();
		request.getSession().setAttribute("schoolYear", schoolYear);
		request.getSession().setAttribute("semester", semester);

		// ���е�¼��֤
		request.getRequestDispatcher("/LoginServlet")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}