
/* Author: Abdullah Majid
 * Date: December 2 2024
 * Description: Defines the behaviour of the play controlled paddles  
 */
import java.awt.*;
import java.awt.event.*;


public class Paddle extends Rectangle {

	
	public boolean upPressed = false;
	public boolean downPressed = false;
	
	public final int SPEED = 10; // movement speed of paddle
	public static final int PADDLE_WIDTH = 20, PADDLE_LENGTH = GamePanel.GAME_HEIGHT / 4; // size of paddle

	// constructor creates paddle at given location with given dimensions
	public Paddle(int x, int y) {
		super(x, y, PADDLE_WIDTH, PADDLE_LENGTH);
	}

	// updates the direction of the paddle based on user input
	public void keyPressed(KeyEvent e) {
		// Detects when a paddle needs to be moved up and moves it
		if (e.getKeyCode() == KeyEvent.VK_W ||  e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = true;
			move();
		}
	

		// Detects when a paddle needs to be moved down and moves it
		if (e.getKeyCode() == KeyEvent.VK_S ||  e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = true;
			move();
		}
	}

	// Makes the paddle stop moving in that direction
	public void keyReleased(KeyEvent e) {

		// makes the left paddle stop moving
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S 
				||e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN ) {
			
			downPressed = false;
			upPressed = false;
			}
			move();
	}


	public void move() {
	    if (upPressed) {
	        this.y -= SPEED;
	    }
	    if (downPressed) {
	        this.y += SPEED;
	    }
	}

	// draws the current location of the paddle to the screen
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, PADDLE_WIDTH, PADDLE_LENGTH);
	}
}