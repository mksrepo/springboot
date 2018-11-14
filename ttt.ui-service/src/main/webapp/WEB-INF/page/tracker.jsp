<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Development Tracking Tool</title>
	<link rel="stylesheet" href="/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/css/dataTables.bootstrap4.min.css" />
	<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="/js/dataTables.bootstrap4.min.js"></script>
	<script type="text/javascript" src="/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/main.js"></script>
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {
			$('table').dataTable({searching: true, paging: true, info: false});
		    $('#taskDataTable').DataTable();
		    $("#taskDataTable, #modalContainer").on("click",".save_task",function(){saveTask($(this).data("index"));});
		    $("#modalContainer").on("click",".delete_task",function(){deleteTask($(this).val());});
		    $("#taskDataTable").on("click",".delete_confirm",function(){$("#deleteModalYes").val($(this).data("id"));});
		    //$(".delete_confirm").click(function(){$("#deleteModalYes").val($(this).data("id"));});
		});
	</script>
	<!-- Modal Section -->
	<div id="modalContainer" class="container">
		<!-- Modal :: Task Entry -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Task Creation Window</h4>
					</div>
					<div class="modal-body">
						<div class="md-form mb-5">
							<label for="title_0">Title</label>
							<input type="text" id="title_0" class="form-control">
						</div>
						<div class="md-form mb-5">
							<label for="owner_0">Owner</label>
							<input type="text" id="owner_0" class="form-control validate">
						</div>
						<div class="md-form mb-5">
							<label for="owner_0">Complexity</label> <select id="complexity_0" class="form-control">
								<option value="Easy">Easy</option>
								<option value="Average">Average</option>
								<option value="Complex">Complex</option>
							</select>
						</div>
						<div class="md-form mb-5">
							<label for="detailEstimateHrs_0">Estimate Hrs.</label>
							<input type="text" id="detailEstimateHrs_0" class="form-control validate">
						</div>
					</div>
					<div class="modal-footer">
						<input type="hidden" id="status_0" name="status_0" value="Open" />
						<input type="hidden" id="taskCode_0" name="taskCode_0" value="" />
						<input type="hidden" id="effortHrs_0" name="effortHrs_0" value="0" />
						<input type="hidden" id="doneHrs_0" name="doneHrs_0" value="0" />
						<input type="hidden" id="toDoHrs_0" name="toDoHrs_0" value="" />
						<input type="hidden" id="id_0" name="id_0" value="0" />
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="save_task btn btn-default" data-index="0">Create Task</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal :: Confirm window -->
		<div class="modal fade" id="confirmModal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Delete Confirmation Window</h4>
					</div>
					<div class="modal-body">
						<p>Are you sure you want to delete this task?</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
						<button id="deleteModalYes" type="button" class="delete_task btn btn-default" value="">Yes</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Data Table -->
	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<img alt="" src="/img/HFU.png" width="125%"/>
			</div>
			<div class="col-sm-8">
				<h2>Task Tracking Tool</h2>
			</div>
			<div class="col-sm-2">
				<a href="#" class="a-style" data-toggle="modal" data-target="#myModal">Add Task</a>&nbsp;||&nbsp;<a href="/tracker/export" class="a-style">Export CSV</a>
			</div>
		</div>
	</div>
	<div class="container">
		<table id="taskDataTable" class="table table-striped table-bordered" style="width:100%">
	        <thead>
	            <tr>
	                <th>Title</th>
					<th>Code</th>
					<th>Owner</th>
					<th>Status</th>
					<th>Complexity</th>
					<th>Estimate Hrs.</th>
					<th>Done Hrs.</th>
					<th>Effort Hrs.</th>
					<th>To Do Hrs.</th>
					<th>Action</th>
	            </tr>
	        </thead>
	         <tbody class="container-min-hight">
	        	<c:choose>
					<c:when test="${fn:length(ALL_TASK) gt 0}">
			        	<c:forEach var="task" items="${ALL_TASK}" varStatus="loop">
				            <tr>
				                <td>
									<input type="hidden" id="title_${loop.count}" name="title_${loop.count}" value="${task.title}"/>
									${task.title}
								</td>
				                <td>
									<input type="hidden" id="taskCode_${loop.count}" name="taskCode_${loop.count}" value="${task.taskCode}"/>
									${task.taskCode}
								</td>
				                <td>
									<input type="hidden" id="owner_${loop.count}" name="owner_${loop.count}" value="${task.owner}"/>
									${task.owner}
								</td>
				                <td>
									<select id="status_${loop.count}" class="browser-default custom-select">
										<option ${"Open" eq task.status?'selected':""} value="Open">Open</option>
										<option ${"Close" eq task.status?'selected':""} value="Close">Close</option>
									</select>
								</td>
				                <td>
									<input type="hidden" id="complexity_${loop.count}" name="complexity_${loop.count}" value="${task.complexity}"/>
									${task.complexity}
								</td>
				                <td>
									<input type="hidden" id="detailEstimateHrs_${loop.count}" name="detailEstimateHrs_${loop.count}" value="${task.detailEstimateHrs}"/>
									${task.detailEstimateHrs}
								</td>
				                <td>
									<input type="hidden" id="doneHrs_${loop.count}" name="doneHrs_${loop.count}" value="${task.doneHrs}"/>
									${task.doneHrs}
								</td>
				                <td>
									<input type="text" size="5" class="effortHrs" id="effortHrs_${loop.count}" name="effortHrs_${loop.count}" value="${task.effortHrs}"/>
								</td>
				                <td>
									<input type="hidden" id="toDoHrs_${loop.count}" name="toDoHrs_${loop.count}" value="${task.toDoHrs}"/>
									${task.toDoHrs}
								</td>
				                <td>
				                	<input type="hidden" id="id_${loop.count}" name="id_${loop.count}"  value="${task.id}"/>
				                	<a href="#" class="save_task a-style" data-index="${loop.count}">Update</a>
				                	&nbsp;&nbsp;&nbsp;
									<a href="#" class="delete_confirm a-style" data-id="${task.id}" data-toggle="modal" data-target="#confirmModal">Delete</a>
								</td>
				            </tr>
				        </c:forEach>
				    </c:when>
				</c:choose>
			</tbody>
	    </table>
	</div>
</body>
</html>