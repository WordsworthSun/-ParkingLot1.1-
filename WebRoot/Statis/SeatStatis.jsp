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
  List<Object> SeatList=(List<Object>)request.getAttribute("SeatList");//获取servlet端转发的list数据列表	
  ArrayList<String> Loca= new ArrayList<String>();
  ArrayList<String> tag= new ArrayList<String>();//存储临时和固定字段
  ArrayList<String> state= new ArrayList<String>();//存储状态0为闲置1为占用
  for(int i=0;i<SeatList.size();i++)
	                		{
	               				Object[] obj;
	               				obj=(Object[])SeatList.get(i); 		
	               				String str0=obj[2].toString();
	               				String str1=obj[4].toString();
	               				String str2=obj[3].toString();
	               				Loca.add(str0);
	               				tag.add(str1);
	               				state.add(str2);
 								}
  ArrayList<Integer> Loca_num= new ArrayList<Integer>();	
  ArrayList<Integer> tag_num= new ArrayList<Integer>();//存储相同的数据	
    ArrayList<Integer> state_num= new ArrayList<Integer>();//存储相同的数据							
 								int East=Collections.frequency(Loca, "东区");
 								int South=Collections.frequency(Loca, "南区");
 								int West=Collections.frequency(Loca, "西区");
 								int North=Collections.frequency(Loca, "北区");
 								 								
 								Loca_num.add(0,East);
 								Loca_num.add(1,South);
 								Loca_num.add(2,West);
 								Loca_num.add(3,North);
 																
 								int fixed=Collections.frequency(tag, "固定");
 								int temp=Collections.frequency(tag, "临时"); 								
 								tag_num.add(0,fixed);
 								tag_num.add(1,temp);	
 									
 								int idle=Collections.frequency(state, "0");
 								int Occupy=Collections.frequency(state, "1"); 		
 									
 								state_num.add(0,idle);
 								state_num.add(1,Occupy);	
 													
 								request.setAttribute("tag_num", tag_num);		
								request.setAttribute("Loca_num", Loca_num);	
								request.setAttribute("state_num", state_num);
								
								Loca.clear();
								Loca=null;
	               				tag.clear();
	               				tag=null;
	               				state.clear();		
	               				state=null;							
 								%>
<div id="left"> 
  	<div id="main" ></div> 
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        var option = {
  title: {
    text: '车位统计',
    subtext: '车位位置',
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
        { value: <%=Loca_num.get(0)%>, name: '东区' },
        { value: <%=Loca_num.get(1)%>, name: '南区' },
        { value: <%=Loca_num.get(2)%>, name: '西区' },
        { value: <%=Loca_num.get(3)%>, name: '北区' }
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
<div id="main02" ></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main02'));
        // 指定图表的配置项和数据
        var option02 = {
  title: {
    text: '车位统计',
    subtext: '车位类型',
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
        { value: <%=tag_num.get(0)%>, name: '固定' },
        { value: <%=tag_num.get(1)%>, name: '临时'  },       
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
        myChart.setOption(option02);
    </script>  
    
<div id="main03" ></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main03'));
        // 指定图表的配置项和数据
        var option03 = {
  title: {
    text: '车位统计',
    subtext: '车位状态',
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
        { value: <%=state_num.get(0)%>, name: '闲置' },
        { value: <%=state_num.get(1)%>, name: '占用'  },       
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
        myChart.setOption(option03);
    </script>    
       
 <%
        tag_num.clear();       
        tag_num = null;		
		Loca_num.clear();
		Loca_num = null;		        
		state_num.clear();		
        state_num = null;	
  %>   
    
    
    
         
</div>      
  			<div id="right"> 				
				     <iframe src="Calendar.jsp" height="100%"  width="100%"  frameborder="0"  >     
				     </iframe> 
 			 </div>
</body>
</html>
