package game.model;

import game.Main;
import game.util.Distance;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Circle;

public abstract class Entity 
{
	protected Point p;
	private SimpleDoubleProperty radius;
	protected double xVelocity;
	protected double yVelocity;
	public Entity()
	{
		p=new Point();
		radius=new SimpleDoubleProperty();
	}
	public Entity(double x,double y,double radius)
	{
		p=new Point(x,y);this.radius=new SimpleDoubleProperty(radius);
	}
	public Entity(double x,double y,double radius,Circle c)
	{
		this(x,y,radius);
		bindCircle(c);
	}
	public void bindCircle(Circle c)
	{
		c.centerXProperty().bind(p.getXProperty());
		c.centerYProperty().bind(p.getYProperty());
		c.radiusProperty().bind(radius);
	}
	public void move()
	{
		p.setX(p.getX()+xVelocity/Main.FPS);
		p.setY(p.getY()+yVelocity/Main.FPS);
	}
	public boolean isCollide(Entity e)
	{
		return (Distance.getDistance(p.getX(), p.getY(), e.getX(), e.getY())<(radius.get()+e.getR()));
	}
	public double getX(){return p.getX();}
	public double getY() {return p.getY();}
	public double getR() {return radius.get();}
}
