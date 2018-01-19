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
	public static final double XBOUND=600;
	public static final double YBOUND=500;
	public GameController() {
	}
	public void init()
	{	
		shooter=new Shooter();
		shooter.bind(shooterRender);
		System.out.println(shooterRender.getCenterX()+" "+shooter.getX());
		System.out.println(shooterRender.getCenterY()+" "+shooter.getY());
		scoreLabel.textProperty().bind(Bindings.concat("score: ").concat(shooter.getScore().asString()));
		healthLabel.textProperty().bind(Bindings.concat("health: ").concat(shooter.getHealth().asString()));
	}
	public void pressHandler(KeyEvent e)
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
	public void releaseHandler(KeyEvent e)
	{
		switch(e.getCode())
		{
		case LEFT:
			shooter.LEFT=false;
			break;
		case RIGHT:
			shooter.RIGHT=false;
			break;
		case DOWN:
			shooter.DOWN=false;
			break;
		case UP:
			shooter.UP=false;
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
