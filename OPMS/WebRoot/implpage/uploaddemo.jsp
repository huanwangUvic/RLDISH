<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String id = request.getParameter("taskid");
request.getSession().setAttribute("uploadtaskid",id);
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<script type="text/javascript">
var xmlHttp;

function createXMLHttp(){
if(window.ActiveXObject){
   xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
}else{
   xmlHttp = new XMLHttpRequest();
}
}
    
function ajaxSend(){
createXMLHttp();
var content = "status" ;
var url ="<%=basePath%>servlet/MyUpload?status="+content ;
xmlHttp.onreadystatechange = handler ;
xmlHttp.open("POST",url,true) ;
xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
xmlHttp.send(url) ;
}
function handler(){
if(xmlHttp.readyState == 4){
   if(xmlHttp.status == 200){
    document.getElementById("status").style.display="block";
    var result=xmlHttp.responseText.split("-");
    var percent = result[0] ; 
    document.getElementById("result").innerHTML=result[1]; 
            var com = document.getElementById("com") ;
            var last = document.getElementById("last") ;
            var p = document.getElementById("percent") ;
            com.setAttribute("width",percent+"%") ;
            last.setAttribute("width",(100-percent)+"%") ;
            if(percent < 99){
                p.innerHTML="<font color='green' face='新宋体' size='2'>已经完成：<b >"+percent+"%</b><font>" ;
                setTimeout("ajaxSend()",200) ;
            }
   }
}
return true;
}
   
 
function mySubmit(form){
form.action="<%=basePath%>servlet/MyUpload" ;
form.submit();
document.getElementById("1").disabled=true;
document.getElementById("2").disabled=true;
document.getElementById("3").disabled=true; 

document.getElementById("submitid").value="上传中...";
document.getElementById("submitid").disabled=true;
ajaxSend();
}
</script>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.Button1
{padding: 2 4 0 4;font-size:12px;height:23;background-color:#ece9d8;border-width:1;}
.STYLE6 {color: #000000; font-size: 14px; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	width: 100px;
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
.inptext1 {
	height: 85px;
	width: 780px;
}

.inptext2 {
	height: 24px;
	width: 130px;
}

.tb1 {
	text-align: center;
}
.pag{
	font-size: 12px;
	line-height: 25px;
	color: #333333;
	text-decoration: none;
}
-->
</style>
</head>

<body>
<div align="center">
<br/>
<h2 align="center"><font size="4" face="宋体" color="#ff8000"><font size="5">上传文件</font></font></h2>
<br />   
<br>
<form enctype="multipart/form-data" name="fileform" method="post">

<font color="#000000" size="3" face="华文中宋"><strong>文件1：</strong></font>
<input type="file" class="Button1" id="1" name="1" /><br /><br />

<font color="#000000"><strong><font face="华文中宋">文件2：</font></strong></font>
<input type="file" class="Button1" id="2" name="2" /><br /><br />

<font color="#000000" face="华文中宋"><strong>文件3：</strong></font>
<input type="file" class="Button1" id="3" name="3" /><br /><br />

<input name="submitid" type="button" class="Button1" value="上传" onclick="mySubmit(fileform)" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<font color="#008000" face="新宋体" size="2"><input type="button" class="Button1" value="返回" onclick="javascript:history.go(-1)" /></font>&nbsp;&nbsp;&nbsp;
<br />

</form>
</div>
<div align="center" id="status" style="display:none;">

<table width="40%" height="20">
   <tr> 
    <td width="0" bgcolor="#66FF00" height="20px" id="com">
    </td>
    <td width="100%" bgcolor="#cddddd" id="last">   
    </td>
   </tr>
</table>

<table width="40%" height="20">
   <tr>
   <td width="100%" id="percent" align="center"></td>
   </tr>
</table> 
<h3 style="font-weight: normal;"><font face="楷体_GB2312" color="#ff0000" size="2"><span id="result"></span></font></h3>
</div>
</body>
</html>
