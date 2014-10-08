package cn.fengyu.wordbook.windows;

import javax.swing.JFrame;

import cn.fengyu.wordbook.gui.WordList6;

public class ConcreteGET6WindowFactory implements IWindowFactory{
	@Override
	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return new WordList6();
	}

}
