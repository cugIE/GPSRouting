    function addTab(title,url){
        parent.addTab(title,url);
    }
    function newSheet(){
        $('#add-sheet-dlg').dialog('open').dialog('setTitle','新表');
        $('#add-sheet-form').form('clear');
        url = 'AddSheetServlet';
    }