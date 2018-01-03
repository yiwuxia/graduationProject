<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<style type="text/css">
	body {
		font-weight:bolder;
	}
	#t1{
		width:1000px;
	}
	
</style>
</head>
<script>

	//修改密码
	function changePassword(){
		var oldPassword = document.getElementById("oldPassword").value;
		var newPassword = document.getElementById("newPassword").value;
		if(oldPassword == ""){
			alert("原密码不能为空！");
			return false;
		} else if(newPassword == ""){
			alert("新密码不能为空！");
			return false;
		} else if(newPassword == oldPassword){
			alert("两个密码不能相同！");
			return false;
		} else if("${sessionScope.loginUser.password }" != oldPassword){
			alert("原密码错误！");
			return false;
		}  else{
			alert("修改密码成功！");
			document.getElementById("form").action = "${pageContext.request.contextPath }/Controller?op=changePassword&newPassword=" + newPassword + "&oldPassword=" + oldPassword;
			document.getElementById("form").submit();
		}
	}
</script>
<body>
	<div style="width: 1000px;margin-left: 70px">
		<h2 align="center"><label><b>修改密码</b></label></h2>
	</div>
	<form action="" method="post" id="form">
		<table id="t1" style="margin-left: 20px;">
			<tbody>
				<tr>
					<td align="right" width="50%">原密码:</td>
					<td align="left"><input type="text" id="oldPassword"></td>
				</tr>
				<tr>
					<td align="right" width="50%">新密码:</td>
					<td align="left"><input type="text" id="newPassword"></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input type="button" value="修改密码" style="margin-left: 100px;margin-top: 15px" onclick="changePassword()"></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>





