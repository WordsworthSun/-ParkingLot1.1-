<%@page import="DAL.*" errorPage="_Error.jsp"%>
<%@page import="java.util.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				<th>临时编号</th>
				<th>临时IC卡号</th>
				<th>车牌号码</th>
				<th>入场日期</th>
				<th>出场日期</th>
				<th>停车费用</th>
				
			</tr>
		</thead>

		<tbody>
			<% 
	            response.setCharacterEncoding("UTF-8");//设置输出数据的编码格式
				request.setCharacterEncoding("UTF-8");//设置获取数据的编码格式
				List<Object> list=(List<Object>)request.getAttribute("list");//获取servlet端转发的list数据列表				
                		if(list!=null) //循环数据列表，生成表格行
                		{
                			for(int i=0;i<list.size();i++)
	                		{
	               				Object[] obj;
	               				obj=(Object[])list.get(i);
	               				String result=obj[5].toString().equals("1111-11-11")?"未 出":obj[4].toString();
	               				String result2=obj[6].toString().equals("11:11:11")?"场":obj[5].toString();              		                		
	              				/* out.print("<tr><td align='center'>"+obj[0]+"</td><td align='center'>"+obj[1]+"</td><td align='center'>"+obj[2]+"</td><td align='center'>"+obj[3]+" "+obj[4]+"</td><td align='center'>"+obj[5]+" "+obj[6]+"</td><td>"+(obj[7].toString().equals("0.0")?"待结算":obj[7])+"</td><td><a onclick='return confirm(\"是否打印？\")'>打印</a></td></tr>"); */
	              				/*out.print("<tr><td align='center'>"+obj[0]+"</td><td align='center'>"+obj[1]+"</td><td align='center'>"+obj[2]+"</td><td align='center'>"+obj[3]+" "+obj[4]+"</td><td align='center'>"+obj[5]+" "+obj[6]+"</td><td>"+ obj[7] +"</td></tr>");*/
	                		out.print("<tr><td align='center'>"+obj[0]+"</td><td align='center'>"+obj[1]+"</td><td align='center'>"+obj[2]+"</td><td align='center'>"+obj[3]+" "+obj[4]+"</td><td align='center'>"+result+" "+result2+"</td><td align='center'>"+(obj[7].toString().equals("0.0")?"待结算":obj[7])+"</td></tr>");
                		}
	               				
	               				
	               				
// 	               				String outDate=obj[5]+" "+obj[6];
// 	               				if(obj[7].toString().equals("0.0"))
// 	               				{
// 	               					            out.print("<tr><td>"+obj[0]+"</td><td>"+obj[1]+"</td><td>"+obj[2]+"</td><td>"+obj[3]+" "+obj[4]+"</td><td>"+(outDate.equals("null null")?"未出场":outDate)+"</td><td>"+(obj[7].toString().equals("0.0")?"待结算":obj[7])+"</td><td></tr>");
// 	               				}
// 	               				else{
// 	              	       	               		out.print("<tr><td>"+obj[0]+"</td><td>"+obj[1]+"</td><td>"+obj[2]+"</td><td>"+obj[3]+" "+obj[4]+"</td><td>"+(outDate.equals("null null")?"未出场":outDate)+"</td><td>"+(obj[7].toString().equals("0.0")?"待结算":obj[7])+"</td><td><a onclick='return confirm(\"是否打印？\")'>打印</a></td></tr>");
// 	         	               		}
// 	                		}
                		}

	                %>
		</tbody>


	</table>
</div>
</body>
</html>
