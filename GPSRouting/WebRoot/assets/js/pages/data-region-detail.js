/**
 * 
 */
$(document).ready(function(){
	$("#region-edit-button").on("click", function(){
		if($("#region-type").attr("value") == "site"){
			$("#region-edit-range-group").hide();
		}
		$("#region-edit-id").val($("#region-id").text());
		$("#region-edit-name").val($("#region-name").text());
		$("#region-edit-intro").val($("#region-intro").text());
		$("#region-edit-range").val($("#region-range").text());
		$("#region-edit-static").text($("#region-id").text());
	});
	$('#changeregion').submit(function() {
	    // submit the form
	    $(this).ajaxSubmit(function(message){
	    	if(message=='error_no_index'){
	    		alert("修改失败，修改值不能为空");
	    	}
	    	else if(message == 'error'){
	    		alert("修改失败，无此区域")
	    	}
	    	else if(message == '1'){
	    		alert("修改成功");
	    		$('#changeregion').resetForm();
	    		window.location.reload();
	    	}
	    	else {
	    		alert("修改失败，无此区域")
	    	}
	    });
	   
	    
	    // return false to prevent normal browser submit and page navigation
	    return false;
	});
	 $('#addquestion').submit(function() {
		    // submit the form
		    $(this).ajaxSubmit(function(message){
		    	if (message != 'error'){
		    		alert("添加成功成功,编号为"+message);
		    		var actions="<a class = \"edit pull-right bk-margin-left-10\" data-toggle=\"modal\" data-target=\"#myModal\" href = \"#\" value=\""+ message +"\"><i class=\"fa fa-pencil fa-2x\"></i></a><a class = \"delete pull-right\" value=\""+ message+"\" href = \"#\"><i class=\"fa fa-trash-o fa-2x\"></i></a>"

		    		var str = "<tr id="+ message +">"+"<td>"+$("#question-title").val()+"</td>"+"<td>"+$("#question-possasws").val()+"</td>"+"<td>"+$("#question-normalasws").val()+actions+"</td>";
		    		
		    		$("#questions-table").append(str);
		    		$('#addquestion').resetForm();
		    	}
		    	else {
		    		alert("添加失败")
		    	}
		    });
		    return false;
		});
		    
	
});