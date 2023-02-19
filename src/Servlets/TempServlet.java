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
public class TempServlet extends HttpServlet {
			HttpServletRequest request;
			HttpServletResponse response;
			DAL.Temp temp=new DAL.Temp();	
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
						getEntityByWhere_CUS();
						break;
					case 6://类型6代表获取表中信息
						getEntity_CUS();
						break;
					case 7://类型7代表根据查询条件获取表中信息
						getEntityByWhere();	
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
				String temp_id=request.getParameter("temp_id");//获取前台通过get方式传过来的JId
				String seat_id=new String(request.getParameter("seat_id").getBytes("ISO8859_1"),"UTF-8");
				temp.deleteEntity(temp_id);//执行删除操作
				int seat_state=0;
				temp.Update_seat_state(seat_state,seat_id);//通过根据seat_id寻找到车位的seat_state将其车位状态改为占用1
				response.sendRedirect("/PARK1.1/TempServlet?type=4");//删除成功后跳转至管理页面
			}

//更新数据操作type=2
			private void updateEntity() throws UnsupportedEncodingException
			{												
				String temp_id=new String(request.getParameter("temp_id").getBytes("ISO8859_1"),"UTF-8");
				String card_id=new String(request.getParameter("card_id").getBytes("ISO8859_1"),"UTF-8");
				String car_num=new String(request.getParameter("car_num").getBytes("ISO8859_1"),"UTF-8");
				String seat_id=new String(request.getParameter("seat_id").getBytes("ISO8859_1"),"UTF-8");
				String entry_date=new String(request.getParameter("entry_date").getBytes("ISO8859_1"),"UTF-8");
				String entry_time=new String(request.getParameter("entry_time").getBytes("ISO8859_1"),"UTF-8");
				String out_date=new String(request.getParameter("out_date").getBytes("ISO8859_1"),"UTF-8");
				String out_time=new String(request.getParameter("out_time").getBytes("ISO8859_1"),"UTF-8");
				String temp_money=new String(request.getParameter("temp_money").getBytes("ISO8859_1"),"UTF-8");												
				if(temp.updateEntity(temp_id,card_id,car_num,entry_date,entry_time,out_date,out_time,temp_money,seat_id)==1)
				{
					try {						
						int seat_state=0;
						temp.Update_seat_state(seat_state,seat_id);//通过根据seat_id寻找到车位的seat_state将其车位状态改为占用1								
						response.sendRedirect("/PARK1.1/TempServlet?type=4");//成功更新数据后跳转至TempMsg.jsp页面
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
				SimpleDateFormat dateFormat =new SimpleDateFormat("yyyyMMddHHmmss"); 
			    String temp_id=dateFormat.format(new Date());			    
				String card_id=new String(request.getParameter("card_id").getBytes("ISO8859_1"),"UTF-8");											   			  
				String seat_id=new String(request.getParameter("seat_id").getBytes("ISO8859_1"),"UTF-8");//车位
				String car_num=new String(request.getParameter("car_num").getBytes("ISO8859_1"),"UTF-8");				
				SimpleDateFormat dFormat =new    SimpleDateFormat("yyyy-MM-dd"); 
			    String entry_date=dFormat.format(new Date());			    
			    SimpleDateFormat tFormat =new    SimpleDateFormat("HH:mm:ss"); 
			    String entry_time=tFormat.format(new Date());			    			    
				String out_date="1111-11-11";
				String out_time="11:11:11";
				String temp_money="0";
				
				if(temp.checkExist(card_id))
				{
					String out_date_check="";
					List<Object> TempList=temp.getEntityTemp_card_id(card_id);//获取数据列表
					for(int i=0;i<TempList.size();i++)
						{
							Object[] obj;
							obj=(Object[])TempList.get(i);
							out_date_check=obj[5].toString();	
						}
							if(out_date_check!="1111-11-11") {	
										if(temp.insertEntity(temp_id,card_id,car_num,entry_date,entry_time,out_date,out_time,temp_money,seat_id)==1)
											{	
											int seat_state=1;
											temp.Update_seat_state(seat_state,seat_id);//通过根据seat_id寻找到车主的seat_state将其车位状态改为占用1			
											out.write("<script>alert('数据添加成功！'); location.href = '/PARK1.1/TempServlet?type=4';</script>");
											}else {
													out.write("<script>alert('数据添失败！'); location.href = '/PARK1.1/TempServlet?type=4';</script>");
											}
								
								}else {
										out.write("<script>alert('车辆已入库！'); location.href = '/PARK1.1/FixedServlet?type=6';</script>");
										}	
												
				}else {
					if(temp.insertEntity(temp_id,card_id,car_num,entry_date,entry_time,out_date,out_time,temp_money,seat_id)==1)
					{	
					int seat_state=1;
					temp.Update_seat_state(seat_state,seat_id);//通过根据seat_id寻找到车主的seat_state将其车位状态改为占用1			
					out.write("<script>alert('数据添加成功！'); location.href = '/PARK1.1/TempServlet?type=4';</script>");
					}else {
							out.write("<script>alert('数据添失败！'); location.href = '/PARK1.1/TempServlet?type=4';</script>");
					}
			}
	}

//获取对象所有数据列表type=4
			private void getEntity() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//三元操作符获取跳转的页面号
				int totalPage=Integer.parseInt(temp.getPageCount().toString()) ;//获取分页总数				
				List<Object> list=temp.getEntity(page);//获取数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("totalPage",totalPage );//将totalPage存放到request对象中，用于转发给前台页面使用
				request.getRequestDispatcher("/Control/TempMsg.jsp").forward(request, response);//请求转发
				list.clear();
				list=null;
			}
			
//根据查询条件获取对象所有数据列表type=7
			private void getEntityByWhere() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				String condition=request.getParameter("condition");//获取查询字段的名称
				//String value=new String(request.getParameter("value").getBytes("ISO8859_1"),"UTF-8");//获取查询的值
				String value = request.getParameter("value");
				String sqlWhere=condition+"=\""+value+"\"";//拼接查询字符串
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));//获取要跳转的页面号
				int wherePage=Integer.parseInt(temp.getPageCountByWhere(sqlWhere).toString()) ;//获取查询后的分页总数
				List<Object> list=temp.getEntityByWhere(sqlWhere, page);//获取查询后的数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("wherePage",wherePage );
				request.setAttribute("condition",condition);
				request.setAttribute("value",value);
				request.getRequestDispatcher("/Control/TempMsg.jsp").forward(request, response);
				list.clear();
				list=null;
			}
			
//////////////////////	
			
////////////////////////type=6
			private void getEntity_CUS() throws ServletException, IOException
			{
				
				
				request.setCharacterEncoding("UTF-8");
				HttpSession session = request.getSession(); 						
				String car_num=(String)session.getAttribute("car_num");//获取用户id 	
				System.out.print(car_num);
				List<Object> list=temp.getEntityById_CUS(car_num);//获取数据列表						
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用					
				request.getRequestDispatcher("/Customer/TempMsg.jsp").forward(request, response);//请求转发
			}
			
//根据查询条件获取对象所有数据列表    type=7
			private void getEntityByWhere_CUS() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				String condition=request.getParameter("condition");//获取查询字段的名称
				//String value=new String(request.getParameter("value").getBytes("ISO8859_1"),"UTF-8");//获取查询的值
				String value = request.getParameter("value");
				String where=condition+"=\""+value+"\"";//拼接查询字符串
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));//获取要跳转的页面号
				int wherePage=Integer.parseInt(temp.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
				List<Object> list=temp.getEntityByWhere(where, page);//获取查询后的数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("wherePage",wherePage );
				request.setAttribute("condition",condition);
				request.setAttribute("value",value);
				request.getRequestDispatcher("/Customer/TempMsg.jsp").forward(request, response);
			}
		}
