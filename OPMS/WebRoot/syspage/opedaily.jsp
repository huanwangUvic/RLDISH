<%@ page language="java" import="java.util.*,com.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'opedaily.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">

	function openwindow()
	{ 
	 var temp= window.showModalDialog("<%=basePath%>syspage/NewBox.jsp",window,"status:no;scroll:no;dialogWidth:350px;dialogHeight:300px");
	   
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
	height: 45px;
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
<script language="javascript" type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
 <link rel="stylesheet" href="../css/skin.css" type="text/css"></link></head>
  
  <body onload="WdatePicker()">

  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="images/mail_leftbg.gif"><img src="images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
		<td height="31"><div class="titlebt">操作日志</div></td>
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
    
    <tr >
       <td colspan="7" bgcolor="d3eaef" class="STYLE6" align="center">	
       								
			<form action="<%=basePath%>servlet/RecordQueryServlet" method="post">
			
			<br/>用户账号：
    <input type="text" class="text" name="usernum" /> 操作类型：<select name="opetype" id="opetype">
    <option value="0">不限</option>
    <option value="1">添加数据</option>
    <option value="2">删除数据</option>
    <option value="3">修改数据</option>
    <option value="4">查看数据</option>
   </select> 内容关键字：<input type="text" class="text" name="content" id="content" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" class="Button1" onclick="return checkform()" value="查询"/>&nbsp;&nbsp;



            </form>
      </td>
   </tr>
  
  
    		<tr bgcolor="d3eaef" class="STYLE6">
	    		<th width="15%">操作用户</th>
	    		<th width="15%">用户姓名</th>
	    		<th width="15%">用户角色</th>
	    		<th width="15%">操作内容</th>
	    		<th width="15%">操作类型</th>
	    		<th width="15%">操作时间</th>
    		</tr>
    		<% List list = (List) request.getSession().getAttribute("listx");
    		List list2 = (List) request.getSession().getAttribute("liststrx");
    		List list3 = (List) request.getSession().getAttribute("liststrx2");
    		
    			if(list!=null && list.size()>0){
    				for(int j=0; j < list.size(); j++){
    				Operecord record = (Operecord)list.get(j);%>
    		 <tr  bgcolor="#FFFFFF" class="STYLE19" class="tb1" align="center">
    		 	<td width="15%"><%=record.getOpeman() %></td>
    			 <td width="15%"><%=(String)list2.get(j) %></td>
    			 
    			  <td width="15%"><%if((Integer)list3.get(j)==1) {%>地方单位<%} %> 
    			  <%if((Integer)list3.get(j)==2) {%>项目专工<%} %> 
    			  <%if((Integer)list3.get(j)==3) {%>乐山局领导<%} %>
    			  <%if((Integer)list3.get(j)==4) {%>系统管理员<%} %> 
    			  </td>
    			  <td width="15%"><%=record.getOpecontent() %></td>
    			  
    			  <td width="15%"><%if(record.getOpetype()==1) {%>添加数据<%} %> 
    			  <%if(record.getOpetype()==2) {%>删除数据<%} %> 
    			  <%if(record.getOpetype()==3) {%>修改数据<%} %>
    			  <%if(record.getOpetype()==4) {%>查看数据<%} %> 
    			  </td>
    			 <td width="15%"><%=record.getOpetime() %></td>
    			  
    		 </tr>
    		<%			}}  else {%>
    		<tr>
    		<td colspan="5">&nbsp;</td>
    		</tr>
    		<tr>
    	    <td colspan="5" align="center" style="color:#F00">无相关查询结果！</td>
    		</tr>
    		
    		<%} %>
    		<tr class="tb1">
							<%
								int curpage = (Integer)request.getSession().getAttribute("curpage");
								int pageall = (Integer)request.getSession().getAttribute("pageall");
							%>
								<td colspan="7" class="pag">
									<a href="<%=basePath%>servlet/OpeRecordPageServlet?pg=1">首页</a>
									<a href="<%=basePath%>servlet/OpeRecordPageServlet?pg=<%=curpage-1 %>">上一页</a>
									<a href="<%=basePath%>servlet/OpeRecordPageServlet?pg=<%=curpage+1 %>">下一页</a>
									<a href="<%=basePath%>servlet/OpeRecordPageServlet?pg=<%=pageall %>">尾页</a>
									共<%=pageall %>页&nbsp;&nbsp;&nbsp;第<%=curpage %>页
								</td>
							</tr>
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