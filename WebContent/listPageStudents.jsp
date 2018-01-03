<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删改学生</title>
</head>
<style type="text/css">
	body {
		font-weight:bolder;
	}	
	#t1 {
		width: 1100px;
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
	
	var arr = ["${sessionScope.dept[0].deptName }", "${sessionScope.dept[1].deptName }", "${sessionScope.dept[2].deptName }", "${sessionScope.dept[3].deptName }"];
	
	arr["${sessionScope.dept[0].deptName }"] = ["物联网工程专业", "自动化专业", "电气工程及其自动化专业", "电子信息科学技术专业"];
	arr["${sessionScope.dept[1].deptName }"] = ["翻译专业", "英语专业(科技方向)", "商务英语专业", "日语专业"];
	arr["${sessionScope.dept[2].deptName }"] = ["计算机科学与技术", "软件工程", "信息安全"];
	arr["${sessionScope.dept[3].deptName }"] = ["通信工程", "信息工程", "信息工程(卓越工程师班)", "电子信息工程"];
	
	function init(){
		//填充学院
		fillDate(arr, "deptName");
		//填充专业
		fillDate(arr[arr[0]], "majorName");	
		
		loadData();
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
		fillDate(arr[dept], "majorName");
	}
	
	//加载当前学院和专业的下拉框选中的选项
	function loadData(){
		//判断当前学院
		var deptName = document.getElementById("deptName");
		for(var i = 0; i < deptName.options.length; i++){
			if("${sessionScope.deptName}" == deptName.options[i].value){
				deptName.options[i].selected = "selected";
			}
		}
		
		//填充专业
		fillDate(arr["${sessionScope.deptName}"], "majorName");	
		//判断当前专业
		var majorName = document.getElementById("majorName");
		for(var i = 0; i < majorName.options.length; i++){
			if("${sessionScope.majorName}" == majorName.options[i].value){
				majorName.options[i].selected = "selected";
			}
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
	
	//修改一个学生
	function updateStudent(){
		var id = document.getElementById("studentNum").value;
		if(id != null){
			alert("修改成功");
			//数据传递到服务端
			window.location = "${pageContext.request.contextPath }/Controller?op=updateStudent&studentNum=" + id;
		}
	}
	
	//删除一个学生
	function deleteStudent(id){
		if(id != null){
			if(confirm("确定删除学生吗?")){
				alert("删除成功");
				//数据传递到服务端
				window.location = "${pageContext.request.contextPath }/Controller?op=deleteStudent&id=" + id;
			}
		}
	}
	
	//删除多个学生
	function deleteStudents(){
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
			if(confirm("确定删除学生吗?")){
				alert("删除成功");
				//数据传递到服务端
				window.location = "${pageContext.request.contextPath }/Controller?op=deleteStudents&ids=" + s;
			}
		}
	}
	
	//查询某专业某一页的学生
	function jumps(){
		var currentPageIndex = document.getElementsByName("currentPageIndex")[0].value;
		var index = Number(currentPageIndex);
		var majorName = document.getElementById("majorName").value;
		if(currentPageIndex != ""){
			if(!isNaN(index) & index > 0){
				//当前页面索引是正整数
				//数据传递到服务端
				window.location = "${pageContext.request.contextPath }/Controller?op=toListPageStudents&currentPageIndex=" + parseInt(index) + "&majorName=" + encodeURI(majorName);
			}else{
				alert("请输入一个正整数！");
				document.getElementsByName("currentPageIndex")[0].value = "";
			}
		}
	}
	
	//查询某专业某一页的学生
	function listPageStudents(currentPageIndex){
		var deptName = document.getElementById("deptName").value;
		var majorName = document.getElementById("majorName").value;
		//数据传递到服务端
		window.location = "${pageContext.request.contextPath }/Controller?op=toListPageStudents&deptName=" + encodeURI(deptName) + "&majorName=" + encodeURI(majorName) + "&currentPageIndex=" + currentPageIndex;
	}
</script>
<body onload="init()">
	<div style="margin-left: 30px;margin-bottom: 10px">
		<label><b>删改学生&nbsp;&nbsp;</b></label>
		<select name="deptName" id="deptName" onchange="changeMajor(this.value),listPageStudents(1)" class="option" style="text-align: right"></select>&nbsp;&nbsp;
		<select name="majorName" id="majorName" class="option"  onchange="listPageStudents(1)" style="text-align: right"></select>
	</div>
	<table id="t1" align="center">
		<tbody>
			<tr>
				<td>
					<table width="100%" id="t2">
						<tr>
							<th nowrap  width="40px"><label style="font-size: 15px">全选<br><input type="checkbox" id="all" onclick="checkAll(this.checked)"></label></th>
							<th width="120px">学号</th>
							<th width="80px">姓名</th>
							<th width="120px">系名称</th>
							<th width="150px">专业名称</th>
							<th width="60px">年级</th>
							<th width="60px">班级</th>
							<th width="60px">性别</th>
							<th width="100px">生日</th>
							<th width="120px">密码</th>
							<th colspan="2">操作</th>
						</tr>
						<c:choose>
							<c:when test="${empty page.studentsList }">
								<td colspan="13" height="50px" align="center">暂时没有数据</td>
							</c:when>
							<c:otherwise>
								<c:forEach items="${page.studentsList }" var="student">
									<tr>
										<td height="50px"><input type="checkbox" name="ids" value="${student.studentNum }"></td>
										<td style="word-wrap:break-word;word-break:break-all">${student.studentNum }</td>
										<td>${student.name }</td>
										<td style="word-wrap:break-word;word-break:break-all">${student.deptName }</td>
										<td style="word-wrap:break-word;word-break:break-all">${student.majorName }</td>
										<td>${student.gradeNum }级</td>
										<td>${student.classNum%100 }&nbsp;班</td>
										<td>${student.gender }</td>
										<td>${student.birthday }</td>
										<td style="word-wrap:break-word;word-break:break-all">${student.password }</td>
										<td style="font-size: 17px" width="50px">
											<a href="${pageContext.request.contextPath }/Controller?op=toUpdateStudent&studentNum=${student.studentNum }">修改</a>
										</td>
										<td style="font-size: 17px" width="50px">
											<a href="javascript:deleteStudent(${student.studentNum })">删除</a>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
					<c:choose>
						<c:when test="${empty page.studentsList }">
						</c:when>
						<c:otherwise>
							<button onclick="deleteStudents()" style="margin-top: 10px; margin-bottom: 0px">删除学生</button>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</tbody>
	</table>
	<c:choose>
		<c:when test="${page.pageCount != 0}">
			<table style="position:fixed;bottom:20px;left:30px">
				<tr>
					<td>第${page.currentPageIndex }页/共${page.pageCount }页</td>
					<td>
						<a href="javascript:listPageStudents(1)"><span class="border" style="margin-left: 25px">&laquo;</span></a>
					</td>
					<c:forEach begin="${page.startIndex }" end="${page.endIndex }" var="n">
						<td><a href="javascript:listPageStudents(${n})"><span class="${n == page.currentPageIndex?'currentPageIndex':'border' }">${n }</span></a></td>
					</c:forEach>
					<td>
						<a href="javascript:listPageStudents(${page.pageCount})"><span class="border">&raquo;</span></a>
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