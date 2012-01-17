<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
  
   <form action="<%=request.getContextPath()%>/user/add" method="post">
   	姓名：<input name="name"><br/>
   	密码：<input type="password" name="password"><br/>
   	确认密码：<input type="password" name="confirmpwd"><br/>
   	年龄：<input name="age"><br/>
   	出生日期：<input class="Wdate" name="birthday" onClick="WdatePicker()"><br/>
   	部门：<select name="deptId">
	   		<c:forEach items="${deptList}" var="d">
		   		<option value="${d.id}">${d.name}</option>
		   </c:forEach>
	   	</select>
	  <input type="submit" value="提交">
   </form>
  </body>
</html>
