<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎使用学生宿舍管理系统！请登录！</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
#top{
	width:60%;
	height:auto;
	margin-left:auto;
	margin-right:auto;
}
#lay1{
	width:55%;
	height:300px;
	background-color: #6C60CD;
	background-image:url(images/1.jpg);
	margin-left:auto;
	margin-right:auto;
}
#lay2{
	width:50%;
	height:300px;
	background-color:#6C60CD;
	float:right;
	border-width:6px;
	font-size:15pt;
	font-weight:bolder;
	color:#DDFEFF;
	font-family:黑体;
}
</style>
  
 
  </head>
  
  <body>
 <div id="top"><img src="images/3.jpg" /></div>
<div id="lay1" >
<div id="lay2">
  <form name="form1" action="login" method="post">
  <p align="center"><br>

学&nbsp;&nbsp;&nbsp;号:<input type="text" name="username" value="${username}"><br><br>
<font color="red">${error}</font><br>
密&nbsp;&nbsp;&nbsp;码:<input type="password" name="password" value="${password}"><br><br>

<input type="radio" name="Identity" value="student" checked/>学生
<input type="radio" name="Identity" value="administrator">管理员
<input type="radio" name="Identity" value="teacher">教导员
<input type="radio" name="Identity" value="service">维修人员<br>

<input type="submit" value="登陆">&nbsp;&nbsp;
<input type="reset" value="重置"><br>

</p>
</form>
</div>
</div>
 </body>
</html>
