package game.model;

//giga cell.Appeared in level 4
public class GigaCell extends Chaser
{
	private static double INIT_R=15;
	private static int HEALTHPARA=60;
	private static int damagePARA=10;
	private static int velocityPARA=7;
	public GigaCell(Shooter shooter)
	{
		super(shooter);
		radius=INIT_R;
		velocity=velocityPARA;
		damage=damagePARA;
		life=HEALTHPARA;
		bindCircle(CircleFactory.getGigaCell());
	}
}
