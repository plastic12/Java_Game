package game.model;

public class Charger extends Chaser
{
	private static double INIT_R=7;
	private static int HEALTHPARA=1;
	private static int damagePARA=10;
	private static int velocityPARA=30;
	public Charger(Shooter shooter)
	{
		super(shooter);
		radius=INIT_R;
		velocity=velocityPARA;
		damage=damagePARA;
		life=HEALTHPARA;
		bindCircle(CircleFactory.getCharger());
	}
}
