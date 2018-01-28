package game.model;

import javafx.scene.paint.Color;

public class BossLevel extends Level
{

	public BossLevel() {
		super();
		color=Color.BLACK;
		prompt="Boss Level";
		enemyRate= new int[] {0,0,0,0,0,0,0};
	}

}
