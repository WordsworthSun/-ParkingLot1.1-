package DAL;

import java.util.List;

import DBUtil.SQLUtil;

public class Statis {

		
	private String date;
	private float money;
	
	public Statis(){}
	
	public Statis(String date,float money){
		this.date = date;
		this.money = money;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

			public Object getPageCount()
			{
				String sqlCmd="SELECT CEIL( COUNT(*)/15.0) FROM temp ";
				return SQLUtil.excuteScalar(sqlCmd, null);
			}
	//获取临时车主出入记录表信息列表
			public List<Object> getEntity()
			{			
				String sqlCmd="select *from temp";
				return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
			
			}
			public List<Object> getEntity_User()
			{			
				String sqlCmd="select *from user";
				return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
			
			}
			public List<Object> getEntity_Card()
			{			
				String sqlCmd="select *from card";
				return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
			
			}
			public List<Object> getEntity_Seat()
			{			
				String sqlCmd="select *from seat";
				return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
			
			}
	            
	//获取分页后零时车主出入记录表信息列表
			public List<Object> getEntity(int page)
			{
				int size=(page-1)*15;
				String sqlCmd="select *from temp limit "+size+",15";
				return DBUtil.SQLUtil.executeQuery(sqlCmd, null);//执行查询操作executeQuery
			}
	
	
	
	
	
	
}

