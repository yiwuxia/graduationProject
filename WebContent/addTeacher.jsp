<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加教师</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/laydate/laydate.js"></script>
<style type="text/css">
	#t1{
		width:1000px;
	}
	
	.option {
		width: 145px;
	}
	
	.class_grade {
		width: 45px;
	}
	
</style>
</head>
<script type="text/javascript">
	
	var arr = ["请选择职称", "助教", "讲师", "副教授", "教授"];
	
	function init(){
		//填充职称
		fillDate(arr, "teacherTitle");
	}
	
	//填充数据到下拉框
	function fillDate(arr, id){
		
		//清空选项
		document.getElementById(id).options.length = 0;

		//填充选项
		for(var i = 0; i < arr.length; i++){
			//创建一个option对象
			var option = new Option(arr[i], arr[i]);

			//将option对象添加到select中
			document.getElementById(id).options.add(option);
		}
	}
	
	//添加一个教师
	function addTeacher(){
		var name = document.getElementsByName("name")[0].value;
		var teacherNum = document.getElementsByName("teacherNum")[0].value;
		if(name == ""){
			alert("姓名不能为空！");
			return false;
		} else if(teacherNum == ""){
			alert("工号不能为空！");
			return false;
		}else{
			alert("添加教师成功！");
			document.getElementById("form").submit();
		}
	}
</script>
<body onload="init()">
	<div style="width: 1000px;margin-left: 80px">
		<h2 align="center"><label><b>添加教师</b></label></h2>
	</div>
	<form action="${pageContext.request.contextPath }/Controller?op=addTeacher" method="post" id="form">
		<table id="t1" style="margin-left: 20px;">
			<tbody>
				<tr>
					<td align="right" width="50%">姓名:</td>
					<td align="left"><input type="text" name="name"></td>
				</tr>
				<tr>
					<td align="right">工号:</td>
					<td align="left"><input type="text" name="teacherNum" value="${sessionScope.newTeacherNum }" readonly style="color: gray"></td>
				</tr>
				<tr>
					<td align="right">性别:</td>
					<td align="left">
						<label><input type="radio" name="gender" value="男" checked>男</label>
						<label><input type="radio" name="gender" value="女">女</label>
					</td>
				</tr>
				<tr>
					<td align="right">学院:</td>
					<td align="left">
						<select name="deptName" id="dept" class="option">
							<option value="">请选择学院</option>
							<c:forEach items="${sessionScope.dept }" var="d">
								<option value="${d.deptName }">${d.deptName }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">职称:</td>
					<td align="left">
						<select name="teacherTitle" id="teacherTitle" class="option"></select>
					</td>
				<tr>
					<td align="right">邮箱:</td>
					<td align="left"><input type="text" name="email" ></td>
				</tr>
				<tr>
					<td align="right">电话:</td>
					<td align="left"><input type="text" name="cellphone" ></td>
				</tr>
				<tr>
					<td align="right">生日:</td> 
					<td align="left"><input type="text" name="formBirthday" onclick="laydate()" readonly="readonly"></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input type="button" value="添加" style="margin-left: 120px; margin-top: 10px" onclick="addTeacher()"></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>





