$(document).ready(function(){
	$('#add-sheet-form').submit(function() {
		    // submit the form
		    $(this).ajaxSubmit(function(message){
		    	if(message=='error'){
		    		alert("添加失败");
		    	}
		    	else{
		    		alert("添加成功，编号为"+message);
		    		$('#addregion').resetForm();
		    	}
		    });
		    
		    // return false to prevent normal browser submit and page navigation
		    return false;
		});
	});