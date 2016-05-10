/**
 *  部门管理主界面对应脚本
 */
$(document).ready(function(){
	$(".branch-delete").on("click",function(){
		var branchid = $(this).attr("value");
		if(confirm("确定要删除编号为"+branchid+"的部门信息?")){
		$.post("ManageBranchServlet?action=delete3",
		  {
		    id:branchid
		  },
		  function(data,status){
			  if(status == "success"){
				  alert("删除成功");
				  window.location.reload();
				  /*if (data=="deletesuccess"){
					  alert("删除成功");
					  window.location.reload();
				  }
				  else{
					  alert("删除失败");
				  }*/
			  }
			  else{
				  alert("链接超时");
			  }
			  
		  });
		}else{
			
		}
	});
	
});
