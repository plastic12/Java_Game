package game.model;

public class Upgrade extends Entity
{
	private static final int radius=5;
	private int powerUp;
	private int progressUp;
	private int scoreUp;
	private int timeOutCounter;
	private final int timeOut=10;
	private Upgrade(int x,int y)
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
	public Upgrade powerUpgrade(int x,int y)
	{
		Upgrade u= new Upgrade(x,y);
		setPowerUp(1);
		setProgressUp(1);
		setScoreUp(10);
		//bind circle
		bindCircle(CircleFactory.powerUpgrade());
		return u;
	}
	public Upgrade progressUpgrade(int x,int y)
	{
		Upgrade u= new Upgrade(x,y);
		setPowerUp(0);
		setProgressUp((int)Math.random()*5);
		setScoreUp(10);
		//bind circle
		bindCircle(CircleFactory.scoreUpgrade());
		return u;
	}
	public Upgrade scoreUpgrade(int x,int y)
	{
		Upgrade u= new Upgrade(x,y);
		setPowerUp(0);
		setProgressUp(1);
		setScoreUp(50);
		//bind circle
		bindCircle(CircleFactory.scoreUpgrade());
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
}
