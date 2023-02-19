<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
	String path = request.getContextPath();//获取项目名称
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎注册</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/Style/Register.css" />
<script type="text/javascript" src="Script/jquery-1.10.1.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="wrapLogin">
	<span class="retun_login">
			        <label>已有账号？返回 </label><a href="Login.jsp">登录</a>
			    </span> 
		    <div class="loginPanel">
		    	<form action="<%=path %>/UserServlet?type=8"  method="post">
		    	   
		    		<h2>停车场管理系统</h2>
			       	<p><label>用户名：</label><input type="text" name="user_name" class="id"placeholder="使用用户登录" required/></p>
			        <p><label >密&nbsp;&nbsp;码&nbsp;：</label><input type="password" name="user_pwd" required/></p>
			        <p><label>Email&nbsp;：</label><input type="text" name="user_email" class="email" placeholder="请输入电子邮箱" required/></p>
			        <p><label>姓&nbsp;&nbsp;名&nbsp;：</label><input type="text" name="real_name" class="name" placeholder="请输入真实姓名" required/></p>
			        <p><label>手机号：</label><input type="text" name="user_phone" class="phone" /></p>
			        
			        <p>
			        <label>性&nbsp;&nbsp;别&nbsp;：</label>
			        <span class="gender">
			        <input name="user_gender" type="radio" value="男" checked="checked">男
     				<input name="user_gender" type="radio" value="女">女
     				</span>
     				</p>
     				
			        <p>
			        <label>生&nbsp;日&nbsp;：</label>
			        <span class="birth_span"><input id="birth" type="date" name="user_birth"/></span>
			        </p>
			        <p><label>车牌号：</label><input type="text" name="car_num"  class="car"/></p>
			        <p><label>&nbsp;住&nbsp;址&nbsp;：</label><input type="text" name="user_addr"  class="car"/></p>
			        <p><label>角&nbsp;色&nbsp;：</label>
			        <span class="role">
						<select name="role_id">
						<option value="c001">用户</option>						
						</select>
					</span>
						</p>
				
			        <p><label>验证码：</label> <input type="verify" placeholder="输入验证码" name="ValiImage" id="ValiImage" /><!-- 输入验证码文本 -->
			        <img class="i" src="<%=path %>/VerifyCode" onclick="changeValiImage(this)" ><!-- 验证码图片 -->
			        </p>
					<script type="text/javascript">
						function changeValiImage(img){
						img.src = "VerifyCode?time=" + new Date().getTime();//刷新
						}
					</script>    
			        <p class="btn">
			        <input type="submit" class="btnLogin" value="注册"  />
			        <input type="reset" class="btnCancel" value="重置" />
			        
			        </p>	
			        
			         		
		       	</form>
		     </div>
    </div>
		<!-- loginPanel End -->
</body>
</html>