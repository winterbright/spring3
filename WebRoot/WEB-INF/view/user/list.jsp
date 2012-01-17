<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <a href="<%=request.getContextPath()%>/user/add">增加</a>
  <br/>${message }
  <hr/>
  <table>
   <c:forEach items="${userList}" var="u">
   	<tr>
   		<td>${u.name}</td>
   		<td>${u.age}</td> 
   		<td><fmt:formatDate value="${u.birthday}" pattern="yyyy-MM-dd"/></td>
   		<td>
	   		<c:forEach items="${deptList}" var="d">
	   			<c:if test="${d.id == u.deptId}">
			   		${d.name}
	   			</c:if>
		   </c:forEach>
	   	</td>
	   	<td>
	   	<a href="<%=request.getContextPath()%>/user/update/${u.id}">更新</a>
	   	<a href="<%=request.getContextPath()%>/user/delete/${u.id}">删除</a>
	   	</td>
   	</tr>
   </c:forEach>
   </table>
  </body>
</html>
