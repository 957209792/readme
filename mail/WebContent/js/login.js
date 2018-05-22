/**
 * 登录页业务脚本
 */
/*
 * $(function() { // 绑定登录按钮事件 $("#login_button").bind({click:function(){
 * $("#login_form").submit(); }});
 *  // 绑定记住密码按钮事件 $("#remember-me").bind({click:function(){
 * $("#remember").val(this.checked); }}); })
 */
//读取以下内容
$(document).ready(function() {

	// var register=new Object();
	$("[type='submit']").bind({
		click : function() {
			// serialize():将表单内容序列化为字符串
			$.ajax({
				type : "post",
				// 发送到服务端的数据对象
				data : $("#login_form").serialize(),
				//设置返回数据类型，把返回的String类型转换成JSON对象
				dataType : "json",
				url : "../loginServlet",
				// 获取到目标地址返回过来的json传入result
				success : function(result) {
					/**/
					if (result.message =="200") {
						window.location.assign("home.jsp");
						
					}else{
						alert("账号密码错误");
					}
				},
				error : function(error) {
					alert("找不到服务器");
				}
			});
		}
	});

	// 绑定记住密码按钮事件
	$("#remember-me").bind({
		click : function() {
			$("#remember").val(this.checked);
		}
	});
	
	

});
/*
 * var register = new Object(); register.paths = { user_url:"../LoginServlet", };
 * 
 * login.submit = function() {
 *  }
 * 
 * $("#login_submit").bing({click:function(){
 * 
 * }});
 * 
 * });
 */