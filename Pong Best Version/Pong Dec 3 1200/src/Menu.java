/* 	Author: Abdullah Majid
 * 	Date: December 2 2024
 *  Description: Is the main menu in the game
 *  Sources used to help with main menu: https://www.youtube.com/watch?app=desktop&v=FZWX5WoGW00&t=132s 
 */

import java.awt.*;
import java.awt.event.*;

public class Menu implements KeyListener {

	//creates boxes
	 Rectangle playButton = new Rectangle(GamePanel.GAME_WIDTH / 4, 300, 300, 90);
	 Rectangle quitButton = new Rectangle(GamePanel.GAME_WIDTH / 2, 300, 300, 90);

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		//sets the font for the header
		Font headerFont = new Font("SansSerif", Font.BOLD, 90);
		g.setFont(headerFont);

		g.setColor(Color.WHITE);

		//draws header
		g.drawString("WELCOME TO PONG", GamePanel.GAME_WIDTH / 7, 160);


		//creates new font
		Font buttonFont = new Font("SansSerif", Font.BOLD, 30); // sets font

		g.setFont(buttonFont); //uses font

		//adds boxes to contain text
		g2d.draw(playButton); 
		g2d.draw(quitButton); 

		//places the text inside the boxes
		g.drawString("Play - Press Enter", playButton.x + 15, playButton.y + 55); 
		g.drawString("Quit - Press Q", quitButton.x + 15, playButton.y + 55);
		
		//draws the text for how to play
		g.drawString("Left Player Controls: Use 'w' and 's' keys to control the paddle", 60,GamePanel.GAME_HEIGHT-200);
		g.drawString("Right Player Controls: Use up and down arrows to control the paddle", 60,GamePanel.GAME_HEIGHT-100);

	}

	public void keyPressed(KeyEvent e) {

		// main menu inputs allows user to decide to enter game or not
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			GamePanel.GameState = GamePanel.GAMESTATE.GAME;
		}
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			System.exit(0); //quits program
		}
	}

	public void keyReleased(KeyEvent e) {
		// main menu inputs allows user to decide to enter game or not
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			GamePanel.GameState = GamePanel.GAMESTATE.GAME;
		}
	}

	public void keyTyped(KeyEvent e) {

	}

}
