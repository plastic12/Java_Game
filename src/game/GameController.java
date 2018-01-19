package game;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController 
{
	@FXML
	private Label scoreLabel;
	@FXML
	private Label healthLabel;
	private Shooter shooter;
	private SimpleIntegerProperty s;
	private SimpleIntegerProperty h;
	public GameController() {
	}
	public void init()
	{
		shooter=new Shooter();
		//setScore(0);
		scoreLabel.textProperty().bind(Bindings.concat("score: ").concat(shooter.getScore().asString()));
		healthLabel.textProperty().bind(Bindings.concat("health: ").concat(shooter.getHealth().asString()));
	}
	public void scoreInc(int increment)
	{
		shooter.scoreInc(increment);
	}
	public void healthInc(int increment) {shooter.healthInc(increment);}
}
