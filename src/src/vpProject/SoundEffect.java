package vpProject;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffect extends Thread{
	static Clip clip = null;
	static Clip clip2 = null;
	
    public static void buttonSound() {
    	final String pathToClip = "bgm/buttonSound.wav";
        boolean soundLoaded;
        
	    try {
	       File file1 = new File(pathToClip);
	       AudioInputStream audioIn = AudioSystem.getAudioInputStream(file1);
	       
	       clip = AudioSystem.getClip();
	       clip.open(audioIn);
	       soundLoaded = true;
	    }catch (UnsupportedAudioFileException e) {
	       soundLoaded = false; 
	       e.printStackTrace();
	    }catch (IOException e) {
	         soundLoaded = false; 
	         e.printStackTrace();
	    }catch (LineUnavailableException e) {
	       soundLoaded = false; 
	       e.printStackTrace();
	    }
	    if(soundLoaded) {
	    	clip.start();
		}
	    clip.addLineListener(new LineListener() {
	        public void update(LineEvent myLineEvent) {
	          if (myLineEvent.getType() == LineEvent.Type.STOP)
	            clip.close();
	        }
	     });
    }
    
    public static void winSound() {
    	final String pathToClip = "bgm/StageWin.wav";
        boolean soundLoaded;
        
	    try {
	       File file1 = new File(pathToClip);
	       AudioInputStream audioIn = AudioSystem.getAudioInputStream(file1);
	       
	       clip2 = AudioSystem.getClip();
	       clip2.open(audioIn);
	       soundLoaded = true;
	    }catch (UnsupportedAudioFileException e) {
	       soundLoaded = false; 
	       e.printStackTrace();
	    }catch (IOException e) {
	         soundLoaded = false; 
	         e.printStackTrace();
	    }catch (LineUnavailableException e) {
	       soundLoaded = false; 
	       e.printStackTrace();
	    }
	    if(soundLoaded) {
	    	TicTacToe.bgm.pauseBgm();
	    	clip2.start();
		}
	    clip2.addLineListener(new LineListener() {
	        public void update(LineEvent myLineEvent) {
	          if (myLineEvent.getType() == LineEvent.Type.STOP)
	            clip2.close();
	        }
	     });
    }
	
    public static void stopBgm() {
    	if(TicTacToe.backgroundMusic)
    		TicTacToe.bgm.resumeBgm();
    	clip2.stop();
    }
    
    public static void startBgm() {
    	TicTacToe.bgm.pauseBgm();
    	clip2.start();
    }

}
