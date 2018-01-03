<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-2.1.3.js"></script>
<script type="text/javascript" src="js/nav.js"></script>
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/nav.css" media="screen" type="text/css" />
<title>学生选课系统</title>
<style type="text/css">
	
	body{
		background-color: #fff;
		margin: 5px 0 0 0;
		background: url(images/body_bg.jpg) no-repeat;
	}
	
	body a{
		font-size:16px;
		text-decoration: none;
		color: black;
	}
	
	.header_left {
		display:block;
	}
	
	.header_right {
		filter:alpha(opacity=50);   
     	-moz-opacity:0.5;   
      	-khtml-opacity: 0.5;   
      	opacity: 0.5;
		text-align: right;
		background-color: #E9EAEF;
		padding-left: 7px;
		width: auto;
		float: right;
		color: black;
	}
	
	b {
		font-size:13px;
	}
	
	div.header_left a, div.header_right a, span {
		font-size:13px;
	}
	
	.left_nav {
		margin-top: -20px;
		margin-left: 10px;
		float:left;
		width: 180px;
	}
	
	.right {
		filter:alpha(opacity=50);   
     	-moz-opacity:0.5;   
      	-khtml-opacity: 0.5;   
      	opacity: 0.5;
		background-color: rgb(185, 227 ,217);
		margin-left: 190px;
		width: auto;
		height: 586px;
		border: 1px solid #E9EAEF;
	}
</style>
</head>
<script type="text/javascript">
	
	//回到首页
	function toMain(){
		window.location = "${pageContext.request.contextPath }/main.jsp";
	}
	
	//查询课程
	function listAllCourses(){
		var iframe = document.getElementByID("iframe");
		iframe.src = "${pageContext.request.contextPath }/Controller?op=listCourses";
		
	}
</script>
<body>
	<div class="header_left" >
		<img src="images/project_logo.png" width="250px" style="margin-left: 10px;margin-bottom: -15px;cursor: pointer" onclick="toMain()" title="回到主页">
	</div>
	<div class="header_right">
		<b>${sessionScope.userType == "student"?"学生": sessionScope.userType == "teacher"?"教师": sessionScope.userType == "manager"?"管理员":"未登录" }</b>
		<span>|</span>
		<b>${sessionScope.loginUser.name }</b>
		<span>|</span>
		<a href="${pageContext.request.contextPath }/main.jsp">回到首页</a>
		<span>|</span>
		<a href="${pageContext.request.contextPath }/Controller?op=quitLogin" style="padding-right: 5px">退出登陆</a>
	</div>
	<div style="margin-top: 20px">
		<div class="left_nav">
			<c:choose>
				<c:when test="${sessionScope.userType == 'student' }">
					<ul id="accordion" class="accordion">
						<li>
							<div class="link"><span style="margin-left: 18px">在线选课</span><i class="fa fa-chevron-down"></i></div>
							<ul class="submenu">
								<li><a href="${pageContext.request.contextPath }/Controller?op=listCourses" target="iframe"><span style="margin-left: 18px">查询课程</span></a></li>
								<li><a href="${pageContext.request.contextPath }/Controller?op=listSelectedCourses" target="iframe"><span style="margin-left: 18px">已选课程</span></a></li>
							</ul>
						</li>
						<li>
							<div class="link"><span style="margin-left: 18px">个人主页</span><i class="fa fa-chevron-down"></i></div>
							<ul class="submenu">
								<li><a href="${pageContext.request.contextPath }/Controller?op=showInfo" target="iframe"><span style="margin-left: 18px">个人信息</span></a></li>
								<li><a href="${pageContext.request.contextPath }/changePassword.jsp" target="iframe"><span style="margin-left: 18px">修改密码</span></a></li>
							</ul>
						</li>
						<li>
							<div class="link"><span style="margin-left: 18px">信息查询</span><i class="fa fa-chevron-down"></i></div>
							<ul class="submenu">
								<li><a href="${pageContext.request.contextPath }/Controller?op=showTimetable" target="iframe"><span style="margin-left: 18px">个人课表</span></a></li>
								<li><a href="${pageContext.request.contextPath }/Controller?op=showGrade" target="iframe"><span style="margin-left: 18px">成绩查询</span></a></li>
							</ul>
						</li>
					</ul>
				</c:when>
				<c:when test="${sessionScope.userType == 'teacher' }">
					<ul id="accordion" class="accordion">
						<li>
							<div class="link"><span style="margin-left: 18px">在线选课</span><i class="fa fa-chevron-down"></i></div>
							<ul class="submenu">
								<li><a href="${pageContext.request.contextPath }/Controller?op=listCourses" target="iframe"><span style="margin-left: 18px">查询课程</span></a></li>
								<li><a href="${pageContext.request.contextPath }/Controller?op=toAddCourse" target="iframe"><span style="margin-left: 18px">发布课程</span></a></li>
							</ul>
						</li>
						<li>
							<div class="link"><span style="margin-left: 18px">个人主页</span><i class="fa fa-chevron-down"></i></div>
							<ul class="submenu">
								<li><a href="${pageContext.request.contextPath }/Controller?op=showInfo" target="iframe"><span style="margin-left: 18px">个人信息</span></a></li>
								<li><a href="${pageContext.request.contextPath }/changePassword.jsp" target="iframe"><span style="margin-left: 18px">修改密码</span></a></li>
							</ul>
						</li>
						<li>
							<div class="link"><span style="margin-left: 18px">信息查询</span><i class="fa fa-chevron-down"></i></div>
							<ul class="submenu">
								<li><a href="${pageContext.request.contextPath }/Controller?op=showTimetable" target="iframe"><span style="margin-left: 18px">个人课表</span></a></li>
								<li><a href="${pageContext.request.contextPath }/Controller?op=currentCourses" target="iframe"><span style="margin-left: 18px">录入成绩</span></a></li>
							</ul>
						</li>
					</ul>
				</c:when>
				<c:when test="${sessionScope.userType == 'manager' }">
					<ul id="accordion" class="accordion">
						<li>
							<div class="link"><span style="margin-left: 18px">在线选课</span><i class="fa fa-chevron-down"></i></div>
							<ul class="submenu">
								<li><a href="${pageContext.request.contextPath }/Controller?op=listCourses" target="iframe"><span style="margin-left: 18px">删改课程</span></a></li>
								<li><a href="${pageContext.request.contextPath }/Controller?op=toExamineCourses" target="iframe"><span style="margin-left: 18px">审核课程</span></a></li>
								<li><a href="${pageContext.request.contextPath }/Controller?op=toPermitCourseSelection" target="iframe"><span style="margin-left: 18px">开放选课</span></a></li>
							</ul>
						</li>
						<li>
							<div class="link"><span style="margin-left: 18px">个人主页</span><i class="fa fa-chevron-down"></i></div>
							<ul class="submenu">
								<li><a href="${pageContext.request.contextPath }/Controller?op=showInfo" target="iframe"><span style="margin-left: 18px">个人信息</span></a></li>
								<li><a href="${pageContext.request.contextPath }/changePassword.jsp" target="iframe"><span style="margin-left: 18px">修改密码</span></a></li>
							</ul>
						</li>
						<li>
							<div class="link"><span style="margin-left: 18px">信息维护</span><i class="fa fa-chevron-down"></i></div>
							<ul class="submenu">
								<li><a href="${pageContext.request.contextPath }/Controller?op=toListPageStudents" target="iframe"><span style="margin-left: 18px">删改学生</span></a></li>
								<li><a href="${pageContext.request.contextPath }/Controller?op=toListPageTeachers" target="iframe"><span style="margin-left: 18px">删改教师</span></a></li>
								<li><a href="${pageContext.request.contextPath }/Controller?op=toAddStudent" target="iframe"><span style="margin-left: 18px">添加学生</span></a></li>
								<li><a href="${pageContext.request.contextPath }/Controller?op=toAddTeacher" target="iframe"><span style="margin-left: 18px">添加教师</span></a></li>
							</ul>
						</li>
					</ul>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</div>
		<div class="right">
			<iframe name="iframe" width="100%" height="100%" frameborder="0">这是个内联框架</iframe>
		</div>
	</div>
</body>
</html>