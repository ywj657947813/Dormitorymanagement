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
  //没有库存，推迟维修
  function delayService(Id,state){
    if(state=="无库存"){
         alert("不要重复点击！");
         return;
    }
//     if(state=="维修中"){
//          alert("不要重复点击！");
//          return;
//     }
    if(state=="已完成"){
         alert("该信息已完成！");
         return;
    }
    if(confirm("你确定延迟维修吗？")){  
	    $.ajax({
		       type: "POST",
		       async:false,  // 设置同步方式
		       cache:false,
		       url: "changeService",
		       data:{"Id":Id,
		             "action":"delay"
		             },
		       success:function(result){
		       var result = eval("(" + result + ")");
		       
		           if(result.msg){
		               window.location.reload(true);//强制刷新页面，从服务器端刷新数据
		           }
		           
		          
		       }
	     });
    }
}
  
  //接受学生的报修信息，修改状态，若已经完成，会提示对应信息
  function acceptService(Id,state){
    if(state=="维修中"){
         alert("该信息已经接受过了，请尽快前往维修！");
         return;
      }
      if(state=="已完成"){
         alert("已经维修完毕，不需要再次接受！");
         return;
      }
    $.ajax({
	       type: "POST",
	       async:false,  // 设置同步方式
	       cache:false,
	       url: "changeService",
	       data:{"Id":Id,
	             "action":"accept"
	             },
	       success:function(result){
	       var result = eval("(" + result + ")");
	       
	           if(result.msg){
	               alert(result.msg);
	               window.location.reload(true);//强制刷新页面，从服务器端刷新数据
	           }
	           
	          
	       }
        });
}
  
  //完成学生的报修信息，修改状态，若已经完成，会提示对应信息
function finishService(Id,state){
      if(state=="待维修"){
         alert("还未接受该信息，请先接受！");
         return;
      }
      if(state=="已完成"){
         alert("该信息已完成了！");
         return;
      }
      if(state=="无库存"){
         alert("还未接受该信息，请先接受！");
         return;
      }
	  if(confirm("你确认完成该项信息了吗？")){
	  
	    $.ajax({
		       type: "POST",
		       async:false,  // 设置同步方式
		       cache:false,
		       url: "changeService",
		       data:{"Id":Id,
		             "action":"finish"
		             },
		       success:function(result){
		          var result = eval("(" + result + ")");
		        
		          if(result.msg=="true1"){
	                  window.location.reload(true);//强制刷新页面，从服务器端刷新数据
	              }else{
	                  alert(result.msg);
	                  window.location.reload(true);//强制刷新页面，从服务器端刷新数据
	              }
		       }
	        });
	  }
  }
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
 
</script>
  </head>
  
  <body>
    <%@include file="s_menu.jsp" %>
    
    <div id="page">
    <form name="form2" action="selectService?action=service" method="get">
    	<table class="table table-bordered" width="500" height="60">
    	     <tr>
				<td align="center"><h4>按条件查询</h4></td>	
                    
			    <td align="center"><h4>宿舍楼:<select id="dormId" name="dormId" editable="false" panelHeight="auto" style="width: 150px">
											       
											</select>
				</h4>
				<input type="text" value="service" name="action" hidden/>
				</td>
				
			    <td align="center"><h4>维修状态:<select name="state" editable="false" panelHeight="auto" style="width: 150px">
											    <option value="">请选择...</option>
												<option value="待维修">待维修</option>
												<option value="维修中">维修中</option>
												<option value="已完成">已完成</option>
												<option value="无库存">无库存</option>
											</select>
				</h4></td>	 
		        <td><input class="btn btn-primary btn-lg active" type="submit" value="查询"></input></td>
			</tr>
   	    </table>
   	</form>
   	<br/>
    <table class="table table-bordered">
     <c:choose> 
      <c:when test="${message!=null}" >
			<tr>
			    <td align="center" width="5%"><h4>学号</h4></td>
				<td align="center" width="6%"><h4>宿舍楼</h4></td>
				<td align="center" width="6%"><h4>房间号</h4></td>	
				<td align="center" width="6%"><h4>名字</h4></td>				
				<td align="center" width="10%"><h4>维修物品类型</h4></td>				
				<td align="center" width="20%"><h4>维修物品及原因</h4></td>	
				<td align="center" width="9%"><h4>联系方式</h4></td>
				<td align="center" width="8%"><h4>报修时间</h4></td>
				<td align="center" width="8%"><h4>维修状态</h4></td>
				<td align="center" width="15%"><h4>操作</h4></td>
			</tr>
	   </c:when>
	   <c:otherwise>
				<h3 align="center">没有您对应的维修数据！</h3> 
	   </c:otherwise>
	  </c:choose>
	  <c:forEach var="service" items="${message.beanList}">
			<tr>
				<td align="center">${service.stuid}</td>
				<td align="center">${service.dormName}</td>
				<td align="center">${service.roomno}</td>
				<td align="center">${service.name}</td>
				<td align="center">${service.servicetype}</td>
				<td align="center">${service.servicereason}</td>
				<td align="center">${service.phone}</td>
				<td align="center">${service.time}</td>
				<td align="center">${service.state}</td>
				<td>
				    <input type="button" value="接受" onclick="acceptService('${service.id}','${service.state}')"/>
				    &nbsp;
				    <input type="button" value="完成" onclick="finishService('${service.id}','${service.state}')"/>
				     &nbsp;
				    <input type="button" value="延迟" onclick="delayService('${service.id}','${service.state}')"/>
				    
				</td>
			</tr>
     </c:forEach>
		</table><br/>
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
