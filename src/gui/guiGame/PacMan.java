package gui.guiGame;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class PacMan extends Rectangle{

	Random random;
	int xVelocity;
	int yVelocity;
	int speed = 5;
	private static PacMan instance;
	
	PacMan(int x, int y, int width, int height){

		super(x,y,width,height);
		
	}

	// Singleton
	
	public static PacMan getInstance(int x, int y, int width, int height){
		if (instance == null) {
            instance = new PacMan(x,y,width,height);
        }
        return instance;
	}
	
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_W) {
			setYDirection(-speed);
			setXDirection(0);
		}
		if(e.getKeyCode()==KeyEvent.VK_S) {
			setYDirection(speed);
			setXDirection(0);
		}
		if(e.getKeyCode()==KeyEvent.VK_D) {
			setXDirection(speed);
			setYDirection(0);
		}
		if(e.getKeyCode()==KeyEvent.VK_A) {
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
		g.setColor(Color.yellow);
		g.fillOval(x, y, height, width);
	}
}