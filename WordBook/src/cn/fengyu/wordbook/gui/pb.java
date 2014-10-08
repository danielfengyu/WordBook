package cn.fengyu.wordbook.gui;

import java.awt.*; 
import java.awt.event.*; 
import java.util.*;
import javax.swing.*;
import java.awt.geom.*;
import java.io.*; 
class Point implements Serializable {
	int x,y;
	Color col;
	int tool;
	int boarder;
	Point(int x, int y, Color col, int tool, int boarder) { 
		this.x = x; 
		this.y = y; 
		this.col = col; 
		this.tool = tool;
		this.boarder = boarder;
		} 
	} 
class paintboard extends JFrame implements ActionListener,MouseMotionListener,
MouseListener,ItemListener {
	int x = -1, y = -1; 
	int con = 1;//画笔大小
	int Econ = 5;//橡皮大小
	int toolFlag = 0;//toolFlag:工具标记 
	//toolFlag工具对应表： 
	//（0--画笔）；（1--橡皮）；（2--清除）；
	//（3--直线）；（4--圆）；（5--矩形）；
	Color c = new Color(0,0,0); //画笔颜色 
	BasicStroke size = new BasicStroke(con,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);//画笔粗细 
	Point cutflag = new Point(-1, -1, c, 6, con);//截断标志
	Vector pointInfo = null;//点信息向量组 
	int n = 1; 
	FileInputStream picIn = null;
	FileOutputStream picOut = null; 
	ObjectInputStream VIn = null; 
	ObjectOutputStream VOut = null; 
	// *工具面板--画笔，直线，圆，矩形，多边形,橡皮，清除*/
	Panel toolPanel; 
	JButton eraser, drLine,drCircle,drRect;
	JButton clear ,pen; 
	Choice ColChoice,SizeChoice,EraserChoice;
	JButton colchooser;
	JLabel color,sizeB,sizeE; 
	//保存功能 
	JButton openPic,savePic; 
	FileDialog openPicture,savePicture; 
	paintboard(String s) { 
		
		super(s); 
		
		addMouseMotionListener(this);
		addMouseListener(this); 
		pointInfo = new Vector(); 
		/*各工具按钮及选择项*/
		//颜色选择 
		ColChoice = new Choice();
		ColChoice.add("black");
		ColChoice.add("red"); 
		ColChoice.add("blue");
		ColChoice.add("green");
		ColChoice.addItemListener(this); 
		//画笔大小选择 
		SizeChoice = new Choice(); 
		SizeChoice.add("1");
		SizeChoice.add("3"); 
		SizeChoice.add("5");
		SizeChoice.add("7");
		SizeChoice.add("9");
		SizeChoice.addItemListener(this); 
		//橡皮大小选择
		EraserChoice = new Choice();
		EraserChoice.add("5"); 
		EraserChoice.add("9"); 
		EraserChoice.add("13"); 
		EraserChoice.add("17"); 
		EraserChoice.addItemListener(this); 
		
		//////////////////////////////////////////////////// 
		toolPanel = new Panel(); 
		clear = new JButton("清除"); 
		eraser = new JButton("橡皮"); 
		pen = new JButton("画笔"); 
		drLine = new JButton("画直线");
		drCircle = new JButton("画圆形"); 
		drRect = new JButton("画矩形"); 
		openPic = new JButton("打开图画"); 
		savePic = new JButton("保存图画");
		colchooser = new JButton("显示调色板");
		//各组件事件监听
		clear.addActionListener(this); 
		eraser.addActionListener(this);
		pen.addActionListener(this);
		drLine.addActionListener(this);
		drCircle.addActionListener(this);
		drRect.addActionListener(this);
		openPic.addActionListener(this);
		savePic.addActionListener(this); 
		colchooser.addActionListener(this);
		color = new JLabel("画笔颜色",JLabel.CENTER);
		color.setText("画笔颜色");
		sizeB = new JLabel("画笔大小",JLabel.CENTER);
		sizeE = new JLabel("橡皮大小",JLabel.CENTER); 
		//面板添加组件
		toolPanel.add(openPic); 
		toolPanel.add(savePic); 
		toolPanel.add(pen); 
		toolPanel.add(drLine); 
		toolPanel.add(drCircle); 
		toolPanel.add(drRect); 
		toolPanel.add(color); 
		toolPanel.add(ColChoice);
		toolPanel.add(sizeB); 
		toolPanel.add(SizeChoice);
		toolPanel.add(colchooser); 
		toolPanel.add(eraser); 
		toolPanel.add(sizeE); 
		toolPanel.add(EraserChoice);
		toolPanel.add(clear); 
		//工具面板到APPLET面板 
		add(toolPanel,BorderLayout.NORTH); 
		setBounds(60,60,1100,600); 
		setVisible(true); 
		validate();
		//dialog for save and load 
		openPicture = new FileDialog(this,"打开图画",FileDialog.LOAD); 
		openPicture.setVisible(false); 
		savePicture = new FileDialog(this,"保存图画",FileDialog.SAVE); 
		savePicture.setVisible(false); 
		openPicture.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				openPicture.setVisible(true);
				}
			});
		savePicture.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) { 
				savePicture.setVisible(false);
				} });
		addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) { 
				System.exit(0);
				}
			});
		} 
	public void paint(Graphics g) { 
		
		Graphics2D g2d = (Graphics2D)g; 
		Point p1,p2; 
		n = pointInfo.size();
		if(toolFlag==2)
			g.clearRect(0,0,getSize().width,getSize().height);//清除 
		for(int i=0; i<n ;i++){
			p1 = (Point)pointInfo.elementAt(i);
			p2 = (Point)pointInfo.elementAt(i+1);
			size = new BasicStroke(p1.boarder,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
			g2d.setColor(p1.col);
			g2d.setStroke(size);
			if(p1.tool==p2.tool) { 
				switch(p1.tool) {
				case 0://画笔 
					Line2D line1 = new Line2D.Double(p1.x, p1.y, p2.x, p2.y); 
					g2d.draw(line1); 
					break; 
				case 1://橡皮 
						g.clearRect(p1.x, p1.y, p1.boarder, p1.boarder);
						break; 
				case 3://画直线
							Line2D line2 = new Line2D.Double(p1.x, p1.y, p2.x, p2.y);
							g2d.draw(line2);
							break; 
				case 4://画圆 
								Ellipse2D ellipse = new 
								Ellipse2D.Double(p1.x, p1.y, Math.abs(p2.x-p1.x) , Math.abs(p2.y-p1.y));
								g2d.draw(ellipse); 
								break; 
				case 5://画矩形 
									Rectangle2D rect = new Rectangle2D.Double(p1.x, p1.y,
									Math.abs(p2.x-p1.x) , Math.abs(p2.y-p1.y));
									g2d.draw(rect); 
									break;
				case 6://截断，跳过
										i=i+1; 
										break; 
										default :
					}//end switch
				}//end if 
			}//end for
		} 
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==ColChoice)//预选颜色
			{ String name = ColChoice.getSelectedItem();
			if(name=="black") {
				c = new Color(0,0,0);
				}
			else if(name=="red") {
					c = new Color(255,0,0);
					} 
			else if(name=="green") {
				c = new Color(0,255,0);
				} 
			else if(name=="blue") {
				c = new Color(0,0,255);
				} 
			} 
		else if(e.getSource()==SizeChoice)//画笔大小 
			{ 
			String selected = SizeChoice.getSelectedItem();
			if(selected=="1") { 
				con = 1; 
				size = new BasicStroke(con,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL); 
				} 
			else if(selected=="3") { 
				con = 3; 
				size = new BasicStroke(con,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
				} 
			else if(selected=="5") {
				con = 5; 
				size = new BasicStroke(con,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
				} 
			else if(selected=="7") {
				con = 7;
				size = new BasicStroke(con,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL); 
				} 
			else if(selected=="9") {
				con = 9;
				size = new BasicStroke(con,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
				} 
			} 
		else if(e.getSource()==EraserChoice)//橡皮大小 
			{ 
			String Esize = EraserChoice.getSelectedItem();
			if(Esize=="5") { 
				Econ = 5*2; 
				} 
			else if(Esize=="9") { 
				Econ = 9*2; 
				} 
			else if(Esize=="13") {
				Econ = 13*2;
				}
			else if(Esize=="17") { 
				Econ = 17*3;
				} 
			}
		} 
	public void mouseDragged(MouseEvent e) { 
		Point p1 ; 
		switch(toolFlag){
		case 0://画笔 
			x = (int)e.getX(); 
			y = (int)e.getY();
			p1 = new Point(x, y, c, toolFlag, con);
			pointInfo.addElement(p1);
			repaint();
			break;
		case 1://橡皮
				x = (int)e.getX();
				y = (int)e.getY();
				p1 = new Point(x, y, null, toolFlag, Econ);
				pointInfo.addElement(p1);
				repaint(); 
				break; 
				default : 
					} 
		} 
	public void mouseMoved(MouseEvent e) {} 
	
	public void update(Graphics g) { 
		paint(g);
	} 
	
	public void mousePressed(MouseEvent e) {
		Point p2;
		switch(toolFlag){ 
		case 3://直线 
			x = (int)e.getX();
			y = (int)e.getY(); 
			p2 = new Point(x, y, c, toolFlag, con);
			pointInfo.addElement(p2);
			break; 
		case 4: //圆 
				x = (int)e.getX();
				y = (int)e.getY();
				p2 = new Point(x, y, c, toolFlag, con); 
				pointInfo.addElement(p2);
		break; case 5: //矩形
					x = (int)e.getX();
					y = (int)e.getY();
					p2 = new Point(x, y, c, toolFlag, con); 
					pointInfo.addElement(p2); 
		break; default : 
	} 
} 
	public void mouseReleased(MouseEvent e) { 
		Point p3;
		switch(toolFlag){ 
		case 0://画笔 
			pointInfo.addElement(cutflag); 
			break; 
			case 1: //eraser
				pointInfo.addElement(cutflag); 
				break; 
				
			case 3://直线 
					x = (int)e.getX(); 
					y = (int)e.getY();
					p3 = new Point(x, y, c, toolFlag, con);
					pointInfo.addElement(p3);
					pointInfo.addElement(cutflag); 
					repaint();
					break; 
			case 4: //圆
						x = (int)e.getX();
						y = (int)e.getY(); 
						p3 = new Point(x, y, c, toolFlag, con);
						pointInfo.addElement(p3);
						pointInfo.addElement(cutflag); 
						repaint(); 
						break; 
			case 5: //矩形
							x = (int)e.getX();
							y = (int)e.getY();
							p3 = new Point(x, y, c, toolFlag, con);
							pointInfo.addElement(p3);
							pointInfo.addElement(cutflag); 
							repaint();
							break; 
							default:
			} 
}
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource()==pen)//画笔 
			{toolFlag = 0;}
		if(e.getSource()==eraser)//橡皮 
			{
				toolFlag = 1;
			}
		if(e.getSource()==clear)//清除 
			{ 
				toolFlag = 2; 
				pointInfo.removeAllElements();
				
				repaint();
			} 
		if(e.getSource()==drLine)//画线
			{
			toolFlag = 3;
			} 
		if(e.getSource()==drCircle)//画圆
			{
				toolFlag = 4;
			} 
		if(e.getSource()==drRect)//画矩形
			{toolFlag = 5;} 
		if(e.getSource()==colchooser)//调色板 
			{ 
				Color newColor = JColorChooser.showDialog(this,"调色板",c);
				c = newColor;
			} 
		if(e.getSource()==openPic)//打开图画
			{ 
			openPicture.setVisible(true);
			if(openPicture.getFile()!=null) {
				int tempflag; 
				tempflag = toolFlag; 
				toolFlag = 2 ; 
				repaint(); 
				try{ 
					pointInfo.removeAllElements(); 
					File filein = new File(openPicture.getDirectory(),openPicture.getFile()); 
					picIn = new FileInputStream(filein);
					VIn = new ObjectInputStream(picIn);
					pointInfo = (Vector)VIn.readObject();
					VIn.close(); repaint(); toolFlag = tempflag;
					} catch(ClassNotFoundException IOe2) { 
						repaint(); 
						toolFlag = tempflag; 
						System.out.println("can not read object");
					} catch(IOException IOe) { 
							repaint(); 
							toolFlag = tempflag;
							System.out.println("can not read file");
					} 
				} 
			} 
		if(e.getSource()==savePic)//保存图画
			{
			savePicture.setVisible(true);
			try{
				File fileout = new File(savePicture.getDirectory(),savePicture.getFile());
				picOut = new FileOutputStream(fileout); 
				VOut = new ObjectOutputStream(picOut); 
				VOut.writeObject(pointInfo); 
				VOut.close();
				} catch(IOException IOe) {
					System.out.println("can not write object"); 
					} 
			} 
		}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}//end paintboard 
public class pb { 
	public static void main(String args[]) {
		paintboard paintboard_ = new paintboard("画图程序"); 
		paintboard_.getContentPane().setBackground(Color.BLACK);
	}
}
	