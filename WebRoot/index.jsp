<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<base href="<%=basePath%>">
    	<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<title>停车场管理系统</title>
		<link href="<%=path %>/Style/Index.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path %>/Script/jquery-1.10.1.js"></script>
		<script type="text/javascript" src="<%=path %>/Script/Index.js"></script>
	</head>
		 <%-- 判断uName是否存在，如何不存在则证明非登录操作，跳转至登录页面 --%>
		 <%  if (session.getAttribute("user_name") == null ) {%>
	 	<jsp:forward page="Login.jsp" ></jsp:forward>
	 <% } %>
	<!--header-->
 	<div class="header">
 			<div class="logo">
 				<img class="logo_img"alt="这是logo" src="<%=path %>/Images/logo.png" >
 			</div>
	        <div class="header_logo">
	        	停车场管理系统
	        	
	        </div>	        
	        <div class="data">
	       		 欢迎使用停车场管理系统登录时间是: <%= (new java.util.Date()).toLocaleString()%>
	        </div>
    </div>
    <div class="wrap">
        <ul class="siderbar">       	
        	<%
        		if(session.getAttribute("role_id").toString().equals("a001"))
        		{
        	%>
        	<li><span><img src="<%=path %>/Images/user.png" >用户信息管理</span>
	        	<ul>	        	
	               <li><a href="<%=path %>/Control/UserAdd.jsp" target="mainFrame">添加用户信息</a></li>
	               <li><a href="<%=path %>/UserServlet?type=4" target="mainFrame">管理用户信息</a></li>
	               <li><a href="<%=path %>/StatisServlet?type=3"  target="mainFrame">用户信息统计</a></li>
	        	</ul>
	        </li>
	       	<li ><span><img src="<%=path %>/Images/card.png" >会员卡信息管理</span>
	            <ul>
	                <li><a href="<%=path %>/Control/CardAdd.jsp" target="mainFrame">添加会员卡</a></li>
	                <li><a href="<%=path %>/CardServlet?type=4" target="mainFrame">管理会员卡</a></li>
	                <li><a href="<%=path %>/StatisServlet?type=5"  target="mainFrame">会员卡信息统计</a></li>
	            </ul>
	        </li>
	        <li><span><img src="<%=path %>/Images/seat.png" >车位信息管理</span>
	            <ul>
	               <li><a href="<%=path %>/Control/SeatAdd.jsp" target="mainFrame">添加车位信息</a></li>
	               <li><a href="<%=path %>/SeatServlet?type=4" target="mainFrame">管理车位信息</a></li>
	               <li><a href="<%=path %>/StatisServlet?type=6"    target="mainFrame">车位信息统计</a></li>
	            </ul>
	        </li>
	        
	        <li><span><img src="<%=path %>/Images/fixed.png" >固定车主停车管理</span>
	            <ul>
	                <li><a href="<%=path %>/FixedServlet?type=6" target="mainFrame">出入场设置</a></li>
	                <li><a href="<%=path %>/FixedServlet?type=4" target="mainFrame">停车信息管理</a></li>
	        	</ul>
	        </li>
	        <li><span><img src="<%=path %>/Images/temp.png" >临时车辆停车管理</span>
	        	<ul>
	                <li><a href="<%=path %>/Control/TempAdd.jsp" target="mainFrame">车辆入场信息</a></li>
	                <li><a href="<%=path %>/TempServlet?type=4" target="mainFrame">车辆出场信息</a></li>
	                <li><a href="<%=path %>/StatisServlet?type=4"  target="mainFrame">临时车辆收费统计</a></li>
	        	</ul>
            </li>
	        <li><span><img src="<%=path %>/Images/system.png" >系统功能操作</span>
	        	<ul>	        		
	        		<li><a href="<%=path %>/MessageServlet?type=4"  target="mainFrame">留言</a></li>	        		
<%-- 	        		<li><a href="<%=path %>/LeaveWord/Comment.jsp" target="mainFrame">留言板</a></li>	              	 	         --%>
<!-- 	 	            <li><a href="Txt.jsp" target="mainFrame">文本</a></li> -->
<%-- 	                <li><a href="<%=path %>/Control/QRCode.jsp" target="mainFrame">生成二维码</a></li> --%>
	                <li><a href="<%=path %>/Control/ChagePwd.jsp" target="mainFrame">修改密码</a></li>
	                <li><a href="<%=path %>/Control/Logout.jsp">退出系统</a></li>
	        	</ul>
	       </li>
	        <%
	        	}
	        	else if(session.getAttribute("role_id").toString().equals("e001"))	        	
	        	{
	        %>  	        	    	        	  
	        <li><span>车位信息管理</span>
	            <ul>
	               <li><a href="<%=path %>/Control/SeatAdd.jsp" target="mainFrame">添加车位信息</a></li>
	               <li><a href="<%=path %>/SeatServlet?type=4" target="mainFrame">管理车位信息</a></li>
	            </ul>
	        </li>
	        <li><span>会员卡信息管理</span>
	            <ul>
	                <li><a href="<%=path %>/Control/CardAdd.jsp" target="mainFrame">添加会员卡类型</a></li>
	                <li><a href="<%=path %>/CardServlet?type=4" target="mainFrame">管理会员卡类型</a></li>
	            </ul>
	        </li>
	        <li><span>固定车主停车管理</span>
	            <ul>
	                <li><a href="<%=path %>/FixedServlet?type=6" target="mainFrame">出入场设置</a></li>
	                <li><a href="<%=path %>/FixedServlet?type=4" target="mainFrame">停车信息管理</a></li>
	        	</ul>
	        </li>
	        <li><span>临时车辆停车管理</span>
	        	<ul>
	                <li><a href="<%=path %>/Control/TempAdd.jsp" target="mainFrame">车主入场信息</a></li>
	                <li><a href="<%=path %>/TempServlet?type=4" target="mainFrame">车主出场信息</a></li>
	        	</ul>
			</li>
	        <li><span>系统功能操作</span>
	        	<ul>
	        	    <li><a href="<%=path %>/LeaveWord/Announce.jsp" target="mainFrame">发布公告</a></li>
	        	    <li><a href="<%=path %>/LeaveWord/Comment.jsp" target="mainFrame">留言板</a></li>	        	    
	                <li><a href="<%=path %>/Control/ChagePwd.jsp" target="mainFrame">修改密码</a></li>
	                <li><a href="<%=path %>/Control/Logout.jsp">退出系统</a></li>
	        	</ul>
	       </li>
	       <%
	         }
	         else if(session.getAttribute("role_id").toString().equals("c001"))//固定用户
	         {%>
	          <li><span>车位信息</span>
	            <ul>	               
	               <li><a href="<%=path %>/SeatServlet?type=6" target="mainFrame">查看车位</a></li>	               
	            </ul>
	        </li>
	        <li><span>我的会员卡信息</span>
	            <ul>	                
	                <li><a href="<%=path %>/CardServlet?type=6" target="mainFrame">我的会员卡信息</a></li>
	            </ul>
	        </li>
	        <li><span>我的停车信息</span>
	            <ul>	               
	                <li><a href="<%=path %>/FixedServlet?type=7" target="mainFrame">固定车主停车信息</a></li>	                
	        	</ul>
	        </li>	       
	        <li><span>系统功能操作</span>
	        	<ul>	        	
	        	    <li><a href="<%=path %>/UserServlet?type=9" target="mainFrame">我的信息</a></li>
<%-- 	                <li><a href="<%=path %>/MessageServlet?type=7"target="mainFrame">公告</a></li>	    	                 --%>
	                <li><a href="<%=path %>/MessageServlet?type=9"target="mainFrame">留言</a></li>	           	    	               
	                <li><a href="<%=path %>/Control/Logout.jsp">退出系统</a></li>
	        	</ul>
	       </li>	        	         
	         <%}	         
	         else{//临时用户
	         %>	         
	           <li><span>我的车位信息</span>
	            <ul>	               
	               <li><a href="<%=path %>/SeatServlet?type=6" target="mainFrame">我的车位</a></li>	               
	            </ul>
	        </li>
	        <li><span>我的会员卡信息</span>
	            <ul>	                
	                <li><a href="<%=path %>/CardServlet?type=6" target="mainFrame">我的会员卡类型</a></li>
	            </ul>
	        </li>
	        <li><span>我的停车信息</span>
	            <ul>	               
	                <li><a href="<%=path %>/FixedServlet?type=7" target="mainFrame">我的停车信息</a></li>
	        	</ul>
	        </li>
	        <li><span>我的临时车辆停车管理</span>
	        	<ul>	                
	                <li><a href="<%=path %>/TempServlet?type=6" target="mainFrame">车主出场信息</a></li>
	        	</ul>
			</li>
	        <li><span>系统功能操作</span>
	        	<ul>
	                <li><a href="<%=path %>/MessageServlet?type=7"target="mainFrame">公告</a></li>
	                <li><a href="<%=path %>/LeaveWord/Comment.jsp" target="mainFrame">留言板</a></li>
	                <li><a href="<%=path %>/Control/Logout.jsp">退出系统</a></li>
	        	</ul>
	       </li>	        	        	         
	         <%} %>
        </ul>       
       	<div class="content">
       	
       	
<%--               	<jsp:include page="Calendar.jsp" flush="true" /> --%>
				<iframe width="99%" height="100%" name="mainFrame" frameborder="0" >        
				
				    	<p>这是测试2</p>
                </iframe>
                
        </div>
   </div>       
<div class="footer">
   			<div class="footer_left">
   				<span class="footer_left_sp"><img class="footer_logo" src="<%=path %>/Images/logo.png"/>停车场管理系统</span>
   			<p class="footer_cop"><img alt="" class="footer_copyright" src="<%=path %>/Images/版权.png">2021-2022停车场管理系统</p>
   			<p class="footer_ZL">商丘工学院孙延宇<img alt="" class="footer_zhuanli" src="<%=path %>/Images/专利.png"></p>
   			</div> 
   			
   			  
   			<div class="footer_right">
    					<p class="_h3" id="_h3">联系方式</p>   					
   						<a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=j-y9vbm3vr_5ubu8z_ng9_Lu5uOh7ODi" style="text-decoration:none;">
   						<img class="email" src="<%=path %>/Images/邮箱.png"/>
   						</a>   					
   					<img class="email" src="<%=path %>/Images/分隔.png"/>
   					
   						<a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=j-y9vbm3vr_5ubu8z_ng9_Lu5uOh7ODi" style="text-decoration:none;">
   						<img class="email" src="<%=path %>/Images/微信.png"/>
   						</a>   				
   					<img class="email" src="<%=path %>/Images/分隔.png"/>   					
   						<a target="_blank" href="https://twitter.com/en/tos" style="text-decoration:none;">
   						<img class="email" src="<%=path %>/Images/推特.png"/>
   						</a>  	
   					<p class="footer_AD"><a href="https://www.sqgxy.edu.cn/"style="text-decoration:none;"><img alt="" class="footer_adress" src="<%=path %>/Images/地址.png"></a>商丘工学院</p>					
 		 	 </div>
   
   			
   			
</div>
   
</body>
</html>