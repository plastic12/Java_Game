package game.model;

public class Split extends FixedVelocityEnemy
{
	private static double INIT_R=5;
	private static int HEALTHPARA=1;
	private static int damagePARA=5;
	private static int velocityPARA=10;
	public Split()
	{
		super();
		radius=INIT_R;
		velocity=velocityPARA;
		damage=damagePARA;
		life=HEALTHPARA;
		setVelocity(super.setPos());
		bindCircle(CircleFactory.getRepliconeCircle());
	}	

}
