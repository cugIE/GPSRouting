/**
 * Created by XinLi on 16/6/21.
 */


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
    addTab(row.date+'-'+row.shift+'-'+row.time, "result-data-detail.jsp?date="+row.date+"&period_id="+row.id);
}