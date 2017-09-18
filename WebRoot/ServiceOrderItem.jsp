<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生宿舍管理系统</title>
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
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
	width:75%;
	margin-left:auto;
	margin-right:auto;
}
	</style>

<script type="text/javascript">
function deleteOItem(ids,state){
	if(state=="1"){
		alert("信息已接受，不可删除！");
		return;
	}
	
	$.ajax({
	url :"deleteProduct",
	type:"POST", //传递方式
	data:{"otId":ids,"action":"orderitem"},
	success:function(result){
		    var result = eval("(" + result + ")");
	       
	        if(result.msg == 'true'){
	            alert("删除成功！");
		        window.location.reload();//删除后，自动刷新页面
	        }else if(result.msg == 'false'){
	            alert("删除失败！");
	        }
        }
	});
	     
}

</script>
  </head>
  <body>
 <%@include file="s_menu.jsp" %>
    <div id="page">
    <h1 align="center">清单详情</h1>
	<table class="table table-bordered">
                <tr>
                	<td  align="center" colspan="3" width="66%">
                		申请人：${suid1}
                	</td>
                	<td  align="center">
                		<input class="btn btn-primary btn-lg active" type="button" onclick="location.href='javascript:history.go(-1);'" value="返回"/>
                	</td>
                </tr>
		<c:choose> 
            <c:when  test="${message!=null}" >   
				<tr>
					<td align="center"><h4>物品</h4></td>	
					<td align="center"><h4>数量</h4></td>	
					<td align="center"><h4>用于宿舍</h4></td>	
					<td align="center"><h4>操作</h4></td>
				</tr>
		 
			<c:forEach var="message" items="${message.beanList}">
			<tr>
				<td align="center">${message.pname}</td>
				<td align="center">${message.count}</td>
				<td align="center">${message.dname}</td>
				<td align="center">
						<a href="javascript:deleteOItem('${message.otid}','${state1}');">删除</a>
				</td>
			</tr>
			</c:forEach>
		 </c:when>
		 <c:otherwise>
		 <tr>
		 	<td colspan="3" align="center"><h5>没有数据！</h5><td>
		 </tr> 
		 </c:otherwise>
		</c:choose>
		
	</table> 
	<!-- 分页---------------------------------------------------------------------- -->
	<c:if test="${message.beanList!=null}">
    <center>
             第${message.pc}页/共${message.tp}页&nbsp;&nbsp;
       <a href="<c:url value='${message.url}&pc=1' />">首页</a>&nbsp;&nbsp;
       <c:choose> 
             <c:when test="${message.pc>1}" >     
			   <a href="<c:url value='${message.url}&pc=${message.pc-1}'/>">上一页</a>
			 </c:when>
			 <c:otherwise>
			    上一页
			 </c:otherwise>
	   </c:choose>
	   
	   <c:choose>
	        <%-- 页数不足10页，则全部显示出来 --%>
	   		<c:when test="${message.tp<=10}">
	   		 	<c:set var="begin" value="1"></c:set>
	   		 	<c:set var="end" value="${message.tp}"></c:set>
	   		</c:when>
	   		<c:otherwise>
	   			<%-- 当总页数大于10时 --%>
	   			<c:set var="begin" value="${message.pc-5}"></c:set>
	   		 	<c:set var="end" value="${message.pc+4}"></c:set>
	   		 	<%-- 头溢出 --%>
	   		 	<c:if test="${begin<1}">
	   		 		<c:set var="begin" value="1"></c:set>
	   		 		<c:set var="end" value="10"></c:set>
	   		 	</c:if>
	   		 	<%-- 尾溢出 --%>
	   		 	<c:if test="${end>message.tp}">
	   		 		<c:set var="end" value="${message.tp}"></c:set>
	   		 		<c:set var="begin" value="${message.tp-9}"></c:set>
	   		 	</c:if>
	   		</c:otherwise>
	   </c:choose>
	   <%-- 循环页面列表 --%>
	   <c:forEach var="i" begin="${begin}" end="${end}">
	   		<c:choose> 
	   			<c:when test="${i eq message.pc}">
	   				[${i}]
	   			</c:when>
	   			<c:otherwise>
	   				<a href="<c:url value='${message.url}&pc=${i}'/>">[${i}]</a>
	   			</c:otherwise>
	   		</c:choose>
	   </c:forEach>
	        
       <c:choose> 
             <c:when test="${message.pc<message.tp}" >     
			   <a href="<c:url value='${message.url}&pc=${message.pc+1}'/>">下一页</a>
			 </c:when>
			 <c:otherwise>
			    下一页
			 </c:otherwise>
	   </c:choose>
       &nbsp;&nbsp;
       <a href="<c:url value='${message.url}&pc=${message.tp}' />">尾页</a>
        &nbsp;&nbsp;
                    共${message.tr}条记录
    </center>
   </c:if>
    </div>
   </body>
   </html>
