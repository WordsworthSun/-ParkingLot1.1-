<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   
<meta charset="utf-8">
<meta name="description" content="Html日历插件" />
<link type="text/css" href="<%=path %>/Style/Calendar.css" rel="stylesheet" />
<script type="text/javascript" src="Script/jquery-1.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/Script/Calendar.js"></script>
<title>日历插件</title>

</head>
<body>

<div id="CM">
  <div id="title"> 
  	<a class="selectBtn month" href="javascript:" onClick="CalendarHandler.CalculateLastMonthDays();"><</a>
  	<a class="selectBtn selectYear" href="javascript:" onClick="CalendarHandler.CreateSelectYear(CalendarHandler.showYearStart);">1998年</a>
  	<a class="selectBtn selectMonth" onClick="CalendarHandler.CreateSelectMonth()">9月</a> 
  	<a class="selectBtn nextMonth" href="javascript:" onClick="CalendarHandler.CalculateNextMonthDays();">></a>
  	<a class="selectBtn currentDay" href="javascript:" onClick="CalendarHandler.CreateCurrentCalendar(0,0,0);">今天</a>
  </div>
  <div id="context">
    <div class="week">
      <h3> 一 </h3>
      <h3> 二 </h3>
      <h3> 三 </h3>
      <h3> 四 </h3>
      <h3> 五 </h3>
      <h3> 六 </h3>
      <h3> 日 </h3>
    </div>
    <div id="center">
        <div id="centerMain">
        	<div id="selectYearDiv"></div>
        		 <div id="centerCalendarMain">
          			<div id="Container"></div>
       			 </div>
       	 	<div id="selectMonthDiv"></div>
        </div>
    </div>
    <div id="foots"><a id="footNow">06:07:30</a></div>
  </div>
</div>


</body>
</html>
