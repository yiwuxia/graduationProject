<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>当前课程</title>
</head>
<style type="text/css">
	
	#t1 {
		width: 950px;
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

	//查询当前学年学期某一页的课程
	function jumps(){
		var currentPageIndex = document.getElementsByName("currentPageIndex")[0].value;
		var index = Number(currentPageIndex);
		var schoolYear = document.getElementById("schoolYear").value;
		var semester = document.getElementById("semester").value;
		if(currentPageIndex != ""){
			if(!isNaN(index) & index > 0){
				//当前页面索引是正整数
				//数据传递到服务端
				window.location = "${pageContext.request.contextPath }/Controller?op=listCourses&schoolYear=" + schoolYear + "&semester=" + semester + "&currentPageIndex=" + parseInt(index);
			}else{
				alert("请输入一个正整数！");
				document.getElementsByName("currentPageIndex")[0].value = "";
			}
		}
	}
	
	//查询当前学年学期某一页的课程
	function listPageCourses(currentPageIndex){
		var schoolYear = document.getElementById("schoolYear").value;
		var semester = document.getElementById("semester").value;
		if(currentPageIndex != ""){
			//数据传递到服务端
			window.location = "${pageContext.request.contextPath }/Controller?op=listCourses&schoolYear=" + schoolYear + "&semester=" + semester + "&currentPageIndex=" + currentPageIndex;
		}
	}
	
	//查看一门课程的成绩
	function showGrade(id){
		if(id != null){
			//数据传递到服务端
			window.location = "${pageContext.request.contextPath }/Controller?op=showGrade&courseNum=" + encodeURIComponent(id);
		}
	}
	
</script>
<body>
	<div style="margin-left: 80px;margin-bottom: 10px">
		<label><b>当前课程&nbsp;&nbsp;</b>
		${sessionScope.schoolYear }学年<input type="hidden" id="schoolYear" value="${sessionScope.schoolYear }">&nbsp;&nbsp;
		第${sessionScope.semester }学期<input type="hidden" id="semester" value="${sessionScope.semester }"></label>
	</div>
	<table id="t1" align="center">
		<tbody>
			<tr>
				<td>
					<table width="100%" id="t2">
						<tr>
							<th height="30px" width="180px">课程号码</th>
							<th width="80px">课程名称</th>
							<th width="80px">教师姓名</th>
							<th width="100px">学年</th>
							<th width="60px">学期</th>
							<th width="60px">学时</th>
							<th width="60px">学分</th>
							<th width="100px">已选人数</th>
							<th colspan="2">操作</th>
						</tr>
						<c:choose>
							<c:when test="${empty page.coursesList }">
								<c:choose>
									<c:when test="${sessionScope.status == 'true' }">
										<td colspan="11" height="50px" align="center">暂时没有数据</td>
									</c:when>
									<c:otherwise>
										<td colspan="11" height="50px" align="center"><span>当前不是选课时段，暂时不能查询！</span></td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach items="${page.coursesList }" var="course">
									<tr>
										<td style="word-wrap:break-word;word-break:break-all">${course.courseNum }</td>
										<td width="140px">${course.courseName }</td>
										<td>${course.teacherName }</td>
										<td>${course.schoolYear }</td>
										<td>${course.semester }</td>
										<td>${course.coursePeriod }&nbsp;h</td>
										<td>${course.courseCredit }</td>
										<td>${course.selectedNum }&nbsp;人</td>
										<td style="font-size: 17px">
											<a href="javascript:showGrade('${course.courseNum }')">录入成绩</a>
										</td>
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
			<table style="position:fixed;bottom:20px;margin-left: 50px">
				<tr>
					<td>第${page.currentPageIndex }页/共${page.pageCount }页</td>
					<td>
						<a href="javascript:listPageCourses(1)"><span class="border" style="margin-left: 25px">&laquo;</span></a>
					</td>
					<c:forEach begin="${page.startIndex }" end="${page.endIndex }" var="n">
						<td><a href="javascript:listPageCourses(${n})"><span class="${n == page.currentPageIndex?'currentPageIndex':'border' }">${n }</span></a></td>
					</c:forEach>
					<td>
						<a href="javascript:listPageCourses(${page.pageCount})"><span class="border">&raquo;</span></a>
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