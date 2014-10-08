package cn.fengyu.wordbook.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import cn.fengyu.wordbook.controller.DialogAction;
import cn.fengyu.wordbook.controller.DialogActionApdater;
import cn.fengyu.wordbook.controller.DialogActionEnsure;
import cn.fengyu.wordbook.windows.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class WordBookRegisterPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFildUserName;
	private JTextField txtFieldMail;
	private JTextField txtFieldPwd;
	private JTextField txtFieldValidatePwd;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public WordBookRegisterPage() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\pictrue\\\u9A6C\\014d032cedad047f5c597d5781aae926.jpg"));
		setTitle("\u6CE8\u518C\u9875");
		 setResizable(false); 
		setForeground(Color.CYAN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(126, 58, 85, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u6CE8\u518C\u90AE\u7BB1\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(126, 104, 85, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(125, 153, 86, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setBounds(126, 200, 85, 15);
		contentPane.add(label_3);
		
		JButton btnSubmitInfo = new JButton("\u63D0\u4EA4\u6CE8\u518C\u4FE1\u606F");
		btnSubmitInfo.setFont(new Font("宋体", Font.PLAIN, 16));
		btnSubmitInfo.setBounds(126, 239, 144, 23);
		contentPane.add(btnSubmitInfo);
		
		JButton btnCancelRegister = new JButton("\u53D6\u6D88\u6CE8\u518C");
		btnCancelRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
            	EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        					IWindowFactory loginPageWindowFactory=new ConcreteLoginWindowFactory();
        					loginPageWindowFactory.getFrame().setVisible(true);
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		});
			}
		});
		btnCancelRegister.setFont(new Font("宋体", Font.PLAIN, 16));
		btnCancelRegister.setBounds(280, 239, 109, 23);
		contentPane.add(btnCancelRegister);
		
		txtFildUserName = new JTextField();
		txtFildUserName.setFont(new Font("宋体", Font.PLAIN, 16));
		txtFildUserName.setBounds(221, 55, 168, 21);
		contentPane.add(txtFildUserName);
		txtFildUserName.setColumns(10);
		
		txtFieldMail = new JTextField();
		txtFieldMail.setFont(new Font("宋体", Font.PLAIN, 16));
		txtFieldMail.setBounds(221, 101, 168, 21);
		contentPane.add(txtFieldMail);
		txtFieldMail.setColumns(10);
		
		txtFieldPwd = new JTextField();
		txtFieldPwd.setFont(new Font("宋体", Font.PLAIN, 16));
		txtFieldPwd.setBounds(221, 150, 168, 21);
		contentPane.add(txtFieldPwd);
		txtFieldPwd.setColumns(10);
		
		txtFieldValidatePwd = new JTextField();
		txtFieldValidatePwd.setFont(new Font("宋体", Font.PLAIN, 16));
		txtFieldValidatePwd.setBounds(221, 197, 168, 21);
		contentPane.add(txtFieldValidatePwd);
		txtFieldValidatePwd.setColumns(10);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 469, 21);
		contentPane.add(menuBar);
		
		JMenu menu = new JMenu("\u64CD\u4F5C");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogActionEnsure dae = new DialogActionEnsure();
				DialogAction da=new DialogActionApdater(dae);
				da.exitSystem();	
			}
		});
		menu.add(menuItem);
	}

}
