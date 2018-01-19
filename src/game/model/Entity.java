package game.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Circle;

public abstract class Entity 
{
	private Point p;
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
		bind(c);
	}
	public void bind(Circle c)
	{
		c.centerXProperty().bind(p.getXProperty());
		c.centerYProperty().bind(p.getYProperty());
		c.radiusProperty().bind(radius);
	}
	

}
