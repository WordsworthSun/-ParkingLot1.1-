package DAL;
import java.util.*;       
import DBUtil.SQLUtil;  

        public class Card {
//获取IC卡表信息列表
		public List<Object> getEntity()
		{
			String sqlCmd="select *from Card";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
            
//获取分页后IC卡表信息列表
		public List<Object> getEntity(int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from Card limit "+size+",15";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
                
//根据查询条件sqlWhere获取分页后IC卡表信息列表
		public List<Object> getEntityByWhere(String where,int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from Card where "+where+" limit "+ size+",15";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
        
//删除IC卡表信息
        public int deleteEntity(String card_id)
        {
            String sqlCmd="delete from Card where card_id='"+card_id+"'";
            return DBUtil.SQLUtil.executeNonQuery(sqlCmd, null);//执行非查询操作executeNonQuery
        }
        
//根据IC卡表编号获取IC卡表信息
        public List<Object> getEntityById(String card_id)
        {
            String sqlCmd="select *From Card where card_id='"+card_id+"'";
            return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
//根据name编号获取IC卡表信息
        public List<Object> getEntity_User(String user_id)
        {
            String sqlCmd="select *From user where user_id='"+user_id+"'";
            return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
        public List<Object> getEntityById_CUS(String user_id)
        {
            String sqlCmd="select *From Card where user_id='"+user_id+"'";
            return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
        
//更新IC卡表信息
        public int updateEntity(String card_id,String seat_id,String user_id,String user_phone,String user_addr,String car_num)
        {
            String sqlCmd="Update Card set seat_id='" + seat_id + "',user_id='" + user_id + "',user_phone='" + user_phone + "',user_addr='" + user_addr + "',car_num='" + car_num + "' where card_id='"+card_id+"'";
            return SQLUtil.executeNonQuery(sqlCmd, null);
        }
        
//插入IC卡表信息
        public int insertEntity(String card_id,String seat_id,String user_id,String user_phone,String user_addr,String car_num)
        {
            String sqlCmd="Insert into Card values('" + card_id + "','" + seat_id + "','" + user_id + "','" + user_phone + "','" + user_addr + "','"+car_num+"')";
            return SQLUtil.executeNonQuery(sqlCmd, null);
        }
        
//检查插入主键是否重复
        public boolean checkExist(String user_id)
        {
            String sqlCmd="select *from Card where user_id='"+user_id+"'";
            if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) )
            {
                return true;
            }
            return false;
        }

//获取分页总数
		public Object getPageCount()
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM Card ";
			return SQLUtil.excuteScalar(sqlCmd, null);
		}

//根据查询条件获取分页总数
		public Object getPageCountByWhere(String where)
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM Card where "+where;
			return SQLUtil.excuteScalar(sqlCmd, null);
		}
		
		
		 public boolean checkExist_car_num(String car_num)
	        {
	            String sqlCmd="select count(*) from card where car_num='"+car_num+"'";
	            if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) )
	            {
	            	return true;
	            }
	            
	            return false;
	        }
		 public boolean checkExist_seat_id(String seat_id)
	        {
	            String sqlCmd="select count(*) from card where seat_id='"+seat_id+"'";
	            if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) )
	            {
	            	return true;
	            }
	            
	            return false;
	        }
		 public int Update_seat_tag (String seat_tag,String seat_id)	//根据seat_id寻找到车主的车位将其车位状态改为占用1		
			{
				String sqlCmd="update seat set seat_tag='"+seat_tag+"' where seat_id='"+seat_id+"'";
	            return SQLUtil.executeNonQuery(sqlCmd, null);
			}
            
        }
