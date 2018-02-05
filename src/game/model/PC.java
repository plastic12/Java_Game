package game.model;

import javafx.scene.shape.Circle;

//PC is enemy appear in level 1
public class PC extends FixedVelocityEnemy
{
	private static double INIT_R=10;
	private static int HEALTHPARA=1;
	private static int damagePARA=10;
	private static int velocityPARA=10;
	public PC()
	{
		super();
		radius=INIT_R;
		velocity=velocityPARA;
		damage=damagePARA;
		life=HEALTHPARA;
		setVelocity(super.setPos());
		bindCircle(CircleFactory.getPCCircle());
	}
}
