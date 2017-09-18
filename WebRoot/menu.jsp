<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <div id="top"><img src="images/2.jpg" /></div>
    <br>
    <div id="lay">
    	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      		<ul class="nav navbar-nav">
      		 <li><a href="<c:url value='/selectDorm?action=admin' />"><b><font color="#CCCCFF">学生信息</font></b></a></li>
      		 <li><a href="insert.jsp"><b><font color="#CCCCFF">添加学生信息</font></b></a></li>
       		 <li><a href="<c:url value='/selectService?action=admin' />"><b><font color="#CCCCFF">宿舍维修管理</font></b></a></li>
      		 <li><a href="<c:url value='/selectLeave?action=admin' />"><b><font color="#CCCCFF">请假情况信息管理</font></b></a></li>
        	 <li><a href="Adminwrong.jsp"><b><font color="#CCCCFF">违规学生信息管理</font></b></a></li>   
        	 <li><a href="<c:url value='/selectProduct?action=admin' />"><b><font color="#CCCCFF">物品信息管理</font></b></a></li>
        	 <li><a href="<c:url value='/selectProductOrder?action=admin' />"><b><font color="#CCCCFF">物品清单信息管理</font></b></a></li>                
      		</ul> 
   			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      			<ul class="nav navbar-nav navbar-right">
        		<li><a href="Adminpass.jsp"><b><font color="#CCCCFF">修改密码</font></b></a></li>
        		<li><a href="safeExit"><b><font color="#CCCCFF">退出登陆</font></b></a></li>
      			</ul>
    		</div>
    	</div>
    </div>