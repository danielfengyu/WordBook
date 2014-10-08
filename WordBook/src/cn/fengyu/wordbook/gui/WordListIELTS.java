package cn.fengyu.wordbook.gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import cn.fengyu.wordbook.bean.JdbcBean;
import cn.fengyu.wordbook.controller.DialogAction;
import cn.fengyu.wordbook.controller.DialogActionApdater;
import cn.fengyu.wordbook.controller.DialogActionEnsure;
import cn.fengyu.wordbook.jdbc.JdbcHelper;
import cn.fengyu.wordbook.windows.ConcreteFirstPageWindowFactory;
import cn.fengyu.wordbook.windows.IWindowFactory;

@SuppressWarnings("serial")
public class WordListIELTS extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblImag;
	private JPanel panelImage;
	private JdbcHelper jh=new JdbcHelper();
	private IWindowFactory firstPageWindowFactory=new ConcreteFirstPageWindowFactory();

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "static-access"})
	public WordListIELTS() {
		JdbcBean jdbcBean=JdbcBean.getInstance();
		 Connection con=null;
		setTitle("IELTS单词表");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 463);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u64CD\u4F5C");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u8FD4\u56DE");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aet) {
				setVisible(false);
            	EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        					firstPageWindowFactory.getFrame().setVisible(true);
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		});
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u9000\u51FA");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogActionEnsure dae = new DialogActionEnsure();
				DialogAction da=new DialogActionApdater(dae);
				da.exitSystem();
			}
		});
		menu.add(menuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 395, 378);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 395, 374);
		panel.add(scrollPane);
		panelImage = new JPanel();
        panelImage.setBounds(405, 0, 385, 367);
        contentPane.add(panelImage);
        panelImage.setBorder(new TitledBorder(null, "图片", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelImage.setLayout(null);       
       try   { 
    	   Class.forName(jdbcBean.JDriver);
	        con=DriverManager.getConnection(jdbcBean.connectDB,jdbcBean.username,jdbcBean.password);
           Statement   stmt   =   con.createStatement(); 
           String   sqlCode   =   "select * from Word where property='IELTS'";//只提取三个字段 
           ResultSet   rs   =   stmt.executeQuery(sqlCode); 
           @SuppressWarnings("rawtypes")
		Vector   vect   =   new   Vector();//用于存放数据记录 
           vect.removeAllElements();//初始化向量对象 
           while(rs.next())   {//依次读取数据结果集 
               @SuppressWarnings("rawtypes")
			Vector   rec_vector=new   Vector();//从结果集中取数据放入向量rec_vector中 
               rec_vector.addElement(rs.getString(1)); 
               rec_vector.addElement(rs.getString(2)); 
               rec_vector.addElement(rs.getString(6));
               vect.addElement(rec_vector);//向量rec_vector加入向量vect中 
           } 
           //表列标题 
           String[]   titleStr   =   { "单词", "汉译", "类型"};//表列标题设定 
           @SuppressWarnings("rawtypes")
           Vector   title   =   new   Vector();//用于存放表列标题 
           title.removeAllElements(); 
           for(int   i=0;i <titleStr.length;i++)   { 
               title.addElement(titleStr[i]); 
           } 
           //生成表 
           table   =   new   JTable(vect,title);
           scrollPane.setViewportView(table);
           table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
           
           
           table.addMouseListener(new MouseAdapter() {
           	@Override
           	public void mouseClicked(MouseEvent e) {
           			//if(e.getClickCount()==2){
		           			int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //获得行位置 
		                    int  col=((JTable)e.getSource()).columnAtPoint(e.getPoint()); //获得列位置
		                    String cellVal=(String)(table.getValueAt(row,col)); //获得点击单元格数据
		                    String imagePath=jh.getImagePath(cellVal);
		                    ImageIcon image = new ImageIcon(imagePath); 
		                    image.setImage(image.getImage().getScaledInstance(380,340,Image.SCALE_DEFAULT));lblImag = new JLabel();
		                    lblImag.setBounds(0, 0, 385, 367);
		                    panelImage.add(lblImag);
		                    lblImag.setIcon(image);
		                    lblImag.setOpaque(true);
           			}
                    
           	//}
           });

           //释放资源 
           rs.close(); 
           stmt.close(); 
           con.close();
       } 
       catch(Exception   e)   { 
           System.out.println( "GetTableFromDB   error: "   +   e); 
       } 
	}


}
