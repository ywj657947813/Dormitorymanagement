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
#page{
	width:90%;
	margin-left:auto;
	margin-right:auto;
}
</style>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript">

$(document).ready(function(){
  //获取系别下拉框的值
	$.ajax({
        url: "depaSelectList",
        type: "Post",
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
           var ddl = $("#depa");

           var result = eval(data);

           //循环遍历 下拉框绑定
           $(result).each(function (key) {
               //第一种方法
               var opt = $("<option></option>").text(result[key].depaNa).val(result[key].depaId);
               ddl.append(opt);

           });

         },
         error: function (data) {
           alert("Error");
         }
	 });
});

function deleLeave(){
	var optIDs =[];  
	$("input:checkbox[name='Ids']:checked").each(function(){ 
	    optIDs.push(this.value);
	}); //遍历，用逗号串联
	if(optIDs==""){
		alert("请选择一条要删除的记录！");
		return false;   
	}else{
	    var ids=optIDs.join(",");
	    if(confirm("你确定要删除该"+optIDs.length+"条记录吗？")){
			$.ajax({
			url :"deleteLeave",
			type:"POST", //传递方式
			data:{"Ids":ids},
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
    <br>
    <div id="page">
   <form action="selectLeave" method="get">
    <table class="table table-bordered" width="800" height="60">
    	     <tr>
				<td align="center">按条件查询</td>	
                 
			    <td align="center">学号:<input type="text" name="id" style="width: 140px">
				</td>
				<td align="center">系别:<select  id="depa" name="depa" editable="false" panelHeight="auto" style="width: 140px;height:28px">
											     
											</select>
				</td>
			    <td align="center">信息状态:<select name="state" editable="false" panelHeight="auto" style="width: 140px;height:28px">
											    <option value="">请选择...</option>
												<option value="未审批">未审批</option>
												<option value="已审批">已审批</option>
												<option value="已结束">已结束</option>
											</select>
			    </td>	 
		        <td align="center" style="padding-top:15px;">
		            <input type="text" value="admin" name="action" hidden="true"/>
		            <input class="btn btn-primary btn-lg active" type="submit" value="查询"/>&nbsp;&nbsp;
		            <input class="btn btn-primary btn-lg active" type="button" value="删除" onclick="deleLeave()"/>
		        </td>
			</tr>
   	    </table>
    </form>
    
    <table class="table table-bordered">
    <c:choose> 
     <c:when test="${leave.beanList!=null}" >
			<tr>
				<td align="center" width="5%"><input type="checkbox" id="all" onClick="if(this.checked==true) { checkAll('Ids'); } else { clearAll('Ids'); }" >全选</td>
				<td align="center" width="8%"><h4>学号</h4></td>	
				<td align="center" width="8%"><h4>姓名</h4></td>
				<td align="center" colspan="3"><h4>请假时间</h4></td>				
				<td align="center" width="19%"><h4>请假理由</h4></td>				
				<td align="center" width="8%"><h4>请假去向</h4></td>	
				<td align="center" width="11%"><h4>学生联系方式</h4></td>
				<td align="center" width="11%"><h4>家长联系方式</h4></td>
				<td align="center" width="6%"><h4>状态</h4></td>
			</tr>
	 </c:when>
	 <c:otherwise>
				<h3 align="center">没有数据！</h3> 
	 </c:otherwise>
	</c:choose>
	<c:forEach var="leave" items="${leave.beanList}">
			<tr>
				<td align="center"><input type="checkbox" name="Ids" value="${leave.id}"/></td>
				<td align="center" >${leave.stuid}</td>
				<td align="center" >${leave.name}</td>
				<td align="center" width="9%">${leave.firsttime}</td>
				<td align="center" width="1%">至</td>
				<td align="center" width="9%">${leave.lasttime}</td>
				<td align="center">${leave.leavereason}</td>
				<td align="center">${leave.address}</td>
				<td align="center">${leave.studentphone}</td>
				<td align="center" >${leave.parentsphone}</td>
				<td align="center" >${leave.state}</td>
			</tr>
		</c:forEach>	
			
		</table>
   <c:if test="${leave.beanList!=null}">
    <center>
             第${leave.pc}页/共${leave.tp}页&nbsp;&nbsp;
       <a href="<c:url value='${leave.url}&pc=1' />">首页</a>&nbsp;&nbsp;
       <c:choose> 
             <c:when test="${leave.pc>1}" >     
			   <a href="<c:url value='${leave.url}&pc=${leave.pc-1}'/>">上一页</a>
			 </c:when>
			 <c:otherwise>
			    上一页
			 </c:otherwise>
	   </c:choose>
	   
	   <c:choose>
	        <%-- 页数不足10页，则全部显示出来 --%>
	   		<c:when test="${leave.tp<=10}">
	   		 	<c:set var="begin" value="1"></c:set>
	   		 	<c:set var="end" value="${leave.tp}"></c:set>
	   		</c:when>
	   		<c:otherwise>
	   			<%-- 当总页数大于10时 --%>
	   			<c:set var="begin" value="${leave.pc-5}"></c:set>
	   		 	<c:set var="end" value="${leave.pc+4}"></c:set>
	   		 	<%-- 头溢出 --%>
	   		 	<c:if test="${begin<1}">
	   		 		<c:set var="begin" value="1"></c:set>
	   		 		<c:set var="end" value="10"></c:set>
	   		 	</c:if>
	   		 	<%-- 尾溢出 --%>
	   		 	<c:if test="${end>leave.tp}">
	   		 		<c:set var="end" value="${leave.tp}"></c:set>
	   		 		<c:set var="begin" value="${leave.tp-9}"></c:set>
	   		 	</c:if>
	   		</c:otherwise>
	   </c:choose>
	   <%-- 循环页面列表 --%>
	   <c:forEach var="i" begin="${begin}" end="${end}">
	   		<c:choose> 
	   			<c:when test="${i eq leave.pc}">
	   				[${i}]
	   			</c:when>
	   			<c:otherwise>
	   				<a href="<c:url value='${leave.url}&pc=${i}'/>">[${i}]</a>
	   			</c:otherwise>
	   		</c:choose>
	   </c:forEach>
	        
       <c:choose> 
             <c:when test="${leave.pc<leave.tp}" >     
			   <a href="<c:url value='${leave.url}&pc=${leave.pc+1}'/>">下一页</a>
			 </c:when>
			 <c:otherwise>
			    下一页
			 </c:otherwise>
	   </c:choose>
       &nbsp;&nbsp;
       <a href="<c:url value='${leave.url}&pc=${leave.tp}' />">尾页</a>
    </center>
   </c:if>
		</div>
   			
  </body>
</html>
