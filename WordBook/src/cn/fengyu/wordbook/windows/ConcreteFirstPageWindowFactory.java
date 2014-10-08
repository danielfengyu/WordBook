package cn.fengyu.wordbook.windows;

import javax.swing.JFrame;

import cn.fengyu.wordbook.gui.WordbookFirstPage;

public class ConcreteFirstPageWindowFactory implements IWindowFactory{

	@Override
	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return new WordbookFirstPage();
	}
	
}
