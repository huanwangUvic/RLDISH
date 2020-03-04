<%@ page language="java" import="java.util.*,com.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String proname = (String)request.getAttribute("proname");
Task task = (Task)request.getAttribute("task");
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
	<script type="text/javascript">
	function updatetask()
	{
	if(confirm("确认修改此阶段任务？")){
	
	var taskname = document.getElementById("taskname").value;
	var plantime = document.getElementById("plantime").value;
	var endtime = document.getElementById("endtime").value;
	var file = document.getElementById("subfile").value;
	//var con = document.getElementById("taskcon").value;
	var content = document.getElementById("content").value;
	
	//document.getElementById("hid").value=2;
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
	   return true;
	   }
	else{
			return false;
		}
	
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

  <link rel="stylesheet" href="../css/skin.css" type="text/css"></link></head>
  <script language="javascript" type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
  
  <body onload="WdatePicker()">
  
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="images/mail_leftbg.gif"><img src="images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt"> 修改任务信息  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     
         所属项目：<%=proname %></div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="images/mail_rightbg.gif"><img src="images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
    <td valign="middle" background="images/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9">

 
   <table align="center" width="100%">
<form method="post" action="<%=basePath %>servlet/UpdatetaskServlet?id=<%=task.getTaskid() %>">
<tr height="10%" bgcolor="d3eaef" class="STYLE19" class="tb1">
    <td width="20%">任务名称：</td>
    <td width="30%"><input type="text" value="<%=task.getTaskname() %>" name="taskname" id="taskname"/></td>
    <td>计划开始时间：</td>
    <td><input type="text" value="<%=task.getPlanTime() %>" id="plantime" name="plantime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>  
  </tr>
  <tr height="10%" bgcolor="#FFFFFF" class="STYLE19" class="tb1">
    <td>计划结束时间：</td>
    <td><input type="text" value="<%=task.getEndTime() %>" id="endtime" name="endtime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
    <td>实际开始时间：</td>
    <td><% if(task.getStartTime()==null) {%><input type="text" value="尚未填写" id="starttime" name="starttime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
    <%} else {%><input type="text" value="<%=task.getStartTime() %>" id="starttime" name="starttime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});"/><%} %></td> 
  </tr>
 
  <tr height="10%" bgcolor="#FFFFFF" class="STYLE19" class="tb1">
    <td>任务完成需提交资料</td>
    <td><input type="text" value="<%=task.getSubFile() %>" id="subfile" name="subfile"></td>
    <td>资料文档是否已提交：</td>
    <td><%if(task.getFileCon()==0) {%>否<%} else { %>是 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<a href="<%=basePath %>implpage/download.jsp?taskid=<%=task.getTaskid() %>">点此下载</a><%} %></td>
 </tr>
 <tr height="10%" bgcolor="#FFFFFF" class="STYLE19" class="tb1">
    
    <td>任务当前状态：</td>
    <td>
    <%if(task.getTaskCondition()==0) {%>
    <input type="radio" name="taskcon" value=0 checked="checked" >未启动
    <input  type="radio" name="taskcon" value=1>实施中
    <input type="radio" name="taskcon" value=2>已完成
    <%} %>
    <%if(task.getTaskCondition()==1) {%>
    <input type="radio" name="taskcon" value=0  >未启动
    <input  type="radio" name="taskcon" value=1 checked="checked">实施中
    <input type="radio" name="taskcon" value=2>已完成
    <%} %>
    
   <%if(task.getTaskCondition()==2) {%>
    <input type="radio" name="taskcon" value=0 >未启动
    <input  type="radio" name="taskcon" value=1 >实施中
    <input type="radio" name="taskcon" value=2 checked="checked" >已完成
    <%} %>
    </td>
    <td>提交该任务资料</td>
    <td>&nbsp;
    <a href="<%=basePath %>implpage/uploaddemo.jsp?taskid=<%=task.getTaskid() %>">点此提交</a>
    </td>
 </tr>
 <tr height="34%" bgcolor="#FFFFFF" class="STYLE19" class="tb1">
  <td> 任务说明：</td>
    <td colspan="3" align="center"><textarea name="content" id="content" class="inptext1"><%=task.getTaskexplain() %></textarea></td>
 </tr>

</table>

<div align="center">
<input type="submit" value="修改该阶段任务" class="Button1" onclick="return updatetask()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<input type="button" class="Button1" value=" 返回 " onclick="javascript:history.go(-1)" />
</div>
</form>
 </td> 

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
