<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <div id="top"><img src="images/2.jpg" /></div>
    <br>
    <div id="lay">
    	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      		<ul class="nav navbar-nav">
       		 <li><a href="selectService?action=service"><b><font color="#CCCCFF">宿舍维修管理</font></b></a></li>
       		 <li><a href="Servicepextract.jsp"><b><font color="#CCCCFF">物品申领</font></b></a></li>
       		 <li><a href="<c:url value='/selectProductOrder?action=service&suid=${suid}' />"><b><font color="#CCCCFF">物品申领信息</font></b></a></li>
      		</ul> 
   			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      			<ul class="nav navbar-nav navbar-right">
        		<li><a href="safeExit"><b><font color="#CCCCFF">退出登陆</font></b></a></li>
      			</ul>
    		</div>
    	</div>
    </div>