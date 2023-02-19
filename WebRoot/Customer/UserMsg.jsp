<%@page import="DAL.*" errorPage="_Error.jsp"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();//获取项目名称
 %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path %>/Style/MsgStyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/Script/jquery-1.10.1.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="div_container">
	

	<table>
		<thead>
			<tr>
				<th>用户编号</th>
				<th>用户名称</th>
				<th>用户密码</th>
				<th>用户邮箱</th>				
				<th>真实姓名</th>
				<th>联系方式</th>
				<th>用户性别</th>
				<th>出生日期</th>
				<th>车辆号码</th>
				<th>角色编号</th>
				<th>用户住址</th>
				<th>操作</th>
			</tr>
		</thead>

		<tbody>
			<% 
	            response.setCharacterEncoding("UTF-8");//设置输出数据的编码格式
				request.setCharacterEncoding("UTF-8");//设置获取数据的编码格式

				List<Object> list=(List<Object>)request.getAttribute("list");//获取servlet端转发的list数据列表
				out.write("<input type='hidden' name='hcondition' value='"+(request.getAttribute("condition")!=null?request.getAttribute("condition").toString():"")+"' />");
				out.write("<input type='hidden' name='hvalue' value='"+(request.getAttribute("value")!=null?request.getAttribute("value").toString():"")+"' />");
                		if(list!=null) //循环数据列表，生成表格行
                		{
                			for(int i=0;i<list.size();i++)
	                		{
	               				Object[] obj;
	               				obj=(Object[])list.get(i);
	               				out.print("<tr><td align='center'>"+obj[0]+"</td><td align='center'>"+obj[1]+"</td><td align='center'>"+obj[2]+"</td><td align='center'>"+obj[3]+"</td><td align='center'>"+obj[4]+"</td><td align='center'>"+obj[5]+"</td><td align='center'>"+obj[6]+"</td><td align='center'>"+obj[7]+"</td><td align='center'>"+obj[8]+"</td><td align='center'>"+obj[9]+"</td><td align='center'>"+obj[10]+"</td><td align='center'><a href='"+path+"/Customer/UserEdit.jsp?user_id="+obj[0]+"' class='a_edit'>编辑</a></td></tr>");
	                		}
                		}

	                %>
		</tbody>
</div>
</body>
</html>
