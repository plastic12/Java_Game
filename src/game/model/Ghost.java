package game.model;

import game.Main;

public class Ghost extends Chaser
{
	private static double INIT_R=10;
	private static int HEALTHPARA=20;
	private static int damagePARA=10;
	private static int velocityPARA=20;
	private boolean invisible=false;
	private int invisibleCount;
	public Ghost(Shooter shooter)
	{
		super(shooter);
		radius=INIT_R;
		velocity=velocityPARA;
		damage=damagePARA;
		life=HEALTHPARA;
		bindCircle(CircleFactory.getGhost());
	}
	@Override
	public boolean getShot(Bullet b) 
	{
		double distance=b.getDistance(this);

		if(distance<getR()&&!invisible)
		{
			life-=b.getDamage();
			return true;
		}
		return false;
	}
	@Override
	public void move()
	{
		super.move();
		if(invisibleCount==0)
		{
			invisibleCount=(int)(Math.random()*10*Main.FPS);
			toggleInvisible();
		}
		invisibleCount--;
	}
	private void toggleInvisible()
	{
		invisible=!invisible;
		if(invisible)
		{
			circle.setOpacity(0.5);
			
		}
		else
		{
			circle.setOpacity(1);
		}
	}
}
