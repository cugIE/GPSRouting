var branch_id = getQueryString('branch_id');



window.onload = function(){
	$('#fault-data').datagrid({url:'ManageFaultmsgServlet?action=list3&userBranchid='+branch_id });
	
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