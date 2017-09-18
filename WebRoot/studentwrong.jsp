
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生宿舍管理系统</title>
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
	width:70%;
	height:auto;
	margin-left:auto;
	margin-right:auto;
}
#lay{
	width:100%;
	height:50px;
	background-color: #09F;
	margin-left:auto;
	margin-right:auto;
}
#table{
	width:60%;
	margin-left:auto;
	margin-right:auto;
}
#text{
	resize: none;
	margin-left:auto;
	margin-right:auto;
}
#down
{
	width:120px;
	margin-right:0;
	margin-left:auto;
}

	</style>
  </head>
  
  <body>
  	<div id="top"><img src="images/2.jpg" /></div>
    	<br>
    <div id="lay">
    	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      		<ul class="nav navbar-nav">
      		<li><b><p class="navbar-text"><font color="#FFFFFF">欢迎您， ${currentUser.name}同学！</font></p></b></li>
       		 <li><a href="page.jsp"><b><font color="#CCCCFF">个人信息</font></b></a></li>
      		 <li><a href="service.jsp"><b><font color="#CCCCFF">宿舍维修</font></b></a></li>
        	 <li><a href="leave.jsp"><b><font color="#CCCCFF">请假申请</font></b></a></li>
      		</ul> 
   			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      			<ul class="nav navbar-nav navbar-right">
        		<li><a href="studentpass.jsp"><b><font color="#CCCCFF">修改密码</font></b></a></li>
        		<li><a href="login.jsp"><b><font color="#CCCCFF">退出登陆</font></b></a></li>
      			</ul>
    		</div>
    	</div>
    </div>
    
    <br>
    <div id="table">
    
    	<table class="table table-bordered">
	    	<c:choose> 
		    <c:when test="${wrong!=null}" >
				 <tr>
					<td align="center"><h4>违规日期</h4></td>
					<td align="center"><h4>违规理由</h4></td>
					<td align="center"><h4>处理方式</h4></td>
				</tr>
		    </c:when>
				    <c:otherwise>
				          <h3 align="center">不存在违规信息！</h3> 
				    </c:otherwise>
	        </c:choose>
			
		   <c:forEach var="wrong" items="${wrong}">
			<tr>
				<td align="center">${wrong.wrongday}</td>
				<td align="center" width="500">${wrong.wrongreason}</td>
			 	<td align="center">${wrong.result}</td>
			</tr>
		   </c:forEach>
		</table>
		<div id="down">
			<a href="page.jsp" class="btn btn-default btn-lg active" type="button">返回上一页</a>
		</div>
		
	</div>

</body>
</html>