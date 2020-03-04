<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% String info  = (String)request.getAttribute("erroinfo");
String type = null;
int proid = -1;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'erro.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script  language="javascript" runat="server" >
<%if(info.equals("用户名密码错误!")) {%>
alert('用户名密码错误！');
window.location="<%=basePath%>login.jsp";

<%}%>

<%if(info.equals("操作成功！")) {
type = (String)request.getSession().getAttribute("proleixing");
%>
alert('操作成功！');
window.location="<%=basePath%>servlet/ResproServlet?a=<%=type%>";
<%}%>

<%if(info.equals("删除成功！")) {
type = (String)request.getSession().getAttribute("proleixing");
%>
alert('删除成功！');
window.location="<%=basePath%>servlet/ResproServlet?a=<%=type%>";
<%}%>

<%if(info.equals("申请项目成功！等待审核")) {
type = (String)request.getSession().getAttribute("proleixing");
%>
alert('申请项目成功！等待审核');
window.location="<%=basePath%>servlet/ResproServlet?a=<%=type%>";
<%}%>

<%if(info.equals("更新项目信息成功！")) {
type = (String)request.getSession().getAttribute("proleixing");%>
alert('更新项目信息成功！');
window.location="<%=basePath%>servlet/ResproServlet?a=<%=type%>";
<%}%>

<%if(info.equals("1")) {
proid = (Integer)request.getAttribute("proid");%>
alert('修改意见提交成功！');
window.location="<%=basePath%>servlet/ImpltaskServlet?id=<%=proid%>";
<%}%>

<%if(info.equals("2")) {
proid = (Integer)request.getAttribute("proid");%>
alert('操作成功，该项正式转入实施阶段！');
window.location="<%=basePath%>servlet/ImpltaskServlet?id=<%=proid%>";
<%}%>

<%if(info.equals("3")) {
proid = (Integer)request.getAttribute("id");%>
alert('项目计划制定成功，等待局领导审批！');
window.location="<%=basePath%>servlet/ImpltaskServlet?id=<%=proid%>";
<%}%>

<%if(info.equals("5")) {
proid = (Integer)request.getAttribute("id");%>
alert('任务删除成功！');
window.location="<%=basePath%>servlet/ImpltaskServlet?id=<%=proid%>";
<%}%>

<%if(info.equals("4")) {
proid = (Integer)request.getAttribute("proid");%>

alert('修改成功！');
window.location="<%=basePath%>servlet/ImpltaskServlet?id=<%=proid%>";
<%}%>

<%if(info.equals("6")) {
proid = (Integer)request.getAttribute("proid");%>

alert('修改成功！');
window.location="<%=basePath%>servlet/ImplclearServlet?id=<%=proid%>";
<%}%>

<%if(info.equals("7")) {
%>

alert('添加账户成功！');
window.location="<%=basePath%>servlet/UserManagementServlet";
<%}%>

<%if(info.equals("8")) {
%>

alert('重置密码成功！');
window.location="<%=basePath%>servlet/UserManagementServlet";
<%}%>

<%if(info.equals("9")) {
%>

alert('账户已注销！');
window.location="<%=basePath%>servlet/UserManagementServlet";
<%}%>

<%if(info.equals("10")) {
%>

alert('修改密码成功！');
window.location="<%=basePath%>syspage/updatepwd.jsp";
<%}%>

<%if(info.equals("11")) {
%>

alert('原始密码错误！');
window.location="<%=basePath%>syspage/updatepwd.jsp";
<%}%>
</script>

  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
