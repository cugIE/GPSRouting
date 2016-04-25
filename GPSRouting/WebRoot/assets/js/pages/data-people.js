/**
 * 人员管理主界面对应脚本
 */
$(document).ready(function(){
	$(".people-delete").on("click",function(){
		var peopleid = $(this).attr("value");
		if(confirm("确定要删除编号为"+peopleid+"的人员信息?")){
		$.post("ManagePeopleServlet?action=delete3",
		  {
		    id:peopleid
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
	
});
