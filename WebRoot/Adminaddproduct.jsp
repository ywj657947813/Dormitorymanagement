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
	float:center;
	width:45%;
	margin-left:auto;
	margin-right:auto;
	padding-left:4%;
}
	</style>

<script type="text/javascript">
$(document).ready(function(){
	
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

function insertProduct(){
	 var count = document.getElementById('count').value;    //物品数量
	 var price = document.getElementById('pprice').value;    //价钱
     if(isNaN(count)){		      
        alert("数量只能为数字！");
        return;
     }
     if(isNaN(price)){		      
        alert("价格只能为数字！");
        return;
     }
	
    $.ajax({
	       type: "POST",
	       async:false,  // 设置同步方式
	       cache:false,
	       url: "productInsert",
	       data:$('#aa').serialize(),
	       success:function(result){
	           var result = eval("(" + result + ")");
	           if(result.msg=="true"){
	               alert("提交成功！");
	               window.location.href="Adminaddproduct.jsp";
	           }
	           
	          
	       }
        });
}

function selectPrice(pId){
//当选择物品的时候，显示对应价格
$.ajax({
      url: "selectPrice",
      type: "Post",
      contentType:"application/x-www-form-urlencoded",
      dataType: "json",
      data:{"pId":pId},
      success: function (data) {
         //转成Json对象
         var result = eval(data);
         //循环遍历 下拉框绑定
         $(result).each(function (key) {
             //第一种方法
             document.getElementById("pprice").value=result[key].pprice;
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
    	<h1 align="center">物品信息录入</h1>
    <hr size="1" style="width:55%;" align="center">
   <div id="table">
    <form id="aa" action="productInsert" method="post">
    	<table class="table table-bordered">
			<tr>
				<td align="center" width="50">物品</td>
				<td>
					<select id="pid" name="pid" editable="false" required panelHeight="auto" style="width: 120px;height:28px" onchange="selectPrice(this.value)">
												       
					</select>
				</td>
				<td align="center">价格</td>
				<td>
						<input type="text" required  style="resize:none;width: 50px;"id="pprice" name="pprice" placeholder="必填。"/>元
			</tr>
			<tr>
				<td align="center">数量</td>
				<td colspan="3">
					<input type="text" required  style="resize:none;width: 120px;"id="count" name="count" placeholder="必填。"></input>
				</td>
				
			</tr>
			<tr>
				<td align="center" colspan="4">
					<input type="button" onclick="insertProduct()" value="提交"/>
				</td>
			</tr>
			
		</table>
		</form>
 </div>	
   </body>
   </html>
