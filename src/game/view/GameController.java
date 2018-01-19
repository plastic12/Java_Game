package game.view;

import game.model.Shooter;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
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
		shooter=new Shooter();
		shooter.bind(shooterRender);
		//setScore(0);
		scoreLabel.textProperty().bind(Bindings.concat("score: ").concat(shooter.getScore().asString()));
		healthLabel.textProperty().bind(Bindings.concat("health: ").concat(shooter.getHealth().asString()));
	}
	public void handler(KeyEvent e)
	{
		switch(e.getCode())
		{
		case LEFT:
			shooter.LEFT=true;
			break;
		case RIGHT:
			shooter.RIGHT=true;
			break;
		case DOWN:
			shooter.DOWN=true;
			break;
		case UP:
			shooter.UP=true;
			break;
		default:
			break;
		}
	}
	public void movePhase()
	{
		shooter.accelerate();
		shooter.move();
	}
	public void scoreInc(int increment)
	{
		shooter.scoreInc(increment);
	}
	public void healthInc(int increment) {shooter.healthInc(increment);}
}
