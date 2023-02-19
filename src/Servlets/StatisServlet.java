package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StatisServlet extends HttpServlet {
	HttpServletRequest request;
	HttpServletResponse response;
	DAL.Statis statis=new DAL.Statis();
			//通过表单get方式传值 将进入doGet函数（method="get"）
			public void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
					this.response=response;
					this.request=request;
					int handleType=Integer.parseInt(request.getParameter("type").toString());
					switch (handleType) {
					case 3://类型4代表获取表中信息
						getEntity_User();
						break;
					case 4://类型4代表获取表中信息
						getEntity();
						break;
					case 5://类型4代表获取表中信息
						getEntity_Card();
						break;
					case 6://类型4代表获取表中信息
						getEntity_Seat();
						break;	
					default:
						break;
					}
			}
	
			//通过表单post方式传值 将进入doPost函数（method="post"）
			public void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
					this.request=request;
					this.response=response;
					int handleType=Integer.parseInt(request.getParameter("type").toString());//将前台页面传过来的type类型转化成整型
					switch (handleType) {
					case 2://类型2代表更新表中的数据
						//updateEntity();
						break;
					
					default:
						break;
					}
			}
			
		
/////////////////////type=4		
			private void getEntity() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");						
				List<Object> list=statis.getEntity();//获取数据列表			
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.getRequestDispatcher("/Statis/TempStatis.jsp").forward(request, response);//请求转发
			}
//////////////////////////////////	type=3		
			private void getEntity_User() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");														
				List<Object> UserList=statis.getEntity_User();//获取数据列表			
				request.setAttribute("UserList",UserList);//将数据存放到request对象中，用于转发给前台页面使用					
				request.getRequestDispatcher("/Statis/UserStatis.jsp").forward(request, response);//请求转发
			}
//////////////////////////////////type=5		
			private void getEntity_Card() throws ServletException, IOException
{
				request.setCharacterEncoding("UTF-8");														
				List<Object> CardList=statis.getEntity_Card();//获取数据列表			
				request.setAttribute("CardList",CardList);//将数据存放到request对象中，用于转发给前台页面使用					
				request.getRequestDispatcher("/Statis/CardStatis.jsp").forward(request, response);//请求转发
}			
//////////////////////////////////type=6		
	private void getEntity_Seat() throws ServletException, IOException
{
		request.setCharacterEncoding("UTF-8");														
		List<Object> SeatList=statis.getEntity_Seat();//获取数据列表			
		request.setAttribute("SeatList",SeatList);//将数据存放到request对象中，用于转发给前台页面使用					
		request.getRequestDispatcher("/Statis/SeatStatis.jsp").forward(request, response);//请求转发
}	
				

}
