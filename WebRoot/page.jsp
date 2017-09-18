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
#page{
	width:55%;
	margin-left:auto;
	margin-right:auto;
}
	</style>
	
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript">
   var changePhone = function(){
	     var x = prompt ("输入新的电话号码: ", ""); //弹出一个输入电话对话框
	     if(x==""){
	     	alert("请输入电话号码！");
	     	return;
	     }
	     if(!(isNaN(x)||x.length!=11)){
	     
		     if(confirm("你确定修改为"+x+"吗？")){
			         $.ajax({
				       type: "POST",
				       async:false,  // 设置同步方式
				       cache:false,
				       url: "changePhone",
				       data:{"phone":x},
				       success:function(result){
				          var result = eval("(" + result + ")");
				        
				          if(result.msg){
				              alert(result.msg);
			                  window.location.reload(true);//强制刷新页面，从服务器端刷新数据
			              }
				       }
			         });
		      }
	     }else{
	       alert("您输入的联系电话有误，请输入长度为11的数字号码！");
	     }
   };
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
        		<li><a href="login.jsp"><b><font color="#CCCCFF">退出登陆</font></b></a></li>
      			</ul>
    		</div>
    	</div>
    </div>
    <br>
    <br>
    <div id="page">
		<table class="table table-bordered">
		
			<tr>
			    <td align="center" width="70"><h4>系别</h4></td>
				<td align="center" width="150">${currentUser.depaName}</td>
				<td align="center" width="70"><h4>专业</h4></td>
				<td align="center" width="150">${currentUser.majorName}</td>
				<td align="center" width="80" rowspan="4"><br><img src="images/4.jpg"/></td>
			</tr>
			<tr>
				<td align="center" width="70"><h4>学号</h4></td>
				<td align="center" width="150" >${currentUser.id}</td>
				<td align="center" width="70"><h4>姓名</h4></td>
				<td align="center" width="150">${currentUser.name}</td>
			</tr>
			
			<tr>
				<td align="center" width="70"><h4>性别</h4></td>
				<td align="center" width="150">${currentUser.sex}</td>
				<td align="center" width="70"><h4>电话</h4></td>
				<td width="150">${currentUser.phone}&nbsp;<a href="javascript:changePhone()">修改</a></td>
			</tr>
			<tr>
				<td  width="82"><h4>所在宿舍</h4></td>
				<td align="center" width="75">${currentUser.dormName}</td>
				<td  width="82"><h4>所在房间</h4></td>
				<td align="center" width="75">${currentUser.roomno}</td>
			</tr>
			<tr>
				<td  width="82"><h4>请假记录</h4></td>
				<td align="center" colspan="4"><a href="selectLeave?action=student&id=${currentUser.id}">查询我的请假记录</a></td>
			</tr>
			<tr>
				<td  width="82"><h4>违规记录</h4></td>
				<td align="center" colspan="4"><a href="selectWrong?action=student&stuId=${currentUser.id}">查询我的违规记录</a></td>
			</tr>
			<tr>
				<td  width="82" height="5"><h4>报修信息</h4></td>
				<td align="center" colspan="4"><a href="selectService?action=student&stuId=${currentUser.id}">查询我的报修信息</a></td>
			</tr>
		
		</table>
		</div>
		
	
	</body>
</html>		

