<%@ page contentType="text/html;charset=utf-8"%>
<%@ page language="java" import="java.io.*,java.net.*" pageEncoding="utf-8"%>
<html>
<head> 
<title>test</title>

</head> 
<body>
<% 
response.setContentType("text/html");
String id = request.getParameter("id");
javax.servlet.ServletOutputStream ou = response.getOutputStream();
String filepath="D:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\EPB\\upload\\";
filepath+=id;
filepath+="\\";
String filename=new String(request.getParameter("name1").getBytes("ISO8859_1"),"utf-8").toString();
System.out.println("DownloadFile filepath:" + filepath);
System.out.println("DownloadFile filename:" + filename);
java.io.File file = new java.io.File(filepath + filename);
if (!file.exists()) {
System.out.println(file.getAbsolutePath() + " 文件不存在!");
return;
}
// 读取文件流
java.io.FileInputStream fileInputStream = new java.io.FileInputStream(file);
// 下载文件
// 设置响应头和下载保存的文件名
if (filename != null && filename.length() > 0) {
response.setContentType("application/x-msdownload");
response.setHeader("Content-Disposition", "attachment; filename=" + new String(filename.getBytes("utf-8"),"iso8859-1") + "");
if (fileInputStream != null) {
int filelen = fileInputStream.available();
//文件太大时内存不能一次读出,要循环
byte a[] = new byte[filelen];
fileInputStream.read(a);
ou.write(a);
}
fileInputStream.close();
ou.close();
}
%> 
</body>
</html>

