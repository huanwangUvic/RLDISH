<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
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
 if(confirm("确认添加新用户账号？")){
	
	var usernum = document.getElementById("usernum").value;
	var username = document.getElementById("username").value;
	var pwd = document.getElementById("pwd").value;
	
	var agpwd = document.getElementById("agpwd").value;
	
	var userrole = document.getElementById("userrole").value;
	
	
	//document.getElementById("hid").value=2;
	if(usernum.length <4 ){
	   alert('账号不能小于4位！');
	   return false;
	   }
	   
	  
	
	
	if(!isNaN(username)){
	   alert('姓名格式非法！');
	   return false;
	   }
	   
	   if(pwd.length <5&&pwd.length!=0)
	   {
	      alert('密码不能小于5位！');
	      return false;
	   }
	  
	  if(pwd!=agpwd)
	 {
	  alert('两次密码输入不一致！');
	  return false;
	 }
    
	
	   
	    if(userrole =="0")
	 {
	  alert('请选择用户角色！');
	  return false;
	 }
	   window.close();
	   return true;
	   }
	else{
			return false;
		}
	
}


  </script>
  <base target="mainFrame">
  </head>
  
  <body>
  <form method="post" action="<%=basePath %>servlet/AddUserServlet">
    <table width="80%" align="center">
    <tr>
    <td>用户账号：</td>
    <td><input type="text" id="usernum" name="usernum"/>*</td></tr>
    <tr><td>使用者姓名：</td>
    <td><input type="text" id="username" name="username"/>*</td></tr>
    <tr><td>密码：</td>
    <td><input type="password" id="pwd" name="pwd"/></td></tr>
     <tr><td>确认密码：</td>
    <td><input type="password" id="agpwd" name="agpwd"/></td></tr>
    <tr><td>用户角色：</td>
    <td><select name="userrole" id="userrole">
    <option value="0">请选择</option>
    <option value="1">地方单位</option>
    <option value="2">项目专工</option>
    <option value="3">乐山局领导</option>
    <option value="4">系统管理员</option>
    </select>*</td></tr>
    <tr>
    		<td colspan="2">&nbsp;</td>
    		</tr>
    <tr>
    <td colspan="2" align="center"><input type="submit" value="确认添加" class="Button1" onclick="return checkform()"/></td>
    </tr>  
    </table>
    </form>
  </body>
</html>
