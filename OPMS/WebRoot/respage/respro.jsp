<%@ page language="java" import="java.util.*,com.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%Department dep = (Department)request.getSession().getAttribute("userinfo");
int role = dep.getDeprole();
int i=0; %>
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

	function checkform()
	{
	  return true;
	}
	
	function addrecord()
	{
	 if(confirm("确认申报新的项目？"))
   {
     window.location = "<%=basePath%>servlet/AddproServlet";
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
	height: 39px;
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
		<td height="31"><div class="titlebt">查看记录</div></td>
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
       								
			<form action="<%=basePath%>servlet/QueryServlet" method="post">
			项目名称：
    <input type="text" class="text" name="proname" />
    &nbsp;<%if(role==3||role==4){ %>申报单位：<input type="text" class="text" name="prodep"/>&nbsp;<%} %>
    
<input type="submit" class="Button1" onclick="return checkform()" value="查询"/>&nbsp;&nbsp;

<%if(role==1) { %><input type="button" class="Button1" value="申报新项目" onclick="addrecord()"/>
<%} %>

            </form>
      </td>
   </tr>
  
  
    		<tr bgcolor="d3eaef" class="STYLE6">
	    		<th width="20%">项目名称</th>
	    		<th width="20%">申报单位</th>
	    		<th width="20%">申报时间</th>
	    		<th width="20%">计划资金</th>
	    		<th width="20%">操作</th>
    		</tr>
    		<% ArrayList<Respro> list = (ArrayList<Respro>)request.getAttribute("list");
    		ArrayList<String> str = (ArrayList<String>)request.getAttribute("depname");
    			if(list!=null && list.size()>0){
    				for(Respro pro:list){%>
    		 <tr bgcolor="#FFFFFF" class="STYLE19" class="tb1" align="center">
    		 	<td width="20%"><%=pro.getProname() %></td>
    			 <td width="20%"><%=str.get(i++) %></td>
    			  <td width="20%"><%=pro.getApptime() %></td>
    			  <td width="20%"><%=pro.getPlanmoney() %></td>
    			  
    			  <td width="20%"><a href="<%=basePath%>servlet/ProclearServlet?id=<%=pro.getProid() %>&num=<%=pro.getProdep() %>">详细</a></td>
    		 </tr>
    		<%			}}  else {%>
    		<tr>
    		<td colspan="5">&nbsp;</td>
    		</tr>
    		<tr>
    	    <td colspan="5" align="center" style="color:#F00">无相关查询结果！</td>
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