<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生宿舍管理系统安全中心</title>
    
	<link rel="stylesheet" href="css/bootstrap.css">
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
		width:100%;
		height:50px;
		background-color: #09F;
		margin-left:auto;
		margin-right:auto;
	}
	#top2
	{
		width:952px;
		margin-left:auto;
		margin-right:auto;
	}
	</style>
  </head>
  
  <body>
    <div id="top">
    	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      		<ul class="nav navbar-nav">
       		 <li><b><p class="navbar-text"><font color="#FFFFFF">学生宿舍管理系统安全中心</font></p></b></li>    
       		 <li><font color="#CCCCFF"></font></li>
       		 <li><b><p class="navbar-text"><font color="#FFFFFF">欢迎您，${currentUser.name}同学！</font></p></b></li> 
      		</ul> 
   			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      			<ul class="nav navbar-nav navbar-right">
      			<li><a href="page.jsp"><b><font color="#CCCCFF">返回首页</font></b></a></li>
        		<li><a href="safeExit"><b><font color="#CCCCFF">退出登陆</font></b></a></li>
      			</ul>
    		</div>
    	</div>
    </div>
    <br>
    <div id="top2"><img src="images/pass2.jpg" /><img src="images/ok.jpg" /><img src="images/pass3.jpg" /><img src="images/ok.jpg" /><img src="images/pass4.jpg" /></div>
    <div align="center"><h2>修改成功！</h2></div>
  </body>
</html>
