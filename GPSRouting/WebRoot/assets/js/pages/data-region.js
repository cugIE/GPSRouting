$(document).ready(function(){
	
//	$(".region-delete").on("click",function(){
//		if(confirm("确定要删除编号"+this.val()+"?"){
//			
//		}
//		else{
//			
//		}
//		
//	});
	$(".region-delete").on("click",function(){
		var region = $(this).attr("value");
		if(confirm("确定要删除编号为"+region+"的设备?")){
		$.post("DeleteSingleRegionServlet",
		  {
		    region_id: region
		  },
		  function(data,status){
			  if(status == "success"){
				  if (data=="1"){
					  alert("删除成功");
					  window.location.reload();
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
	var check_type=$("#region_type").val();
	if (check_type == 'site'){
		$("#range_group").hide();
	}
	$("#region_type").change(function(){
		var type=$("#region_type").val();
		if (type == 'site'){
			$("#range_group").fadeOut();
		}
		else{
			$("#range_group").fadeIn();
		}
	});
	$('#addregion').submit(function() {
	    // submit the form
	    $(this).ajaxSubmit(function(message){
	    	if(message=='error'){
	    		alert("添加失败");
	    	}
	    	else{
	    		alert("添加成功，区域编号为"+message);
	    		$('#addregion').resetForm();
	    	}
	    });
	    
	    // return false to prevent normal browser submit and page navigation
	    return false;
	});
	
});