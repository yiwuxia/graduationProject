<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成绩查询</title>
</head>
<style type="text/css">
	
	#t1 {
		width: 1050px;
	}
	
	#t2{
		border:1px solid gray;
		border-collapse: collapse;
		text-align:center;
		font-size: 15px;
	}
	
	#t2 td,th{
		border:1px solid gray;
		padding-left: 3px;
		padding-right: 3px;
	}
	
	a {
		text-decoration: none;
	}
	
	a span {
		color: black;
	}
	
	.border {
		display: block;
		text-align: center;
		background-color: white;
		border: 1px solid white;
		width: 30px;
		cursor: pointer;
	}
	
	label {
		font-size: 25px;
	}
	
	span {
		color: red;
	}
	
	span.border:hover {
		background-color: rgb(131,175,155);
		border: 1px solid rgb(131,175,155);
	}
	
	.currentPageIndex {
		display: block;
		text-align: center;
		background-color: rgb(131,175,155);
		border: 1px solid rgb(131,175,155);
		width: 30px;
		cursor: pointer;
	}
</style>
</head>
<script type="text/javascript">

	//加载当前学期和学年的下拉框选中的选项
	function loadData(){
		//判断当前学年
		var schoolYear = document.getElementById("schoolYear");
		for(var i = 0; i < schoolYear.options.length; i++){
			if("${sessionScope.schoolYear}" == schoolYear.options[i].value){
				schoolYear.options[i].selected = "selected";
			}
		}
		//判断当前学期
		var semester = document.getElementById("semester");
		for(var i = 0; i < semester.options.length; i++){
			if("${sessionScope.semester}" == semester.options[i].value){
				semester.options[i].selected = "selected";
			}
		}
	}

	//查询某学年某学期某一页的课程成绩
	function jumps(){
		var schoolYear = document.getElementById("schoolYear").value;
		var semester = document.getElementById("semester").value;
		var currentPageIndex = document.getElementsByName("currentPageIndex")[0].value;
		var index = Number(currentPageIndex);
		if(currentPageIndex != ""){
			if(!isNaN(index) & index > 0){
				//当前页面索引是正整数
				//数据传递到服务端
				if(schoolYear == "" & semester == ""){
					//查询所有学期的课程
					window.location = "${pageContext.request.contextPath }/Controller?op=showGrade&currentPageIndex=" + parseInt(index) + "&schoolYear=" + "all" + "&semester=" + "all";
				}else if(schoolYear != "" & semester != ""){
					//数据传递到服务端
					window.location = "${pageContext.request.contextPath }/Controller?op=showGrade&currentPageIndex=" + parseInt(index) + "&schoolYear=" + schoolYear + "&semester=" + semester;
				}
			}else{
				alert("请输入一个正整数！");
				document.getElementsByName("currentPageIndex")[0].value = "";
			}
		}
	}
	
	//查询某学年某学期某一页的课程成绩
	function showGrade(currentPageIndex){
		var schoolYear = document.getElementById("schoolYear").value;
		var semester = document.getElementById("semester").value;
		
		if(schoolYear == "" & semester == ""){
			//查询所有学期的课程
			window.location = "${pageContext.request.contextPath }/Controller?op=showGrade&currentPageIndex=" + currentPageIndex + "&schoolYear=" + "all" + "&semester=" + "all";
		}else if(schoolYear != "" & semester != ""){
			//数据传递到服务端
			window.location = "${pageContext.request.contextPath }/Controller?op=showGrade&currentPageIndex=" + currentPageIndex + "&schoolYear=" + schoolYear + "&semester=" + semester;
		}
	}
	
	//查询所有课程成绩
	function showAllGrade(){
		document.getElementById("schoolYear").value = "";
		document.getElementById("semester").value = "";
		
		showGrade(1);
	}
	
</script>
<body onload="loadData()">
	<div style="margin-left: 50px;margin-bottom: 10px">
		<label><b>成绩查询&nbsp;&nbsp;</b></label>
		<select id="schoolYear" onchange="showGrade(1)">
			<c:forEach begin="2013" end="2017" var="n">
				<option>${n-1 }-${n }</option>
			</c:forEach>
			<option value="">----------</option>
		</select>
		学年&nbsp;&nbsp;
		第
		<select id="semester" onchange="showGrade(1)">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="">--</option>
		</select>
		学期
		<button onclick="showAllGrade()">查询所有成绩</button>
	</div>
	<table id="t1" align="center">
		<tbody>
			<tr>
				<td>
					<table width="100%" id="t2">
						<tr>
							<th  height="30px" width="180px">课程号码</th>
							<th width="140px">课程名称</th>
							<th width="100px">教师工号</th>
							<th width="80px">教师姓名</th>
							<th width="70px">平时成绩</th>
							<th width="70px">期中成绩</th>
							<th width="70px">期末成绩</th>
							<th width="70px">综合成绩</th>
						</tr>
						<c:choose>
							<c:when test="${empty page.courseSelectionsList }">
								<td colspan="9" height="50px" align="center">暂时没有数据</td>
							</c:when>
							<c:otherwise>
								<c:forEach items="${page.courseSelectionsList }" var="courseSelection">
									<tr>
										<td  height="40px" style="word-wrap:break-word;word-break:break-all">${courseSelection.courseNum }</td>
										<td style="word-wrap:break-word;word-break:break-all">${courseSelection.courseName }</td>
										<td>${courseSelection.teacherNum }</td>
										<td>${courseSelection.teacherName }</td>
										<td>${courseSelection.regularGrade }</td>
										<td>${courseSelection.midtermGrade }</td>
										<td>${courseSelection.finalExamGrade }</td>
										<td>${courseSelection.grade }</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	<c:choose>
		<c:when test="${page.pageCount != 0}">
			<table style="position:fixed;bottom:20px;left:50px">
				<tr>
					<td>第${page.currentPageIndex }页/共${page.pageCount }页</td>
					<td>
						<a href="javascript:showGrade(1)"><span class="border" style="margin-left: 25px">&laquo;</span></a>
					</td>
					<c:forEach begin="${page.startIndex }" end="${page.endIndex }" var="n">
						<td><a href="javascript:showGrade(${n})"><span class="${n == page.currentPageIndex?'currentPageIndex':'border' }">${n }</span></a></td>
					</c:forEach>
					<td>
						<a href="javascript:showGrade(${page.pageCount})"><span class="border">&raquo;</span></a>
					</td>
					<td>
						<input type="text" name="currentPageIndex" style="width: 40px; text-align: right; margin-left: 50px">&nbsp;
						<a href="javascript:jumps()">跳转</a>
					</td>
				</tr>
			</table>
		</c:when>
	</c:choose>
</body>
</html>