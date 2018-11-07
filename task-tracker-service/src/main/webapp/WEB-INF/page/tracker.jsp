<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Task Tracker</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="/js/main.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
    	jQuery(".save").click(function(){
    		var btnValue = $(this).val();
    		var index=btnValue.substr(btnValue.indexOf('_')+1,btnValue.length);
    		saveTask(index);
    	});
 	});
</script>
</head>
<body>
	<table>
		<tr>
			<td>Title</td>
			<td>Code</td>
			<td>Owner</td>
			<td>Status</td>
			<td>Complexity</td>
			<td>Detail Estimate Hrs.</td>
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
					<input type="text" id="taskCode_${loop.count}" name="taskCode_${loop.count}" value="${task.taskCode}"/>
				</td>
				<td>
					<input type="text" id="owner_${loop.count}" name="owner_${loop.count}" value="${task.owner}"/>
				</td>
				<td>
					<select id="status_${loop.count}" name="status_${loop.count}">
						<option value="Open">Open</option>
						<option value="Completed">Completed</option>
					</select>
				</td>
				<td>
					<select id="complexity_${loop.count}" name="complexity_${loop.count}">
						<option value="Easy">Easy</option>
						<option value="Average">Average</option>
						<option value="Complex">Complex</option>
					</select>
				</td>
				<td>
					<input type="text" id="detailEstimateHrs_${loop.count}" name="detailEstimateHrs_${loop.count}" value="${task.detailEstimateHrs}"/>
				</td>
				<td>
					<label>0.0 </label>
				</td>
				<td>
					<input type="text" id="effortHrs_${loop.count}" name="effortHrs_${loop.count}" value="${task.effortHrs}"/>
				</td>
				<td>
					<label>0.0 </label>
				</td>
				<td>
					<button class="save" id="save" name="save" value="save_${loop.count}">Save</button>
					<button class="close" id="close" name="close" value="close_${loop.count}">Close</button>
				</td>
			</tr>
		</c:forEach>
		<tr height="50">
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
				<input type="text" id="title_0" name="title_0" value="FSD Create"/>
			</td>
			<td>
				<input type="text" id="taskCode_0" name="taskCode_0" value=""/>
			</td>
			<td>
				<input type="text" id="owner_0" name="owner_0" value="Khushboo"/>
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
				<input type="text" id="detailEstimateHrs_0" name="detailEstimateHrs_0" value="24"/>
			</td>
			<td>
				<label>0.0 </label>
			</td>
			<td>
				<input type="text" id="effortHrs_0" name="effortHrs_0" value="8"/>
			</td>
			<td>
				<label>0.0 </label>
			</td>
			<td>
				<button class="save" id="save" name="save" value="save_0">Save</button>
				<button class="close" id="close" name="close" value="close_0">Close</button>
			</td>
		</tr>
	</table>
</body>
</html>