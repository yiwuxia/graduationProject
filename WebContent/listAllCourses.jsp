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
	body {
		font-weight:bolder;
	}
	
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

	//查询某学年某学期的课程
	function listCourses(){
		var schoolYear = document.getElementById("schoolYear").value;
		var semester = document.getElementById("semester").value;
		//数据传递到服务端
		window.location = "${pageContext.request.contextPath }/Controller?op=listCourses&schoolYear=" + schoolYear + "&semester=" + semester;
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

	//全选全不选
	function checkAll(flag){
		//拿到所有的复选框
		var ids = document.getElementsByName("ids");
		//循环设置每一个复选框
		for(var i in ids){
			ids[i].checked = flag;
		}
	}
	
	//选修一门课程
	function selectCourse(id, selectedNum){
		var number = Number(selectedNum);
		if(id != null){
			if(number < 150){
				alert("选课成功");
				//数据传递到服务端
				window.location = "${pageContext.request.contextPath }/Controller?op=selectCourse&id=" + encodeURIComponent(id);
			}
		}
	}
	
	//选修多门课程
	function selectCourses(selectedNum){
		var number = Number(selectedNum);
		//拿到所有的复选框
		var ids = document.getElementsByName("ids");
		//循环判断每一个复选框是否选中，构建id字符串
		var s = "";
		for(var i in ids){
			if(ids[i].value == undefined){
				break;
			}
			if(ids[i].checked){
				//如果被勾选则拿到该复选框的value
				s += ids[i].value + ",";
			}
		}

		if(s != ""){
			if(number < 150){
				alert("选课成功");
				//数据传递到服务端
				window.location = "${pageContext.request.contextPath }/Controller?op=selectCourses&ids=" + s;
			}
		}
	}
	
	//查看一门课程
	function showCourse(id){
		if(id != null){
			//数据传递到服务端
			window.location = "${pageContext.request.contextPath }/Controller?op=showCourse&courseNum=" + encodeURIComponent(id);
		}
	}
	
	//删除一门课程
	function deleteCourse(id){
		if(id != null){
			if(confirm("确定删除课程吗?")){
				alert("删除成功");
				//数据传递到服务端
				window.location = "${pageContext.request.contextPath }/Controller?op=deleteCourse&id=" + encodeURIComponent(id);
			}
		}
	}
	
	//删除多门课程
	function deleteCourses(){
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
			if(confirm("确定删除课程吗?")){
				alert("删除成功");
				//数据传递到服务端
				window.location = "${pageContext.request.contextPath }/Controller?op=deleteCourses&ids=" + s;
			}
		}
	}
</script>
<body onload="loadData()">
	<div style="margin-left: 7px">
		<label><b>课程信息&nbsp;&nbsp;</b></label>
		<c:choose>
			<c:when test="${sessionScope.userType == 'manager' }">
				<select id="schoolYear" onchange="listCourses()">
					<c:forEach begin="2013" end="2017" var="n">
						<option>${n-1 }-${n }</option>
					</c:forEach>
				</select>
				学年&nbsp;&nbsp;
				第
				<select id="semester" onchange="listCourses()">
					<option value="1">1</option>
					<option value="2">2</option>
				</select>
				学期
			</c:when>
			<c:when test="${sessionScope.userType == 'teacher' }">
				<select id="schoolYear" onchange="listCourses()">
					<c:forEach begin="2013" end="2017" var="n">
						<option>${n-1 }-${n }</option>
					</c:forEach>
				</select>
				学年&nbsp;&nbsp;
				第
				<select id="semester" onchange="listCourses()">
					<option value="1">1</option>
					<option value="2">2</option>
				</select>
				学期
			</c:when>
			<c:otherwise>
				<select id="schoolYear">
						<option>${schoolYear}</option>
				</select>
				学年&nbsp;&nbsp;
				第
				<select id="semester">
					<option>${semester }</option>
				</select>
				学期
			</c:otherwise>
		</c:choose>
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
							<th width="70px">教师姓名</th>
							<th width="80px">学年</th>
							<th width="40px">学期</th>
							<th width="40px">学时</th>
							<th width="40px">学分</th>
							<th width="60px">已选人数</th>
							<c:choose>
								<c:when test="${sessionScope.userType == 'student' }">
									<th>课程介绍</th>
									<th width="40px">操作</th>
								</c:when>
								<c:when test="${sessionScope.userType == 'teacher' }">
									<th width="370px">课程介绍</th>
									<th colspan="2">操作</th>
								</c:when>
								<c:when test="${sessionScope.userType == 'manager' }">
									<th width="80px">上课周次</th>
									<th width="80px">上课日次</th>
									<th width="80px">上课时间</th>
									<th width="80px">上课地点</th>
									<th width="60px" colspan="2">操作</th>
								</c:when>
							</c:choose>
						</tr>
						<c:choose>
							<c:when test="${empty page.coursesList }">
								<c:choose>
									<c:when test="${sessionScope.userType == 'student' }">
										<c:choose>
											<c:when test="${sessionScope.status == 'true' }">
												<td colspan="10" height="50px" align="center">暂时没有数据</td>
											</c:when>
											<c:otherwise>
												<td colspan="10" height="50px" align="center"><span>当前不是选课时段，暂时不能选课！</span></td>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:when test="${sessionScope.userType == 'teacher' }">
										<c:choose>
											<c:when test="${sessionScope.status == 'true' }">
												<td colspan="11" height="50px" align="center">暂时没有数据</td>
											</c:when>
											<c:otherwise>
												<td colspan="11" height="50px" align="center"><span>当前不是选课时段，暂时不能查询！</span></td>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:when test="${sessionScope.userType == 'manager' }">
										<td colspan="13" height="50px" align="center">暂时没有数据</td>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:forEach items="${page.coursesList }" var="course">
									<tr>
										<td height="50px"><input type="checkbox" name="ids" value="${course.courseNum }"></td>
										<td style="word-wrap:break-word;word-break:break-all;font-size: 12px">${course.courseNum }</td>
										<td style="font-size: 14px">${course.courseName }</td>
										<td>${course.teacherName }</td>
										<td style="font-size: 14px">${course.schoolYear }</td>
										<td>${course.semester }</td>
										<td>${course.coursePeriod }&nbsp;h</td>
										<td>${course.courseCredit }</td>
										<td>${course.selectedNum }&nbsp;人</td>
										<c:choose>
											<c:when test="${sessionScope.userType == 'student' }">
												<td style="font-size: 13px">${course.description }</td>
												<td>
													<a href="javascript:selectCourse('${course.courseNum }','${course.selectedNum }')">选修</a>
												</td>
											</c:when>
											<c:when test="${sessionScope.userType == 'teacher' }">
												<td style="font-size: 13px">${course.description }</td>
												<td style="font-size: 13px">
													<a href="javascript:showCourse('${course.courseNum }')">查看详情</a>
												</td>
												<td style="font-size: 13px">
													<a href="${pageContext.request.contextPath }/Controller?op=toUpdateCourse&courseNum=${course.courseNum }">修改课程</a>
												</td>
											</c:when>
											<c:when test="${sessionScope.userType == 'manager' }">
												<td style="font-size: 13px">${course.week }</td>
												<td style="font-size: 13px">${course.weekday }</td>
												<td style="font-size: 13px">${course.time }</td>
												<td style="font-size: 13px">${course.place }</td>
												<td style="font-size: 17px" width="50px">
													<a href="${pageContext.request.contextPath }/Controller?op=toUpdateCourse&courseNum=${course.courseNum }">修改</a>
												</td>
												<td style="font-size: 17px" width="50px">
													<a href="${pageContext.request.contextPath }/Controller?op=deleteCourse&id=${course.courseNum }">删除</a>
												</td>
											</c:when>
										</c:choose>									
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
					<c:choose>
						<c:when test="${sessionScope.userType == 'student' }">
							<c:choose>
								<c:when test="${empty page.coursesList }">
								</c:when>
								<c:otherwise>
									<button onclick="selectCourses('${course.selectedNum }')" style="margin-top: 10px; margin-bottom: 0px">确定选修</button>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${sessionScope.userType == 'manager' }">
							<c:choose>
								<c:when test="${empty page.coursesList }">
								</c:when>
								<c:otherwise>
									<button onclick="deleteCourses()" style="margin-top: 10px; margin-bottom: 0px">删除课程</button>
								</c:otherwise>
							</c:choose>
						</c:when>
					</c:choose>
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
						<a href="javascript:jumps()">跳转</a>
					</td>
				</tr>
			</table>
		</c:when>
	</c:choose>
</body>
</html>