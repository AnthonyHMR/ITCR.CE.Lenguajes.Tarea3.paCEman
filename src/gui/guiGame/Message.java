package gui.guiGame;

import java.awt.*;

public class Message extends Rectangle {
    static int GAME_WIDTH;
    static int GAME_HEIGHT;

    Message(int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas",Font.PLAIN,90));

        g.drawString("GAME OVER", (GAME_WIDTH/2)-85, 50);
    }
}
