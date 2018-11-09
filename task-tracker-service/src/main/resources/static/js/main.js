function saveTask(index) {
	var id = $('#id_'+index).val();
	var taskCode = $('#taskCode_'+index).val();
	var title = $('#title_'+index).val();
	var owner = $('#owner_'+index).val();
	var status = $('#status_'+index).val();
	var complexity = $('#complexity_'+index).val();
	var detailEstimateHrs = $('#detailEstimateHrs_'+index).val();
	var effortHrs = $('#effortHrs_'+index).val();
	var toDoHrs = $('#toDoHrs_'+index).val();
	var doneHrs = $('#doneHrs_'+index).val();
	var requestPayload = '{"id":"'+id+'","taskCode":"'+taskCode+'","title":"'+title+'","owner":"'+owner+'","status":"'+status+'","complexity":"'+complexity+'","detailEstimateHrs":"'+detailEstimateHrs+'","effortHrs":"'+effortHrs+'","toDoHrs":"'+toDoHrs+'","doneHrs":"'+doneHrs+'"}';
	console.log(requestPayload);
	if(!validateRequest(index, title, owner, status, complexity, detailEstimateHrs, effortHrs, toDoHrs)) return;
	
	$.ajax({
	   url: "/tracker/task/save",
	   method: "POST",
	   data: requestPayload,
	   dataType: 'text',
	   contentType: "application/json",
	   success: function(result, status, jqXHR){
		   location.reload();
		   alert(result);
	   },
	   error(jqXHR, textStatus, errorThrown){
		   console.log( errorThrown );
	   }
	}); 
}

function validateRequest(index, title, owner, status, complexity, detailEstimateHrs, effortHrs, toDoHrs){
	if(index==0){
		if($('#title_0').val().trim().length==0 || $('#owner_0').val().trim().length==0 || $('#detailEstimateHrs_0').val().trim().length==0){
			alert("Warning! Please Enter All Require Fields.");
			$('#title_0').focus();
			return false;
		}
		if(isNaN(parseInt(detailEstimateHrs)) || parseInt(detailEstimateHrs)<0){
			alert("Warning! Invalide Estimate Hrs.");
			$('#detailEstimateHrs_'+index).focus();
			return false;
		}
	}else{
		if(title.trim().length==0 || owner.trim().length==0 || status.trim().length==0 || effortHrs.trim().length==0){
			alert("Warning! Please Enter All Require Fields.");
			$('#title_'+index).focus();
			return false;
		}
		if(isNaN(parseInt(effortHrs)) || parseInt(effortHrs)<0){
			alert("Warning! Invalide Effort Hrs.");
			$('#effortHrs_'+index).focus();
			return false;
		}
		if(parseInt(effortHrs) > parseInt(toDoHrs)){
			alert("Warning! Invalide Effort Hrs.");
			$('#effortHrs_'+index).focus();
			return false;
		}
	}
	return true;
}
