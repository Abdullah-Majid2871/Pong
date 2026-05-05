
/* Author: ABdullah Majid
 * Date: December 2 2024
 * Description: Serves as the main loop in the game, constantly runs and calls other methods
 * 
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	// dimensions of window
	public static final int GAME_WIDTH = 1250;
	public static final int GAME_HEIGHT = 800;

	public Thread gameThread;
	public Image image;
	public Graphics graphics;
	public Ball ball;
	public static Paddle padLeft, padRight;
	public Scoreclass score;
	public Menu mainMenu = new Menu();
	public AudioClass audio = new AudioClass();

	public GamePanel() {
		ball = new Ball(GAME_WIDTH / 2, GAME_HEIGHT / 2); // create a ball, set spawn location to middle of screen
		score = new Scoreclass(0, 0); // creates a class for score

		padLeft = new Paddle(15, GAME_HEIGHT / 2); // creates the paddles for the player on the left
		padRight = new Paddle(GAME_WIDTH - Paddle.PADDLE_WIDTH - 15, GAME_HEIGHT / 2); // creates the paddle for the
																						// player on the right

		this.setFocusable(true); // make everything in this class appear on the screen
		this.addKeyListener(this); // start listening for keyboard input
		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));

		// Helps remove lag by using multi-threading
		gameThread = new Thread(this);
		gameThread.start();
	}

	// update what appears in the window. You NEVER call paint() yourself
	public void paint(Graphics g) {

		// draws images OFF the screen, then moves the image on screen as needed.
		image = createImage(GAME_WIDTH, GAME_HEIGHT); // draw off screen
		graphics = image.getGraphics();
		draw(graphics);// update the positions of everything on the screen
		g.drawImage(image, 0, 0, this); // move the image on the screen

	}

	// creates an enum for the game running condition
	public static enum GAMESTATE {
		MENU, GAME
	};

	// sets the menu variable to be true so game starts at main menu
	public static GAMESTATE GameState = GAMESTATE.MENU;

	// call the draw methods in each class to update positions as things move
	public void draw(Graphics g) {

		// words if the player selects the play option in the menu
		if (GameState == GAMESTATE.GAME) {
			ball.draw(g);
			padLeft.draw(g);
			padRight.draw(g);
			score.draw(g);
		}
		// displays the main menu 
		else if (GameState == GAMESTATE.MENU) {
			mainMenu.render(g);
		}
	}

	// if a key is pressed, we'll send it over to the PlayerBall class for processing
	public void keyPressed(KeyEvent e) {
		
		//sends keyboard input on the main menu to the main menu class
		if (GameState == GAMESTATE.MENU) {
			mainMenu.keyPressed(e);
		}

		// Controls for the player on the left
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S ) {
			padLeft.keyPressed(e);					
		}


		// Controls for the player on the right
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN ) {
			padRight.keyPressed(e);
		}
	
	}

	// if a key is released gets sent to the paddle and menu class for processing
	public void keyReleased(KeyEvent e) {
		if (GameState == GAMESTATE.MENU) {
			mainMenu.keyReleased(e);
		}
		
		padRight.keyReleased(e);
		padLeft.keyReleased(e);
	}

	// calls the move methods in other classes to update positions
	public void move() {
		// movement only occurs if game condition is running
		if (GameState == GAMESTATE.GAME) {
			ball.move();
			padLeft.move();
			padRight.move();
		}
	}

	// handles all collision detection and responds accordingly
	public void checkCollision() {

		// controls the direction of the ball once it interacts with the paddle
		if (ball.intersects(padLeft)) {
			Ball.ballXVelocity = 15;

		}
		if (ball.intersects(padRight)) {
			Ball.ballXVelocity = -15;
		}

		// Forces the right paddle to stay on the screen
		if (padRight.y + Paddle.PADDLE_LENGTH >= GAME_HEIGHT) {
			padRight.y = GAME_HEIGHT - Paddle.PADDLE_LENGTH;
		} 
		if (padRight.y <= 0) {
			padRight.y = 0;
		}

		// Forces the left paddle to stay on the screen
		if (padLeft.y + Paddle.PADDLE_LENGTH >= GAME_HEIGHT) {
			padLeft.y = GAME_HEIGHT - Paddle.PADDLE_LENGTH;
		}
		if (padLeft.y <= 0) {
			padLeft.y = 0;
		}

		// controls how to ball bounces off the border on the top and bottom of window
		if (ball.y <= 0 || ball.y >= GAME_HEIGHT) {
			Ball.ballYVelocity *= -1;
		}

		// determines which player got the point when the ball hit the sides of the
		// window
		// slows down the ball upon respawn
		if (ball.x <= 0) {
			Ball.ballXVelocity = -4;
			Ball.ballYVelocity = 6;
			ball.x = GAME_WIDTH / 2;
			ball.y = GAME_HEIGHT / 2;
			audio.playScore();
			Scoreclass.playerRightScore++; // Increases score of right player
		}
		if (ball.x >= GAME_WIDTH) {
			Ball.ballXVelocity = 4;
			Ball.ballYVelocity = 6;
			ball.x = GAME_WIDTH / 2;
			ball.y = GAME_HEIGHT / 2;
			audio.playScore();
			Scoreclass.playerLeftScore++; // Increases score of left player
		}
		
		//upon intersection, forces the ball to move int the direction of the moving paddle
		// only if the paddles are moving
		if(ball.intersects(padLeft) && padLeft.yVelocity > 0) {
			Ball.ballYVelocity = 6;				
		}
		if(ball.intersects(padLeft) && padLeft.yVelocity < 0) {
			Ball.ballYVelocity = -6;				
		}
		
		if(ball.intersects(padRight) && padRight.yVelocity > 0) {
			Ball.ballYVelocity = 6;				
		}
		if(ball.intersects(padRight) && padRight.yVelocity < 0) {
			Ball.ballYVelocity = -6;				
		}
	}

	// Calls other methods to perform actions and makes the game continue running without end.
	public void run() {
		// Slows down the code from running too quickly
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long now;

		while (true) { // this is the infinite game loop
			now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			// only move objects around and update screen if enough time has passed
			if (delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}