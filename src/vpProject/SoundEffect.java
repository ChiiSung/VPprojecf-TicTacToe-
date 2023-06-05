package vpProject;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffect {
	
    public static void buttonSound() {
    	final String pathToClip = "bgm/buttonSound.wav";
        boolean soundLoaded;
        Clip clip = null;
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
    }
    
    public static void winSound() {
    	final String pathToClip = "bgm/Rick Astley - Never Gonna Give You Up (Official Music Video).wav";
        boolean soundLoaded;
        Clip clip = null;
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
	    
	    clip.close();
    }
	

}
