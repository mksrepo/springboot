<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Development Tracking Tool</title>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<link rel="stylesheet" href="/css/font-awesome.min.css">
	<link rel="stylesheet" href="/css/main.css">
	<link href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/compiled-4.5.13.min.css" rel="stylesheet">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/2.0.0/clipboard.min.js"></script>
	<script type="text/javascript" src="/js/main.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
	    	jQuery(".save_task").click(function(){saveTask($(this).data("index"));});
	 	});
	</script>
</head>
<body aria-busy="true">
	<div class="wrapper-modal-editor">
		<!-- Page Title -->
		<div class="block my-4">
			<div class="d-flex justify-content-center">
				<p class="h5 text-primary createShowP">Development Tracking Tool</p>
			</div>
		</div>
		<!-- Page Body -->
		<div class="row d-flex justify-content-center modalWrapper">
			<div class="modal fade addNewInputs" id="modalAdd" tabindex="-1" role="dialog" aria-labelledby="modalAdd" aria-hidden="true">
				<!-- Task adding widow -->
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header text-center">
							<h4 class="modal-title w-100 font-weight-bold text-primary ml-5">
								Task Creation Page
							</h4>
							<button type="button" class="close text-primary" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">X</span>
							</button>
						</div>
						<div class="modal-body mx-3">
							<div class="md-form mb-5">
								<input type="text" id="title_0" class="form-control validate">
								<label for="title_0">Title</label>
							</div>
							<div class="md-form mb-5">
								<input type="text" id="owner_0" class="form-control validate">
								<label for="owner_0">Owner</label>
							</div>
							<div class="md-form mb-5">
								<select id="complexity_0" class="browser-default custom-select">
									<option value="Complexity" selected disabled>Complexity</option>
									<option value="Easy">Easy</option>
									<option value="Average">Average</option>
									<option value="Complex">Complex</option>
								</select>
							</div>
							<div class="md-form mb-5">
								<input type="text" id="detailEstimateHrs_0" class="form-control validate">
								<label for="detailEstimateHrs_0">Estimate Hrs.</label>
							</div>
						</div>
						<div class="modal-footer d-flex justify-content-center buttonAddFormWrapper">
							<input type="hidden" id="status_0" name="status_0" value="Open"/>
							<input type="hidden" id="taskCode_0" name="taskCode_0" value=""/>
							<input type="hidden" id="effortHrs_0" name="effortHrs_0" value="0"/>
							<input type="hidden" id="doneHrs_0" name="doneHrs_0" value="0"/>
							<input type="hidden" id="toDoHrs_0" name="toDoHrs_0" value=""/>
							<input type="hidden" id="id_0" name="id_0"  value="0" />
							<button class="save_task btn btn-outline-primary btn-block buttonAdd waves-effect waves-light" data-index="0">
								Add Task <i class="fa fa-paper-plane-o ml-1"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="text-center">
				<a href="" class="btn btn-info btn-rounded btn-sm waves-effect waves-light" data-toggle="modal" data-target="#modalAdd">
					Add<i class="fa fa-plus-square ml-1"></i>
				</a>
				<a href="/tracker/export" class="btn btn-info btn-rounded btn-sm waves-effect waves-light">
					Export<i class="fa fa-file-excel-o ml-1"></i>
				</a>
			</div>
		</div>
		<table id="dtBasicExample" class="table table-striped table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr class="header-style">
					<th class="th-sm">Title</th>
					<th class="th-sm">Code</th>
					<th class="th-sm">Owner</th>
					<th class="th-sm">Status</th>
					<th class="th-sm">Complexity</th>
					<th class="th-sm">Estimate Hrs.</th>
					<th class="th-sm">Done Hrs.</th>
					<th class="th-sm">Effort Hrs.</th>
					<th class="th-sm">To Do Hrs.</th>
					<th class="th-sm">Action</th>
				</tr>
			</thead>
			<c:choose>
				<c:when test="${fn:length(ALL_TASK) gt 0}">
					<tbody>
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
								<td align="center">
									<input type="text" size="5" class="effortHrs" id="effortHrs_${loop.count}" name="effortHrs_${loop.count}" value="${task.effortHrs}"/>
								</td>
								<td>
									<input type="hidden" id="toDoHrs_${loop.count}" name="toDoHrs_${loop.count}" value="${task.toDoHrs}"/>
									${task.toDoHrs}
								</td>
								<td align="center">
									<input type="hidden" id="id_${loop.count}" name="id_${loop.count}"  value="${task.id}"/>
									<div class="text-center">
										<a href="" class="save_task btn btn-info btn-rounded btn-sm waves-effect waves-light" data-index="${loop.count}">
											Update <i class="fa fa-edit ml-1"></i>
										</a>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<th colspan="10">Empty Task list. Pleas add task by using above "Add +" button.</th>
					</tr>
				</c:otherwise>
			</c:choose>
			<tfoot>
				<tr align="right">
					<th colspan="10">developed by Khushboo</th>
				</tr>
			</tfoot>
		</table>
	</div>
	<script type="text/javascript" src="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/js/compiled-4.5.13.min.js"></script>
</body>
</html>