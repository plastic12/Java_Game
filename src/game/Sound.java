package game;

import java.net.URISyntaxException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound 
{
	private Media music;
	private MediaPlayer player;
		
	public void set() {
		try {
			music = new Media(ClassLoader.getSystemResource("liquid.wav").toURI().toString());
		} catch (URISyntaxException e) {
			System.out.println("Media not found");
		}
		MediaPlayer player = new MediaPlayer(music);
		player.play();
	}
}
