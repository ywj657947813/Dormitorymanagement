<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    <link rel="stylesheet" type="text/css" href="css/lrtk.css">
<!-- dd menu -->
<script type="text/javascript">
<!--
var timeout         = 500;
var closetimer		= 0;
var ddmenuitem      = 0;

// open hidden layer
function mopen(id)
{	
	// cancel close timer
	mcancelclosetime();

	// close old layer
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';

	// get new layer and show it
	ddmenuitem = document.getElementById(id);
	ddmenuitem.style.visibility = 'visible';

}
// close showed layer
function mclose()
{
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';
}

// go close timer
function mclosetime()
{
	closetimer = window.setTimeout(mclose, timeout);
}

// cancel close timer
function mcancelclosetime()
{
	if(closetimer)
	{
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}

// close layer when click-out
document.onclick = mclose; 
// -->
</script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
   <ul id="sddm">
	<li><a href="#" onmouseover="mopen('m1')" onmouseout="mclosetime()">选择宿舍楼</a>
		<div id="m1" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
		<a href="#">HTML DropDown</a>
		<a href="#">DHTML DropDown menu</a>
		<a href="#">JavaScript DropDown</a>
		<a href="#">DropDown Menu</a>
		<a href="#">CSS DropDown</a>
		</div>
	</li>
	<li><a href="#" onmouseover="mopen('m2')" onmouseout="mclosetime()">Download</a>
		<div id="m2" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
		<a href="#">ASP Dropdown</a>
		<a href="#">Pulldown menu</a>
		<a href="#">AJAX dropdown</a>
		<a href="#">DIV dropdown</a>
		</div>
	</li>
	<li><a href="#" onmouseover="mopen('m3')" onmouseout="mclosetime()">Order</a>
		<div id="m3" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
		<a href="#">Visa Credit Card</a>
		<a href="#">Paypal</a>
		</div>
	</li>
	<li><a href="#" onmouseover="mopen('m4')" onmouseout="mclosetime()">Help</a>
		<div id="m4" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
		<a href="#">Download Help File</a>
		<a href="#">Read online</a>
		</div>
	</li>
	<li><a href="#" onmouseover="mopen('m5')" onmouseout="mclosetime()">Contact</a>
		<div id="m5" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
		<a href="#">E-mail</a>
		<a href="#">Submit Request Form</a>
		<a href="http://www.lanrentuku.com/" target="_blank">lanrentuku.com</a>
		</div>
	</li>
</ul>
  </body>
</html>
