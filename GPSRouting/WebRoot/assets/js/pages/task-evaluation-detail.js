/**
 * 
 */

$(document).ready(function(){
	if($("#record-checker").text()==""){
		
	}
	else{
		$("#check-button").hide();
	}
$("#check-button").on("click", function(){
	var record_id = $("#eva-record-id").text();
	var comment =$("#record-comment").val();
	if(confirm("确定审核完毕？")){
		$.post("CheckRecordServlet",{
			record_id : record_id,
			comment : comment
		},function(data, status){
			if(data=="1"){
				alert("成功");
				window.location.reload();
			}
			else{
				alert("失败")
			}
		});
	}
});
})