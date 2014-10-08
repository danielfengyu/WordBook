package cn.fengyu.painttest;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;

import javax.swing.*;

@SuppressWarnings("serial")
public class Board extends JPanel{
	JRadioButton jrb1=new JRadioButton("Line");
	JRadioButton jrb2=new JRadioButton("Rectangle");
	JRadioButton jrb3=new JRadioButton("Oval");
	JRadioButton jrb4=new JRadioButton("Curve");
	JButton jbt = new JButton("Clear");
	BufferedImage bi=new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);;
	
	int x=-1;
	int y=-1;
	int [] location=new int[4];
	int mode=0;
	boolean ifNotCurveClicked=false;
	boolean ifCurveClicked=false;
	public Board(){
		setLayout(new BorderLayout());
		JPanel jp1=new JPanel();
		jp1.setLayout(new GridLayout(5,1,5,5));
		jp1.add(jrb1);
		jp1.add(jrb2);
		jp1.add(jrb3);
		jp1.add(jrb4);
		jp1.add(jbt);
		add(jp1,BorderLayout.WEST);
		ButtonGroup group = new ButtonGroup();
		group.add(jrb1);
		group.add(jrb2);
		group.add(jrb3);
		group.add(jrb4);
		
		ActionListener chooseListener = new chooseListener();
		MouseListener clearListener = new clearListener();
		jrb1.addActionListener(chooseListener);
		jrb2.addActionListener(chooseListener);
		jrb3.addActionListener(chooseListener);
		jrb4.addActionListener(chooseListener);
		jbt.addMouseListener(clearListener);
	}
	public void drawLine(){
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				location[0]=e.getX();
				location[1]=e.getY();
			}
			public void mouseReleased(MouseEvent e){
				if(!ifCurveClicked){
					location[2]=e.getX();
					location[3]=e.getY();
					mode=1;
					repaint();
				}
			}
		});
	}
	public void drawRectangle(){
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				location[0]=e.getX();
				location[1]=e.getY();
			}
			public void mouseReleased(MouseEvent e){
				if(!ifCurveClicked){
					location[2]=e.getX();
					location[3]=e.getY();
					mode=2;
					repaint();
				}
			}
		});
	}
	public void drawOval(){
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				location[0]=e.getX();
				location[1]=e.getY();
			}
			public void mouseReleased(MouseEvent e){
				if(!ifCurveClicked){
					location[2]=e.getX();
					location[3]=e.getY();
					mode=3;
					repaint();
				}
			}
		});
	}
	public void drawCurve(){
		addMouseMotionListener(new MouseAdapter(){
			public void mouseDragged(MouseEvent e){
				if(!ifNotCurveClicked){
					x=e.getX();
					y=e.getY();
					mode=4;
					repaint();
				}
			}
		});
	}
	public void drawClear(){
		mode=5;
		repaint();
	}
	protected void paintComponent(Graphics g){
		switch(mode){
			case(1):
				g.drawLine(location[0],location[1],location[2],location[3]);
				break;
			case(2):
				if(location[0]<=location[2]&&location[1]<location[3])
					g.drawRect(location[0],location[1],location[2]-location[0],location[3]-location[1]);
				else if(location[0]<=location[2]&&location[1]>=location[3])
					g.drawRect(location[0],location[3],location[2]-location[0],location[1]-location[3]);
				else if(location[0]>location[2]&&location[1]<=location[3])
					g.drawRect(location[2],location[1],location[0]-location[2],location[3]-location[1]);
				else
					g.drawRect(location[2],location[3],location[0]-location[2],location[1]-location[3]);
				break;
			case(3):
				if(location[0]<=location[2]&&location[1]<location[3])
					g.drawOval(location[0],location[1],location[2]-location[0],location[3]-location[1]);
				else if(location[0]<=location[2]&&location[1]>=location[3])
					g.drawOval(location[0],location[3],location[2]-location[0],location[1]-location[3]);
				else if(location[0]>location[2]&&location[1]<=location[3])
					g.drawOval(location[2],location[1],location[0]-location[2],location[3]-location[1]);
				else
					g.drawOval(location[2],location[3],location[0]-location[2],location[1]-location[3]);
				break;
			case(4):
				g.fillOval(x,y,2,2);
				break;
			case(5):
				setBackground(Color.WHITE);
				super.paintComponent(g);
				break;
		}
	}
	class chooseListener implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			 if(e.getSource()==jrb1){
				 ifNotCurveClicked=true;
				 ifCurveClicked=false;
				 drawLine();
			 }
			 else if(e.getSource()==jrb2){
				 ifNotCurveClicked=true;
				 ifCurveClicked=false;
				 drawRectangle();
			 }
			 else if(e.getSource()==jrb3){
				 ifNotCurveClicked=true;
				 ifCurveClicked=false;
				 drawOval();
			 }
			 else if(e.getSource()==jrb4){
				 ifNotCurveClicked=false;
				 ifCurveClicked=true;
				 drawCurve();
			 }
		 }
	}
	class clearListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			drawClear();
		}
	}
}

