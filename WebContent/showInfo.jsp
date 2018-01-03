<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/laydate/laydate.js"></script>
<style type="text/css">
	body {
		font-weight:bolder;
	}

	table {
		border: 1px solid black;
		border-collapse: collapse;
		margin-top: 35px;
	}
	
	tr, td {
		border: 1px solid black;
		text-align: center;
	}
</style>
</head>
<script type="text/javascript">
	
	//添加一个学生
	function addStudent(){
		var name = document.getElementsByName("name")[0].value;
		var studentNum = document.getElementsByName("studentNum")[0].value;
		if(name == ""){
			alert("姓名不能为空！");
			return false;
		} else if(studentNum == ""){
			alert("学号不能为空！");
			return false;
		}else{
			alert("添加学生成功！");
			document.getElementById("form").submit();
		}
	}
</script>
<body>
	<div style="width: 1000px;margin-left: 80px">
		<h2 align="center"><label><b>个人信息</b></label></h2>
	</div>
	<c:choose>
		<c:when test="${sessionScope.userType == 'student' }">
			<table style="margin-left: 160px;" width="850px" height="200px">
				<tbody>
					<tr>
						<td width="90px" height="40px">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</td>
						<td width="150px">${sessionScope.loginUser.name }</td>
						<td width="90px">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</td>
						<td width="150px">${sessionScope.loginUser.gender }</td>
						<td width="90px">民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族</td>
						<td width="150px"></td>
					</tr>
					<tr>
						<td width="90px" height="40px">出生日期</td>
						<td width="150px">${sessionScope.loginUser.birthday }</td>
						<td width="90px">政治面貌</td>
						<td width="150px"></td>
						<td width="90px">健康状况</td>
						<td width="150px"></td>
					</tr>
					<tr>
						<td width="90px" height="40px">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历</td>
						<td width="150px"></td>
						<td width="90px">生源地区</td>
						<td width="150px"></td>
						<td width="90px">入学时间</td>
						<td width="150px"></td>
					</tr>
					<tr>
						<td width="90px" height="40px">修业年限</td>
						<td width="150px"></td>
						<td width="90px">邮箱地址</td>
						<td width="150px"></td>
						<td width="90px">联系电话</td>
						<td width="150px"></td>
					</tr>
					<tr>
						<td width="90px" height="40px">邮政编码</td>
						<td width="150px"></td>
						<td width="90px">外语水平</td>
						<td width="150px"></td>
						<td width="90px">奖惩情况</td>
						<td width="150px"></td>
					</tr>
				</tbody>
			</table>
		</c:when>
		<c:when test="${sessionScope.userType == 'teacher' }">
			<table style="margin-left: 290px;" width="570px" height="200px">
				<tbody>
					<tr>
						<td width="90px" height="40px">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</td>
						<td width="150px">${sessionScope.loginUser.name }</td>
						<td width="90px">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</td>
						<td width="150px">${sessionScope.loginUser.gender }</td>
					</tr>
					<tr>
						<td width="90px" height="40px">民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族</td>
						<td width="150px"></td>
						<td width="90px">出生日期</td>
						<td width="150px">${sessionScope.loginUser.birthday }</td>
					</tr>
					<tr>
						<td width="90px" height="40px">政治面貌</td>
						<td width="150px"></td>
						<td width="90px">健康状况</td>
						<td width="150px"></td>
					</tr>
					<tr>
						<td width="90px" height="40px">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位</td>
						<td width="150px"></td>
						<td width="90px">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</td>
						<td width="150px">${sessionScope.loginUser.teacherTitle }</td>
					</tr>
					<tr>
						<td width="90px">邮箱地址</td>
						<td width="150px">${sessionScope.loginUser.email }</td>
						<td width="90px" height="40px">联系电话</td>
						<td width="150px">${sessionScope.loginUser.cellphone }</td>
					</tr>
				</tbody>
			</table>
		</c:when>
		<c:when test="${sessionScope.userType == 'manager' }">
			<table style="margin-left: 290px;" width="570px" height="160px">
				<tbody>
					<tr>
						<td width="90px" height="40px">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</td>
						<td width="150px">${sessionScope.loginUser.name }</td>
						<td width="90px">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</td>
						<td width="150px">${sessionScope.loginUser.gender }</td>
					</tr>
					<tr>
						<td width="90px" height="40px">民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族</td>
						<td width="150px"></td>
						<td width="90px">出生日期</td>
						<td width="150px">${sessionScope.loginUser.birthday }</td>
					</tr>
					<tr>
						<td width="90px" height="40px">政治面貌</td>
						<td width="150px"></td>
						<td width="90px">健康状况</td>
						<td width="150px"></td>
					</tr>
					<tr>
						<td width="90px">邮箱地址</td>
						<td width="150px">${sessionScope.loginUser.email }</td>
						<td width="90px" height="40px">联系电话</td>
						<td width="150px">${sessionScope.loginUser.cellphone }</td>
					</tr>
				</tbody>
			</table>
		</c:when>
	</c:choose>
</body>
</html>





