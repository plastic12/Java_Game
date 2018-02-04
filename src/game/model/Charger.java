package game.model;

import game.Main;

public class Charger extends Chaser
{
	private double angle=0;
	private static double INIT_R=7;
	private static int HEALTHPARA=1;
	private static int damagePARA=10;
	private static int velocityPARA=30;
	private static double angularV=40;
	public Charger(Shooter shooter)
	{
		super(shooter);
		radius=INIT_R;
		velocity=velocityPARA;
		damage=damagePARA;
		life=HEALTHPARA;
		bindCircle(CircleFactory.getCharger());
	}
	public void move()
	{
		super.move();
		angle+=angularV/Main.FPS;
		circle.setRotate(angle);

	}
}
