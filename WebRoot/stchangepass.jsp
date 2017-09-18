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
    <div id="top2"><img src="images/pass2.jpg" /><img src="images/ok.jpg" /><img src="images/pass3.jpg" /><img src="images/next.jpg" /><img src="images/null4.jpg" /></div>
  	<div id="table">
  		<form name="form5" action="oldpass" method="post">
    	<table class="table table-bordered">
    			<tr>
				<td align="center" width="200">确认您输入的新密码：</td>
				<td align="center">
					<input type="text" required style="resize:none;" name="newpass" readonly value="${password}"></input></td>
				    <input type="text" name="action" value="change" hidden="true"/>	
			    </tr>
		
    	</table>
    	<div id="down">
				<input class="btn btn-primary btn-lg active" type="submit" value="下一页">
		</div>
    	</form>
  	</div>
  	
  </body>
</html>