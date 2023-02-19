package DAL;
import java.util.*;
import DBUtil.SQLUtil;
public class Temp {

//获取临时车主出入记录表信息列表
		public List<Object> getEntity()
		{
			String sqlCmd="select *from temp";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
            
//获取分页后零时车主出入记录表信息列表
		public List<Object> getEntity(int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from temp limit "+size+",15";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
                
//根据查询条件sqlWhere获取分页后零时车主出入记录表信息列表
		public List<Object> getEntityByWhere(String sqlWhere,int page)
		{
			int size=(page-1)*15;
			String sqlCmd="select *from temp where "+sqlWhere+" limit "+ size+",15";
			return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
                }
                
//删除零时车主出入记录表信息
                public int deleteEntity(String temp_id)
                {
                    String sqlCmd="delete from temp where temp_id='"+temp_id+"'";
                    return DBUtil.SQLUtil.executeNonQuery(sqlCmd, null);//执行非查询操作executeNonQuery
                }
                
//根据车主出入记录表编号获取车主出入记录表信息
                public List<Object> getEntityById(String temp_id)
                {
                    String sqlCmd="select *From temp where temp_id='"+temp_id+"'";
                    return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
                }
                
//根据车主出入记录表的车牌号获取车主出入记录表信息                
                public List<Object> getEntityById_CUS(String car_num)
                {
                    String sqlCmd="select *From temp where car_num='"+car_num+"'";
                    return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
                }  
                
//更新临时车主出入记录表信息
                public int updateEntity(String temp_id,String card_id,String car_num,String entry_date,String entry_time,String out_date,String out_time,String temp_money,String seat_id)
                {
                    String sqlCmd="Update temp set card_id='" + card_id + "',car_num='" + car_num + "',entry_date='" + entry_date + "',entry_time='" + entry_time + "',out_date='" + out_date + "',out_time='" + out_time + "',temp_money='" + temp_money + "',seat_id='" + seat_id + "' where temp_id='"+temp_id+"'";
                    return SQLUtil.executeNonQuery(sqlCmd, null);
                }
                
                               
//插入临时车主出入记录表信息
                public int insertEntity(String temp_id,String card_id,String car_num,String entry_date,String entry_time,String out_date,String out_time,String temp_money,String seat_id)
                {
                    String sqlCmd="Insert into temp values('" + temp_id + "','" + card_id + "','" + car_num + "','" + entry_date + "','" + entry_time + "','" + out_date + "','" + out_time + "','"+temp_money+"','"+seat_id+"')";
                    return SQLUtil.executeNonQuery(sqlCmd, null);
                }
                
                
//检查插入主键是否重复
                public boolean checkExist(String card_id)
                {
                    String sqlCmd="select count(*) from temp where card_id='"+card_id+"'";
                    if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) )
                    {
                        return true;
                    }
                    return false;
                }

//获取分页总数
		public Object getPageCount()
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM temp ";
			return SQLUtil.excuteScalar(sqlCmd, null);
		}

//根据查询条件获取分页总数
		public Object getPageCountByWhere(String sqlWhere)
		{
			String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM temp where "+sqlWhere;
			return SQLUtil.excuteScalar(sqlCmd, null);
		}
		public int Update_seat_state (int seat_state,String seat_id)	//根据seat_id寻找到车主的车位将其车位状态改为占用1		
		{
			String sqlCmd="update seat set seat_state='"+seat_state+"' where seat_id='"+seat_id+"'";
            return SQLUtil.executeNonQuery(sqlCmd, null);
		}
//		public Object 	InsertParking(String seat_state,String seat_id)	//根据seat_id寻找到车主的车位将其车位状态改为占用1		
//		{
//			String sqlCmd="update seat set seat_state='"+seat_state+"' where seat_id='"+seat_id+"'";
//            return SQLUtil.executeNonQuery(sqlCmd, null);
//		}
		public List<Object> getEntityTemp_card_id(String card_id)//根据fixed_id来查找车主的card_id
		{
			String sqlCmd="select *From temp where card_id='"+card_id+"'";
            return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
		}
		
		
		
		
		
            
        }
