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
	$(".question-edit").on("click", function(){
		var td=$(this).parent(); 
		var tr=td.parent();
		var id = tr.attr("value");
		var title = tr.children('td').eq(0).text();
		var possasws = tr.children('td').eq(1).text();
		var normalasws = tr.children('td').eq(2).text();
		$("#question-edit-id").val(id);
		$("#question-edit-title").val(title);
		$("#question-edit-possasws").val(possasws);
		$("#question-edit-normalasws").val(normalasws);
		
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
	$('#changequestion').submit(function() {
	    // submit the form
	    $(this).ajaxSubmit(function(message){
	    	
	    	if(message == '1'){
	    		alert("修改成功");
	    		$('#changequestion').resetForm();
	    		window.location.reload();
	    	}
	    	else {
	    		alert("修改失败")
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
		    		var actions="<a class = \"question-edit pull-right bk-margin-left-10\" data-toggle=\"modal\" data-target=\"#myModal\" href = \"#\" value=\""+ message +"\"><i class=\"fa fa-pencil fa-2x\"></i></a>"
//<a class = \"pull-right question-delete\" value=\""+ message+"\" href = \"#\"><i class=\"fa fa-trash-o fa-2x\"></i></a>
		    		var str = "<tr id="+ message +">"+"<td>"+$("#question-title").val()+"</td>"+"<td>"+$("#question-possasws").val()+"</td>"+"<td>"+$("#question-normalasws").val()+"</td>";
		    		
		    		$("#questions-table").append(str);
		    		$('#addquestion').resetForm();
		    		//window.location.reload();
		    	}
		    	else {
		    		alert("添加失败")
		    	}
		    });
		    return false;
		});
	 $("td").delegate(".question-delete","click",function(){
		var question_id = $(this).attr("value");
		var td=$(this).parent(); 
		var tr=td.parent(); 
		if(confirm("确定要删除编号为"+question_id+"的问题?")){
			$.post("DeleteSingleQuestionServlet",
			  {
			    question_id: question_id
			  },
			  function(data,status){
				  if(status == "success"){
					  if (data=="1"){
						  alert("删除成功");
						  tr.remove();
					  }
					  else{
						  alert("删除失败");
					  }
				  }
				  else{
					  alert("链接超时");
				  }
				  
			  });
			}else{
				
			}
		});
	
});