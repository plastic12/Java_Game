package game.model;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;

public class CircleDesign 
{
	private Circle circle;
	private ArrayList<Node> components;
	private Rotate rotation;
	
	public CircleDesign(Circle circle)
	{
		this.circle=circle;
		components=new ArrayList<Node>();
		rotation=new Rotate();
        rotation.pivotXProperty().bind(circle.centerXProperty());
        rotation.pivotYProperty().bind(circle.centerYProperty());
	}
	//add Node after bind all the attribute to circle
	public void add(Node node)
	{
		components.add(node);
		node.getTransforms().add(rotation);
	}
	public void setRotate(double value)
	{
		rotation.setAngle(value);
	}
	public Circle getCircle()
	{
		return circle;
	}
	public void setRadius(double radius)
	{
		circle.setRadius(radius);
	}
	public DoubleProperty centerXProperty()
	{
		return circle.centerXProperty();
	}
	public DoubleProperty centerYProperty()
	{
		return circle.centerYProperty();
	}
	public void setOpacity(double value)
	{
		circle.setOpacity(value);
		for(Node n:components)
		{
			n.setOpacity(value);
		}
	}
	public void render(ObservableList<Node> observablelist)
	{
		observablelist.add(circle);
		observablelist.addAll(components);
	}
}
