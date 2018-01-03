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
import com.lewky.bean.Teacher;
import com.lewky.dao.TeacherDao;
import com.lewky.utils.JdbcUtils;

public class TeacherDaoImpl implements TeacherDao {

	@Override
	public Teacher login(String teacherNum, String password) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Teacher teacher = null;
		try {
			// 先判断账号是否是有效账号
			pstmt = conn
					.prepareStatement("select status from teacher where teacherNum = ?");
			// 指定studentNum的值
			pstmt.setString(1, teacherNum);
			// 执行sql语句
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 账号无效，返回null
				if (!rs.getBoolean("status")) {
					return null;
				} else {
					// 账号有效，进行登录操作
					pstmt = conn
							.prepareStatement("select * from teacher where teacherNum = ? and password = ?");
					// 指定teacherNum的值
					pstmt.setString(1, teacherNum);
					// 指定password的值
					pstmt.setString(2, password);
					// 执行sql语句
					rs = pstmt.executeQuery();

					if (rs.next()) {
						// 找到了教师，封装数据
						teacher = new Teacher();

						teacher.setTeacherNum(rs.getString("teacherNum"));
						teacher.setDeptNum(rs.getString("deptNum"));
						teacher.setName(rs.getString("name"));
						teacher.setGender(rs.getString("gender"));
						teacher.setBirthday(rs.getDate("birthday"));
						teacher.setPassword(rs.getString("password"));
						teacher.setTeacherTitle(rs.getString("teacherTitle"));
					}
				}
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
	public boolean addCourse(Course course) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("insert into "
							+ "course_examination(courseNum, courseName, teacherNum, teacherName, schoolYear, semester, coursePeriod, courseCredit, description)"
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, pstmt, conn);
		}
		return n > 0 ? true : false;
	}

	@Override
	public Teacher showInfo(String teacherNum) {

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
				// 找到了教师，封装数据
				teacher = new Teacher();

				teacher.setTeacherNum(rs.getString("teacherNum"));
				teacher.setDeptNum(rs.getString("deptNum"));
				teacher.setDeptName(rs.getString("deptName"));
				teacher.setName(rs.getString("name"));
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
	public List<CourseSelection> showCourse(String courseNum,
			int currentPageIndex, int count) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseSelection> list = new ArrayList<CourseSelection>();
		CourseSelection courseSelection = null;
		try {
			pstmt = conn
					.prepareStatement("select * from course_selection where courseNum = ? order by studentNum limit ?, ?");
			// 指定?的值
			pstmt.setString(1, courseNum);
			pstmt.setInt(2, (currentPageIndex - 1) * count);
			pstmt.setInt(3, count);

			// 执行sql语句
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 封装数据
				courseSelection = new CourseSelection();

				try {
					// 课程号码可能有特殊符号，先进行编码再存入该对象中
					courseNum = URLEncoder.encode(rs.getString("courseNum"),
							"UTF-8");
					courseSelection.setCourseNum(courseNum);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				courseSelection.setCourseName(rs.getString("courseName"));
				courseSelection.setTeacherNum(rs.getString("teacherNum"));
				courseSelection.setTeacherName(rs.getString("teacherName"));
				courseSelection.setStudentName(rs.getString("studentName"));
				courseSelection.setRegularGrade(rs.getString("regularGrade"));
				courseSelection.setMidtermGrade(rs.getString("midtermGrade"));
				courseSelection.setFinalExamGrade(rs.getString("finalExamGrade"));
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
	public boolean changePassword(String teacherNum, String newPassword) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("update teacher set password = ? where teacherNum = ?");
			// 指定?的值
			pstmt.setString(1, newPassword);
			pstmt.setString(2, teacherNum);

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
	public boolean inputGrade(CourseSelection courseSelection) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("update course_selection set regularGrade=?, finalExamGrade=?, grade=? where courseNum = ? and studentNum = ?");
			// 指定?的值
			pstmt.setString(1, courseSelection.getRegularGrade());
			pstmt.setString(2, courseSelection.getFinalExamGrade());
			pstmt.setString(3, courseSelection.getGrade());
			pstmt.setString(4, courseSelection.getCourseNum());
			pstmt.setString(5, courseSelection.getStudentNum());

			// 执行sql语句
			n = pstmt.executeUpdate();
			
			//判断期中成绩是否为空，若不为空则录入数据库中
			if (!"".equals(courseSelection.getMidtermGrade())) {
				pstmt = conn
						.prepareStatement("update course_selection set midtermGrade=? where courseNum = ? and studentNum = ?");
				// 指定?的值
				pstmt.setString(1, courseSelection.getMidtermGrade());
				pstmt.setString(2, courseSelection.getCourseNum());
				pstmt.setString(3, courseSelection.getStudentNum());

				// 执行sql语句
				n = pstmt.executeUpdate();
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
	public List<Course> listPageCourses(String teacherNum,
			int currentPageIndex, int count, String schoolYear, String semester) {

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
				pstmt = conn
						.prepareStatement("select * from course where schoolYear = ? and semester = ? and teacherNum = ? order by courseNum limit ?, ?");
				// 指定?的值
				pstmt.setString(1, schoolYear);
				pstmt.setString(2, semester);
				pstmt.setString(3, teacherNum);
				pstmt.setInt(4, (currentPageIndex - 1) * count);
				pstmt.setInt(5, count);

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
					course.setSelectedNum(rs.getString("selectedNum"));
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
	public int showTotalDataCount(String schoolYear, String semester,
			String teacherNum) {

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
					.prepareStatement("select count(*) from course where schoolYear = ? and semester = ? and teacherNum = ?");
			// 指定?的值
			pstmt.setString(1, schoolYear);
			pstmt.setString(2, semester);
			pstmt.setString(3, teacherNum);

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
	public int showCourseSelectionCount(String courseNum) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement("select count(*) from course_selection where courseNum = ?");
			// 指定?的值
			pstmt.setString(1, courseNum);

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
	public CourseSelection showGrade(String courseNum, String studentNum) {

		// 拿到连接对象
		Connection conn = JdbcUtils.getConnection();
		// 创建预处理命令对象
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CourseSelection courseSelection = null;
		try {
			pstmt = conn
					.prepareStatement("select * from course_selection where courseNum = ? and studentNum = ?");
			// 指定?的值
			pstmt.setString(1, courseNum);
			pstmt.setString(2, studentNum);

			// 执行sql语句
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 封装数据
				courseSelection = new CourseSelection();

				try {
					// 课程号码可能有特殊符号，先进行编码再存入该对象中
					courseNum = URLEncoder.encode(rs.getString("courseNum"),
							"UTF-8");
					courseSelection.setCourseNum(courseNum);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				courseSelection.setCourseName(rs.getString("courseName"));
				courseSelection.setTeacherNum(rs.getString("teacherNum"));
				courseSelection.setTeacherName(rs.getString("teacherName"));
				courseSelection.setStudentName(rs.getString("studentName"));
				courseSelection.setRegularGrade(rs.getString("regularGrade"));
				courseSelection.setMidtermGrade(rs.getString("midtermGrade"));
				courseSelection.setFinalExamGrade(rs.getString("finalExamGrade"));
				courseSelection.setGrade(rs.getString("grade"));
				courseSelection.setStudentNum(rs.getString("studentNum"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.release(rs, pstmt, conn);
		}
		return courseSelection;
	}
	
	@Override
	public List<Course> showTimetable(String teacherNum, String schoolYear,
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
					.prepareStatement("select * from course where teacherNum = ? and schoolYear = ? and semester = ? order by courseNum");
			// 指定?的值
			pstmt.setString(1, teacherNum);
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
