package cn.fengyu.wordbook.controller;

import javax.swing.JOptionPane;

public class DialogActionApdater implements DialogAction{
	DialogActionEnsure dae=new DialogActionEnsure();
	InputDialogAction ida=new InputDialogAction();
	public DialogActionApdater(DialogActionEnsure dae){
		this.dae=dae;
		
	}
	public DialogActionApdater (InputDialogAction ida){
		this.ida=ida;
	}
	@Override
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
		dae.ensureScussess();
		
	}
	@Override
	public String inputWord() {
		// TODO Auto-generated method stub
		return ida.inputWord();
	}
	public static void main(String[] args) {
		InputDialogAction ida=new InputDialogAction();
		DialogActionApdater daa=new DialogActionApdater(ida);
		System.out.println(daa.inputWord());
	}

}
