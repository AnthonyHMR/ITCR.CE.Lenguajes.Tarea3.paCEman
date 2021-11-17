package gui.guiGame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.sound.sampled.SourceDataLine;
import javax.swing.*;

//import gui.GamePanel;

public class GamePanel extends JPanel implements Runnable{

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
	Constants cons;
	Wall[] wallArr = new Wall[4];
	Ghost[] ghostArr = new Ghost[4];
	LinkedList<Dot> dotList = new LinkedList<Dot>();
	
	GamePanel(){
		newPaddles();
		newBall();
		newGhosts();
		score = new Score(GAME_WIDTH,GAME_HEIGHT);
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
		ghostArr[0] = blinky;
		ghostArr[1] = inky;
		ghostArr[2] = clyde;
		ghostArr[3] = pinky;
	}

	public void newDot(int x, int y) {
		Dot dot = new Dot(x*(cons.SQUARE_SIDE/2),y*(cons.SQUARE_SIDE/2),5,5);
		dotList.add(dot);
	}

	public void newPaddles() {
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
		for(int i = 0; i < ghostArr.length; i++){
			ghostArr[i].draw(g);
		}
		
Toolkit.getDefaultToolkit().sync();

	}
	public void move() {
		ball.move();
		for(int i = 0; i < ghostArr.length; i++){
			ghostArr[i].move();
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

		
		//stop ball when collision with walls

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

			for(int k=0; k < ghostArr.length; k++){

				if(ghostArr[k].intersects(wallArr[i])) {
					if(ghostArr[k].xVelocity > 0){
						ghostArr[k].x = ghostArr[k].x - 7;
					}
					else if(ghostArr[k].xVelocity < 0){
						ghostArr[k].x = ghostArr[k].x + 7;
					}
					else if(ghostArr[k].yVelocity > 0){
						ghostArr[k].y = ghostArr[k].y - 7;
					}
					else if(ghostArr[k].yVelocity < 0){
						ghostArr[k].y = ghostArr[k].y + 7;
					}
					ghostArr[k].setXDirection(0);
					ghostArr[k].setYDirection(0);
					
				}
			}
		}

		for (int j = 0; j<ghostArr.length; j++){
			if(ghostArr[j].y <=0) {
				ghostArr[j].setYDirection(0);
				ghostArr[j].y = ghostArr[j].y + 7;
			}
			if(ghostArr[j].y >= GAME_HEIGHT-BALL_DIAMETER) {
				ghostArr[j].setYDirection(0);
				ghostArr[j].y = ghostArr[j].y - 7;
			}
			if(ghostArr[j].x <=0) {
				ghostArr[j].setXDirection(0);
				ghostArr[j].x = ghostArr[j].x + 7;
			}
			if(ghostArr[j].x >= GAME_WIDTH-BALL_DIAMETER) {
				ghostArr[j].setXDirection(0);
				ghostArr[j].x = ghostArr[j].x - 7;
			}
		}


		//give a player 1 point and creates new paddles & ball
		/*
		if(ball.x <=0) {
			score.player2++;
			newPaddles();
			newBall();
			System.out.println("Player 2: "+score.player2);
		}
		if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
			score.player1++;
			newPaddles();
			newBall();
			System.out.println("Player 1: "+score.player1);
		} */
	}
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		double delta2 = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			delta2 += (now -lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
			if(delta2 >= 100){
				for(int i = 0; i < ghostArr.length; i++){
					ghostArr[i].movement();
				}
				delta2 = delta2 - 100;
			}
		}
	}
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {

			ball.keyPressed(e);
		
		}
	}
}