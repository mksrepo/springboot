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
    	test();
 	});
</script>
</head>
<body>
	<table>
		<tr>
			<td>Title</td>
			<td>Id</td>
			<td>Owner</td>
			<td>Status</td>
			<td>Complexity</td>
			<td>Detail Estimate Hrs.</td>
			<td>Done Hrs.</td>
			<td>To Do Hrs.</td>
			<td>Action</td>
		</tr>
		<tr>
			<td>
				<input type="text" id="id12345" name="id12345" />
			</td>
			<td>
				<label>12345</label>
			</td>
			<td>
				<input type="text" id="owner12345" name="owner12345" />
			</td>
			<td>
				<select id="status12345" name="status12345">
					<option value="Open">Open</option>
					<option value="Completed">Completed</option>
				</select>
			</td>
			<td>
				<select id="complexity12345" name="complexity12345">
					<option value="Easy">Easy</option>
					<option value="Average">Average</option>
					<option value="Complex">Complex</option>
				</select>
			</td>
			<td>
				<input type="text" id="estHrs12345" name="estHrs12345" />
			</td>
			<td>
				<input type="text" id="doneHrs12345" name="doneHrs12345" />
			</td>
			<td>
				<input type="text" id="todoHrs12345" name="todoHrs12345" />
			</td>
			<td>
				<button id="save12345">Save</button>
				<button id="close12345">Close</button>
			</td>
		</tr>
	</table>
</body>
</html>