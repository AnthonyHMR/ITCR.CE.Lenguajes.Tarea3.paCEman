package gui.guiGame;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Dot extends Rectangle{

    int value = 5;
	
	Dot(int x, int y, int width, int height){

		super(x,y,width,height);
		
	}
	

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
}