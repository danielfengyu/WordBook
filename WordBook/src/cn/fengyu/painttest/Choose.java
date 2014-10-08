package cn.fengyu.painttest;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Choose extends Board{
	JRadioButton jrb1=new JRadioButton("Line");
	JRadioButton jrb2=new JRadioButton("Rectangle");
	JRadioButton jrb3=new JRadioButton("Oval");
	JRadioButton jrb4=new JRadioButton("Curve");
	JButton jbt = new JButton("Clear");
	public Choose(){
		JPanel jp1=new JPanel();
		jp1.setLayout(new GridLayout(5,1,5,5));
		jp1.add(jrb1);
		jp1.add(jrb2);
		jp1.add(jrb3);
		jp1.add(jrb4);
		jp1.add(jbt);
		add(jp1);
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
}
