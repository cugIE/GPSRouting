/*var userTeamid = getQueryString('userTeamid');


window.onload=function()
{
	if(userTeamid>2){
		$(".datagrid-toolbar").hide();
	}	
};*/




function sendFault() {
	 $.messager.confirm('Confirm','确认发送故障信息至故障处理人员?',function(r){
		 if(r){
			 $('#doFault').form('submit',{
			        url: 'ManageFaultmsgServlet?action=updatestatus',
			        onSubmit: function(){
			            return $(this).form('validate');
			        },
			        success: function(result){
			            if (result=="error"){
			                $.messager.show({
			                    title: 'Error',
			                    msg: "发送失败！"
			                });
			            } else {
			                /*$('#dlg-people').dialog('close');		// close the dialog
			                $('#people-data').datagrid('reload');	// reload the user data*/
			                $.messager.show({
			                    title: 'success',
			                    msg: "发送成功！"
			                });
			            }
			        }
			    });
		 }
	 });
    
}

    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }