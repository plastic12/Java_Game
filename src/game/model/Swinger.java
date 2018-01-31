package game.model;

public class Swinger extends FixedVelocityEnemy
{
	private static double INIT_R=10;
	private static int HEALTHPARA=1;
	private static int damagePARA=10;
	private static int velocityPARA=10;
	public Swinger()
	{
		super();
		radius=INIT_R;
		velocity=velocityPARA;
		damage=damagePARA;
		life=HEALTHPARA;
		setVelocity(super.setPos());
		bindCircle(CircleFactory.getSwinger());
	}

}
