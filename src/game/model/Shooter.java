package game.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;

public class Shooter extends Entity
{
	private SimpleIntegerProperty s;
	private SimpleIntegerProperty h;
	public static final double INIT_X=300;
	public static final double INIT_Y=250;
	public static final double INIT_R=10;
	public static final int MAX_HEALTH=100;
	public Shooter()
	{
		super(INIT_X,INIT_Y,INIT_R);
		s=new SimpleIntegerProperty(0);
		h=new SimpleIntegerProperty(MAX_HEALTH);
		xVelocity=0;
		yVelocity=0;
	}
	
	public void scoreInc(int increment)
	{
		s.set(s.get()+increment);
	}
	public IntegerProperty getScore()
	{
		return s;
	}
	public IntegerProperty getHealth()
	{
		return h;
	}
	public void healthInc(int increment)
	{
		int newValue=h.get()+increment;
		if(newValue>MAX_HEALTH)
			newValue=MAX_HEALTH;
		h.set(newValue);
	}

}
