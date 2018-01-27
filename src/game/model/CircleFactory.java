package game.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public final class CircleFactory 
{
	//this is a class to generate circle, all the function should be static.
	//TODO
	public static Circle powerUpgrade()
	{
		return new Circle();
	}
	public static Circle scoreUpgrade()
	{
		return new Circle();
	}
	public static Circle progressUpgrade(Color color)
	{
		return new Circle();
	}
}
