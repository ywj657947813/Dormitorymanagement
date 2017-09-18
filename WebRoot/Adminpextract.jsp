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
	width:90%;
	margin-left:auto;
	margin-right:auto;
}
#table{
	float:left;
	width:45%;
	margin-left:auto;
	margin-right:auto;
	padding-left:4%;
}
#table1{
	float:right;
	width:45%;
	margin-left:auto;
	margin-right:auto;
	padding-right:4%;
}

	</style>

<script type="text/javascript">
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
   //获取物品下拉框的值
		 $.ajax({
                      url: "productSelectList",
                      type: "Post",
                      contentType: "application/json",
                      dataType: "json",
                      success: function (data) {
                         var ddl = $("#pid");
                         //转成Json对象
                         var result = eval(data);
                         //循环遍历 下拉框绑定
                         $(result).each(function (key) {
                             //第一种方法
                             var opt = $("<option></option>").text(result[key].pname).val(result[key].pid);
                             ddl.append(opt);
                         });
                     },
                     error: function (data) {
                         alert("Error");
                     }
           });

});

function insertOrder(){
	
    $.ajax({
	       type: "POST",
	       async:false,  // 设置同步方式
	       cache:false,
	       url: "orderInsert",
	       data:$('#form2').serialize(),
	       success:function(result){
	           var result = eval("(" + result + ")");
	           if(result.msg=="true"){
	               alert("提交成功！");
	               window.location.href="Adminpextract.jsp";
	           }
	           
	          
	       }
        });
}

 function CheckLogin(){
 	 var suid = document.getElementById('suid').value;     //维修员工号
	 var count = document.getElementById('count').value;    //物品数量
	 
	 
	 if(isNaN(suid)){		      
        alert("工号只能为数字！");
        return false;
     }else{
		var flag=true;
     	//如果符合输入条件，查询工号是否存在
	    $.ajax({
	       type: "POST",
	       async:false,  // 设置同步方式
	       cache:false,
	       url: "checkStuId",
	       data:{"suid":suid,
	             "action":"serviceId"
	       },
	       success:function(result){
	       var result = eval("(" + result + ")");
	           if(result.msg == 'false'){
	               alert("请确认申请人工号是否正确！");
	               flag = false;
	           }
	       }
        });
        return flag;
     }
     if(isNaN(count)){		      
        alert("数量只能为数字！");
        return false;
     }
     
 }
</script>
  </head>
  <body>
  
  <%@ include file="menu.jsp" %>
 
   
    	<h1 align="center">物品提取登记</h1>
    <hr size="1" style="width:55%;" align="center">
   <div id="table">
    <form action="cartInsert" method="post" onsubmit="return CheckLogin();">
    	<table class="table table-bordered">
			<tr>
				<td align="center" width="50">物品</td>
				<td>
					<select id="pid" name="pid" editable="false" required panelHeight="auto" style="width: 120px;height:28px">
												       
					</select>
				</td>
				<td align="center">数量</td>
				<td>
						<input type="text" required  style="resize:none;width: 120px;"id="count" name="count" placeholder="必填。"></input>
				</td>
			</tr>
			<tr>
				<td align="center">申请人</td>
				<td>
					<input type="text" required  style="width: 120px;"id="suid" name="suid" value="${suid}" placeholder="必填，请填工号。"></input>
				</td>
				<td align="center">用于宿舍</td>
				<td>
					<select id="dormId"  required name="dormId" editable="false" panelHeight="auto" style="width: 120px;height:28px">
												       
					</select>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="4">
				    <input type="text" name="action" value="addCart" hidden/>
				    <input type="text" name="people" value="admin" hidden/>
					<input type="submit" value="提交"/>
				</td>
			</tr>
			
		</table>
		</form>
 </div>	
  <div id="table1">
		 
		<form id="form2" method="post">
    	<table class="table table-bordered">
    		<c:choose> 
             <c:when test="${cart.cartItems.size() >0}" >   
	    		<tr>
					<td align="center" colspan="4">已选物品</td>
	    		</tr>
				<tr>
					<td align="center">物品</td>
					<td align="center">数量</td>
					<td align="center">用于宿舍</td>
					<td align="center">操作</td>
				</tr>
				<c:forEach var="message" items="${cart.cartItems}">
					<tr>
						<td align="center">${message.product.pname}</td>
						<td align="center">${message.count}</td>
						<td align="center">${message.dname}</td>
						<td align="center"><a href="cartInsert?people=admin&action=removeCart&deleteid=${message.dormid}${message.product.pid}">删除</a></td>
					</tr>
				</c:forEach>
				<tr>
					<td align="center" colspan="4">
						<input type="text" name="action" value="saveOrder" hidden/>
						<input type="text" name="people" value="admin" hidden/>
						<input class="btn btn-primary btn-lg active" type="button" onclick="insertOrder()" value="提交">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<c:if test="${cart.cartItems.size() >0}">
							<a href="cartInsert?people=admin&action=clearCart"class="btn btn-default btn-lg active" onClick="return confirm('确定清空吗?');">清空清单</a>
						 </c:if>
						 <c:if test="${cart.cartItems.size() ==0}">
							<a class="btn btn-default btn-lg active">清空清单</a>
						 </c:if>
					</td>
					
				</tr>
			 </c:when>
			 
			 </c:choose>
			
		</table>
		</form>

   </div>
   </body>
   </html>
