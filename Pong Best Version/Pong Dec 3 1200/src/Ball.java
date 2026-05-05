/* Author: Abdullah Majid 
 * Date: December 2 2024
 * Description: Defines the behaviors for the ball
 * 
*/
import java.awt.*;
import java.awt.event.*;

public class Ball extends Rectangle {

	public static int ballYVelocity = 5;
	public static int ballXVelocity = 6;

	public static final int SPEED = 5; // movement speed of ball
	public static int BALL_DIAMETER = 20; // size of ball

	// constructor creates ball at given location with given dimensions
	public Ball(int x, int y) {
		super(x, y, BALL_DIAMETER, BALL_DIAMETER);
	}

	//moves the ball
	public void move() {
		x += ballXVelocity;
		y += ballYVelocity;
	}

	// draws the current location of the ball to the screen
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, BALL_DIAMETER, BALL_DIAMETER);
	}

}