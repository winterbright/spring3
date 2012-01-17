<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>增加用户</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
  </head>
  
  <body>
  
   <form action="<%=request.getContextPath()%>/user/update/${user.id}" method="post">
   <input type="hidden" name="id" value="${user.id}">
   	姓名：<input name="name" value="${user.name}"><br/>
   	密码：<input type="password" name="password" value="${user.password}"><br/>
   	年龄：<input name="age" value="${user.age}"><br/>
   	出生日期：
   	<input class="Wdate" name="birthday" value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>" onClick="WdatePicker()"><br/>
   	部门：
   <select name="deptId">
   		<c:forEach items="${deptList}" var="d">
   			<c:if test="${d.id == user.deptId}">
		   		<option value="${d.id}" selected="selected">${d.name}</option>
   			</c:if>
   			<c:if test="${d.id != user.deptId}">
		   		<option value="${d.id}">${d.name}</option>
   			</c:if>
	   </c:forEach>
   	</select>
	<input type="submit" value="提交">
   </form>
  </body>
</html>
