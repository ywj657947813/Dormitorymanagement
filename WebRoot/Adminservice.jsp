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
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
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
  
  <script type="text/javascript">

$(document).ready(function(){
  //获取宿舍号下拉框的值
    $.ajax({
         url: "dormSelectList",
         type: "Post",
         contentType: "application/json",
         dataType: "json",
         success: function (data) {
            var ddl = $("#dormId");

            //方法1：添加默认节点 
            //ddl.append("<option value='-1'>--请选择--</option>");

            //方法2：添加默认节点
            //ddl[0].options.add(new Option("--请选择--", "-1"));

            //转成Json对象
            var result = eval(data);

            //循环遍历 下拉框绑定
            $(result).each(function (key) {
                //第一种方法
                var opt = $("<option></option>").text(result[key].dormna).val(result[key].id);
                ddl.append(opt);

               //第二种方法
               // var proid = result[key].ProID;
                // var proname = result[key].ProName;
                //调用自定义方法
                //AppendOption(proid, proname);
            });

                //第三种方法
                //$.each(result, function (key, value) {
                //alert("dd");
                //var op = new Option(value.ProName, value.ProID);
               // ddl[0].options.add(op);
         // });
        },
        error: function (data) {
            alert("Error");
        }
    });
});
 
function deleService(){
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
			url :"deleteService",
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
    <form name="form2" action="selectService" method="get">
    	<table class="table table-bordered" width="500" height="60">
    	     <tr>
				<td align="center"><h4>按条件查询</h4></td>	
                    
			    <td align="center"><h4>宿舍楼:<select id="dormId" name="dormId" editable="false" panelHeight="auto" style="width: 150px">
											       
											</select>
				</h4></td>
				<td align="center"><h4>维修类型:<select  name="type" editable="false" panelHeight="auto" style="width: 150px">
											     <option value="">请选择...</option>
												 <option value="公共设施">公共设施</option>
												 <option value="水电设施">水电设施</option>
												 <option value="家具设施">家具设施</option>
											</select>
				</h4></td>
			    <td align="center"><h4>维修状态:<select name="state" editable="false" panelHeight="auto" style="width: 150px">
											    <option value="">请选择...</option>
												<option value="待维修">待维修</option>
												<option value="维修中">维修中</option>
												<option value="已完成">已完成</option>
											</select>
				</h4></td>	 
				   
		        <td align="center">
		         	<input type="text" value="admin" name="action" hidden="true"/>
		        	<input class="btn btn-primary btn-lg active" type="submit" value="查询"></input>&nbsp;&nbsp;
		        	<input class="btn btn-primary btn-lg active" type="button" value="删除" onclick="deleService()"/>
		        </td>
			</tr>
   	    </table>
   	</form>
   	<br/>
    <table class="table table-bordered">
     <c:choose> 
      <c:when test="${service.beanList!=null}" >
			<tr>
				<td align="center" width="5%"><input type="checkbox" id="all" onClick="if(this.checked==true) { checkAll('Ids'); } else { clearAll('Ids'); }" >全选</td>
			    <td align="center" width="5%"><h4>学号</h4></td>
				<td align="center" width="5%"><h4>宿舍楼</h4></td>
				<td align="center" width="5%"><h4>房间号</h4></td>	
				<td align="center" width="5%"><h4>名字</h4></td>				
				<td align="center" width="10%"><h4>维修物品类型</h4></td>				
				<td align="center" width="20%"><h4>维修物品及原因</h4></td>	
				<td align="center" width="9%"><h4>联系方式</h4></td>
				<td align="center" width="8%"><h4>报修时间</h4></td>
				<td align="center" width="8%"><h4>维修状态</h4></td>
			</tr>
	   </c:when>
	   <c:otherwise>
				<h3 align="center">没有数据！</h3> 
	   </c:otherwise>
	  </c:choose>
	  <c:forEach var="service" items="${service.beanList}">
			<tr>
				<td align="center"><input type="checkbox" name="Ids" value="${service.id}"/></td>
				<td align="center">${service.stuid}</td>
				<td align="center">${service.dormName}</td>
				<td align="center">${service.roomno}</td>
				<td align="center">${service.name}</td>
				<td align="center">${service.servicetype}</td>
				<td align="center">${service.servicereason}</td>
				<td align="center">${service.phone}</td>
				<td align="center">${service.time}</td>
				<td align="center">${service.state}</td>
			</tr>
     </c:forEach>
			<tr>
			</tr>
			
		</table>
	<c:if test="${service.beanList!=null}">
    <center>
             第${service.pc}页/共${service.tp}页&nbsp;&nbsp;
       <a href="<c:url value='${service.url}&pc=1' />">首页</a>&nbsp;&nbsp;
       <c:choose> 
             <c:when test="${service.pc>1}" >     
			   <a href="<c:url value='${service.url}&pc=${service.pc-1}'/>">上一页</a>
			 </c:when>
			 <c:otherwise>
			    上一页
			 </c:otherwise>
	   </c:choose>
	   
	   <c:choose>
	        <%-- 页数不足10页，则全部显示出来 --%>
	   		<c:when test="${service.tp<=10}">
	   		 	<c:set var="begin" value="1"></c:set>
	   		 	<c:set var="end" value="${service.tp}"></c:set>
	   		</c:when>
	   		<c:otherwise>
	   			<%-- 当总页数大于10时 --%>
	   			<c:set var="begin" value="${service.pc-5}"></c:set>
	   		 	<c:set var="end" value="${service.pc+4}"></c:set>
	   		 	<%-- 头溢出 --%>
	   		 	<c:if test="${begin<1}">
	   		 		<c:set var="begin" value="1"></c:set>
	   		 		<c:set var="end" value="10"></c:set>
	   		 	</c:if>
	   		 	<%-- 尾溢出 --%>
	   		 	<c:if test="${end>service.tp}">
	   		 		<c:set var="end" value="${service.tp}"></c:set>
	   		 		<c:set var="begin" value="${service.tp-9}"></c:set>
	   		 	</c:if>
	   		</c:otherwise>
	   </c:choose>
	   <%-- 循环页面列表 --%>
	   <c:forEach var="i" begin="${begin}" end="${end}">
	   		<c:choose> 
	   			<c:when test="${i eq service.pc}">
	   				[${i}]
	   			</c:when>
	   			<c:otherwise>
	   				<a href="<c:url value='${service.url}&pc=${i}'/>">[${i}]</a>
	   			</c:otherwise>
	   		</c:choose>
	   </c:forEach>
	        
       <c:choose> 
             <c:when test="${service.pc<service.tp}" >     
			   <a href="<c:url value='${service.url}&pc=${service.pc+1}'/>">下一页</a>
			 </c:when>
			 <c:otherwise>
			    下一页
			 </c:otherwise>
	   </c:choose>
       &nbsp;&nbsp;
       <a href="<c:url value='${service.url}&pc=${service.tp}' />">尾页</a>
    </center>
   </c:if>
		</div>		
      
    </body>
    </html>
