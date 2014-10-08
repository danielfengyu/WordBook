package cn.fengyu.wordbook.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import cn.fengyu.wordbook.bean.JdbcBean;
import cn.fengyu.wordbook.controller.DialogAction;
import cn.fengyu.wordbook.controller.DialogActionApdater;
import cn.fengyu.wordbook.controller.DialogActionEnsure;
import cn.fengyu.wordbook.jdbc.JdbcHelper;
import cn.fengyu.wordbook.windows.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
public class WordbookFirstPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblImag;
	private JPanel panelImage;
	private JdbcHelper jh=new JdbcHelper();

	private IWindowFactory listenWindowFactory=new ConcreteListenWindowFactory();
	private IWindowFactory wordList4Factory=new ConcreteGET4WindowFactory();
	private IWindowFactory wordList6Factory=new ConcreteGET6WindowFactory();
	private IWindowFactory wordListIELTSFactory=new ConcreteIELTSWindowFactory();
	private IWindowFactory wordListTOELFFactory=new ConcreteTOELFWindowFactory();
	private DialogActionEnsure dae;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WordbookFirstPage frame = new WordbookFirstPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public WordbookFirstPage() {
		JdbcBean jdbcBean=JdbcBean.getInstance();
		Connection con=null;
		setTitle("WordBook");
		setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 766, 437);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u9009\u62E9\u5355\u8BCD\u672C");
		menuBar.add(menu);
		
		JMenuItem menuItemGet6 = new JMenuItem("\u5927\u5B66\u516D\u7EA7");
		menuItemGet6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
            	EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        					wordList6Factory.getFrame().setVisible(true);
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		});
			}
		});
		menu.add(menuItemGet6);
		
		JMenuItem menuItemGet4 = new JMenuItem("\u5927\u5B66\u56DB\u7EA7");
		menuItemGet4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				setVisible(false);
            	EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        					wordList4Factory.getFrame().setVisible(true);
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		});
			}
		});
		menu.add(menuItemGet4);
		
		JMenuItem menuItemToefl = new JMenuItem("TOEFL");
		menuItemToefl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				setVisible(false);
            	EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        					wordListTOELFFactory.getFrame().setVisible(true);
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		});
			}
		});
		menu.add(menuItemToefl);
		
		JMenuItem menuItemYasi = new JMenuItem("\u96C5\u601D");
		menuItemYasi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				setVisible(false);
            	EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        					wordListIELTSFactory.getFrame().setVisible(true);
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		});
			}
		});
		menu.add(menuItemYasi);
		
		JMenu menu_1 = new JMenu("\u81EA\u5B9A\u4E49\u80CC\u5355\u8BCD\u6A21\u5F0F");
		menuBar.add(menu_1);
		
		JMenuItem menuItemDictate = new JMenuItem("\u542C\u5199\u6A21\u5F0F");
		menuItemDictate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aet) {
				setVisible(false);
            	EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        					listenWindowFactory.getFrame().setVisible(true);
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		});
			}
		});
		menu_1.add(menuItemDictate);
		
		JMenu menu_4 = new JMenu("\u5B66\u4E60\u6A21\u5F0F");
		menu_1.add(menu_4);
		
		JMenuItem menuItemComPic = new JMenuItem("\u56FE\u7247\u5BF9\u7167");
		menu_4.add(menuItemComPic);
		
		JMenuItem blackboard = new JMenuItem("\u6D82\u9E26\u5B66\u4E60");
		blackboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Blackboard frame = new Blackboard();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		menu_4.add(blackboard);
		
		JMenu menu_2 = new JMenu("\u64CD\u4F5C");
		menuBar.add(menu_2);
		
		JMenu menuOneMinutes = new JMenu("\u8BBE\u7F6E\u5B9A\u65F6");
		menu_2.add(menuOneMinutes);
		
		JMenuItem menuItem_5 = new JMenuItem("1\u5206\u949F");
		menuOneMinutes.add(menuItem_5);
		
		JMenuItem menuItemFiveMinutes = new JMenuItem("5\u5206\u949F");
		menuOneMinutes.add(menuItemFiveMinutes);
		
		JMenuItem menuItemTenMinutes = new JMenuItem("10\u5206\u949F");
		menuOneMinutes.add(menuItemTenMinutes);
		
		JMenuItem menuItemAddWord = new JMenuItem("\u6DFB\u52A0\u8BCD\u6C47");
		menuItemAddWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
            	EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        					IWindowFactory addWordFactory=new ConcreteAddWordWindowFactory();
        					addWordFactory.getFrame().setVisible(true);
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		});
			}
		});
		menu_2.add(menuItemAddWord);
		
		JMenuItem menuItemExit = new JMenuItem("\u9000\u51FA");
		menuItemExit.addActionListener(new ActionListener() {//弹出对话框，确定是否退出
			public void actionPerformed(ActionEvent arg0) {
				DialogAction da=new DialogActionApdater(dae);
				da.exitSystem();

			}
		});
		menu_2.add(menuItemExit);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 395, 367);
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 395, 367);
		panel.add(scrollPane);
		
		panelImage = new JPanel();
        panelImage.setBounds(405, 0, 385, 367);
        contentPane.add(panelImage);
        //panelImage.setToolTipText("图片");
        panelImage.setBorder(new TitledBorder(null, "图片", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelImage.setLayout(null);       
       try   { 
    	   Class.forName(jdbcBean.JDriver);
	       con=DriverManager.getConnection(jdbcBean.connectDB,jdbcBean.username,jdbcBean.password);
           Statement   stmt   =   con.createStatement(); 
           String   sqlCode   =   "select * from Word";//只提取三个字段 
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
