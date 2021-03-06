<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <meta content="all" name="robots" />
    <meta name="author" content="Fisher" />
    <meta name="Copyright" content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
    <meta name="description" content="reefdesign" />
    <meta name="keywords" content="reefdesign" />
    <title>电子书城</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css" media="all" />
</head>
<body class="main">
<%@include file="header.jsp" %>

    <div id="divpagecontent">
        <table width="100%" border="0" cellspacing="0">
            <tr>
                <td>
                    <div style="text-align: right; margin: 5px 10px 5px 0px">
                        <a href="${pageContext.request.contextPath }/index.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;${cate.cname }</div>
                    <table cellspacing="0" class="infocontent">
                        <tr>
                            <td>
                                <table width="100%" border="0" cellspacing="0">
                                    <tr>
                                        <td style="padding: 10px">
                                        <c:set var="cid" value="1"></c:set>
                                            以下 <strong>${pageinfo.totalNumber }</strong> 条结果 每页显示<strong>${fn:length(pageinfo.datas) }</strong>条<hr />
										
									<c:forEach items="${ pageinfo.datas}" var="goods">
										<c:set var="cid" value="${goods.cid }"></c:set>
                                            <table border="0" cellspacing="0" class="searchtable">
                                                <tr>
                                                    <td width="20%" rowspan="2">
                                                        <div class="divbookpic">
                                                            <p>
                                                                <a href="${pageContext.request.contextPath }/dispacher.do?type=goods&id=${goods.gid}">
                                                                    <img src="${pageContext.request.contextPath }/images/bookcover/dayongxiaohua.jpg" width="115" height="129" border="0" /></a></p>
                                                        </div>
                                                    </td>
                                                    <td colspan="2">
                                                        <font class="bookname">${goods.gtitle }</font><br />
                                                        作者：${goods.gauthor } 著<br />
                                                        ${goods.gdesc }
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        售价：<font color="#FF0000">￥${goods.gsaleprice }</font>&nbsp;&nbsp;&nbsp;&nbsp;原价：<s>￥${goods.ginprice }</s>
                                                    </td>
                                                    <td style="text-align: right">
                                                        <a href="${pageContext.request.contextPath }/cart.do?gid=${goods.gid}&type=buy">
                                                            <img src="${pageContext.request.contextPath }/images/buy.gif" width="91" height="27" border="0" style="margin-bottom: -8px" /></a>
                                                    </td>
                                                </tr>
                                            </table>
                                        </c:forEach>
                                           
                                            <div class="pagination">
                                                <ul>
                                                <c:if test="${pageinfo.isFirstPage }">
                                                	<li class="disablepage"><< 上一页 </li>
                                                </c:if>
                                                 <c:if test="${!pageinfo.isFirstPage }">
                                                	<li><a href="${pageContext.request.contextPath }/list.do?cid=${cid }&pageindex=${pageinfo.pageIndex-1 }"><< 上一页</a> </li>
                                                </c:if>
                                                    
                                                   <!--  <li class="currentpage">1</li> -->
                <!-- 显示数字页码 --> 
				<c:if test="${ pageinfo.totalPages>=10}">
				<c:set var="currentPage" value="${pageinfo.pageIndex }"></c:set>
				<c:set var="beginIndex" value="${currentPage-5 }"></c:set>
				
				<c:if test="${beginIndex<=0 }">
						<c:set var="beginIndex" value="1"></c:set>
						<c:set var="endIndex" value="${beginIndex+9}"></c:set>
				</c:if>
				
				<c:set var="endIndex" value="${beginIndex+9 }" /> 
				<c:if test="${endIndex>=pageinfo.totalPages }">
						<c:set var="beginIndex" value="${pageinfo.totalPages-9 }"></c:set>
						<c:set var="endIndex" value="${pageinfo.totalPages}"></c:set>
				</c:if>
				
				</c:if>
				<c:if test="${ pageinfo.totalPages<10}">
					<c:set var="beginIndex" value="1"></c:set>
					<c:set var="endIndex" value="${pageinfo.totalPages}"></c:set>
				</c:if>
				
				<c:forEach var="pId" begin="${beginIndex }" end="${endIndex }">
						<c:if test="${pId==pageinfo.pageIndex }"><li><a style="text-decoration:underline;color:orange"  href="${pageContext.request.contextPath }/list.do?cid=${cid }&pageindex=${pId}">${pId }</a></li></c:if>
						<c:if test="${pId!=pageinfo.pageIndex }">
							<li><a href="${pageContext.request.contextPath }/list.do?cid=${cid }&pageindex=${pId}">${pId }</a></li>
						</c:if>
				</c:forEach>
                                                   
                                                 <c:if test="${pageinfo.isLastPage }">
                                                	<li class="disablepage">下一页 >></li>
                                                </c:if>
                                                 <c:if test="${!pageinfo.isLastPage }">
                                                	<li class="nextpage"><a href="${pageContext.request.contextPath }/list.do?cid=${cid }&pageindex=${pageinfo.pageIndex+1}">下一页 >></a> </li>
                                                </c:if>

                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
<!-- 导入尾部 -->
<%@include file="footer.jsp" %>
</body>
</html>
