package gui.guiGame;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * Se encarga de manejar las frutas del juego
 * @author Ignacio Morales
 * @since 1.0
 */
public class Fruit extends Rectangle{

    int value = 500;
	
	Fruit(int x, int y, int width, int height){

		super(x,y,width,height);
		
	}
	
	/**
	 * Dibuja la fruta en pantalla
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(Color.magenta);
		g.fillOval(x, y, height, width);
	}
}