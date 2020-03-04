<%@ page language="java" import="java.util.*,com.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<%Respro pro = (Respro)request.getAttribute("pro"); 
String name = (String)request.getAttribute("name");
ArrayList<Department> list = (ArrayList<Department>)request.getAttribute("list");
String task = (String)request.getAttribute("task");

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

function updaterecord()
{
 if(confirm("确认修改此阶段任务？")){
	
	var proname = document.getElementById("proname").value;
	var apptime = document.getElementById("apptime").value;
	var planmoney = document.getElementById("planmoney").value;
	
	var nowmoney = document.getElementById("nowmoney").value;
	var content = document.getElementById("content").value;
	var resman = document.getElementById("resman").value;
	var protype = document.getElementById("protype").value;
	
	//document.getElementById("hid").value=2;
	if(proname.length == 0){
	   alert('任务名称不能为空！');
	   return false;
	   }
	   
	   if(protype =="0")
	 {
	  alert('请选择项目类型！');
	  return false;
	 }
	if(apptime.length == 0){
	   alert('开始时间不能为空');
	   return false;
	   }
	
	if(isNaN(planmoney)){
	   alert('计划资金格式非法！');
	   return false;
	   }
	   
	   if(isNaN(nowmoney)){
	   alert('到账资金格式非法！');
	   return false;
	   }
	   
	  if(resman =="0")
	 {
	  alert('请选择项目负责人！');
	  return false;
	 }
    
	if(file.length ==0)
	   {
	      alert('请填写需提交文件！');
	      return false;
	   }
	   
	    if(content.length == 0)
	   {
	     alert('项目说明不能为空！');
	     return false;
	   }
	   
	   return true;
	   }
	else{
			return false;
		}
	
}


  </script>
  <script language="javascript" type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
  <body onload="WdatePicker()">
  
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr >
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
  <form method="post" action="<%=basePath%>servlet/ImplUpdateproServlet?id=<%=pro.getProid() %>">
   <table align="center" width="100%">
 
<tr height="10%" bgcolor="d3eaef" class="STYLE19" class="tb1">
    <td width="20%">项目名称：</td>
    <td width="30%"><input type="text" name="proname" id="proname" value="<%=pro.getProname() %>"></td>
    <td>项目类型：</td>
    <td><select name="protype" id="protype">
    <option value="0">请选择</option>
    <option value="1">大修项目</option>
    <option value="2">技术改造项目</option>
    <option value="3">配网改造项目</option>
    <option value="4">科技项目</option>
    <option value="5">信息建设、维护项目</option>
    <option value="6">独立二次项目</option>
    </select></td>
  </tr>
  <tr height="10%" bgcolor="d3eaef" class="STYLE19" class="tb1">
    <td>申请单位：</td>
    <td><%=name %></td>
    <td>申请时间：</td>
    <td><input type="text" value="<%=pro.getApptime() %>" class="text" name="apptime" id="apptime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
  </tr>
  <tr height="10%" bgcolor="#FFFFFF" class="STYLE19" class="tb1">
    <td>计划资金：</td>
    <td><input type="text" value="<%=pro.getPlanmoney() %>" id="planmoney" name="planmoney"/></td>
    <td>项目批次：</td>
    <td><input type="text" value="<%=pro.getProbatch() %>" name="probatch" id="probatch"/></td>
    
  </tr>
  <tr height="10%" bgcolor="#FFFFFF" class="STYLE19" class="tb1">
    <td>项目批文编号：</td>
    <td><input type="text" value="<%=pro.getPronum() %>" name="pronum" id="pronum"/></td>
    <td>负责人姓名：</td>
    <td><select name="resman" id="resman">
    <option value="0">请选择</option>
    <%int i=0; 
    while(i<list.size()) {
    %>
    <option value="<%=list.get(i).getDepnum() %>"><%=list.get(i).getDepnum() %>: <%=list.get(i++).getDepname() %></option>
  <%} %>
    </select></td>
  </tr>
  
  <tr height="10%" bgcolor="#FFFFFF" class="STYLE19" class="tb1">
  <td>当前进行任务</td>
  <td><%=task %></td>
   <td>到账资金</td>
  <td><input type="text" id="nowmoney" name="nowmoney" value="<%=pro.getNowMoney() %>"/></td>
  </tr>
 
  <tr height="10%" bgcolor="#FFFFFF" class="STYLE19" class="tb1">
  
  <td >当前状态：</td>
    <td colspan="3">
    <%if(pro.getProCon()==0) {%>
    <input type="radio" name="taskcon" value=0 checked="checked" >储备中
    <input  type="radio" name="taskcon" value=1>实施中
    <input type="radio" name="taskcon" value=2>已完成
    <%} %>
    <%if(pro.getProCon()==1) {%>
    <input type="radio" name="taskcon" value=0  >储备中
    <input  type="radio" name="taskcon" value=1 checked="checked">实施中
    <input type="radio" name="taskcon" value=2>已完成
    <%} %>
    
   <%if(pro.getProCon()==2) {%>
    <input type="radio" name="taskcon" value=0 >储备中
    <input  type="radio" name="taskcon" value=1 >实施中
    <input type="radio" name="taskcon" value=2 checked="checked" >已完成
    <%} %>
    </td>
  </tr>
  
  <tr height="34%" bgcolor="#FFFFFF" class="STYLE19" class="tb1" >
    <td> 项目说明：</td>
    <td colspan="3" align="center"><textarea name="content"  id="content" class="inptext1"><%=pro.getProexplain() %></textarea></td>
 </tr>

</table>

<div align="center"><input type="submit" value="更新项目信息" class="Button1" onclick="return updaterecord()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" value="删除该条记录" class="Button1" onclick="deleterecord()"/></div>
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
