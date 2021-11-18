package gui.guiGame;

import java.awt.*;

/**
 * Se encarga de manejar el punntaje del juego
 * @author Ignacio Morales
 * @since 1.0
 */
public class Score extends Rectangle{

	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int points;
	
	
	Score(int GAME_WIDTH, int GAME_HEIGHT){
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}

	/**
	 * Dibuja el puntaje en pantalla
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.PLAIN,60));
				
		g.drawString(String.valueOf(points), (GAME_WIDTH/2)-85, 50);
	}
}
