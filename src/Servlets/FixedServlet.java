package Servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.*;

public class FixedServlet extends HttpServlet {
	HttpServletRequest request;
	HttpServletResponse response;
	DAL.Fixed fixed=new DAL.Fixed();
			//通过表单get方式传值 将进入doGet函数（method="get"）
			public void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
					this.response=response;
					this.request=request;
					int handleType=Integer.parseInt(request.getParameter("type").toString());
					switch (handleType) {
					case 1://类型1代表删除表中的数据
						deleteEntity();
						break;
					case 4://类型4代表获取表中信息
						getEntity();
						break;
					case 5://类型5代表根据查询条件获取表中信息
						getEntityByWhere();
						break;
					case 6://类型6代表管理员获取未出场车辆
						getNoOut();
						break;
					case 7://类型7代表获取表中信息
						getEntity_CUS();
						break;
					case 8://类型8代表根据查询条件获取表中信息
						getEntityByWhere_CUS();						
						break;
					case 10://类型10代表更新车辆出场
						setOut();
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
						updateEntity();
						break;
					case 3://类型3代表向表中添加数据
						insertEntity();
						break;
					default:
						break;
					}
			}

//删除数据操作type=1
			private void deleteEntity() throws IOException
			{
				String fixed_id=request.getParameter("fixed_id");//获取前台通过get方式传过来的JId
				fixed.deleteEntity(fixed_id);//执行删除操作
				response.sendRedirect("/PARK1.1/FixedServlet?type=4");//删除成功后跳转至管理页面
			}
			
//车辆出场更新操作type=10
			private void setOut() throws IOException
			{
				String fixed_id=new String(request.getParameter("fixed_id").getBytes("ISO8859_1"),"UTF-8");
				SimpleDateFormat dateFormat =new    SimpleDateFormat("yyyy-MM-dd"); 
			    String out_date=dateFormat.format(new Date());
			    SimpleDateFormat timeFormat =new    SimpleDateFormat("HH:mm:ss"); 
			    String out_time=timeFormat.format(new Date());		    
			    List<Object>FixedList=fixed.getEntity_fixed_id(fixed_id);//获取数据列表
			    String card_id="0";
			    for(int i=0;i<FixedList.size();i++)
         		{
        				Object[] obj;
        				obj=(Object[])FixedList.get(i);
        				card_id=obj[1].toString();//通过fixed_id获取card_id
         		}
			    List<Object> CardList=fixed.getEntity_card_id(card_id);//获取数据列表
				String seat_id="";
//				ArrayList<String> list_seat_id= new ArrayList<String>();
				 for(int i=0;i<CardList.size();i++)
         		{
        				Object[] obj;
        				obj=(Object[])CardList.get(i);
        				seat_id=obj[1].toString();//通过card_id获取seat_id
        				//list_seat_id.add(obj[5].toString());				
				}			    		    
			    if(fixed.setOut(fixed_id, out_date, out_time)==1)
			    {			    				    	
			    	String seat_state="0";//使seat表内seat_state设为0，状态为									
					fixed.InsertParking(seat_state,seat_id);//通过根据seat_id寻找到车主的seat_state将其车位状态改为占用1	
			    	response.sendRedirect("/PARK1.1/FixedServlet?type=6");
			    }
			}

//更新数据操作type=2
			private void updateEntity() throws UnsupportedEncodingException
			{
				String fixed_id=new String(request.getParameter("fixed_id").getBytes("ISO8859_1"),"UTF-8");
				String card_id=new String(request.getParameter("card_id").getBytes("ISO8859_1"),"UTF-8");
				String entry_date=new String(request.getParameter("entry_date").getBytes("ISO8859_1"),"UTF-8");
				String entry_time=new String(request.getParameter("entry_time").getBytes("ISO8859_1"),"UTF-8");
				String out_date=new String(request.getParameter("out_date").getBytes("ISO8859_1"),"UTF-8");
				String out_time=new String(request.getParameter("out_time").getBytes("ISO8859_1"),"UTF-8");
				if(fixed.updateEntity(fixed_id,card_id,entry_date,entry_time,out_date,out_time)==1)
				{
					try {
						response.sendRedirect("/PARK1.1/FixedServlet?type=4");//成功更新数据后跳转至FixedMsg.jsp页面
					} catch (IOException e) {
						e.printStackTrace();//异常处理
					}
				}
			}

//插入数据操作type=3
			private void insertEntity() throws UnsupportedEncodingException, IOException
			{
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();				
			    SimpleDateFormat dateFormat =new    SimpleDateFormat("yyyyMMddHHmmss"); 
			    String fixed_id=dateFormat.format(new Date());//当地时间
			    
				String card_id=new String(request.getParameter("card_id").getBytes("ISO8859_1"),"UTF-8");//前台传入的数据
				String seat_id="";
				List<Object> CardList=fixed.getEntity_card_id(card_id);//获取数据列表						
				 for(int i=0;i<CardList.size();i++)
         		{
        				Object[] obj;
        				obj=(Object[])CardList.get(i);
        				seat_id=obj[1].toString();
        				        				
						}
				SimpleDateFormat dFormat =new    SimpleDateFormat("yyyy-MM-dd"); 
			    String entry_date=dFormat.format(new Date());
			    SimpleDateFormat tFormat =new    SimpleDateFormat("HH:mm:ss"); 
			    String entry_time=tFormat.format(new Date());
				String out_date="1111-11-11";
				String out_time="11:11:11";				
				String car_num=fixed.getCarNum(card_id);
				System.out.println("这是insertEntity01");
				
				if(fixed.checkExist(fixed_id))//如果fixed表中存在card_id
				{				
			                                int check_seat_state=2;
											List<Object> SeatList=fixed.getEntitySeat_seat_state(seat_id);//获取数据列表
											for(int i=0;i<SeatList.size();i++)
												{
													Object[] obj;
													obj=(Object[])SeatList.get(i);
													check_seat_state=Integer.parseInt(obj[3].toString());						
												}
											
					if(check_seat_state==0) {									
														if(fixed.insertEntity(fixed_id,card_id,entry_date,entry_time,out_date,out_time,car_num)==1)
															{	String seat_state="1";//使seat表内seat_state设为1，状态为占用										
																fixed.InsertParking(seat_state,seat_id);						
																out.write("<script>alert('入场成功，车辆已入场！'); location.href = '/PARK1.1/FixedServlet?type=6';</script>");												
															}else {
																	out.write("<script>alert('数据添失败！'); location.href = '/PARK1.1/FixedServlet?type=6';</script>");
																 }
														
								
												}else {
														out.write("<script>alert('车位已占用，入库失败！'); location.href = '/PARK1.1/FixedServlet?type=6';</script>");
														}
					SeatList.clear();//可能会有问题
					SeatList=null;//可能会有问题
								}else {
									
											if(fixed.insertEntity(fixed_id,card_id,entry_date,entry_time,out_date,out_time,car_num)==1)
											{						
												String seat_state="1";//使seat表内seat_state设为1，状态为占用										
												fixed.InsertParking(seat_state,seat_id);						
												out.write("<script>alert('入场成功，车辆已入场！'); location.href = '/PARK1.1/FixedServlet?type=6';</script>");												
											}
											else {		
												out.write("<script>alert('数据添失败！'); location.href = '/PARK1.1/FixedServlet?type=6';</script>");
											}
			}
				
				
				CardList.clear();
				CardList=null;
				

	}
					
					
			

//获取对象所有数据列表type=4
			private void getEntity() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//获取跳转的页面号
				int totalPage=Integer.parseInt(fixed.getPageCount().toString()) ;//获取分页总数
				List<Object> list=fixed.getEntity(page);//获取数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("totalPage",totalPage );//将totalPage存放到request对象中，用于转发给前台页面使用
				request.getRequestDispatcher("/Control/FixedMsg.jsp").forward(request, response);//请求转发
			}
			
//获取未出场的车辆type=6
			private void getNoOut() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//获取跳转的页面号
				int totalPage=Integer.parseInt(fixed.getPageCount().toString()) ;//获取分页总数
				List<Object> list=fixed.getNoOut(page);//获取数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("totalPage",totalPage );//将totalPage存放到request对象中，用于转发给前台页面使用
				request.getRequestDispatcher("/Control/FixedOut.jsp").forward(request, response);//请求转发
			}
			
//根据查询条件获取对象所有数据列表type=5
			private void getEntityByWhere() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				String condition=request.getParameter("condition");//获取查询字段的名称
				//String value=new String(request.getParameter("value").getBytes("ISO8859_1"),"UTF-8");//获取查询的值
				String value = request.getParameter("value");
				String where=condition+"=\""+value+"\"";//拼接查询字符串
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));//获取要跳转的页面号
				int wherePage=Integer.parseInt(fixed.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
				List<Object> list=fixed.getEntityByWhere(where, page);//获取查询后的数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("wherePage",wherePage );
				request.setAttribute("condition",condition);
				request.setAttribute("value",value);
				request.getRequestDispatcher("/Control/FixedMsg.jsp").forward(request, response);
			}
			
			
///////////////////////////////////////////////////////////////		
			//type=7
			private void getEntity_CUS() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				HttpSession session = request.getSession(); 
				String car_num=(String)session.getAttribute("car_num");//获取用户car		
				//List<Object> list=fixed.getEntity_CUS(car_num);//获取数据列表
				List<Object> list=fixed.getEntityById_CUS(car_num);//获取数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.getRequestDispatcher("/Customer/FixedMsg.jsp").forward(request, response);//请求转发
			}			
			
			
			//type=8
			private void getEntityByWhere_CUS() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				String condition=request.getParameter("condition");//获取查询字段的名称
				//String value=new String(request.getParameter("value").getBytes("ISO8859_1"),"UTF-8");//获取查询的值
				String value = request.getParameter("value");
				String where=condition+"=\""+value+"\"";//拼接查询字符串
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));//获取要跳转的页面号
				int wherePage=Integer.parseInt(fixed.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
				List<Object> list=fixed.getEntityByWhere(where, page);//获取查询后的数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("wherePage",wherePage );
				request.setAttribute("condition",condition);
				request.setAttribute("value",value);
				request.getRequestDispatcher("/Customer/FixedMsg.jsp").forward(request, response);
			}
				
			
			
			
			
							
}
