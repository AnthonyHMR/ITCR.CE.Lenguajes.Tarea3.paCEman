package gui.guiGame;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * Se encarga de manejar los fantasmas del juego
 * @author Ignacio Morales
 * @since 1.0
 */
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
	
	/**
	 * Decide el siguiente movimiento del fantasma
	 */
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

	/**
	 * Setea la direccion en X en la que se mueve el fantasma
	 * @param xDirection
	 */
	public void setXDirection(int xDirection) {
		xVelocity = xDirection;
	}
	
	/**
	 * Setea la direccion en Y en la que se mueve el fantasma
	 * @param yDirection
	 */
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}

	/**
	 * Cambia coordenadas del fantasma
	 */
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}

	/**
	 * Dibuja el fantasma en pantalla
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(_color);
		g.fillOval(x, y, height, width);
	}
}
