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
<link href="<%=path %>/Style/Announce.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/Script/jquery-1.10.1.js"></script>
<title>Insert title here</title>
<script>
	$(function()
	{
		$(".btnGo").click(function()
		{
			if($("[name=htype]").val()=="7")
			{
				location.href="<%=path %>/MessageServlet?type=7&page="+($(".txtPage").val()==""?1:$(".txtPage").val());
			}
			else
			{
				location.href="<%=path %>/MessageServlet?type=8&page="+($(".txtPage").val()==""?1:$(".txtPage").val())+"&condition="+$("[name=hcondition]").val()+"&value="+$("[name=hvalue]").val();
			}		
		});
	});
</script>
</head>
<body>
<p id="title">公告</p>
		<hr size="1">
<div class="div_container">
	<div class="searchDiv">
		<form action="<%=path %>/MessageServlet" method="get">
			<span>查询条件：</span>
			<select name="condition">
			 	<option value='real_name'>姓名</option>
				<option value='title'>标题</option>
				<option value='context'>内容</option>
				<option value='time'>时间</option>
				<option value='message_id'>编号</option>


			</select>
			 	
			<span>查询值：</span>
			<input type="hidden" name="type" value="8" />
			<input type="text" name="value" />
			<input type="submit" value="查询 "/>
		</form>
	</div>
	<!-- searchDiv End -->

	<table>
		<thead>
			<tr>
				<th>姓名</th>
				<th>标题</th>
				<th>内容</th>
				<th>时间</th>
				<th>编号</th>
				
				
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
	               				out.print("<tr><td align='center'>"+obj[0]+"</td><td align='center'>"+obj[1]+"</td><td align='center'>"+obj[2]+"</td><td align='center'>"+obj[3]+"</td><td align='center'>"+obj[4]+"</td><td> </tr>");
	                		}
                		}

	                %>
		</tbody>

		<tfoot>
            		<tr><td colspan="7">
            				<div>
						<%
              						int totalPage; 
            						if(request.getAttribute("totalPage")!=null){ 
             							totalPage=Integer.parseInt(request.getAttribute("totalPage").toString()); 
              							out.write("<input type='hidden' name='htype' value='"+7+"' />"); 
              						} 
              						else{ 
              							totalPage=Integer.parseInt(request.getAttribute("wherePage").toString()); 
             							out.write("<input type='hidden' name='htype' value='"+8+"' />"); 
              						} 
            					 %>
            					<span>共<strong><%=totalPage %></strong>页  </span>
            					<span>跳转至</span><input type="text" class="txtPage" /><input type="button" value="转" class="btnGo" />
            				</div>
            			</td>
            			
			</tr>
            </tfoot>
            

	</table>
	
</div>
</body>
</html>
