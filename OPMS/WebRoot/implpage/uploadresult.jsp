<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'uploadresult.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
  <br/><br/><br/>
    <strong>文件上传成功！</strong>
    <br/><br/>
    <input type="button" class="Button1" value="返回" onclick="javascript:history.go(-2)" />
    <br>
    </div>
  </body>
</html>
