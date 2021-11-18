package gui.guiGame;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * Se encarga de manejar los puntos del juego
 * @author Ignacio Morales
 * @since 1.0
 */
public class Dot extends Rectangle{

    int value = 5;
	
	Dot(int x, int y, int width, int height){

		super(x,y,width,height);
		
	}
	

	/**
	 * Dibuja el punto en la ventana
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
}