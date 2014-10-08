package cn.fengyu.wordbook.gui;

import java.awt.BasicStroke;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JColorChooser;
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
import cn.fengyu.wordbook.bean.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComboBox;
import javax.swing.JLabel;
public class Blackboard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JPanel panelImage;
	private JdbcHelper jh=new JdbcHelper();
	private Vector list = new Vector();//存储笔迹上的各点的向量。
	private java.awt.Point startPos = null;
	private boolean moved = false;
	private Color color;
	private JComboBox<String> cmbxColor;
	private JComboBox<String> cmbxSize;
	private Graphics g; 
	private Graphics2D g2d;
	private float penSize;
	private int i=0;
	
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
					Blackboard frame = new Blackboard();
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
	public Blackboard() {
		JdbcBean jdbcBean=JdbcBean.getInstance();
		Connection con=null;
		setTitle("Blackboard");
		setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 914, 437);
		
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
		
		JMenu mnuBlackboardColor = new JMenu("选择黑板颜色");
		menuBar.add(mnuBlackboardColor);
		
		JMenuItem mnuWhite = new JMenuItem("\u767D\u8272");
		mnuWhite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 panelImage.setBackground(Color.WHITE);
			}
		});
		mnuBlackboardColor.add(mnuWhite);
		
		JMenuItem mnuBlack = new JMenuItem("\u9ED1\u8272");
		mnuBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 panelImage.setBackground(Color.black);
			}
		});
		mnuBlackboardColor.add(mnuBlack);
		
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
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 395, 367);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 395, 367);
		panel.add(scrollPane);
		
		    
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
           //释放资源 
           rs.close(); 
           stmt.close(); 
           con.close();
       } 
       catch(Exception   e)   { 
           System.out.println( "GetTableFromDB   error: "   +   e); 
       } 
       panelImage = new JPanel();
      
       panelImage.setBorder(new TitledBorder(null, "黑板", TitledBorder.LEADING, TitledBorder.TOP, null, null));
       panelImage.addMouseMotionListener(new MouseMotionAdapter() {
       	@Override
       	public void mouseDragged(MouseEvent e) {
       		if(moved){   
			    Point endPos = e.getPoint();
			    LineObject obj = new LineObject();
			    obj.start = startPos;
			    obj.end = endPos;			   
			    list.add(obj);
			    drawAllList();
			    startPos = e.getPoint();
		   }
       	}
       	@Override
       	public void mouseMoved(MouseEvent e) {
       		
       	}
       });
       panelImage.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mousePressed(MouseEvent e) {
       		moved = true;
		   startPos = e.getPoint();
       	}
       	@Override
       	public void mouseReleased(MouseEvent e) {
       	 moved = false;
		    Point endPos = e.getPoint();
		   
		    LineObject obj = new LineObject();
		    obj.start = startPos;
		    obj.end = endPos;
		   
       	}
       });
       panelImage.setBounds(405, 0, 385, 367);
       contentPane.add(panelImage);
       
       JButton btnPen = new JButton("\u753B\u7B14");
       btnPen.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {//获得画笔
       		Color col=new Color(255,255,255);
       		g = panelImage.getGraphics();
       		g2d=(Graphics2D)g;
    		Stroke stroke=new BasicStroke(3.0f);//设置线宽为3.0
    		g2d.setStroke(stroke);
    		g2d.setColor(col);
       	}
       });
       btnPen.setBounds(800, 10, 98, 21);
       contentPane.add(btnPen);
       
       cmbxColor = new JComboBox<String>();
       cmbxColor.addItem("white");
       cmbxColor.addItem("black");
       cmbxColor.addItem("red");
       cmbxColor.addItem("green");
       cmbxColor.addItem("blue");
       cmbxColor.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		int n=cmbxColor.getSelectedIndex();
       		System.out.println(n);
       		if(n==0){
       			color=new Color(255,255,255);
       		}else if(n==1){
       			color=new Color(0,0,0);
       		}else if(n==2){
       			color=new Color(255,0,0);
       		}else if(n==3){
       			color=new Color(0,255,0);
       		}else if(n==4){
       			color=new Color(0,0,255);
       		}
       	}
       });
       cmbxColor.setBounds(800, 70, 98, 21);
       contentPane.add(cmbxColor);
       
       cmbxSize = new JComboBox<String>();
       cmbxSize.addItem("1");
       cmbxSize.addItem("3");
       cmbxSize.addItem("5");
       cmbxSize.addItem("7");
       cmbxSize.addItem("9");
       cmbxSize.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		String size=cmbxSize.getSelectedItem().toString();
       		if(size.equals("1")){
       			penSize=1.0f;
       			
       		}else if(size.equals("3")){
       			penSize=3.0f;
       		}else if(size.equals("5")){
       			penSize=5.0f;
       		}else if (size.equals("7")){
       			penSize=7.0f;
       		}else if(size.equals("9")){
       			penSize=9.0f;
       		}
       	}
       });
       cmbxSize.setBounds(800, 168, 98, 21);
       contentPane.add(cmbxSize);
       JButton btnEraser = new JButton("黑板擦");
       btnEraser.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		
       	}
       });
       btnEraser.setBounds(800, 245, 98, 21);
       contentPane.add(btnEraser);
       
       JButton btnColorPan = new JButton("调色板");
       btnColorPan.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		Color newColor = JColorChooser.showDialog(null, "调色板", color);
			color = newColor;
       	}
       });
       btnColorPan.setBounds(800, 294, 98, 21);
       contentPane.add(btnColorPan);
       
       JButton btnClear = new JButton("清除");
       btnClear.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		list.clear();
			repaint();
			drawAllList();
       	}
       });
       btnClear.setBounds(800, 344, 98, 21);
       contentPane.add(btnClear);
       
       JLabel lblNewLabel = new JLabel("画笔颜色",JLabel.CENTER);
       lblNewLabel.setBounds(800, 45, 93, 15);
       contentPane.add(lblNewLabel);
       
       JLabel lblNewLabel_1 = new JLabel("画笔大小",JLabel.CENTER);
       lblNewLabel_1.setBounds(800, 143, 93, 15);
       contentPane.add(lblNewLabel_1);
	}
	public void drawAllList()
	{
//		g=panelImage.getGraphics();
//		g = panelImage.getGraphics();
		Stroke stroke=new BasicStroke(penSize);
		g2d.setStroke(stroke);
		g2d.setColor(color);
		LineObject obj;
		
		//for( i=0; i<list.size(); i++)
		/*
		 * i是存入List中的点，使用while循环，可以使笔迹接着上次，且两次的颜色，
		 * 大小可以不同，但能同时存在。
		 */
		while(i<list.size())
		{
		   obj = (LineObject)list.get(i);
		   
		   g2d.drawLine(obj.start.x, obj.start.y, obj.end.x, obj.end.y);
		   i++;
		}
		
	}
}
