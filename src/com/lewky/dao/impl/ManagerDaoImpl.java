package com.lewky.dao.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.lewky.bean.Course;
import com.lewky.bean.Dept;
import com.lewky.bean.Major;
import com.lewky.bean.Manager;
import com.lewky.bean.Student;
import com.lewky.bean.Teacher;
import com.lewky.dao.ManagerDao;
import com.lewky.utils.JdbcUtils;

public class ManagerDaoImpl implements ManagerDao {

	@Override
	public Manager login(String managerNum, String password) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Manager manager = null;
		try {
			pstmt = conn
					.prepareStatement("select managerNum, name, gender, birthday, password "
							+ "from manager where managerNum = ? and password = ?");
			// 指定studentNum的值
			pstmt.setString(1, managerNum);
			// 指定password的值
			pstmt.setString(2, password);
			// 执行sql语句
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 找到了管理员，封装数据
				manager = new Manager();

				manager.setManagerNum(rs.getString("managerNum"));
				manager.setName(rs.getString("name"));
				manager.setGender(rs.getString("gender"));
				manager.setBirthday(rs.getDate("birthday"));
				manager.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return manager;
	}

	@Override
	public List<Student> listPageStudents(int currentPageIndex, int count,
			String majorName) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Student> list = new ArrayList<Student>();
		Student student = null;
		try {
			pstmt = conn
					.prepareStatement("select * from student where majorName = ? and status = 1 order by studentNum limit ?,?");
			//指定?的值
			pstmt.setString(1, majorName);
			pstmt.setInt(2, (currentPageIndex - 1)*count);
			pstmt.setInt(3, count);
			
			// 执行sql语句
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 找到了学生，封装数据
				student = new Student();

				student.setStudentNum(rs.getString("studentNum"));
				student.setDeptNum(rs.getString("deptNum"));
				student.setDeptName(rs.getString("deptName"));
				student.setMajorNum(rs.getString("majorNum"));
				student.setMajorName(rs.getString("majorName"));
				student.setGradeNum(rs.getString("gradeNum"));
				student.setClassNum(rs.getString("classNum"));
				student.setName(rs.getString("name"));
				student.setGender(rs.getString("gender"));
				student.setBirthday(rs.getDate("birthday"));
				student.setPassword(rs.getString("password"));

				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public List<Teacher> listPageTeachers(int currentPageIndex, int count,
			String deptName) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Teacher> list = new ArrayList<Teacher>();
		Teacher teacher = null;
		try {
			pstmt = conn
					.prepareStatement("select * from teacher where deptName = ? and status = 1 order by teacherNum limit ?,?");
			//指定?的值
			pstmt.setString(1, deptName);
			pstmt.setInt(2, (currentPageIndex - 1)*count);
			pstmt.setInt(3, count);
			
			// 执行sql语句
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 找到了教师，封装数据
				teacher = new Teacher();

				teacher.setTeacherNum(rs.getString("teacherNum"));
				teacher.setDeptNum(rs.getString("deptNum"));
				teacher.setDeptName(rs.getString("deptName"));
				teacher.setName(rs.getString("name"));
				teacher.setGender(rs.getString("gender"));
				teacher.setBirthday(rs.getDate("birthday"));
				teacher.setTeacherTitle(rs.getString("teacherTitle"));
				teacher.setEmail(rs.getString("email"));
				teacher.setCellphone(rs.getString("cellphone"));
				teacher.setPassword(rs.getString("password"));

				list.add(teacher);}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return list;
	}
	
	@Override
	public List<Course> listCourses() {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		try {
			pstmt = conn
					.prepareStatement("select * from course order by courseNum");
			// 执行sql语句
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 找到了课程和教师，封装数据
				course = new Course();

				try {
					// 课程号码可能有特殊符号，先进行编码再存入该课程对象中
					String courseNum = URLEncoder.encode(
							rs.getString("courseNum"), "UTF-8");
					course.setCourseNum(courseNum);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				course.setCourseName(rs.getString("courseName"));
				course.setTeacherNum(rs.getString("teacherNum"));
				course.setTeacherName(rs.getString("teacherName"));
				course.setSchoolYear(rs.getString("schoolYear"));
				course.setSemester(rs.getString("semester"));
				course.setCoursePeriod(rs.getString("coursePeriod"));
				course.setCourseCredit(rs.getString("courseCredit"));
				course.setPlace(rs.getString("place"));
				course.setWeek(rs.getString("week"));
				course.setWeekday(rs.getString("weekday"));
				course.setTime(rs.getString("time"));
				course.setDescription(rs.getString("description"));

				list.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public List<Course> listExaminedCourses() {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		try {
			pstmt = conn
					.prepareStatement("select * from course_examination order by courseNum");
			// 执行sql语句
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 找到了课程和教师，封装数据
				course = new Course();
				try {
					// 课程号码可能有特殊符号，先进行编码再存入该课程对象中
					String courseNum = URLEncoder.encode(
							rs.getString("courseNum"), "UTF-8");
					course.setCourseNum(courseNum);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				course.setCourseName(rs.getString("courseName"));
				course.setTeacherNum(rs.getString("teacherNum"));
				course.setTeacherName(rs.getString("teacherName"));
				course.setSchoolYear(rs.getString("schoolYear"));
				course.setSemester(rs.getString("semester"));
				course.setCoursePeriod(rs.getString("coursePeriod"));
				course.setCourseCredit(rs.getString("courseCredit"));
				course.setDescription(rs.getString("description"));

				list.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public boolean addStudent(Student student) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("select deptNum from dept where deptName = ?");
			// 指定?的值
			pstmt.setString(1, student.getDeptName());
			// 执行sql语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 找到了专业，将专业号码存入student对象中
				student.setDeptNum(rs.getString("deptNum"));
			}
			
			pstmt = conn
					.prepareStatement("select majorNum from major where majorName = ?");
			// 指定?的值
			pstmt.setString(1, student.getMajorName());
			// 执行sql语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 找到了专业，将专业号码存入student对象中
				student.setMajorNum(rs.getString("majorNum"));
			}

			//对班号进行处理
			int classNum = Integer.parseInt(student.getMajorNum())%100*100 + Integer.parseInt(student.getClassNum());
			
			if (student.getBirthday() != null) {
				pstmt = conn
						.prepareStatement("insert into "
								+ "student(studentNum, deptNum, deptName, majorNum, majorName, classNum, name, gender, birthday, password, gradeNum)"
								+ "value(?,?,?,?,?,?,?,?,?,?,?)");
				// 指定?的值
				pstmt.setString(1, student.getStudentNum());
				pstmt.setString(2, student.getDeptNum());
				pstmt.setString(3, student.getDeptName());
				pstmt.setString(4, student.getMajorNum());
				pstmt.setString(5, student.getMajorName());
				pstmt.setString(6, classNum + "");
				pstmt.setString(7, student.getName());
				pstmt.setString(8, student.getGender());
				pstmt.setDate(9, new java.sql.Date(student.getBirthday()
						.getTime()));
				pstmt.setString(10, student.getPassword());
				pstmt.setString(11, student.getGradeNum());
			} else {
				pstmt = conn
						.prepareStatement("insert into "
								+ "student(studentNum,  deptNum, deptName, majorNum, majorName, classNum, name, gender, password, gradeNum)"
								+ "value(?,?,?,?,?,?,?,?,?,?)");
				// 指定?的值
				pstmt.setString(1, student.getStudentNum());
				pstmt.setString(2, student.getDeptNum());
				pstmt.setString(3, student.getDeptName());
				pstmt.setString(4, student.getMajorNum());
				pstmt.setString(5, student.getMajorName());
				pstmt.setString(6, classNum + "");
				pstmt.setString(7, student.getName());
				pstmt.setString(8, student.getGender());
				pstmt.setString(9, student.getPassword());
				pstmt.setString(10, student.getGradeNum());
			}

			
			// 执行sql语句
			n = pstmt.executeUpdate();

			//再查询class表中是否存在相应的班号，如果不存在则将班号、级号、专业号码信息存入class表中
			pstmt = conn.prepareStatement("select * from class where classNum = ?");
			//指定?的值
			pstmt.setString(1, classNum + "");
			
			// 执行sql语句
			rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				pstmt = conn.prepareStatement("insert into class(classNum, majorNum, gradeNum) value(?,?,?)");
				// 指定?的值
				pstmt.setString(1, classNum + "");
				pstmt.setString(2, student.getMajorNum());
				pstmt.setString(3, student.getGradeNum());
				
				// 执行sql语句
				n = pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return n > 0 ? true : false;
	}

	@Override
	public boolean addTeacher(Teacher teacher) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("select deptNum from dept where deptName = ?");
			// 指定?的值
			pstmt.setString(1, teacher.getDeptName());
			// 执行sql语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 找到了系，将系号码存入teacher对象中
				teacher.setDeptNum(rs.getString("deptNum"));
			}

			if (teacher.getBirthday() != null) {
				pstmt = conn
						.prepareStatement("insert into "
								+ "teacher(teacherNum, deptNum, deptName, name, gender, birthday, password, teacherTitle, email, cellphone)"
								+ "value(?,?,?,?,?,?,?,?,?,?)");
				// 指定?的值
				pstmt.setString(1, teacher.getTeacherNum());
				pstmt.setString(2, teacher.getDeptNum());
				pstmt.setString(3, teacher.getDeptName());
				pstmt.setString(4, teacher.getName());
				pstmt.setString(5, teacher.getGender());
				pstmt.setDate(6, new java.sql.Date(teacher.getBirthday()
						.getTime()));
				pstmt.setString(7, teacher.getPassword());
				pstmt.setString(8, teacher.getTeacherTitle());
				pstmt.setString(9, teacher.getEmail());
				pstmt.setString(10, teacher.getCellphone());
			} else {
				pstmt = conn
						.prepareStatement("insert into "
								+ "teacher(teacherNum, deptNum, deptName, name, gender, password, teacherTitle, email, cellphone)"
								+ "value(?,?,?,?,?,?,?,?,?)");
				// 指定?的值
				pstmt.setString(1, teacher.getTeacherNum());
				pstmt.setString(2, teacher.getDeptNum());
				pstmt.setString(3, teacher.getDeptName());
				pstmt.setString(4, teacher.getName());
				pstmt.setString(5, teacher.getGender());
				pstmt.setString(6, teacher.getPassword());
				pstmt.setString(7, teacher.getTeacherTitle());
				pstmt.setString(8, teacher.getEmail());
				pstmt.setString(9, teacher.getCellphone());
			}

			// 执行sql语句
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, pstmt, conn);
		}
		return n > 0 ? true : false;
	}

	@Override
	public boolean examineCourses(String[] courseNums, String op) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Course course = null;
		try {
			for (int i = 0; i < courseNums.length; i++) {
				// 通过课程号码查询到要审核的课程
				pstmt = conn
						.prepareStatement("select course_examination.* from course_examination where courseNum = ?");
				// 指定courseNum的值
				pstmt.setString(1, courseNums[i]);
				// 执行sql语句
				rs = pstmt.executeQuery();

				if (rs.next()) {
					// 找到了课程，封装数据
					course = new Course();

					course.setCourseName(rs.getString("courseName"));
					course.setCourseNum(rs.getString("courseNum"));
					course.setTeacherNum(rs.getString("teacherNum"));
					course.setTeacherName(rs.getString("teacherName"));
					course.setSchoolYear(rs.getString("schoolYear"));
					course.setSemester(rs.getString("semester"));
					course.setCoursePeriod(rs.getString("coursePeriod"));
					course.setCourseCredit(rs.getString("courseCredit"));
					course.setDescription(rs.getString("description"));
				}

				// 无论进行哪种操作都要先将课程从审核表中先删去
				pstmt = conn
						.prepareStatement("delete from course_examination where courseNum = ?");
				// 指定courseNum的值
				pstmt.setString(1, courseNums[i]);
				// 执行sql语句
				n = pstmt.executeUpdate();

				// 判断该课程是否通过审核
				if (op.equals("pass")) {
					// 课程通过审核，存入数据库的course表中
					pstmt = conn
							.prepareStatement("insert into "
									+ "course(courseNum, courseName, teacherNum, teacherName, schoolYear, semester, coursePeriod, courseCredit, description)"
									+ "value(?,?,?,?,?,?,?,?,?)");
					// 指定?的值
					pstmt.setString(1, course.getCourseNum());
					pstmt.setString(2, course.getCourseName());
					pstmt.setString(3, course.getTeacherNum());
					pstmt.setString(4, course.getTeacherName());
					pstmt.setString(5, course.getSchoolYear());
					pstmt.setString(6, course.getSemester());
					pstmt.setString(7, course.getCoursePeriod());
					pstmt.setString(8, course.getCourseCredit());
					pstmt.setString(9, course.getDescription());

					// 执行sql语句
					n = pstmt.executeUpdate();
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, pstmt, conn);
		}
		return n > 0 ? true : false;
	}

	@Override
	public boolean deleteStudents(String[] studentNums) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 将学生的状态修改为无效账号
			for (int i = 0; i < studentNums.length; i++) {
				String studentNum = studentNums[i];
				pstmt = conn
						.prepareStatement("update student set status = 0 where studentNum = ?");
				// 指定studentNum的值
				pstmt.setString(1, studentNum);
				
				// 执行sql语句
				n = pstmt.executeUpdate();
				
				//将学生选课表里的相关记录也设为无效记录
				pstmt = conn.prepareStatement("update course_selection set status = 0 where studentNum = ?");
				// 指定studentNum的值
				pstmt.setString(1, studentNum);
				
				// 执行sql语句
				n = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return n > 0 ? true : false;
	}

	@Override
	public boolean deleteTeachers(String[] teacherNums) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 将教师的状态修改为无效账号
			for (int i = 0; i < teacherNums.length; i++) {
				String teacherNum = teacherNums[i];
				pstmt = conn
						.prepareStatement("update teacher set status = 0 where teacherNum = ?");
				// 指定teacherNum的值
				pstmt.setString(1, teacherNum);
				// 执行sql语句
				n = pstmt.executeUpdate();
				
				//将course表中教师相关的记录全设置为无效记录
				pstmt = conn
						.prepareStatement("update course set status = 0 where teacherNum = ?");
				// 指定teacherNum的值
				pstmt.setString(1, teacherNum);
				// 执行sql语句
				n = pstmt.executeUpdate();
				
				//将course_selection表中教师相关的记录全设置为无效记录
				pstmt = conn
						.prepareStatement("update course_selection set status = 0 where teacherNum = ?");
				// 指定teacherNum的值
				pstmt.setString(1, teacherNum);
				// 执行sql语句
				n = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return n > 0 ? true : false;
	}

	@Override
	public boolean deleteCourses(String[] courseNums) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 将课程从数据库中删除
			for (int i = 0; i < courseNums.length; i++) {
				String courseNum = courseNums[i];
				pstmt = conn
						.prepareStatement("delete from course_selection where courseNum = ?");
				// 指定courseNum的值
				pstmt.setString(1, courseNum);
				// 执行sql语句
				n = pstmt.executeUpdate();
				
				pstmt = conn
						.prepareStatement(" delete from course where courseNum = ?");
				// 指定courseNum的值
				pstmt.setString(1, courseNum);
				// 执行sql语句
				n = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return n > 0 ? true : false;
	}

	@Override
	public boolean updateCourse(Course course) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("update "
							+ "course set courseName=?, teacherNum=?, teacherName=?, schoolYear=?, semester=?, coursePeriod=?, courseCredit=?, time=?, place=?, description=?, week = ?, weekday = ? where courseNum = ?");
			// 指定?的值
			pstmt.setString(1, course.getCourseName());
			pstmt.setString(2, course.getTeacherNum());
			pstmt.setString(3, course.getTeacherName());
			pstmt.setString(4, course.getSchoolYear());
			pstmt.setString(5, course.getSemester());
			pstmt.setString(6, course.getCoursePeriod());
			pstmt.setString(7, course.getCourseCredit());
			pstmt.setString(8, course.getTime());
			pstmt.setString(9, course.getPlace());
			pstmt.setString(10, course.getDescription());
			pstmt.setString(11, course.getWeek());
			pstmt.setString(12, course.getWeekday());
			pstmt.setString(13, course.getCourseNum());

			// 执行sql语句
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, pstmt, conn);
		}
		return n > 0 ? true : false;
	}
	
	@Override
	public boolean updateStudent(Student student) {
		
		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			if (student.getBirthday() != null) {
				pstmt = conn
						.prepareStatement("update "
								+ "student set name=?, deptNum=?, deptName=?, majorNum=?, majorName=?, gradeNum=?, classNum=?, gender=?, birthday=?, password=? where studentNum = ?");
				// 指定?的值
				pstmt.setString(1, student.getName());
				pstmt.setString(2, student.getDeptNum());
				pstmt.setString(3, student.getDeptName());
				pstmt.setString(4, student.getMajorNum());
				pstmt.setString(5, student.getMajorName());
				pstmt.setString(6, student.getGradeNum());
				pstmt.setString(7, student.getClassNum());
				pstmt.setString(8, student.getGender());
				pstmt.setDate(9, new java.sql.Date(student.getBirthday()
						.getTime()));
				pstmt.setString(10, student.getPassword());
				pstmt.setString(11, student.getStudentNum());
			} else {
				pstmt = conn
						.prepareStatement("update "
								+ "student set name=?, deptNum=?, deptName=?, majorNum=?, majorName=?, gradeNum=?, classNum=?, gender=?, password=?, birthday=null where studentNum = ?");
				// 指定?的值
				pstmt.setString(1, student.getName());
				pstmt.setString(2, student.getDeptNum());
				pstmt.setString(3, student.getDeptName());
				pstmt.setString(4, student.getMajorNum());
				pstmt.setString(5, student.getMajorName());
				pstmt.setString(6, student.getGradeNum());
				pstmt.setString(7, student.getClassNum());
				pstmt.setString(8, student.getGender());
				pstmt.setString(9, student.getPassword());
				pstmt.setString(10, student.getStudentNum());
			}
			
			// 执行sql语句
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, pstmt, conn);
		}
		return n > 0 ? true : false;
	}
	
	@Override
	public boolean updateTeacher(Teacher teacher) {
		
		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			if (teacher.getBirthday() != null) {
				pstmt = conn
						.prepareStatement("update "
								+ "teacher set name=?, deptNum=?, deptName=?, gender=?, birthday=?, password=?, teacherTitle=?, email=?, cellphone=? where teacherNum = ?");
				// 指定?的值
				pstmt.setString(1, teacher.getName());
				pstmt.setString(2, teacher.getDeptNum());
				pstmt.setString(3, teacher.getDeptName());
				pstmt.setString(4, teacher.getGender());
				pstmt.setDate(5, new java.sql.Date(teacher.getBirthday()
						.getTime()));
				pstmt.setString(6, teacher.getPassword());
				pstmt.setString(7, teacher.getTeacherTitle());
				pstmt.setString(8, teacher.getEmail());
				pstmt.setString(9, teacher.getCellphone());
				pstmt.setString(10, teacher.getTeacherNum());
			} else {
				pstmt = conn
						.prepareStatement("update "
								+ "teacher set name=?, deptNum=?, deptName=?, gender=?, password=?, teacherTitle=?, email=?, cellphone=?, birthday=null where teacherNum = ?");
				// 指定?的值
				pstmt.setString(1, teacher.getName());
				pstmt.setString(2, teacher.getDeptNum());
				pstmt.setString(3, teacher.getDeptName());
				pstmt.setString(4, teacher.getGender());
				pstmt.setString(5, teacher.getPassword());
				pstmt.setString(6, teacher.getTeacherTitle());
				pstmt.setString(7, teacher.getEmail());
				pstmt.setString(8, teacher.getCellphone());
				pstmt.setString(9, teacher.getTeacherNum());
			}
			
			// 执行sql语句
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, pstmt, conn);
		}
		return n > 0 ? true : false;
	}

	@Override
	public Course findCourseByCourseNum(String courseNum) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course course = null;
		try {
			pstmt = conn
					.prepareStatement("select * from course where courseNum = ?");
			// 指定courseNum的值
			pstmt.setString(1, courseNum);
			// 执行sql语句
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 找到了课程，封装数据
				course = new Course();

				course.setCourseName(rs.getString("courseName"));
				course.setCourseNum(rs.getString("courseNum"));
				course.setTeacherNum(rs.getString("teacherNum"));
				course.setTeacherName(rs.getString("teacherName"));
				course.setSchoolYear(rs.getString("schoolYear"));
				course.setSemester(rs.getString("semester"));
				course.setCoursePeriod(rs.getString("coursePeriod"));
				course.setCourseCredit(rs.getString("courseCredit"));
				course.setTime(rs.getString("time"));
				course.setPlace(rs.getString("place"));
				course.setWeek(rs.getString("week"));
				course.setWeekday(rs.getString("weekday"));
				course.setDescription(rs.getString("description"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return course;
	}
	
	@Override
	public Student findStudentByStudentNum(String studentNum) {
		
		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student student = null;
		try {
			pstmt = conn
					.prepareStatement("select * from student where studentNum = ?");
			// 指定studentNum的值
			pstmt.setString(1, studentNum);
			// 执行sql语句
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// 找到了课程，封装数据
				student = new Student();
				
				student.setStudentNum(rs.getString("studentNum"));
				student.setName(rs.getString("name"));
				student.setDeptNum(rs.getString("deptNum"));
				student.setDeptName(rs.getString("deptName"));
				student.setMajorNum(rs.getString("majorNum"));
				student.setMajorName(rs.getString("majorName"));
				student.setGradeNum(rs.getString("gradeNum"));
				student.setClassNum(rs.getString("classNum"));
				student.setGender(rs.getString("gender"));
				student.setBirthday(rs.getDate("birthday"));
				student.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return student;
	}
	
	@Override
	public Teacher findTeacherByTeacherNum(String teacherNum) {
		
		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Teacher teacher = null;
		try {
			pstmt = conn
					.prepareStatement("select * from teacher where teacherNum = ?");
			// 指定teacherNum的值
			pstmt.setString(1, teacherNum);
			// 执行sql语句
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// 找到了课程，封装数据
				teacher = new Teacher();
				
				teacher.setTeacherNum(rs.getString("teacherNum"));
				teacher.setName(rs.getString("name"));
				teacher.setDeptNum(rs.getString("deptNum"));
				teacher.setDeptName(rs.getString("deptName"));
				teacher.setGender(rs.getString("gender"));
				teacher.setBirthday(rs.getDate("birthday"));
				teacher.setPassword(rs.getString("password"));
				teacher.setTeacherTitle(rs.getString("teacherTitle"));
				teacher.setEmail(rs.getString("email"));
				teacher.setCellphone(rs.getString("cellphone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return teacher;
	}

	@Override
	public boolean changePassword(String managerNum, String newPassword) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("update manager set password = ? where managerNum = ?");
			// 指定?的值
			pstmt.setString(1, newPassword);
			pstmt.setString(2, managerNum);

			// 执行sql语句
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, pstmt, conn);
		}
		return n > 0 ? true : false;
	}

	@Override
	public boolean showStatus() {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean status = false;
		try {
			pstmt = conn
					.prepareStatement("select status from authority_management where id = 1");
			// 执行sql语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				status = rs.getBoolean("status");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return status;
	}

	@Override
	public Boolean permitCourseSelection(String schoolYear, String semester,
			String status) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		boolean _status = false;
		try {
			// 先对status进行处理
			if ("1".equals(status)) {
				_status = true;
			} else {
				_status = false;
			}

			pstmt = conn
					.prepareStatement("update authority_management set schoolYear = ?, semester = ?, status = ? where id = 1");
			// 指定?的值
			pstmt.setString(1, schoolYear);
			pstmt.setString(2, semester);
			pstmt.setBoolean(3, _status);

			// 执行sql语句
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, pstmt, conn);
		}
		return n > 0 ? true : false;
	}

	@Override
	public String showSchoolYear() {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String schoolYear = null;
		try {
			pstmt = conn
					.prepareStatement("select schoolYear from authority_management where id = 1");
			// 执行sql语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				schoolYear = rs.getString("schoolYear");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return schoolYear;
	}

	@Override
	public String showSemester() {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String semester = null;
		try {
			pstmt = conn
					.prepareStatement("select semester from authority_management where id = 1");
			// 执行sql语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				semester = rs.getString("semester");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return semester;
	}
	
	@Override
	public List<Dept> showDept() {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Dept> list = new ArrayList<Dept>();
		try {
			pstmt = conn
					.prepareStatement("select deptName, deptNum from dept order by deptNum");
			// 执行sql语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//找到了数据，封装进dept里
				Dept dept = new Dept();
				dept.setDeptName(rs.getString("deptName"));
				dept.setDeptNum(rs.getString("deptNum"));
				
				list.add(dept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return list;
	}
	
	@Override
	public List<Major> showMajor() {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Major> list = new ArrayList<Major>();
		try {
			pstmt = conn
					.prepareStatement("select majorName from major order by majorNum");
			// 执行sql语句
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return list;
	}
	
	@Override
	public Manager showInfo(String managerNum) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Manager manager = null;
		try {
			pstmt = conn
					.prepareStatement("select * from manager where managerNum = ?");
			// 指定studentNum的值
			pstmt.setString(1, managerNum);
			// 执行sql语句
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 找到了管理员，封装数据
				manager = new Manager();

				manager.setManagerNum(rs.getString("managerNum"));
				manager.setName(rs.getString("name"));
				manager.setGender(rs.getString("gender"));
				manager.setBirthday(rs.getDate("birthday"));
				manager.setPassword(rs.getString("password"));
				manager.setEmail(rs.getString("email"));
				manager.setCellphone(rs.getString("cellphone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return manager;
	}
	
	@Override
	public List<Course> listPageCourses(int currentPageIndex, int count, String schoolYear, String semester) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		try {
			
			pstmt = conn
					.prepareStatement("select * from course where schoolYear = ? and semester = ? and status = 1 order by courseNum limit ?, ?");
			//指定?的值
			pstmt.setString(1, schoolYear);
			pstmt.setString(2, semester);
			pstmt.setInt(3, (currentPageIndex - 1)*count);
			pstmt.setInt(4, count);
			
			// 执行sql语句
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 找到了课程和教师，封装数据
				course = new Course();

				try {
					// 课程号码可能有特殊符号，先进行编码再存入该课程对象中
					String courseNum = URLEncoder.encode(
							rs.getString("courseNum"), "UTF-8");
					course.setCourseNum(courseNum);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				course.setCourseName(rs.getString("courseName"));
				course.setTeacherNum(rs.getString("teacherNum"));
				course.setTeacherName(rs.getString("teacherName"));
				course.setSchoolYear(rs.getString("schoolYear"));
				course.setSemester(rs.getString("semester"));
				course.setCoursePeriod(rs.getString("coursePeriod"));
				course.setCourseCredit(rs.getString("courseCredit"));
				course.setPlace(rs.getString("place"));
				course.setWeek(rs.getString("week"));
				course.setWeekday(rs.getString("weekday"));
				course.setTime(rs.getString("time"));
				course.setDescription(rs.getString("description"));
				course.setSelectedNum(rs.getString("selectedNum"));

				list.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return list;
	}
	
	@Override
	public int showTotalDataCount(String schoolYear, String semester) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement("select count(*) from course where schoolYear = ? and semester = ?");
			//指定?的值
			pstmt.setString(1, schoolYear);
			pstmt.setString(2, semester);
			
			// 执行sql语句
			rs = pstmt.executeQuery();

			if(rs.next()){	//执行第一条记录
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return 0;
	}
	
	@Override
	public int showExaminedCoursesCount() {
		
		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement("select count(*) from course_examination");
			
			// 执行sql语句
			rs = pstmt.executeQuery();
			
			if(rs.next()){	//执行第一条记录
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return 0;
	}
	
	@Override
	public String showNewTeacherNum() {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String newTeacherNum = null;
		try {
			pstmt = conn
					.prepareStatement("select max(teacherNum) from teacher");
			// 执行sql语句
			rs = pstmt.executeQuery();

			if(rs.next()){	//执行第一条记录
				newTeacherNum = rs.getInt(1) + 1 + "";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return newTeacherNum;
	}
	
	@Override
	public String showNewStudentNum() {
		
		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String newStudentNum = null;
		try {
			pstmt = conn
					.prepareStatement("select max(studentNum) from student");
			// 执行sql语句
			rs = pstmt.executeQuery();
			
			if(rs.next()){	//执行第一条记录
				newStudentNum = rs.getLong(1) + 1 + "";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return newStudentNum;
	}
	
	@Override
	public List<Course> listPageExaminedCourses(int currentPageIndex, int count) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		try {
			pstmt = conn
					.prepareStatement("select * from course_examination order by courseNum limit ?,?");
			//指定?的值
			pstmt.setInt(1, (currentPageIndex - 1)*count);
			pstmt.setInt(2, count);
			
			// 执行sql语句
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 找到了课程和教师，封装数据
				course = new Course();
				try {
					// 课程号码可能有特殊符号，先进行编码再存入该课程对象中
					String courseNum = URLEncoder.encode(
							rs.getString("courseNum"), "UTF-8");
					course.setCourseNum(courseNum);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				course.setCourseName(rs.getString("courseName"));
				course.setTeacherNum(rs.getString("teacherNum"));
				course.setTeacherName(rs.getString("teacherName"));
				course.setSchoolYear(rs.getString("schoolYear"));
				course.setSemester(rs.getString("semester"));
				course.setCoursePeriod(rs.getString("coursePeriod"));
				course.setCourseCredit(rs.getString("courseCredit"));
				course.setDescription(rs.getString("description"));

				list.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return list;
	}
	
	@Override
	public int showStudentsCount(String majorName) {
		
		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement("select count(*) from student where majorName = ?");
			//指定?的值
			pstmt.setString(1, majorName);
			
			// 执行sql语句
			rs = pstmt.executeQuery();
			
			if(rs.next()){	//执行第一条记录
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return 0;
	}
	
	@Override
	public int showTeachersCount(String deptName) {
		
		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement("select count(*) from teacher where deptName = ?");
			//指定?的值
			pstmt.setString(1, deptName);
			
			// 执行sql语句
			rs = pstmt.executeQuery();
			
			if(rs.next()){	//执行第一条记录
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return 0;
	}
}
