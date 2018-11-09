<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Task Tracker</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="/js/main.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
    	jQuery(".save").click(function(){saveTask($(this).val());});
 	});
</script>
</head>
<body>
	<c:if test="${fn:length(ALL_TASK) gt 0}">
		<table>
			<tr>
				<td>Title</td>
				<td>Code</td>
				<td>Owner</td>
				<td>Status</td>
				<td>Complexity</td>
				<td>Estimate Hrs.</td>
				<td>Done Hrs.</td>
				<td>Effort Hrs.</td>
				<td>To Do Hrs.</td>
				<td>Action</td>
			</tr>
			<c:forEach var="task" items="${ALL_TASK}" varStatus="loop">
				<tr>
					<td>
						<input type="text" id="title_${loop.count}" name="title_${loop.count}" value="${task.title}"/>
					</td>
					<td>
						<input type="text" readonly="readonly" id="taskCode_${loop.count}" name="taskCode_${loop.count}" value="${task.taskCode}"/>
					</td>
					<td>
						<input type="text" id="owner_${loop.count}" name="owner_${loop.count}" value="${task.owner}"/>
					</td>
					<td>
						<select id="status_${loop.count}" name="status_${loop.count}">
							<option ${task.status=="Open"?"Selected":""} value="Open">Open</option>
							<option ${task.status=="Completed"?"Selected":""} value="Completed">Completed</option>
						</select>
					</td>
					<td>
						<select id="complexity_${loop.count}" name="complexity_${loop.count}">
							<option ${task.complexity=="Easy"?"Selected":""} value="Easy">Easy</option>
							<option ${task.complexity=="Average"?"Selected":""} value="Average">Average</option>
							<option ${task.complexity=="Complex"?"Selected":""} value="Complex">Complex</option>
						</select>
					</td>
					<td>
						<input type="text" readonly="readonly" id="detailEstimateHrs_${loop.count}" name="detailEstimateHrs_${loop.count}" value="${task.detailEstimateHrs}"/>
					</td>
					<td>
						
						<input type="text" readonly="readonly" id="doneHrs_${loop.count}" name="doneHrs_${loop.count}" value="${task.doneHrs}"/>
					</td>
					<td>
						<input type="text" class="effortHrs" id="effortHrs_${loop.count}" name="effortHrs_${loop.count}" value="${task.effortHrs}"/>
					</td>
					<td>
						<input type="text" readonly="readonly" id="toDoHrs_${loop.count}" name="toDoHrs_${loop.count}" value="${task.toDoHrs}"/>
					</td>
					<td>
						<input type="hidden" id="id_${loop.count}" name="id_${loop.count}"  value="${task.id}"/>
						<button class="save" id="save_${loop.count}" name="save_${loop.count}" value="${loop.count}">Update</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<table>
		<tr>
			<td>Title</td>
			<td>Owner</td>
			<td>Status</td>
			<td>Complexity</td>
			<td>Estimate Hrs.</td>
			<td>Action</td>
		</tr>
		<tr>
			<td>
				<input type="text" id="title_0" name="title_0" value=""/>
			</td>
			<td>
				<input type="text" id="owner_0" name="owner_0" value=""/>
			</td>
			<td>
				<select id="status_0" name="status_0">
					<option value="Open">Open</option>
					<option value="Completed">Completed</option>
				</select>
			</td>
			<td>
				<select id="complexity_0" name="complexity_0">
					<option value="Easy">Easy</option>
					<option value="Average">Average</option>
					<option value="Complex">Complex</option>
				</select>
			</td>
			<td>
				<input type="text" id="detailEstimateHrs_0" name="detailEstimateHrs_0" value=""/>
			</td>
			<td>
				<input type="hidden" id="taskCode_0" name="taskCode_0" value=""/>
				<input type="hidden" id="effortHrs_0" name="effortHrs_0" value="0"/>
				<input type="hidden" id="doneHrs_0" name="doneHrs_0" value="0"/>
				<input type="hidden" id="toDoHrs_0" name="toDoHrs_0" value=""/>
				<input type="hidden" id="id_0" name="id_0"  value="0" />
				<button class="save" id="save_0" name="save_0" value="0">Create</button>
			</td>
		</tr>
	</table>
</body>
</html>