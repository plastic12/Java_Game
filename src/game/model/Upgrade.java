package game.model;

import game.Main;
import javafx.scene.paint.Color;

public class Upgrade extends Entity
{
	private static final int radius=5;
	private int powerUp;
	private int progressUp;
	private int scoreUp;
	private int timeOutCounter;
	private final int timeOut=10;
	//testing constant
	public static final int progressMul=50;
	private Upgrade(double x,double y)
	{
		super(x,y,radius);
		timeOutCounter=0;
	}
	public int getPowerUp() {
		return powerUp;
	}
	public int getProgressUp() {
		return progressUp;
	}
	public int getScoreUp() {
		return scoreUp;
	}
	public static Upgrade powerUpgrade(double x,double y)
	{
		Upgrade u= new Upgrade(x,y);
		u.setPowerUp(1);
		u.setProgressUp(1);
		u.setScoreUp(10);
		//bind circle
		u.bindCircle(CircleFactory.powerUpgrade());
		return u;
	}
	public static Upgrade progressUpgrade(double x,double y,Color color)
	{
		Upgrade u= new Upgrade(x,y);
		u.setPowerUp(0);
		u.setProgressUp((int)(Math.random()*progressMul));
		u.setScoreUp(10);
		//bind circle
		u.bindCircle(CircleFactory.progressUpgrade(color));
		return u;
	}
	public static Upgrade scoreUpgrade(double x,double y)
	{
		Upgrade u= new Upgrade(x,y);
		u.setPowerUp(0);
		u.setProgressUp(1);
		u.setScoreUp(50);
		//bind circle
		u.bindCircle(CircleFactory.scoreUpgrade());
		return u;
	}
	private void setPowerUp(int powerUp) {
		this.powerUp = powerUp;
	}
	public void setProgressUp(int progressUp) {
		this.progressUp = progressUp;
	}
	private void setScoreUp(int scoreUp) {
		this.scoreUp = scoreUp;
	}
	public boolean timeOut() {timeOutCounter++;return(timeOutCounter>=timeOut*Main.FPS);}
}
