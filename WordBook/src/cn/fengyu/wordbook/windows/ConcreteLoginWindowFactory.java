package cn.fengyu.wordbook.windows;

import javax.swing.JFrame;

import cn.fengyu.wordbook.gui.WordBookLoginPage;

public class ConcreteLoginWindowFactory implements IWindowFactory{

	@Override
	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return new WordBookLoginPage();
	}
}
