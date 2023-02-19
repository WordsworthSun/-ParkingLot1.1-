<%@page import="java.util.*" errorPage="_Error.jsp"%>
<%@page import="DAL.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();//获取项目名称
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/Style/AddStyle.css"/>
<script type="text/javascript" src="<%=path %>/Script/jquery-1.10.1.js"></script>
</head>
<body>
<p id="title">添加会员卡</p>
		<hr size="1">
	<form action="<%=path %>/CardServlet?type=3" method="post" class="Form">
		<table style=" margin:50px auto;"> 
	            <tbody>
			<tr><td>车位编号：</td><td>
				<select name="seat_id">
					<%
					
					     Seat seat=new Seat();
					     List<Object> list=seat.getNoUseSeat();
					     for(int i=0;i<list.size();i++)
					     {
					      Object[] object=(Object[])list.get(i);
					      out.write("<option value='"+object[0]+"'>"+object[0]+"-"+object[1]+"</option>");
					     }
					     
					 %>
				</select>
			</td></tr>
<!-- 			<tr><td>用户编号：</td><td><input type="text" name="user_id"  required /></td></tr> -->
			<tr><td>用户编号：</td><td>
				<select name="user_id">
					<%
					     User user=new User();
					     List<Object> Userlist=user.getEntity();
					     for(int i=0;i<Userlist.size();i++)
					     {
					      Object[] object=(Object[])Userlist.get(i);
					      out.write("<option value='"+object[0]+"'>"+object[0]+"-"+object[1]+"</option>");
					     }
					     
					 %>
				</select>	
	            </tbody>   	            
	            <tfoot>
	                <tr><td>
	                <input type="submit" value="确定" id="btnSure"/></td><td><input type="reset" value="取消" id="btnCancel"/>
	                </td></tr>
	            </tfoot> 
	        </table>
       </form>
       
       
       <%
       		list.clear();
			list=null;
       		Userlist.clear();
			Userlist=null;
			
        %>
</body>
</html>
