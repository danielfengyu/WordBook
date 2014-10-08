package cn.fengyu.wordbbok.listener;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JTextField;

import cn.fengyu.wordbook.controller.DialogAction;
import cn.fengyu.wordbook.controller.DialogActionApdater;
import cn.fengyu.wordbook.controller.DialogActionEnsure;

public class ButtonSaveImage extends ButtonListener{
	JTextField txtFieldWord;
	String image;
	public ButtonSaveImage(JTextField txtFieldWord,String image){
		this.txtFieldWord=txtFieldWord;
		this.image=image;
	}
	@Override
	public void ActionPerformed(ActionEvent ave) {
		// TODO Auto-generated method stub
		String imagename=txtFieldWord.getText();
		try{
		FileInputStream fis=new FileInputStream(new File(image));
		FileOutputStream fos = new FileOutputStream(new File(".\\ImageFile\\"+imagename+".jpg"));
		byte[] b = new byte[1];		
		while(fis.read(b) != -1) {		
			fos.write(b);			
			fos.flush();	
		}	
		fis.close();		
		fos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		DialogActionEnsure dae = new DialogActionEnsure();
		DialogAction da=new DialogActionApdater(dae);
		da.ensureScussess();	
	}
}
