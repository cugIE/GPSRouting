/**
 * Created by XinLi on 16/6/21.
 */

var userTeamid = getQueryString('userTeamid');
var userName = getQueryString('userName');
//alert("用户职位id："+userTeamid+",用户姓名："+userName);

window.onload = function () { 
	$('#pick-date').datebox('setValue', formatterDate(new Date()));//设置日历框显示默认当前系统时间
	var data = $('#sheet-list').combobox('getData');
//	alert("sheetlistdata:"+data);
    if (data.length > 0) {
        $('#sheet-list').combobox('select', data[0].id);
    } 
};

formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};

function addTab(title,url){
    parent.addTab(title,url);
}
function getUrlParam(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r!=null) return unescape(r[2]); return null; //返回参数值
}

function showResults(){
    $('#result-data').datagrid('load',{
        index: "result",
        sheet_id: $('#sheet-list').combobox('getValue'),
        date: $("#pick-date").datetimebox('getValue')
    });
}

function showDetail() {
    var row = $('#result-data').datagrid('getSelected');
    addTab(row.date+'-'+row.shift+'-'+row.time, "result-data-detail.jsp?date="+row.date+"&period_id="+row.id+"&shift="+row.shift+"&time="+row.time);
}

function deleteRecord(){
	var row = $('#result-data').datagrid('getSelected');
	if(userTeamid<3||userName == row.geners){
	if (row){
        $.messager.confirm('Confirm','确认删除?',function(r){
            if (r){
                $.post('DeleteRecordServlet?index=period',{period_id:row.id,date:row.date},function(result){
                    if (result=="1"){
                        $('#result-data').datagrid('reload');	// reload the user data
                    } else {
                        $.messager.confirm({	// show error message
                            title: 'Error',
                            msg: "删除失败"
                        });
                    }
                },'json');
            }
        });
    }
	}else{
		$.messager.confirm({	// show error message
            title: 'Error',
            msg: "您不具备删除记录权限，请联系管理员或巡检员本人！"
        });
	}
	
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
};