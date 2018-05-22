$(document).ready(function(){
	
	var register = new Object();
	register.paths = {
			user_url:"../userServlet",
			validate_code_url:"../validateCodeServlet"
			
	};
	
	register.loadValidateCode = function() {
		// 页面加载获取验证码
	    $.ajax({
	        type: "get",
	        url: register.paths.validate_code_url,
	        success: function(result){
	            var jsonResult = JSON.parse(result); 
	        	if(jsonResult.status == 200){
	        		var data = jsonResult.data;
	        		$("#codeImg")[0].src= data;
	             }
	         },
	         error: function(error) {
				// 加载验证码异常处理
			}
	     });
	}
	
	register.submit = function(formData) {
		// 页面加载获取验证码
	    $.ajax({
	        type: "post",
	        data: formData,
	        url: register.paths.user_url,
	        success: function(result){
	            var jsonResult = JSON.parse(result); 
	        	if(jsonResult.status == 200){
	        		alert(jsonResult.message);
	             }
	         },
	         error: function(error) {
				// 加载验证码异常处理
			}
	     });
	}
	
	// 页面加载获取验证码
	register.loadValidateCode();
	
	$("[type='submit']").bind({click:function(){
		if(this.id='0'){// 邮箱注册
			//获取表单序列号
			var form = $(document.forms[0]).serialize();
			register.submit(form);
		}else{// 手机注册,serialize():将表单内容序列化为字符串
			var form = $(document.forms[1]).serialize();
			register.submit(form);
		}
	}});
	
	
	// 绑定实时验证事件(邮箱，手机,验证码)
	$("#email").bind({blur:function(){
		// 页面加载获取验证码
	    $.ajax({
	        type: "get",
	        url: register.paths.user_url+"?email="+this.value,
	        success: function(result){
	        	//把字符串转换为对象
	            var jsonResult = JSON.parse(result); 
	        	if(jsonResult.status != 200){
	        		alert(jsonResult.message);
	             }
	         },
	         error: function(error) {
				// 加载验证码异常处理
			}
	     });
	}});
	
	$("#phone").bind({blur:function(){
		// 页面加载获取验证码
	    $.ajax({
	        type: "get",
	        url: register.paths.user_url+"?phone="+this.value,
	        success: function(result){
	            var jsonResult = JSON.parse(result); 
	        	if(jsonResult.status != 200){
	        		alert(jsonResult.message);
	             }
	         },
	         error: function(error) {
				// 加载验证码异常处理
			}
	     });
	}});
	
	$("#validateCode").bind({blur:function(){
		// 页面加载获取验证码
	    $.ajax({
	        type: "post",
	        url: register.paths.validate_code_url +"?validateCode="+this.value,
	        success: function(result){
	            var jsonResult = JSON.parse(result); 
	        	if(jsonResult.status != 200){
	        		alert(jsonResult.message);
	             }
	         },
	         error: function(error) {
				// 加载验证码异常处理
			}
	     });
	}});
	
	// 点击验证码图片自动刷新验证码
	$("#codeImg").bind({click:function(){
		register.loadValidateCode();
	}});
	
	
})