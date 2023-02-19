<%@page import="DAL.*" errorPage="_Error.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
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
  User user=new User();					
  List<Object> UserList=(List<Object>)request.getAttribute("UserList");//获取servlet端转发的list数据列表	
  ArrayList<String> gender= new ArrayList<String>();
  ArrayList<String> birth= new ArrayList<String>();  
  ArrayList<Integer> ago= new ArrayList<Integer>(); 
  for(int i=0;i<UserList.size();i++)
	                		{
	               				Object[] obj;
	               				obj=(Object[])UserList.get(i); 		
	               				String str0=obj[6].toString();
	               				String str1=obj[7].toString();
	               				gender.add(str0);	               				
	               				birth.add(str1);
 								} 								
 ArrayList<Integer> gender_num= new ArrayList<Integer>();							
 								int male=Collections.frequency(gender, "男");
 								int female=Collections.frequency(gender, "女");
 								gender_num.add(0,male);
 								gender_num.add(1,female);															
 ArrayList<String> birth_a= new ArrayList<String>();//存放地址
 ArrayList<Integer> birth_num= new ArrayList<Integer>();	//存放地址重复的数量									 
 Map<String,Integer> map = new HashMap<String,Integer>();																						
 								Set uniqueSet = new HashSet(birth);//area集合内存储的是地址
       								 for (Object temp : uniqueSet) 
       								 {       								            							    							      						  
           							  map.put(temp.toString(), Collections.frequency(birth, temp)); //area为地址也是键值，temp为数目也是value         							 
 									} 																	
 									for(Map.Entry<String, Integer> entry:map.entrySet())
 									{																		    																			
										birth_a.add(entry.getKey());//出生年月日																				
									}									
									for(int i=0;i<birth_a.size();i++)
	                				{
	                		          String birthday =birth_a.get(i);
	                		          int a = user.getAgeFromBirthTime(birthday);
	                		          ago.add(a);
	                				}	                				
	                	ArrayList<Integer> ago_num= new ArrayList<Integer>();			
	                	ArrayList<String> ago_a= new ArrayList<String>();			
	                	Map<String,Integer> ago_map = new HashMap<String,Integer>();																						
 								Set uniqueSet_ago = new HashSet(ago);//area集合内存储的是地址
       								 for (Object temp : uniqueSet_ago) 
       								 {       								
//            							  int i=Collections.frequency(area, temp);         							 
//            							  String str01=temp.toString();          							      						  
           							 ago_map.put(temp.toString(), Collections.frequency(ago, temp)); //area为地址也是键值，temp为数目也是value         							 
 									} 									 									
 									for(Map.Entry<String, Integer> entry:ago_map.entrySet())
 									{																		    																			
										ago_a.add(entry.getKey());//地址
										ago_num.add(entry.getValue());//数目
									}									
							Map<Integer,Integer> ago_map_sort = new HashMap<Integer,Integer>();	
							for(int i=0;i<ago_num.size();i++){
							ago_map_sort.put(Integer.parseInt(ago_a.get(i)),ago_num.get(i));							
							}									
								ago_a.clear();
								ago_num.clear();	
							for(Map.Entry<String, Integer> entry:ago_map.entrySet())
 									{																		    																			
										ago_a.add(entry.getKey());//地址
										ago_num.add(entry.getValue());//数目
									}																									
 								request.setAttribute("birth_num", birth_num);
 								request.setAttribute("ago_a", ago_a);										 								 							
 								request.setAttribute("ago_num", ago_num);	
%>
 
 <div id="left">
  <div id="main" ></div><!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        var option = {
    title: {
    text: '用户统计',
    subtext: '性别',
    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: 'Access From',
      type: 'pie',
      radius: '50%',
      data: [
        { value: <%=gender_num.get(0)%>, name: '男' },
        { value: <%=gender_num.get(1)%>, name: '女' },
        
      ],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
};
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
 <div id="main02" ></div><!-- 为ECharts准备一个具备大小（宽高）的Dom --> 
  <script type="text/javascript">    
      var myChart = echarts.init(document.getElementById('main02'));
      var arr = new Array();
      var index = 0;
      <c:forEach items="${requestScope.ago_num}" var="num">
          arr[index++] = ${num};
      </c:forEach>
      // 指定图表的配置项和数据
      var option01 = {
          title: {
              text: '用户年龄'
          },
          tooltip: {
              show: true
          },
          legend: {
              data:['年龄']
          },
          xAxis : [
              {
                  type : 'category',
                  data : [
                      <c:forEach items="${requestScope.ago_a}" var="a">
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
                  name:'年龄',
                  type:'bar',
                  data: arr
              }
          ]
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option01);
  </script>
</div>   
    
  			<div id="right">
  				
				     <iframe src="Calendar.jsp" height="100%"  width="100%"  frameborder="0"  > 
    
				     </iframe> 
 			 </div>
 	<%
 					birth_num.clear();
 					birth_num=null;
 					ago_a.clear();
 					ago_a=null;						 								 							
 					ago_num.clear();
 					ago_num=null;
 					ago_map_sort.clear();
 					ago_map_sort=null;	
 					ago_map.clear();
 					ago_map=null;
 					map.clear();
 					map=null;					
 					gender.clear();
					gender=null;
					birth.clear();
  					birth=null;  					
 					ago.clear();
 					ago=null;
 	 %>		 
 			 
 			 
 			 
  </body>
</html>
