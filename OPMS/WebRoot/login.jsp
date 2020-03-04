<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>用户登录 </title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
.Button1
{padding: 2 4 0 4;font-size:12px;height:23;background-color:#ece9d8;border-width:1;}

</style>
<link href="css/css.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">
function yanzheng()
{
var username = document.myform.username.value;
var password = document.myform.password.value;

  if(username.length < 4){
   alert('账号格式错误！');
   return false;
  }
  if(password.length < 5){
	  alert('密码格式错误！');
	return false; 
  }
  return true;	
		}
</script>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="147" background="images/top02.gif"><img src="images/top03.gif" width="776" height="147" /></td>
  </tr>
</table>
<table width="562" border="0" align="center" cellpadding="0" cellspacing="0" class="right-table03">
  <tr>
    <td width="221"><table width="95%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
      
      <tr>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
          <tr>
            <td align="center"><img src="images/ico13.gif" width="107" height="97" /></td>
          </tr>
          <tr>
            <td height="40" align="center">&nbsp;</td>
          </tr>
          
        </table></td>
        <td><img src="images/line01.gif" width="5" height="292" /></td>
      </tr>
    </table></td>
   <form name="myform" action="<%=basePath%>servlet/LoginServlet" method="post">
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="31%" height="35" class="login-text02">用户名称：<br /></td>
        <td width="69%"><input name="username" type="text" size="30" /></td>
        
      </tr>
      <tr>
        <td height="35" class="login-text02">密　码：<br /></td>
        <td><input name="password" type="password" size="31" /></td>
      </tr>
      
      <tr><td class="login-text02">用户身份：</td>
      <td>
      <select name="protype" id="protype">
    <option value="0">地方单位</option>
    <option value="1">乐山局领导</option>
    <option value="2">项目负责人</option>
    <option value="3">系统管理员</option>
    
    </select></td></tr>
      
      <tr>
        <td height="35">&nbsp;</td>
        <td><input name="Submit2" type="submit" class="Button1" value="确认登陆" onclick="return yanzheng()" />
          <input name="Submit232" type="button" class="Button1" value="重 置" /></td>
      </tr>
      </form>
    </table></td>
  </tr>
</table>
</body>
</html>
