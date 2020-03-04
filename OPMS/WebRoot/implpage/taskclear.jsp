<%@ page language="java" import="java.util.*,com.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String proname = (String)request.getAttribute("proname");
Task task = (Task)request.getAttribute("task");
Department dep = (Department)request.getSession().getAttribute("userinfo");
int role = dep.getDeprole();
int plancon = (Integer)request.getAttribute("plancon");
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

function document.onkeydown() //禁用backspace键
	{
	 if ( window.event.keyCode==27)
	     {
	      window.event.keyCode=8;
	     }
	     if ( event.keyCode==8)//后退键 
	         {
	          event.keyCode = 0;       
	          event.cancelBubble = true;  
	          return false; 
	         }        
	}

function deletetask()
{
  if(confirm("确认删除该任务？"))
  window.location="<%=basePath%>servlet/DeletetaskServlet?taskid=<%=task.getTaskid()%>";
  
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
        <td height="31"><div class="titlebt">任务详细</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="images/mail_rightbg.gif"><img src="images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
    <td valign="middle" background="images/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9">

 
   <table align="center" width="100%" >

<tr height="11%" bgcolor="d3eaef" class="STYLE19" class="tb1" align="center">
    <td width="20%">任务名称：</td>
    <td width="30%"><%=task.getTaskname() %></td>
    <td width="20%">所属项目：</td>
    <td width="30%"><%=proname %></td>
  </tr>
  <tr height="11%" bgcolor="#FFFFFF" class="STYLE19" class="tb1" align="center">
    <td>计划开始时间：</td>
    <td><%=task.getPlanTime() %></td>
    <td>计划结束时间：</td>
    <td><%=task.getEndTime() %></td>
  </tr>
 
  <tr height="11%" bgcolor="#FFFFFF" class="STYLE19" class="tb1" align="center">
    <td>实际开始时间：</td>
    <td><% if(task.getStartTime()==null) {%>未开始<%} else {%> <%=task.getStartTime() %><%} %></td>
    <td>任务完成需提交资料</td>
    <td><%=task.getSubFile() %></td>
 </tr>
 <tr height="11%" bgcolor="#FFFFFF" class="STYLE19" class="tb1" align="center">
    <td>资料文档是否已提交：</td>
    <td><%if(task.getFileCon()==0) {%>否<%} else { %>是&nbsp;&nbsp;&nbsp;
    
    <a href="<%=basePath %>implpage/download.jsp?taskid=<%=task.getTaskid() %>">点此下载</a>
    
    <%} %></td>
    <td>任务当前状态：</td>
    <td><% if(task.getTaskCondition()==0) { %>未启动<%} %>
    <% if(task.getTaskCondition()==1) { %>进行中<%} %>
    <% if(task.getTaskCondition()==2) { %>已完成<%} %>
    </td>
 </tr>
 <tr height="34%" bgcolor="#FFFFFF" class="STYLE19" class="tb1" align="center">
  <td> 任务说明：</td>
    <td colspan="3" align="center"><textarea name="content" readonly="readonly" id="content" class="inptext1"><%=task.getTaskexplain() %></textarea></td>
 </tr>

</table>
<%if(((role!=3)&&plancon!=3)||(role==4)) {%>
<div align="center"><input type="button" value="删除该阶段任务" class="Button1" onclick="deletetask()"/></div><%} %>
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
