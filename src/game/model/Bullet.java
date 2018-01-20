package game.model;

import game.util.MatrixHelper;

public class Bullet 
{
	private Point p1;
	private Point p2;
	private double[][] affineM;
	public static final double velocity=20;
	public static final double length=5;
	public Bullet()
	{}
	public Bullet(int x1,int x2,int y1,int y2,int velocity)
	{
		p1=new Point(x1,y1);
		p2=new Point(x2,y2);
		affineM=MatrixHelper.getAffineTransformation(x1, y1, x2, y2);
	}
	

}
