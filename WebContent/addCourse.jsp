<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布课程</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/laydate/laydate.js"></script>
<style type="text/css">
	body {
		font-weight:bolder;
	}
	#t1{
		width:1000px;
	}
	
	span {
		color: red;
	}
</style>
</head>
<script>

	//发布一门课程
	function addCourse(){
		var courseName = document.getElementsByName("courseName")[0].value;
		var coursePeriod = document.getElementsByName("coursePeriod")[0].value;
		var courseCredit = document.getElementsByName("courseCredit")[0].value;
		if(courseName == ""){
			alert("课程名称不能为空！");
			return false;
		} else if(coursePeriod == ""){
			alert("学时不能为空！");
			return false;
		} else if(courseCredit == ""){
			alert("学分不能为空！");
			return false;
		} else if(isNaN(coursePeriod)){
			alert("学时必须是正数！");
			document.getElementsByName("coursePeriod")[0].value = "";
			return false;
		} else if(isNaN(courseCredit)){
			alert("学分必须是正数！");
			document.getElementsByName("courseCredit")[0].value = "";
			return false;
		} else if(Number(coursePeriod) > 100){
			alert("学时不能超过100h！");
			document.getElementsByName("coursePeriod")[0].value = "";
			return false;
		} else if(Number(courseCredit) > 6){
			alert("学分不能超过5.5分！");
			document.getElementsByName("courseCredit")[0].value = "";
			return false;
		}else{
			alert("课程发布成功，请等待管理员审核！");
			document.getElementById("form").submit();
		}
	}
</script>
<body>
	<c:choose>
		<c:when test="${sessionScope.status == 'true' }">
			<div style="width: 1000px;margin-left: 80px">
				<h2 align="center"><label><b>发布课程</b></label></h2>
			</div>
			<form action="${pageContext.request.contextPath }/Controller?op=addCourse" method="post" id="form">
				<table id="t1" style="margin-left: 20px;">
					<tbody>
						<tr>
							<td align="right" width="50%">课程:</td>
							<td align="left"><input type="text" name="courseName"></td>
							<td align="left"><input type="hidden" name="teacherNum" value="${sessionScope.loginUser.teacherNum }"></td>
							<td align="left"><input type="hidden" name="teacherName" value="${sessionScope.loginUser.name }"></td>
						</tr>
						<tr>
							<td align="right">学年:</td>
							<td align="left"><input type="text" name="schoolYear" value="${sessionScope.schoolYear }" style="color:gray" readonly></td>
						</tr>
						<tr>
							<td align="right">学期:</td>
							<td align="left"><input type="text" name="semester" value="${sessionScope.semester }" style="color:gray" readonly></td>
						</tr>
						<tr>
							<td align="right">学时:</td>
							<td align="left"><input type="text" name="coursePeriod" style="width:40px;text-align: right">h</td>
						</tr>
						<tr>
							<td align="right">学分:</td>
							<td align="left"><input type="text" name="courseCredit" style="width:40px;text-align: right">分</td>
						</tr>
						<tr>
							<td align="right">课程介绍:</td>
							<td align="left">
								<textarea rows="5" cols="20" name="description"></textarea>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2"><input type="button" value="发布课程" style="margin-left: 120px" onclick="addCourse()"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</c:when>
		<c:otherwise>
			<h3 align="center" style="margin-top: 50px"><span>当前不是选课时段，暂时不能发布课程！</span></h3>
		</c:otherwise>
	</c:choose>
</body>
</html>





