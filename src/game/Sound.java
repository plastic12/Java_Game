package game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound 
{
	private Media music;
	private MediaPlayer player;
		
	public void set() {
		music=new Media(Sound.class.getResource("resource/liquid.wav").toString());
		MediaPlayer player = new MediaPlayer(music);
		player.play();
	}
}
