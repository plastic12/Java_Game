package game;

public class Point 
{
	public int x;
	public int y;
	public Point(){}
	public Point(int x,int y){this.x=x;this.y=y;}
	public double getDistance(Point p)
	{
		return Math.sqrt((p.x-x)*(p.x-x)+(p.y-y)*(p.y-y));
	}

}
