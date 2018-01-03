<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登陆学生选课系统!</title>
<style type="text/css">
.login_bg {
	background-image: url(images/login_bg.jpg);
	background-repeat: no-repeat;
	background-attachment: scroll;
	background-size: 1500px 650px;
	background-color: white;
}

.login_main {
	width: 890px;
	margin: 0px auto 0px auto;
}

.login_left {
	width: 420px;
	height: 340px;
	float: left;
	background-attachment: scroll;
	background-repeat: no-repeat;
	background-size: 50%;
}

.login_right {
	width: 425px;
	height: 400px;
	float: left;
	background-image: url(images/login_right.png);
	background-attachment: scroll;
	background-repeat: no-repeat;
	background-size: 100%;
	margin-top: 30px;
	padding-left: 30px;
}
</style>
</head>
<script type="text/javascript">
	
	//更换验证码
	function change(){
		var img = document.getElementById("img");
		//加一个无意义的参数，目的就是让地址每次都发生变化
		img.src = "${pageContext.request.contextPath }/VerificationCodeServlet?" + new Date().getTime();
	}
	
</script>
<body class="login_bg" >
	<form action="${pageContext.request.contextPath }/Controller?op=login"
		method="post">
		<input type="hidden" name="validate" value="">
		<div class="login_main">
			<div>
				<img src="images/school_logo.png" width="30%" align="top"> 
				<img src="images/project_logo.png" width="37%" align="top" style="margin-left: 20px">
			</div>
			<div class="login_left">
				<img src="images/login_left.png">
			</div>
			<div class="login_right">
				<img src="images/login_right2.png" width="60%" style="margin-left: 110px;margin-top: 20px;margin-bottom: 20px"><br>
				<label style="margin-left: 55px">用户名：</label><input type="text" name="username" style="width:45%;margin-top: 10px;color: gray"
					  value="${sessionScope.username == null?'请输入学号或工号':sessionScope.username }" onfocus="if(this.value=='请输入学号或工号'){this.value='';this.style.color='black';}else{this.style.color='black'}"
					  onblur="if(this.value==''){this.style.color='gray';this.value='请输入学号或工号';}else{this.style.color='gray'}">
				<br>
				<label style="margin-left: 55px">密&ensp;&ensp;码：</label><input type="text" name="password" style="width:45%;margin-top: 10px;color:gray"
					  value="请输入密码" onfocus="if(this.value=='请输入密码'){this.value='';this.style.color='black';this.type='password';}"
					  onblur="if(this.value==''){this.style.color='gray';this.value='请输入密码';this.type='text';}">
				<br>
				<label style="margin-left: 55px">验证码：</label><input type="text" name="code" style="width:20%;margin-top: 10px;vertical-align:bottom;color:gray"
					  value="请输入验证码" onfocus="if(this.value=='请输入验证码'){this.value='';this.style.color='black';}"
					  onblur="if(this.value==''){this.style.color='gray';this.value='请输入验证码';}">
				<img src="${pageContext.request.contextPath }/VerificationCodeServlet" id="img" title="看不清楚，换一张" style="padding-bottom: 0px;vertical-align:bottom" onclick="javascript:change()">&nbsp;&nbsp;<a href="javascript:change()">换一张</a>
				<br>
				<label style="margin-left: 55px">用户类型:</label>&nbsp;&nbsp;
				<select name="usertype" style="margin-top: 10px">
					<option value="student">学生</option>
					<option value="teacher">教师</option>
					<option value="manager">管理员</option>
				</select>
				<input type="submit" value="登陆" style="margin-left:10px">
					<span style="color:red">${sessionScope.error}</span>
			</div>
		</div>
	</form>
</body>
</html>