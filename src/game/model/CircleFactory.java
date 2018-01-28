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
	public static Circle getPCCircle()
	{
		Circle c=new Circle();
		c.setFill(Color.RED);
		return c;
	}
	public static Circle getNCCircle()
	{
		Circle c=new Circle();
		c.setFill(Color.ORANGE);
		return c;
	}
	public static Circle getRepliconeCircle()
	{
		Circle c=new Circle();
		c.setFill(Color.YELLOW);
		return c;
	}
	public static Circle getGigaCell()
	{
		Circle c=new Circle();
		c.setFill(Color.GREEN);
		return c;
	}
	public static Circle getCharger()
	{
		Circle c=new Circle();
		c.setFill(Color.BLUE);
		return c;
	}
}
