package game.model;

public class Level 
{
	private String prompt;
	public static final int LEVELCOUNT=8;
	public static Level[] level=new Level[LEVELCOUNT];
	static
	{
		level[0]=new Level("Welcome!\n Here is the world of black and white");
		level[1]=new Level("level: 2");
		level[2]=new Level("level: 3");
		level[3]=new Level("level: 4");
		level[4]=new Level("level: 5");
		level[5]=new Level("level: 6");
		level[6]=new Level("level: 7");
		level[7]=new BossLevel();
	}
	public Level(String prompt)
	{
		this.prompt=prompt;
	}
	public String getPrompt() {return prompt;}
}
