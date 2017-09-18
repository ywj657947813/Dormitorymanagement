<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
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
		width:664px;
		margin-left:auto;
		margin-right:auto;
	}
	#table
	{
		width:550px;
		margin-left:auto;
		margin-right:auto;
	}
	#down
{
	width:100px;
	margin-right:0;
	margin-left:auto;
}
</style>
</head>
  
  <body>
    <div id="top">
    	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      		<ul class="nav navbar-nav">
       		 <li><b><p class="navbar-text"><font color="#FFFFFF">学生宿舍管理系统安全中心</font></p></b></li>    
       		 <li><font color="#CCCCFF"></font></li>
      		</ul> 
   			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      			<ul class="nav navbar-nav navbar-right">
      			<li><a href="selectLeave?action=teacher"><b><font color="#CCCCFF">返回首页</font></b></a></li>
        		<li><a href="safeExit"><b><font color="#CCCCFF">退出登陆</font></b></a></li>
      			</ul>
    		</div>
    	</div>
    </div>
    <br>
    <div id="top2"><img src="images/pass2.jpg" /><img src="images/next.jpg" /><img src="images/null3.jpg" /><img src="images/not.jpg" /><img src="images/null4.jpg" /></div>
    <br>
    <div id="table">
    <form name="form5" action="aoldpass?action1=teacher" method="post">
    	<table class="table table-bordered">	
    		<tr>
				<td align="center" width="200">请输入密码：</td>
				<td>
					<input type="password" name="oldpass" value="${oldpass}" required ></input>
					<font color="red">${error}</font>
				</td>
			</tr>
			<tr>
				<td align="center" width="200">请输入新密码：</td>
				<td>
					<input type="password" name="newpass" value="${newpass}" required></input>
					<input type="text" name="action" value="choice" hidden="true" />
				</td>
			</tr>
		</table>
			<div id="down">
				<input class="btn btn-primary btn-lg active" type="submit" value="下一步">
			</div>
			</form>
    </div>
 
  </body>
</html>
