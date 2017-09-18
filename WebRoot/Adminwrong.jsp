<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
 alert("保存成功！");
 </script>
 <%
}
 %>
<base href="<%=basePath%>">
   
<title>学生宿舍管理系统</title>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
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
#down
{
	width:160px;
	margin-right:0;
	margin-left:auto;
}

</style>
<script type="text/javascript">
   function CheckLogin(){
	    var ID = document.getElementById('id').value;     //学生学号
	    var name = document.getElementById('name').value;    //学生姓名
 	    var date = document.getElementById('c10').value;     //违纪日期
	    var flag = true;
	    
	    if(isNaN(ID)||ID.length!=8){		      
	        alert("您输入的学号有误，请输入长度为8的数字！");
	        return false;
        }
		var r = new RegExp("^[1-2]\\d{3}-(0?[1-9]||1[0-2])-(0?[1-9]||[1-2][1-9]||3[0-1])$");
		
// 	    var k=/^(\d{4})\/([\d]+)\/([\d]+)$/;
// 	    var reg = date.match(/^(\d{4})\/([\d]+)\/([\d]+)$/);  （判断格式为年/月/日）
	    if(!r.test(date)){
	        alert("日期格式不正确，格式为yyyy-mm-dd！");
	        return false;
	    }

	    //查询学号是否存在
	    $.ajax({
	       type: "POST",
	       async:false,  // 设置同步方式
	       cache:false,
	       url: "checkStuId",
	       data:{"stuID":ID,
	             "name":name,
	             "action":"xuehao",
	             "action1":"action1"
	       },
	       success:function(result){
	       var result = eval("(" + result + ")");
	       
	           if(result.msg == 'false'){
	               alert("请确认学号是否正确！");
	               flag = false;
	           }
	           if(result.msg == 'false1'){
	               alert("请确认学号跟姓名是否一致！");
	               flag = false;
	           }
	       }
        });
	    return flag;
}
	
//    function insertWrong(){
    
//     $.ajax({
// 	       type: "POST",
// 	       async:false,  // 设置同步方式
// 	       cache:false,
// 	       url: "wrongInsert",
// 	       data:$('#form2').serialize(),
// 	       success:function(result){
// 	           var result = eval("(" + result + ")");
	       
// 	           if(result.msg){
// 	               alert(result.msg);
// 	               window.location.reload(true);
// 	           }
	           
	          
// 	       }
//         });
//   }
  
</script>

  </head>
  
  <body>
    <%@ include file="menu.jsp" %>


   <h1 align="center">学生违规信息表</h1>
    <hr size="1" style="width:55%;" align="center">
   <div id="table">
    <form id="form2" name="form2" action="wrongInsert" method="post" onSubmit="return CheckLogin();">
    	<table class="table table-bordered">
			<tr>
				<td align="center" width="200">学号</td>
				<td align="center">
					<input type="text" required size="50" style="resize:none;"id="id" name="id" placeholder="必填。"></input></td>
			</tr>
			<tr>
				<td align="center">姓名</td>
				<td align="center">
					<input type="text" required size="50" style="resize:none;"id="name" name="name" placeholder="必填。"></input></td>
			</tr>
			<tr>
				<td align="center">违规日期</td>
				<td align="center">
				    <input type="text" size="50" required id="c10" placeholder="必填。" editable="false"onclick="J.calendar.get();" id="day" name="day"/>
<!-- 					<textarea required cols="52" rows="1" style="resize:none;" name="day" placeholder="必填。"></textarea></td> -->
			</tr>
			<tr>
				<td align="center">违规原因</td>
				<td align="center">
					<textarea cols="52" required rows="3" style="resize:none;"id="reason" name="reason"  placeholder="必填。"></textarea></td>
			</tr>
			<tr>
				<td align="center">处理方式</td>
				<td align="center">
					<textarea cols="52" required rows="2" style="resize:none;" id="result"name="result"  placeholder="必填。"></textarea></td>
			</tr>
		</table>
		<div id="down">
			<input class="btn btn-primary btn-lg active" type="submit"  value="提交">&nbsp;&nbsp;&nbsp;&nbsp;
			<input class="btn btn-default btn-lg active" type="reset" value="重置">
		</div>
		</form>
	</div>
  </body>
</html>
