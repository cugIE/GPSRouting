function newUser(){
	$('#dlg').dialog('open').dialog('setTitle','添加人员');
	$('#fm').form('clear');
	url = 'save_user.php';
}