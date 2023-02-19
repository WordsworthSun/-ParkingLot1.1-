package Servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import java.text.SimpleDateFormat;
import java.util.*;

public class UserServlet extends HttpServlet {
HttpServletRequest request;
HttpServletResponse response;
DAL.User user=new DAL.User();
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
					case 9://类型4代表获取表中信息
						getEntity_CUS();
						break;
					case 10://类型5代表根据查询条件获取表中信息
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
					case 6://类型6代表向更改密码
						chagePwd();
						break;
					case 7://类型7代表用户更新个人数据
						updateEntity();
						break;
					case 8://用户注册
						register();
						break;
					case 11://类型7代表用户更新个人数据
						updateEntity_CUS();
						break;	
					default:
						break;
					}
			}
			
//更改密码type=6
			private void chagePwd() throws IOException
			{
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				HttpSession session=request.getSession();
				String userId=session.getAttribute("user_id").toString();
				String oldPwd=new String(request.getParameter("OldPwd").getBytes("ISO8859_1"),"UTF-8");
				String newPwd=new String(request.getParameter("NewPwd").getBytes("ISO8859_1"),"UTF-8");
				if(user.checkPwd(userId, oldPwd))
				{
					if(user.updataPwd(userId, newPwd))
					{
						out.write("<script>alert('密码更改成功~~~');location.href='/PARK1.1/Control/UserInfo.jsp'</script>");
					}
					else {
						out.write("<script>alert('密码更改失败~~~');location.href='/PARK1.1/Control/ChagePwd.jsp'</script>");
					}
				}
				else {
					out.write("<script>alert('原始密码错误~~~');location.href='/PARK1.1/Control/ChagePwd.jsp'</script>");
				}
			}
			
//用户注册添加数据type=8
			private void register() throws UnsupportedEncodingException, IOException
			{
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();				    
				SimpleDateFormat dateFormat =new    SimpleDateFormat("yyyyMMddHHmmss"); 
			    String UserId=dateFormat.format(new Date());				
				String UserName=new String(request.getParameter("user_name").getBytes("ISO8859_1"),"UTF-8");
				String RoleId=new String(request.getParameter("role_id").getBytes("ISO8859_1"),"UTF-8");								
				String UserPwd=new String(request.getParameter("user_pwd").getBytes("ISO8859_1"),"UTF-8");
				String UserEmail=new String(request.getParameter("user_email").getBytes("ISO8859_1"),"UTF-8");
				String RealName=new String(request.getParameter("real_name").getBytes("ISO8859_1"),"UTF-8");
				String UserPhone=new String(request.getParameter("user_phone").getBytes("ISO8859_1"),"UTF-8");
				String UserGender=new String(request.getParameter("user_gender").getBytes("ISO8859_1"),"UTF-8");
				String UserBirth=new String(request.getParameter("user_birth").getBytes("ISO8859_1"),"UTF-8");
				String CarNum=new String(request.getParameter("car_num").getBytes("ISO8859_1"),"UTF-8");
				String UserAddr=new String(request.getParameter("user_addr").getBytes("ISO8859_1"),"UTF-8");
				if(!user.checkExist(UserId))
				{
					if(user.insertEntity_Register(UserId,UserName,UserPwd,UserEmail,RealName,UserPhone,UserGender,UserBirth,CarNum,RoleId,UserAddr)==1)
					{					    
					    out.write("<script>alert('恭喜你，注册成功~'); location.href = '/PARK1.1/Login.jsp';</script>");
					}
				}
				else {
					  out.write("<script>alert('您注册的登陆账号已存在，请重新注册！'); location.href = '/PARK1.1/Login.jsp';</script>");
				}
			}

//删除数据操作type=1
			private void deleteEntity() throws IOException
			{
				String user_id=request.getParameter("user_id");//获取前台通过get方式传过来的JId
				user.deleteEntity(user_id);//执行删除操作
				response.sendRedirect("/PARK1.1/UserServlet?type=4");//删除成功后跳转至管理页面
			}

////////////////////////////////更新数据操作type=2
			private void updateEntity() throws UnsupportedEncodingException
			{
				String user_id=new String(request.getParameter("user_id").getBytes("ISO8859_1"),"UTF-8");				
				String user_name=new String(request.getParameter("user_name").getBytes("ISO8859_1"),"UTF-8");			
				String user_pwd=new String(request.getParameter("user_pwd").getBytes("ISO8859_1"),"UTF-8");
				String user_email=new String(request.getParameter("user_email").getBytes("ISO8859_1"),"UTF-8");
				String real_name=new String(request.getParameter("real_name").getBytes("ISO8859_1"),"UTF-8");			
				String user_phone=new String(request.getParameter("user_phone").getBytes("ISO8859_1"),"UTF-8");			
				String user_gender=new String(request.getParameter("user_gender").getBytes("ISO8859_1"),"UTF-8");
				String user_birth=new String(request.getParameter("user_birth").getBytes("ISO8859_1"),"UTF-8");
				String car_num=new String(request.getParameter("car_num").getBytes("ISO8859_1"),"UTF-8");
				String role_name=new String(request.getParameter("role_name").getBytes("ISO8859_1"),"UTF-8");
				String role_id=" ";
				if(role_name.equals("管理员"))
					{
					role_id="a001";
					}else {
						role_id="c001";
					}
				String user_addr=new String(request.getParameter("user_addr").getBytes("ISO8859_1"),"UTF-8");				
				if(user.updateEntity(user_id,user_name,user_pwd,user_email,real_name,user_phone,user_gender,user_birth,car_num,role_id,user_addr,role_name)==1)
				{		
					JOptionPane.showConfirmDialog(null,"修改成功","提示",JOptionPane.WARNING_MESSAGE);	//确认对话框
					try {		
						
						//该消息框的提示图标会被自定义的图标覆盖掉
								response.sendRedirect("/PARK1.1/UserServlet?type=4");//成功更新数据后跳转至UserInfo.jsp页面						
					} catch (IOException e) {
						e.printStackTrace();//异常处理
					}			
				}
				
			}
			
			
			
			
///////////////////////////////////更新数据操作type=11
			private void updateEntity_CUS() throws UnsupportedEncodingException
			{
				
				
				HttpSession session=request.getSession();
				String user_id=session.getAttribute("user_id").toString();
				System.out.println(user_id);
				String user_name=new String(request.getParameter("user_name").getBytes("ISO8859_1"),"UTF-8");
				String real_name=new String(request.getParameter("real_name").getBytes("ISO8859_1"),"UTF-8");
				String user_pwd=new String(request.getParameter("user_pwd").getBytes("ISO8859_1"),"UTF-8");
				String user_phone=new String(request.getParameter("user_phone").getBytes("ISO8859_1"),"UTF-8");
				String user_email=new String(request.getParameter("user_email").getBytes("ISO8859_1"),"UTF-8");
				String user_gender=new String(request.getParameter("user_gender").getBytes("ISO8859_1"),"UTF-8");
				String user_birth=new String(request.getParameter("user_birth").getBytes("ISO8859_1"),"UTF-8");
				String car_num=new String(request.getParameter("car_num").getBytes("ISO8859_1"),"UTF-8");
				String user_addr=new String(request.getParameter("user_addr").getBytes("ISO8859_1"),"UTF-8");
				
				if(user.updateEntity_CUS(user_id,user_name,real_name,user_pwd,user_phone,user_email,user_gender,user_birth,car_num,user_addr)==1)
				{
					try {
							if(request.getSession().getAttribute("role_id").toString().equals("r001"))
							{
								response.sendRedirect("/PARK1.1/UserServlet?type=9");//成功更新数据后跳转至UserInfo.jsp页面
							}
							else {
								response.sendRedirect("/PARK1.1/UserServlet?type=9");//成功更新数据后跳转至UserInfo.jsp页面
							}
					} catch (IOException e) {
						e.printStackTrace();//异常处理
					}
				}
			}
			
		
//////////////////////////////////插入数据操作type=3
			private void insertEntity() throws UnsupportedEncodingException, IOException
			{
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();	
				
				String user_id=new String(request.getParameter("user_id").getBytes("ISO8859_1"),"UTF-8");
				String role_id=new String(request.getParameter("role_id").getBytes("ISO8859_1"),"UTF-8");
				String role_name="";
				if(role_id.equals("a001"))
				{
					role_name="管理员";
				}else { 
					role_name="用户";
				}
				String user_name=new String(request.getParameter("user_name").getBytes("ISO8859_1"),"UTF-8");
				String real_name=new String(request.getParameter("real_name").getBytes("ISO8859_1"),"UTF-8");
				String user_pwd=new String(request.getParameter("user_pwd").getBytes("ISO8859_1"),"UTF-8");
				String user_phone=new String(request.getParameter("user_phone").getBytes("ISO8859_1"),"UTF-8");
				String user_email=new String(request.getParameter("user_email").getBytes("ISO8859_1"),"UTF-8");
				String user_gender=new String(request.getParameter("user_gender").getBytes("ISO8859_1"),"UTF-8");
				String user_birth=new String(request.getParameter("user_birth").getBytes("ISO8859_1"),"UTF-8");
				String car_num=new String(request.getParameter("car_num").getBytes("ISO8859_1"),"UTF-8");
				String user_addr=new String(request.getParameter("user_addr").getBytes("ISO8859_1"),"UTF-8");
				if(!user.checkExist(user_id))
				{
					if(user.insertEntity(user_id,user_name,user_pwd,user_email,real_name,user_phone,user_gender,user_birth,car_num,role_id,user_addr,role_name)==1)
					{
						out.write("<script>alert('数据添加成功！'); location.href = '/PARK1.1/UserServlet?type=4';</script>");
					}
					else {
						out.write("<script>alert('数据添失败！'); location.href = '/PARK1.1/UserServlet?type=4';</script>");
					}
				}
				else {
					out.write("<script>alert('主键重复，数据添加失败！'); location.href = '/PARK1.1/UserServlet?type=4';</script>");
				}
			}

//获取对象所有数据列表type=4
			private void getEntity() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//获取跳转的页面号
				int totalPage=Integer.parseInt(user.getPageCount().toString()) ;//获取分页总数
				List<Object> list=user.getEntity(page);//获取数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("totalPage",totalPage );//将totalPage存放到request对象中，用于转发给前台页面使用
				request.getRequestDispatcher("/Control/UserMsg.jsp").forward(request, response);//请求转发
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
				int wherePage=Integer.parseInt(user.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
				List<Object> list=user.getEntityByWhere(where, page);//获取查询后的数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("wherePage",wherePage );
				request.setAttribute("condition",condition);
				request.setAttribute("value",value);
				request.getRequestDispatcher("/Control/UserMsg.jsp").forward(request, response);
			}
///////////////////////////////////////////////////////////////////			
			//获取对象所有数据列表     type=9
			private void getEntity_CUS() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				HttpSession session = request.getSession(); 				
				String user_id=(String)session.getAttribute("user_id");//获取用户id 							
				List<Object> list=user.getEntityById(user_id);//获取数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用				
				request.getRequestDispatcher("/Customer/UserMsg.jsp").forward(request, response);//请求转发
			}
			
			//根据查询条件获取对象所有数据列表type=10
			private void getEntityByWhere_CUS() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				String condition=request.getParameter("condition");//获取查询字段的名称
				//String value=new String(request.getParameter("value").getBytes("ISO8859_1"),"UTF-8");//获取查询的值
				String value = request.getParameter("value");
				String where=condition+"=\""+value+"\"";//拼接查询字符串
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));//获取要跳转的页面号
				int wherePage=Integer.parseInt(user.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
				List<Object> list=user.getEntityByWhere(where, page);//获取查询后的数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("wherePage",wherePage );
				request.setAttribute("condition",condition);
				request.setAttribute("value",value);
				request.getRequestDispatcher("/Customer/UserMsg.jsp").forward(request, response);
			}			
			
			
			
			
						
}
