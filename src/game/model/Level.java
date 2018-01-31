package game.model;

import java.util.Iterator;
import java.util.LinkedList;

import game.Main;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Level 
{
	protected Color color;
	protected String prompt;
	private int enemyCounter=0;
	private int collCheckCounter=0;
	private SimpleIntegerProperty progress;
	private LinkedList<Bullet> bullets;
	private LinkedList<Enemy> enemies;
	private LinkedList<Upgrade> upgrades;
	protected int[] enemyRate;
	public static final int LEVELCOUNT=8;
	public static final int enemyfreq=1;
	public static final int collisionCheckfreq=50;
	public static final int powerUprate=20;
	public static final int scoreUprate=20;
	public static final int progressUprate=20;
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
	public static Level initLevel(int level)
	{
		if(level<7)
			return new Level(level);
		else if(level==7)
			return new BossLevel();
		else
			//TODO
			return null;
	}
	public void setupEnemy(int level)
	{

	}
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
	protected Level(int level)
	{
		this();
		color=levelColor[level];
		prompt=levelPrompt[level];
		enemyRate=levelsetup[level];
	}
	protected Level()
	{
		progress=new SimpleIntegerProperty(0);
		bullets=new LinkedList<Bullet>();
		enemies=new LinkedList<Enemy>();
		upgrades=new LinkedList<Upgrade>();
	}
	public void render(ObservableList<Node> observableList)
	{
		for(Iterator<Bullet> itor=bullets.iterator();itor.hasNext();)
		{
			observableList.add(itor.next().getLine());
		}
		//remove Enemy
		for(Iterator<Enemy> itor=enemies.iterator();itor.hasNext();)
		{
			observableList.add(itor.next().getCircle());
		}
		for(Iterator<Upgrade> itor=upgrades.iterator();itor.hasNext(); )
		{
			observableList.add(itor.next().getCircle());
		}
	}
	public void bindProgress(Rectangle progressBar)
	{
		progressBar.widthProperty().bind(progress);
		progressBar.setFill(color);
	}
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

				for(Iterator<Enemy> itor2=enemies.iterator();itor2.hasNext();)
				{
					Enemy e=itor2.next();
					if(e.getShot(b))
					{
						itor.remove();
						if(e.isDead())
						{
							e.dead();
							genUpgrade(e.getX(),e.getY());
							//chance add upgrade
							itor2.remove();
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
	public boolean loop(SimpleIntegerProperty score, Shooter shooter)
	{
		genEnemy(shooter);
		genBullet(shooter);
		collisionPhase(score,shooter);
		movePhase(shooter);
		//removeOutBound(observableList);
		upgradeTimeout();
		return(progress.get()>=100);
	}
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
	public void removeOutBound(ObservableList<Node> observableList)
	{
		//remove Bullet
		for(Iterator<Bullet> itor=bullets.iterator();itor.hasNext();)
		{
			Bullet b=itor.next();
			if(!b.inBound())
			{
				observableList.remove(b.getLine());
				itor.remove();
				//System.out.println("removed");
			}
		}
		//remove Enemy
		for(Iterator<Enemy> itor=enemies.iterator();itor.hasNext();)
		{
			Enemy b=itor.next();
			if(!b.inBound())
			{
				observableList.remove(b.getCircle());
				itor.remove();
			}
		}
	}
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
	public void genEnemy(Shooter shooter)
	{
		enemyCounter++;
		if(enemyCounter>=Main.FPS/enemyfreq)
		{
			addEnemy(shooter);
			enemyCounter=0;
		}
	}
	public void genBullet(Shooter shooter)
	{
		Bullet b=shooter.shoot();
		if(b!=null)
		{
			bullets.add(b);
		}
	}
	public void cleanUp(ObservableList<Node> observableList)
	{
		for(Iterator<Bullet> itor=bullets.iterator();itor.hasNext();)
		{
			Bullet b=itor.next();
			observableList.remove(b.getLine());
			itor.remove();
		}
		//remove Enemy
		for(Iterator<Enemy> itor=enemies.iterator();itor.hasNext();)
		{
			Enemy b=itor.next();
			observableList.remove(b.getCircle());
			itor.remove();
		}
		for(Iterator<Upgrade> itor=upgrades.iterator();itor.hasNext(); )
		{
			Upgrade u=itor.next();
			observableList.remove(u.getCircle());
			itor.remove();
		}
	}
	public String getPrompt() {return prompt;}
}
