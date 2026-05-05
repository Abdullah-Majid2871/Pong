/*	Author: Abdullah Majid
 * 	Date: December 2 2024
 * 	Description: Defines the player's scores
 */	

import java.awt.*;

public class Scoreclass extends Rectangle {

	public static int playerLeftScore = 0, playerRightScore = 0;
	public  AudioClass audio = new AudioClass();

	
	public Scoreclass(int x, int y) {
		super(x, y, GamePanel.GAME_WIDTH / 2, 20);
	}

	public void draw(Graphics g) {
		
		// Set the font size to 20 and text font is arial
		g.setFont(new Font("SansSerif", Font.BOLD, 45));
		g.setColor(Color.WHITE);

		// win condition
		if (playerLeftScore >= 10 || playerRightScore >= 10) {
			audio.playApplause();
			
			//Makes the ball invisible by setting its diameter to 0
			Ball.BALL_DIAMETER = 0;
			//makes it so that another goal can't be scored
			Ball.ballXVelocity = 0;
			Ball.ballYVelocity = 0;


			//displays who won
			if (playerLeftScore > playerRightScore) {
				g.drawString("PLAYER 1 WINS!", GamePanel.GAME_WIDTH / 6, GamePanel.GAME_HEIGHT / 2);
			}
			else if (playerRightScore > playerLeftScore) {
				g.drawString("PLAYER 2 WINS!", GamePanel.GAME_WIDTH/2 + 50, GamePanel.GAME_HEIGHT / 2);
			}
		}

		// Draws line dividing the territories
		g.drawLine(GamePanel.GAME_WIDTH / 2, 0, GamePanel.GAME_WIDTH / 2, GamePanel.GAME_HEIGHT);

		// Draws the score for the players
		g.drawString(String.valueOf(playerLeftScore), GamePanel.GAME_WIDTH / 4, 60);
		g.drawString(String.valueOf(playerRightScore), GamePanel.GAME_WIDTH - GamePanel.GAME_WIDTH / 4, 60);

	}
}