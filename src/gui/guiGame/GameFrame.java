package gui.guiGame;

import java.awt.*;
import javax.swing.*;

/**
 * Esta clase se encarga de crear el marco de la GUI
 * @author Ignacio Morales
 * @since 1.0
 */
public class GameFrame extends JFrame{

	GamePanel panel;
	
	public GameFrame(){
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("PaCE-Man");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}