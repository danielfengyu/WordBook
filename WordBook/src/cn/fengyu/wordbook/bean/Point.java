package cn.fengyu.wordbook.bean;

import java.awt.Color;
import java.io.Serializable;

public class Point implements Serializable {

	int x,y;
	Color col;
	int tool;
	int boarder;
	Point(int x, int y, Color col, int tool, int boarder) { 
		this.x = x; 
		this.y = y; 
		this.col = col; 
		this.tool = tool;
		this.boarder = boarder;
		} 
}
