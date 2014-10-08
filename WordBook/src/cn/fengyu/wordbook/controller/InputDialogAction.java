package cn.fengyu.wordbook.controller;

import javax.swing.JOptionPane;

public class InputDialogAction {
	
	public String inputWord() {
		// TODO Auto-generated method stub
		String inputValue;
		inputValue = JOptionPane.showInputDialog("输入对应的单词:");
		return inputValue;
	}
}
