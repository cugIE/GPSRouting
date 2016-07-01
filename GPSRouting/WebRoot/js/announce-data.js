var branch_id = getQueryString('branch_id');
/*alert("部门id："+branch_id);*/
window.onload = function(){
	$('#announce-data').datagrid({url:'ManageAnnounceServlet?action=announceList&userBranchid='+branch_id });
};

    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    };
    function pushMsg(){
    	var row = $('#announce-data').datagrid('getSelected');
    	if (row){
            $.messager.confirm('Confirm','确认发送?',function(r){
                if (r){
                    $.post('SendMsgServlet',{id:row.id},function(result){
                        if (result=="1"){
                        	$.messager.show({	// show error message
                                title: 'message',
                                msg: "发送成功！"
                            });
                            /*$('#region-data').datagrid('reload');*/	// reload the user data
                        } else {
                            $.messager.show({	// show error message
                                title: 'Error',
                                msg: "发送失败！"
                            });
                        }
                    },'json');
                }
            });
        }
    }
    function checkFault(){
    	 var row = $('#fault-data').datagrid('getSelected');
    	/* alert("故障id:"+row.id);*/
    	 url:'ManageFaultmsgServlet?action=list2&id='+row.id;
    	 if(row){
    		 parent.addTab("故障信息审核",'ManageFaultmsgServlet?action=list2&id='+row.id);
    	 }
    	 
    }
    
    function alarmPOI(){
    	var row = $('#alarm-data').datagrid('getSelected');
    	if(row){
    		parent.addTab("警报位置",'FindAlarmpoiServlet?id='+row.id);
    	}
    }