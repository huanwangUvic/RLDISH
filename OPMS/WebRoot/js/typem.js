var xmlHttp = false;
 function createXMLHttpRequest(){
  if(window.ActiveXObject){
   try{
    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
   }catch(e){
    try{
     xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }catch(ee){
     xmlHttp = false;
    }
   }
  }else if(window.XMLHttpRequest){
   try{
    xmlHttp = new XMLHttpRequest();
   }catch(e){
    xmlHttp = false;
   }
  }
 }
 
var bar_color = 'green';
 var span_id = "block";
 var clear = "&nbsp;&nbsp;&nbsp;";
 
function goUpFile(){
  var obj = document.getElementById("file1");
  if(obj.value == ""){
   alert("请选择需要上传的文件");
   return false;
  }
  createXMLHttpRequest();
  checkDiv();
  var url = "uploadbar.jsp?task=create";    //设置处理文件上传的服务器地址
  var button = document.getElementById("go");
  button.disabled = true;
  xmlHttp.open("get",url,true);        //发送请求
  xmlHttp.onreadystatechange = goCallback;    //设置回调函数
  xmlHttp.send(null);
 }
 
function goCallback(){
  if(xmlHttp.readyState == 4){
   if(xmlHttp.status == 200){
    setTimeout("pollServer()", 200);
   }
  }
 }
 
function checkDiv(){
  var progress_bar = document.getElementById("progressBar");
  if(progress_bar.style.visibility == "visible"){
   clearBar();
   document.getElementById("complete").innerHTML = "";
  }else{
   progress_bar.style.visibility = "visible";
  }
 }
 
function pollServer(){
  createXMLHttpRequest();
  var url = "uploadbar.jsp?task=poll";
  xmlHttp.open("get",url,true);
  xmlHttp.onreadystatechange = pollCallback;
  xmlHttp.send(null);
 }
 
function pollCallback(){           //实现进度条
  if(xmlHttp.readyState == 4){
   if(xmlHttp.status == 200){
    var percent_complete = xmlHttp.responseXML.getElementsByTagName("percent")[0].firstChild.data;
    var index = processResult(percent_complete);
    for(var i = 1; i <= index; i++){
     var elem = document.getElementById("block"+i);
     elem.innerHTML = clear;
     elem.style.backgroundColor = bar_color;
     var next_cell = i + 1;
     if(next_cell > index && next_cell <= 10){
      document.getElementById("block"+next_cell).innerHTML = percent_complete + "%";
     }
    }
    if(index < 10){
     setTimeout("pollServer()", 200);
    }else{
     document.getElementById("complete").innerHTML = "完成";
     document.getElementById("go").disabled = false;
     upfileform.enctype = "multipart/form-data";
     upfileform.action = "upfile.jsp";
     upfileform.submit();
    }
   }
  }
 }
 
function processResult(percent_complete){
  var ind;
  if(percent_complete.length == 1){
   ind = 1;
  }else if(percent_complete.length == 2){
   ind = percent_complete.substring(0,1);
  }else{
   ind = 10;
  }
  return ind;
 }
 
function clearBar(){
  for(var i = 1; i < 11; i++){
   var elem = document.getElementById("block"+i);
   elem.innerHTML = clear;
   elem.style.backgroundColor = "while";
  }
 }
