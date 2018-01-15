package game;

public class Circle 
{
	private Point p;
	private int radius;
	public Circle()
	{
		p=new Point();
		radius=0;
	}
	public Circle(int x,int y,int radius)
	{
		p=new Point(x,y);this.radius=radius;
	}

}
