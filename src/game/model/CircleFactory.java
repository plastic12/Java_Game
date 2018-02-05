package game.model;

import game.Main;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;

public final class CircleFactory 
{
	//this is a class to generate circle, all the function should be static.
	//gen power upgrade circle
	public static CircleDesign powerUpgrade()
	{
		Circle c=new Circle();
		c.setFill(Color.BLACK);
		return new CircleDesign(c);
	}
	//gen score upgrade circle
	public static CircleDesign scoreUpgrade()
	{
		Circle c=new Circle();
		c.setFill(Color.WHITE);
		c.setStroke(Color.BLACK);
		return new CircleDesign(c);
	}
	//gen progress upgrade circle
	public static CircleDesign progressUpgrade(Color color)
	{
		Circle c=new Circle();
		c.setFill(color);
		return new CircleDesign(c);
	}
	//gen PC enemy(level 1)
	public static CircleDesign getPCCircle()
	{
		Circle c=new Circle();
		c.setFill(Color.WHITE);
		if(Main.color)
			c.setFill(Color.RED);
		else
			c.setStroke(Color.BLACK);
		CircleDesign design=new CircleDesign(c);
		return design;
	}
	//gen NC enemy(level 2)
	public static CircleDesign getNCCircle()
	{
		Circle c=new Circle();
		c.setFill(Color.BLACK);
		if(Main.color)
			c.setFill(Color.ORANGE);
		return new CircleDesign(c);
	}
	//gen split(generate after Replicone explode)
	public static CircleDesign getSplit()
	{
		Circle c=new Circle();
		c.setFill(Color.BLACK);
		if(Main.color)
			c.setFill(Color.YELLOW);
		return new CircleDesign(c);
	}
	//gen replicone(level 3)
	public static CircleDesign getRepliconeCircle()
	{
		Color fill=(Color.BLACK);
		if(Main.color)
			fill=Color.YELLOW;
		Circle c=new Circle();
		c.setFill(Color.WHITE);
		//add four chord
		Arc arc1 = new Arc(c.getCenterX(), c.getCenterY(), c.getRadius(), c.getRadius(), 315.0f, 90.0f);
		arc1.setFill(fill);
		arc1.centerXProperty().bind(c.centerXProperty());
		arc1.centerYProperty().bind(c.centerYProperty());
		arc1.radiusXProperty().bind(c.radiusProperty());
		arc1.radiusYProperty().bind(c.radiusProperty());
		arc1.setType(ArcType.CHORD);

		Arc arc2 = new Arc(c.getCenterX(), c.getCenterY(), c.getRadius(), c.getRadius(), 45.0f, 90.0f);
		arc2.setFill(fill);
		arc2.centerXProperty().bind(c.centerXProperty());
		arc2.centerYProperty().bind(c.centerYProperty());
		arc2.radiusXProperty().bind(c.radiusProperty());
		arc2.radiusYProperty().bind(c.radiusProperty());
		arc2.setType(ArcType.CHORD);

		Arc arc3 = new Arc(c.getCenterX(), c.getCenterY(), c.getRadius(), c.getRadius(), 135.0f, 90.0f);
		arc3.setFill(fill);
		arc3.centerXProperty().bind(c.centerXProperty());
		arc3.centerYProperty().bind(c.centerYProperty());
		arc3.radiusXProperty().bind(c.radiusProperty());
		arc3.radiusYProperty().bind(c.radiusProperty());
		arc3.setType(ArcType.CHORD);

		Arc arc4 = new Arc(c.getCenterX(), c.getCenterY(), c.getRadius(), c.getRadius(), 225.0f, 90.0f);
		arc4.setFill(fill);
		arc4.centerXProperty().bind(c.centerXProperty());
		arc4.centerYProperty().bind(c.centerYProperty());
		arc4.radiusXProperty().bind(c.radiusProperty());
		arc4.radiusYProperty().bind(c.radiusProperty());
		arc4.setType(ArcType.CHORD);


		CircleDesign design=new CircleDesign(c);
		design.add(arc1);
		design.add(arc2);
		design.add(arc3);
		design.add(arc4);
		return design;
	}
	//gen giga cell(level 4)
	public static CircleDesign getGigaCell()
	{
		Circle c=new Circle();
		if(Main.color)
			c.setFill(Color.GREEN);
		Circle cInner=new Circle();
		//add inner circle
		cInner.setFill(Color.WHITE);
		cInner.centerXProperty().bind(c.centerXProperty());
		cInner.centerYProperty().bind(c.centerYProperty());
		cInner.radiusProperty().bind(c.radiusProperty().divide(2));
		CircleDesign design=new CircleDesign(c);
		design.add(cInner);
		return design;
	}
	//gen charger(level 5)
	public static CircleDesign getCharger()
	{
		Color fill=(Color.BLACK);
		if(Main.color)
			fill=Color.BLUE;
		Circle c=new Circle();
		c.setFill(Color.WHITE);
		c.setStroke(fill);
		//add four round arc
		Arc arc1 = new Arc(c.getCenterX(), c.getCenterY(), c.getRadius(), c.getRadius(), 337.5f, 45.0f);
		arc1.setFill(fill);
		arc1.centerXProperty().bind(c.centerXProperty());
		arc1.centerYProperty().bind(c.centerYProperty());
		arc1.radiusXProperty().bind(c.radiusProperty());
		arc1.radiusYProperty().bind(c.radiusProperty());
		arc1.setType(ArcType.ROUND);

		Arc arc2 = new Arc(c.getCenterX(), c.getCenterY(), c.getRadius(), c.getRadius(), 67.5f, 45.0f);
		arc2.setFill(fill);
		arc2.centerXProperty().bind(c.centerXProperty());
		arc2.centerYProperty().bind(c.centerYProperty());
		arc2.radiusXProperty().bind(c.radiusProperty());
		arc2.radiusYProperty().bind(c.radiusProperty());
		arc2.setType(ArcType.ROUND);

		Arc arc3 = new Arc(c.getCenterX(), c.getCenterY(), c.getRadius(), c.getRadius(), 157.5f, 45.0f);
		arc3.setFill(fill);
		arc3.centerXProperty().bind(c.centerXProperty());
		arc3.centerYProperty().bind(c.centerYProperty());
		arc3.radiusXProperty().bind(c.radiusProperty());
		arc3.radiusYProperty().bind(c.radiusProperty());
		arc3.setType(ArcType.ROUND);

		Arc arc4 = new Arc(c.getCenterX(), c.getCenterY(), c.getRadius(), c.getRadius(), 247.5f, 45.0f);
		arc4.setFill(fill);
		arc4.centerXProperty().bind(c.centerXProperty());
		arc4.centerYProperty().bind(c.centerYProperty());
		arc4.radiusXProperty().bind(c.radiusProperty());
		arc4.radiusYProperty().bind(c.radiusProperty());
		arc4.setType(ArcType.ROUND);


		CircleDesign design=new CircleDesign(c);
		design.add(arc1);
		design.add(arc2);
		design.add(arc3);
		design.add(arc4);
		return design;
		}
	//gen swinger(level 6)
	public static CircleDesign getSwinger()
	{
		Color fill=(Color.BLACK);
		if(Main.color)
			fill=Color.INDIGO;
		Circle c=new Circle();
		c.setFill(Color.WHITE);
		c.setStroke(fill);
		//add two round arc
		Arc arc1 = new Arc(c.getCenterX(), c.getCenterY(), c.getRadius(), c.getRadius(), 315.5f, 90.0f);
		arc1.setFill(fill);
		arc1.centerXProperty().bind(c.centerXProperty());
		arc1.centerYProperty().bind(c.centerYProperty());
		arc1.radiusXProperty().bind(c.radiusProperty());
		arc1.radiusYProperty().bind(c.radiusProperty());
		arc1.setType(ArcType.ROUND);

		Arc arc2 = new Arc(c.getCenterX(), c.getCenterY(), c.getRadius(), c.getRadius(), 135.5f, 90.0f);
		arc2.setFill(fill);
		arc2.centerXProperty().bind(c.centerXProperty());
		arc2.centerYProperty().bind(c.centerYProperty());
		arc2.radiusXProperty().bind(c.radiusProperty());
		arc2.radiusYProperty().bind(c.radiusProperty());
		arc2.setType(ArcType.ROUND);
		CircleDesign design=new CircleDesign(c);
		design.add(arc1);
		design.add(arc2);
		return design;
	}
	//gen orbit(surrounding swinger)
	public static CircleDesign getOrbit()
	{
		Circle c=new Circle();
		c.setFill(Color.BLACK);
		if(Main.color)
			c.setFill(Color.INDIGO);
		return new CircleDesign(c);
	}
	//gen ghost(level 7)
	public static CircleDesign getGhost()
	{
		Circle c=new Circle();
		c.setFill(Color.BLACK);
		if(Main.color)
			c.setFill(Color.web("#8B00FF"));
		return new CircleDesign(c);
	}
}
