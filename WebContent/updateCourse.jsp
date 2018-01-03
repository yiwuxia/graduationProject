<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改课程</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/laydate/laydate.js"></script>
<style type="text/css">
	#t1{
		width:1000px;
	}
	
</style>
</head>
<script>

	//修改一门课程
	function updateCourse(){
		var courseName = document.getElementsByName("courseName")[0].value;
		var coursePeriod = document.getElementsByName("coursePeriod")[0].value;
		var courseCredit = document.getElementsByName("courseCredit")[0].value;
		
		var courseNum = document.getElementsByName("courseNum")[0].value;
		if(courseName == ""){
			alert("课程名称不能为空！");
			return false;
		} else if(coursePeriod == ""){
			alert("学时不能为空！");
			return false;
		} else if(courseCredit == ""){
			alert("学分不能为空！");
			return false;
		} else{
			alert("课程修改成功！");
			document.getElementById("form").submit();
		}
	}
	
</script>
<body>
	<h2 align="center"><label><b>修改课程</b></label></h2>
	<form action="${pageContext.request.contextPath }/Controller?op=updateCourse" method="post" id="form">
		<table id="t1" style="margin-left: 20px;">
			<tbody>
				<c:choose>
					<c:when test="${sessionScope.userType == 'manager' }">
						<tr>
							<td align="right" width="50%">课程:</td>
							<td align="left"><input type="text" name="courseName" value="${sessionScope.course.courseName }" readonly style="color: gray"></td>
							<td><input type="hidden" name="courseNum" value="${sessionScope.course.courseNum }"></td>
							<td><input type="hidden" name="teacherNum" value="${sessionScope.course.teacherNum }"></td>
							<td><input type="hidden" name="teacherName" value="${sessionScope.course.teacherName }"></td>
						</tr>
						<tr>
							<td align="right">学年:</td>
							<td align="left"><input type="text" name="schoolYear" value="${sessionScope.course.schoolYear }" readonly style="color: gray"></td>
						</tr>
						<tr>
							<td align="right">学期:</td>
							<td align="left"><input type="text" name="semester" value="${sessionScope.course.semester }" readonly style="color: gray"></td>
						</tr>
						<tr>
							<td align="right">学时:</td>
							<td align="left"><input type="text" name="coursePeriod" style="width:40px;text-align: right;color: gray" value="${sessionScope.course.coursePeriod }" readonly><span>h</span></td>
						</tr>
						<tr>
							<td align="right">学分:</td>
							<td align="left"><input type="text" name="courseCredit" style="width:40px;text-align: right;color: gray" value="${sessionScope.course.courseCredit }" readonly><span>分</span></td>
						</tr>
						<tr>
							<td align="right">上课周次:</td>
							<td align="left"><input type="text" name="week" style="text-align: left" value="${sessionScope.course.week }"></td>
						</tr>
						<tr>
							<td align="right">上课日次:</td>
							<td align="left"><input type="text" name="weekday" style="text-align: left" value="${sessionScope.course.weekday }"></td>
						</tr>
						<tr>
							<td align="right">上课节次:</td>
							<td align="left"><input type="text" name="time" style="text-align: left" value="${sessionScope.course.time }"></td>
						</tr>
						<tr>
							<td align="right">上课地点:</td>
							<td align="left"><input type="text" name="place" style="text-align: left" value="${sessionScope.course.place }"></td>
						</tr>
						<tr>
							<td align="right">课程介绍:</td>
							<td align="left">
								<textarea rows="5" cols="30" name="description">${sessionScope.course.description }</textarea>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2"><input type="button" value="修改课程" style="margin-left: 120px"  onclick="updateCourse()"></td>
						</tr>
					</c:when>
					<c:when test="${sessionScope.userType == 'teacher' }">
						<tr>
							<td align="right" width="50%">课程:</td>
							<td align="left"><input type="text" name="courseName" value="${sessionScope.course.courseName }"></td>
							<td><input type="hidden" name="courseNum" value="${sessionScope.course.courseNum }"></td>
							<td><input type="hidden" name="teacherNum" value="${sessionScope.course.teacherNum }"></td>
							<td><input type="hidden" name="teacherName" value="${sessionScope.course.teacherName }"></td>
						</tr>
						<tr>
							<td align="right">学年:</td>
							<td align="left"><input type="text" name="schoolYear" value="${sessionScope.course.schoolYear }" readonly style="color: gray"></td>
						</tr>
						<tr>
							<td align="right">学期:</td>
							<td align="left"><input type="text" name="semester" value="${sessionScope.course.semester }" readonly style="color: gray"></td>
						</tr>
						<tr>
							<td align="right">学时:</td>
							<td align="left"><input type="text" name="coursePeriod" style="width:40px;text-align: right" value="${sessionScope.course.coursePeriod }"><span>h</span></td>
						</tr>
						<tr>
							<td align="right">学分:</td>
							<td align="left"><input type="text" name="courseCredit" style="width:40px;text-align: right" value="${sessionScope.course.courseCredit }"><span>分</span></td>
						</tr>
						<tr>
							<td align="right">上课周次:</td>
							<td align="left"><input type="text" name="week" style="text-align: left;color: gray" value="${sessionScope.course.week }" readonly></td>
						</tr>
						<tr>
							<td align="right">上课日次:</td>
							<td align="left"><input type="text" name="weekday" style="text-align: left;color: gray" value="${sessionScope.course.weekday }" readonly></td>
						</tr>
						<tr>
							<td align="right">上课节次:</td>
							<td align="left"><input type="text" name="time" style="text-align: left;color: gray" value="${sessionScope.course.time }" readonly></td>
						</tr>
						<tr>
							<td align="right">上课地点:</td>
							<td align="left"><input type="text" name="place" style="text-align: left;color: gray" value="${sessionScope.course.place }" readonly></td>
						</tr>
						<tr>
							<td align="right">课程介绍:</td>
							<td align="left">
								<textarea rows="5" cols="30" name="description">${sessionScope.course.description }</textarea>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2"><input type="button" value="修改课程" style="margin-left: 120px"  onclick="updateCourse()"></td>
						</tr>
					</c:when>
				</c:choose>
			</tbody>
		</table>
	</form>
</body>
</html>





