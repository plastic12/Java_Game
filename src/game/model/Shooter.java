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

//shooter class, the entity player control
public class Shooter extends Entity
{
	//init value
	public static final double INIT_X=300;
	public static final double INIT_Y=250;
	private static final double INIT_R=10;
	//constant
	public static final int MAX_HEALTH=100;
	public static final int MAX_POWER=100;
	public static final double ACCELERATION=50;
	public static final double SPEED_LIMIT=100;
	//attribute
	private SimpleIntegerProperty health;
	private SimpleIntegerProperty power;
	public boolean LEFT=false;
	public boolean RIGHT=false;
	public boolean DOWN=false;
	public boolean UP=false;
	//helper variable
	private double mouseX=0;
	private double mouseY=0;
	private int shootCounter=0;
	public static final double shootfreq=5;
	public Shooter()
	{
		super(INIT_X,INIT_Y);
		radius=INIT_R;
		health=new SimpleIntegerProperty(MAX_HEALTH);
		power=new SimpleIntegerProperty(0);
		xVelocity=0;
		yVelocity=0;
	}
	//accelerate if mouse press
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
	//bind health to health bar in game
	public void bindHealth(Rectangle healthBar)
	{
		healthBar.widthProperty().bind(DoubleExpression.doubleExpression(health).divide(MAX_HEALTH).multiply(100));
	}
	//bind power to power bar in game
	public void bindPower(Rectangle powerBar)
	{
		powerBar.widthProperty().bind(power);
	}
	//get health
	public int getHealth()
	{
		return health.get();
	}
	//increase health
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
		//restrict out bound
		if(p.getX()+xVelocity/Main.FPS+getR()>GameController.XBOUND||p.getX()+xVelocity/Main.FPS-getR()<0)
			xVelocity=0;
		if(p.getY()+yVelocity/Main.FPS+getR()>GameController.YBOUND||p.getY()+yVelocity/Main.FPS-getR()<0)
			yVelocity=0;
		super.move();
	}
	//increase power
	public void powerInc(int increment)
	{
		int temp=power.get()+increment;
		if(temp<0)
			temp=0;
		else if(temp>MAX_POWER)
			temp=MAX_POWER;
		power.set(temp);
		return;
	}
	//setMouseX in the handler
	public void setMouseX(double mouseX) {
		this.mouseX = mouseX;
	}
	public void setMouseY(double mouseY) {
		this.mouseY = mouseY;
	}
	//add bullet
	private Bullet addBullet()
	{
		return new Bullet(getX(),getY(),mouseX,mouseY,getDamage());
	}
	//get damage of bullet
	public int getDamage()
	{
		return 10+power.get()*10/100;
	}
	//shoot bullet according to counter
	public Bullet shoot()
	{
		shootCounter++;
		if(shootCounter>=Main.FPS/shootfreq)
		{
			shootCounter=0;
			return addBullet();
		}
		else
			return null;
	}
}
