window.onload = function () { 
	var data = $('#sheet-list').combobox('getData');
    if (data.length > 0) {
        $('#sheet-list').combobox('select', data[0].id);
    } 
};
	
/*$('#sheet-list').combobox({
	onSelect: function(record){
		var sheet_id = record.id;
		 $('#calendar').fullCalendar({ 
		        //设置选项和回调 
		    	 header: {
		             left: 'prev,next today',
		             center: 'title',
		             right: 'month,agendaWeek,agendaDay'
		         },
		        
		         timeFormat: 'H:mm',
		         axisFormat: 'H:mm',
		         height:'470px',
		         weekMode:'variable',
		         events: {
		        	 url:'MonthDataServlet?sheet_id='+sheet_id,
		        	 type:'POST',
		        	 error: function() {  
		                 alert('there was an error while fetching events!'+title);  
		             },  
		             color:'white',// 背景色  
		             textColor:'black'// 文字颜色
		            
		         }
		         
		         events: [{
		        	 id : 1,
		             title: '生日聚会',
		             start: '2016-07-05 1:00',
		             end: '2016-07-05 8:00',
		             allDay: false
		         },
		         {
		        	 id:2,
		        	 title :'全天编程',
		        	 start:'2016-07-05 2:00',
		        	 allDay:false    	 
		         }]
		    });
	}
});*/
function showResults(){
	$('#calendar').fullCalendar('destroy');
	var sheet_id = $('#sheet-list').combobox('getValue');
    //页面加载完初始化日历 
    $('#calendar').fullCalendar({ 
        //设置选项和回调 
    	 header: {
             left: 'prev,next today',
             center: 'title',
             right: 'month,agendaWeek,agendaDay'
         },
         prev:function(){
        	 alert("上一月！");
         },
        
         timeFormat: 'H:mm',
         axisFormat: 'H:mm',
         height:'470px',
         weekMode:'variable',

        /* events: {
        	 url:'MonthDataServlet',
        	 type:'POST',
        	 data:{
        		 	startDate:$('#calendar').fullCalendar('getView').start,
        		 	sheet_id:sheet_id
        	 },
        	 error: function() {  
                 alert('there was an error while fetching events!');  
             },               
             color:'white',// 背景色  
             textColor:'black'// 文字颜色
         }*/
         
         events: function(start,end,callback) {

        	 $.ajax({
        		 url:'MonthDataServlet',
//        		 type:'POST',
        		 dataType: 'json',
                 data: {
                     // ourhypothetical feed requires UNIX timestamps
                	 sheet_id:sheet_id,
//                     start:Math.round(start.getTime() / 1000),
                	 start:start.getTime(),
                     end: end.getTime()
                 },
                                              
                 success:function(doc) {
                    var events =[];
                    $(doc).each(function() {
                        events.push({
                             title:$(this).attr('title'),
                             start:$(this).attr('start'), // will be parsed
                             color:'white',
                             textColor:'black'
                         });
                     });
                    callback(events);
                 }
             });
        	 
         }
         
        
    });
}

function getUrlParam(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r!=null) return unescape(r[2]); return null; //返回参数值
}
