/**
 * Created by XinLi on 16/6/21.
 */
function addTab(title,url){
    parent.addTab(title,url);
}
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

function newQuestion(){
    $('#add-question-dlg').dialog('open').dialog('setTitle','区域');
    $('#add-question-form').form('clear');

    url = 'AddQuestionServlet?region_id='+getQueryString("region_id");
}

function editQuestion(){
    var row = $('#question-data').datagrid('getSelected');
    if (row){
        $('#add-question-dlg').dialog('open').dialog('setTitle','修改表');
        $('#add-question-form').form('load',row);
        url = 'ChangeQuestionServlet?question_id='+row.id;
    }
}

function saveQuestion() {
    $('#add-question-form').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            if (result-="error"){
                $.messager.show({
                    title: 'Error',
                    msg: "添加错误"
                });
            } else {
                $('#add-question-dlg').dialog('close');		// close the dialog
                $('#question-data').datagrid('reload');	// reload the user data
            }
        }
    });
}
function destroyQuestion(){
    var row = $('#question-data').datagrid('getSelected');
    if (row){
        $.messager.confirm('Confirm','确认删除?',function(r){
            if (r){
                $.post('DeleteSingleQuestionServlet',{question_id:row.id},function(result){
                    if (result=="1"){
                        $('#question-data').datagrid('reload');	// reload the user data
                    } else {
                        $.messager.show({	// show error message
                            title: 'Error',
                            msg: "添加错误"
                        });
                    }
                },'json');
            }
        });
    }
}