<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询选课信息</title>
</head>
<style type="text/css">
	
	#t1 {
		width: 1150px;
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
		font-size: 20px;
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
	
	//全选全不选
	function checkAll(flag){
		//拿到所有的复选框
		var ids = document.getElementsByName("ids");
		//循环设置每一个复选框
		for(var i in ids){
			ids[i].checked = flag;
		}
	}
	
	//退选一门课程
	function quit(id){
		if(id != null){
			alert("退选成功");
			//数据传递到服务端
			window.location = "${pageContext.request.contextPath }/Controller?op=quit&id=" + encodeURIComponent(id);
		}
	}
	
	//退选多门课程
	function quitCourses(){
		//拿到所有的复选框
		var ids = document.getElementsByName("ids");
		//循环判断每一个复选框是否选中，构建id字符串
		var s = "";
		for(var i in ids){
			if(ids[i].checked){
				//如果被勾选则拿到该复选框的value
				s += ids[i].value + ",";
			}
		}
		
		if( s != ""){
			alert("退选成功");
			//数据传递到服务端
			window.location = "${pageContext.request.contextPath }/Controller?op=quitCourses&ids=" + s;
		} else{
			alert("请选择要退选的课程！");
		}
	}

	//查询某学年某学期某一页的课程
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
	
	//查询某学年某学期某一页的课程
	function listPageCourses(currentPageIndex){
		var schoolYear = document.getElementById("schoolYear").value;
		var semester = document.getElementById("semester").value;
		if(currentPageIndex != ""){
			//数据传递到服务端
			window.location = "${pageContext.request.contextPath }/Controller?op=listCourses&schoolYear=" + schoolYear + "&semester=" + semester + "&currentPageIndex=" + currentPageIndex;
		}
	}

</script>
<body>
	<div style="margin-left: 7px">
		<label><b>已选课程&nbsp;&nbsp;</b></label>
		<select id="schoolYear">
			<option>${schoolYear}</option>
		</select>
		学年&nbsp;&nbsp;
		第
		<select id="semester">
			<option>${semester }</option>
		</select>
		学期
	</div>
	<table id="t1" align="center">
		<tbody>
			<tr>
				<td>
					<table width="100%" id="t2">
						<tr>
							<th nowrap  width="40px"><label style="font-size: 15px">全选<br><input type="checkbox" id="all" onclick="checkAll(this.checked)"></label></th>
							<th width="120px">课程号码</th>
							<th width="80px">课程名称</th>
							<th width="80px">教师姓名</th>
							<th width="80px">学年</th>
							<th width="40px">学期</th>
							<th width="40px">学时</th>
							<th width="40px">学分</th>
							<th>课程介绍</th>
							<th width="40px">操作</th>
						</tr>
						<c:choose>
							<c:when test="${empty page.coursesList }">
								<c:choose>
									<c:when test="${sessionScope.status == 'true' }">
										<td colspan="10" height="50px" align="center">暂时没有数据</td>
									</c:when>
									<c:otherwise>
										<td colspan="10" height="50px" align="center"><span>当前不是选课时段，暂时不能选课！</span></td>
									</c:otherwise>
									</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach items="${page.coursesList }" var="c">
									<tr>
										<td height="50px"><input type="checkbox" name="ids" value="${c.courseNum }"></td>
										<td style="word-wrap:break-word;word-break:break-all;font-size: 12px">${c.courseNum }</td>
										<td>${c.courseName }</td>
										<td>${c.teacherName }</td>
										<td>${c.schoolYear }</td>
										<td>${c.semester }</td>
										<td>${c.coursePeriod }&nbsp;h</td>
										<td>${c.courseCredit }</td>
										<td style="font-size: 13px">${c.description }</td>
										<td>
											<a href="javascript:quit('${c.courseNum }')">退选</a>
										</td>									
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
					<button onclick="quitCourses()" style="margin-top: 10px; margin-bottom: 0px">退选</button>
				</td>
			</tr>
		</tbody>
	</table>
	<c:choose>
		<c:when test="${page.pageCount != 0}">
			<table style="position:fixed;bottom:20px">
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
						<a href="" onclick="jumps()">跳转</a>
					</td>
				</tr>
			</table>
		</c:when>
	</c:choose>
</body>
</html>