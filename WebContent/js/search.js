var xmlhttp=null;
window.onload=function(){
	document.querySelector('#searchtext').oninput=function(){
		//1、创建XMLHttpequest;
		xmlhttp=new XMLHttpRequest();		
		//2、创建一个http请求；
		var val=document.querySelector('#searchtext').value;
		var url="search.do";
		xmlhttp.open("POST",url,true);
		//3、发送请求；
		var data="keyword="+val;
		//设置http请求头；
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		xmlhttp.send(data);
		//4、取数据
		xmlhttp.onreadystatechange=callback;
	}
}
function callback(){
	if(xmlhttp.readyState==4&&xmlhttp.status==200){
		var data=xmlhttp.responseText;//拿到的是string类型数据；
		//console.log(data);
		//将string转成json对象；
		//var json=eval("("+data+")");
		var json=JSON.parse(data);//拿到的是数组；【】
		var options="";
		for(var i=0;i<json.length;i++){
			options+="<option value='"+json[i].keyword+"'/>";
			
		}
		var ds=document.querySelector('#ds');
		ds.innerHTML=options;
	}
}