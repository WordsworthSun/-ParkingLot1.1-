        package DAL;

        import java.util.*;
        
import DBUtil.SQLUtil;
        
        public class User {

        	
//获取用户表信息列表
		public List<Object> getEntity()
		{
			String sqlCmd="select *from User";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
       
		
		
//获取分页后用户表信息列表
		public List<Object> getEntity(int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from User limit "+size+",15";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
      
		
		
//根据查询条件sqlWhere获取分页后用户表信息列表
		public List<Object> getEntityByWhere(String where,int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from User where "+where+" limit "+ size+",15";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
       
		
		
//删除用户表信息
        public int deleteEntity(String user_id)
        {
            String sqlCmd="delete from User where user_id='"+user_id+"'";
            return DBUtil.SQLUtil.executeNonQuery(sqlCmd, null);//执行非查询操作executeNonQuery
        }
        
        
        
//根据用户表编号获取用户表信息
        public List<Object> getEntityById(String user_id)
        {
            String sqlCmd="select *From User where user_id='"+user_id+"'";
            return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
        
    
        
//更新用户表信息user_id,user_name,user_pwd,user_email,real_name,user_phone,user_gender,user_birth,car_num,role_id
        public int updateEntity( String user_id,String user_name, String user_pwd, String user_email, String real_name, String user_phone,String user_gender,String user_birth,String car_num,String role_id,String user_addr,String role_name)
        {
            String sqlCmd="Update User set user_name='" + user_name + "',user_pwd='" + user_pwd + "',user_email='" + user_email + "',real_name='" + real_name + "',user_phone='" + user_phone + "' ,user_gender='" + user_gender + "',user_birth='" + user_birth +"',car_num='" + car_num+ "',role_id='" + role_id+ "',user_addr='" + user_addr+ "',role_name='" + role_name+ "'where user_id='"+user_id+"'";
            return SQLUtil.executeNonQuery(sqlCmd, null);
        }
        
//更新用户表信息user_id,user_name,real_name,user_pwd,user_phone,user_email,user_gender,user_birth,car_num,user_addr
        public int updateEntity_CUS(String user_id,String user_name,String real_name,String user_pwd,String user_phone,String user_email,String user_gender,String user_birth,String car_num,String user_addr)
        {
            String sqlCmd="Update User set user_name='" + user_name + "',real_name='" + real_name + "',user_pwd='" + user_pwd + "',user_phone='" + user_phone + "',user_email='" + user_email + "',user_gender='" + user_gender + "',user_birth='" + user_birth + "',car_num='" + car_num + "',user_addr='" + user_addr + "' where user_id='"+user_id+"'";
            return SQLUtil.executeNonQuery(sqlCmd, null);
        }
                
        
        
        
//插入用户表信息user_id,user_name,user_pwd,user_email,real_name,user_phone,user_gender,user_birth,car_num,role_id
        public int insertEntity(String user_id,String user_name,String user_pwd,String user_email,String real_name,String user_phone,String user_gender,String user_birth,String car_num,String role_id,String user_addr,String role_name)
        {
        	
            String sqlCmd="Insert into User values('" + user_id + "','" + user_name + "','" + user_pwd + "','" + user_email+ "','" + real_name+ "','"+user_phone+"','"+user_gender+"','"+user_birth+"','"+car_num+"','"+role_id+"','"+user_addr+"','"+role_name+"')";
            return SQLUtil.executeNonQuery(sqlCmd, null);
        }
        

//插入用户表信息_添加用户数据   into User (user_id,role_id,user_name,real_name,user_pwd,user_phone)values


        public int insertEntity_Register(String user_id,String user_name,String user_pwd,String user_email,String real_name,String user_phone,String user_gender,String user_birth,String car_num,String role_id,String user_addr) {
        	String sqlCmd="Insert into User values('" + user_id + "','" + user_name + "','" + user_pwd + "','" + user_email + "','"+real_name+"','"+user_phone+"','"+user_gender+"','"+user_birth+"','"+car_num+"','"+role_id+"','"+user_addr+"')";
            return SQLUtil.executeNonQuery(sqlCmd, null);
        }
        
        
        
        
        
//检查插入主键是否重复
        public boolean checkExist(String user_id)
        {
            String sqlCmd="select count(*) from User where user_id='"+user_id+"'";
            if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) )
            {
                return true;
            }
            return false;
        }

//获取分页总数
		public Object getPageCount()
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM User ";
			return SQLUtil.excuteScalar(sqlCmd, null);
		}

//根据查询条件获取分页总数
		public Object getPageCountByWhere(String sqlWhere)
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM User where "+sqlWhere;
			return SQLUtil.excuteScalar(sqlCmd, null);
		}
		
//检查插入密码是否输入正确（改密码）
        public boolean checkPwd(String UserId,String pwd)
        {
            String sqlCmd="select count(*) from User where user_id='"+UserId+"' and user_pwd='"+pwd+"'";
            if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) )
            {
                return true;
            }
            return false;
        }
        
//检查插入密码是否输入正确（改密码）
        public boolean updataPwd(String UserId,String pwd)
        {
            String sqlCmd="Update user set user_pwd='"+pwd+"'  where user_id='"+UserId+"'";
            if(SQLUtil.executeNonQuery(sqlCmd, null)==1)
            {
            	return true;
            }
            return false;
        }
 ///////////////////////根据年月日判断年纪       
        public  int getAgeFromBirthTime(String birthTimeString) {
            
            // 先截取到字符串中的年、月、日
            String strs[] = birthTimeString.trim().split("-");
            int selectYear = Integer.parseInt(strs[0]);
            int selectMonth = Integer.parseInt(strs[1]);
            int selectDay = Integer.parseInt(strs[2]);
            // 得到当前时间的年、月、日
            Calendar cal = Calendar.getInstance();
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayNow = cal.get(Calendar.DATE);

            // 用当前年月日减去生日年月日
            int yearMinus = yearNow - selectYear;
            int monthMinus = monthNow - selectMonth;
            int dayMinus = dayNow - selectDay;

            int age = yearMinus;// 先大致赋值
            if (yearMinus < 0) {
        // 选了未来的年份
                age = 0;
            } else if (yearMinus == 0) {
        // 同年的，要么为1，要么为0
                if (monthMinus < 0) {
        // 选了未来的月份
                    age = 0;
                } else if (monthMinus == 0) {
        // 同月份的
                    if (dayMinus < 0) {
        // 选了未来的日期
                        age = 0;
                    } else if (dayMinus >= 0) {
        
                        age = 1;
                    }
                } else if (monthMinus > 0) {
        
                    age = 1;
                }
            } else if (yearMinus > 0) {       
                if (monthMinus < 0) {
        // 当前月>生日月
                } else if (monthMinus == 0) {
        // 同月份的，再根据日期计算年龄
                    if (dayMinus < 0) {       
                    } else if (dayMinus >= 0) {        
                        age = age + 1;
                    }
                } else if (monthMinus > 0) {       
                    age = age + 1;
                }
            }
            return age;
        }           
    }
