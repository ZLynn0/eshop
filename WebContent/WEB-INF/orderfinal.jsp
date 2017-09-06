<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta content="all" name="robots"/>
<meta name="author" content="Fisher" />
<meta name="Copyright" content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
<meta name="description" content="reefdesign" />
<meta name="keywords" content="reefdesign" />
<title>电子书城</title>
<link rel="shortcut icon" href="favicon.ico" >
<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
</head>

<body class="main">

<!-- 导入头文件 -->
<%@include file="header.jsp" %>

<!-- 正文   -->
<div id="divpagecontent">
  <table width="100%" border="0" cellspacing="0">
    <tr>
      <td width="25%"><table width="100%" border="0" cellspacing="0" style="margin-top:30px">
        <tr>
          <td class="listtitle">配送方式、时间及费用</td>
        </tr>
        <tr>
          <td class="listtd"><img src="${pageContext.request.contextPath }/images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="news.html">普通快递送货上门</a></td>
        </tr>
		
<tr>
          <td class="listtd"><img src="${pageContext.request.contextPath }/images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">特快专递送货上门(EMS)</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="${pageContext.request.contextPath }/images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">申通快递送货上门</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="${pageContext.request.contextPath }/images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">普通邮寄</a></td>
        </tr>	
      </table></td>
      <td><div style="text-align:right; margin:5px 10px 5px 0px"><a href="${pageContext.request.contextPath }/index.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/dispacher.do?type=cart">&nbsp;购物车</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;订单</div>
   
        <table cellspacing="0" class="infocontent">
        <tr>
          <td><table width="100%" border="0" cellspacing="0">
              <tr>
                <td><img src="${pageContext.request.contextPath }/images/buy3.gif" width="635" height="38" />
                  <p>尊敬的${user.uloginid }<c:if test="${user.usex=='男 ' }">先生</c:if><c:if test="${user.usex=='女 ' }">女士</c:if>！您的购买流程还有最后一步啦</p></td>
              </tr>
              <tr>
                <td><table cellspacing="1" class="carttable">
                  <tr>
                    <td width="10%">序号</td>
                    <td width="40%">商品名称</td>
                    <td width="10%">市场价</td>
                    <td width="10%">优惠价</td>
                    <td width="10%">数量</td>
                    <td width="10%">小计</td>
                  </tr>
                </table>
     		    <c:if test="${cart==null }">
							   <table width="100%" border="0" cellspacing="0">
			                    <tr>
			                      <td style="text-align:center"><font color='red'>购物车里空空的呀~~</font></td>		                   
			                    </tr>
			                  </table>
							   
				   </c:if>
				   <c:set var="sum" value="0"></c:set>
				   <c:if test="${cart!=null }">
				   <c:forEach items="${cart }"  var="item">
				   <table width="100%" border="0" cellspacing="0">
                    <tr>
                      <td width="10%">${item.gid }</td>
                      <td width="40%">${item.gtitle }</td>
                      <td width="10%">${item.ginprice }</td>
                      <td width="10%">${item.gsaleprice }</td>
                      <td width="10%"><input name="text" type="text" value="${item.amount }" style="width:20px"/></td>
                      <td width="10%">${item.gsaleprice*item.amount }</td>
                     <c:set var="sum" value="${item.gsaleprice*item.amount+sum }"></c:set>
                    </tr>
                  </table>
                  </c:forEach></c:if>
				  
				  
				   <table cellspacing="1" class="carttable">
                     <tr>
                       <td style="text-align:right; padding-right:40px;"><font style="color:#FF6600; font-weight:bold">合计：&nbsp;&nbsp;<c:if test="${cart==null }">0</c:if><c:if test="${cart!=null }">${sum }</c:if>元</font></td>
                      </tr>
                   </table>  	   
				   <p>收货地址：${user.uaddress}<br/>
				   收货人：${user.uloginid }<br/>
				   联系方式：${user.utel }</p>
				   
				   
				   
				   <hr/> 
				   <p style="text-align:center"><a href="${pageContext.request.contextPath }/dispacher.do?type=ordersuccess"><img src="images/finalbutton.gif" width="204" height="51" border="0" /></a></p></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
    </tr>
  </table>
</div>



<!-- 导入尾部 -->
<%@include file="footer.jsp" %>


</body>
</html>
