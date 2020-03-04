<%@ page language="java" import="java.util.*,java.io.File,java.io.FilenameFilter" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
String id = request.getParameter("taskid");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'download.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <center>
   <h3>请选择要下载的文件</h3>
   <table>
   <%
      String path2 = request.getRealPath("/");
      
      path2+="upload\\";
      path2+=id;
      path2+="\\";
     
      File file1= new File(path2);
     //out.print(path2);
      String str[] = file1.list();
      for(int i=0;i<str.length;i++)
      {
        String ss=str[i];
        out.println("<tr><td>"+ss+"</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+basePath+"implpage/download1.jsp?name1="+ss+"&id="+id+"'>下载 </a></td></tr>");
        
      }
   
    %>
   </table>
   </center>
  </body>
</html>
