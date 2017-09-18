<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 <%
 if ((String)request.getAttribute("success") == "true") {
 %>
 <script>
 alert("提交成功！");
 </script>
 <%
}
 %>
 <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript">
  //此方法为文档加载完调用
$(document).ready(function(){
  //获取当前时间年 月 日
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
       
       $("#NowTime").val(currentdate);
 });
 
function CheckLogin(){
 	    var phone = document.getElementById('phone').value;     //学生联系
	    var flag = true;
	    if(isNaN(phone)||phone.length!=11){		      
                   alert("您输入的联系电话有误，请输入长度为11的数字号码！");
                   return false;
        } 
	    return flag;
}
</script>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
    
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
	width:45%;
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
	width:160px;
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
        		<li><a href="safeExit"><b><font color="#CCCCFF">退出登陆</font></b></a></li>
      			</ul>
    		</div>
    	</div>
    </div>
    <h1 align="center">报修表</h1>
    <hr size="1" style="width:55%;" align="center">
    <div id="table">
    <form name="form2" action="dormService" method="post" onSubmit="return CheckLogin();">
    	<table class="table table-bordered">
			<tr>
				<td align="center" width="200">宿舍号</td>
				<td >
				    <input type="text" required size="10" style="resize:none;"  value="${currentUser.dormName}" disabled="true"/>
				    &nbsp;&nbsp;
				    <input type="text" required size="10" style="resize:none;"  value="${currentUser.roomno}" disabled="true"/>
				    <input type="text" required size="50" style="resize:none;" name="Dormmun" value="${currentUser.dormno}" hidden="true"/>
				    <input type="text" required size="50" style="resize:none;" name="RoomId" value="${currentUser.roomno}" hidden="true"/>
				</td>
			</tr>
			<tr>
				<td align="center">维修物品类型</td>
				<td >
				    <select  id="thing" name="thing" required editable="false" panelHeight="auto" style="width: 150px; height:30px;">
					    <option value="">请选择...</option>
						<option value="公共设施">公共设施</option>
						<option value="水电设施">水电设施</option>
						<option value="家具设施">家具设施</option>
					</select>
<!-- 					<input type="text" required size="50" style="resize:none;" name="thing" placeholder="必填。"></input> -->
				</td>
					
			</tr>
			<tr>
				<td align="center">维修物品及原因</td>
				<td>
					<textarea required cols="47" rows="3" style="resize:none;" name="reason" placeholder="必填，请简要说明损坏情况及其理由。"></textarea></td>
			</tr>
			<tr>
				<td align="center">报修人</td>
				<td>
				    <input type="text" required size="45" style="resize:none;" name="Dname" value="${currentUser.name}"  />
				</td>
			</tr>
			<tr>
				<td align="center">联系电话</td>
				<td>
					 <input type="text" required size="45" style="resize:none;" id="phone" name="phone" value="${currentUser.phone}"  />
			         <input type="text" id="NowTime" name="NowTime" hidden="true"/>
				</td>
			</tr>
		</table>
		<div id="down">
			<input class="btn btn-primary btn-lg active" type="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;
			<input class="btn btn-default btn-lg active" type="reset" value="重置">
		</div>
		</form>
	</div>

</body>
</html>