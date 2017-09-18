<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.sql.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<link rel="stylesheet" href="css/bootstrap.css">
<script type="text/javascript">
function CheckLogin(){
	    var ID = document.getElementById('ID').value;         //学生学号
	    var phone = document.getElementById('phone').value;         //学生学号
	    var DormId = document.getElementById('dormId').value; //宿舍楼
	    var HeightId = document.getElementById('HeightId').value; //楼层
	    var RoomId = document.getElementById('roomno').value; //房号
	    var BedId = document.getElementById('bedno').value;   //床号
	    var sex = document.getElementById('sex').value;       //性别
	    var depaId = document.getElementById('depa').value; //系别
	    var marjorId = document.getElementById('major').value;//专业
	    var flag = true;
	    
	    if(isNaN(ID)||ID.length!=8){		      
                   alert("您输入的学号有误，请输入长度为8的数字！");
                   return false;
        }
        if(isNaN(phone)||phone.length!=11){		      
                   alert("您输入的联系电话有误，请输入长度为11的数字号码！");
                   return false;
        } 
        if(depaId==""){		      
                   alert("请选择系别！");
                   return false;
        }
        if(marjorId==""){		      
                   alert("请选择专业！");
                   return false;
        }
        if(sex==""){		      
                   alert("请选择性别！");
                   return false;
        }
        if(DormId==""){		      
                   alert("请选择宿舍楼！");
                   return false;
        }
        if(HeightId==""){		      
                   alert("请选择楼层！");
                   return false;
        }
        if(RoomId==""){		      
                   alert("请选择房号！");
                   return false;
        }
        if(BedId==""){		      
                   alert("请选择床位！");
                   return false;
        }  
           
                
	    //查询学号是否存在
	    $.ajax({
	       type: "POST",
	       async:false,  // 设置同步方式
	       cache:false,
	       url: "checkStuId",
	       data:{"stuID":ID,
	             "action":"xuehao"
	       },
	       success:function(result){
	       var result = eval("(" + result + ")");
	       
	           if(result.msg == 'true'){
	               alert("该学号已存在，请重新输入");
	               flag = false;
	           }
	           
	          
	       }
        });
        if(flag){
	        //查询该床位是否有人使用
	        $.ajax({
		       type: "POST",
		       async:false,  // 设置同步方式
		       cache:false,
		       url: "checkStuId",
		       data:{"DormId":DormId,
		             "RoomId":RoomId,
		             "BedId":BedId,
		             "action":"chuangwei"
		       },
		       success:function(result){
		       var result = eval("(" + result + ")");
		       
		           if(result.msg == 'true'){
		               alert("该床位已有学生使用，请重新选择！");
		               flag = false;
		           }
		           
		          
		       }
	        });
        }
	               return flag;
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
function selectRoom(){
//当选择宿舍的时候，显示选择楼层div
    document.getElementById('div2').style.display='block'; 
}

function selectRoom1(HeightId){
//当选择楼层的时候，显示对应的房号

    var DormId=document.getElementById('dormId').value;  //宿舍楼号
    $.ajax({
            url: "roomSelectList",
            type: "Post",
            contentType:"application/x-www-form-urlencoded",
            dataType: "json",
            data:{"HeightId":HeightId,
                  "DormId":DormId,  
            },
            success: function (data) {
                document.getElementById("roomno").options.length = 1; //先清空房间列表的值
               var ddl = $("#roomno");
               //转成Json对象
               var result = eval(data);
               //循环遍历 下拉框绑定
               $(result).each(function (key) {
                   //第一种方法
                   var opt = $("<option></option>").text(result[key].roomno).val(result[key].roomno);
                   ddl.append(opt);
               });
               document.getElementById('div3').style.display='block'; //显示查询到的房号
           },
           error: function (data) {
               alert("Error");
           }
      });
}

function selectRoom2(){
//当选择房号的时候，显示选择床号div
    document.getElementById('div4').style.display='block'; 
}


function uploadFile(File){
      var formData = new FormData($( "#uploadForm" )[0]); 
      $.ajax({
         type: "POST",
         url:"lotInsert",
         data:formData,// 你的formid
         async: false,  
         cache: false,  
         contentType: false,  
   		 processData: false, 
         success: function(data) {
           var result = eval("(" + data + ")");
           if(result.msg=="true"){
              alert("上传成功！");
              window.location.reload();//删除后，自动刷新页面
           }
           if(result.msg1){
              alert(result.msg1);
           }
         },
         error: function(request) {
             alert("Connection error");
         }
       });
    
}
function insertStu(){
        var ID = document.getElementById('ID').value;         //学生学号
	    var phone = document.getElementById('phone').value;         //学生学号
	    var DormId = document.getElementById('dormId').value; //宿舍楼
	    var HeightId = document.getElementById('HeightId').value; //楼层
	    var RoomId = document.getElementById('roomno').value; //房号
	    var BedId = document.getElementById('bedno').value;   //床号
	    var sex = document.getElementById('sex').value;       //性别
	    var depaId = document.getElementById('depa').value; //系别
	    var marjorId = document.getElementById('major').value;//专业
	    var flag = true;
	    
	    if(isNaN(ID)||ID.length!=8){		      
                   alert("您输入的学号有误，请输入长度为8的数字！");
                   return false;
        }
        if(isNaN(phone)||phone.length!=11){		      
                   alert("您输入的联系电话有误，请输入长度为11的数字号码！");
                   return false;
        } 
        if(depaId==""){		      
                   alert("请选择系别！");
                   return false;
        }
        if(marjorId==""){		      
                   alert("请选择专业！");
                   return false;
        }
        if(sex==""){		      
                   alert("请选择性别！");
                   return false;
        }
        if(DormId==""){		      
                   alert("请选择宿舍楼！");
                   return false;
        }
        if(HeightId==""){		      
                   alert("请选择楼层！");
                   return false;
        }
        if(RoomId==""){		      
                   alert("请选择房号！");
                   return false;
        }
        if(BedId==""){		      
                   alert("请选择床位！");
                   return false;
        }  
           
                
	    //查询学号是否存在
	    $.ajax({
	       type: "POST",
	       async:false,  // 设置同步方式
	       cache:false,
	       url: "checkStuId",
	       data:{"stuID":ID,
	             "action":"xuehao"
	       },
	       success:function(result){
	       var result = eval("(" + result + ")");
	       
	           if(result.msg == 'true'){
	               alert("该学号已存在，请重新输入");
	               flag = false;
	           }
	           
	          
	       }
        });
        if(flag){
	        //查询该床位是否有人使用
	        $.ajax({
		       type: "POST",
		       async:false,  // 设置同步方式
		       cache:false,
		       url: "checkStuId",
		       data:{"DormId":DormId,
		             "RoomId":RoomId,
		             "BedId":BedId,
		             "action":"chuangwei"
		       },
		       success:function(result){
		       var result = eval("(" + result + ")");
		       
		           if(result.msg == 'true'){
		               alert("该床位已有学生使用，请重新选择！");
		               flag = false;
		           }
		       }
	        });
        }
	 if(flag){//当所有验证通过时，才会出向后台提交数据
	     $.ajax({
           url: "insert",
           type: "Post",
           data:$('#form2').serialize(),
           onSubmit:function(){
           	alert("执行onsubmit");
           	return $(this).form("validate");
           },
           success: function (data) {
              var result = eval("(" + data + ")");
              if(result.msg == 'true'){
	            alert("添加成功！");
		        window.location.reload();//删除后，自动刷新页面
	        }else if(result.msg == 'false'){
	            alert("添加失败！");
	        }
          },
          error: function (data) {
              alert("Error");
          }
	     });
	 }          
    
}
</script>

    <title>学生宿舍管理系统</title>
  
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
#table{
	width:35%;
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
  </head>
  
  <body>
    <%@include file="menu.jsp" %>
    <br>
   <div id="table">
        <form id="uploadForm"  method="post" enctype="multipart/form-data">
        	<table>
        		<tr>
        			<td>批量添加学生：</td>
        			<td><input  type="file" name="File"/></td>
        		</tr>
        		<tr>
<!--         		   <td><a href="javascript:void(0)" class="btn btn-primary btn-lg active" onclick="uploadFile()">上传</a></td> -->
                       <td><input type="button" onclick="uploadFile()" value="上传"/></td>
        		</tr>
        	</table>
        </form>
	 </div>
	 
	 
    <div id="table">
    <form id="form2" name="form2"  method="post" ><!-- onSubmit="return CheckLogin();" -->
    	<table class="table table-bordered">
    	    <tr>
    	        <td colspan="4">单个添加学生：</td>
    	    </tr>
			<tr>
				<td align="center" width="20%">学号</td>
				<td width="80%" colspan="3">
					<input type="text" required="true" size="25" style="resize:none;" id="ID" name="stuid" placeholder="必填。"/></td>
			</tr>
			<tr>
				<td align="center">名字</td>
				<td colspan="3">
					<input type="text" required="true" size="25" style="resize:none;" name="name" placeholder="必填。"/></td>
			</tr>
			<tr>
				<td align="center">电话</td>
				<td colspan="3">
					<input type="text" required="true" size="25" style="resize:none;" id="phone"name="phone" placeholder="必填。"/></td>
			</tr>
			<tr>
				<td align="center">系别</td>
				<td colspan="3">
				     <select id="depa" required="true" name="depa" editable="false" panelHeight="auto" style="width: 180px" onchange="selectMajor(this.value)">   
											       
					</select>
				</td>
			</tr>
			<tr>
				<td align="center">专业</td>
				<td>
				    <select id="major" required="true" name="major" editable="false" panelHeight="auto" style="width: 120px" onchange="selectGrade(this.value)">
											       
					</select>
				</td>
				<td align="center">班级</td>
				<td>
				    <select id="grade" required="true" name="grade" editable="false" panelHeight="auto" style="width: 120px">
											       
					</select>
				</td>
			</tr>
			<tr>
				<td align="center">性别</td>
				<td colspan="3">
				     <select id="sex" required="true" name="sex" editable="false" panelHeight="auto" style="width: 120px">
				            <option value="">请选择...</option>	
							<option value="男">男</option>	
							<option value="女">女</option>					       
					</select>
			</tr>
			<tr>
				<td align="center">选择宿舍</td>
				<td colspan="3">
				    <div id="div1" style="float:left;">
					    <select id="dormId" required="true" name="dormno" editable="false" panelHeight="auto" style="width: 60px" onchange="selectRoom()">
												       
						</select>&nbsp;楼&nbsp;
					</div>
				    <div id="div2" style="display:none;float:left;">
					    <select id="HeightId" required="true" name="HeightId" editable="false" panelHeight="auto" style="width: 50px" onchange="selectRoom1(this.value)">
					        <option value="">请选择...</option>	
							<option value="R1">R1</option>	
							<option value="R2">R2</option>
							<option value="R3">R3</option>	
							<option value="R4">R4</option>
							<option value="R5">R5</option>	
							<option value="R6">R6</option>	
            		    </select>&nbsp;层&nbsp;
					</div>	
					<div id="div3" style="display:none;float:left;">
					    <select id="roomno" required="true" name="roomno" editable="false" panelHeight="auto" style="width: 80px" onchange="selectRoom2()">
							<option value="">请选择...</option>				       
					    </select>&nbsp;号&nbsp;	
					</div>		
					<div id="div4" style="display:none;">
					    <select id="bedno" required="true" name="bedno" editable="false" panelHeight="auto" style="width: 50px">
							<option value="">请选择...</option>	
							<option value="1">1</option>	
							<option value="2">2</option>
							<option value="3">3</option>	
							<option value="4">4</option>					       
					    </select>&nbsp;号床	
					</div>		       
				</td>
            </tr>
		</table>
		<div id="down">
			<input class="btn btn-primary btn-lg active" type="button" onclick="insertStu()" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;
			<input class="btn btn-default btn-lg active" type="reset" value="重置">
		</div>
		</form>
		</div>
		
		
	 
    
  </body>
</html>