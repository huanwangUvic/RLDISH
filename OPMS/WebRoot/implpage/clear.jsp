<%@ page language="java" import="java.util.*,com.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<%Respro pro = (Respro)request.getAttribute("pro"); 
String name = (String)request.getAttribute("name");
String resman = (String)request.getAttribute("resman");
String task = (String)request.getAttribute("task");
Department dep = (Department) request.getSession().getAttribute("userinfo");
int role = dep.getDeprole();
int plan = pro.getPlanCon();
String caozuo=null;
String condition = null;
if(plan==0)
condition = "尚未制定";
if(plan==1)
condition = "已制定等待审批";
if(plan==2)
condition="限时修改";
if(plan==3)
condition="里程碑计划已审核通过";

int type = pro.getProtype();
String leixing=null;
if(type==1)
leixing = "大修项目";
if(type==2)
leixing = "技术改造项目";
if(type==3)
leixing ="配网改造项目";
if(type==4)
leixing = "科技项目";
if(type==5)
leixing = "信息建设、维护项目";
if(type==6)
leixing = "独立二次项目"; 



String con=null;
if(pro.getProCon()==0)
con="储备中";
if(pro.getProCon()==1)
con="实施中";
if(pro.getProCon()==2)
con="已完成";

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

  <link rel="stylesheet" href="../css/skin.css" type="text/css"></link></head>
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
 

function updaterecord()
{
 if(confirm("确认更新项目信息？"))
 {
    window.location = "<%=basePath%>servlet/ImplUpdateproServlet?id=<%=pro.getProid()%>";
 }
}

function deleterecord()
{
  if(confirm("确认删除该条记录？"))
  {
  window.location = "<%=basePath%>servlet/DeleteServlet?id=<%=pro.getProid()%>&type=<%=pro.getProtype()%>";
  } 
}
  </script>
  
  <body>
  
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="images/mail_leftbg.gif"><img src="images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">项目信息</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="images/mail_rightbg.gif"><img src="images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
    <td valign="middle" background="images/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9">
  
   <table align="center" width="100%" >
 
<tr height="11%" bgcolor="d3eaef" class="STYLE19" class="tb1" align="center">
    <td width="20%">项目名称：</td>
    <td width="30%"><%=pro.getProname()%></td>
    <td width="20%">项目类型：</td>
    <td width="30%"><%=leixing %></td>
  </tr>
  <tr height="11%" bgcolor="d3eaef" class="STYLE19" class="tb1" align="center">
    <td>申请单位：</td>
    <td><%=name %></td>
    <td>申请时间：</td>
    <td><%=pro.getApptime() %></td>
  </tr>
  <tr height="11%" bgcolor="#FFFFFF" class="STYLE19" class="tb1" align="center">
    <td>计划资金：</td>
    <td><%=pro.getPlanmoney() %></td>
    <td>项目批次：</td>
    <td><% if(pro.getProbatch()==null) {%>尚未填写<%} else {%><%=pro.getProbatch() %><%} %></td>
    
  </tr>
  <tr height="11%" bgcolor="#FFFFFF" class="STYLE19" class="tb1" align="center">
    <td>项目批文编号：</td>
    <td><% if(pro.getPronum()==null) {%>尚未填写<%} else {%><%=pro.getPronum() %><%} %></td>
    <td>负责人姓名：</td>
    <td><%=resman %></td>
  </tr>
  
  <tr height="11%" bgcolor="#FFFFFF" class="STYLE19" class="tb1" align="center">
  <td>项目里程碑计划情况：</td>
  <td><%=condition %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=basePath%>servlet/ImpltaskServlet?id=<%=pro.getProid()%>">详细</a></td>
  <td>当前进行任务</td>
  <td><%=task %></td>
  </tr>
 
  <tr height="11%" bgcolor="#FFFFFF" class="STYLE19" class="tb1" align="center">
  <td>到账资金</td>
  <td><% if(pro.getNowMoney()<0) {%>0<%}  else {%><%=pro.getNowMoney() %><%} %></td>
  <td>当前状态：</td>
    <td><%=con %></td>
  </tr>
  
  <tr height="34%" bgcolor="#FFFFFF" class="STYLE19" class="tb1" align="center">
    <td> 项目说明：</td>
    <td colspan="3" align="center"><textarea name="content" readonly="readonly" id="content" class="inptext1"><%=pro.getProexplain() %></textarea></td>
 </tr>

</table>
<div align="center"><input type="button" value="更新项目信息" class="Button1" onclick="updaterecord()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" value="删除该条记录" class="Button1" onclick="deleterecord()"/></div>
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
