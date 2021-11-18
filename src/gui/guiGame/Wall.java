package gui.guiGame;

import java.awt.*;
import java.awt.event.*;

/**
 * Se encarga de manejar las paredes obstaculo del juego
 * @author Ignacio Morales
 * @since 1.0
 */
public class Wall extends Rectangle{

	
	Wall(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT){
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
	}
	
	/**
	 * Dibuja la pared en ventana de juego
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
}