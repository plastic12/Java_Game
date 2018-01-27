package game.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public final class CircleFactory 
{
	//this is a class to generate circle, all the function should be static.
	//TODO
	public static Circle powerUpgrade()
	{
		Circle c=new Circle();
		c.setFill(Color.BLACK);
		return c;
	}
	public static Circle scoreUpgrade()
	{
		Circle c=new Circle();
		c.setFill(Color.WHITE);
		c.setStroke(Color.BLACK);
		return c;
	}
	public static Circle progressUpgrade(Color color)
	{
		Circle c=new Circle();
		c.setFill(color);
		return c;
	}
}
