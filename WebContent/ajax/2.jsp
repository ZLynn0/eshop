<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js">		</script>

		<script type="text/javascript">
			/*$('#txtSearch').on('input',function(){
			$.ajax({
				url:'/web_project/search.do',
				type:'post',
				dataType:'json',
				data:{key:$(this).val()},
				success:function(data){
					$('#ds').empty();
					$(data).each(function(index,keyword){
						$('#ds').append("<option value='"+keyword.keyword+"'/>");
					})
				}
			})	*/
			$.getJSON('/web_project/search.do',{key:$(this).val()},function(data){
				$('#ds').empty();
				$(data).each(function(index,keyword){
					$('#ds').append("<option value='"+keyword.keyword+"'/>");
				})
			})
			})
	})
	
</script>

</head>
<body>
		<input type="text" id="txtSearch" list="ds" /><br />
		<datalist id="ds"></datalist>

</body>
</html>