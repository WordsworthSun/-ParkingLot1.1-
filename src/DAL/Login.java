package DAL;

import DBUtil.SQLUtil;

public class Login {
	
	//检测用户登录信息是否合法，合法这返回true
	public boolean checkLogin(String user_name,String user_pwd)
	{
		String sqlCmd="select count(*) from user where user_name=? and user_pwd=?";//要执行的查询T-SQL命令
		Object[] objList=new Object[2];//对象数组，用来作为？参数的容器
		objList[0]=user_name;
		objList[1]=user_pwd;
		String result=SQLUtil.excuteScalar(sqlCmd,objList).toString();//执行带参数查询
		if(result.equals("1"))
		{
			return true;
		}
		else {
			return false;
		}
		
	}
	
	//根据用户name获取用户id
	public String  getName(String user_name)
	{
		String sqlCmd="select user_id from user where user_name='"+user_name+"'";
		String result=SQLUtil.excuteScalar(sqlCmd, null).toString();
		return result;
	}
	//根据用户name获取真名	
	public String  getRealName(String user_name)
	{
		String sqlCmd="select real_name from user where user_name='"+user_name+"'";
		String result=SQLUtil.excuteScalar(sqlCmd, null).toString();
		return result;
	}
	
	//获取用户（角色Id）信息
	public String  getRole(String user_name)
	{
		String sqlCmd="select role_id from user where user_name='"+user_name+"'";
		String result=SQLUtil.excuteScalar(sqlCmd, null).toString();
		return result;
	}
	//根据用户name获取真名	
		public String  getCarNum(String user_name)
		{
			String sqlCmd="select car_num from user where user_name='"+user_name+"'";
			String result=SQLUtil.excuteScalar(sqlCmd, null).toString();
			return result;
		}
}
