package gui.guiGame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.sound.sampled.SourceDataLine;
import javax.swing.*;

//import gui.GamePanel;

public class GamePanel extends JPanel implements Runnable{

	static int lives = 3;
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = 700;
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 40;
	
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Wall paddle1;
	Wall paddle2;
	PacMan ball;
	Ghost blinky;
	Ghost inky;
	Ghost clyde;
	Ghost pinky;
	Score score;
	Life life;
	Constants cons;
	Boolean energized = false;
	Wall[] wallArr = new Wall[4];
	//Ghost[] ghostArr = new Ghost[4];
	LinkedList<Ghost> ghostList = new LinkedList<Ghost>();
	LinkedList<Dot> dotList = new LinkedList<Dot>();
	LinkedList<Energizer> energList = new LinkedList<Energizer>();

	int lifes = 3;

	GamePanel(){
		newWalls();
		newBall();
		newGhosts();
		newDot(1, 1);
		newEnergizer(2, 1);
		score = new Score(GAME_WIDTH,GAME_HEIGHT);
		life = new Life(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBall() {
		random = new Random();
		ball = new PacMan((GAME_WIDTH/2)-(BALL_DIAMETER/2),(GAME_HEIGHT/2)-(BALL_DIAMETER/2),BALL_DIAMETER,BALL_DIAMETER);	
	}

	public void newGhosts() {
		blinky = new Ghost(0, 0, BALL_DIAMETER, BALL_DIAMETER, Color.red);
		inky = new Ghost(GAME_WIDTH-BALL_DIAMETER, 0, BALL_DIAMETER, BALL_DIAMETER, Color.cyan);
		clyde = new Ghost(0, GAME_HEIGHT-BALL_DIAMETER, BALL_DIAMETER, BALL_DIAMETER, Color.orange);
		pinky = new Ghost(GAME_WIDTH-BALL_DIAMETER, GAME_HEIGHT-BALL_DIAMETER, BALL_DIAMETER, BALL_DIAMETER, Color.pink);
		
		ghostList.add(blinky);
		ghostList.add(inky);
		ghostList.add(clyde);
		ghostList.add(pinky);
	}

	public void newDot(int x, int y) {
		Dot dot = new Dot(x*(cons.SQUARE_SIDE/2)-5,y*(cons.SQUARE_SIDE/2)-5,10,10);
		dotList.add(dot);
	}

	public void newEnergizer(int x, int y) {
		Energizer energizer = new Energizer(x*(cons.SQUARE_SIDE/2)-10,y*(cons.SQUARE_SIDE/2)-10,20,20);
		energList.add(energizer);
	}

	public void newWalls() {
		wallArr[0] = new Wall(cons.SQUARE_SIDE,GAME_HEIGHT-(6*cons.SQUARE_SIDE),cons.SQUARE_SIDE,5*cons.SQUARE_SIDE);
		wallArr[1] = new Wall(GAME_WIDTH-2*cons.SQUARE_SIDE,GAME_HEIGHT-(6*cons.SQUARE_SIDE),cons.SQUARE_SIDE,5*cons.SQUARE_SIDE);
		wallArr[2] = new Wall(2*cons.SQUARE_SIDE,2*cons.SQUARE_SIDE,4*cons.SQUARE_SIDE,4*cons.SQUARE_SIDE);
		wallArr[3] = new Wall(GAME_WIDTH-6*cons.SQUARE_SIDE,2*cons.SQUARE_SIDE,4*cons.SQUARE_SIDE,4*cons.SQUARE_SIDE);
	}

	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}

	public void draw(Graphics g) {

		for (int i = 0; i < wallArr.length; i++) {
			wallArr[i].draw(g);
		}
		
		ball.draw(g);
		score.draw(g);
		life.draw(g);
		for(int j = 0; j < ghostList.size(); j++){
			ghostList.get(j).draw(g);
		}

		for (int k = 0; k < dotList.size(); k++){
			dotList.get(k).draw(g);
		}

		for (int l = 0; l < energList.size(); l++){
			energList.get(l).draw(g);
		}
		
Toolkit.getDefaultToolkit().sync();

	}
	public void move() {
		ball.move();
		for(int i = 0; i < ghostList.size(); i++){
			ghostList.get(i).move();
			
		}
	}
	
	public void checkCollision() {
		
		//stop ball at window edges
		if(ball.y <=0) {
			ball.setYDirection(0);
			ball.y = ball.y + 7;
		}
		if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
			ball.setYDirection(0);
			ball.y = ball.y - 7;
		}
		if(ball.x <=0) {
			ball.setXDirection(0);
			ball.x = ball.x + 7;
		}
		if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
			ball.setXDirection(0);
			ball.x = ball.x - 7;
		}

		
		//stop ball and ghosts when collision with walls

		for (int i = 0; i < wallArr.length; i++) {
			if(ball.intersects(wallArr[i])) {
				if(ball.xVelocity > 0){
					ball.x = ball.x - 7;
				}
				else if(ball.xVelocity < 0){
					ball.x = ball.x + 7;
				}
				else if(ball.yVelocity > 0){
					ball.y = ball.y - 7;
				}
				else if(ball.yVelocity < 0){
					ball.y = ball.y + 7;
				}
				ball.setXDirection(0);
				ball.setYDirection(0);	
			}

			for(int k=0; k < ghostList.size(); k++){

				if(ghostList.get(k).intersects(wallArr[i])) {
					if(ghostList.get(k).xVelocity > 0){
						ghostList.get(k).x = ghostList.get(k).x - 7;
					}
					else if(ghostList.get(k).xVelocity < 0){
						ghostList.get(k).x = ghostList.get(k).x + 7;
					}
					else if(ghostList.get(k).yVelocity > 0){
						ghostList.get(k).y = ghostList.get(k).y - 7;
					}
					else if(ghostList.get(k).yVelocity < 0){
						ghostList.get(k).y = ghostList.get(k).y + 7;
					}
					ghostList.get(k).setXDirection(0);
					ghostList.get(k).setYDirection(0);
					
				}
			}
		}



		for (int j = 0; j<ghostList.size(); j++){
			if(ghostList.get(j).y <=0) {
				ghostList.get(j).setYDirection(0);
				ghostList.get(j).y = ghostList.get(j).y + 7;
			}
			if(ghostList.get(j).y >= GAME_HEIGHT-BALL_DIAMETER) {
				ghostList.get(j).setYDirection(0);
				ghostList.get(j).y = ghostList.get(j).y - 7;
			}
			if(ghostList.get(j).x <=0) {
				ghostList.get(j).setXDirection(0);
				ghostList.get(j).x = ghostList.get(j).x + 7;
			}
			if(ghostList.get(j).x >= GAME_WIDTH-BALL_DIAMETER) {
				ghostList.get(j).setXDirection(0);
				ghostList.get(j).x = ghostList.get(j).x - 7;
			}
		}

		// Collision pacman and ghosts

		for(int m =0; m < ghostList.size(); m++){

			if(ghostList.get(m).intersects(ball)) {
				
				if(energized == false){
					life.lifes--;
					newBall();
					System.out.println("Has muerto");
				}
				else{
					score.points += ghostList.get(m).value;
					ghostList.remove(m);
				}				
			}
		}

		// Collision with dot

		for(int l = 0; l < dotList.size(); l++){

			if(dotList.get(l).intersects(ball)) {
				
				//SUMAR PUNTOS
				score.points += dotList.get(l).value;
				dotList.remove(l);
			}

		}

		// Collision with energizer

		for(int n = 0; n < energList.size(); n++){

			if(energList.get(n).intersects(ball)) {
				
				// MODO MIEO ACTIVADO
				energize();
				energList.remove(n);
			}

		}
	}

	public void energize(){

		energized = true;

		for(int i = 0; i < ghostList.size(); i++){

			ghostList.get(i)._color = Color.blue;

		}
	}

	public void deenergize(){

		energized = false;

		for(int i = 0; i < ghostList.size(); i++){

			ghostList.get(i)._color = ghostList.get(i)._tcolor;

		}
	}

	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		double delta2 = 0;
		double energDelta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			delta2 += (now -lastTime)/ns;
			energDelta += (now -lastTime)/ns;
			lastTime = now;

			if(delta >=1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}

			if(delta2 >= 100){
				for(int i = 0; i < ghostList.size(); i++){
					ghostList.get(i).movement();
				}
				delta2 = delta2 - 100;
			}

			if(energized == true){
				if(energDelta >= 1000){
					deenergize();
				}
			}
		}
	}
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {

			ball.keyPressed(e);
		
		}
	}
}