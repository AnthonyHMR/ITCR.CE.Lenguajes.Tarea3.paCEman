package gui.guiGame;

import com.company.Client;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.sound.sampled.SourceDataLine;
import javax.swing.*;

/**
 * Se encarga de manejar la interacción entre elementos del juego
 * @author Ignacio Morales
 * @author Anthony Montero
 * @since 1.0
 */

public class GamePanel extends JPanel implements Runnable {

	static int lives = 3;
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = 700;
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 40;

	Game game;
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
	LinkedList<Fruit> fruitList = new LinkedList<Fruit>();
	LinkedList<Energizer> energList = new LinkedList<Energizer>();
	int count=1;

	//int lifes = 3;

	GamePanel() {
		newWalls();
		newBall();
		newGhosts();
		newDot(1, 1);
		newFruit(1, 1);
		newEnergizer(2, 1);
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		life = new Life(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);

		game = new Game();
		game.start();

		gameThread = new Thread(this);
		gameThread.start();
	}

	/**
	 * newBall() crea un nuevo personaje en la posición inicial
	 * 
	 */
	public void newBall() {
		random = new Random();
		ball = PacMan.getInstance((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), (GAME_HEIGHT / 2) - (BALL_DIAMETER / 2), BALL_DIAMETER, BALL_DIAMETER);
	}

	/**
	 * Crea fantasmas en sus posisciones iniciales y los agrega a la lista de fantamsas
	 */
	public void newGhosts() {

		blinky = new Ghost(0, 0, BALL_DIAMETER, BALL_DIAMETER, Color.red);
		inky = new Ghost(GAME_WIDTH - BALL_DIAMETER, 0, BALL_DIAMETER, BALL_DIAMETER, Color.cyan);
		clyde = new Ghost(0, GAME_HEIGHT - BALL_DIAMETER, BALL_DIAMETER, BALL_DIAMETER, Color.orange);
		pinky = new Ghost(GAME_WIDTH - BALL_DIAMETER, GAME_HEIGHT - BALL_DIAMETER, BALL_DIAMETER, BALL_DIAMETER, Color.pink);

		ghostList.add(blinky);
		ghostList.add(inky);
		ghostList.add(clyde);
		ghostList.add(pinky);
	}

	/**
	 * newDot crea un punto en las coordenadas dadas
	 * @param x coordenada en x
	 * @param y coordenada en y
	 */
	public void newDot(int x, int y) {
		for (int i = 0; i < 40; i++) {
			for (int j = 0; j < 25; j++) {
				Dot dot = new Dot(i * x * (cons.SQUARE_SIDE / 2) + 7, j * y * (cons.SQUARE_SIDE / 2) + 50, 10, 10);
				dotList.add(dot);
			}
		}
	}

	/**
	 * newEnergizer crea un energizer en las coordenadas dadas
	 * @param x coordenada en x
	 * @param y coordenada en y
	 */
	public void newEnergizer(int x, int y) {
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int indX = random.nextInt(6);
			int indY = random.nextInt(6);
			Energizer energizer = new Energizer(indX * i * x * (cons.SQUARE_SIDE / 2) + 2, indY * i * y * (cons.SQUARE_SIDE / 2) + 45, 20, 20);
			energList.add(energizer);
		}
	}

	/**
	 * newDot crea una fruta en las coordenadas dadas
	 * @param x coordenada en x
	 * @param y coordenada en y
	 */
	public void newFruit(int x, int y) {
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			int indX = random.nextInt(6);
			int indY = random.nextInt(6);
			Fruit fruit = new Fruit(indX * i * x * (cons.SQUARE_SIDE / 2) + 2, indY * i * y * (cons.SQUARE_SIDE / 2) + 45, 15, 15);
			fruitList.add(fruit);
		}
	}

	/**
	 * newWalls crea las paredes del juego y las guarda en un arreglo de paredes
	 */
	public void newWalls() {
		wallArr[0] = new Wall(cons.SQUARE_SIDE, GAME_HEIGHT - (6 * cons.SQUARE_SIDE), cons.SQUARE_SIDE, 5 * cons.SQUARE_SIDE);
		wallArr[1] = new Wall(GAME_WIDTH - 2 * cons.SQUARE_SIDE, GAME_HEIGHT - (6 * cons.SQUARE_SIDE), cons.SQUARE_SIDE, 5 * cons.SQUARE_SIDE);
		wallArr[2] = new Wall(2 * cons.SQUARE_SIDE, 2 * cons.SQUARE_SIDE, 4 * cons.SQUARE_SIDE, 4 * cons.SQUARE_SIDE);
		wallArr[3] = new Wall(GAME_WIDTH - 6 * cons.SQUARE_SIDE, 2 * cons.SQUARE_SIDE, 4 * cons.SQUARE_SIDE, 4 * cons.SQUARE_SIDE);
	}

	/**
	 * pinta el componente por primera vez
	 */
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	/**
	 * Dibuja los componentes en la ventana
	 * @param g
	 */
	public void draw(Graphics g) {

		for (int i = 0; i < wallArr.length; i++) {
			wallArr[i].draw(g);
		}

		ball.draw(g);
		score.draw(g);
		life.draw(g);
		for (int j = 0; j < ghostList.size(); j++) {
			ghostList.get(j).draw(g);
		}

		for (int k = 0; k < dotList.size(); k++) {
			dotList.get(k).draw(g);
		}

		for (int l = 0; l < energList.size(); l++) {
			energList.get(l).draw(g);
		}

		Toolkit.getDefaultToolkit().sync();

	}

	/**
	 * Animacion de personajes
	 */
	public void move() {
		ball.move();
		for (int i = 0; i < ghostList.size(); i++) {
			ghostList.get(i).move();

		}
	}

	/**
	 * Revisa si hay colisiones entre los 
	 * posibles elementos de la ventana
	 */
	public void checkCollision() {

		//stop ball at window edges
		if (ball.y <= 0) {
			ball.setYDirection(0);
			ball.y = ball.y + 7;
		}
		if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
			ball.setYDirection(0);
			ball.y = ball.y - 7;
		}
		if (ball.x <= 0) {
			ball.setXDirection(0);
			ball.x = ball.x + 7;
		}
		if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			ball.setXDirection(0);
			ball.x = ball.x - 7;
		}


		//stop ball and ghosts when collision with walls

		for (int i = 0; i < wallArr.length; i++) {
			if (ball.intersects(wallArr[i])) {
				if (ball.xVelocity > 0) {
					ball.x = ball.x - 7;
				} else if (ball.xVelocity < 0) {
					ball.x = ball.x + 7;
				} else if (ball.yVelocity > 0) {
					ball.y = ball.y - 7;
				} else if (ball.yVelocity < 0) {
					ball.y = ball.y + 7;
				}
				ball.setXDirection(0);
				ball.setYDirection(0);
			}

			for (int k = 0; k < ghostList.size(); k++) {

				if (ghostList.get(k).intersects(wallArr[i])) {
					if (ghostList.get(k).xVelocity > 0) {
						ghostList.get(k).x = ghostList.get(k).x - 7;
					} else if (ghostList.get(k).xVelocity < 0) {
						ghostList.get(k).x = ghostList.get(k).x + 7;
					} else if (ghostList.get(k).yVelocity > 0) {
						ghostList.get(k).y = ghostList.get(k).y - 7;
					} else if (ghostList.get(k).yVelocity < 0) {
						ghostList.get(k).y = ghostList.get(k).y + 7;
					}
					ghostList.get(k).setXDirection(0);
					ghostList.get(k).setYDirection(0);

				}
			}
		}


		for (int j = 0; j < ghostList.size(); j++) {
			if (ghostList.get(j).y <= 0) {
				ghostList.get(j).setYDirection(0);
				ghostList.get(j).y = ghostList.get(j).y + 7;
			}
			if (ghostList.get(j).y >= GAME_HEIGHT - BALL_DIAMETER) {
				ghostList.get(j).setYDirection(0);
				ghostList.get(j).y = ghostList.get(j).y - 7;
			}
			if (ghostList.get(j).x <= 0) {
				ghostList.get(j).setXDirection(0);
				ghostList.get(j).x = ghostList.get(j).x + 7;
			}
			if (ghostList.get(j).x >= GAME_WIDTH - BALL_DIAMETER) {
				ghostList.get(j).setXDirection(0);
				ghostList.get(j).x = ghostList.get(j).x - 7;
			}
		}

		// Collision pacman and ghosts

		for (int m = 0; m < ghostList.size(); m++) {

			if (ghostList.get(m).intersects(ball)) {

				if (energized == false) {
					life.lifes--;
					newBall();
					System.out.println("Has muerto");
					game.sendMessage("muerte");
				} else {
					score.points += ghostList.get(m).value;
					ghostList.remove(m);
				}
			}
		}

		// Collision with dot

		for (int l = 0; l < dotList.size(); l++) {

			if (dotList.get(l).intersects(ball)) {

				//SUMAR PUNTOS
				score.points += dotList.get(l).value;
				dotList.remove(l);
			}

		}

		// Collision with energizer

		for (int n = 0; n < energList.size(); n++) {

			if (energList.get(n).intersects(ball)) {

				// MODO MIEO ACTIVADO
				energize();
				energList.remove(n);
			}

		}
		// Collision with fruit

		for(int p = 0; p < fruitList.size(); p++){

			if(fruitList.get(p).intersects(ball)) {

				//SUMAR PUNTOS
				score.points += fruitList.get(p).value;
				fruitList.remove(p);
			}

		}
	}

	/**
	 * Activa modo energizado despues de comer un energizer
	 */
	public void energize(){

		energized = true;

		for(int i = 0; i < ghostList.size(); i++){

			ghostList.get(i)._color = Color.blue;

		}
	}

	/**
	 * Detiene modo energizado
	 */
	public void deenergize(){

		energized = false;

		for(int i = 0; i < ghostList.size(); i++){

			ghostList.get(i)._color = ghostList.get(i)._tcolor;

		}
	}

	/**
	 * Bucle de juego
	 */
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
				if (score.points >= 10000*count) {
					System.out.println("10000 PUNTOS, más una vida");
					life.lifes++;
					count++;
				}
				
				if (life.lifes < 0){
					ghostList.clear();
					ball.setXDirection(0);
					ball.setYDirection(0);
				}
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
	/**
	 * Escucha eventos de teclas para movimiento de jugador
	 */
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {

			ball.keyPressed(e);

		}
	}
}