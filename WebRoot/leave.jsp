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
    <base href="<%=basePath%>">
    
    <title>学生宿舍管理系统</title>
    <script type="text/javascript" src="lhgcalendar/lhgcore.js"></script>
	<script type="text/javascript" src="lhgcalendar/lhgcalendar.js"></script>
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
	width:40%;
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
<script type="text/javascript">
function CheckLogin(){
 	    var date = document.getElementById('c10').value;     //开始日期
 	    var date1 = document.getElementById('c11').value;    //结束日期
 	    var phone = document.getElementById('studentsphone').value;     //学生联系
 	    var pphone = document.getElementById('Parentsphone').value;     //家长联系
	    var flag = true;
	    
	    
		var r = new RegExp("^[1-2]\\d{3}-(0?[1-9]||1[0-2])-(0?[1-9]||[1-2][1-9]||3[0-1])$");
// 	    var k=/^(\d{4})\/([\d]+)\/([\d]+)$/;
// 	    var reg = date.match(/^(\d{4})\/([\d]+)\/([\d]+)$/);  （判断格式为年/月/日）

		//alert(r.test(date));
		//alert(r.test(date1));
	    if(!(r.test(date)&&r.test(date1))){
	        alert("日期格式不正确，格式为yyyy-mm-dd！");
	        return false;
	    }
	    if(isNaN(phone)||phone.length!=11){		      
            alert("您输入的学生联系电话有误，请输入长度为11的数字号码！");
            return false;
        } 
        if(isNaN(pphone)||pphone.length!=11){		      
            alert("您输入的家长联系电话有误，请输入长度为11的数字号码！");
            return false;
        } 
	    return flag;
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
    <h1 align="center">请假申请表</h1>
    <hr size="1" style="width:55%;" align="center">
     <div id="table">
     <form name="form4" action="leave" method="post" onSubmit="return CheckLogin();">
    	<table class="table table-bordered">
			<tr>
				<td align="center" width="250">请假时间：</td>
				<td align="center" width="500">
					<input type="text" required id="c10" placeholder="必填。" onclick="J.calendar.get({to:'c11,min'});" name="firsttime"/>&nbsp;至&nbsp;
					<input type="text" required id="c11" onclick="J.calendar.get({to:'c10,max'});" name="lasttime"/>
				</td>
			</tr>
			<tr>
				<td align="center">请&nbsp;&nbsp;假&nbsp;&nbsp;人：</td>
				<td align="center">
					<input type="text" required size="50" style="resize:none;" name="who" value="${currentUser.name}" readonly />
				</td>
			</tr>
			<tr>
				<td align="center">请假理由：</td>
				<td align="center">
					<input type="text" required size="50" style="resize:none;" name="leavereason" placeholder="必填，请简要说明理由。"  />
				</td>
			</tr>
			<tr>
				<td align="center">请假去向：</td>
				<td align="center">
					<input type="text" required size="50" style="resize:none;" name="where" placeholder="必填，请简要说明地址。"  />
				</td>
			</tr>
			<tr>
				<td align="center">联系方式：</td>
				<td align="center">
					<input type="text" required size="50" style="resize:none;" id="studentsphone" name="studentsphone" value="${currentUser.phone}"  />
				</td>
			</tr>
			<tr>
				<td align="center">家长联系方式：</td>
				<td align="center">
					<input type="text" required size="50" style="resize:none;" id="Parentsphone" name="Parentsphone"  placeholder="必填。"/>
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
