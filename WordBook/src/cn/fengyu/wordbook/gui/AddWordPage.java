package cn.fengyu.wordbook.gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;

import cn.fengyu.wordbbok.listener.ButtonListener;
import cn.fengyu.wordbbok.listener.ButtonSaveImage;
import cn.fengyu.wordbook.controller.DialogAction;
import cn.fengyu.wordbook.controller.DialogActionApdater;
import cn.fengyu.wordbook.controller.DialogActionEnsure;
import cn.fengyu.wordbook.jdbc.JdbcHelper;
import cn.fengyu.wordbook.windows.ConcreteFirstPageWindowFactory;
import cn.fengyu.wordbook.windows.IWindowFactory;

public class AddWordPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private JPanel contentPane;
	private JTextField txtFieldWord;
	private JTextField txtFieldMean;
	private JComboBox<String> comboBox;
	private JTextArea textArea;
	private JLabel lblImage;
	private String image;
	private IWindowFactory firstPageWindowFactory=new ConcreteFirstPageWindowFactory();
	/**
	 * Create the frame.
	 */
	public AddWordPage(){
		setTitle("添加单词");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 764, 21);
		contentPane.add(menuBar);
		
		JMenu menu = new JMenu("\u64CD\u4F5C");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u8FD4\u56DE\u7EE7\u7EED\u5B66\u4E60");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JMenuItem menuItem_1 = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogActionEnsure dae = new DialogActionEnsure();
				DialogAction da=new DialogActionApdater(dae);
				da.exitSystem();
			}
		});
		menu.add(menuItem_1);
		
		JLabel lblNewLabel = new JLabel("\u5355\u8BCD\uFF1A");
		lblNewLabel.setBounds(105, 58, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u6C49\u8BD1\uFF1A");
		label.setBounds(105, 100, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u7C7B\u578B\uFF1A");
		label_1.setBounds(105, 150, 54, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u63CF\u8FF0\uFF1A");
		label_2.setBounds(105, 206, 54, 15);
		contentPane.add(label_2);
		
		txtFieldWord = new JTextField();
		txtFieldWord.setBounds(163, 55, 208, 21);
		contentPane.add(txtFieldWord);
		txtFieldWord.setColumns(10);
		
		txtFieldMean = new JTextField();
		txtFieldMean.setBounds(163, 97, 208, 21);
		contentPane.add(txtFieldMean);
		txtFieldMean.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(163, 206, 208, 143);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
		);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);
		panel.setLayout(gl_panel);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aet) {
				String word=txtFieldWord.getText();
				String mean=txtFieldMean.getText();
				int length=word.length();
				String type=comboBox.getSelectedItem().toString();
				String imagePath=".\\ImageFile\\"+word+".jpg";
				String textPath=".\\WordFile\\"+word+".txt";
				JdbcHelper.insertWordInfo(word, mean, length, type, imagePath, textPath);
				txtFieldWord.setText("");
				txtFieldMean.setText("");
				textArea.setText("");
				lblImage.setIcon(null);
			}
		});
		button.setBounds(105, 402, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aet) {
				txtFieldWord.setText("");
				txtFieldMean.setText("");
				textArea.setText("");
				lblImage.setIcon(null);
			}
		});
		button_1.setBounds(278, 402, 93, 23);
		contentPane.add(button_1);
		
		JButton btnSave = new JButton("\u4FDD\u5B58");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String file=txtFieldWord.getText();
				try {     
					@SuppressWarnings("resource")
					BufferedWriter buf = new BufferedWriter(new FileWriter(".\\WordFile\\"+file+".txt"));   
					String str = textArea.getText();    
					buf.write(str);     
					buf.flush();
					} catch (IOException e1)
					{    
						e1.printStackTrace(); 
					}
				DialogActionEnsure dae = new DialogActionEnsure();
				DialogAction da=new DialogActionApdater(dae);
				da.ensureScussess();	
			}
		});
		btnSave.setBounds(163, 350, 93, 23);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.setBounds(278, 350, 93, 23);
		contentPane.add(btnCancel);
		JButton btnUpload = new JButton("\u4E0A\u4F20");
		btnUpload.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "image files", "jpg","bmp");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(chooser);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	
		            image=chooser.getSelectedFile().getPath();//获取图片的路径
		            //System.out.println(image);
		            lblImage.setText("");
		            ImageIcon imag = new ImageIcon(image); 
		   		 	imag.setImage(imag.getImage().getScaledInstance(380,340,Image.SCALE_DEFAULT)); 
		   		 	lblImage.setIcon(imag);
		   		 	lblImage.setOpaque(true);
			    }else if(returnVal==JFileChooser.CANCEL_OPTION){
			    	return;
			    }
			}
		});
		
		JPanel jPanel = new JPanel();
		jPanel.setToolTipText("图片");
		jPanel.setBorder(new TitledBorder(null, "图片", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jPanel.setBounds(411, 38, 353, 310);
		contentPane.add(jPanel);
		jPanel.setLayout(null);
		
		
		lblImage = new JLabel();
		lblImage.setBounds(6, 17, 347, 291);
		jPanel.add(lblImage);
		btnUpload.setBounds(417, 350, 93, 23);
		contentPane.add(btnUpload);
		
		JButton btnSaveImag = new JButton("\u4FDD\u5B58");
		btnSaveImag.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ave) {
				// TODO Auto-generated method stub
				ButtonListener bl= new ButtonSaveImage(txtFieldWord, image);
				bl.ActionPerformed(ave);
				
			}
//			public void actionPerformed(ActionEvent aet) {
//				String imagename=txtFieldWord.getText();
//				try{
//				FileInputStream fis=new FileInputStream(new File(image));
//				FileOutputStream fos = new FileOutputStream(new File(".\\ImageFile\\"+imagename+".jpg"));
//				byte[] b = new byte[1];		
//				while(fis.read(b) != -1) {		
//					fos.write(b);			
//					fos.flush();	
//				}	
//				fis.close();		
//				fos.close();
//				}catch(IOException e){
//					e.printStackTrace();
//				}
//				ButtonListener bl=new ConcreteButtonListener(image, imagename);
//				bl.btnSaveImageListener(aet);
//				JOptionPane.showMessageDialog(null, "保存成功！");
			//}
		});
		btnSaveImag.setBounds(546, 350, 93, 23);
		contentPane.add(btnSaveImag);
		
		JButton btnAddImag = new JButton("\u53D6\u6D88");
		btnAddImag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aet) {
				lblImage.setIcon(null);
			}
		});
		btnAddImag.setBounds(671, 350, 93, 23);
		contentPane.add(btnAddImag);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("六级");
		comboBox.addItem("四级");
		comboBox.addItem("TOELF");
		comboBox.addItem("IELTS");
		comboBox.setSelectedIndex(3);
		comboBox.setEditable(true);
		comboBox.setBounds(163, 147, 208, 21);
		contentPane.add(comboBox);
		
		
	}
}
