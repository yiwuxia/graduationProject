<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删改教师</title>
</head>
<style type="text/css">
	
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
	var arr1 = ["请选择职称", "助教", "讲师", "副教授", "教授"];
	
	function init(){
		//填充学院
		fillDate(arr, "deptName");
		
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
	
	//加载当前学院和专业的下拉框选中的选项
	function loadData(){
		//判断当前学院
		var deptName = document.getElementById("deptName");
		for(var i = 0; i < deptName.options.length; i++){
			if("${sessionScope.deptName}" == deptName.options[i].value){
				deptName.options[i].selected = "selected";
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
	
	//修改一个教师
	function updateTeacher(){
		var id = document.getElementById("teacherNum").value;
		if(id != null){
			alert("修改成功");
			//数据传递到服务端
			window.location = "${pageContext.request.contextPath }/Controller?op=updateTeacher&teacherNum=" + id;
		}
	}
	
	//删除一个教师
	function deleteTeacher(id){
		if(id != null){
			if(confirm("确定删除教师吗?")){
				alert("删除成功");
				//数据传递到服务端
				window.location = "${pageContext.request.contextPath }/Controller?op=deleteTeacher&id=" + id;
			}
		}
	}
	
	//删除多个教师
	function deleteTeachers(){
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
			if(confirm("确定删除教师吗?")){
				alert("删除成功");
				//数据传递到服务端
				window.location = "${pageContext.request.contextPath }/Controller?op=deleteTeachers&ids=" + s;
			}
		}
	}
	
	//查询某学院某一页的教师
	function jumps(){
		var currentPageIndex = document.getElementsByName("currentPageIndex")[0].value;
		var index = Number(currentPageIndex);
		var deptName = document.getElementById("deptName").value;
		if(currentPageIndex != ""){
			if(!isNaN(index) & index > 0){
				//当前页面索引是正整数
				//数据传递到服务端
				window.location = "${pageContext.request.contextPath }/Controller?op=toListPageTeachers&currentPageIndex=" + parseInt(index) + "&deptName=" + encodeURI(deptName);
			}else{
				alert("请输入一个正整数！");
				document.getElementsByName("currentPageIndex")[0].value = "";
			}
		}
	}
	
	//查询某学院某一页的教师
	function listPageTeachers(currentPageIndex){
		var deptName = document.getElementById("deptName").value;
		//数据传递到服务端
		window.location = "${pageContext.request.contextPath }/Controller?op=toListPageTeachers&deptName=" + encodeURI(deptName) + "&currentPageIndex=" + currentPageIndex;
	}
</script>
<body onload="init()">
	<div style="margin-left: 30px;margin-bottom: 10px">
		<label><b>删改教师&nbsp;&nbsp;</b></label>
		<select name="deptName" id="deptName" onchange="listPageTeachers(1)" class="option" style="text-align: right"></select>
	</div>
	<table id="t1" align="center">
		<tbody>
			<tr>
				<td>
					<table width="100%" id="t2">
						<tr>
							<th nowrap  width="40px"><label style="font-size: 15px">全选<br><input type="checkbox" id="all" onclick="checkAll(this.checked)"></label></th>
							<th width="60px">工号</th>
							<th width="80px">姓名</th>
							<th width="100px">系名称</th>
							<th width="70px">职称</th>
							<th width="40px">性别</th>
							<th width="100px">生日</th>
							<th width="100px">密码</th>
							<th width="200px">邮箱地址</th>
							<th width="130px">联系电话</th>
							<th colspan="2">操作</th>
						</tr>
						<c:choose>
							<c:when test="${empty page.teachersList }">
								<td colspan="10" height="50px" align="center">暂时没有数据</td>
							</c:when>
							<c:otherwise>
								<c:forEach items="${page.teachersList }" var="teacher">
									<tr>
										<td height="50px"><input type="checkbox" name="ids" value="${teacher.teacherNum }"></td>
										<td style="word-wrap:break-word;word-break:break-all">${teacher.teacherNum }</td>
										<td>${teacher.name }</td>
										<td style="word-wrap:break-word;word-break:break-all">${teacher.deptName }</td>
										<td>${teacher.teacherTitle }</td>
										<td>${teacher.gender }</td>
										<td>${teacher.birthday }</td>
										<td style="word-wrap:break-word;word-break:break-all">${teacher.password }</td>
										<td style="word-wrap:break-word;word-break:break-all">${teacher.email }</td>
										<td>${teacher.cellphone }</td>
										<td style="font-size: 17px" width="50px">
											<a href="${pageContext.request.contextPath }/Controller?op=toUpdateTeacher&teacherNum=${teacher.teacherNum }">修改</a>
										</td>
										<td style="font-size: 17px" width="50px">
											<a href="javascript:deleteTeacher(${teacher.teacherNum })">删除</a>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
					<c:choose>
						<c:when test="${empty page.teachersList }">
						</c:when>
						<c:otherwise>
							<button onclick="deleteTeachers()" style="margin-top: 10px; margin-bottom: 0px">删除教师</button>
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
						<a href="javascript:listPageTeachers(1)"><span class="border" style="margin-left: 25px">&laquo;</span></a>
					</td>
					<c:forEach begin="${page.startIndex }" end="${page.endIndex }" var="n">
						<td><a href="javascript:listPageTeachers(${n})"><span class="${n == page.currentPageIndex?'currentPageIndex':'border' }">${n }</span></a></td>
					</c:forEach>
					<td>
						<a href="javascript:listPageTeachers(${page.pageCount})"><span class="border">&raquo;</span></a>
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