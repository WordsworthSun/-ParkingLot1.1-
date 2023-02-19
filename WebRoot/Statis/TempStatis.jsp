<%@page import="DAL.*" errorPage="_Error.jsp"%>
<%@page import="java.util.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path=request.getContextPath();//获取项目名称
 %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=path %>/Style/Statis.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/Script/jquery-1.10.1.js"></script>
<script type="text/javascript" src="<%=path %>/Script/echarts.js"></script>
</head>
<body>
  <%
  response.setCharacterEncoding("UTF-8");//设置输出数据的编码格式
  request.setCharacterEncoding("UTF-8");//设置获取数据的编码格式
  List<Object> list=(List<Object>)request.getAttribute("list");//获取servlet端转发的list数据列表  
  ArrayList<Float> money01= new ArrayList<Float>();
  ArrayList<String> date01= new ArrayList<String>();  
  List<Statis> MoneyList = new ArrayList<Statis>();
  for(int i=0;i<list.size();i++)
 	                		{
 	               				Object[] obj;
 	               				obj=(Object[])list.get(i);	               				
 	               				String date=obj[5].toString();//日期
 	               				float money=Float.parseFloat(obj[7].toString());//费用 	               				
								MoneyList.add(new Statis(date,money));	               			
	               				}
   List<Statis> money = new ArrayList<Statis>();		
	   for(int i = 0; i < MoneyList.size(); i++){
			String tmp = MoneyList.get(i).getDate();
			float   k  = MoneyList.get(i).getMoney();			
			for(int j = i+1; j < MoneyList.size();j++){
				if(tmp.equals(MoneyList.get(j).getDate()))
				{
					k += MoneyList.get(j).getMoney();							
					MoneyList.remove(MoneyList.get(j));//遍历完成后，要删除MoneyList的name相同的数据							
					j--;//remove一个元素时，要把遍历的指针减一
				}
			}		
				
			money.add(new Statis(MoneyList.get(i).getDate(),k));
		}		
		for(int i = 0;i<money.size();i++){
		money01.add(money.get(i).getMoney());
	    date01.add(money.get(i).getDate());	 
		}               				  
  request.setAttribute("money01", money01);
  request.setAttribute("date01", date01);
   %>
  
   <div id="left">
  <div id="main" style="width: 600px;height:400px;"></div> 
 <script type="text/javascript">
     
      var myChart = echarts.init(document.getElementById('main'));
      var arr = new Array();
      var index = 0;
      <c:forEach items="${requestScope.money01}" var="tempmoney">
          arr[index++] = ${tempmoney};
      </c:forEach>

      // 指定图表的配置项和数据
      var option = {
          title: {
              text: '临时用户收费统计'
          },
          tooltip: {
              show: true
          },
          legend: {
              data:['收费']
          },
          xAxis : [
              {
                  type : 'category',
                  data : [
                      <c:forEach items="${requestScope.date01}" var="tempdate">
                      ["${tempdate}"],
                      </c:forEach>
                  ]
              }
          ],
          yAxis : [
              {
                  type : 'value'
              }
          ],
          series : [
              {
                  name:'日期',
                  type:'bar',
                  data: arr
              }
          ]
      };

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
  </script>
  <%
  money01.clear();
  money01=null;
  date01.clear();
  date01=null;
  money.clear();
  money=null;
  MoneyList.clear();
  MoneyList=null;
  list.clear();
  list=null;
   %>
  
  
</div>      
  			<div id="right">  				
				     <iframe src="Calendar.jsp" height="100%"  width="100%"  frameborder="0"  >    
				     </iframe> 
 			 </div>
 			 
 			 
 			 
 			 
  </body>
</html>
