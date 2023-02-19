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
  <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
  <div id="left">
  <div id="main" style="width: 600px;height:400px;"></div>
 <% 
  response.setCharacterEncoding("UTF-8");//设置输出数据的编码格式
  request.setCharacterEncoding("UTF-8");//设置获取数据的编码格式
  List<Object> CardList=(List<Object>)request.getAttribute("CardList");//获取servlet端转发的list数据列表	
  ArrayList<String> area= new ArrayList<String>();
  for(int i=0;i<CardList.size();i++)
	                		{
	               				Object[] obj;
	               				obj=(Object[])CardList.get(i); 		
	               				String str0=obj[4].toString();	
	               				area.add(str0);	
 								}
 								ArrayList<String> area_a= new ArrayList<String>();//存放地址
  								ArrayList<Integer> area_num= new ArrayList<Integer>();	//存放地址重复的数量									 
 								Map<String,Integer> map = new HashMap<String,Integer>();																						
 								Set uniqueSet = new HashSet(area);//area集合内存储的是地址
       								 for (Object temp : uniqueSet) 
       								 {       								
//            							  int i=Collections.frequency(area, temp);         							 
//            							  String str01=temp.toString();          							      						  
           							  map.put(temp.toString(), Collections.frequency(area, temp)); //area为地址也是键值，temp为数目也是value         							 
 									} 									
 									for(Map.Entry<String, Integer> entry:map.entrySet())
 									{																		    																			
										area_a.add(entry.getKey());//地址
										area_num.add(entry.getValue());//数目
									}																								
 								request.setAttribute("area_num", area_num);
 								request.setAttribute("area_a", area_a);															
 %>
 <script type="text/javascript">    
      var myChart = echarts.init(document.getElementById('main'));
      var arr = new Array();
      var index = 0;
      <c:forEach items="${requestScope.area_num}" var="num">
          arr[index++] = ${num};
      </c:forEach>
      // 指定图表的配置项和数据
      var option = {
          title: {
              text: '用户住址统计'
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
                      <c:forEach items="${requestScope.area_a}" var="a">
                      ["${a}"],
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
                  name:'地点',
                  type:'bar',
                  data: arr
              }
          ]
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
  </script>
 <%
 
         area_num.clear();       
         area_num = null;	
         area_a.clear();       
       	 area_a = null;
         area.clear();       
       	 area = null;
         CardList.clear();
		 CardList= null;
		 map.clear();
		 map=null;
  %>
 
 
 
 
 

   </div>   
    
  			<div id="right">
  				
				     <iframe src="Calendar.jsp" height="100%"  width="100%"  frameborder="0"  > 
    
				     </iframe> 
 			 </div> 
    
    
    
    
  </body>
</html>
