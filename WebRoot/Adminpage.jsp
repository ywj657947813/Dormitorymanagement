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
  <%
	// 权限验证
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("login.jsp");
		return;
	}
%>




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
function deleStu(){
	var optIDs =[];  
	$("input:checkbox[name='stuIds']:checked").each(function(){ 
	    optIDs.push(this.value);
	}); //遍历，用逗号串联
	if(optIDs==""){
		alert("请选择一条要删除的记录！");
		return false;   
	}else{
	    var ids=optIDs.join(",");
	    if(confirm("你确定要删除该"+optIDs.length+"条记录吗？")){
			$.ajax({
			url :"deleteStu",
			type:"POST", //传递方式
			data:{"stuIds":ids},
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
	
$(document).ready(function(){
   //获取宿舍楼下拉框的值
		 $.ajax({
                      url: "dormSelectList",
                      type: "Post",
                      contentType: "application/json",
                      dataType: "json",
                      success: function (data) {
                         var ddl = $("#dormId");
                         //转成Json对象
                         var result = eval(data);
                         //循环遍历 下拉框绑定
                         $(result).each(function (key) {
                             //第一种方法
                             var opt = $("<option></option>").text(result[key].dormna).val(result[key].id);
                             ddl.append(opt);
                         });
                     },
                     error: function (data) {
                         alert("Error");
                     }
           });

      //获取系别下拉框的值
   $.ajax({
           url: "depaSelectList",
           type: "Post",
           contentType: "application/json",
           dataType: "json",
           success: function (data) {
              var ddl = $("#depa");
              //转成Json对象
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

   
         
function selectMajor(depaId){
//当选择系别的时候，显示对应的专业
      $.ajax({
            url: "majorSelectList",
            type: "Post",
            contentType:"application/x-www-form-urlencoded",
            dataType: "json",
            data:{"depaId":depaId},
            success: function (data) {
               document.getElementById("major").options.length = 0; //先清空专业列表的值
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
}
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
</script>
  </head>
  <body>
  
  <%@ include file="menu.jsp" %>
 
    	
    	<div id="page">
    	<form name="form2" action="selectDorm" method="get">
    	<table class="table table-bordered" width="500" height="60">
			<tr>
				<td align="center"><h4>按条件查询</h4></td>	
                <td align="center">学号:<input type="text" size="10" name="selectid" /></td>	 
			    <td align="center">名字:<input type="text" size="10" name="selectname"  /></td>
			    <td align="center">系别:
			        <select id="depa"  name="depa"  panelHeight="auto" style="width: 180px;height:28px" onchange="selectMajor(this.value)">   
											       
					</select>
			    </td>	 
			    <td align="center">专业:
			        <select id="major"  name="major" editable="false" panelHeight="auto" style="width: 130px;height:28px" onchange="selectGrade(this.value)">
											       
					</select>
			    </td>	
			    <td align="center">班级:
			        <select id="grade"  name="grade" editable="false" panelHeight="auto" style="width: 130px;height:28px">
											       
					</select>
			    </td> 	 
			    <td align="center">宿舍楼:
			            <select id="dormId"  name="dormid" editable="false" panelHeight="auto" style="width: 120px;height:28px">
												       
						</select>
			    </td>
		        <td width="200px">
		            <input class="btn btn-primary btn-lg active" type="submit" value="查询"/>
		            <input type="text" value="admin" name="action" hidden="true"/>
		            &nbsp;&nbsp;
		            <input class="btn btn-primary btn-lg active" type="button" value="删除" onclick="deleStu()"/>
		        </td>
			</tr>
		</table>	
		</form>	
			<table class="table table-bordered">
			<c:choose> 
             <c:when test="${message!=null}" >     
			<tr>
			    <td align="center"><input type="checkbox" id="all" onClick="if(this.checked==true) { checkAll('stuIds'); } else { clearAll('stuIds'); }" />全选</td>
				<td align="center"><h4>学号</h4></td>	
				<td align="center"><h4>名字</h4></td>	
				<td align="center"><h4>系别</h4></td>	
				<td align="center"><h4>专业</h4></td>	
				<td align="center"><h4>班级</h4></td>					
				<td align="center" ><h4>宿舍楼</h4></td>				
				<td align="center" ><h4>房号</h4></td>
				<td align="center" ><h4>床号</h4></td>	
			</tr>
			 </c:when>
			 
			</c:choose>
			
			<c:forEach var="message" items="${message.beanList}">
			<tr>
			    <td align="center"><input type="checkbox" name="stuIds" value="${message.id}"/></td>
				<td align="center">${message.id}</td>
				<td align="center">${message.name}</td>
				<td align="center">${message.depaName}</td>
				<td align="center">${message.majorName}</td>
				<td align="center">${message.gradeName}</td>
				<td align="center">${message.dormName}</td>
				<td align="center">${message.roomno}</td>
				<td align="center">${message.bedno}</td>
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
<!--        <a href="<c:url value='/selectDorm?pc=${message.tp}' />">尾页</a> -->
			<a href="<c:url value='${message.url}&pc=${message.tp}' />">尾页</a>
        &nbsp;&nbsp;
                    共${message.tr}条记录
    </center>
   </c:if>
    </div>
   </body>
   </html>
