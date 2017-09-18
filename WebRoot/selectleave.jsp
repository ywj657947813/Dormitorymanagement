
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
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
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
	width:80%;
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
<script type="text/javascript">
function changeLeave(id,lasttime,state){
    //获取当前时间，单位日
    var date = new Date();
    var seperator1 = "-";
//     var seperator2 = ":";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();
    
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hour >= 1 && hour <= 9) {
        hour = "0" + hour;
    }
    if (minute >= 0 && minute <= 9) {
        minute = "0" + minute;
    }
    if (second >= 0 && second <= 9) {
        second = "0" + second;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;   //当前时间（年月日 ）
	var sArr = currentdate.split("-");             //当前时间（年月日 ）
    var eArr = lasttime.split("-");                      //回校时间（年月日 ）
    var sRDate = new Date(sArr[0], sArr[1], sArr[2]); 
    var eRDate = new Date(eArr[0], eArr[1], eArr[2]); 
    var result = (eRDate-sRDate)/(24*60*60*1000);  //假期剩余天数
    //结束前先判断是否被接受
    if(state=="未审批" || state=="已拒绝"){
        alert("此信息未被接受！");
        return;
    }
    var a;
    if(result>0){
        a="假期还有"+result+"天，你确定要结束假期吗？";
    }else{
        a="你确定要结束假期吗？";
    }
    if(confirm(a)){
        alert("sss");
		$.ajax({
		url :"changeLeave",
		type:"POST", //传递方式
		data:{"Id":id},
		success:function(result){
			    var result = eval("(" + result + ")");
		       
		        if(result.msg){
		            alert(result.msg);
			        window.location.reload();//删除后，自动刷新页面
		        }
	        }
		});
    }
}

function deleteLeave(id,state){
//     var state=document.getElementById(state1).value; //状态
    if(state=="已审批"){
        alert("此请假信息已被接受，不能删除！");
        return;
    }
    if(confirm("你确定要删除该条信息吗？")){
		$.ajax({
		url :"deleteLeave",
		type:"POST", //传递方式
		data:{"Id":id},
		success:function(result){
			    var result = eval("(" + result + ")");
		       
		        if(result.msg){
		            alert(result.msg);
			        window.location.reload();//删除后，自动刷新页面
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
      		<li><b><p class="navbar-text"><font color="#FFFFFF">欢迎您，  ${currentUser.name}同学！</font></p></b></li>
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
		   <c:when test="${leave!=null}" >
			<tr>
				<td align="center"><h4>请假人</h4></td>
				<td align="center" colspan="3"><h4>请假时间</h4></td>
				<td align="center"><h4>请假去向</h4></td>
				<td align="center"><h4>请假理由</h4></td>
				<td align="center">状态</td>
				<td align="center">操作</td>
			</tr>
		   </c:when>
		   <c:otherwise>
				<h3 align="center">你当前没有新的请假信息！</h3> 
		   </c:otherwise>
	     </c:choose>
	     
		 <c:forEach var="leave" items="${leave}">
			<tr>
				<td align="center">${leave.name}</td>
				<td align="center" width="120px">${leave.firsttime}</td>
				<td align="center" >至</td>
				<td align="center" width="120px">${leave.lasttime}</td>
				<td align="center" width="150px">${leave.address}</td>
				<td align="center" width="300px">${leave.leavereason}</td>
				<td align="center">${leave.state}</td>
				<td align="center">
				    <input type="button" value="结束" onclick="changeLeave('${leave.id}','${leave.lasttime}','${leave.state}')"/>
				    <input type="button" value="删除" onclick="deleteLeave('${leave.id}','${leave.state}')"/>
				</td>
			</tr>
		</c:forEach>
		</table>
		<div id="down">
			<a href="page.jsp" class="btn btn-default btn-lg active" type="button">返回上一页</a>
		</div>
		
	</div>

</body>
</html>