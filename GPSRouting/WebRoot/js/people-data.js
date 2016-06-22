function newUser(){
	$('#dlg').dialog('open').dialog('setTitle','添加人员');
	$('#fm').form('clear');
	url = 'save_user.php';
};

	function destroyUser(){
		var peopleid = $('#destroyPeo').attr("value");
		if(confirm("确定要删除编号为"+peopleid+"的人员信息?")){
		$.post("ManagePeopleServlet?action=delete3",
		  {
		    id:peopleid
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
	};
	
	function addTab(title,url){
        if ($('#tbs').tabs('exists', title)){
            $('#tbs').tabs('select', title);
        } else {
            var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="margin-left: 10%;width:80%;height:100%;"></iframe>';
            $('#tbs').tabs('add',{
                title:title,
                content:content,
                closable:true
            });
        }
    };
	