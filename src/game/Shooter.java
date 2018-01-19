package game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Shooter 
{
	private SimpleIntegerProperty s;
	private SimpleIntegerProperty h;
	private int xVelocity;
	private int yVelocity;
	private SimpleDoubleProperty xPos;
	private SimpleDoubleProperty yPos;
	public static final int MAX_HEALTH=100;
	public Shooter()
	{
		s=new SimpleIntegerProperty(0);
		h=new SimpleIntegerProperty(MAX_HEALTH);
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
