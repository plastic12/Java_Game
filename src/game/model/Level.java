package game.model;

import java.util.Iterator;
import java.util.LinkedList;

import game.Main;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Level 
{
	private Color color;
	private String prompt;
	private int enemyCounter=0;
	private int collCheckCounter=0;
	private SimpleIntegerProperty progress;
	private LinkedList<Bullet> bullets;
	private LinkedList<Enemy> enemies;
	public static final int LEVELCOUNT=8;
	public static final int enemyfreq=1;
	public static final int collisionCheckfreq=50;
	public static Level initLevel(int level)
	{
		switch(level)
		{
		case 1:
			return new Level("Welcome!\n Here is the world of black and white",Color.RED);
		case 2:
			return new Level("level: 2",Color.ORANGE); 
		case 3:
			return new Level("level: 3",Color.YELLOW);
		case 4:
			return new Level("level: 4",Color.GREEN);
		case 5:
			return new Level("level: 5",Color.BLUE);
		case 6:
			return new Level("level: 6",Color.INDIGO);
		case 7:
			return new Level("level: 7",Color.web("#8B00FF"));
		case 8:
			return new BossLevel();
		default:
			System.out.println("The invalid level number");
			return null;
		}
	}
	public void addEnemy(ObservableList<Node> observableList)
	{
		Enemy e=new Enemy();
		enemies.add(e);
		observableList.add(e.getCircle());
	}
	protected Level(String prompt,Color color)
	{
		this.color=color;
		progress=new SimpleIntegerProperty(0);
		bullets=new LinkedList<Bullet>();
		enemies=new LinkedList<Enemy>();
		this.prompt=prompt;
	}
	public void bindProgress(Rectangle progressBar)
	{
		progressBar.widthProperty().bind(progress);
		progressBar.setFill(color);
	}
	public void collisionPhase(ObservableList<Node> observableList, SimpleIntegerProperty score, Shooter shooter)
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
					double distance=b.getDistance(e);

					if(distance<e.getR())
					{
						score.set(score.get()+e.getScore());
						itor.remove();
						itor2.remove();
						progress.set(progress.get()+20);
						observableList.remove(b.getLine());
						observableList.remove(e.getCircle());
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
					score.set(score.get()+e.getScore());
					itor2.remove();
					observableList.remove(e.getCircle());
				}
			}
			collCheckCounter=0;
		}
	}
	public boolean loop(ObservableList<Node> observableList, SimpleIntegerProperty score, Shooter shooter)
	{
		genEnemy(observableList);
		genBullet(observableList,shooter);
		collisionPhase(observableList,score,shooter);
		movePhase(shooter);
		removeOutBound(observableList);
		return(progress.get()==100);
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
	public void genEnemy(ObservableList<Node> observableList)
	{
		enemyCounter++;
		if(enemyCounter>=Main.FPS/enemyfreq)
		{
			addEnemy(observableList);
			enemyCounter=0;
		}
	}
	public void genBullet(ObservableList<Node> observableList,Shooter shooter)
	{
		Bullet b=shooter.shoot();
		if(b!=null)
		{
			bullets.add(b);
			observableList.add(b.getLine());
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
	}
	public String getPrompt() {return prompt;}
}
