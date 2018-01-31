package game.model;

public class Split extends FixedVelocityEnemy
{
	private static double INIT_R=5;
	private static int HEALTHPARA=1;
	private static int damagePARA=5;
	private static int velocityPARA=10;
	public Split(double d,double e,int direction)
	{
		super(d,e);
		radius=INIT_R;
		velocity=velocityPARA;
		damage=damagePARA;
		life=HEALTHPARA;
		setVelocity(direction);
		bindCircle(CircleFactory.getRepliconeCircle());
	}
}
