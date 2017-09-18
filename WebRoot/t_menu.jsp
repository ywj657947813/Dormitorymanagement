<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <div id="top"><img src="images/2.jpg" /></div>
    <br>
    <div id="lay">
    	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      		<ul class="nav navbar-nav">
      		 <li><a href="<c:url value='/selectDorm?action=teacher'/>"><b><font color="#CCCCFF">学生信息</font></b></a></li>
      		 <li><a href="<c:url value='/selectLeave?action=teacher'/>"><b><font color="#CCCCFF">请假情况信息管理</font></b></a></li>
      		 <li><a href="<c:url value='/selectWrong?action=teacher'/>"><b><font color="#CCCCFF">违纪信息查询</font></b></a></li>
      		</ul> 
   			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      			<ul class="nav navbar-nav navbar-right">
        		<li><a href="Teacherpass.jsp"><b><font color="#CCCCFF">修改密码</font></b></a></li>
        		<li><a href="safeExit"><b><font color="#CCCCFF">退出登陆</font></b></a></li>
      			</ul>
    		</div>
    	</div>
    </div>