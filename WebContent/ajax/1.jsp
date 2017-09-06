<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js">		</script>
		<script type="text/javascript">
			$(function(){
				$('#uname').on('input',function(){
					$.ajax({
						data:{'uname':$(this).val()},
						dataType:'text',
						type:'post',
						url:'/web_project/checkname.do',
						async:true,
						success:function(data,b,c){		//第一个参数：服务器返回的数据；第二个参数：jquery ajax状态值；第三个参数：返回XMLHttpRequest对象；				
						if(data=="1"){
								$('span:first').html('alreadey register..');
							}else if(data=="0"){
								$('span:first').html('ok..');
							} 
						}
					});
				})
			})
		</script>
<title>Insert title here</title>
</head>
<body>
	账号：<input type="text"  id="uname"/><span></span><br/>
	密码：<input type="text"  id="upwd"/><span></span><br/>
</body>
</html>