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
//维修员删除申领信息
function deletePOrder(oid,state){
	if(state=="1"){
		alert("信息已接受，不可删除！");
		return;
	}
    if(confirm("你确定要删除吗？")){
		$.ajax({
		url :"deleteProduct",
		type:"POST", //传递方式
		data:{"oIds":oid,"action":"order"},
		success:function(result){
			    var result = eval("(" + result + ")");
		        if(result.msg == 'true'){
		            alert("删除成功！");
			        window.location.reload();//删除后，自动刷新页面
		        }
	        }
		});
    }
	        
}
</script>
  </head>
  
  <body>
     <%@include file="s_menu.jsp" %>
  	<div id="page">
  	 <h1 align="center">物品申领信息</h1>
  	 <table class="table table-bordered">
		<c:choose> 
            <c:when test="${message!=null}" >     
		<tr>
		    <td align="center"><h4>清单编号</h4></td>	
			<td align="center"><h4>时间</h4></td>	
			<td align="center"><h4>状态</h4></td>	
			<td align="center"><h4>备注</h4></td>	
			<td align="center"><h4>操作</h4></td>	
			
		</tr>
		 </c:when>
		 
		</c:choose>
		
		<c:forEach var="message" items="${message.beanList}">
		<tr>
		    <td align="center">${message.oid}</td>
			<td align="center">${message.otime}</td>
			<td align="center">
				<c:if test="${message.state==0}">
					未接受
				</c:if>
				<c:if test="${message.state==1}">
					已接受
				</c:if>
				<c:if test="${message.state==2}">
					已领取
				</c:if>
				<c:if test="${message.state==3}">
					已延时
				</c:if>
			</td>
			<td align="center">${message.descs}</td>
			<td align="center">
				<a href="selectProductOrder?action1=orderitem&action=service&oid=${message.oid}&suid=${message.suid}&state=${message.state}">查看详情</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:deletePOrder('${message.oid}','${message.state}');" >删除</a>
			</td>
		</tr>
		</c:forEach>
	</table>	
  	</div>
  </body>
</html>