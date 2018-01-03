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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Manager manager = null;
		try {
			pstmt = conn
					.prepareStatement("select managerNum, name, gender, birthday, password "
							+ "from manager where managerNum = ? and password = ?");
			// ָ��studentNum��ֵ
			pstmt.setString(1, managerNum);
			// ָ��password��ֵ
			pstmt.setString(2, password);
			// ִ��sql���
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// �ҵ��˹���Ա����װ����
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Student> list = new ArrayList<Student>();
		Student student = null;
		try {
			pstmt = conn
					.prepareStatement("select * from student where majorName = ? and status = 1 order by studentNum limit ?,?");
			//ָ��?��ֵ
			pstmt.setString(1, majorName);
			pstmt.setInt(2, (currentPageIndex - 1)*count);
			pstmt.setInt(3, count);
			
			// ִ��sql���
			rs = pstmt.executeQuery();

			while (rs.next()) {
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Teacher> list = new ArrayList<Teacher>();
		Teacher teacher = null;
		try {
			pstmt = conn
					.prepareStatement("select * from teacher where deptName = ? and status = 1 order by teacherNum limit ?,?");
			//ָ��?��ֵ
			pstmt.setString(1, deptName);
			pstmt.setInt(2, (currentPageIndex - 1)*count);
			pstmt.setInt(3, count);
			
			// ִ��sql���
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// �ҵ��˽�ʦ����װ����
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		try {
			pstmt = conn
					.prepareStatement("select * from course order by courseNum");
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		try {
			pstmt = conn
					.prepareStatement("select * from course_examination order by courseNum");
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("select deptNum from dept where deptName = ?");
			// ָ��?��ֵ
			pstmt.setString(1, student.getDeptName());
			// ִ��sql���
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// �ҵ���רҵ����רҵ�������student������
				student.setDeptNum(rs.getString("deptNum"));
			}
			
			pstmt = conn
					.prepareStatement("select majorNum from major where majorName = ?");
			// ָ��?��ֵ
			pstmt.setString(1, student.getMajorName());
			// ִ��sql���
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// �ҵ���רҵ����רҵ�������student������
				student.setMajorNum(rs.getString("majorNum"));
			}

			//�԰�Ž��д���
			int classNum = Integer.parseInt(student.getMajorNum())%100*100 + Integer.parseInt(student.getClassNum());
			
			if (student.getBirthday() != null) {
				pstmt = conn
						.prepareStatement("insert into "
								+ "student(studentNum, deptNum, deptName, majorNum, majorName, classNum, name, gender, birthday, password, gradeNum)"
								+ "value(?,?,?,?,?,?,?,?,?,?,?)");
				// ָ��?��ֵ
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
				// ָ��?��ֵ
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

			
			// ִ��sql���
			n = pstmt.executeUpdate();

			//�ٲ�ѯclass�����Ƿ������Ӧ�İ�ţ�����������򽫰�š����š�רҵ������Ϣ����class����
			pstmt = conn.prepareStatement("select * from class where classNum = ?");
			//ָ��?��ֵ
			pstmt.setString(1, classNum + "");
			
			// ִ��sql���
			rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				pstmt = conn.prepareStatement("insert into class(classNum, majorNum, gradeNum) value(?,?,?)");
				// ָ��?��ֵ
				pstmt.setString(1, classNum + "");
				pstmt.setString(2, student.getMajorNum());
				pstmt.setString(3, student.getGradeNum());
				
				// ִ��sql���
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("select deptNum from dept where deptName = ?");
			// ָ��?��ֵ
			pstmt.setString(1, teacher.getDeptName());
			// ִ��sql���
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// �ҵ���ϵ����ϵ�������teacher������
				teacher.setDeptNum(rs.getString("deptNum"));
			}

			if (teacher.getBirthday() != null) {
				pstmt = conn
						.prepareStatement("insert into "
								+ "teacher(teacherNum, deptNum, deptName, name, gender, birthday, password, teacherTitle, email, cellphone)"
								+ "value(?,?,?,?,?,?,?,?,?,?)");
				// ָ��?��ֵ
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
				// ָ��?��ֵ
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
	public boolean examineCourses(String[] courseNums, String op) {

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Course course = null;
		try {
			for (int i = 0; i < courseNums.length; i++) {
				// ͨ���γ̺����ѯ��Ҫ��˵Ŀγ�
				pstmt = conn
						.prepareStatement("select course_examination.* from course_examination where courseNum = ?");
				// ָ��courseNum��ֵ
				pstmt.setString(1, courseNums[i]);
				// ִ��sql���
				rs = pstmt.executeQuery();

				if (rs.next()) {
					// �ҵ��˿γ̣���װ����
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

				// ���۽������ֲ�����Ҫ�Ƚ��γ̴���˱�����ɾȥ
				pstmt = conn
						.prepareStatement("delete from course_examination where courseNum = ?");
				// ָ��courseNum��ֵ
				pstmt.setString(1, courseNums[i]);
				// ִ��sql���
				n = pstmt.executeUpdate();

				// �жϸÿγ��Ƿ�ͨ�����
				if (op.equals("pass")) {
					// �γ�ͨ����ˣ��������ݿ��course����
					pstmt = conn
							.prepareStatement("insert into "
									+ "course(courseNum, courseName, teacherNum, teacherName, schoolYear, semester, coursePeriod, courseCredit, description)"
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// ��ѧ����״̬�޸�Ϊ��Ч�˺�
			for (int i = 0; i < studentNums.length; i++) {
				String studentNum = studentNums[i];
				pstmt = conn
						.prepareStatement("update student set status = 0 where studentNum = ?");
				// ָ��studentNum��ֵ
				pstmt.setString(1, studentNum);
				
				// ִ��sql���
				n = pstmt.executeUpdate();
				
				//��ѧ��ѡ�α������ؼ�¼Ҳ��Ϊ��Ч��¼
				pstmt = conn.prepareStatement("update course_selection set status = 0 where studentNum = ?");
				// ָ��studentNum��ֵ
				pstmt.setString(1, studentNum);
				
				// ִ��sql���
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// ����ʦ��״̬�޸�Ϊ��Ч�˺�
			for (int i = 0; i < teacherNums.length; i++) {
				String teacherNum = teacherNums[i];
				pstmt = conn
						.prepareStatement("update teacher set status = 0 where teacherNum = ?");
				// ָ��teacherNum��ֵ
				pstmt.setString(1, teacherNum);
				// ִ��sql���
				n = pstmt.executeUpdate();
				
				//��course���н�ʦ��صļ�¼ȫ����Ϊ��Ч��¼
				pstmt = conn
						.prepareStatement("update course set status = 0 where teacherNum = ?");
				// ָ��teacherNum��ֵ
				pstmt.setString(1, teacherNum);
				// ִ��sql���
				n = pstmt.executeUpdate();
				
				//��course_selection���н�ʦ��صļ�¼ȫ����Ϊ��Ч��¼
				pstmt = conn
						.prepareStatement("update course_selection set status = 0 where teacherNum = ?");
				// ָ��teacherNum��ֵ
				pstmt.setString(1, teacherNum);
				// ִ��sql���
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// ���γ̴����ݿ���ɾ��
			for (int i = 0; i < courseNums.length; i++) {
				String courseNum = courseNums[i];
				pstmt = conn
						.prepareStatement("delete from course_selection where courseNum = ?");
				// ָ��courseNum��ֵ
				pstmt.setString(1, courseNum);
				// ִ��sql���
				n = pstmt.executeUpdate();
				
				pstmt = conn
						.prepareStatement(" delete from course where courseNum = ?");
				// ָ��courseNum��ֵ
				pstmt.setString(1, courseNum);
				// ִ��sql���
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("update "
							+ "course set courseName=?, teacherNum=?, teacherName=?, schoolYear=?, semester=?, coursePeriod=?, courseCredit=?, time=?, place=?, description=?, week = ?, weekday = ? where courseNum = ?");
			// ָ��?��ֵ
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
	public boolean updateStudent(Student student) {
		
		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			if (student.getBirthday() != null) {
				pstmt = conn
						.prepareStatement("update "
								+ "student set name=?, deptNum=?, deptName=?, majorNum=?, majorName=?, gradeNum=?, classNum=?, gender=?, birthday=?, password=? where studentNum = ?");
				// ָ��?��ֵ
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
				// ָ��?��ֵ
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
	public boolean updateTeacher(Teacher teacher) {
		
		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			if (teacher.getBirthday() != null) {
				pstmt = conn
						.prepareStatement("update "
								+ "teacher set name=?, deptNum=?, deptName=?, gender=?, birthday=?, password=?, teacherTitle=?, email=?, cellphone=? where teacherNum = ?");
				// ָ��?��ֵ
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
				// ָ��?��ֵ
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
	public Course findCourseByCourseNum(String courseNum) {

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course course = null;
		try {
			pstmt = conn
					.prepareStatement("select * from course where courseNum = ?");
			// ָ��courseNum��ֵ
			pstmt.setString(1, courseNum);
			// ִ��sql���
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// �ҵ��˿γ̣���װ����
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
		
		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
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
				// �ҵ��˿γ̣���װ����
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
				// �ҵ��˿γ̣���װ����
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn
					.prepareStatement("update manager set password = ? where managerNum = ?");
			// ָ��?��ֵ
			pstmt.setString(1, newPassword);
			pstmt.setString(2, managerNum);

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
			JdbcUtils.release(rs, pstmt, conn);
		}
		return status;
	}

	@Override
	public Boolean permitCourseSelection(String schoolYear, String semester,
			String status) {

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		boolean _status = false;
		try {
			// �ȶ�status���д���
			if ("1".equals(status)) {
				_status = true;
			} else {
				_status = false;
			}

			pstmt = conn
					.prepareStatement("update authority_management set schoolYear = ?, semester = ?, status = ? where id = 1");
			// ָ��?��ֵ
			pstmt.setString(1, schoolYear);
			pstmt.setString(2, semester);
			pstmt.setBoolean(3, _status);

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
	public List<Dept> showDept() {

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Dept> list = new ArrayList<Dept>();
		try {
			pstmt = conn
					.prepareStatement("select deptName, deptNum from dept order by deptNum");
			// ִ��sql���
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//�ҵ������ݣ���װ��dept��
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Major> list = new ArrayList<Major>();
		try {
			pstmt = conn
					.prepareStatement("select majorName from major order by majorNum");
			// ִ��sql���
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Manager manager = null;
		try {
			pstmt = conn
					.prepareStatement("select * from manager where managerNum = ?");
			// ָ��studentNum��ֵ
			pstmt.setString(1, managerNum);
			// ִ��sql���
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// �ҵ��˹���Ա����װ����
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		try {
			
			pstmt = conn
					.prepareStatement("select * from course where schoolYear = ? and semester = ? and status = 1 order by courseNum limit ?, ?");
			//ָ��?��ֵ
			pstmt.setString(1, schoolYear);
			pstmt.setString(2, semester);
			pstmt.setInt(3, (currentPageIndex - 1)*count);
			pstmt.setInt(4, count);
			
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement("select count(*) from course where schoolYear = ? and semester = ?");
			//ָ��?��ֵ
			pstmt.setString(1, schoolYear);
			pstmt.setString(2, semester);
			
			// ִ��sql���
			rs = pstmt.executeQuery();

			if(rs.next()){	//ִ�е�һ����¼
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
		
		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement("select count(*) from course_examination");
			
			// ִ��sql���
			rs = pstmt.executeQuery();
			
			if(rs.next()){	//ִ�е�һ����¼
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String newTeacherNum = null;
		try {
			pstmt = conn
					.prepareStatement("select max(teacherNum) from teacher");
			// ִ��sql���
			rs = pstmt.executeQuery();

			if(rs.next()){	//ִ�е�һ����¼
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
		
		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String newStudentNum = null;
		try {
			pstmt = conn
					.prepareStatement("select max(studentNum) from student");
			// ִ��sql���
			rs = pstmt.executeQuery();
			
			if(rs.next()){	//ִ�е�һ����¼
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

		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		int n = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		try {
			pstmt = conn
					.prepareStatement("select * from course_examination order by courseNum limit ?,?");
			//ָ��?��ֵ
			pstmt.setInt(1, (currentPageIndex - 1)*count);
			pstmt.setInt(2, count);
			
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
		
		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement("select count(*) from student where majorName = ?");
			//ָ��?��ֵ
			pstmt.setString(1, majorName);
			
			// ִ��sql���
			rs = pstmt.executeQuery();
			
			if(rs.next()){	//ִ�е�һ����¼
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
		
		// �õ����Ӷ���
		Connection conn = JdbcUtils.getConnection();
		// ����Ԥ�����������
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement("select count(*) from teacher where deptName = ?");
			//ָ��?��ֵ
			pstmt.setString(1, deptName);
			
			// ִ��sql���
			rs = pstmt.executeQuery();
			
			if(rs.next()){	//ִ�е�һ����¼
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
