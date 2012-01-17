<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'welcome.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
   	欢迎： ${userSessionInfo.name}<br/>
   <hr/>
   <a href="<%=request.getContextPath()%>/user/list">用户管理</a> <br/>
   <a>部门管理</a> <br/>
   <img alt="慧星" src="<%=request.getContextPath()%>/img/hx.jpg">
  </body>
</html>
