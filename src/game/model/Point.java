package game.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Point 
{
	private SimpleDoubleProperty x;
	private SimpleDoubleProperty y;
	public Point(){}
	public Point(double x,double y){this.x=new SimpleDoubleProperty(x);this.y=new SimpleDoubleProperty(y);}
	public double getDistance(Point p)
	{
		return Math.sqrt((p.getX()-getX())*(p.getX()-getX())+(p.getY()-getY())*(p.getY()-getY()));
	}
	public double getX() {return x.get();}
	public double getY() {return y.get();}
	public DoubleProperty getXProperty() {return x;}
	public DoubleProperty getYProperty() {return y;}
	public void setX(double x) {this.x.set(x);}
	public void setY(double y) {this.y.set(y);}

}
