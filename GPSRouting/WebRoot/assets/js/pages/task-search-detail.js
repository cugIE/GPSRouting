

$(document).ready(function(){
	var allTimes = "00:00,02:00,04:00,06:00,08:00,10:00,12:00,14:00,16:00,18:00,20:00,22:00"
	var usedTimes = new Array();
	$('#sheet-edit-form').submit(function() {
	    // submit the form
	    $(this).ajaxSubmit(function(message){
	    	
	    	if(message == '1'){
	    		alert("修改成功");
	    		$('#sheet-edit-form').resetForm();
	    		window.location.reload();
	    	}
	    	else {
	    		alert("修改失败")
	    	}
	    });
	    return false;
	});
	$('#changeperiodtime').submit(function() {
	    // submit the form
	    $(this).ajaxSubmit(function(message){
	    	
	    	if(message == '1'){
	    		alert("修改成功");
	    		$('#changeperiodtime').resetForm();
	    		window.location.reload();
	    	}
	    	else {
	    		alert("修改失败")
	    	}
	    });
	    return false;
	});
	
	$("#sheet-edit-button").on("click",function(){
		$("#sheet-edit-id").val($("#sheet-id").text());
		$("#sheet-edit-name").val($("#sheet-name").text());
		$("#sheet-edit-intro").val($("#sheet-intro").text());
	  
	});
	 
     $("#shift-add-submit-button").on("click",function(){
    	 var str = "";
    	 var gener_id = "0001";
    	 var sheet_id = $("#sheet-id").text();
    	 $("input[name='times']:checkbox").each(function(){ 
             
    		 if($(this).attr("checked")){
                 str += $(this).val()+","
             }
         })
    	 if(str == ""){
    		 alert("请选择时间点")
    	 }
    	 else{
    		 var shift_name = $("#shift-add-name").val();
    		 $.post("AddPeriodServlet",{
    			 index: "several",
    			 shift: shift_name,
    			 periods: str,
    			 gener_id: gener_id,
    			 sheet_id: sheet_id
    		 },function(data,status){
    			 if(data=="1"){
    				 alert("添加成功");
    				 window.location.reload();
    			 }
    			 else{
    				 alert("添加失败")
    			 }
    			 
    		 });
    	 }
     });
     $(".sheet-delete-shift").on("click",function(){
    	 var shift = $(this).attr("value");
    	 var th = $(this).parents(".panel-accordion");
    	 var sheet_id = $("#sheet-id").text();
    	 if(confirm("确定删除？")){
	    	 $.post("DeletePeriodServlet",{
				 index: "shift",
				 shift: shift,
				 sheet_id: sheet_id
			 },function(data,status){
				 if(data!="error"&&data!="error_index"){
					 alert("删除成功");
					 th.remove();
				 }
				 else{
					 alert("删除失败")
				 }
				 
			 });
    	 }
     });
     $(".sheet-delete-period-time").on("click",function(){
    	 var period = $(this).attr("value");
    	 var th = $(this).parents(".single-time");
    	 if(confirm("确定删除？")){
	    	 $.post("DeletePeriodServlet",{
				 index: "period",
				 period_id: period,
			 },function(data,status){
				 if(data=="1"){
					 alert("删除成功");
					 th.remove();
				 }
				 else{
					 alert("删除失败")
				 }
				 
			 });
    	 }
     });
     
     $(".sheet-add-period").on("click",function(){
    	 
    	 $("#period-add-time-gener").val("0001");
    	 $("#period-add-time-sheet").val($("#sheet-id").text());
    	 $("#period-add-time-shift").val($(this).attr("value"));
    	 var shift = $(this).attr("value");
    	 var parent = $(this).parents(".sheet-times");
    	 var count = parent.children().length-1;
    	 var restTimes = allTimes;
    	 for (var i = 0; i< count; i++){
    		 var tempS = parent.find(".single-time").eq(i).attr("value");
    		 
    		 restTimes = restTimes.replace(tempS.toString(), "");
    	 }
    	 
    	var Times = restTimes.split(/,+/);
    	var len = Times.length;
    		$("#period-add-time-options").children("option").remove();
    		for(var j = 0; j< len ; j++){
        		var temp = Times.shift();
        		if(temp !=""){
        		$("#period-add-time-options").append("<option value='"+ temp + "'>" + temp + "</option>");
        		}
    		}
    		
    	
     });
     $(".sheet-edit-period-button").on("click",function(){
    	 
    	 var values = $(this).attr("value");
    	 var valArray = values.split(",");
    	 $("#period-edit-time-id").val(valArray.shift());
    	 var parent = $(this).parents(".sheet-times");
    	 var count = parent.children().length-1;
    	 var restTimes = allTimes;
    	 for (var i = 0; i< count; i++){
    		 var tempS = parent.find(".single-time").eq(i).attr("value");
    		 
    		 restTimes = restTimes.replace(tempS.toString(), "");
    	 }
    	var Times = restTimes.split(/,+/);
    	var len = Times.length;
		var timeStr = valArray.pop();

		$("#period-edit-time-options").children("option").remove();
		$("#period-edit-time-options").append("<option value='"+timeStr+ "'>" + timeStr + "</option>");

		for(var j = 0; j< len ; j++){
    		var temp = Times.shift();
    		if(temp !=""){
    		$("#period-edit-time-options").append("<option value='"+ temp + "'>" + temp + "</option>");
    	
    		}
		}
    	
    	var shiftStr = valArray.pop();
   	 $("#period-edit-shift-options").append("<option selected='selected' value='"+ shiftStr+ "'>" + shiftStr + "</option>");

    	
     });
     $('#addperiodtime').submit(function() {
 		// submit the form
    	
 	    $(this).ajaxSubmit(function(message){
 	    	if(message=='error'||message=='error_index'){
 	    		alert("添加失败");
 	    	}
 	    	else{
 	    		alert("添加成功");
 	    		window.location.reload();
 	    		
 	    	}
 	    });
 	    
 	    // return false to prevent normal browser submit and page navigation
 	    return false;
 	});
     
	/*---shift ---*/
	
	/* ---------- View ---------- */

     function stringToJson(stringValue) 
     { 
	     eval("var theJsonValue = "+stringValue); 
	     return theJsonValue; 
     } 
    var button_up = "<a class = 'search-regions-actions-delete pull-right'><i class = 'fa fa-trash-o'></i></a><a class = 'search-regions-actions-up pull-right'><i class = ' fa fa-angle-up'></i></a>"
  	var button_left = "<a class ='search-regions-actions pull-left bk-margin-right-10'><i class = 'fa fa-angle-left'></i></a>"
 	$(".sheet-view-period-button").on("click", function(){
 		period_id = $(this).attr("value");
 		var urlall = "GetAllRegionServlet?index=period&period_id="+ $(this).attr("value");
 		var urlrest = "GetAllRegionServlet?index=rest&period_id="+$(this).attr("value")+"&branch_id="+$("#sheet-branch-id").attr("value");
 		
 		$.get(urlall,function(data,status){
 			var srarrays = stringToJson(data);
			$("#SelectedRegions").children().remove();
			
 			for (var i = 0; i<srarrays.length; i++){
 				var currsr = srarrays[i];
	 			$("#SelectedRegions").append("<tr class='selected-region-tr'><td period_id = '"+ period_id + "' region_id = '" + currsr.id + "' id = '" + currsr.ptr_id + "' >"+ currsr.name +button_up+"</td><tr>");
	 		}
 		});
 		$.get(urlrest,function(data,status){
 			var rarrays = stringToJson(data);
			$("#RestRegions").children().remove();
 			for (var i = 0; i<rarrays.length; i++){
 				var currr = rarrays[i];
	 			$("#RestRegions").append("<tr  class= 'rest-regions-tr'><td period_id = '"+ period_id +"' id = '" + currr.id + "' >" + button_left + currr.name +"</td><tr>");
	 		}
 		});
 		
 	});
 	$(document).on("click",'#SelectedRegions tr td .search-regions-actions-delete',function(){
 		var th = $(this).parents("tr");
 		var td = $(this).parents("td");
 		var ptr_id = td.attr("id");
 		var region_id = td.attr("region_id");
 		var period_id = td.attr("period_id");
 		var name = td.text();
 		$.post("DeleteConnectionServlet",{
			 ptr_id: ptr_id
		 },function(data,status){
			 if(data=="1"){
				 alert("删除成功");
				 $("#RestRegions").append("<tr class= 'rest-regions-tr'><td  period_id = '"+ period_id +"' id = '" + region_id + "' >" +  button_left+ name +"</td><tr>");
				 th.remove();
			 }
			 else{
				 alert("删除失败")
			 }
			 
		 });
 	});
 	
 	$(document).on("click",'#SelectedRegions tr td .search-regions-actions-up',function(){
 		var th = $(this).parents("tr");
 		var td = $(this).parents("td");
 		var prev = th.prev();
 		var len = th.prevAll().length;
 		while (len!=0&&prev.attr("class")===undefined){
 			prev = prev.prev();
 			len=len-1;
 		}
 		if(prev.attr("class")!==undefined){
 		var prevtd = prev.children("td");
 		var previd = prevtd.attr("id");
 		var tdid = td.attr("id");
 		$.post("SwitchSortServlet",{
 			ptr_id_bef : previd,
 			ptr_id_aft : tdid
 		},function(data, status){
 			if(data=="success"){
 				th.insertBefore(prev);
 			}
 			else{
 				alert("error");
 			}
 		});}
 //		ptr_id_bef
 //     ptr_id_aft
 //		th.insertBefore(prev);
 //		var prevtd = prev.children("td");
 	});
 	$(document).on("click",'#RestRegions tr td a',function(){
 		var th = $(this).parents("tr");
 		var td = $(this).parents("td");
 		var region_id = td.attr("id");
 		var period_id = td.attr("period_id");
 		var name = td.text();
 		var sort = 0
 		$.post("GetMaxSortServlet",{
 			period_id : period_id 
 		},function(data, status){
 			if(data!="error"){
//	 			alert(data);
	 			sort = parseInt(data);
	 			$.post("AddConnectionServlet",{
	 				 period_id: period_id,
	 				 gener_id: "0001",
	 				 sort: sort+1,
	 				 region_id: region_id
	 			 },function(data,status){
	 				 if(data!="error"&&status=="success"){
	 					 alert("添加成功");
	 			 		$("#SelectedRegions").append("<tr class='selected-region-tr'><td period_id = '"+ period_id + "' region_id = '" + region_id + "' id = '" + data + "' >" + name + button_up+ "</td><tr>");
	 					 th.remove();
	 				 }
	 				 else{
	 					 alert("添加失败")
	 				 }
	 				 
	 			 });
 			}
 			else{
 				alert("失败，无法返回现在最大sort")
 			}
 		});
 		
 	});
	
});