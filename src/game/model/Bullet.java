package game.model;

public class Bullet 
{
	private Point p1;
	private Point p2;
	private int velocity;
	public Bullet()
	{}
	public Bullet(int x1,int x2,int y1,int y2,int velocity)
	{
		p1=new Point(x1,y1);
		p2=new Point(x2,y2);
		this.velocity=velocity;
	}

}
