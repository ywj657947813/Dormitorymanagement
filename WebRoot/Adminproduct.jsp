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
    <title>学生宿舍管理系统</title>
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
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
	width:70%;
	margin-left:auto;
	margin-right:auto;
}
#table{
	width:45%;
	margin-left:auto;
	margin-right:auto;
}
</style>

<script type="text/javascript">
function deleteProduct(){
	var optIDs =[];  
	$("input:checkbox[name='pIds']:checked").each(function(){ 
	    optIDs.push(this.value);
	}); //遍历，用逗号串联
	if(optIDs==""){
		alert("请选择一条要删除的记录！");
		return false;   
	}else{
	    var ids=optIDs.join(",");
	    if(confirm("你确定要删除该"+optIDs.length+"条记录吗？")){
			$.ajax({
			url :"deleteProduct",
			type:"POST", //传递方式
			data:{"pIds":ids,"action":"product"},
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
	}   
	        
}
//点击全选执行该方法

function checkAll(name) { 
	var el = document.getElementsByTagName('input'); 
	var len = el.length; 
	for(var i=0; i<len; i++){ 
		if((el[i].type=="checkbox") && (el[i].name==name)){ 
		el[i].checked = true; 
	    } 
    } 
} 
function clearAll(name) { 
	var el = document.getElementsByTagName('input'); 
	var len = el.length; 
	for(var i=0; i<len; i++){ 
		if((el[i].type=="checkbox") && (el[i].name==name)){ 
		el[i].checked = false; 
		} 
	} 

} 
</script>
  </head>
  <body>
  
  <%@ include file="menu.jsp" %>
 
    	
    	<div id="page" >
    	<form name="form2" action="selectProduct" method="get">
    	 <table class="table table-bordered" width="500" height="60">
			<tr>
				<td align="center"><h4>按条件查询</h4></td>	
                <td align="center">物品名称:<input type="text" size="10" name="pname" /></td>	 
		        <td width="48%">
		        	<a href="Adminaddproduct.jsp" class="btn btn-primary btn-lg active">添加物品</a>
		        	 &nbsp;&nbsp;
		            <input class="btn btn-primary btn-lg active" type="submit" value="查询"/>
		            <input type="text" value="admin" name="action" hidden="true"/>
		            &nbsp;&nbsp;
		            <input class="btn btn-primary btn-lg active" type="button" value="删除" onclick="deleteProduct()"/>
		             &nbsp;&nbsp;
		            <a href="Adminpextract.jsp" class="btn btn-primary btn-lg active">提取物品</a>
		        </td>
			</tr>
		 </table>	
		</form>	
			<table class="table table-bordered">
			<c:choose> 
             <c:when test="${message!=null}" >     
			<tr>
			    <td align="center" width="7%"><input type="checkbox" id="all" onClick="if(this.checked==true) { checkAll('pIds'); } else { clearAll('pIds'); }" >全选</td>
				<td align="center"><h4>物品名称</h4></td>	
				<td align="center"><h4>物品价格</h4></td>	
				<td align="center"><h4>物品库存</h4></td>	
			</tr>
			 </c:when>
			 
			</c:choose>
			
			<c:forEach var="message" items="${message.beanList}">
			<tr>
			    <td align="center"><input type="checkbox" name="pIds" value="${message.pid}"/></td>
				<td align="center">${message.pname}</td>
				<td align="center">${message.pprice}</td>
				<td align="center">${message.ptotal}</td>
			</tr>
			</c:forEach>
		</table>
    <br/>
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
