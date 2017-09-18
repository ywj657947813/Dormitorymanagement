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
#page{
	width:90%;
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

$(document).ready(function(){
   //获取专业下拉框的值
   $.ajax({
          url: "majorSelectList",
          type: "Post",
          contentType: "application/json",
          dataType: "json",
          
          success: function (data) {
             var ddl = $("#major");
             //转成Json对象
             var result = eval(data);
             //循环遍历 下拉框绑定
             $(result).each(function (key) {
                 //第一种方法
                 var opt = $("<option></option>").text(result[key].majorNa).val(result[key].majorId);
                 ddl.append(opt);
             });
         },
         error: function (data) {
             alert("Error");
         }
  });
  //获取班级下拉框的值
   $.ajax({
           url: "gradeSelectList",
           type: "Post",
           contentType: "application/json",
           dataType: "json",
           success: function (data) {
              var ddl = $("#grade");
            // 转成Json对象
              var result = eval(data);
             //循环遍历 下拉框绑定
              $(result).each(function (key) {
                  //第一种方法
                  var opt = $("<option></option>").text(result[key].gradena).val(result[key].gradeid);
                  ddl.append(opt);
              });
          },
          error: function (data) {
              alert("Error");
          }
   });

});

function selectGrade(majorId){
//当选择专业的时候，显示对应的班级
      $.ajax({
            url: "gradeSelectList",
            type: "Post",
            contentType:"application/x-www-form-urlencoded",
            dataType: "json",
            data:{"majorId":majorId},
            success: function (data) {
               document.getElementById("grade").options.length = 0; //先清空专业列表的值
               var ddl = $("#grade");
             //  转成Json对象
               var result = eval(data);
            //  循环遍历 下拉框绑定
               $(result).each(function (key) {
                 // 第一种方法
                   var opt = $("<option></option>").text(result[key].gradena).val(result[key].gradeid);
                   ddl.append(opt);
               });
           },
           error: function (data) {
               alert("Error");
           }
      });
}   

//导出数据（学生违纪信息）
function exportmessage(message){
    if(message=="true"){
    	alert("没有数据，不可以导出！");
    	return;
    }
    var fileName = prompt ("输入文件名: ", ""); //弹出一个对话框
    if(fileName==null){
    	return;
    }
    
    if(fileName==""){
    	alert("请输入文件名！");
    	return;
    }
    window.open('exportMessage?type=wrong&fileName='+fileName);

    
}
</script>

  </head>
  
  <body>
     <%@include file="t_menu.jsp" %>    
  <body>
   <div id="page">
    	<form name="form2" action="selectWrong" method="get">
    	<table class="table table-bordered" width="500" height="60">
			<tr>
				<td align="center"><h4>按条件查询</h4></td>	
                <td align="center">学号:<input type="text" size="10" name="selectid" /></td>	 
			    <td align="center">专业:
			        <select id="major"  name="major" editable="false" panelHeight="auto" style="width: 180px;height:28px" onchange="selectGrade(this.value)">
											       
					</select>
			    </td>	 
			    <td align="center">班级:
			        <select id="grade"  name="grade" editable="false" panelHeight="auto" style="width: 180px;height:28px">
											       
					</select>
			    </td>	 
		        <td width="200px">
		            <input type="text" value="teacher" name="action" hidden="true"/>
		            <input  type="submit" value="查询"/>
		            <input  type="button" onclick="exportmessage('${message==null}')" value="导出数据"/>
		        </td>
			</tr>
		</table>	
		</form>	
			<table class="table table-bordered">
			<c:choose> 
             <c:when test="${message!=null}" >     
			<tr>
				<td align="center" width="8%"><h4>学号</h4></td>	
				<td align="center" width="8%"><h4>名字</h4></td>	
				<td align="center" width="8%"><h4>专业</h4></td>	
				<td align="center" width="8%"><h4>违纪日期</h4></td>				
				<td align="center" width="19%"><h4>违纪原因</h4></td>				
				<td align="center" width="20%"><h4>处理方式</h4></td>
			</tr>
			 </c:when>
			 <c:otherwise>
			   <h3 align="center">不存在违规记录！</h3> 
			 </c:otherwise>
			</c:choose>
			
			<c:forEach var="message" items="${message.beanList}">
			<tr>
				<td align="center">${message.stuid}</td>
				<td align="center">${message.name}</td>
				<td align="center">${message.majorna}</td>
				<td align="center">${message.wrongday}</td>
				<td align="center">${message.wrongreason}</td>
				<td align="center">${message.result}</td>
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
    </center>
   </c:if>
    </div>
  </body>
</html>
