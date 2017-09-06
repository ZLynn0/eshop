window.onload = function(){
		document.querySelector('#btn1').onclick=function(){
			//1、创建xmlhttprequest对象;
			var xmlhttp = new XMLHttpRequest();
			//2、创建与与服务器连接
			xmlhttp.open("POST","email.do",true);
			xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			xmlhttp.send("email=" + "admin" +"&a="+'中华人民共和国');
			//3、接收来自服务器信息;
			xmlhttp.onreadystatechange = function(){
				 if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
						document.querySelector('div').innerHTML=xmlhttp.responseText;
				 }
			}
		}
		var path="${pageContext.request.contextPath}";
		document.querySelector('#btn2').onclick=function(){
			//1、创建xmlhttprequest对象;
			var xmlhttp = new XMLHttpRequest();
			//2、创建与与服务器连接
			var url ="email.do?email="+"admin";
			xmlhttp.open("GET",url,true);
			//xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			//xmlhttp.send("email=" + "admin");
			//3、接收来自服务器信息;
			xmlhttp.onreadystatechange = function(){
				 if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
						document.querySelector('div').innerHTML=xmlhttp.responseText;
						console.log(path);
				 }
			}
			xmlhttp.send(null);
		}
		document.querySelector('#s1').oninput=function(){
			//1、创建xmlhttprequest对象;
			var xmlhttp = new XMLHttpRequest();
			//2、创建与与服务器连接
			xmlhttp.open("POST","search.do",true);
			xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			xmlhttp.send(null);
			xmlhttp.onreadystatechange = function(){
				 if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
						//var list =eval("("+ xmlhttp.responseText+")");
					    var list = JSON.parse(xmlhttp.responseText);
						var ds = document.querySelector('#ds');
						var options="";
						for(var i=0;i<list.length;i++){
							options+="<option value='"+ list[i] +"'/>"
						}
						ds.innerHTML=options;
				 }
			}
		}
	}