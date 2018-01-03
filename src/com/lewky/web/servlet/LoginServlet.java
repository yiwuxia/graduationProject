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
 * @version ����ʱ�䣺2017-4-1 ����7:11:26
 */
@WebServlet("/LoginServlet")
// ��ɵ�½�Ĺ���
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

		// �õ�session���code
		String code = (String) request.getSession().getAttribute("code");
		// �õ�ҳ�洫�ݹ�������Ϣ
		String formCode = request.getParameter("code");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// ����Ϣ�浽session������
		request.getSession().setAttribute("username", username);
//		System.out.println(username);
		
		// ����������֤
		if ("������ѧ�Ż򹤺�".equals(username)) {
			// �û���Ϊ��
			request.getSession().setAttribute("error", "�û�������Ϊ��");
			String error = (String) request.getSession().getAttribute("error");
			System.out.println(error);
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} else if ("����������".equals(password)) {
			// ����Ϊ��
			request.getSession().setAttribute("error", "���벻��Ϊ��");
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} else if ("��������֤��".equals(formCode)) {
			// ��֤��Ϊ��
			request.getSession().setAttribute("error", "��֤�벻��Ϊ��");
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} else {
			// �ж���֤���Ƿ���ȷ
			if (formCode.equalsIgnoreCase(code)) {
				// ��֤����ȷ
				// �жϵ�½���û�����
				String usertype = request.getParameter("usertype");
				if ("student".equals(usertype)) {
					// ����service�����ҵ���߼�
					Student student = ss.login(username, password);
					if (student != null) {
						// �Ϸ��û�����½�ɹ�
						request.getSession().setAttribute("loginUser", student);
						request.getSession().setAttribute("userType", "student");
						response.sendRedirect(request.getContextPath()
								+ "/main.jsp");
					} else {
						// �Ƿ��û�
						request.getSession().setAttribute("error", "�û������������");
						response.sendRedirect(request.getContextPath()
								+ "/login.jsp");
					}
				} else if ("teacher".equals(usertype)) {
					// ����service�����ҵ���߼�
					Teacher teacher = ts.login(username, password);
					if (teacher != null) {
						// �Ϸ��û�����½�ɹ�
						request.getSession().setAttribute("loginUser", teacher);
						request.getSession().setAttribute("userType", "teacher");
						response.sendRedirect(request.getContextPath()
								+ "/main.jsp");
					} else {
						// �Ƿ��û�
						request.getSession().setAttribute("error", "�û������������");
						response.sendRedirect(request.getContextPath()
								+ "/login.jsp");
					}
				} else if ("manager".equals(usertype)) {
					// ����service�����ҵ���߼�
					Manager manager = ms.login(username, password);
					if (manager != null) {
						// �Ϸ��û�����½�ɹ�
						request.getSession().setAttribute("loginUser", manager);
						request.getSession().setAttribute("userType", "manager");
						response.sendRedirect(request.getContextPath()
								+ "/main.jsp");
					} else {
						// �Ƿ��û�
						request.getSession().setAttribute("error", "�û������������");
						response.sendRedirect(request.getContextPath()
								+ "/login.jsp");
					}
				} else {
					// ��������Ϣ����session�����У�����ת���½ҳ��
					request.getSession().setAttribute("error", "�û����ʹ���");
					response.sendRedirect(request.getContextPath()
							+ "/login.jsp");
				}

			} else {
				// ��֤�����
				// ��������Ϣ����session�����У�����ת���½ҳ��
				request.getSession().setAttribute("error", "��֤���������");
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			}

		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}