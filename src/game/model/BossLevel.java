package game.model;

import javafx.scene.paint.Color;

//boss level not be implement in this assignment
public class BossLevel extends Level
{

	public BossLevel() {
		super();
		color=Color.BLACK;
		prompt="Boss Level";
		enemyRate= new int[] {0,0,0,0,0,0,0};
	}

}
