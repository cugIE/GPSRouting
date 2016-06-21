/**
 * Created by XinLi on 16/6/21.
 */

$('#sheet-list').combobox({
    onChange: function (n, o) {
        $('#period-list').combobox({
            url:'GetAllPeriodServlet?index=sheet&sheet_id='+n+'&web=1',
            valueField:'id',
            textField:'time',
            method:'get'
        });
    }
});

function getUrlParam(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r!=null) return unescape(r[2]); return null; //返回参数值
}

function showContent(){
    $('#question-data').datagrid('load',{
        index: "period",
        period_id: $('#period-list').combobox('getValue'),
        web:1
    });
}