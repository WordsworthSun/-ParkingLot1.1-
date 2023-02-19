<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
	String path = request.getContextPath();//获取项目名称
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎登陆</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/Style/Login.css" />
<script type="text/javascript" src="Script/jquery-1.10.1.js"></script>
<script type="text/javascript">
	$(function()
	{
		$(document).on("click",".a_reg",function()
		{
			$(".model").fadeIn();
		});
		
		$(document).on("click",".a_close",function()
		{
			$(".model").fadeOut();
		});
	});
	function checkPwd()
	{
		if($("[name=user_pwd1]").val()!=$("[name=re_pwd]").val())
		{
			alert("两次输入密码不一致~~~");
			return false;
		}
		else
		{
			return true;
		}
	};
</script>
</head>
<body>
	<div class="wrapLogin">
		    <div class="loginPanel">
		    	<form action="<%=path %>/LoginServlet" method="post">
		    		<h2>停车场管理系统</h2>
			       	<p><label>用户名：</label><input type="text" name="user_name" value="SAdmin" /></p>
			        <p><label >密&nbsp;码：</label><input type="password" name="user_pwd" value="123456" /></p>
			        <p><label>验证码：</label> <input type="verify" placeholder="输入验证码" name="ValiImage" id="ValiImage" /><!-- 输入验证码文本 -->
			        <img class="i" src="<%=path %>/VerifyCode" onclick="changeValiImage(this)" ><!-- 验证码图片 -->
			        </p>
					<script type="text/javascript">
						function changeValiImage(img){
						img.src = "VerifyCode?time=" + new Date().getTime();//刷新
						}
					</script>    
			        <p class="btn">
			        <input type="submit" class="btnLogin" value="登录"  />
			        <input type="button"  class="btnCancel" value="注册"  onclick='location.href=("Register.jsp")'/>
			        </p>	    
			         		
		       	</form>
		     </div>
    </div>
		<!-- loginPanel End -->
</body>
</html>