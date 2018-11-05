function saveTask(taskId) {
	var title = $('#title_'+taskId).val();
	var owner = $('#owner_'+taskId).val();
	var status = $('#status_'+taskId).val();
	var complexity = $('#complexity_'+taskId).val();
	var detailEstimateHrs = $('#detailEstimateHrs_'+taskId).val();
	var effortHrs = $('#effortHrs_'+taskId).val();
	
	
	var JSONString = '{"title":"'+title+'","owner":"'+owner+'","status":"'+status+'","complexity":"'+complexity+'","detailEstimateHrs":"'+detailEstimateHrs+'","effortHrs":"'+effortHrs+'"}';
	var owner;
	var status;
	var complexity;
	var detailEstimateHrs;
	var doneHrs;
	var effortHrs;
	var toDoHrs;
	$.ajax({
	   url: "/tracker/task/save",
	   method: "POST",
	   data: JSONString,
	   dataType: 'json',
	   contentType: "application/json",
	   success: function(result,status,jqXHR ){
		   alert("Task has been saved successfully.");
	   },
	   error(jqXHR, textStatus, errorThrown){
	   }
	}); 
}