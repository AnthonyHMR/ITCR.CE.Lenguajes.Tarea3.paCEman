package gui.guiGame;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Ghost extends Rectangle{

	Random random = new Random();
	int xVelocity;
	int yVelocity;
	int speed = 3;
	int value = 1000;
    Color _color;
	Color _tcolor;
	
	Ghost(int x, int y, int width, int height, Color color){

		super(x,y,width,height);
		_color = color;
		_tcolor = color;
	}
	
	public void movement() {
		int _dir = random.nextInt(4);

		if(_dir==0) {
			setYDirection(-speed);
			setXDirection(0);
		}
		if(_dir==1) {
			setYDirection(speed);
			setXDirection(0);
		}
		if(_dir==2) {
			setXDirection(speed);
			setYDirection(0);
		}
		if(_dir==3) {
			setXDirection(-speed);
			setYDirection(0);
		}

	}

	public void setXDirection(int xDirection) {
		xVelocity = xDirection;
	}
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	public void draw(Graphics g) {
		g.setColor(_color);
		g.fillOval(x, y, height, width);
	}
}
