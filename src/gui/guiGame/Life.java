package gui.guiGame;

import java.awt.*;

public class Life extends Rectangle{

    static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int lifes = 3;

    Life(int GAME_WIDTH, int GAME_HEIGHT){
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}

    public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.PLAIN,32));
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		g.drawString(String.valueOf(lifes), 20, 30);
	}
}
