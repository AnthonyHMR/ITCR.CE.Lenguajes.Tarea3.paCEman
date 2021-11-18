package gui.guiGame;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Fruit extends Rectangle{

    int value = 500;
	
	Fruit(int x, int y, int width, int height){

		super(x,y,width,height);
		
	}
	

	public void draw(Graphics g) {
		g.setColor(Color.magenta);
		g.fillOval(x, y, height, width);
	}
}