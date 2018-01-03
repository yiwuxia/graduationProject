<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成绩详情</title>
</head>
<style type="text/css">
	body {
		font-weight:bolder;
	}	
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
	
	//查询某一页的课程成绩
	function jumps(){
		var currentPageIndex = document.getElementsByName("currentPageIndex")[0].value;
		var index = Number(currentPageIndex);
		var courseNum = document.getElementById("courseNum").value;
		if(currentPageIndex != ""){
			if(!isNaN(index) & index > 0){
				//当前页面索引是正整数
				//数据传递到服务端
				window.location = "${pageContext.request.contextPath }/Controller?op=showGrade&currentPageIndex=" + parseInt(index) + "&courseNum=" + courseNum;
			}else{
				alert("请输入一个正整数！");
				document.getElementsByName("currentPageIndex")[0].value = "";
			}
		}
	}
	
	//查看一门课程的成绩
	function showGrade(currentPageIndex){
		var courseNum = document.getElementById("courseNum").value;
		if(courseNum != null){
			//数据传递到服务端
			window.location = "${pageContext.request.contextPath }/Controller?op=showGrade&courseNum=" + courseNum + "&currentPageIndex=" + currentPageIndex;
		}
	}
	
	//跳转向录入成绩页面
	function toInputGrade(courseNum, studentNum){
		//数据传递到服务端
		window.location = "${pageContext.request.contextPath }/Controller?op=toInputGrade&courseNum=" + encodeURIComponent(courseNum) + "&studentNum=" + studentNum;
	}
</script>
<body>
	<div style="margin-left: 50px;margin-bottom: 10px">
		<label><b>成绩详情&nbsp;&nbsp;</b></label>
	</div>
	<table id="t1" align="center">
		<tbody>
			<tr>
				<td>
					<table width="100%" id="t2">
						<tr>
							<th  height="30px" width="180px">课程号码</th>
							<th width="140px">课程名称</th>
							<th width="100px">学号</th>
							<th width="80px">学生姓名</th>
							<th width="70px">平时成绩</th>
							<th width="70px">期中成绩</th>
							<th width="70px">期末成绩</th>
							<th width="70px">综合成绩</th>
							<th width="70px">操作</th>
						</tr>
						<c:choose>
							<c:when test="${empty page.courseSelectionsList }">
								<td colspan="9" height="50px" align="center">暂时没有数据</td>
							</c:when>
							<c:otherwise>
								<c:forEach items="${page.courseSelectionsList }" var="courseSelection">
									<tr>
										<td  height="40px" style="word-wrap:break-word;word-break:break-all">${courseSelection.courseNum }<input type="hidden" value="${courseSelection.courseNum }" id="courseNum"></td>
										<td style="word-wrap:break-word;word-break:break-all">${courseSelection.courseName }</td>
										<td>${courseSelection.studentNum }</td>
										<td>${courseSelection.studentName }</td>
										<td>${courseSelection.regularGrade }</td>
										<td>${courseSelection.midtermGrade }</td>
										<td>${courseSelection.finalExamGrade }</td>
										<td>${courseSelection.grade }</td>
										<td style="font-size: 17px">
											<a href="javascript:toInputGrade('${courseSelection.courseNum }', '${courseSelection.studentNum }')">录入成绩</a>
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