package cn.fengyu.wordbook.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import cn.fengyu.wordbook.bean.JdbcBean;

public class JdbcHelper {
	public static void main(String[] args) {
		
		JdbcHelper jh=new JdbcHelper();
		jh.insertUserInfo("gggg", "gghhh", "fengyu");
		//jh.getImagePath("apple");
		//jh.insertWordInfo("brother", "¸ç¸ç", 7, "ËÄ¼¶", "ffff", "ggggg");
	}
	public void insertUserInfo(String username,String password,String email){
		JdbcBean jdbcBean=JdbcBean.getInstance(); 
		Connection con=null;
		 try{
			 	Class.forName(JdbcBean.JDriver);
		        con=DriverManager.getConnection(JdbcBean.connectDB,jdbcBean.username,jdbcBean.password);
		        Statement statement=con.createStatement();
		        statement.executeQuery("insert into wordbookuser values" +
		        		"('"+username+"','"+password+"','"+email+"')");
		        con.close();
		  }catch(Exception e){
			  	System.out.println(e.getMessage());
		}
	}
	public String getImagePath(String word){
		 String sw = null;
		 JdbcBean jdbcBean=JdbcBean.getInstance(); 
		 Connection con=null;
		 try{
			 	Class.forName(JdbcBean.JDriver);
		        con=DriverManager.getConnection(JdbcBean.connectDB,jdbcBean.username,jdbcBean.password);
		        Statement statement=con.createStatement();
		        ResultSet rs=statement.executeQuery("select imagePath from Word where word='"+word+"'");
		        while(rs.next()){
		        	sw=rs.getString("imagePath");
		        	//System.out.println(sw);
		        }
		        con.close();
		  }catch(Exception e){
			  	System.out.println(e.getMessage());
		}
		 return sw;
	}
	public static void insertWordInfo(String word,String mean,int length,
			String type,String imagePath,String textPath){
			JdbcBean jdbcBean=JdbcBean.getInstance();
			Connection con=null;
			try{
					Class.forName(JdbcBean.JDriver);
			        con=DriverManager.getConnection(JdbcBean.connectDB,jdbcBean.username,jdbcBean.password);
			        Statement statement=con.createStatement();
			        statement.executeQuery("insert into Word values('"+word+"','"
			        +mean+"','"+length+"','"+imagePath+"','"+textPath+"','"+type+"')");
			        con.close();
			}catch(Exception e){
			  		System.out.println(e.getMessage());
			}
	}
}
