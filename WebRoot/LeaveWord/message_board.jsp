<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'message_board.jsp' starting page</title>
    
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
<nav class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false"></button>
            <a class="navbar-brand" style="font-size: 30px;color: #fff;" href="index.html">首页</a>
        </div>
    </div>
</nav>
    <div style="width: 550px; margin: 0 auto;">
        <div class="inputMessage">
            <textarea id="textArea" rows="3" cols="70" name="msg" placeholder=" 说点什么吧！"></textarea>
            <div style="padding-bottom: 5px;">
                <input type="text" name="name" autocomplete="off" class="input-name" placeholder=" 请输入昵称">
                <button type="submit" class="btn btn-primary submitMsg">发表</button>
                <label id="limitSize">50</label><span>/</span><span>50</span>
            </div>
        </div>
        <div>
            <ul>
                <li class="li-admin">
                    <hr>
                    <div class="mname-admin">admin</div>
                    <div>大家好！文明留言，从我做起~</div>
                    <div>2019-04-01 17：02：58</div>
                    <hr>
                </li>
            </ul>
        </div>
        <div>
            <ul id="ul">

            </ul>
        </div>
    </div>
    <script src="js/jquery-2.1.0.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        // 发表留言时用append插入div语句
        var comment = null;
        function div(name,msg,time) {
            comment = '<li>\n' +
                '<hr>\n' +
                '<div class="mname">' + name +
                '</div>\n' +
                '<div class="mmsg">' + msg +
                '</div>\n' +
                '<div class="mtime">' + time +
                '</div>\n' +
                '<hr>\n' +
                '</li>';
        }
        // 加载页面时调用的添加div语句的方法
        function addBox(data_display) {
            $.each(JSON.parse(data_display),function(index,obj){
                $("#ul").append('<li>\n' +
                    '<hr>\n' +
                    //获取名字
                    "<div class='mname'>"+obj['mname']+"</div>"+
                    //获取留言
                    "<div class='mmsg'>"+obj['mmsg']+"</div>"+
                    //获取留言时间
                    "<div class='mtime'>"+obj['mtime']+"</div>"+
                    '<hr>\n' +
                    "</li>");
            });
        }

        $(function () {
            // 页面加载时从数据库读取留言数据
            $.post("displayMsgServlet",function (data_display) {
                addBox(data_display);
            });

            // 设置留言最长为50个字符
            var long = $("#limitSize");
            $("#textArea").bind("input propertychange",function () {
                long.html(50 - this.value.length);
                if (this.value.length > 50) {
                    long.css("color","red");
                    // 禁用发表按钮
                    $(".submitMsg").attr("disabled",true);
                } else {
                    long.css("color","black");
                    $(".submitMsg").attr("disabled",false);
                }
            });
            // 提交留言
            $(".submitMsg").click(function () {
                var _name = $(".input-name").val();
                var _msg = $("#textArea").val();
                if (_msg != "" && _name != "") {
                    /**
                     * sensitiveWordsServlet 使用filter铭感词过滤 
                     * 并使用JDBC将昵称 过滤后的留言和 时间存入到数据库中
                     */
                    $.post("sensitiveWordsServlet", {name: _name, msg: _msg}, function (data_submit) {
                        alert("留言成功！");
                        div(data_submit.name,data_submit.msg,data_submit.time);
                        $("#ul").append(comment);
                        // 清空留言框中的内容
                        $("#textArea").val("");
                    }, "json");
                } else {
                    alert("请输入留言或昵称！请检查");
                }
            });
        });
    </script>
</body>
</html>
</html>
