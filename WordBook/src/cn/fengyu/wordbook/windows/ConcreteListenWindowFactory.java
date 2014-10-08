package cn.fengyu.wordbook.windows;

import javax.swing.JFrame;

import cn.fengyu.wordbook.gui.ListenPage;

public class ConcreteListenWindowFactory implements IWindowFactory{

	@Override
	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return new ListenPage();
	}
	

}
