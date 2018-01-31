package game.model;

import game.Main;
import game.util.Distance;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Circle;

public abstract class Entity 
{
	protected Point p;
	protected double radius;
	protected Circle circle;
	protected double xVelocity;
	protected double yVelocity;
	public Entity()
	{
		p=new Point();
	}
	public Entity(double x,double y)
	{
		p=new Point(x,y);
	}
	public void bindCircle(Circle c)
	{
		circle=c;
		c.centerXProperty().bind(p.getXProperty());
		c.centerYProperty().bind(p.getYProperty());
		c.setRadius(radius);
	}
	public Circle getCircle() {return circle;}
	public void move()
	{
		p.setX(p.getX()+xVelocity/Main.FPS);
		p.setY(p.getY()+yVelocity/Main.FPS);
	}
	public boolean isCollide(Entity e)
	{
		return (Distance.getDistance(p.getX(), p.getY(), e.getX(), e.getY())<(radius+e.getR()));
	}
	public double getX(){return p.getX();}
	public double getY() {return p.getY();}
	public double getR() {return radius;}
}
