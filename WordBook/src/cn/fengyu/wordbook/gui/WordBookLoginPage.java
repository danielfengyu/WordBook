package cn.fengyu.wordbook.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import cn.fengyu.wordbook.controller.DialogAction;
import cn.fengyu.wordbook.controller.DialogActionApdater;
import cn.fengyu.wordbook.controller.DialogActionEnsure;
import cn.fengyu.wordbook.windows.ConcreteFirstPageWindowFactory;
import cn.fengyu.wordbook.windows.ConcreteRegisterWindowFactory;
import cn.fengyu.wordbook.windows.IWindowFactory;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class WordBookLoginPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneLogin;
	private JTextField textFieldUserName;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WordBookLoginPage frame = new WordBookLoginPage();
//					WordbookFirstPage frame1=new WordbookFirstPage();
//					frame1.setVisible(false);
			
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
	public WordBookLoginPage() {
		setTitle("登录");
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u64CD\u4F5C");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aet) {
				DialogActionEnsure dae = new DialogActionEnsure();
				DialogAction da=new DialogActionApdater(dae);
				da.exitSystem();	
			}
		});
		menu.add(menuItem);
		contentPaneLogin = new JPanel();
		contentPaneLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneLogin);
		SpringLayout sl_contentPaneLogin = new SpringLayout();
		contentPaneLogin.setLayout(sl_contentPaneLogin);
		
		 textFieldUserName = new JTextField();
		textFieldUserName.setFont(new Font("宋体", Font.PLAIN, 16));
		sl_contentPaneLogin.putConstraint(SpringLayout.WEST, textFieldUserName, 189, SpringLayout.WEST, contentPaneLogin);
		sl_contentPaneLogin.putConstraint(SpringLayout.EAST, textFieldUserName, -48, SpringLayout.EAST, contentPaneLogin);
		contentPaneLogin.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("宋体", Font.PLAIN, 16));
		sl_contentPaneLogin.putConstraint(SpringLayout.NORTH, passwordField, 159, SpringLayout.NORTH, contentPaneLogin);
		sl_contentPaneLogin.putConstraint(SpringLayout.WEST, passwordField, 189, SpringLayout.WEST, contentPaneLogin);
		sl_contentPaneLogin.putConstraint(SpringLayout.SOUTH, textFieldUserName, -41, SpringLayout.NORTH, passwordField);
		sl_contentPaneLogin.putConstraint(SpringLayout.EAST, passwordField, -48, SpringLayout.EAST, contentPaneLogin);
		contentPaneLogin.add(passwordField);
		
		
		JButton btLogin = new JButton("\u767B\u5F55");
		btLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName=textFieldUserName.getText();
				@SuppressWarnings("deprecation")
				String password=passwordField.getText();
				if((userName.equals("fengyu"))&&(password.equals("123456"))){
	                	setVisible(false);
	                	EventQueue.invokeLater(new Runnable() {
	            			public void run() {
	            				try {
	            					IWindowFactory firstPageWindowFactory=new ConcreteFirstPageWindowFactory();
	            					firstPageWindowFactory.getFrame().setVisible(true);
	            				} catch (Exception e) {
	            					e.printStackTrace();
	            				}
	            			}
	            		});
	            }
				else{
					JOptionPane.showMessageDialog(contentPaneLogin, "用户名或密码有误，请从新输入！");
				}
			}
		});
		sl_contentPaneLogin.putConstraint(SpringLayout.SOUTH, btLogin, -10, SpringLayout.SOUTH, contentPaneLogin);
		contentPaneLogin.add(btLogin);
		
		JButton btnRegister = new JButton("\u6CE8\u518C");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
            	EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        					IWindowFactory registerWindowFactory=new ConcreteRegisterWindowFactory();
        					registerWindowFactory.getFrame().setVisible(true);
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		});
			}
		});
		sl_contentPaneLogin.putConstraint(SpringLayout.EAST, btLogin, -31, SpringLayout.WEST, btnRegister);
		sl_contentPaneLogin.putConstraint(SpringLayout.WEST, btnRegister, 0, SpringLayout.WEST, textFieldUserName);
		sl_contentPaneLogin.putConstraint(SpringLayout.SOUTH, btnRegister, 0, SpringLayout.SOUTH, btLogin);
		contentPaneLogin.add(btnRegister);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		sl_contentPaneLogin.putConstraint(SpringLayout.NORTH, lblNewLabel, 99, SpringLayout.NORTH, contentPaneLogin);
		sl_contentPaneLogin.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, btLogin);
		sl_contentPaneLogin.putConstraint(SpringLayout.SOUTH, lblNewLabel, 118, SpringLayout.NORTH, contentPaneLogin);
		sl_contentPaneLogin.putConstraint(SpringLayout.EAST, lblNewLabel, -6, SpringLayout.WEST, textFieldUserName);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		contentPaneLogin.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		sl_contentPaneLogin.putConstraint(SpringLayout.NORTH, lblNewLabel_1, -19, SpringLayout.SOUTH, passwordField);
		sl_contentPaneLogin.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, btLogin);
		sl_contentPaneLogin.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 0, SpringLayout.SOUTH, passwordField);
		sl_contentPaneLogin.putConstraint(SpringLayout.EAST, lblNewLabel_1, -6, SpringLayout.WEST, passwordField);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		contentPaneLogin.add(lblNewLabel_1);
	}
}
