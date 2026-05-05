/* Author: Abdullah Majid
* Date: December 2 2024
* Description: Sets up and helps with the playing of the sound
*/
import java.io.*;
import javax.sound.sampled.*;

public class AudioClass {

	private File wrongAnswerFile = new File("GoalScore.wav"); // Obtains the soudn effect for goal scored
	private File applauseFile = new File("Applause.wav"); // Obtains the soudn effect for Applause
	private Clip scored, applause;
	private AudioInputStream streamScoreEffect, streamApplause;
	
//constructor for Audio class
	public AudioClass() {
		try {
			// Sets up the GoalScore file
			streamScoreEffect = AudioSystem.getAudioInputStream(wrongAnswerFile);
			scored = AudioSystem.getClip();
			scored.open(streamScoreEffect);
			
			// Sets up the Applause file
			streamApplause = AudioSystem.getAudioInputStream(applauseFile);
			applause = AudioSystem.getClip();
			applause.open(streamApplause);

		// Catches exceptions
		} catch (UnsupportedAudioFileException e) {
			System.err.println("Audio file format is not supported: " + e.getMessage()); //prints to the standard error.
		} catch (IOException e) {
			System.err.println("Error playing audio file: " + e.getMessage());
		} catch (LineUnavailableException e) {
			System.err.println("Audio line for playing back is unavailable: " + e.getMessage());
		}
	}
	
	//Plays goal scored effect
	public void playScore() {
		scored.setFramePosition(0); // rewinds from start
		scored.start();
	}
	
	// Plays applause
	public void playApplause() {
		applause.loop(4); // makes the soundtrack play 5 times before stopping
		applause.start();
	}

}





