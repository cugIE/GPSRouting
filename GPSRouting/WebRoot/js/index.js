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
    }
    
 