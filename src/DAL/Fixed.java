 package DAL;
import java.util.*;   
import DBUtil.SQLUtil;        
public class Fixed {

		//获取固定车主出入记录表信息列表
		public List<Object> getEntity()
		{
			String sqlCmd="select *from fixed";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
            
         //获取分页后固定车主出入记录表信息列表
		public List<Object> getEntity(int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from fixed limit "+size+",15";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
		
		
		
		
		
		 //获取未出场车辆
		public List<Object> getNoOut(int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from fixed where out_date='1111-11-11' limit "+size+",15";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
                
        //根据查询条件sqlWhere获取分页后固定车主出入记录表信息列表
		public List<Object> getEntityByWhere(String sqlWhere,int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from fixed where "+sqlWhere+" limit "+ size+",15";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
        
        //删除固定车主出入记录表信息
        public int deleteEntity(String fixed_id)
        {
            String sqlCmd="delete from fixed where fixed_id='"+fixed_id+"'";
            return DBUtil.SQLUtil.executeNonQuery(sqlCmd, null);//执行非查询操作executeNonQuery
        }
        
        //根据固定车主出入记录表编号获取固定车主出入记录表信息
        public List<Object> getEntityById(String fixed_id)
        {
            String sqlCmd="select *From fixed where fixed_id='"+fixed_id+"'";
            return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
        
      //根据固定车主car_num编号获取固定车主出入记录表信息用户查看信息
        public List<Object> getEntityById_CUS(String car_num)
        {
            String sqlCmd="select *From fixed where car_num='"+car_num+"'";
            return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
        
        
        
        
        //更新固定车主出入记录表信息
        public int updateEntity(String fixed_id,String card_id,String entry_date,String entry_time,String out_date,String out_time)
        {
            String sqlCmd="Update fixed set card_id='" + card_id + "',entry_date='" + entry_date + "',entry_time='" + entry_time + "',out_date='" + out_date + "',out_time='" + out_time + "' where fixed_id='"+fixed_id+"'";
            return SQLUtil.executeNonQuery(sqlCmd, null);
        }
        
        public int setOut(String fixed_id,String out_date,String out_time )
        {
        	String sqlCmd="update fixed set out_date='"+out_date+"',out_time='"+out_time+"' where fixed_id='"+fixed_id+"'";
        	 return SQLUtil.executeNonQuery(sqlCmd, null);
        }
        
        
        
        
        
        //插入固定车主出入记录表信息
        public int insertEntity(String fixed_id,String card_id,String entry_date,String entry_time,String out_date,String out_time ,String car_num)
        {
            String sqlCmd="Insert into fixed values('" + fixed_id + "','" + card_id + "','" + entry_date + "','" + entry_time + "','" + out_date + "','"+out_time+"','"+car_num+"')";
            return SQLUtil.executeNonQuery(sqlCmd, null);
        }
        
        //检查插入主键是否重复
        public boolean checkExist(String fixed_id)
        {
            String sqlCmd="select count(*) from fixed where fixed_id='"+fixed_id+"'";
            if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) )
            {
            	return false;
            }
           
            return true;
        }
 

		//获取分页总数
		public Object getPageCount()
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM fixed ";
			return SQLUtil.excuteScalar(sqlCmd, null);
		}

		//根据查询条件获取分页总数
		public Object getPageCountByWhere(String sqlWhere)
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM fixed where "+sqlWhere;
			return SQLUtil.excuteScalar(sqlCmd, null);
		}
		
		public String getCarNum( String card_id)
		{
			String sqlCmd="select car_num from card where card_id='"+card_id+"'";
			String result=SQLUtil.excuteScalar(sqlCmd, null).toString();
			return result;
		}
		
		public Object 	InsertParking(String seat_state,String seat_id)	//根据seat_id寻找到车主的车位将其车位状态改为占用1		
		{
			String sqlCmd="update seat set seat_state='"+seat_state+"' where seat_id='"+seat_id+"'";
            return SQLUtil.executeNonQuery(sqlCmd, null);
		}
		
		
		public List<Object> getEntity_card_id(String card_id)//根据车主的card来获取车主的sead_id,使用seat_id更改seat_state
        {
            String sqlCmd="select *From card where card_id='"+card_id+"'";
            return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
		
		
		public List<Object> getEntity_out_date(String out_date)//根据车主的card来获取车主的sead_id,使用seat_id更改seat_state
        {
            String sqlCmd="select *From fixed where out_date='"+out_date+"'";
            return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
        }
		
		
		
		
//		public List<Object> getEntity_out_date(String out_date)//根据车主的card来获取车主的sead_id,使用seat_id更改seat_state
//        {
//            String sqlCmd="select *From fixed where out_date='"+out_date+"'";
//            return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
//        }
		
		
		
		
		public List<Object> getEntity_fixed_id(String fixed_id)//根据fixed_id来查找车主的card_id
		{
			String sqlCmd="select *From fixed where fixed_id='"+fixed_id+"'";
            return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
		public List<Object> getEntityFixed_card_id(String card_id)//根据fixed_id来查找车主的card_id
		{
			String sqlCmd="select *From fixed where card_id='"+card_id+"'";
            return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
//		 public boolean checkExist_card_id(String card_id)//判断card——id
//	        {
//	            String sqlCmd="select count(*) from fixed where card_id='"+card_id+"'";
//	            if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) )
//	            {
//	            	return true;//Card_id存在
//	            }
//	            
//	            return false;
//	        }
//		public int checkExist_card_id(String card_id)//判断card——id
//        {
//            String sqlCmd="select 1  from fixed where card_id='"+card_id+"' limit 1";
//           
//            int	i=	Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString());
//            System.out.println("这是checkExist_card_id打印i");
//            System.out.println(i);
//            return   i;
          
            	
           
            
           
//        }
		 
		 public List<Object> getEntitySeat_seat_state(String seat_id)//根据card_id来返回card_id集合来查看车主的seat_state
			{
				String sqlCmd="select *From seat where seat_id='"+seat_id+"'";
	            return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
			}
		 
		
		
            
}
