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
<script>
	$(function()
	{
		$(".btnGo").click(function()
		{
			if($("[name=htype]").val()=="6")
			{
				location.href="<%=path %>/SeatServlet?type=6&page="+($(".txtPage").val()==""?1:$(".txtPage").val());
			}
			else
			{
				location.href="<%=path %>/SeatServlet?type=7&page="+($(".txtPage").val()==""?1:$(".txtPage").val())+"&condition="+$("[name=hcondition]").val()+"&value="+$("[name=hvalue]").val();
			}		
		});
	});
</script>
</head>
<body>
<div class="div_container">
	
	
	<!-- searchDiv End -->

	<table>
		<thead>
			<tr>
				<th>车位ID</th>
				<th>车位编号</th>
				<th>所属区域</th>
				<th>车位状态</th>
				<th>车位备注</th>

				
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
	               				String result=obj[3].toString().equals("0")?"闲置":"占用";
	               				out.print("<tr><td align='center'>"+obj[0]+"</td><td align='center'>"+obj[1]+"</td><td align='center'>"+obj[2]+"</td><td align='center'>"+result+"</td><td align='center'>"+obj[4]+"</td></tr>");
	                		}
                		}

	                %>
		</tbody>

		<tfoot>
        
            </tfoot>

	</table>
</div>
</body>
</html>
