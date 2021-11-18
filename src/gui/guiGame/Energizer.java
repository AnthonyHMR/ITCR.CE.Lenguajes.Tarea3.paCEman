package gui.guiGame;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * Se encarga de manejar los energizers del juego
 * @author Ignacio Morales
 * @since 1.0
 */
public class Energizer extends Rectangle{
	
	Energizer(int x, int y, int width, int height){

		super(x,y,width,height);
		
	}
	
	/**
	 * Dibuja el energizer en pantalla
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}

}
