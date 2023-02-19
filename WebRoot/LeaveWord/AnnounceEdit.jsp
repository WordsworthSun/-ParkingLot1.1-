<%@page import="DAL.*" errorPage="_Error.jsp"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();//获取项目名称
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../Style/EditStyle.css"/>
<script type="text/javascript" src="<%=path %>/Script/jquery-1.10.1.js"></script>
<script type="text/javascript">
	$(function()
	{		
		//取消按钮点击事件
		$("#btnCancel").click(function()
		{
			location.href="<%=path %>/MessageServlet?type=4";//点击后跳转至MessageServlet Servlet页面
		});
	});
</script>

</head>
<body>
	<%
			response.setCharacterEncoding("UTF-8");//设置输出编码格式
			String message_id=request.getParameter("message_id").toString();//获取url传过来的real_name
	        DAL.Messages messages=new DAL.Messages();//实例化Mesages对象
	        List<Object> MessagesList=messages.getEntityById(message_id);//根据ID获取messages数据
	        Object[] obj=(Object[])MessagesList.get(0);//将List数据转换成Object[]
	        
	 %>
	 <form action="<%=path %>/MessageServlet?type=2" method="post">
		<table style=" margin:50px auto;">
	      <tbody>
			 <tr><td>姓名：</td><td><input type="text" name="real_name" value="<%=obj[0] %>" readonly /></td></tr>
			 <tr><td>标题：</td><td><input type="text" name="title" value="<%=obj[1] %>" readonly /></td></tr>							 
			 <tr><td>内容：</td><td><input type="text" name="context" value="<%=obj[2] %>" /></td></tr>			 				  			 
			 <tr><td>时间：</td><td><input type="text" name="time" value="<%=obj[3] %>" required /></td></tr>
			 <tr><td>编号：</td><td><input type="text" name="message_id" value="<%=obj[4] %>" required /></td></tr>
		   </tbody>   
	            
	            <tfoot>
	                <tr><td><input type="submit" value="确定" id="btnSure"/></td><td><input type="button" value="取消" id="btnCancel"/></td></tr>
	            </tfoot> 
	        </table>
     </form>
</body>
</html>