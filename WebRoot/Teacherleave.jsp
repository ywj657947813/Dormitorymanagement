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
$(document).ready(function(){
  //获取宿舍号下拉框的值
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

//批准假期
function changeLeave(id,name,state){

    if(state=="已审批"){
        alert("此信息已批准过了！");
        return;
    }else if(state=="已结束"){
        alert("此信息已结束了！");
        return;
    }
    if(confirm("确定批准学生 "+name+" 的假期吗？")){
		$.ajax({
		url :"changeLeave",
		type:"POST", //传递方式
		data:{"Id":id,
		      "action":"teacher",
		      "state":"已批准"
		},
		success:function(result){
			    var result = eval("(" + result + ")");
		       
		        if(result.msg){
		            alert("批准成功！");
			        window.location.reload();//删除后，自动刷新页面
		        }
	        }
		});
    }
}

//拒绝假期
function objectLeave(id,name,state){

    if(state=="已拒绝"){
        alert("不要重复点击！");
        return;
    }else if(state=="已结束"){
        alert("此信息已结束了！");
        return;
    }
    if(confirm("确定拒绝学生 "+name+" 的假期吗？")){
		$.ajax({
		url :"changeLeave",
		type:"POST", //传递方式
		data:{"Id":id,
		      "action":"teacher",
		      "state":"已拒绝"
		},
		success:function(result){
			    var result = eval("(" + result + ")");
		       
		        if(result.msg){
		            alert("拒绝成功！");
			        window.location.reload();//删除后，自动刷新页面
		        }
	        }
		});
    }
}

//导出数据（请假）
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
    window.open('exportMessage?type=leave&fileName='+fileName);

    
}
</script>
  </head>
  <body>
 <%@include file="t_menu.jsp" %>    	
    	<div id="page">
   <form action="selectLeave" method="get">
    <table class="table table-bordered" width="800" height="60">
    	     <tr>
				<td align="center"><h4>按条件查询</h4></td>	
                <input type="text" value="teacher" name="action" hidden="true"/>   
			    <td align="center">学号:<input type="text" name="id" style="width: 140px">
				</td>
				<td align="center">专业:
			        <select id="major"  name="major" editable="false" panelHeight="auto" style="width: 180px;height:28px" onchange="selectGrade(this.value)">
											       
					</select>
			    </td>
			    <td align="center">班级:
			        <select id="grade"  name="grade" editable="false" panelHeight="auto" style="width: 180px;height:28px">
											       
					</select>
			    </td>	
			    <td align="center">信息状态:<select name="state" editable="false" panelHeight="auto" style="width: 140px;height:28px">
											    <option value="">请选择...</option>
												<option value="未审批">未审批</option>
												<option value="已审批">已审批</option>
												<option value="已结束">已结束</option>
												<option value="已结束">已拒绝</option>
											</select>
			    </td>	 
		        <td  align="center" style="padding-top:15px;">
		            <input  type="submit" value="查询"/>
		            <input  type="button" onclick="exportmessage('${leave==null}')" value="导出数据"/>
		        </td>
			</tr>
   	    </table>
    </form>
    
    <table class="table table-bordered">
    <c:choose> 
     <c:when test="${leave.beanList!=null}" >
			<tr>
				<td align="center" width="8%"><h4>学号</h4></td>	
				<td align="center" width="8%"><h4>姓名</h4></td>
				<td align="center" colspan="3"><h4>请假时间</h4></td>				
				<td align="center" width="19%"><h4>请假理由</h4></td>				
				<td align="center" width="8%"><h4>请假去向</h4></td>	
				<td align="center" width="11%"><h4>学生联系方式</h4></td>
				<td align="center" width="11%"><h4>家长联系方式</h4></td>
				<td align="center" width="6%"><h4>状态</h4></td>
				<td align="center" width="12%"><h4>操作</h4></td>
			</tr>
	 </c:when>
	 <c:otherwise>
				<h3 align="center">没有数据！</h3> 
	 </c:otherwise>
	</c:choose>
	<c:forEach var="leave" items="${leave.beanList}">
			<tr>
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
				<td align="center" >
				   <input type="button" value="批准" onclick="changeLeave('${leave.id}','${leave.name}','${leave.state}')"/>
				   <input type="button" value="拒绝" onclick="objectLeave('${leave.id}','${leave.name}','${leave.state}')"/>
				</td>
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
