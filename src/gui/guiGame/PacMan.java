package gui.guiGame;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * Se encarga de manejar el jugador del juego
 * @author Ignacio Morales
 * @since 1.0
 */
public class PacMan extends Rectangle{

	Random random;
	int xVelocity;
	int yVelocity;
	int speed = 5;
	private static PacMan instance;
	
	PacMan(int x, int y, int width, int height){

		super(x,y,width,height);
		
	}

	
	/**
	 * Singleton para PacMan
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return unica instancia de PacMan
	 */
	public static PacMan getInstance(int x, int y, int width, int height){
		if (instance == null) {
            instance = new PacMan(x,y,width,height);
        }
        return instance;
	}
	
	/**
	 * Controla movimiento del jugador por medio de eventos de teclado
	 * @param e tecla presionada
	 */
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

	/**
	 * Setea la direccion en X en la que se mueve el jugador
	 * @param xDirection
	 */
	public void setXDirection(int xDirection) {
		xVelocity = xDirection;
	}

	/**
	 * Setea la direccion en Y en la que se mueve el jugador
	 * @param yDirection
	 */
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}

	/**
	 * Cambia coordenadas del jugador
	 */
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	
	/**
	 * Dibuja jugador en ventana
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, height, width);
	}
}