package game.model;

import game.Main;
import game.view.GameController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class Shooter extends Entity
{
	private SimpleIntegerProperty s;
	private SimpleIntegerProperty h;
	public static final double INIT_X=300;
	public static final double INIT_Y=250;
	public static final double INIT_R=10;
	public static final int MAX_HEALTH=100;
	public static final double ACCELERATION=50;
	public static final double SPEED_LIMIT=100;
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
	public int getScore()
	{
		return s.get();
	}
	public int getHealth()
	{
		return h.get();
	}
	public void bindHealth(Label health)
	{
		health.textProperty().bind(Bindings.concat("health: ").concat(h.asString()));
	}
	public void bindScore(Label score)
	{
		score.textProperty().bind(Bindings.concat("score: ").concat(s.asString()));
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
		if(!RIGHT&&!LEFT)
		{
			xVelocity-=Math.signum(xVelocity)*ACCELERATION/Main.FPS;
		}
		if(UP)
			yVelocity-=ACCELERATION/Main.FPS;
		if(DOWN)
			yVelocity+=ACCELERATION/Main.FPS;
		if(!UP&&!DOWN)
		{
			yVelocity-=Math.signum(yVelocity)*ACCELERATION/Main.FPS;
		}
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
		if(p.getX()+xVelocity/Main.FPS>GameController.XBOUND||p.getX()+xVelocity/Main.FPS<0)
			xVelocity=0;
		if(p.getY()+yVelocity/Main.FPS>GameController.YBOUND||p.getY()+yVelocity/Main.FPS<0)
			yVelocity=0;
		super.move();
	}
	public double getX() {return p.getX();}
	public double getY() {return p.getY();}
}
