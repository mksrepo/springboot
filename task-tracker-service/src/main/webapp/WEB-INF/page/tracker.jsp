<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Task Tracker</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript" src="/js/main.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
    	$(".save").click(function(){
    		var btnValue = $(this).val();
    		var taskId=btnValue.substr(btnValue.indexOf('_')+1,btnValue.length);
    		saveTask(taskId);
    	});
 	});
</script>
</head>
<body>
	<table>
		<tr>
			<td>Title</td>
			<td>Owner</td>
			<td>Status</td>
			<td>Complexity</td>
			<td>Detail Estimate Hrs.</td>
			<td>Done Hrs.</td>
			<td>Effort Hrs.</td>
			<td>To Do Hrs.</td>
			<td>Action</td>
		</tr>
		<tr>
			<td>
				<input type="text" id="title_0" name="title_0" />
			</td>
			<td>
				<input type="text" id="owner_0" name="owner_0" />
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
				<input type="text" id="estHrs_0" name="estHrs_0" />
			</td>
			<td>
				<label>0.0 </label>
			</td>
			<td>
				<input type="text" id="effortHrs_0" name="effortHrs_0" />
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