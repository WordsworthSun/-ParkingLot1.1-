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

public class CardServlet extends HttpServlet {

HttpServletRequest request;
HttpServletResponse response;
DAL.Card card=new DAL.Card();	
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
					case 6://类型4代表获取表中信息
						getEntity_CUS();
						break;
					case 7://类型5代表根据查询条件获取表中信息
						getEntityByWhere_CUS();							
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			 
//删除数据操作1
			private void deleteEntity() throws IOException
			{
				String card_id=request.getParameter("card_id");//获取前台通过get方式传过来的Id				
				String seat_id=request.getParameter("seat_id");//获取前台通过get方式传过来的Id
				String seat_tag="临时";//使seat表内seat_state设为0，状态为									
				card.Update_seat_tag(seat_tag,seat_id);//通过根据seat_id寻找到车主的seat_state将其车位状态改为占用1	
				card.deleteEntity(card_id);//执行删除操作
				response.sendRedirect("/PARK1.1/CardServlet?type=4");//删除成功后跳转至管理页面
			}

//更新数据操作
			private void updateEntity() throws UnsupportedEncodingException
			{
				String card_id=new String(request.getParameter("card_id").getBytes("ISO8859_1"),"UTF-8");
				String seat_id=new String(request.getParameter("seat_id").getBytes("ISO8859_1"),"UTF-8");
				String user_id=new String(request.getParameter("user_id").getBytes("ISO8859_1"),"UTF-8");
				String user_phone=new String(request.getParameter("user_phone").getBytes("ISO8859_1"),"UTF-8");
				String user_addr=new String(request.getParameter("user_addr").getBytes("ISO8859_1"),"UTF-8");
				String car_num=new String(request.getParameter("car_num").getBytes("ISO8859_1"),"UTF-8");

				if(card.updateEntity(card_id,seat_id,user_id,user_phone,user_addr,car_num)==1)
				{
					try {
						response.sendRedirect("/PARK1.1/CardServlet?type=4");//成功更新数据后跳转至CardMsg.jsp页面
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
			    String card_id=dateFormat.format(new Date());			    
				String seat_id=new String(request.getParameter("seat_id").getBytes("ISO8859_1"),"UTF-8");//根据seat_id使seat_tag成为固定				
				String user_id=new String(request.getParameter("user_id").getBytes("ISO8859_1"),"UTF-8");				
				List<Object> UserList=card.getEntity_User(user_id);//获取数据列表				
				ArrayList<String> phone= new ArrayList<String>();
				ArrayList<String> num= new ArrayList<String>();
				ArrayList<String> addr= new ArrayList<String>();
				 for(int i=0;i<UserList.size();i++)
         		{
        				Object[] obj;
        				obj=(Object[])UserList.get(i);
        				phone.add(obj[5].toString());
        				num.add(obj[8].toString());       				
        				addr.add(obj[10].toString());
						}				 
				String user_phone=phone.get(0);
				String car_num=num.get(0);
				String user_addr=addr.get(0);
				
//				String user_phone=new String(request.getParameter("user_phone").getBytes("ISO8859_1"),"UTF-8");
//				String user_addr=new String(request.getParameter("user_addr").getBytes("ISO8859_1"),"UTF-8");
//				String car_num=new String(request.getParameter("car_num").getBytes("ISO8859_1"),"UTF-8");
		if(!card.checkExist_car_num(car_num)) {
				if(!card.checkExist_seat_id(seat_id)) {
							if(card.insertEntity(card_id,seat_id,user_id,user_phone,user_addr,car_num)==1)
									{
								
								String seat_tag="固定";//使seat表内seat_state设为0，状态为									
								card.Update_seat_tag(seat_tag,seat_id);//通过根据seat_id寻找到车主的seat_state将其车位状态改为占用1	
								out.write("<script>alert('数据添加成功！'); location.href = '/PARK1.1/CardServlet?type=4';</script>");
									}
								else {
										out.write("<script>alert('数据添失败！'); location.href = '/PARK1.1/CardServlet?type=4';</script>");
									}
					}
					else {
						out.write("<script>alert('车位已被占用！'); location.href = '/PARK1.1/CardServlet?type=4';</script>");
					}
		}else {
					
			out.write("<script>alert('您已是会员！'); location.href = '/PARK1.1/CardServlet?type=4';</script>");
			}		
		phone.clear();
		phone=null;		
		num.clear();
		num=null;
		addr.clear();
		addr=null;
		}
//获取对象所有数据列表type=4
			private void getEntity() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//获取跳转的页面号
				int totalPage=Integer.parseInt(card.getPageCount().toString()) ;//获取分页总数
				List<Object> list=card.getEntity(page);//获取数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("totalPage",totalPage );//将totalPage存放到request对象中，用于转发给前台页面使用
				request.getRequestDispatcher("/Control/CardMsg.jsp").forward(request, response);//请求转发
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
				int wherePage=Integer.parseInt(card.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
				List<Object> list=card.getEntityByWhere(where, page);//获取查询后的数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("wherePage",wherePage );
				request.setAttribute("condition",condition);
				request.setAttribute("value",value);
				request.getRequestDispatcher("/Control/CardMsg.jsp").forward(request, response);
			}
			
/////////////////////////////////////////////////			
			
			//获取对象所有数据列表type=6
			private void getEntity_CUS() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				HttpSession session = request.getSession(); 				
				String user_id=(String)session.getAttribute("user_id");//获取用户id 					
				List<Object> list=card.getEntityById_CUS(user_id);//获取数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用		
				request.getRequestDispatcher("/Customer/CardMsg.jsp").forward(request, response);//请求转发
			}
			
//根据查询条件获取对象所有数据列表type=7
			private void getEntityByWhere_CUS() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				String condition=request.getParameter("condition");//获取查询字段的名称
				//String value=new String(request.getParameter("value").getBytes("ISO8859_1"),"UTF-8");//获取查询的值
				String value = request.getParameter("value");
				String where=condition+"=\""+value+"\"";//拼接查询字符串
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));//获取要跳转的页面号
				int wherePage=Integer.parseInt(card.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
				List<Object> list=card.getEntityByWhere(where, page);//获取查询后的数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("wherePage",wherePage );
				request.setAttribute("condition",condition);
				request.setAttribute("value",value);
				request.getRequestDispatcher("/Customer/CardMsg.jsp").forward(request, response);
			}			
			
		
			
		}
