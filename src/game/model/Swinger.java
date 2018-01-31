package game.model;

import game.Main;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class Swinger extends FixedVelocityEnemy
{
	private static double INIT_R=10;
	private static int HEALTHPARA=30;
	private static int damagePARA=10;
	private static int velocityPARA=10;
	private double angle=0;
	private Orbit s1;
	private Orbit s2;
	private static double angularV=10;

	public Swinger()
	{
		super();
		radius=INIT_R;
		velocity=velocityPARA;
		damage=damagePARA;
		life=HEALTHPARA;
		setVelocity(super.setPos());
		bindCircle(CircleFactory.getSwinger());
		s1=new Orbit();
		s2=new Orbit();
		updateOrbit();
	}
	@Override
	public void move()
	{
		super.move();
		angle+=angularV/Main.FPS;
		updateOrbit();

	}
	private void updateOrbit()
	{
		if(s1!=null)
		{
			s1.p.setY(getY()-(getR()+s1.getR())*Math.sin(Math.PI*angle/180));
			s1.p.setX(getX()+(getR()+s1.getR())*Math.cos(Math.PI*angle/180));
		}
		if(s2!=null)
		{
			s2.p.setY(getY()-(getR()+s2.getR())*Math.sin(Math.PI*(angle+180)/180));
			s2.p.setX(getX()+(getR()+s2.getR())*Math.cos(Math.PI*(angle+180)/180));
		}
	}
	@Override
	public int getDamage()
	{
		int increment=0;
		if(s1!=null)
			increment+=s1.getDamage();
		if(s2!=null)
			increment+=s2.getDamage();
		System.out.println(damage+increment);
		return damage+increment;
	}
	@Override
	public boolean getShot(Bullet b)
	{
		if(s1!=null&&s1.getShot(b))
		{
			s1=null;
			return true;
		}
		if(s2!=null&&s2.getShot(b))
		{
			s2=null;
			return true;
		}
		double distance=b.getDistance(this);
		if(distance<getR())
		{
			if(s1!=null)
			{
				s1=null;
			}
			else if(s2!=null)
			{
				s2=null;
			}
			else
			{
				life-=b.getDamage();
			}
			return true;
		}
		return false;
	}
	public void render(ObservableList<Node> observableList)
	{
		if(s1!=null)
			observableList.add(s1.getCircle());
		if(s2!=null)
			observableList.add(s2.getCircle());
		observableList.add(getCircle());
	}

}
