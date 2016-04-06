$(document).ready(function(){
	$('#addsheetform').submit(function() {
		// submit the form
	    $(this).ajaxSubmit(function(message){
	    	if(message=='error'){
	    		alert("添加失败");
	    	}
	    	else{
	    		alert("添加成功，编号为"+message);
	    		$('#addsheetform').resetForm();
	    	}
	    });
	    
	    // return false to prevent normal browser submit and page navigation
	    return false;
	});
	$("td").delegate(".sheet-delete","click",function(){
		var sheet_id = $(this).attr("value");
		var td=$(this).parent(); 
		var tr=td.parent(); 
		if(confirm("确定要删除编号为"+sheet_id+"的问题?")){
			$.post("DeleteSingleSheetServlet",
			  {
			    sheet_id: sheet_id
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