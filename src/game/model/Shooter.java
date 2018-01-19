package game.model;

import game.Main;
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
	public static final double ACCELERATION=10;
	public static final double SPEED_LIMIT=50;
	public boolean LEFT=false;
	public boolean RIGHT=false;
	public boolean DOWN=false;
	public boolean UP=false;
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
	public void accelerate()
	{
		if(RIGHT)
			xVelocity+=ACCELERATION/Main.FPS;
		if(LEFT)
			xVelocity-=ACCELERATION/Main.FPS;
		if(UP)
			yVelocity-=ACCELERATION/Main.FPS;
		if(DOWN)
			yVelocity+=ACCELERATION/Main.FPS;
		if(xVelocity>SPEED_LIMIT)
			xVelocity=SPEED_LIMIT;
		if(xVelocity<-SPEED_LIMIT)
			xVelocity=-SPEED_LIMIT;
		if(yVelocity>SPEED_LIMIT)
			yVelocity=SPEED_LIMIT;
		if(yVelocity<-SPEED_LIMIT)
			yVelocity=-SPEED_LIMIT;
	}
	@Override
	public void move()
	{
		//TODO add boundary criterion
		super.move();
	}
}
