window.onload=function(){
	//生成验证码；
	document.querySelector('#imgs').onclick=function(){
		this.src='codeServlet.do?id='+Math.random();
	}
	//检查邮箱（POST）
	document.querySelector('#check_email').onclick = function(){
		var email=document.querySelector('#uemail').value;
		console.log(email)
		//1、创建一个XMLHttpRequest对象；
		xmlhttp=new XMLHttpRequest();
		//2、创建一个http请求；
		xmlhttp.open("POST","checkemail.do",true);
		//发送请求；
		var data="email="+email;
		//设置http请求头；
		xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		xmlhttp.send(data);
		//4、接收服务器数据；
		xmlhttp.onreadystatechange=emailcallback;
	}
	function emailcallback(){
		if(xmlhttp.readyState==4){ //数据传输完成;
			if(xmlhttp.status==200){ //成功在客户端接收到数据;
				var data = xmlhttp.responseText;//接收服务器返回的字符串；1,0
				var sp = document.querySelector('#spemail');
				if(data=="1"){ //1:表示存在;
					sp.innerHTML='邮箱已被注册';
					sp.style.color='red';
				}else{
					sp.innerHTML='该邮箱可以使用';
					sp.style.color='green';
				}
			}
		}
	}
	//验证用户名(get请求）；
	document.querySelector('#check_user').onclick=function(){
		//将账号拿到，再传到服务器去验证，验证后拿到服务器返回的验证结果；
		var uname=document.querySelector('#uname').value;
		//AJAX与服务器通信是通过XMLRequest实现的；
		//1、得到一个异步通信的组件对象；
		var xmlhttp=new XMLHttpRequest();//省去针对IE浏览器(<7.0)创建此对象的兼容判断
		//2、使用此对象与服务器通信；
		//A、创建一个http请求（建立与服务器某一资源的通信）；
		xmlhttp.open("GET","checkname.do?uname="+uname,true);
		//B、发送请求；
		xmlhttp.send(null);
		//C、接收服务器返回的数据；
		xmlhttp.onreadystatechange= function(){//处理状态改变事件
			if(xmlhttp.readyState==4){//数据传输完成；
				if(xmlhttp.status==200){//成功在客户端接收到数据
					//可以取服务器返回的数据；
					var data=xmlhttp.responseText;//接收服务器返回的字符串；1,0
					var sp=document.querySelector('#sp');
					if(data=="1"){//1:表示存在
						sp.innerHTML='账号已被注册';
						sp.style.color='red';
					}else{
						sp.innerHTML="该账号可以使用";
						sp.style.color='green';
					}
				}
			}
			
		}
	}

}


