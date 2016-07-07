/*$(document).ready(function() { */
	/*var sheet_id = $('#sheet-list').combobox('getValue');*/
	
	function showResults(){
	
		var sheet_id = $('#sheet-list').combobox('getValue');
    //页面加载完初始化日历 
    $('#calendar').fullCalendar({ 
        //设置选项和回调 
    	 header: {
             left: 'prev,next today',
             center: 'title',
             right: 'month,agendaWeek,agendaDay'
         },
        
         timeFormat: 'H:mm',
         axisFormat: 'H:mm',
         events: {
        	 url:'MonthDataServlet?sheet_id='+sheet_id,
        	 type:'POST',
        	 error: function() {  
                 alert('there was an error while fetching events!'+title);  
             },  
            /* color:'yellow',// 背景色  
             textColor:'black'// 文字颜色		 
*/         }
        /* events: [{
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
         }]*/
    });
	}
    function getUrlParam(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r!=null) return unescape(r[2]); return null; //返回参数值
    }
/*}); */