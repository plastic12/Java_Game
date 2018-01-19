package game;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController 
{
	@FXML
	private Label ScoreLabel;
	@FXML
	private Label HealthLabel;

	public GameController() {
	}
	public void setText()
	{
		ScoreLabel.setText("Score: 0");
		HealthLabel.setText("Health: 100");
	}

}
