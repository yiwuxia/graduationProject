<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>录入成绩</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/laydate/laydate.js"></script>
<style type="text/css">
	
	body{
		font-size: 22px;
	}
	
	#t1{
		width:1000px;
	}
	
	span{
		color: red;
	}
	
	input{
		height: 35px;
		font-size: 20px;
	}
</style>
</head>
<script type="text/javascript">

	//计算综合成绩
	function getGrade(){
		var regularGrade = Number(document.getElementsByName("regularGrade")[0].value);
		var midtermGrade = Number(document.getElementsByName("midtermGrade")[0].value);
		var finalExamGrade = Number(document.getElementsByName("finalExamGrade")[0].value);
		var grade = Number(document.getElementsByName("grade")[0].value);
		
		if(isNaN(document.getElementsByName("regularGrade")[0].value)){
			alert("请输入0到100之间的分数！");
		}else if(isNaN(document.getElementsByName("midtermGrade")[0].value)){
			alert("请输入0到100之间的分数！");
		}else if(isNaN(document.getElementsByName("finalExamGrade")[0].value)){
			alert("请输入0到100之间的分数！");
		}else{
			//如果输入的是数字在判断范围
			if(regularGrade > 100 || regularGrade < 0 ){
				alert("请输入0到100之间的分数！");
			} else if(midtermGrade > 100 || midtermGrade < 0 ){
				alert("请输入0到100之间的分数！");
			} else if(finalExamGrade > 100 || finalExamGrade < 0 ){
				alert("请输入0到100之间的分数！");
			} else{
				if(midtermGrade == 0){
					document.getElementsByName("grade")[0].value = parseInt(regularGrade*0.3 + finalExamGrade*0.7);
				}else{
					document.getElementsByName("grade")[0].value = parseInt(regularGrade*0.3 + (midtermGrade + finalExamGrade)/2*0.7);
				}
			}
		}
	}
	
	//录入一个学生的成绩
	function inputGrade(){
		var regularGrade = Number(document.getElementsByName("regularGrade")[0].value);
		var finalExamGrade = document.getElementsByName("finalExamGrade")[0].value;
		var grade = document.getElementsByName("grade")[0].value;
		if(regularGrade == ""){
			alert("平时成绩不能为空！");
			return false;
		}else if(finalExamGrade == ""){
			alert("期末成绩不能为空！");
			return false;
		}else if(grade == ""){
			alert("综合成绩不能为空！");
			return false;
		}else{
			alert("录入成绩成功！");
			document.getElementById("form").submit();
		}
	}	
</script>
<body onload="init()">
	<div style="width: 1000px;margin-left: 80px">
		<h2 align="center"><label><b>录入成绩</b></label></h2>
	</div>
	<form action="${pageContext.request.contextPath }/Controller?op=inputGrade&courseNum=${courseSelection.courseNum }" method="post" id="form">
		<table id="t1" style="margin-left: 20px;">
			<tbody>
				<tr>
					<td align="right" width="50%">课程:</td>
					<td align="left"><input type="text" name=courseName value="${courseSelection.courseName }" readonly></td>
					<td><input type="hidden" name="courseNum" value="${courseSelection.courseNum }"></td>
				</tr>
				<tr>
					<td align="right" width="50%">学生:</td>
					<td align="left"><input type="text" name="studentName" value="${courseSelection.studentName }" readonly></td>
				</tr>
				<tr>
					<td align="right">学号:</td>
					<td align="left"><input type="text" name="studentNum" value="${courseSelection.studentNum }" readonly></td>
				</tr>
				<tr>
					<td align="right">平时成绩:</td> 
					<td align="left"><input type="text" name="regularGrade" value="${courseSelection.regularGrade }"></td>
				</tr>
				<tr>
					<td align="right">期中成绩:</td> 
					<td align="left"><input type="text" name="midtermGrade" value="${courseSelection.midtermGrade }"></td>
				</tr>
				<tr>
					<td align="right">期末成绩:</td> 
					<td align="left"><input type="text" name="finalExamGrade" value="${courseSelection.finalExamGrade }"></td>
				</tr>
				<tr>
					<td align="right">综合成绩:</td> 
					<td align="left"><input type="text" name="grade" value="${courseSelection.grade==null?'请点击计算成绩':courseSelection.grade }" readonly></td>
				</tr>
				<tr><td colspan="2"><span style="margin-left: 320px">*平时成绩占30%,考试成绩占70%,期中成绩可以为空</span></td></tr>
				<tr>
					<td align="right">
						<input type="button" value="计算成绩" onclick="getGrade()" style="margin-top: 10px;margin-right: -30px">
					</td>
					<td align="left">
						<input type="button" value="提交成绩" onclick="inputGrade()" style="margin-left: 80px;margin-top: 10px">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>





