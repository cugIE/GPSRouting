/**
 * Created by XinLi on 16/6/23.
 */
function newCheck() {
    $('#add-check-dlg').dialog('open').dialog('setTitle','审核');
}

function checkAllRecord(record_id){
    var comment =$("#record-comment").val();
    if(confirm("确定审核完毕？")){
        $.post("CheckRecordServlet?index=all",{
            record_id : record_id,
            comment : comment
        },function(data, status){
            if(data!="error"){
                alert("成功");
                window.location.reload();
            }
            else{
                alert("失败")
            }
        });
    }
}
