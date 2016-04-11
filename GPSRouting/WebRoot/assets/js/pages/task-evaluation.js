/**
 * 
 */
$(document).ready(function(){
	$(".record-table tbody tr").on("click", function(){
		var record_id = $(this).children().eq(0).text();
		window.location.href="task-evaluation-detail.jsp?record_id="+record_id;
	});
});