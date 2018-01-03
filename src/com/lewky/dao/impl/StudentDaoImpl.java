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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student student = null;
		try {
			// ���ж��˺��Ƿ�����Ч�˺�
			pstmt = conn
					.prepareStatement("select status from student where studentNum = ?");
			// ָ��studentNum��ֵ
			pstmt.setString(1, studentNum);
			// ִ��sql���
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// �˺���Ч������null
				if (!rs.getBoolean("status")) {
					return null;
				} else {
					// �˺���Ч�����е�¼����
					pstmt = conn
							.prepareStatement("select * from student where studentNum = ? and password = ?");
					// ָ��studentNum��ֵ
					pstmt.setString(1, studentNum);
					// ָ��password��ֵ
					pstmt.setString(2, password);
					// ִ��sql���
					rs = pstmt.executeQuery();

					if (rs.next()) {
						// �ҵ���ѧ������װ����
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		String schoolYear = null;
		String semester = null;
		try {
			// ���ж��Ƿ��Ѿ�����ѡ��
			boolean status = showStatus();
			// �����ǰΪ����ѡ��״̬������null
			if (!status) {
				// System.out.println(rs.getBoolean("status"));
				// System.out.println("��ǰ����ѡ�Σ�");
			} else {
				// ��ǰΪ��ѡ��״̬
				pstmt = conn
						.prepareStatement("select course.* from course where schoolYear = ? and semester = ? and status = 1 order by courseNum");

				// ���õ�ǰ��ѧ���ѧ��
				schoolYear = showSchoolYear();
				semester = showSemester();

				// ָ��?��ֵ
				pstmt.setString(1, schoolYear);
				pstmt.setString(2, semester);
				// ִ��sql���
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// �ҵ��˿γ̺ͽ�ʦ����װ����
					course = new Course();

					try {
						// �γ̺��������������ţ��Ƚ��б����ٴ���ÿγ̶�����
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
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
			// ���õ�ǰ��ѧ���ѧ��
			schoolYear = showSchoolYear();
			semester = showSemester();

			// ָ��?��ֵ
			pstmt.setString(1, studentNum);
			pstmt.setString(2, schoolYear);
			pstmt.setString(3, semester);
			// ִ��sql���
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// �ҵ��˿γ̺ͽ�ʦ����װ����
				course = new Course();

				try {
					// �γ̺��������������ţ��Ƚ��б����ٴ���ÿγ̶�����
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
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
				// �ж��Ƿ�γ��Ѿ���ѧ��ѡ����
				pstmt = conn
						.prepareStatement("select * from course_selection where courseNum = ? and studentNum = ?");
				// ָ��?��ֵ
				pstmt.setString(1, courseNum);
				pstmt.setString(2, studentNum);
				
				// ִ��sql���
				rs = pstmt.executeQuery();
				if (rs.next()) {
					// ˵����ѧ���Ѿ�ѡ���ÿγ��ˣ������ÿγ�
					continue;
				}

				// �ҵ���ʦ���š���ʦ�������γ����ơ���ѡ����
				pstmt = conn
						.prepareStatement("select teacherNum, teacherName, courseName, selectedNum from course where courseNum = ?");
				// ָ��courseNum��ֵ
				pstmt.setString(1, courseNum);
				// ִ��sql���
				rs = pstmt.executeQuery();
				if (rs.next()) {
					teacherNum = rs.getString("teacherNum");
					teacherName = rs.getString("teacherName");
					courseName = rs.getString("courseName");
					selectedNum = rs.getInt("selectedNum");
				}

				// �ҵ�ѧ������
				pstmt = conn
						.prepareStatement("select name from student where studentNum = ?");
				// ָ��studentNum��ֵ
				pstmt.setString(1, studentNum);
				// ִ��sql���
				rs = pstmt.executeQuery();
				if (rs.next()) {
					studentName = rs.getString("name");
				}

				// �γ�ѡ����в�������
				pstmt = conn
						.prepareStatement("insert into "
								+ "course_selection(studentNum, studentName, courseNum, courseName, teacherNum, teacherName)"
								+ "value(?,?,?,?,?,?)");
				// ָ��?��ֵ
				pstmt.setString(1, studentNum);
				pstmt.setString(2, studentName);
				pstmt.setString(3, courseNum);
				pstmt.setString(4, courseName);
				pstmt.setString(5, teacherNum);
				pstmt.setString(6, teacherName);

				// ִ��sql���
				n = pstmt.executeUpdate();

				// ���γ̵�selectedNum��ֵ+1
				pstmt = conn
						.prepareStatement("update course set selectedNum = ? where courseNum = ?");
				// ָ��?��ֵ
				pstmt.setInt(1, selectedNum + 1);
				pstmt.setString(2, courseNum);

				// ִ��sql���
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int selectedNum = 0;
		try {
			// ���γ̴����ݿ���ɾ��
			for (int i = 0; i < courseNums.length; i++) {
				String courseNum = courseNums[i];
				pstmt = conn
						.prepareStatement("delete from course_selection where studentNum = ? and courseNum = ?");
				// ָ��?��ֵ
				pstmt.setString(1, studentNum);
				pstmt.setString(2, courseNum);

				// ִ��sql���
				n = pstmt.executeUpdate();

				// �ҵ���ѡ����
				pstmt = conn
						.prepareStatement("select selectedNum from course where courseNum = ?");
				// ָ��courseNum��ֵ
				pstmt.setString(1, courseNum);
				// ִ��sql���
				rs = pstmt.executeQuery();
				if (rs.next()) {
					selectedNum = rs.getInt("selectedNum");
				}

				// ���γ̵�selectedNum��ֵ-1
				pstmt = conn
						.prepareStatement("update course set selectedNum = ? where courseNum = ?");
				// ָ��?��ֵ
				pstmt.setInt(1, selectedNum - 1);
				pstmt.setString(2, courseNum);

				// ִ��sql���
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student student = null;
		try {
			pstmt = conn
					.prepareStatement("select * from student where studentNum = ?");
			// ָ��studentNum��ֵ
			pstmt.setString(1, studentNum);
			// ִ��sql���
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// �ҵ���ѧ������װ����
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("update student set password = ? where studentNum = ?");
			// ָ��?��ֵ
			pstmt.setString(1, newPassword);
			pstmt.setString(2, studentNum);

			// ִ��sql���
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseSelection> list = new ArrayList<CourseSelection>();
		CourseSelection courseSelection = null;
		try {
			pstmt = conn
					.prepareStatement("select * from course_selection where studentNum = ? order by studentNum limit ?, ?");
			// ָ��?��ֵ
			pstmt.setString(1, studentNum);
			pstmt.setInt(2, (currentPageIndex - 1) * count);
			pstmt.setInt(3, count);

			// ִ��sql���
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ��װ����
				courseSelection = new CourseSelection();

				try {
					// �γ̺��������������ţ��Ƚ��б����ٴ���ö�����
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseSelection> list = new ArrayList<CourseSelection>();
		CourseSelection courseSelection = null;
		try {
			pstmt = conn
					.prepareStatement("select * from course_selection, course where studentNum = ? and schoolYear = ? and semester = ? and course_selection.courseNum = course.courseNum order by studentNum limit ?, ?");
			// ָ��?��ֵ
			pstmt.setString(1, studentNum);
			pstmt.setString(2, schoolYear);
			pstmt.setString(3, semester);
			pstmt.setInt(4, (currentPageIndex - 1) * count);
			pstmt.setInt(5, count);

			// ִ��sql���
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ��װ����
				courseSelection = new CourseSelection();

				try {
					// �γ̺��������������ţ��Ƚ��б����ٴ���ö�����
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean status = false;
		try {
			pstmt = conn
					.prepareStatement("select status from authority_management where id = 1");
			// ִ��sql���
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String semester = null;
		try {
			pstmt = conn
					.prepareStatement("select semester from authority_management where id = 1");
			// ִ��sql���
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String schoolYear = null;
		try {
			pstmt = conn
					.prepareStatement("select schoolYear from authority_management where id = 1");
			// ִ��sql���
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		try {
			// ���ж��Ƿ��Ѿ�����ѡ��
			boolean status = showStatus();
			// �����ǰΪ����ѡ��״̬������null
			if (!status) {
				// System.out.println(rs.getBoolean("status"));
				// System.out.println("��ǰ����ѡ�Σ�");
			} else {
				// ��ǰΪ��ѡ��״̬
				// ��ѧ��ѧ���г����пγ�
				pstmt = conn
						.prepareStatement("select * from course where schoolYear = ? and semester = ? order by courseNum limit ?, ?");
				// ָ��?��ֵ
				pstmt.setString(1, schoolYear);
				pstmt.setString(2, semester);
				pstmt.setInt(3, (currentPageIndex - 1) * count);
				pstmt.setInt(4, count);

				// //�г����еĿγ�
				// pstmt = conn
				// .prepareStatement("select * from course order by courseNum limit ?, ?");
				// // ָ��?��ֵ
				// pstmt.setInt(1, (currentPageIndex - 1) * count);
				// pstmt.setInt(2, count);

				// ִ��sql���
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// �ҵ��˿γ̣���װ����
					course = new Course();

					try {
						// �γ̺��������������ţ��Ƚ��б����ٴ���ÿγ̶�����
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// ���ж��Ƿ��Ѿ�����ѡ��
			boolean status = showStatus();
			// �����ǰΪ����ѡ��״̬������null
			if (!status) {
				return 0;
			}

			pstmt = conn
					.prepareStatement("select count(*) from course where schoolYear = ? and semester = ?");
			// ָ��?��ֵ
			pstmt.setString(1, schoolYear);
			pstmt.setString(2, semester);

			// ִ��sql���
			rs = pstmt.executeQuery();

			if (rs.next()) { // ִ�е�һ����¼
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		String schoolYear = null;
		String semester = null;
		try {
			// ���ж��Ƿ��Ѿ�����ѡ��
			boolean status = showStatus();
			// �����ǰΪ����ѡ��״̬������null
			if (!status) {
				// System.out.println(rs.getBoolean("status"));
				// System.out.println("��ǰ����ѡ�Σ�");
			} else {
				// ��ǰΪ��ѡ��״̬
				pstmt = conn
						.prepareStatement("select course.* from course, course_selection "
								+ "where course_selection.courseNum = course.courseNum and course_selection.studentNum = ? and schoolYear = ? and semester = ? order by courseNum limit ?, ?");

				// ���õ�ǰ��ѧ���ѧ��
				schoolYear = showSchoolYear();
				semester = showSemester();

				// ָ��?��ֵ
				pstmt.setString(1, studentNum);
				pstmt.setString(2, schoolYear);
				pstmt.setString(3, semester);
				pstmt.setInt(4, (currentPageIndex - 1) * count);
				pstmt.setInt(5, count);

				// ִ��sql���
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// �ҵ��˿γ̺ͽ�ʦ����װ����
					course = new Course();

					try {
						// �γ̺��������������ţ��Ƚ��б����ٴ���ÿγ̶�����
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement("select count(*) from course_selection, course where studentNum = ? and schoolYear = ? and semester = ? and course.courseNum = course_selection.courseNum");
			// ָ��?��ֵ
			pstmt.setString(1, studentNum);
			pstmt.setString(2, schoolYear);
			pstmt.setString(3, semester);

			// ִ��sql���
			rs = pstmt.executeQuery();

			if (rs.next()) { // ִ�е�һ����¼
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement("select count(*) from course_selection, course where studentNum = ? and course.courseNum = course_selection.courseNum");
			// ָ��?��ֵ
			pstmt.setString(1, studentNum);

			// ִ��sql���
			rs = pstmt.executeQuery();

			if (rs.next()) { // ִ�е�һ����¼
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		try {
			// ��ǰΪ��ѡ��״̬
			pstmt = conn
					.prepareStatement("select course.* from course, course_selection "
							+ "where course_selection.courseNum = course.courseNum and course_selection.studentNum = ? and schoolYear = ? and semester = ? order by courseNum");
			// ָ��?��ֵ
			pstmt.setString(1, studentNum);
			pstmt.setString(2, schoolYear);
			pstmt.setString(3, semester);

			// ִ��sql���
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// �ҵ��˿γ̣���װ����
				course = new Course();

				try {
					// �γ̺��������������ţ��Ƚ��б����ٴ���ÿγ̶�����
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
					//ֻ���Ѿ��������Ͽ�ʱ��ص�ȵĿγ̲ŷ���list��
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
