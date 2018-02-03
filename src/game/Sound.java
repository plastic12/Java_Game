package game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound 
{
	private boolean playing = false;
	private MediaPlayer playerPlaying;

	public void menu() {
		if(Main.music)
		{
			if (playing)
				playerPlaying.pause();
			Media music = new Media(Sound.class.getResource("resource/liquid.wav").toString());
			MediaPlayer player = new MediaPlayer(music);

			player.play();
			playerPlaying = player;
			playing = true;
		}
	}

	public void game() {
		if(Main.music)
		{
			if (playing)
				playerPlaying.pause();
			Media music = new Media(Sound.class.getResource("resource/game.mp3").toString());
			MediaPlayer player = new MediaPlayer(music);
			player.setVolume(1.0);
			player.setCycleCount(MediaPlayer.INDEFINITE);
			player.play();
			playerPlaying = player;
			playing = true;
		}
	}

	public void boss() {
		if(Main.music)
		{
			if (playing)
				playerPlaying.pause();
			Media music = new Media(Sound.class.getResource("resource/boss.wav").toString());
			MediaPlayer player = new MediaPlayer(music);
			player.setCycleCount(MediaPlayer.INDEFINITE);
			player.play();
			playerPlaying = player;
			playing = true;
		}
	}

	public void gameOver() {
		if(Main.music)
		{
			if (playing)
				playerPlaying.pause();
			Media music = new Media(Sound.class.getResource("resource/gameOver.wav").toString());
			MediaPlayer player = new MediaPlayer(music);
			player.play();
			playerPlaying = player;
			playing = true;
		}
	}
}
