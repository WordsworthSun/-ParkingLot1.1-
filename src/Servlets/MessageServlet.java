package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Messages;

public class MessageServlet extends HttpServlet { 
	HttpServletRequest request;
	HttpServletResponse response;
	DAL.Messages messages=new DAL.Messages();//实例化一个messages的对象
	
	//通过表单get方式传值 将进入doGet函数（method="get"）get是从服务器上获取数据，post是向服务器传送数据
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
			case 7://类型4代表获取表中信息
				getEntity_Ann();
				break;
			case 8://类型5代表根据查询条件获取表中信息
				getEntityByWhere_Ann();
				break;	
			case 9://类型9查看所有内容
				CUS_getEntity();
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
			case 3://类型3向表中添加公告信息
				insertEntity();
				break;
			case 6://类型3代表向表中添加数据
				addEntity();
				break;	
			
			default:
				break;
			}
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
//根据messageID删除留言	type=1
	private void deleteEntity() throws IOException{
		String message_id=request.getParameter("message_id");//获取前台通过get方式传过来的JId
		messages.deleteEntity(message_id);//执行删除操作
		response.sendRedirect("/PARK1.1/MessageServlet?type=4");//删除成功后跳转至管理页面
		
	}
	
	
	
//更新数据操作  type=2
	private void updateEntity() throws UnsupportedEncodingException
	{
		String user_id=new String(request.getParameter("user_id").getBytes("ISO8859_1"),"UTF-8");
		String title=new String(request.getParameter("title").getBytes("ISO8859_1"),"UTF-8");
		String context=new String(request.getParameter("context").getBytes("ISO8859_1"),"UTF-8");
		String time=new String(request.getParameter("time").getBytes("ISO8859_1"),"UTF-8");	
		String message_id=new String(request.getParameter("message_id").getBytes("ISO8859_1"),"UTF-8");
		if(messages.updateEntity(user_id,title,context,time,message_id)==1)
		{
			try {
				response.sendRedirect("/PARK1.1/MessageServlet?type=4");//成功更新数据后跳转至CardMsg.jsp页面
			} catch (IOException e) {
				e.printStackTrace();//异常处理
			}
		}
	}
	
	
	
	
//插入数据操作添加新数据，添加公告数据！！只添加公告！！    type=3
	private void insertEntity() throws UnsupportedEncodingException, IOException
		{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			//通过session获取用户名
			HttpSession session = request.getSession(); 
			//Integer message_id= (Integer)session.getAttribute("user_id");
			String user_id=(String)session.getAttribute("user_id");//获取用户id 
			//String message_id=session.getAttribute("role_id").toString()
			//String message_id=new String(request.getParameter("message_id").getBytes("ISO8859_1"),"UTF-8");
			//String title=new String(request.getParameter("title").getBytes("ISO8859_1"),"UTF-8");
			String title="公告";	//标题为公告	 
			String context=new String(request.getParameter("context").getBytes("ISO8859_1"),"UTF-8");//内容 
	    	SimpleDateFormat dateFormat =new SimpleDateFormat("yyyyMMddHHmmss"); //自动获取时间
	    	String time=dateFormat.format(new Date());//获取所有日期
	    	SimpleDateFormat dateFormat1 =new SimpleDateFormat("HHmmss"); 
	    	String time_clock=dateFormat1.format(new Date());//只获取时间
			String real_name=(String)session.getAttribute("real_name");//真实姓名
			String message_id=real_name+":"+time_clock ;//message_id为日期加用户id
			///String user_name=new String(request.getParameter("user_name").getBytes("ISO8859_1"),"UTF-8");		
			if(messages.insertEntity(user_id,title,context,time,message_id)==1)
			{
				out.write("<script>alert('数据添加成功！'); location.href = '/PARK1.1/MessageServlet?type=4';</script>");
			}
			else {
				out.write("<script>alert('数据添失败！'); location.href = '/PARK1.1/MessageSelvet?type=4';</script>");
			}
		}
		
	

//插入数据添加新数据添加新的留言    type=6
	private void addEntity() throws UnsupportedEncodingException, IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		//通过session获取用户名
		HttpSession session = request.getSession(); 
		//Integer message_id= (Integer)session.getAttribute("user_id");
		String user_id=(String)session.getAttribute("user_id");//获取用户id 
		//String message_id=session.getAttribute("role_id").toString()
		//String message_id=new String(request.getParameter("message_id").getBytes("ISO8859_1"),"UTF-8");
		//String title=new String(request.getParameter("title").getBytes("ISO8859_1"),"UTF-8");
		String title="留言";	//标题为公告	 
		String context=new String(request.getParameter("context").getBytes("ISO8859_1"),"UTF-8");//内容 
    	SimpleDateFormat dateFormat =new SimpleDateFormat("yyyyMMddHHmmss"); //自动获取时间
    	String time=dateFormat.format(new Date());//获取所有日期
    	SimpleDateFormat dateFormat1 =new SimpleDateFormat("HHmmss"); 
    	String time_clock=dateFormat1.format(new Date());//只获取时间
		String real_name=(String)session.getAttribute("real_name");//真实姓名
		String message_id=real_name+":"+time_clock ;//message_id为日期加用户id
		///String user_name=new String(request.getParameter("user_name").getBytes("ISO8859_1"),"UTF-8");		
		if(messages.insertEntity(user_id,title,context,time,message_id)==1)
		{
			out.write("<script>alert('数据添加成功！'); location.href = '/PARK1.1/MessageServlet?type=9';</script>");
		}
		else {
			out.write("<script>alert('数据添失败！'); location.href = '/PARK1.1/MessageSelvet?type=9';</script>");
		}
	}
		

	
//获取对象所有数据列表	type=4
	private void getEntity() throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//获取跳转的页面号
		
		int totalPage=Integer.parseInt(messages.getPageCount().toString()) ;//获取分页总数
		
//		HttpSession session = request.getSession(); 
//		//Integer message_id= (Integer)session.getAttribute("user_id");
//		String real_name=(String)session.getAttribute("real_name");//获取用户id 
		
		List<Object> list=messages.getEntity(page);//获取数据列表
		request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
		request.setAttribute("totalPage",totalPage );//将totalPage存放到request对象中，用于转发给前台页面使用
		request.getRequestDispatcher("/LeaveWord/AnnounceOut.jsp").forward(request, response);//请求转发
		//request.getRequestDispatcher("/LeaveWord/Announce.jsp").forward(request, response);//请求转发
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
		int wherePage=Integer.parseInt(messages.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
		List<Object> list=messages.getEntityByWhere(where, page);//获取查询后的数据列表
		request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
		request.setAttribute("wherePage",wherePage );
		request.setAttribute("condition",condition);
		request.setAttribute("value",value);
		request.getRequestDispatcher("/LeaveWord/AnnounceOut.jsp").forward(request, response);
		//request.getRequestDispatcher("/LeaveWord/Announce.jsp").forward(request, response);//请求转发
	}
	
	

	//获取对象所有数据列表	type=7
	private void getEntity_Ann() throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//获取跳转的页面号		
		int totalPage=Integer.parseInt(messages.getPageCount().toString()) ;//获取分页总数		
		List<Object> list=messages.getEntity(page);//获取数据列表
		request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
		request.setAttribute("totalPage",totalPage );//将totalPage存放到request对象中，用于转发给前台页面使用		
		request.getRequestDispatcher("/LeaveWord/Announce.jsp").forward(request, response);//请求转发
	}	
	
	//根据查询条件获取对象所有数据列表type=8
			private void getEntityByWhere_Ann() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				String condition=request.getParameter("condition");//获取查询字段的名称
				//String value=new String(request.getParameter("value").getBytes("ISO8859_1"),"UTF-8");//获取查询的值
				String value = request.getParameter("value");				
				String where=condition+"=\""+value+"\"";//拼接查询字符串
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page"));//获取要跳转的页面号
				int wherePage=Integer.parseInt(messages.getPageCountByWhere(where).toString()) ;//获取查询后的分页总数
				List<Object> list=messages.getEntityByWhere(where, page);//获取查询后的数据列表
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("wherePage",wherePage );
				request.setAttribute("condition",condition);
				request.setAttribute("value",value);
				//request.getRequestDispatcher("/LeaveWord/AnnounceOut.jsp").forward(request, response);
				request.getRequestDispatcher("/LeaveWord/Announce.jsp").forward(request, response);//请求转发
			}	
	
	
	
	
////////////////////////type=9	
			private void CUS_getEntity() throws ServletException, IOException
			{
				request.setCharacterEncoding("UTF-8");
				int page=request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());//获取跳转的页面号		
				int totalPage=Integer.parseInt(messages.getPageCount().toString()) ;//获取分页总数		
				List<Object> list=messages.getEntity(page);//获取数据列表
				
				request.setAttribute("list",list);//将数据存放到request对象中，用于转发给前台页面使用
				request.setAttribute("totalPage",totalPage );//将totalPage存放到request对象中，用于转发给前台页面使用		
				request.getRequestDispatcher("/Chat/Comment.jsp").forward(request, response);//请求转发
			}
	
	
	
	
	
	
	
	

}
