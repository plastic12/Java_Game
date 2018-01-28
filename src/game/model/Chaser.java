package game.model;

public abstract class Chaser extends Enemy 
{
	protected Shooter shooter;
	public Chaser(Shooter shooter)
	{
		this.shooter=shooter;
		setPos();
	}
	@Override
	public void move()
	{
		double x=shooter.getX()-getX();
		double y=shooter.getY()-getY();
		double metric=Math.sqrt(x*x+y*y);
		xVelocity=x*velocity/metric;
		yVelocity=y*velocity/metric;
		super.move();
	}
}
