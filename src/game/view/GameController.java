package game.view;

import game.model.Shooter;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class GameController 
{
	@FXML
	private Label scoreLabel;
	@FXML
	private Label healthLabel;
	@FXML
	private Circle shooterRender;
	private Shooter shooter;
	public GameController() {
	}
	public void init()
	{
		//test=new SimpleDoubleProperty(100);
		shooter=new Shooter();
		//test.bindBidirectional(shooterRender.layoutXProperty());
		//shooterRender.centerXProperty().bind(test);
		shooter.bind(shooterRender);
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
