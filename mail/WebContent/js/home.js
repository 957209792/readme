$(document).ready(function(){
var home = new Object();
home.load = function(){
	$.ajax({
		type : "get",
		// 发送到服务端的数据对象
		//data : $("#login_form").serialize(),
		// 返回数据类型
		//dataType : "json",
		url : "../HomeServlet",
		success : function(result) {
			//转换JSON对象
			results = JSON.parse(result);
			if (results.status == 200) {
				//window.location.asssign("login.jsp");
				window.location.href="login.jsp";
			}
		},
		error : function(error) {
			alert("找不到服务器");
		}
		
		
	});
	
	
}
$("#headerOver").bind({click:function(){
	
	home.load();
}});

	
	
})

