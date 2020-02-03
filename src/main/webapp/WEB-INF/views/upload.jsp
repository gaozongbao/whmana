<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文件上传</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
    function showStatus(){
        var intervalId = setInterval(function(){
             $.get("SpringMVC006/getStatus.do",{},function(data,status){
                console.log(data);
                var percent = data.percent;
                alert(percent);
                if(percent >= 100){
                    clearInterval(intervalId);
                    percent = 100;//不能大于100
                }
                $("#result").width(percent+"%");
            },"json");
        },1000);
    }
    function ajaxtSubmit(){
        var files = document.getElementById("uploadFile").files;
        alert(files.length);
        var formData = new FormData();
        for(var i=0;i<files.length;i++){
            formData.append("file",files[i]);
        }
        formData.append("username","zxm");
        formData.append("password","123456");
        $.ajax({
            type:"post",
            url:"SpringMVC006/fileUpload3.do",
            data:formData,
            processData : false,
            contentType : false,
            /*beforeSend: function(request) {
                request.setRequestHeader("filePath", file_path);
            }, */
            success:function(data){

            },
            error:function(e){

            }
        })
        showStatus();

    }
</script>
</head>
<body>
<!-- action="SpringMVC006/fileUpload3.do" -->
    <form  method="POST" enctype="multipart/form-data" onsubmit="showStatus()">
        <div id="uploadDiv">
            <input type="file" name="file" multiple id="uploadFile"/>
        </div>

        <input type="submit" value="提交"/>

    </form>

    <button onclick="ajaxtSubmit()">ajax提交</button>
    <div style="border:black solid 1px; width: 800px;height: 10px;">
        <div id="result" style="height: 8px;background: green;position: relative; top: 1px;left: 1px;"></div>
    </div>

</body>
</html>