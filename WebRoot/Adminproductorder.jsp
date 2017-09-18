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
	width:90%;
	margin-left:auto;
	margin-right:auto;
}
	</style>

<script type="text/javascript">
function deletePOrder(){
	var optIDs =[];  
	$("input:checkbox[name='oIds']:checked").each(function(){ 
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
			data:{"oIds":ids,"action":"order"},
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
 
    	
    	<div id="page">
    	<form name="form2" action="selectProductOrder" method="get">
    	<table class="table table-bordered" width="500" height="60">
			<tr>
				<td align="center"><h4>按条件查询</h4></td>	
                <td align="center">申请人工号:<input type="text" size="10" name="suid" /></td>	 
                <td align="center">清单编号:<input type="text" size="10" name="oid" /></td>	 
			    <td align="center">状态:<select name="state" editable="false" panelHeight="auto" style="width: 140px;height:28px">
										    <option value="">请选择...</option>
											<option value="0">未接受</option>
											<option value="1">已接受</option>
											<option value="2">已领取</option>
											<option value="3">已延时</option>
									   </select>
				</td>
		        <td width="200px">
		            <input class="btn btn-primary btn-lg active" type="submit" value="查询"/>
		            <input type="text" value="admin" name="action" hidden="true"/>
		            &nbsp;&nbsp;
		            <input class="btn btn-primary btn-lg active" type="button" value="删除" onclick="deletePOrder()"/>
		        </td>
			</tr>
		</table>	
		</form>	
	<table class="table table-bordered">
		<c:choose> 
            <c:when test="${message!=null}" >     
		<tr>
		    <td align="center" ><input type="checkbox" id="all" onClick="if(this.checked==true) { checkAll('oIds'); } else { clearAll('oIds'); }" >全选</td>
		    <td align="center" ><h4>清单编号</h4></td>	
			<td align="center" ><h4>申请人工号</h4></td>	
			<td align="center" ><h4>时间</h4></td>	
			<td align="center" ><h4>状态</h4></td>	
			<td align="center" hidden><h4>备注</h4></td>	
			<td align="center" ><h4>操作</h4></td>	
			
		</tr>
		 </c:when>
		 
		</c:choose>
		
		<c:forEach var="message" items="${message.beanList}">
		<tr>
		    <td align="center"><input type="checkbox" name="oIds" value="${message.oid}"/></td>
		    <td align="center">${message.oid}</td>
			<td align="center">${message.suid}</td>
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
			<td align="center" hidden>${message.descs}</td>
			<td align="center">
				<a href="selectProductOrder?action1=orderitem&action=admin&oid=${message.oid}&suid=${message.suid}&state=${message.state}">查看详情</a>
			</td>
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
