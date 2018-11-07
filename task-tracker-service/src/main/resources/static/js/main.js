function saveTask(index) {
	var title = $('#title_'+index).val();
	var owner = $('#owner_'+index).val();
	var status = $('#status_'+index).val();
	var complexity = $('#complexity_'+index).val();
	var detailEstimateHrs = $('#detailEstimateHrs_'+index).val();
	var effortHrs = $('#effortHrs_'+index).val();
	var requestPayload = '{"title":"'+title+'","owner":"'+owner+'","status":"'+status+'","complexity":"'+complexity+'","detailEstimateHrs":"'+detailEstimateHrs+'","effortHrs":"'+effortHrs+'"}';
	$.ajax({
	   url: "/tracker/task/save",
	   method: "POST",
	   data: requestPayload,
	   dataType: 'text',
	   contentType: "application/json",
	   success: function(result, status, jqXHR){
		   alert(result);
	   },
	   error(jqXHR, textStatus, errorThrown){
		   console.log( errorThrown );
	   }
	}); 
}
