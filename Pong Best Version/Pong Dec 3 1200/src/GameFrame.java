/* Author: Abdullah Majid
 * Dec 3 2024
 * Description: GameFrame class establishes the frame (window) for the game
*/ 
import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{

  GamePanel panel;
  
  public GameFrame(){
    panel = new GamePanel(); //run GamePanel constructor

    this.add(panel);
    
    this.setTitle("Pong"); //set title for frame
    
    this.setBackground(Color.BLACK); //sets background to black
        
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Allows the window to close if the user presses the x button
    
    this.pack();//makes components fit in window
    
    this.setResizable(false); //makes the window unresizable
    
    this.setVisible(true); //makes window visible to user
    
    this.setLocationRelativeTo(null);//set window in middle of screen
  }
  
}