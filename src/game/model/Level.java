package game.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import game.Main;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Level 
{
	//instance variable
	protected Color color;
	protected String prompt;
	private int enemyCounter=0;
	private int collCheckCounter=0;
	private SimpleIntegerProperty progress;
	private LinkedList<Bullet> bullets;
	private LinkedList<Enemy> enemies;
	private LinkedList<Upgrade> upgrades;
	protected int[] enemyRate;
	//constant
	public static final int enemyfreq=1;
	public static final int collisionCheckfreq=50;
	public static final int powerUprate=20;
	public static final int scoreUprate=20;
	public static final int progressUprate=20;
	//level setup
	private static int[][] levelsetup= {
			{50,50,50,50,50,50,50},
			{30,60,60,60,60,60,60},
			{25,45,70,70,70,70,70},
			{20,30,55,80,80,80,80},
			{10,30,40,65,90,90,90},
			{10,20,40,50,60,100,100},
			{5,15,20,40,60,80,100}};
	private static String[] levelPrompt= {
			"Welcome!\n Here is the world of black and white",
			"level: 2",
			"level: 3",
			"level: 4",
			"level: 5",
			"level: 6",
			"level: 7"
	};
	private static Color[] levelColor= {
			Color.RED,
			Color.ORANGE,
			Color.YELLOW,
			Color.GREEN,
			Color.BLUE,
			Color.INDIGO,
			Color.web("#8B00FF")
	};
	//factory method generate level
	public static Level initLevel(int level)
	{
		if(level<7)
			return new Level(level);
		else if(level==7)
			return new BossLevel();
		else
			//other level add later
			return null;
	}
	//gen enemy according to dice and levelsetup
	public void addEnemy(Shooter shooter)
	{
		double dice=Math.random()*100;
		Enemy e=null;
		if(dice<enemyRate[0])
			e=new PC();
		else if(dice<enemyRate[1])
			e=new NC();
		else if(dice<enemyRate[2])
			e=new Replicone();
		else if(dice<enemyRate[3])
			e=new GigaCell(shooter);
		else if(dice<enemyRate[4])
			e=new Charger(shooter);
		else if(dice<enemyRate[5])
			e=new Swinger();
		else if(dice<enemyRate[6])
			e=new Ghost(shooter);
		if(e!=null)
		{
			enemies.add(e);
		}
	}
	//generate level according to levelsetup
	protected Level(int level)
	{
		this();
		color=levelColor[level];
		prompt=levelPrompt[level];
		enemyRate=levelsetup[level];
	}
	//base constructor
	protected Level()
	{
		progress=new SimpleIntegerProperty(0);
		bullets=new LinkedList<Bullet>();
		enemies=new LinkedList<Enemy>();
		upgrades=new LinkedList<Upgrade>();
	}
	//render bullet enemy upgrade
	public void render(ObservableList<Node> observableList)
	{
		for(Iterator<Bullet> itor=bullets.iterator();itor.hasNext();)
		{
			observableList.add(itor.next().getLine());
		}
		for(Iterator<Enemy> itor=enemies.iterator();itor.hasNext();)
		{
			Enemy e=itor.next();
			if(e instanceof Swinger)
				((Swinger)e).render(observableList);
			else
				observableList.add(e.getCircle());
		}
		for(Iterator<Upgrade> itor=upgrades.iterator();itor.hasNext(); )
		{
			observableList.add(itor.next().getCircle());
		}
	}
	//bind progress bar
	public void bindProgress(Rectangle progressBar)
	{
		progressBar.widthProperty().bind(progress);
		progressBar.setFill(color);
	}
	//gen appropriate upgrade according to dice
	public void genUpgrade(double x,double y)
	{
		double dice=100*Math.random();
		if(dice<progressUprate)
		{
			Upgrade u=Upgrade.progressUpgrade(x, y,color);
			upgrades.add(u);
		}
		else if(dice<progressUprate+scoreUprate)
		{
			Upgrade u=Upgrade.scoreUpgrade(x, y);
			upgrades.add(u);
		}
		else if(dice<progressUprate+scoreUprate+powerUprate)
		{
			Upgrade u=Upgrade.powerUpgrade(x, y);
			upgrades.add(u);
		}
	}
	
	public void collisionPhase(SimpleIntegerProperty score, Shooter shooter)
	{
		collCheckCounter++;
		if(collCheckCounter>=Main.FPS/collisionCheckfreq)
		{
			//bullet enemy check
			for(Iterator<Bullet> itor=bullets.iterator();itor.hasNext();)
			{
				Bullet b=itor.next();

				for(ListIterator<Enemy> itor2=enemies.listIterator();itor2.hasNext();)
				{
					Enemy e=itor2.next();
					if(e.getShot(b))
					{
						itor.remove();
						if(e.isDead())
						{
							e.dead();
							itor2.remove();
							if(e instanceof Replicone)
							{
								for(int i=0;i<4;i++)
									itor2.add(new Split(e.getX(),e.getY(),i));
							}
							if(!(e instanceof Split))
								genUpgrade(e.getX(),e.getY());
							//chance add upgrade
						}
					}
				}
			}
			//player enemy check
			for(Iterator<Enemy> itor2=enemies.iterator();itor2.hasNext();)
			{
				Enemy e=itor2.next();
				if(e.isCollide(shooter))
				{
					shooter.healthInc(-e.getDamage());
					e.setDead();
					e.dead();
					genUpgrade(e.getX(),e.getY());
					itor2.remove();
				}
			}
			//player upgrade check
			for(Iterator<Upgrade> itor=upgrades.iterator();itor.hasNext();)
			{
				Upgrade u=itor.next();
				if(u.isCollide(shooter))
				{
					shooter.powerInc(u.getPowerUp());
					score.set(score.get()+u.getScoreUp());
					progress.set(progress.get()+u.getProgressUp());
					itor.remove();
				}
			}
			collCheckCounter=0;
		}
	}
	//main loop for a level
	public boolean loop(SimpleIntegerProperty score, Shooter shooter)
	{
		genEnemy(shooter);
		genBullet(shooter);
		collisionPhase(score,shooter);
		movePhase(shooter);
		removeOutBound();
		upgradeTimeout();
		//return if levelup
		return(progress.get()>=100);
	}
	//upgrade disappear after a period of time
	public void upgradeTimeout()
	{
		for(Iterator<Upgrade> itor=upgrades.iterator();itor.hasNext();)
		{
			Upgrade u=itor.next();
			if(u.timeOut())
			{
				itor.remove();
			}
		}
	}
	//remove enemy and bullet out of bound
	public void removeOutBound()
	{
		//remove Bullet
		for(Iterator<Bullet> itor=bullets.iterator();itor.hasNext();)
		{
			Bullet b=itor.next();
			if(!b.inBound())
			{
				itor.remove();
			}
		}
		//remove Enemy
		for(Iterator<Enemy> itor=enemies.iterator();itor.hasNext();)
		{
			Enemy b=itor.next();
			if(!b.inBound())
			{
				itor.remove();
			}
		}
	}
	//move enemy bullet shooter
	public void movePhase(Shooter shooter)
	{
		//move enemy
		for(Iterator<Enemy> itor =enemies.iterator();itor.hasNext();)
		{
			Enemy b=itor.next();
			b.move();
		}
		//move bullet
		for(Iterator<Bullet> itor =bullets.iterator();itor.hasNext();)
		{
			Bullet b=itor.next();
			b.move();
		}
		//move player
		shooter.accelerate();
		shooter.move();
	}
	//generate enemy according to counter
	public void genEnemy(Shooter shooter)
	{
		enemyCounter++;
		if(enemyCounter>=Main.FPS/enemyfreq)
		{
			addEnemy(shooter);
			enemyCounter=0;
		}
	}
	//generate bullet
	public void genBullet(Shooter shooter)
	{
		Bullet b=shooter.shoot();
		if(b!=null)
		{
			bullets.add(b);
		}
	}
	public String getPrompt() {return prompt;}
}
