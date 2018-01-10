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
import com.lewky.bean.CourseSelection;
import com.lewky.bean.Student;
import com.lewky.dao.StudentDao;
import com.lewky.utils.JdbcUtils;

public class StudentDaoImpl implements StudentDao {

	CourseSelection courseSelection = null;

	@Override
	public Student login(String studentNum, String password) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student student = null;
		try {
			// 先判断账号是否是有效账号
			pstmt = conn
					.prepareStatement("select status from student where studentNum = ?");
			// 指定studentNum的值
			pstmt.setString(1, studentNum);
			// 执行sql语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 账号无效，返回null
				if (!rs.getBoolean("status")) {
					return null;
				} else {
					// 账号有效，进行登录操作
					pstmt = conn
							.prepareStatement("select * from student where studentNum = ? and password = ?");
					// 指定studentNum的值
					pstmt.setString(1, studentNum);
					// 指定password的值
					pstmt.setString(2, password);
					// 执行sql语句
					rs = pstmt.executeQuery();

					if (rs.next()) {
						// 找到了学生，封装数据
						student = new Student();

						student.setStudentNum(rs.getString("studentNum"));
						student.setMajorNum(rs.getString("majorNum"));
						student.setClassNum(rs.getString("classNum"));
						student.setName(rs.getString("name"));
						student.setGender(rs.getString("gender"));
						student.setBirthday(rs.getDate("birthday"));
						student.setPassword(rs.getString("password"));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return student;
	}

	@Override
	public List<Course> listAllCourses() {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		String schoolYear = null;
		String semester = null;
		try {
			// 先判断是否已经开放选课
			boolean status = showStatus();
			// 如果当前为不可选课状态，返回null
			if (!status) {
				// System.out.println(rs.getBoolean("status"));
				// System.out.println("当前不可选课！");
			} else {
				// 当前为可选课状态
				pstmt = conn
						.prepareStatement("select course.* from course where schoolYear = ? and semester = ? and status = 1 order by courseNum");

				// 设置当前的学年和学期
				schoolYear = showSchoolYear();
				semester = showSemester();

				// 指定?的值
				pstmt.setString(1, schoolYear);
				pstmt.setString(2, semester);
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
					// course.setQuota(rs.getString("quota"));
					course.setDescription(rs.getString("description"));

					list.add(course);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public List<Course> listSelectedCourses(String studentNum) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		String schoolYear = null;
		String semester = null;
		try {
			pstmt = conn
					.prepareStatement("select course.* from course, course_selection "
							+ "where course_selection.courseNum = course.courseNum and course_selection.studentNum = ? and schoolYear = ? and semester = ? order by courseNum");
			// 设置当前的学年和学期
			schoolYear = showSchoolYear();
			semester = showSemester();

			// 指定?的值
			pstmt.setString(1, studentNum);
			pstmt.setString(2, schoolYear);
			pstmt.setString(3, semester);
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
				course.setTime(rs.getString("time"));
				course.setDescription(rs.getString("description"));

				list.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public boolean selectCourses(String studentNum, String[] courseNums) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String teacherNum = null;
		String teacherName = null;
		String studentName = null;
		String courseName = null;
		int selectedNum = 0;
		try {
			for (int i = 0; i < courseNums.length; i++) {
				String courseNum = courseNums[i];
				// 判断是否课程已经被学生选过了
				pstmt = conn
						.prepareStatement("select * from course_selection where courseNum = ? and studentNum = ?");
				// 指定?的值
				pstmt.setString(1, courseNum);
				pstmt.setString(2, studentNum);
				
				// 执行sql语句
				rs = pstmt.executeQuery();
				if (rs.next()) {
					// 说明该学生已经选过该课程了，跳过该课程
					continue;
				}

				// 找到教师工号、教师姓名、课程名称、已选人数
				pstmt = conn
						.prepareStatement("select teacherNum, teacherName, courseName, selectedNum from course where courseNum = ?");
				// 指定courseNum的值
				pstmt.setString(1, courseNum);
				// 执行sql语句
				rs = pstmt.executeQuery();
				if (rs.next()) {
					teacherNum = rs.getString("teacherNum");
					teacherName = rs.getString("teacherName");
					courseName = rs.getString("courseName");
					selectedNum = rs.getInt("selectedNum");
				}

				// 找到学生姓名
				pstmt = conn
						.prepareStatement("select name from student where studentNum = ?");
				// 指定studentNum的值
				pstmt.setString(1, studentNum);
				// 执行sql语句
				rs = pstmt.executeQuery();
				if (rs.next()) {
					studentName = rs.getString("name");
				}

				// 课程选择表中插入数据
				pstmt = conn
						.prepareStatement("insert into "
								+ "course_selection(studentNum, studentName, courseNum, courseName, teacherNum, teacherName)"
								+ "value(?,?,?,?,?,?)");
				// 指定?的值
				pstmt.setString(1, studentNum);
				pstmt.setString(2, studentName);
				pstmt.setString(3, courseNum);
				pstmt.setString(4, courseName);
				pstmt.setString(5, teacherNum);
				pstmt.setString(6, teacherName);

				// 执行sql语句
				n = pstmt.executeUpdate();

				// 将课程的selectedNum的值+1
				pstmt = conn
						.prepareStatement("update course set selectedNum = ? where courseNum = ?");
				// 指定?的值
				pstmt.setInt(1, selectedNum + 1);
				pstmt.setString(2, courseNum);

				// 执行sql语句
				n = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return n > 0 ? true : false;
	}

	@Override
	public boolean quitCourses(String studentNum, String[] courseNums) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int selectedNum = 0;
		try {
			// 将课程从数据库中删除
			for (int i = 0; i < courseNums.length; i++) {
				String courseNum = courseNums[i];
				pstmt = conn
						.prepareStatement("delete from course_selection where studentNum = ? and courseNum = ?");
				// 指定?的值
				pstmt.setString(1, studentNum);
				pstmt.setString(2, courseNum);

				// 执行sql语句
				n = pstmt.executeUpdate();

				// 找到已选人数
				pstmt = conn
						.prepareStatement("select selectedNum from course where courseNum = ?");
				// 指定courseNum的值
				pstmt.setString(1, courseNum);
				// 执行sql语句
				rs = pstmt.executeQuery();
				if (rs.next()) {
					selectedNum = rs.getInt("selectedNum");
				}

				// 将课程的selectedNum的值-1
				pstmt = conn
						.prepareStatement("update course set selectedNum = ? where courseNum = ?");
				// 指定?的值
				pstmt.setInt(1, selectedNum - 1);
				pstmt.setString(2, courseNum);

				// 执行sql语句
				n = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, pstmt, conn);
		}
		return n > 0 ? true : false;
	}

	@Override
	public Student showInfo(String studentNum) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return student;
	}

	@Override
	public boolean changePassword(String studentNum, String newPassword) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("update student set password = ? where studentNum = ?");
			// 指定?的值
			pstmt.setString(1, newPassword);
			pstmt.setString(2, studentNum);

			// 执行sql语句
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, pstmt, conn);
		}
		return n > 0 ? true : false;
	}

	@Override
	public List<CourseSelection> showGrade(String studentNum,
			int currentPageIndex, int count) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseSelection> list = new ArrayList<CourseSelection>();
		CourseSelection courseSelection = null;
		try {
			pstmt = conn
					.prepareStatement("select * from course_selection where studentNum = ? order by studentNum limit ?, ?");
			// 指定?的值
			pstmt.setString(1, studentNum);
			pstmt.setInt(2, (currentPageIndex - 1) * count);
			pstmt.setInt(3, count);

			// 执行sql语句
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 封装数据
				courseSelection = new CourseSelection();

				try {
					// 课程号码可能有特殊符号，先进行编码再存入该对象中
					String courseNum = URLEncoder.encode(
							rs.getString("courseNum"), "UTF-8");
					courseSelection.setCourseNum(courseNum);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				courseSelection.setCourseName(rs.getString("courseName"));
				courseSelection.setTeacherNum(rs.getString("teacherNum"));
				courseSelection.setTeacherName(rs.getString("teacherName"));
				courseSelection.setStudentName(rs.getString("studentName"));
				courseSelection.setRegularGrade(rs.getString("regularGrade"));
				courseSelection.setMidtermGrade(rs.getString("midtermGrade"));
				courseSelection.setFinalExamGrade(rs
						.getString("finalExamGrade"));
				courseSelection.setGrade(rs.getString("grade"));
				courseSelection.setStudentNum(rs.getString("studentNum"));

				list.add(courseSelection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public List<CourseSelection> showGrade(String studentNum,
			String schoolYear, String semester, int currentPageIndex, int count) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseSelection> list = new ArrayList<CourseSelection>();
		CourseSelection courseSelection = null;
		try {
			pstmt = conn
					.prepareStatement("select * from course_selection, course where studentNum = ? and schoolYear = ? and semester = ? and course_selection.courseNum = course.courseNum order by studentNum limit ?, ?");
			// 指定?的值
			pstmt.setString(1, studentNum);
			pstmt.setString(2, schoolYear);
			pstmt.setString(3, semester);
			pstmt.setInt(4, (currentPageIndex - 1) * count);
			pstmt.setInt(5, count);

			// 执行sql语句
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 封装数据
				courseSelection = new CourseSelection();

				try {
					// 课程号码可能有特殊符号，先进行编码再存入该对象中
					String courseNum = URLEncoder.encode(
							rs.getString("courseNum"), "UTF-8");
					courseSelection.setCourseNum(courseNum);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				courseSelection.setCourseName(rs.getString("courseName"));
				courseSelection.setTeacherNum(rs.getString("teacherNum"));
				courseSelection.setTeacherName(rs.getString("teacherName"));
				courseSelection.setStudentName(rs.getString("studentName"));
				courseSelection.setRegularGrade(rs.getString("regularGrade"));
				courseSelection.setMidtermGrade(rs.getString("midtermGrade"));
				courseSelection.setFinalExamGrade(rs
						.getString("finalExamGrade"));
				courseSelection.setGrade(rs.getString("grade"));
				courseSelection.setStudentNum(rs.getString("studentNum"));

				list.add(courseSelection);
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
			JdbcUtils.release(null, pstmt, conn);
		}
		return status;
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
	public List<Course> listPageCourses(int currentPageIndex, int count,
			String schoolYear, String semester) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		try {
			// 先判断是否已经开放选课
			boolean status = showStatus();
			// 如果当前为不可选课状态，返回null
			if (!status) {
				// System.out.println(rs.getBoolean("status"));
				// System.out.println("当前不可选课！");
			} else {
				// 当前为可选课状态
				// 按学年学期列出所有课程
				pstmt = conn
						.prepareStatement("select * from course where schoolYear = ? and semester = ? order by courseNum limit ?, ?");
				// 指定?的值
				pstmt.setString(1, schoolYear);
				pstmt.setString(2, semester);
				pstmt.setInt(3, (currentPageIndex - 1) * count);
				pstmt.setInt(4, count);

				// //列出所有的课程
				// pstmt = conn
				// .prepareStatement("select * from course order by courseNum limit ?, ?");
				// // 指定?的值
				// pstmt.setInt(1, (currentPageIndex - 1) * count);
				// pstmt.setInt(2, count);

				// 执行sql语句
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// 找到了课程，封装数据
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
			// 先判断是否已经开放选课
			boolean status = showStatus();
			// 如果当前为不可选课状态，返回null
			if (!status) {
				return 0;
			}

			pstmt = conn
					.prepareStatement("select count(*) from course where schoolYear = ? and semester = ?");
			// 指定?的值
			pstmt.setString(1, schoolYear);
			pstmt.setString(2, semester);

			// 执行sql语句
			rs = pstmt.executeQuery();

			if (rs.next()) { // 执行第一条记录
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
	public List<Course> listPageSelectedCourses(String studentNum,
			int currentPageIndex, int count) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		String schoolYear = null;
		String semester = null;
		try {
			// 先判断是否已经开放选课
			boolean status = showStatus();
			// 如果当前为不可选课状态，返回null
			if (!status) {
				// System.out.println(rs.getBoolean("status"));
				// System.out.println("当前不可选课！");
			} else {
				// 当前为可选课状态
				pstmt = conn
						.prepareStatement("select course.* from course, course_selection "
								+ "where course_selection.courseNum = course.courseNum and course_selection.studentNum = ? and schoolYear = ? and semester = ? order by courseNum limit ?, ?");

				// 设置当前的学年和学期
				schoolYear = showSchoolYear();
				semester = showSemester();

				// 指定?的值
				pstmt.setString(1, studentNum);
				pstmt.setString(2, schoolYear);
				pstmt.setString(3, semester);
				pstmt.setInt(4, (currentPageIndex - 1) * count);
				pstmt.setInt(5, count);

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
					course.setTime(rs.getString("time"));
					course.setDescription(rs.getString("description"));

					list.add(course);
				}
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
	public int showCourseCount(String studentNum, String schoolYear,
			String semester) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement("select count(*) from course_selection, course where studentNum = ? and schoolYear = ? and semester = ? and course.courseNum = course_selection.courseNum");
			// 指定?的值
			pstmt.setString(1, studentNum);
			pstmt.setString(2, schoolYear);
			pstmt.setString(3, semester);

			// 执行sql语句
			rs = pstmt.executeQuery();

			if (rs.next()) { // 执行第一条记录
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
	public int showCourseCount(String studentNum) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement("select count(*) from course_selection, course where studentNum = ? and course.courseNum = course_selection.courseNum");
			// 指定?的值
			pstmt.setString(1, studentNum);

			// 执行sql语句
			rs = pstmt.executeQuery();

			if (rs.next()) { // 执行第一条记录
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
	public List<Course> showTimetable(String studentNum, String schoolYear,
			String semester) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		try {
			// 当前为可选课状态
			pstmt = conn
					.prepareStatement("select course.* from course, course_selection "
							+ "where course_selection.courseNum = course.courseNum and course_selection.studentNum = ? and schoolYear = ? and semester = ? order by courseNum");
			// 指定?的值
			pstmt.setString(1, studentNum);
			pstmt.setString(2, schoolYear);
			pstmt.setString(3, semester);

			// 执行sql语句
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 找到了课程，封装数据
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
				course.setWeek(rs.getString("week"));
				course.setWeekday(rs.getString("weekday"));
				course.setPlace(rs.getString("place"));
				course.setTime(rs.getString("time"));
				course.setDescription(rs.getString("description"));

				if (!"".equals(course.getWeek())) {
					//只有已经安排了上课时间地点等的课程才放入list中
					list.add(course);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return list;
	}
}
