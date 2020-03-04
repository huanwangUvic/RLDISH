<%@ page language="java" import="java.util.*,com.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%int proid = (Integer)request.getAttribute("id");
int i = (Integer)request.getAttribute("i");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'proclear.jsp' starting page</title>
    
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
	font-size: 14px;
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
<script type="text/javascript">
function nexttask()
{
if(confirm("确认提交此阶段任务？")){
	
	var taskname = document.getElementById("taskname").value;
	
	var plantime = document.getElementById("plantime").value;
	var endtime = document.getElementById("endtime").value;
	var file = document.getElementById("file").value;
	var content = document.getElementById("content").value;
	
	if(taskname.length == 0){
	   alert('任务名称不能为空！');
	   return false;
	   }
	   
	if(plantime.length == 0){
	   alert('开始时间不能为空');
	   return false;
	   }
	if(endtime.length == 0){
	   alert('结束时间不能为空！');
	   return false;
	   }
	if(plantime>endtime)
	   {
	   alert('结束时间早于开始时间！');
	   return false;
	   }
    
	if(file.length ==0)
	   {
	      alert('请填写需提交文件！');
	      return false;
	   }
	   
	}else{
			return false;
		}
	
	return true;
}

function compl()
{
if(confirm("确认提交此阶段任务？")){
	
	var taskname = document.getElementById("taskname").value;
	
	var plantime = document.getElementById("plantime").value;
	var endtime = document.getElementById("endtime").value;
	var file = document.getElementById("file").value;
	var content = document.getElementById("content").value;
	document.getElementById("hid").value=2;
	if(taskname.length == 0){
	   alert('任务名称不能为空！');
	   return false;
	   }
	   
	if(plantime.length == 0){
	   alert('开始时间不能为空');
	   return false;
	   }
	if(endtime.length == 0){
	   alert('结束时间不能为空！');
	   return false;
	   }
	if(plantime>endtime)
	   {
	   alert('结束时间早于开始时间！');
	   return false;
	   }
    
	if(file.length ==0)
	   {
	      alert('请填写需提交文件！');
	      return false;
	   }
	   
	}else{
			return false;
		}
	
	return true;
}

</script>
  <link rel="stylesheet" href="../css/skin.css" type="text/css"></link></head>
  <script language="javascript" type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
  
  <body onload="WdatePicker()">
  
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="images/mail_leftbg.gif"><img src="images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">制定计划</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="images/mail_rightbg.gif"><img src="images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
    <td valign="middle" background="images/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9">
    <form  id="form1" method="post" action="<%=basePath%>servlet/AddtaskServlet?id=<%=proid %>&i=<%=i %>">
 
   <table align="center" width="100%">
 <tr height="20%" class="STYLE19" class="tb1" align="center"  bgcolor="d3eaef">请填写该阶段任务安排：<input type="hidden" id="hid" name="hid" value="1"/></tr>

<tr height="20%" bgcolor="d3eaef" class="STYLE19" class="tb1" >
    <td width="20%">任务名称：</td>
    <td width="30%"><input type="text" id="taskname" name="taskname" /></td>
    <td width="20%">计划开始时间：</td>
    <td width="30%"><input type="text" id="plantime" name="plantime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
  </tr>
  <tr  height="20%" bgcolor="#FFFFFF" class="STYLE19" class="tb1">
    <td>计划结束时间：</td>
    <td><input type="text" id="endtime" name="endtime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
    <td>任务结束需提交资料或文档：</td>
    <td><input type="text" id="file" name="file" /></td>
  </tr>
 
  <tr height="60%" bgcolor="#FFFFFF" class="STYLE19" class="tb1" >
    <td>任务说明：</td>
    <td colspan="3" ><textarea name="content" id="content" class="inptext1"></textarea></td>
 </tr>

</table>

<div align="center"><input type="submit" value="继续制定下一阶段任务" class="Button1" onclick="return nexttask()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="submit" value="完成该项目里程碑计划" class="Button1" onclick="return compl()"/></div>
 </td> 
</form>
    <td background="images/mail_rightbg.gif">&nbsp;</td>
  </tr>
  <tr>
    <td valign="bottom" background="images/mail_leftbg.gif"><img src="images/buttom_left2.gif" width="17" height="17" /></td>
    <td background="images/buttom_bgs.gif"><img src="images/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="images/mail_rightbg.gif"><img src="images/buttom_right2.gif" width="16" height="17" /></td>
  </tr>
</table>   
  </body>
</html>
