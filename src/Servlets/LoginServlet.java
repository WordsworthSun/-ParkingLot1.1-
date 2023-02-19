package Servlets;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet { 
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
		String verifyCode = request.getParameter("ValiImage"); //2 获得服务器session 存放数据 ,如果没有返回null
       
        String sessionCacheData = (String) request.getSession().getAttribute("Valicode"); // *将服务器缓存session数据移除
       
        request.getSession().removeAttribute("Valicode");
       
        if(sessionCacheData == null){ // ** 判断服务器是否存在
        	request.setAttribute("message", "请不要重复提交");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }
        //3 比较
        if(! sessionCacheData.equalsIgnoreCase(verifyCode)){
            //用户输入错误
            // * 存放request作用域
        	request.setAttribute("message", "验证码输入错误");
            // * 请求转发
            request.getRequestDispatcher("Login.jsp").forward(request, response);        
            return;
        }
        
        HttpSession session=request.getSession();
		response.setCharacterEncoding("UTF-8");//设置输出编码格式
		response.setContentType("text/html;charset=UTF-8");
//		String user_name=request.getParameter("user_name");//获取前台url传过来的uName参数
//		String user_pwd=request.getParameter("user_pwd");//获取前台url传过来的uPwd参数
		
		String user_name=new String(request.getParameter("user_name").getBytes("ISO8859_1"),"UTF-8");
		String user_pwd=new String(request.getParameter("user_pwd").getBytes("ISO8859_1"),"UTF-8");
		
		DAL.Login login=new DAL.Login();//实例化Login对象，来至DAL包
		
		boolean result=login.checkLogin(user_name, user_pwd);//检查登陆用户是否合法
		
		if(result)//登陆正确
		{
			
			session.setAttribute("user_name", user_name);//将用户userName保存在session对象中全局使用
			
			String user_id=login.getName(user_name);//获取用户名
			
			session.setAttribute("user_id", user_id);
			
			String real_name=login.getRealName(user_name);//获取真名
			session.setAttribute("real_name", real_name);
			
			String role_id=login.getRole(user_name);
			session.setAttribute("role_id", role_id);
			
			String car_num=login.getCarNum(user_name);
			session.setAttribute("car_num", car_num);
			
			
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		
		}
		else {//登陆错误
			PrintWriter out=response.getWriter();
			out.write("<script>alert('用户名或密码错误！');location.href='Login.jsp';</script>");
		}
	}
	
	
	
	
	
	
	
//	public class LoginServlet extends HttpServlet { 
//		public void doPost(HttpServletRequest request, HttpServletResponse response) 
//				throws ServletException, IOException { 
//			/* * 校验验证码 1. 从session中获取正确的验证码 2. 从表单中获取用户填写的验证码 3. 进行比较 4. * 如果相同，向下运行，否则保存错误信息到request域，转发到login.jsp */ 
//			String sessionCode = (String) request.getSession().getAttribute( "session_vcode"); 
//			System.out.println("系统传递过来的:" + sessionCode); 
//			String formCodeString = request.getParameter("verifyCode"); 
//			System.out.println("用户输入的："+formCodeString); 
//			if (!formCodeString.equalsIgnoreCase(sessionCode)) { 
//				// 保存错误信息到request域 
//				request.setAttribute("msg", "验证码输入错误，请重新输入！");
//				// 得到一个转发器 
//				RequestDispatcher qr = request .getRequestDispatcher("/session2/login.jsp"); 
//				// 转发 
//				qr.forward(request, response);
//				return;
//				} 
//				/* * 1. 获取表单数据 */ 
//				// 处理中文问题 
//			request.setCharacterEncoding("utf-8"); 
//				// 获取表单数据 
//			String username = request.getParameter("username"); 
//				String password = request.getParameter("password"); 
//				/* * 2. 校验用户名和密码是否正确,预设用户名和密码分别为：“root”，“0000” */ 
//				if ("root".equalsIgnoreCase(username) && "0000".equals(password)) { 
//					/******* 附加项 *************************************************************/ 
//					// 把用户名保存到cookie中，发送给客户端浏览器。 
//					// 当再次打开login.jsp时，login.jsp中会读取request中的cookie，把它显示到用户名文本框中 
//					// 1. 创建cookie
	//Cookie cookie = new Cookie("uname", username); 
//					// 2. 设置cookie生命时长为7天。
	//cookie.setMaxAge(60 * 60 * 24 * 7);
//					// 3. 保存cookie 
	//response.addCookie(cookie);
//					/******* 附加项 *************************************************************/ 
//					/* * 3.如果成功 -> 保存用户信息到session中 -> 重定向到succ.jsp */
//					// 获取session 
//					HttpSession session = request.getSession(); 
//					// 向session域中保存用户名 
//					session.setAttribute("username", username); 
//					// 重定向到succ.jsp 
//					response.sendRedirect("/JavaWebLearning/session2/succ.jsp"); } 
//				else { /* * 如果失败 -> 保存错误信息到request域中 -> 转发到login.jsp */ 
//					// 保存错误信息到request域
//					request.setAttribute("msg", "用户名或密码错误！"); 
//					// 得到一个转发器 RequestDispatcher 
//					qr = request .getRequestDispatcher("/session2/login.jsp"); 
//					// 转发 
//					qr.forward(request, response);
//					} 
//				
//		
//				}
//			}
		}

	

