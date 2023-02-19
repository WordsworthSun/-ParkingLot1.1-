package DAL;
import java.util.*;
import DBUtil.SQLUtil;
public class Messages {
	
	
//获取留言表信息列表
	public List<Object> getEntity()
			{
				String sqlCmd="select *from Message";
				return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
			}
	
	
	
//获取分页总数	            
	public Object getPageCount()
	{
		String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM Message ";
		return SQLUtil.excuteScalar(sqlCmd, null);
	}
	
	
	
//获取分页后Message表信息列表
	public List<Object> getEntity(int page)
	{
		int size=(page-1)*15;
		String sqlCmd="select *from Message limit "+size+",15";
		return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
	}

	
//根据查询条件获取分页总数
	public Object getPageCountByWhere(String sqlWhere)
	{
		String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM Message where "+sqlWhere;
		return SQLUtil.excuteScalar(sqlCmd, null);
	}
	            
	
//根据查询条件sqlWhere获取分页后IC卡表信息列表
	public List<Object> getEntityByWhere(String sqlWhere,int page)
	{
		int size=(page-1)*15;
		String sqlCmd="select *from Message where "+sqlWhere+" limit "+ size+",15";
		return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
	 }	

	
//根据message表编号获取message表信息前台Announce Edit使用
    public List<Object> getEntityById(String message_id)
    {
        String sqlCmd="select *From Message where message_id='"+message_id+"'";
        return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
    }
    	
	//根据真姓名获取数据
    
    public List<Object> getEntity_All(String real_name)
    {
        String sqlCmd="select *From Message where real_name='"+real_name+"'";
        return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
    }	
	
	
	
//删除留言表信息
	 public int deleteEntity(String message_id)
	        {
	            String sqlCmd="delete from Message where message_id='"+message_id+"'";
	            return DBUtil.SQLUtil.executeNonQuery(sqlCmd, null);//执行非查询操作executeNonQuery
	        }
	        
	        
	        
//更新留言表信息
	  public int updateEntity(String user_id,String title,String context,String time,String message_id)
	        {
	            String sqlCmd="Update Message set real_name='" + user_id + "',title='" + title + "',context='" + context +  "',time='" + time + "' where message_id='"+message_id+"'";
	            return SQLUtil.executeNonQuery(sqlCmd, null);
	        }
	        
//插入留言表信息
	  public int insertEntity(String user_id,String title,String context,String time ,String message_id)
	        {
	            String sqlCmd="Insert into Message values('" + user_id + "','" + title + "','" + context + "','" + time + "','"+message_id+"')";
	            return SQLUtil.executeNonQuery(sqlCmd, null);
	        }
	        
//检查插入主键是否重复
	   public boolean checkExist(String message_id)
	        {
	            String sqlCmd="select count(*) from V_Message where message_id='"+message_id+"'";
	            if(1==Integer.parseInt(SQLUtil.excuteScalar(sqlCmd, null).toString()) )
	            {
	                return true;
	            }
	            return false;
	        }

			

	
	
	

}
