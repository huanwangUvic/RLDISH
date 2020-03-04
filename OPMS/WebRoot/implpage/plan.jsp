<%@ page language="java" import="java.util.*,com.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
int flag = (Integer)request.getAttribute("flag");
ArrayList<Task> list=null;
String state=null;
if(flag!=0){
 list = (ArrayList<Task>)request.getAttribute("list");
 state = (String)request.getAttribute("state");
 
}
Department dep = (Department)request.getSession().getAttribute("userinfo");
int role = dep.getDeprole();
int proid = (Integer)request.getAttribute("proid");
int counter=1;
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'respro.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function addtask()
	{ 
	if(confirm("确认添加任务？"))
	 window.location="<%=basePath %>servlet/AddtaskServlet?id=<%=proid %>&i=0"
	}
	

	function openwindow()
	{ 
	 var temp= window.showModalDialog("<%=basePath%>implpage/NewBox.jsp?id=<%=proid%>",window,"status:no;scroll:no;dialogWidth:350px;dialogHeight:200px");
	   
	 }
	 function agree()
	 {
	   if(confirm("确认同意该项目里程碑计划？"))
	   window.location="<%=basePath%>servlet/UpdateplanServlet?id=<%=proid%>";
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
.Button1
{padding: 2 4 0 4;font-size:12px;height:23;background-color:#ece9d8;border-width:1;}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 14px; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 17px;
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
	height: 24px;
	width: 240px;
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
<script>

</script>

 <link rel="stylesheet" href="../css/skin.css" type="text/css"></link></head>
  
  <body>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="images/mail_leftbg.gif"><img src="images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
		<td height="31"><div class="titlebt"><%=proid %>项目</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="images/mail_rightbg.gif"><img src="images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
    <td valign="middle" background="images/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9">
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="141" valign="top">
    
    
    
   <table align="center" width="100%">
    
    <tr>
       <td colspan="7" bgcolor="d3eaef" class="STYLE6" align="center">	
       								
			<br/><br/>
      </td>
   </tr>
  
  
    		<tr bgcolor="d3eaef" class="STYLE6">
    		    <th width="16.67%">阶段</th>
	    		<th width="16.66%">任务名称</th>
	    		<th width="16.67%">计划开始时间</th>
	    		<th width="16.66%">计划结束时间</th>
	    		<th width="16.66%">任务完成需提交资料</th>
	    		<th width="16.67%">操作</th>
    		</tr>
    		<% 
    			if(list!=null && list.size()>0){
    				for(Task task:list){%>
    		 <tr bgcolor="#FFFFFF" class="STYLE19" class="tb1" align="center">
    		     <td width="16.67%">阶段<%=counter++ %></td>
    		 	<td width="16.66%"><%=task.getTaskname() %></td>
    			 <td width="16.67%"><%=task.getPlanTime() %></td>
    			  <td width="16.66%"><%=task.getEndTime() %></td>
    			  <td width="16.66%"><%=task.getSubFile() %></td>
    			  
    			  <td width="16.67%">
    			  <a href="<%=basePath%>servlet/TaskclearServlet?taskid=<%=task.getTaskid() %>&flag=1">详细</a>
    			  <%if(role!=3){ %>
    			  <a href="<%=basePath%>servlet/TaskclearServlet?taskid=<%=task.getTaskid() %>&flag=2">修改</a>
    			  <%} %>
    			  </td>
    		 </tr>
    		<%			}}  else {%>
    		<tr>
    		<td colspan="5">&nbsp;</td>
    		</tr>
    		<tr>
    	    <td colspan="6" align="center" style="color:#F00">尚未制定该项目里程碑计划！&nbsp;&nbsp;&nbsp;&nbsp;
    	    <%if(role==1) {%>
    	    <a href="<%=basePath %>servlet/AddtaskServlet?id=<%=proid %>&i=0">立即制定>></a>
    	    <%} %>
    	    </td>
    		</tr>
    		<%} %>
    		<%if((list!=null && list.size()>0)&&(role==1)&&(flag!=3)) {%>
    		<tr>
    		<td colspan="5">&nbsp;</td>
    		</tr>
    		<tr>
    		<td colspan="6" align="center"><input type="button" class="Button1" value="添加任务" onclick="addtask()"/></td>
    		</tr>
    		<%} %>
    		
    		<%if((role==3||role==4)&&list!=null&&flag!=3){%>
    		<tr>
    		<td colspan="5">&nbsp;</td>
    		</tr>
    		<tr>
    		<td colspan="6" align="center">
    		<input type="button" id="update" class="Button1" name="update" value="责令修改里程碑计划" onclick="openwindow()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		<input type="button" id="agree" class="Button1" name ="agree" value="通过该里程碑计划" onclick="agree()"/>
    		</td>
    		</tr>
    		<%} %>
    		
    		
    		<%if((role!=3)&&list!=null&&flag!=3&&flag!=1){%>
    		<tr>
    		<td colspan="5">&nbsp;</td>
    		</tr>
    		<tr>
    		<td colspan="6" align="center" style="color:#F00">
    		里程碑计划修改意见：<% if(state==null){ %>暂无修改意见<% }else {%><%=state %><%} %>
    		</td>
    		</tr>
    		<%} %>
    		
    	</table>
  </td>
      </tr>
      
    </table>		
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