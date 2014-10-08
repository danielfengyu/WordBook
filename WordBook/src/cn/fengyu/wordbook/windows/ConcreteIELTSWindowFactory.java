package cn.fengyu.wordbook.windows;

import javax.swing.JFrame;

import cn.fengyu.wordbook.gui.WordListIELTS;

public class ConcreteIELTSWindowFactory implements IWindowFactory {

	@Override
	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return new WordListIELTS();
	}
	
}
