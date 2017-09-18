<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
#page{
	width:90%;
	margin-left:auto;
	margin-right:auto;
}
</style>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript">
//学生删除维修信息
function deleteService(Id,state){
      if(state=="维修中"){
         alert("正在维修中，不能删除！");
         return;
      }
	  if(confirm("你确定删除该信息吗？")){
	  
	    $.ajax({
		       type: "POST",
		       async:false,  // 设置同步方式
		       cache:false,
		       url: "deleteService",
		       data:{"Id":Id},
		       success:function(result){
		          var result = eval("(" + result + ")");
		        
		          if(result.msg){
		              alert(result.msg);
	                  window.location.reload(true);//强制刷新页面，从服务器端刷新数据
	              }
		       }
	        });
	  }
  }
</script>
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
        		<li><a href="safeExit"><b><font color="#CCCCFF">退出登陆</font></b></a></li>
      			</ul>
    		</div>
    	</div>
    </div>
  	<br/>
  	<div id="page">
  	 <c:choose> 
      <c:when test="${service!=null}">
  		<table class="table table-bordered" width="500" height="60">
  		<tr>
			<td align="center" width="5%"><h4>学号</h4></td>
			<td align="center" width="5%"><h4>宿舍楼</h4></td>
			<td align="center" width="5%"><h4>房间号</h4></td>	
			<td align="center" width="5%"><h4>名字</h4></td>				
			<td align="center" width="10%"><h4>维修物品类型</h4></td>				
			<td align="center" width="20%"><h4>维修物品及原因</h4></td>	
			<td align="center" width="9%"><h4>联系方式</h4></td>
			<td align="center" width="8%"><h4>报修时间</h4></td>
			<td align="center" width="8%"><h4>维修状态</h4></td>
			<td align="center" width="5%"><h4>操作</h4></td>
		</tr>
	   <c:forEach var="service" items="${service}">
		<tr>
			<td align="center">${service.stuid}</td>
			<td align="center">${service.dormName}</td>
			<td align="center">${service.roomno}</td>
			<td align="center">${service.name}</td>
			<td align="center">${service.servicetype}</td>
			<td align="center">${service.servicereason}</td>
			<td align="center">${service.phone}</td>
			<td align="center">${service.time}</td>
			<td align="center">${service.state}</td>
			<td>
			    <input type="button" value="删除" onclick="deleteService('${service.id}','${service.state}')"/>
			</td>
		</tr>
       </c:forEach>
  	   </table>
  	   </c:when>
  	   <c:otherwise>
  	   		<h3 align="center">没有新的维修信息！</h3> 
  	   </c:otherwise>
  	   </c:choose>	
  	</div>
  </body>
</html>