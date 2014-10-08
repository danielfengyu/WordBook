package cn.fengyu.wordbook.bean;
/**
 * 单列，数据库的哥连接字符串子保留在一个对象中，节省空间，方便扩展。
 * @author 冯玉
 *
 */
public class JdbcBean {
	
	 public  static String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
	 public  static String connectDB="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=WordBook" ;//连接字符串
	 public  String username="sa";
	 public  String password="dayingfengyu";
	 private static JdbcBean jdbcConnString;
	 private  JdbcBean() {
		// TODO Auto-generated constructor stub
		 super();
	} 
	 
	public  static JdbcBean getInstance() {
		if(jdbcConnString==null){
				jdbcConnString=new JdbcBean();
			}
			return jdbcConnString;
	}

}
