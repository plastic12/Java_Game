package game.model;

import game.Main;
import game.view.GameController;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Shooter extends Entity
{
	public static final double INIT_X=300;
	public static final double INIT_Y=250;
	public static final double INIT_R=10;
	
	
	
	public static final int MAX_HEALTH=100;
	public static final int MAX_POWER=100;
	public static final double ACCELERATION=50;
	public static final double SPEED_LIMIT=100;
	private SimpleIntegerProperty s;
	private SimpleIntegerProperty health;
	private SimpleIntegerProperty power;
	public boolean LEFT=false;
	public boolean RIGHT=false;
	public boolean DOWN=false;
	public boolean UP=false;
	public Shooter()
	{
		super(INIT_X,INIT_Y,INIT_R);
		s=new SimpleIntegerProperty(0);
		health=new SimpleIntegerProperty(MAX_HEALTH);
		power=new SimpleIntegerProperty(0);
		xVelocity=0;
		yVelocity=0;
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
	public void bindHealth(Rectangle healthBar)
	{
		healthBar.widthProperty().bind(DoubleExpression.doubleExpression(health).divide(MAX_HEALTH).multiply(100));
	}
	public void bindPower(Rectangle powerBar)
	{
		powerBar.widthProperty().bind(power);
	}
	public void bindScore(Label score)
	{
		score.textProperty().bind(Bindings.concat("score: ").concat(s.asString()));
	}
	public int getHealth()
	{
		return health.get();
	}
	//TODO
	public void getHit()
	{
		
	}
	public int getScore()
	{
		return s.get();
	}
	public double getX() {return p.getX();}
	public double getY() {return p.getY();}
	public void healthInc(int increment)
	{
		int newValue=health.get()+increment;
		if(newValue>MAX_HEALTH)
			newValue=MAX_HEALTH;
		health.set(newValue);
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
	public void powerInc(int increment)
	{
		int temp=power.get()+increment;
		if(temp<0)
			temp=0;
		else if(temp>MAX_POWER)
			temp=MAX_POWER;
		return;
	}
	public void scoreInc(int increment)
	{
		s.set(s.get()+increment);
	}
}
