package com.lewky.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lewky.bean.Manager;
import com.lewky.bean.Student;
import com.lewky.bean.Teacher;
import com.lewky.service.ManagerService;
import com.lewky.service.StudentService;
import com.lewky.service.TeacherService;
import com.lewky.service.impl.ManagerServiceImpl;
import com.lewky.service.impl.StudentServiceImpl;
import com.lewky.service.impl.TeacherServiceImpl;

/**
 * @author 123 E-mail: lewkyliu@gmail.com
 * @version 创建时间：2017-4-1 下午7:11:26
 */
@WebServlet("/LoginServlet")
// 完成登陆的功能
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	StudentService ss = new StudentServiceImpl();
	TeacherService ts = new TeacherServiceImpl();
	ManagerService ms = new ManagerServiceImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 拿到session里的code
		String code = (String) request.getSession().getAttribute("code");
		// 拿到页面传递过来的信息
		String formCode = request.getParameter("code");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 将信息存到session对象中
		request.getSession().setAttribute("username", username);
//		System.out.println(username);
		
		// 进行数据验证
		if ("请输入学号或工号".equals(username)) {
			// 用户名为空
			request.getSession().setAttribute("error", "用户名不能为空");
			String error = (String) request.getSession().getAttribute("error");
			System.out.println(error);
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} else if ("请输入密码".equals(password)) {
			// 密码为空
			request.getSession().setAttribute("error", "密码不能为空");
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} else if ("请输入验证码".equals(formCode)) {
			// 验证码为空
			request.getSession().setAttribute("error", "验证码不能为空");
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} else {
			// 判断验证码是否正确
			if (formCode.equalsIgnoreCase(code)) {
				// 验证码正确
				// 判断登陆的用户类型
				String usertype = request.getParameter("usertype");
				if ("student".equals(usertype)) {
					// 调用service层完成业务逻辑
					Student student = ss.login(username, password);
					if (student != null) {
						// 合法用户，登陆成功
						request.getSession().setAttribute("loginUser", student);
						request.getSession().setAttribute("userType", "student");
						response.sendRedirect(request.getContextPath()
								+ "/main.jsp");
					} else {
						// 非法用户
						request.getSession().setAttribute("error", "用户名或密码错误");
						response.sendRedirect(request.getContextPath()
								+ "/login.jsp");
					}
				} else if ("teacher".equals(usertype)) {
					// 调用service层完成业务逻辑
					Teacher teacher = ts.login(username, password);
					if (teacher != null) {
						// 合法用户，登陆成功
						request.getSession().setAttribute("loginUser", teacher);
						request.getSession().setAttribute("userType", "teacher");
						response.sendRedirect(request.getContextPath()
								+ "/main.jsp");
					} else {
						// 非法用户
						request.getSession().setAttribute("error", "用户名或密码错误");
						response.sendRedirect(request.getContextPath()
								+ "/login.jsp");
					}
				} else if ("manager".equals(usertype)) {
					// 调用service层完成业务逻辑
					Manager manager = ms.login(username, password);
					if (manager != null) {
						// 合法用户，登陆成功
						request.getSession().setAttribute("loginUser", manager);
						request.getSession().setAttribute("userType", "manager");
						response.sendRedirect(request.getContextPath()
								+ "/main.jsp");
					} else {
						// 非法用户
						request.getSession().setAttribute("error", "用户名或密码错误");
						response.sendRedirect(request.getContextPath()
								+ "/login.jsp");
					}
				} else {
					// 将错误信息存入session对象中，并跳转向登陆页面
					request.getSession().setAttribute("error", "用户类型错误");
					response.sendRedirect(request.getContextPath()
							+ "/login.jsp");
				}

			} else {
				// 验证码错误
				// 将错误信息存入session对象中，并跳转向登陆页面
				request.getSession().setAttribute("error", "验证码输入错误");
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			}

		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}