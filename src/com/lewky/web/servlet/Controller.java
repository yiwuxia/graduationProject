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
 * @version 创建时间：2017-4-1 上午9:54:00
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

		// 拿到页面传递的信息
		String op = request.getParameter("op");

		// 判断进行哪一种操作
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

	//查询个人课表
	private void showTimetable(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到session里的信息
		String userType = (String) request.getSession()
				.getAttribute("userType");
		// 拿到页面传递的信息
		String schoolYear = request.getParameter("schoolYear");
		String semester = request.getParameter("semester");

		// 判断学期学年是否为空
		if (schoolYear == null || semester == null) {
			// 默认为当前学年和当前学期
			schoolYear = ms.showSchoolYear();
			semester = ms.showSemester();
		}

		// 将schoolYear,semester存入session对象中
		request.getSession().setAttribute("schoolYear", schoolYear);
		request.getSession().setAttribute("semester", semester);

		// 判断用户的类型
		if ("student".equals(userType)) {
			// 用户类型是学生
			//拿到学生学号
			Student student = (Student) request.getSession().getAttribute("loginUser");
			String studentNum = student.getStudentNum();
			
			//调用service完成业务逻辑
			List<Course> list = ss.showTimetable(studentNum, schoolYear, semester);
			List<List<String>> week = ss.showCourseWeek(studentNum, schoolYear, semester);
			List<List<String>> time = ss.showCourseTime(studentNum, schoolYear, semester);
			List<List<String>> weekday = ss.showCourseWeekday(studentNum, schoolYear, semester);
			List<List<String>> place = ss.showCoursePlace(studentNum, schoolYear, semester);
			//将list,week,time,weekday,place存入session对象中
			request.getSession().setAttribute("list", list);
			request.getSession().setAttribute("week", week);
			request.getSession().setAttribute("time", time);
			request.getSession().setAttribute("weekday", weekday);
			request.getSession().setAttribute("place", place);
			//跳转向个人课表页面
			response.sendRedirect(request.getContextPath() + "/showTimetable.jsp");
			
		}else if ("teacher".equals(userType)) {
			// 用户类型是教师
			//拿到教师工号
			Teacher teacher = (Teacher) request.getSession().getAttribute("loginUser");
			String teacherNum = teacher.getTeacherNum();
			
			//调用service完成业务逻辑
			List<Course> list = ts.showTimetable(teacherNum, schoolYear, semester);
			List<List<String>> week = ts.showCourseWeek(teacherNum, schoolYear, semester);
			List<List<String>> time = ts.showCourseTime(teacherNum, schoolYear, semester);
			List<List<String>> weekday = ts.showCourseWeekday(teacherNum, schoolYear, semester);
			List<List<String>> place = ts.showCoursePlace(teacherNum, schoolYear, semester);
			//将list,week,time,weekday,place存入session对象中
			request.getSession().setAttribute("list", list);
			request.getSession().setAttribute("week", week);
			request.getSession().setAttribute("time", time);
			request.getSession().setAttribute("weekday", weekday);
			request.getSession().setAttribute("place", place);
			//跳转向个人课表页面
			response.sendRedirect(request.getContextPath() + "/showTimetable.jsp");
		}
	}

	// 录入成绩
	private void inputGrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 封装页面的数据
		CourseSelectionFormBean csfb = WebUtils.fillFormBean(
				CourseSelectionFormBean.class, request);

		// 拷贝数据
		CourseSelection courseSelection = new CourseSelection();
		try {
			BeanUtils.copyProperties(courseSelection, csfb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 调用service层完成业务逻辑
		boolean flag = ts.inputGrade(courseSelection);
		showGrade(request, response);
	}

	// 跳转向录入成绩页面
	private void toInputGrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的信息
		String courseNum = request.getParameter("courseNum");
		String studentNum = request.getParameter("studentNum");
		// 调用service完成业务逻辑
		CourseSelection courseSelection = ts.showGrade(courseNum, studentNum);
		// 将courseSelection存入session对象中
		request.getSession().setAttribute("courseSelection", courseSelection);
		// 跳转向录入成绩页面
		response.sendRedirect(request.getContextPath() + "/inputGrade.jsp");
	}

	// 显示教师当前学期的课程
	private void currentCourses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 拿到页面传递的信息
		String currentPageIndex = request.getParameter("currentPageIndex");
		Page page = (Page) request.getSession().getAttribute("page");

		// 获取当前学年学期
		String schoolYear = ms.showSchoolYear();
		String semester = ms.showSemester();

		if (page != null & currentPageIndex != null) {
			int pageCount = page.getPageCount();
			// 判断页面是否大于总页面
			if (Integer.parseInt(currentPageIndex) > pageCount) {
				currentPageIndex = pageCount + "";
			}
		}

		// 判断当前页面是否为空或为零
		if (currentPageIndex == null) {
			// 默认为当前页面为第一页
			currentPageIndex = "1";
		} else if ("0".equals(currentPageIndex)) {
			currentPageIndex = "1";
		}

		// 将schoolYear,semester存入session对象中
		request.getSession().setAttribute("schoolYear", schoolYear);
		request.getSession().setAttribute("semester", semester);

		// 从session中获取教师工号
		Teacher teacher = (Teacher) request.getSession().getAttribute(
				"loginUser");
		String teacherNum = teacher.getTeacherNum();

		// 调用service层完成业务逻辑
		page = ts.listPageCourses(teacherNum,
				Integer.parseInt(currentPageIndex), 7, schoolYear, semester);
		boolean status = ts.showStatus();
		// 将status,page存入到session对象中
		request.getSession().setAttribute("status", status);
		request.getSession().setAttribute("page", page);
		response.sendRedirect(request.getContextPath() + "/currentCourses.jsp");
	}

	// 退出登录，回到登录界面
	private void quitLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 将session对象中的error属性清除
		request.getSession().setAttribute("error", "");
		// 跳转到登陆界面
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	// 显示课程详情
	private void showCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String courseNum = request.getParameter("courseNum");
		String currentPageIndex = request.getParameter("currentPageIndex");
		Page page = (Page) request.getSession().getAttribute("page");

		if (page != null & currentPageIndex != null) {
			int pageCount = page.getPageCount();
			// 判断页面是否大于总页面
			if (Integer.parseInt(currentPageIndex) > pageCount) {
				currentPageIndex = pageCount + "";
			}
		}

		// 判断当前页面是否为空或为零
		if (currentPageIndex == null) {
			// 默认为当前页面为第一页
			currentPageIndex = "1";
		} else if ("0".equals(currentPageIndex)) {
			currentPageIndex = "1";
		}

		// 调用service层完成业务逻辑
		page = ts.showCourse(courseNum, Integer.parseInt(currentPageIndex), 10);
		// 将page存入到session对象中
		request.getSession().setAttribute("page", page);
		response.sendRedirect(request.getContextPath() + "/showCourse.jsp");
	}

	// 跳转向查看成绩页面
	private void showGrade(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到session里的信息
		String userType = (String) request.getSession()
				.getAttribute("userType");

		// 拿到页面传递的数据
		String courseNum = request.getParameter("courseNum");
		String schoolYear = request.getParameter("schoolYear");
		String semester = request.getParameter("semester");
		String currentPageIndex = request.getParameter("currentPageIndex");
		Page page = (Page) request.getSession().getAttribute("page");

		if (page != null & currentPageIndex != null) {
			int pageCount = page.getPageCount();
			// 判断页面是否大于总页面
			if (Integer.parseInt(currentPageIndex) > pageCount) {
				currentPageIndex = pageCount + "";
			}
		}

		// 判断学期学年是否为空
		if (schoolYear == null || semester == null) {
			// 默认为当前学年和当前学期
			schoolYear = ms.showSchoolYear();
			semester = ms.showSemester();
		}
		
		// 判断当前页面是否为空或为零
		if (currentPageIndex == null) {
			// 默认为当前页面为第一页
			currentPageIndex = "1";
		} else if ("0".equals(currentPageIndex)) {
			currentPageIndex = "1";
		}

		// 判断用户的类型
		if ("teacher".equals(userType)) {
			// 用户类型是教师
			// 调用service层完成业务逻辑
			page = ts.showCourse(courseNum, Integer.parseInt(currentPageIndex),
					10);

			// 将page存入到session对象中
			request.getSession().setAttribute("page", page);
			response.sendRedirect(request.getContextPath() + "/showGrade.jsp");
		} else if ("student".equals(userType)) {
			// 用户类型是学生
			// 从session中获取学生学号
			Student student = (Student) request.getSession().getAttribute(
					"loginUser");
			String studentNum = student.getStudentNum();

			// 判断学年是否为all
			if ("all".equals(schoolYear)) {
				// 查询该学生的所有选修课程成绩
				// 调用service层完成业务逻辑
				page = ss.showGrade(studentNum,
						Integer.parseInt(currentPageIndex), 10);

				// 将page,schoolYear,semester存入到session对象中
				request.getSession().setAttribute("page", page);
				request.getSession().setAttribute("schoolYear", "");
				request.getSession().setAttribute("semester", "");
				response.sendRedirect(request.getContextPath()
						+ "/showStudentGrade.jsp");
			} else {
				// 查询某学年学期的选修课程成绩
				// 调用service层完成业务逻辑
				page = ss.showGrade(studentNum, schoolYear, semester,
						Integer.parseInt(currentPageIndex), 10);

				// 将page,schoolYear,semester存入到session对象中
				request.getSession().setAttribute("page", page);
				request.getSession().setAttribute("schoolYear", schoolYear);
				request.getSession().setAttribute("semester", semester);
				response.sendRedirect(request.getContextPath()
						+ "/showStudentGrade.jsp");
			}
		}
	}

	// 更新教师
	private void updateTeacher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 封装页面的数据
		TeacherFormBean tfb = WebUtils.fillFormBean(TeacherFormBean.class,
				request);
		// 拷贝数据到一个javabean中
		Teacher teacher = new Teacher();
		if (tfb.getFormBirthday() != "") {
			// 生日不为空
			// 由于时间是date类型，所以首先注册一个时间转换器
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
			// 如果生日为空则将teacher里的birthday设置为null
			teacher.setBirthday(null);
		}

		// 拷贝数据
		try {
			BeanUtils.copyProperties(teacher, tfb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 调用service层完成业务逻辑
		boolean flag = ms.updateTeacher(teacher);
		toUpdateTeacher(request, response);
	}

	// 跳转向更新教师页面
	private void toUpdateTeacher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String teacherNum = request.getParameter("teacherNum");
		// 通过teacherNum找到要修改的教师
		Teacher teacher = ms.findTeacherByTeacherNum(teacherNum);
		// 将teacher存入session对象中，并跳转页面
		request.getSession().setAttribute("teacher", teacher);

		// 获取学院信息，并将dept存入session对象中
		List<Dept> dept = ms.showDept();
		request.getSession().setAttribute("dept", dept);
		response.sendRedirect(request.getContextPath() + "/updateTeacher.jsp");
	}

	// 更新学生
	private void updateStudent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 封装页面的数据
		StudentFormBean sfb = WebUtils.fillFormBean(StudentFormBean.class,
				request);
		// 拷贝数据到一个javabean中
		Student student = new Student();
		if (sfb.getFormBirthday() != "") {
			// 生日不为空
			// 由于时间是date类型，所以首先注册一个时间转换器
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
			// 如果生日为空则将student里的birthday设置为null
			student.setBirthday(null);
		}

		// 拷贝数据
		try {
			BeanUtils.copyProperties(student, sfb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 调用service层完成业务逻辑
		boolean flag = ms.updateStudent(student);
		toUpdateStudent(request, response);
	}

	// 跳转向更新学生页面
	private void toUpdateStudent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String studentNum = request.getParameter("studentNum");
		// 通过studentNum找到要修改的学生
		Student student = ms.findStudentByStudentNum(studentNum);
		// 将student存入session对象中，并跳转页面
		request.getSession().setAttribute("student", student);

		// 获取学院信息，并将dept存入session对象中
		List<Dept> dept = ms.showDept();
		request.getSession().setAttribute("dept", dept);
		response.sendRedirect(request.getContextPath() + "/updateStudent.jsp");
	}

	// 删除多个学生
	private void deleteStudents(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String ids = request.getParameter("ids");
		// 由于ids后边多了一个逗号，要去掉
		ids = ids.substring(0, ids.length() - 1);
		// 拆分字符串
		String[] studentNums = ids.split(",");

		// 调用service层完成业务逻辑
		Boolean flag = ms.deleteStudents(studentNums);
		toListPageStudents(request, response);
	}

	// 删除一个学生
	private void deleteStudent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String studentNum = request.getParameter("id");
		// 放到String数组中
		String[] studentNums = new String[] { studentNum };

		// 调用service层完成业务逻辑
		Boolean flag = ms.deleteStudents(studentNums);
		toListPageStudents(request, response);
	}

	// 删除多个教师
	private void deleteTeachers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String ids = request.getParameter("ids");
		// 由于ids后边多了一个逗号，要去掉
		ids = ids.substring(0, ids.length() - 1);
		// 拆分字符串
		String[] teacherNums = ids.split(",");

		// 调用service层完成业务逻辑
		Boolean flag = ms.deleteTeachers(teacherNums);
		toListPageTeachers(request, response);
	}

	// 删除一个教师
	private void deleteTeacher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String teacherNum = request.getParameter("id");
		// 放到String数组中
		String[] teacherNums = new String[] { teacherNum };

		// 调用service层完成业务逻辑
		Boolean flag = ms.deleteTeachers(teacherNums);
		toListPageTeachers(request, response);
	}

	// 跳转向删改学生页面
	private void toListPageStudents(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的信息
		String deptName = request.getParameter("deptName");
		String majorName = request.getParameter("majorName");
		String currentPageIndex = request.getParameter("currentPageIndex");
		Page page = (Page) request.getSession().getAttribute("page");

		// 判断页面索引的值
		if (page != null & currentPageIndex != null) {
			int pageCount = page.getPageCount();

			// 判断页面索引是否大于总页面
			if (Integer.parseInt(currentPageIndex) > pageCount) {
				currentPageIndex = pageCount + "";
			}
		}

		// 判断系名称是否为空
		if (deptName == null) {
			// 默认为自动化学院
			deptName = "自动化学院";
		} else {
			deptName = new String(deptName.getBytes("iso8859-1"), "UTF-8");
		}

		// 判断专业名称是否为空
		if (majorName == null) {
			// 默认为物联网工程专业
			majorName = "物联网工程专业";
		} else {
			majorName = new String(majorName.getBytes("iso8859-1"), "UTF-8");
		}

		// 判断当前页面是否为空或为零
		if (currentPageIndex == null) {
			// 默认为当前页面为第一页
			currentPageIndex = "1";
		} else if ("0".equals(currentPageIndex)) {
			currentPageIndex = "1";
		}

		// 将deptName,majorName存入session对象中
		request.getSession().setAttribute("deptName", deptName);
		request.getSession().setAttribute("majorName", majorName);

		// 获取学院信息，并将dept存入session对象中
		List<Dept> dept = ms.showDept();
		request.getSession().setAttribute("dept", dept);

		// 调用service层完成业务逻辑
		page = ms.listPageStudents(Integer.parseInt(currentPageIndex), 7,
				majorName);
		// 将page存入到session对象中
		request.getSession().setAttribute("page", page);
		response.sendRedirect(request.getContextPath()
				+ "/listPageStudents.jsp");
	}

	// 跳转向删改教师页面
	private void toListPageTeachers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的信息
		String deptName = request.getParameter("deptName");
		String currentPageIndex = request.getParameter("currentPageIndex");
		Page page = (Page) request.getSession().getAttribute("page");

		if (page != null & currentPageIndex != null) {
			int pageCount = page.getPageCount();
			// 判断页面是否大于总页面
			if (Integer.parseInt(currentPageIndex) > pageCount) {
				currentPageIndex = pageCount + "";
			}
		}

		// 判断系名称是否为空
		if (deptName == null) {
			// 默认为自动化学院
			deptName = "自动化学院";
		} else {
			deptName = new String(deptName.getBytes("iso8859-1"), "UTF-8");
		}

		// 判断当前页面是否为空或为零
		if (currentPageIndex == null) {
			// 默认为当前页面为第一页
			currentPageIndex = "1";
		} else if ("0".equals(currentPageIndex)) {
			currentPageIndex = "1";
		}

		// 将deptName存入session对象中
		request.getSession().setAttribute("deptName", deptName);

		// 获取学院信息，并将dept存入session对象中
		List<Dept> dept = ms.showDept();
		request.getSession().setAttribute("dept", dept);

		// 调用service层完成业务逻辑
		page = ms.listPageTeachers(Integer.parseInt(currentPageIndex), 7,
				deptName);
		// 将page存入到session对象中
		request.getSession().setAttribute("page", page);
		response.sendRedirect(request.getContextPath()
				+ "/listPageTeachers.jsp");
	}

	// 跳转向发布课程页面
	private void toAddCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 设置当前的学年学期
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String schoolYear = null;
		String semester = null;
		if (now.get(Calendar.MONTH) <= 8 & now.get(Calendar.MONTH) >= 1) {
			// 当前为第二学期
			semester = 2 + "";
			schoolYear = (year - 1) + "-" + year;
		} else {
			// 当前为第一学期
			semester = 1 + "";
			schoolYear = year + "-" + (year + 1);
		}
		// 将schoolYear,semester存入session对象中
		request.getSession().setAttribute("schoolYear", schoolYear);
		request.getSession().setAttribute("semester", semester);

		// 查询当前选课状态
		boolean status = ss.showStatus();
		// 将status存入到session对象中
		request.getSession().setAttribute("status", status);

		// 跳转向发布课程页面
		response.sendRedirect(request.getContextPath() + "/addCourse.jsp");
	}

	// 显示个人信息
	private void showInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到session对象中的信息
		String userType = (String) request.getSession()
				.getAttribute("userType");

		if ("student".equals(userType)) {
			// 用户类型是学生，拿到session对象中的学生学号
			Student student = (Student) request.getSession().getAttribute(
					"loginUser");
			String studentNum = student.getStudentNum();
			// 调用service层完成业务逻辑,将student存入session对象中
			student = ss.showInfo(studentNum);
			request.getSession().setAttribute("loginUser", student);
			response.sendRedirect(request.getContextPath() + "/showInfo.jsp");
		}
		if ("teacher".equals(userType)) {
			// 用户类型是教师，拿到session对象中的教师工号
			Teacher teacher = (Teacher) request.getSession().getAttribute(
					"loginUser");
			String teacherNum = teacher.getTeacherNum();
			// 调用service层完成业务逻辑,将teacher存入session对象中
			teacher = ts.showInfo(teacherNum);
			request.getSession().setAttribute("loginUser", teacher);
			response.sendRedirect(request.getContextPath() + "/showInfo.jsp");
		}
		if ("manager".equals(userType)) {
			// 用户类型是管理员，拿到session对象中的管理员工号
			Manager manager = (Manager) request.getSession().getAttribute(
					"loginUser");
			String managerNum = manager.getManagerNum();
			// 调用service层完成业务逻辑,将manager存入session对象中
			manager = ms.showInfo(managerNum);
			request.getSession().setAttribute("loginUser", manager);
			response.sendRedirect(request.getContextPath() + "/showInfo.jsp");
		}
	}

	// 跳转向添加教师页面
	private void toAddTeacher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 获取学院信息，并将dept存入session对象中
		List<Dept> dept = ms.showDept();
		request.getSession().setAttribute("dept", dept);
		// 获取新的教师工号，并存入session对象中
		String newTeacherNum = ms.showNewTeacherNum();
		request.getSession().setAttribute("newTeacherNum", newTeacherNum);
		// 跳转向添加页面
		response.sendRedirect(request.getContextPath() + "/addTeacher.jsp");
	}

	// 跳转向添加学生页面
	private void toAddStudent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 获取学院信息，并将dept存入session对象中
		List<Dept> dept = ms.showDept();
		request.getSession().setAttribute("dept", dept);
		// 获取新的学生学号，并存入session对象中
		String newStudentNum = ms.showNewStudentNum();
		request.getSession().setAttribute("newStudentNum", newStudentNum);
		// 跳转向添加页面
		response.sendRedirect(request.getContextPath() + "/addStudent.jsp");
	}

	// 前往开放选课页面
	private void toPermitCourseSelection(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 设置当前的学年学期
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String schoolYear = null;
		String semester = null;
		if (now.get(Calendar.MONTH) <= 8 & now.get(Calendar.MONTH) >= 1) {
			// 当前为第二学期
			semester = 2 + "";
			schoolYear = (year - 1) + "-" + year;
		} else {
			// 当前为第一学期
			semester = 1 + "";
			schoolYear = year + "-" + (year + 1);
		}
		// 将schoolYear,semester存入session对象中
		request.getSession().setAttribute("schoolYear", schoolYear);
		request.getSession().setAttribute("semester", semester);

		// 查询当前选课状态
		boolean status = ms.showStatus();
		// 将status存入session对象中
		request.getSession().setAttribute("status", status);

		// 跳转向开放选课页面
		response.sendRedirect(request.getContextPath()
				+ "/permitCourseSelection.jsp");
	}

	// 开放选课
	private void permitCourseSelection(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String schoolYear = request.getParameter("schoolYear");
		String semester = request.getParameter("semester");
		String status = request.getParameter("status");

		// 调用service层完成业务逻辑
		Boolean flag = ms.permitCourseSelection(schoolYear, semester, status);
		// 跳转向开放选课页面
		toPermitCourseSelection(request, response);
	}

	// 修改密码
	private void changePassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String newPassword = request.getParameter("newPassword");
		// 拿到session对象中的信息
		String userType = (String) request.getSession()
				.getAttribute("userType");

		if ("student".equals(userType)) {
			// 用户类型是学生，拿到session对象中的学生学号
			Student student = (Student) request.getSession().getAttribute(
					"loginUser");
			String studentNum = student.getStudentNum();
			// 调用service层完成业务逻辑
			Boolean flag = ss.changePassword(studentNum, newPassword);
			// 因为改变了密码，需要将session对象中的loginUser重新赋值
			student = ss.login(studentNum, newPassword);
			request.getSession().setAttribute("loginUser", student);
			response.sendRedirect(request.getContextPath()
					+ "/changePassword.jsp");
		}
		if ("teacher".equals(userType)) {
			// 用户类型是教师，拿到session对象中的教师工号
			Teacher teacher = (Teacher) request.getSession().getAttribute(
					"loginUser");
			String teacherNum = teacher.getTeacherNum();
			// 调用service层完成业务逻辑
			Boolean flag = ts.changePassword(teacherNum, newPassword);
			// 因为改变了密码，需要将session对象中的loginUser重新赋值
			teacher = ts.login(teacherNum, newPassword);
			request.getSession().setAttribute("loginUser", teacher);
			response.sendRedirect(request.getContextPath()
					+ "/changePassword.jsp");
		}
		if ("manager".equals(userType)) {
			// 用户类型是管理员，拿到session对象中的管理员工码
			Manager manager = (Manager) request.getSession().getAttribute(
					"loginUser");
			String managerNum = manager.getManagerNum();
			// 调用service层完成业务逻辑
			Boolean flag = ms.changePassword(managerNum, newPassword);
			// 因为改变了密码，需要将session对象中的loginUser重新赋值
			manager = ms.login(managerNum, newPassword);
			request.getSession().setAttribute("loginUser", manager);
			response.sendRedirect(request.getContextPath()
					+ "/changePassword.jsp");
		}
	}

	// 列出需审核的课程，并跳转向审核课程页面
	private void toExamineCourses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的信息
		String currentPageIndex = request.getParameter("currentPageIndex");
		Page page = (Page) request.getSession().getAttribute("page");

		if (page != null & currentPageIndex != null) {
			int pageCount = page.getPageCount();
			// 判断页面是否大于总页面
			if (Integer.parseInt(currentPageIndex) > pageCount) {
				currentPageIndex = pageCount + "";
			}
		}

		// 判断当前页面是否为空或为零
		if (currentPageIndex == null) {
			// 默认为当前页面为第一页
			currentPageIndex = "1";
		} else if ("0".equals(currentPageIndex)) {
			currentPageIndex = "1";
		}
		// 调用service层完成业务逻辑
		page = ms
				.listPageExaminedCourses(Integer.parseInt(currentPageIndex), 7);

		// 将page存入session对象中，并跳转页面
		request.getSession().setAttribute("page", page);
		response.sendRedirect(request.getContextPath() + "/examineCourses.jsp");
	}

	// 审核一个课程
	private void examineCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String _op = request.getParameter("_op");
		String courseNum = request.getParameter("id");
		// 放到String数组中
		String[] courseNums = new String[] { courseNum };

		// 调用service层完成业务逻辑
		Boolean flag = ms.examineCourses(courseNums, _op);
		toExamineCourses(request, response);
	}

	// 审核多个课程
	private void examineCourses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String _op = request.getParameter("_op");
		String ids = request.getParameter("ids");
		// 由于ids后边多了一个逗号，要去掉
		ids = ids.substring(0, ids.length() - 1);
		// System.out.println(ids);
		// 拆分字符串
		String[] courseNums = ids.split(",");

		// 调用service层完成业务逻辑
		Boolean flag = ms.examineCourses(courseNums, _op);
		toExamineCourses(request, response);
	}

	// 修改课程信息
	private void updateCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 封装页面的数据
		CourseFormBean cfb = WebUtils.fillFormBean(CourseFormBean.class,
				request);
		// 拷贝数据到一个javabean中
		Course course = new Course();
		// 拷贝数据
		try {
			BeanUtils.copyProperties(course, cfb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 调用service层完成业务逻辑
		boolean flag = ms.updateCourse(course);
		toUpdateCourse(request, response);
	}

	// 跳转向修改课程信息页面
	private void toUpdateCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String courseNum = request.getParameter("courseNum");
		// 通过courseNum找到要修改的课程
		Course course = ms.findCourseByCourseNum(courseNum);
		// 将course存入session对象中，并跳转页面
		request.getSession().setAttribute("course", course);
		response.sendRedirect(request.getContextPath() + "/updateCourse.jsp");
	}

	// 发布一门课程
	private void addCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 封装页面的数据
		CourseFormBean cfb = WebUtils.fillFormBean(CourseFormBean.class,
				request);

		// cfb的课程号码为空，生成一个随机id设置为课程号码
		cfb.setCourseNum(WebTools.createNewId());

		// 拷贝数据到一个javabean中
		Course course = new Course();
		// 拷贝数据
		try {
			BeanUtils.copyProperties(course, cfb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 调用service层完成业务逻辑
		boolean flag = ts.addCourse(course);
		toAddCourse(request, response);
	}

	// 添加一个教师
	private void addTeacher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 封装页面的数据
		TeacherFormBean tfb = WebUtils.fillFormBean(TeacherFormBean.class,
				request);

		// 教师密码默认和工号相同
		tfb.setPassword(tfb.getTeacherNum());

		// 拷贝数据到一个javabean中
		Teacher teacher = new Teacher();
		if (tfb.getFormBirthday() != "") {
			// 生日不为空
			// 由于时间是date类型，所以首先注册一个时间转换器
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
			// 如果生日为空则将teacher里的birthday设置为null
			teacher.setBirthday(null);
		}
		// 拷贝数据
		try {
			BeanUtils.copyProperties(teacher, tfb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 调用service层完成业务逻辑
		boolean flag = ms.addTeacher(teacher);
		toAddTeacher(request, response);
	}

	// 添加一个学生
	private void addStudent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 封装页面的数据
		StudentFormBean sfb = WebUtils.fillFormBean(StudentFormBean.class,
				request);

		// 学生密码默认和学号相同
		sfb.setPassword(sfb.getStudentNum());

		// 拷贝数据到一个javabean中
		Student student = new Student();
		if (sfb.getFormBirthday() != "") {
			// 生日不为空
			// 由于时间是date类型，所以首先注册一个时间转换器
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
			// 如果生日为空则将student里的birthday设置为null
			student.setBirthday(null);
		}
		// 拷贝数据
		try {
			BeanUtils.copyProperties(student, sfb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 调用service层完成业务逻辑
		boolean flag = ms.addStudent(student);
		toAddStudent(request, response);
	}

	// 查询已选课程
	private void listSelectedCourses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的信息，并判断用户的类型
		String userType = (String) request.getSession()
				.getAttribute("userType");
		String schoolYear = request.getParameter("schoolYear");
		String semester = request.getParameter("semester");
		String currentPageIndex = request.getParameter("currentPageIndex");
		Page page = (Page) request.getSession().getAttribute("page");

		if (page != null & currentPageIndex != null) {
			int pageCount = page.getPageCount();
			// 判断页面是否大于总页面
			if (Integer.parseInt(currentPageIndex) > pageCount) {
				currentPageIndex = pageCount + "";
			}
		}

		// 判断学期学年是否为空
		if (schoolYear == null || semester == null) {
			// 默认为当前学年和当前学期
			schoolYear = ms.showSchoolYear();
			semester = ms.showSemester();
		}

		// 判断当前页面是否为空为零
		if (currentPageIndex == null) {
			// 默认为当前页面为第一页
			currentPageIndex = "1";
		} else if ("0".equals(currentPageIndex)) {
			currentPageIndex = "1";
		}

		// 将schoolYear,semester存入session对象中
		request.getSession().setAttribute("schoolYear", schoolYear);
		request.getSession().setAttribute("semester", semester);

		if ("student".equals(userType)) {
			// 用户类型是学生
			// 拿到session对象中的学号
			String studentNum = (String) request.getSession().getAttribute(
					"username");
			// 调用service层完成业务逻辑
			page = ss.listPageSelectedCourses(studentNum, 1, 7);
			boolean status = ss.showStatus();
			// 将status,page存入到session对象中
			request.getSession().setAttribute("status", status);
			// 将list存入到session对象中
			request.getSession().setAttribute("page", page);
			response.sendRedirect(request.getContextPath()
					+ "/listSelectedCourses.jsp");
		}
	}

	// 退选一门课程
	private void quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 拿到页面传递的数据
		String courseNum = request.getParameter("id");
		// 放到String数组中
		String[] courseNums = new String[] { courseNum };
		// 拿到session对象中的学号
		String studentNum = (String) request.getSession().getAttribute(
				"username");

		// 调用service层完成业务逻辑
		Boolean flag = ss.quitCourses(studentNum, courseNums);
		listSelectedCourses(request, response);
	}

	// 退选多门课程
	private void quitCourses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String ids = request.getParameter("ids");
		// 由于ids后边多了一个逗号，要去掉
		ids = ids.substring(0, ids.length() - 1);
		// 拆分字符串
		String[] courseNums = ids.split(",");
		// 拿到session对象中的学号
		String studentNum = (String) request.getSession().getAttribute(
				"username");

		// 调用service层完成业务逻辑
		Boolean flag = ss.quitCourses(studentNum, courseNums);
		listSelectedCourses(request, response);
	}

	// 选修一门课程
	private void selectCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String courseNum = request.getParameter("id");
		// 放到String数组中
		String[] courseNums = new String[] { courseNum };
		// 拿到session对象中的学号
		String studentNum = (String) request.getSession().getAttribute(
				"username");

		// 调用service层完成业务逻辑
		Boolean flag = ss.selectCourses(studentNum, courseNums);
		listCourses(request, response);
	}

	// 选修多门课程
	private void selectCourses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String ids = request.getParameter("ids");
		// 由于ids后边多了一个逗号，要去掉
		ids = ids.substring(0, ids.length() - 1);
		// 拆分字符串
		String[] courseNums = ids.split(",");
		// 拿到session对象中的学号
		String studentNum = (String) request.getSession().getAttribute(
				"username");

		// 调用service层完成业务逻辑
		Boolean flag = ss.selectCourses(studentNum, courseNums);
		listCourses(request, response);
	}

	// 删除一门课程
	private void deleteCourse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String courseNum = request.getParameter("id");
		// 放到String数组中
		String[] courseNums = new String[] { courseNum };

		// 调用service层完成业务逻辑
		Boolean flag = ms.deleteCourses(courseNums);
		listCourses(request, response);
	}

	// 删除多门课程
	private void deleteCourses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传递的数据
		String ids = request.getParameter("ids");
		// 由于ids后边多了一个逗号，要去掉
		ids = ids.substring(0, ids.length() - 1);
		// 拆分字符串
		String[] courseNums = ids.split(",");

		// 调用service层完成业务逻辑
		Boolean flag = ms.deleteCourses(courseNums);
		listCourses(request, response);
	}

	// 查询课程信息
	private void listCourses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 拿到session里的信息
		String userType = (String) request.getSession()
				.getAttribute("userType");
		// 拿到页面传递的信息
		String schoolYear = request.getParameter("schoolYear");
		String semester = request.getParameter("semester");
		String currentPageIndex = request.getParameter("currentPageIndex");
		Page page = (Page) request.getSession().getAttribute("page");

		if (page != null & currentPageIndex != null) {
			int pageCount = page.getPageCount();
			// 判断页面是否大于总页面
			if (Integer.parseInt(currentPageIndex) > pageCount) {
				currentPageIndex = pageCount + "";
			}
		}

		// 判断学期学年是否为空
		if (schoolYear == null || semester == null) {
			// 默认为当前学年和当前学期
			schoolYear = ms.showSchoolYear();
			semester = ms.showSemester();
		}

		// 判断当前页面是否为空或为零
		if (currentPageIndex == null) {
			// 默认为当前页面为第一页
			currentPageIndex = "1";
		} else if ("0".equals(currentPageIndex)) {
			currentPageIndex = "1";
		}

		// 将schoolYear,semester存入session对象中
		request.getSession().setAttribute("schoolYear", schoolYear);
		request.getSession().setAttribute("semester", semester);

		// 判断用户的类型
		if ("student".equals(userType)) {
			// 用户类型是学生

			// 调用service层完成业务逻辑
			page = ss.listPageCourses(Integer.parseInt(currentPageIndex), 7,
					schoolYear, semester);
			boolean status = ss.showStatus();
			// 将status,page存入到session对象中
			request.getSession().setAttribute("status", status);
			request.getSession().setAttribute("page", page);
			response.sendRedirect(request.getContextPath()
					+ "/listAllCourses.jsp");
		} else if ("teacher".equals(userType)) {
			// 用户类型是教师
			// 从session中获取教师工号
			Teacher teacher = (Teacher) request.getSession().getAttribute(
					"loginUser");
			String teacherNum = teacher.getTeacherNum();

			// 调用service层完成业务逻辑
			page = ts
					.listPageCourses(teacherNum,
							Integer.parseInt(currentPageIndex), 7, schoolYear,
							semester);
			boolean status = ts.showStatus();
			// 将status,page存入到session对象中
			request.getSession().setAttribute("status", status);
			request.getSession().setAttribute("page", page);
			response.sendRedirect(request.getContextPath()
					+ "/listAllCourses.jsp");
		} else if ("manager".equals(userType)) {
			// 用户类型是管理员

			// 调用service层完成业务逻辑
			page = ms.listPageCourses(Integer.parseInt(currentPageIndex), 7,
					schoolYear, semester);
			// 将page存入到session对象中
			request.getSession().setAttribute("page", page);
			response.sendRedirect(request.getContextPath()
					+ "/listAllCourses.jsp");
		}
	}

	// 进行用户登陆
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 将当前学年学期存入session对象中
		String schoolYear = ms.showSchoolYear();
		String semester = ms.showSemester();
		request.getSession().setAttribute("schoolYear", schoolYear);
		request.getSession().setAttribute("semester", semester);

		// 进行登录验证
		request.getRequestDispatcher("/LoginServlet")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}