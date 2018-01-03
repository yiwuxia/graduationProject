<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加学生</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/laydate/laydate.js"></script>
<style type="text/css">
	body {
		font-weight:bolder;
	}
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
	
	var arr = ["${sessionScope.dept[0].deptName }", "${sessionScope.dept[1].deptName }", "${sessionScope.dept[2].deptName }", "${sessionScope.dept[3].deptName }"];
	
	arr["${sessionScope.dept[0].deptName }"] = ["物联网工程专业", "自动化专业", "电气工程及其自动化专业", "电子信息科学技术专业"];
	arr["${sessionScope.dept[1].deptName }"] = ["翻译专业", "英语专业(科技方向)", "商务英语专业", "日语专业"];
	arr["${sessionScope.dept[2].deptName }"] = ["计算机科学与技术", "软件工程", "信息安全"];
	arr["${sessionScope.dept[3].deptName }"] = ["通信工程", "信息工程", "信息工程(卓越工程师班)", "电子信息工程"];
	
	function init(){
		//填充学院
		fillDate(arr, "dept");
		//填充专业
		fillDate(arr[arr[0]], "major");	
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
	
	function changeMajor(dept){
		
		//填充专业
		fillDate(arr[dept], "major");
	}
	
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
<body onload="init()">
	<div style="width: 1000px;margin-left: 80px">
		<h2 align="center"><label><b>添加学生</b></label></h2>
	</div>
	<form action="${pageContext.request.contextPath }/Controller?op=addStudent" method="post" id="form">
		<table id="t1" style="margin-left: 20px;">
			<tbody>
				<tr>
					<td align="right" width="50%">姓名:</td>
					<td align="left"><input type="text" name="name"></td>
				</tr>
				<tr>
					<td align="right">学号:</td>
					<td align="left"><input type="text" name="studentNum" value="${sessionScope.newStudentNum }" readonly style="color: gray"></td>
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
						<select name="deptName" id="dept" onchange="changeMajor(this.value)" class="option"></select>
					</td>
				</tr>
				<tr>
					<td align="right">专业:</td>
							<td align="left">
								<select name="majorName" id="major" class="option"></select>
							</td>
				</tr>
				<tr>
					<td align="right">班级:</td>
					<td align="left">
						<select name="gradeNum" class="class_grade">
							<option>13</option>
							<option>14</option>
							<option>15</option>
							<option>16</option>
							<option>17</option>
						</select>
						<span>级</span>
						<select name="classNum" class="class_grade">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
							<option>7</option>
							<option>8</option>
						</select>
						<span>班</span>
					</td>
				</tr>
				<tr>
					<td align="right">生日:</td> 
					<td align="left"><input type="text" name="formBirthday" onclick="laydate()" readonly="readonly"></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input type="button" value="添加" style="margin-left: 120px; margin-top: 10px" onclick="addStudent()"></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>





