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
<p id="title">临时车辆入场</p>
		<hr size="1">
	<form action="<%=path %>/TempServlet?type=3" method="post" class="Form">
		<table style=" margin:50px auto;"> 
	            <tbody>
			<tr><td>临时卡号：</td><td><input type="text" name="card_id"  /></td></tr>		
			<tr><td>停车区域：</td><td><select name="seat_id">			
					<%
					     Seat seat=new Seat();
					     List<Object> list=seat.getNoUseSeat();//返回未被固定车主绑定的车位即临时车位
					     ArrayList<String> seat_id_list= new ArrayList<String>();//0
					     ArrayList<String> seat_num_list= new ArrayList<String>();//1
					     for(int i=0;i<list.size();i++)					     
					     {	
					     	 Object[] object=(Object[])list.get(i);
					     	 String seat_state=object[3].toString();
					     	 if(seat_state.equals("1")){
					     	 					     	 
					     	 }else{
					     	 seat_id_list.add(object[0].toString());
					     	 seat_num_list.add(object[1].toString());
					     	 }					     	 
					      }					     					     						     	
					     for(int i=0;i<seat_id_list.size();i++)					     
					     {	
					     String seat_num=seat_num_list.get(i);
					     	String seat_id	=seat_id_list.get(i);	     
					      out.write("<option value='"+ seat_id+"'>"+ seat_id+"-"+seat_num+"</option>");    
					     }						     
					      				     					     					     
					 %>
					 
				</td></tr> 
			<tr><td>车牌号码：</td><td><input type="text" name="car_num" required  /></td></tr>
	            </tbody>   
	            
	            <tfoot>
	                <tr><td><input type="submit" value="确定" id="btnSure"/></td><td><input type="reset" value="取消" id="btnCancel"/></td></tr>
	            </tfoot> 
	        </table>
       </form>
</body>
</html>
