package cn.fengyu.wordbook.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import cn.fengyu.wordbook.controller.DialogAction;
import cn.fengyu.wordbook.controller.DialogActionApdater;
import cn.fengyu.wordbook.controller.DialogActionEnsure;
import cn.fengyu.wordbook.windows.ConcreteFirstPageWindowFactory;
import cn.fengyu.wordbook.windows.IWindowFactory;

import java.awt.Font;

public class ListenPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private DialogActionEnsure dae;
	private DialogAction da=new DialogActionApdater(dae);
	/**
	 * Create the frame.
	 */
	public ListenPage() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u64CD\u4F5C");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				da.exitSystem();
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u8FD4\u56DE\u5B66\u4E60");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aet) {
				setVisible(false);
				EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        					IWindowFactory fisrtPageWindowFactory=new ConcreteFirstPageWindowFactory();
        					fisrtPageWindowFactory.getFrame().setVisible(true);
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		});
			}
		});
		menu.add(menuItem_1);
		
		JMenu menu_1 = new JMenu("\u8BA1\u65F6\u8BBE\u7F6E");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_2 = new JMenuItem("1\u5206\u949F");
		menu_1.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("2\u5206\u949F");
		menu_1.add(menuItem_3);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("\u5F00\u59CB");
		button.setBounds(21, 10, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u4E0A\u4E00\u4E2A");
		button_1.setBounds(124, 10, 93, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u4E0B\u4E00\u4E2A");
		button_2.setBounds(227, 10, 93, 23);
		contentPane.add(button_2);
		
		textField = new JTextField();
		textField.setBounds(21, 43, 402, 38);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u63D0\u4EA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aet) {
			}
		});
		btnNewButton.setBounds(330, 10, 93, 23);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("\u6B63\u786E\u4E2A\u6570\uFF1A");
		label.setFont(new Font("ËÎÌו", Font.PLAIN, 16));
		label.setBounds(21, 112, 93, 23);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("\u9519\u8BEF\u4E2A\u6570\uFF1A");
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 16));
		lblNewLabel.setBounds(21, 158, 93, 23);
		contentPane.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("\u6B63\u786E\u7387\uFF1A");
		label_1.setFont(new Font("ËÎÌו", Font.PLAIN, 16));
		label_1.setBounds(21, 207, 93, 15);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(128, 114, 295, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(128, 160, 295, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(128, 205, 295, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
	}
}
