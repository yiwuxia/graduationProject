<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人课表</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/laydate/laydate.js"></script>
<style type="text/css">
	body {
		font-weight:bolder;
	}	
	#t1 {
		border:1px solid gray;
		border-collapse: collapse;
		text-align:center;
		font-size: 15px;
	}
	
	#t1 td,th{
		border:1px solid gray;
		padding-left: 3px;
		padding-right: 3px;
	}
	
	label {
		font-size: 25px;
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
	
	//查询某学年学期的个人课表
	function showTimetable(){
		var schoolYear = document.getElementById("schoolYear").value;
		var semester = document.getElementById("semester").value;
		//数据传递到服务端
		window.location = "${pageContext.request.contextPath }/Controller?op=showTimetable&schoolYear=" + schoolYear + "&semester=" + semester;
	}
</script>
<body onload="loadData()">
	<div style="margin-left: 7px;margin-bottom: 10px">
		<label><b>个人课表&nbsp;&nbsp;</b></label>
			<select id="schoolYear" onchange="showTimetable()">
				<c:forEach begin="2013" end="2017" var="n">
					<option>${n-1 }-${n }</option>
				</c:forEach>
			</select>
			学年&nbsp;&nbsp;
			第
			<select id="semester" onchange="showTimetable()">
				<option value="1">1</option>
				<option value="2">2</option>
			</select>
			学期
	</div>
	<table id="t1" style="margin-left: 20px;">
		<tbody>
			<tr>
				<td width="40px" height="30px"></td>
				<td width="150px">周一</td>
				<td width="150px">周二</td>
				<td width="150px">周三</td>
				<td width="150px">周四</td>
				<td width="150px">周五</td>
				<td width="150px">周六</td>
				<td width="150px">周日</td>
			</tr>
			<tr>
				<td height="70px">1</td>
				<c:forEach begin="1" end="7" var="n">
					<td rowspan="2" style="font-size: 14px">
						<c:forEach items="${list }" var="course" varStatus="x">
							<c:forEach items="${time }" var="subTime" begin="${x.index }" end="${x.index }">
								<c:forEach items="${subTime }" var="st" varStatus="y">
									<c:set var="st2" value="1,2"></c:set>
									<c:if test="${st == st2}">
										<c:forEach items="${weekday }" var="subWeekday" begin="${x.index }" end="${x.index }">
											<c:forEach items="${subWeekday }" var="swd" begin="${y.index }" end="${y.index }">
												<c:if test="${swd == n }">
													<c:forEach items="${week }" var="subWeek" begin="${x.index }" end="${x.index }">
														<c:forEach items="${subWeek }" var="sw" begin="${y.index }" end="${y.index }">
															<c:forEach items="${place }" var="subPlace" begin="${x.index }" end="${x.index }">
																<c:forEach items="${subPlace }" var="sp" begin="${y.index }" end="${y.index }">
																	${sw }周<br>
																	周${swd }第${st }节&nbsp;${sp }<br>
																	${course.teacherName }<br>
																	${course.courseName }
																</c:forEach>
															</c:forEach>
														</c:forEach>
													</c:forEach>
												</c:if>
											</c:forEach>
										</c:forEach>
									</c:if>
								</c:forEach>
							</c:forEach>
						</c:forEach>
					</td>
				</c:forEach>
			</tr>
			<tr><td height="70px">2</td></tr>
			<tr>
				<td height="70px">3</td>
				<c:forEach begin="1" end="7" var="n">
					<td rowspan="2" style="font-size: 14px">
						<c:forEach items="${list }" var="course" varStatus="x">
							<c:forEach items="${time }" var="subTime" begin="${x.index }" end="${x.index }">
								<c:forEach items="${subTime }" var="st" varStatus="y">
									<c:set var="st2" value="3,4"></c:set>
									<c:if test="${st == st2}">
										<c:forEach items="${weekday }" var="subWeekday" begin="${x.index }" end="${x.index }">
											<c:forEach items="${subWeekday }" var="swd" begin="${y.index }" end="${y.index }">
												<c:if test="${swd == n }">
													<c:forEach items="${week }" var="subWeek" begin="${x.index }" end="${x.index }">
														<c:forEach items="${subWeek }" var="sw" begin="${y.index }" end="${y.index }">
															<c:forEach items="${place }" var="subPlace" begin="${x.index }" end="${x.index }">
																<c:forEach items="${subPlace }" var="sp" begin="${y.index }" end="${y.index }">
																	${sw }周<br>
																	周${swd }第${st }节&nbsp;${sp }<br>
																	${course.teacherName }<br>
																	${course.courseName }
																</c:forEach>
															</c:forEach>
														</c:forEach>
													</c:forEach>
												</c:if>
											</c:forEach>
										</c:forEach>
									</c:if>
								</c:forEach>
							</c:forEach>
						</c:forEach>
					</td>
				</c:forEach>
			</tr>
			<tr><td height="70px">4</td></tr>
			<tr><td height="70px">5</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td height="70px">6</td>
				<c:forEach begin="1" end="7" var="n">
					<td rowspan="2" style="font-size: 14px">
						<c:forEach items="${list }" var="course" varStatus="x">
							<c:forEach items="${time }" var="subTime" begin="${x.index }" end="${x.index }">
								<c:forEach items="${subTime }" var="st" varStatus="y">
									<c:set var="st2" value="6,7"></c:set>
									<c:if test="${st == st2}">
										<c:forEach items="${weekday }" var="subWeekday" begin="${x.index }" end="${x.index }">
											<c:forEach items="${subWeekday }" var="swd" begin="${y.index }" end="${y.index }">
												<c:if test="${swd == n }">
													<c:forEach items="${week }" var="subWeek" begin="${x.index }" end="${x.index }">
														<c:forEach items="${subWeek }" var="sw" begin="${y.index }" end="${y.index }">
															<c:forEach items="${place }" var="subPlace" begin="${x.index }" end="${x.index }">
																<c:forEach items="${subPlace }" var="sp" begin="${y.index }" end="${y.index }">
																	${sw }周<br>
																	周${swd }第${st }节&nbsp;${sp }<br>
																	${course.teacherName }<br>
																	${course.courseName }
																</c:forEach>
															</c:forEach>
														</c:forEach>
													</c:forEach>
												</c:if>
											</c:forEach>
										</c:forEach>
									</c:if>
								</c:forEach>
							</c:forEach>
						</c:forEach>
					</td>
				</c:forEach>
			</tr>
			<tr><td height="70px">7</td></tr>
			<tr>
				<td height="70px">8</td>
				<c:forEach begin="1" end="7" var="n">
					<td rowspan="2" style="font-size: 14px">
						<c:forEach items="${list }" var="course" varStatus="x">
							<c:forEach items="${time }" var="subTime" begin="${x.index }" end="${x.index }">
								<c:forEach items="${subTime }" var="st" varStatus="y">
									<c:set var="st2" value="8,9"></c:set>
									<c:if test="${st == st2}">
										<c:forEach items="${weekday }" var="subWeekday" begin="${x.index }" end="${x.index }">
											<c:forEach items="${subWeekday }" var="swd" begin="${y.index }" end="${y.index }">
												<c:if test="${swd == n }">
													<c:forEach items="${week }" var="subWeek" begin="${x.index }" end="${x.index }">
														<c:forEach items="${subWeek }" var="sw" begin="${y.index }" end="${y.index }">
															<c:forEach items="${place }" var="subPlace" begin="${x.index }" end="${x.index }">
																<c:forEach items="${subPlace }" var="sp" begin="${y.index }" end="${y.index }">
																	${sw }周<br>
																	周${swd }第${st }节&nbsp;${sp }<br>
																	${course.teacherName }<br>
																	${course.courseName }
																</c:forEach>
															</c:forEach>
														</c:forEach>
													</c:forEach>
												</c:if>
											</c:forEach>
										</c:forEach>
									</c:if>
								</c:forEach>
							</c:forEach>
						</c:forEach>
					</td>
				</c:forEach>
			</tr>
			<tr><td height="70px">9</td></tr>
			<tr>
				<td height="70px">10</td>
				<c:forEach begin="1" end="7" var="n">
					<td rowspan="3" style="font-size: 14px">
						<c:forEach items="${list }" var="course" varStatus="x">
							<c:forEach items="${time }" var="subTime" begin="${x.index }" end="${x.index }">
								<c:forEach items="${subTime }" var="st" varStatus="y">
									<c:set var="st2" value="10,11,12"></c:set>
									<c:if test="${st == st2}">
										<c:forEach items="${weekday }" var="subWeekday" begin="${x.index }" end="${x.index }">
											<c:forEach items="${subWeekday }" var="swd" begin="${y.index }" end="${y.index }">
												<c:if test="${swd == n }">
													<c:forEach items="${week }" var="subWeek" begin="${x.index }" end="${x.index }">
														<c:forEach items="${subWeek }" var="sw" begin="${y.index }" end="${y.index }">
															<c:forEach items="${place }" var="subPlace" begin="${x.index }" end="${x.index }">
																<c:forEach items="${subPlace }" var="sp" begin="${y.index }" end="${y.index }">
																	${sw }周<br>
																	周${swd }第${st }节&nbsp;${sp }<br>
																	${course.teacherName }<br>
																	${course.courseName }
																</c:forEach>
															</c:forEach>
														</c:forEach>
													</c:forEach>
												</c:if>
											</c:forEach>
										</c:forEach>
									</c:if>
								</c:forEach>
							</c:forEach>
						</c:forEach>
					</td>
				</c:forEach>
			</tr>
			<tr><td height="70px">11</td></tr>
			<tr><td height="70px">12</td></tr>
		</tbody>
	</table>
</body>
</html>





