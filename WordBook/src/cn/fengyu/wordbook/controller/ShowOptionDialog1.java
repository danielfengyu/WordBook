package cn.fengyu.wordbook.controller;

import javax.swing.JOptionPane;

public class ShowOptionDialog1 implements DialogAction{
	
	public void exitSystem() {
		// TODO Auto-generated method stub
		Object[] options = {"确定","取消"}; 
		int response=JOptionPane.showOptionDialog(null, "亲，累了？确定要退出系统", "退出对话框",
				JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
		if(response==0) 
		{ 
			System.exit(0);
		} 
		else if(response==1) 
		{ 
			
		} 
	}
	@Override
	public void ensureScussess() {
		// TODO Auto-generated method stub
//		JOptionPane.showConfirmDialog(null, 
//				"choose one", "choose one", JOptionPane.YES_NO_OPTION); 
		String inputValue = JOptionPane.showInputDialog("输入对应的单词");
		System.out.println(inputValue);
	}
	
	public static void main(String[] args) {
		ShowOptionDialog1 showWarning=new ShowOptionDialog1();
		showWarning.ensureScussess();
	}
	@Override
	public String inputWord() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
