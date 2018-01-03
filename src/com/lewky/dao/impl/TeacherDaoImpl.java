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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Teacher teacher = null;
		try {
			// ���ж��˺��Ƿ�����Ч�˺�
			pstmt = conn
					.prepareStatement("select status from teacher where teacherNum = ?");
			// ָ��studentNum��ֵ
			pstmt.setString(1, teacherNum);
			// ִ��sql���
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// �˺���Ч������null
				if (!rs.getBoolean("status")) {
					return null;
				} else {
					// �˺���Ч�����е�¼����
					pstmt = conn
							.prepareStatement("select * from teacher where teacherNum = ? and password = ?");
					// ָ��teacherNum��ֵ
					pstmt.setString(1, teacherNum);
					// ָ��password��ֵ
					pstmt.setString(2, password);
					// ִ��sql���
					rs = pstmt.executeQuery();

					if (rs.next()) {
						// �ҵ��˽�ʦ����װ����
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("insert into "
							+ "course_examination(courseNum, courseName, teacherNum, teacherName, schoolYear, semester, coursePeriod, courseCredit, description)"
							+ "value(?,?,?,?,?,?,?,?,?)");
			// ָ��?��ֵ
			pstmt.setString(1, course.getCourseNum());
			pstmt.setString(2, course.getCourseName());
			pstmt.setString(3, course.getTeacherNum());
			pstmt.setString(4, course.getTeacherName());
			pstmt.setString(5, course.getSchoolYear());
			pstmt.setString(6, course.getSemester());
			pstmt.setString(7, course.getCoursePeriod());
			pstmt.setString(8, course.getCourseCredit());
			pstmt.setString(9, course.getDescription());

			// ִ��sql���
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Teacher teacher = null;
		try {
			pstmt = conn
					.prepareStatement("select * from teacher where teacherNum = ?");
			// ָ��teacherNum��ֵ
			pstmt.setString(1, teacherNum);
			// ִ��sql���
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// �ҵ��˽�ʦ����װ����
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseSelection> list = new ArrayList<CourseSelection>();
		CourseSelection courseSelection = null;
		try {
			pstmt = conn
					.prepareStatement("select * from course_selection where courseNum = ? order by studentNum limit ?, ?");
			// ָ��?��ֵ
			pstmt.setString(1, courseNum);
			pstmt.setInt(2, (currentPageIndex - 1) * count);
			pstmt.setInt(3, count);

			// ִ��sql���
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ��װ����
				courseSelection = new CourseSelection();

				try {
					// �γ̺��������������ţ��Ƚ��б����ٴ���ö�����
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("update teacher set password = ? where teacherNum = ?");
			// ָ��?��ֵ
			pstmt.setString(1, newPassword);
			pstmt.setString(2, teacherNum);

			// ִ��sql���
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("update course_selection set regularGrade=?, finalExamGrade=?, grade=? where courseNum = ? and studentNum = ?");
			// ָ��?��ֵ
			pstmt.setString(1, courseSelection.getRegularGrade());
			pstmt.setString(2, courseSelection.getFinalExamGrade());
			pstmt.setString(3, courseSelection.getGrade());
			pstmt.setString(4, courseSelection.getCourseNum());
			pstmt.setString(5, courseSelection.getStudentNum());

			// ִ��sql���
			n = pstmt.executeUpdate();
			
			//�ж����гɼ��Ƿ�Ϊ�գ�����Ϊ����¼�����ݿ���
			if (!"".equals(courseSelection.getMidtermGrade())) {
				pstmt = conn
						.prepareStatement("update course_selection set midtermGrade=? where courseNum = ? and studentNum = ?");
				// ָ��?��ֵ
				pstmt.setString(1, courseSelection.getMidtermGrade());
				pstmt.setString(2, courseSelection.getCourseNum());
				pstmt.setString(3, courseSelection.getStudentNum());

				// ִ��sql���
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
	public List<Course> listPageCourses(String teacherNum,
			int currentPageIndex, int count, String schoolYear, String semester) {

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
				pstmt = conn
						.prepareStatement("select * from course where schoolYear = ? and semester = ? and teacherNum = ? order by courseNum limit ?, ?");
				// ָ��?��ֵ
				pstmt.setString(1, schoolYear);
				pstmt.setString(2, semester);
				pstmt.setString(3, teacherNum);
				pstmt.setInt(4, (currentPageIndex - 1) * count);
				pstmt.setInt(5, count);

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
					.prepareStatement("select count(*) from course where schoolYear = ? and semester = ? and teacherNum = ?");
			// ָ��?��ֵ
			pstmt.setString(1, schoolYear);
			pstmt.setString(2, semester);
			pstmt.setString(3, teacherNum);

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
	public int showCourseSelectionCount(String courseNum) {

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement("select count(*) from course_selection where courseNum = ?");
			// ָ��?��ֵ
			pstmt.setString(1, courseNum);

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
	public CourseSelection showGrade(String courseNum, String studentNum) {

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CourseSelection courseSelection = null;
		try {
			pstmt = conn
					.prepareStatement("select * from course_selection where courseNum = ? and studentNum = ?");
			// ָ��?��ֵ
			pstmt.setString(1, courseNum);
			pstmt.setString(2, studentNum);

			// ִ��sql���
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// ��װ����
				courseSelection = new CourseSelection();

				try {
					// �γ̺��������������ţ��Ƚ��б����ٴ���ö�����
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
					.prepareStatement("select * from course where teacherNum = ? and schoolYear = ? and semester = ? order by courseNum");
			// ָ��?��ֵ
			pstmt.setString(1, teacherNum);
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
