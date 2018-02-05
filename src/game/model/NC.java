package game.model;

//NC is enemy appear in level 2
public class NC extends FixedVelocityEnemy
{
	private static double INIT_R=7;
	private static int HEALTHPARA=20;
	private static int damagePARA=10;
	private static int velocityPARA=10;
	public NC()
	{
		super();
		radius=INIT_R;
		velocity=velocityPARA;
		damage=damagePARA;
		life=HEALTHPARA;
		setVelocity(super.setPos());
		bindCircle(CircleFactory.getNCCircle());
	}
}
