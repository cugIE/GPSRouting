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