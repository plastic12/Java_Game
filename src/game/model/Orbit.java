package game.model;

public class Orbit extends Enemy 
{
	private static double INIT_R=5;
	private static int HEALTHPARA=1;
	private static int damagePARA=5;
	private static int velocityPARA=10;
	public Orbit()
	{
		super();
		radius=INIT_R;
		velocity=velocityPARA;
		damage=damagePARA;
		life=HEALTHPARA;
		bindCircle(CircleFactory.getSwinger());
	}
}
