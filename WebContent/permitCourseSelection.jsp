<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>开放选课</title>
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
<script type="text/javascript">

	//开放选课
	function permitCourseSelection(){
		var schoolYear = document.getElementById("schoolYear").value;
		var semester = document.getElementById("semester").value;
		var status = "";
		//拿到radio标签
		var radio = document.getElementsByName("status");
		for(var i = 0; i < radio.length; i++){
			if(radio[i].checked){
				status = radio[i].value;
			}
		}
		alert("设置成功！");
		document.getElementById("form").action = "${pageContext.request.contextPath }/Controller?op=permitCourseSelection&schoolYear=" + schoolYear + "&semester=" + semester + "&status=" + status;
		document.getElementById("form").submit();
		
	}
</script>
<body>
	<div style="width: 1000px;margin-left: 80px">
		<h2 align="center"><label><b>开放选课</b></label></h2>
	</div>
	<form action="" method="post" id="form">
		<table id="t1" style="margin-left: 80px;">
			<tbody>
				<tr>
					<td align="right" width="50%">当前学年:</td>
					<td align="left"><input type="text" id="schoolYear" value="${sessionScope.schoolYear }" style="width: 100px"></td>
				</tr>
				<tr>
					<td align="right">当前学期:</td>
					<td align="left"><input type="text" id="semester" value="${sessionScope.semester }" style="width: 100px"></td>
				</tr>
				<tr>
					<td align="right" width="50%">当前状态:</td>
					<td align="left"><span>${sessionScope.status ?"已开放选课":"已关闭选课" }</span></td>
				</tr>
				<tr style="height: 50px">
					<td align="right">
						<label><input type="radio" name="status" value="1" ${sessionScope.status?"":"checked"}>开放选课</label>
					</td>
					<td>
						<label><input type="radio" name="status" value="0" ${sessionScope.status?"checked":""}>关闭选课</label>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input type="button" value="确定" style="margin-top: 15px" onclick="permitCourseSelection()"></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>





