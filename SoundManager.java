import javax.sound.sampled.AudioInputStream;		
import javax.sound.sampled.*;
import java.io.*;

import java.util.HashMap;			

public class SoundManager {				
	HashMap<String, Clip> clips;

   	Clip hitClip = null;				
   	Clip appearClip = null;				
   	Clip backgroundClip = null;			

	private static SoundManager instance = null;	

	private SoundManager () {
		clips = new HashMap<String, Clip>();

		Clip clip = loadClip("./sounds/kick.wav");
		clips.put("kick", clip);

		clip = loadClip("./sounds/whistle.wav");
		clips.put("whistle", clip);

		clip = loadClip("./sounds/game_intro.wav");
		clips.put("game_intro", clip);
		
		clip = loadClip("./sounds/goal_chant.wav");
		clips.put("goal", clip);

		clip = loadClip("./sounds/goal_missed.wav");
		clips.put("missed", clip);

		clip = loadClip("./sounds/backg.wav");
		clips.put("background", clip);
	}

	public static SoundManager getInstance() {	
		if (instance == null)
			instance = new SoundManager();
		
		return instance;
	}		

	public Clip getClip (String title) {

		return clips.get(title);
	}

    	public Clip loadClip (String fileName) {	// gets clip from the specified file
 		AudioInputStream audioIn;
		Clip clip = null;

		try {
    			File file = new File(fileName);
    			audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL()); 
    			clip = AudioSystem.getClip();
    			clip.open(audioIn);
		}
		catch (Exception e) {
 			System.out.println ("Error opening sound files: " + e);
		}
    		return clip;
    	}

    	public void playSound(String title, Boolean looping) {
		Clip clip = getClip(title);
		if (clip != null) {
			clip.setFramePosition(0);
			if (looping)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			else
				clip.start();
		}
    	}

    	public void stopSound(String title) {
		Clip clip = getClip(title);
		if (clip != null) {
			clip.stop();
		}
    	}
}