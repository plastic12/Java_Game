package game.view;

import java.io.IOException;

import game.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class GameOverController 
{
	@FXML
	private Label scoreLabel;
    @FXML
    public void menu(MouseEvent event) throws IOException 
    {
    		Main.menu();
    }
    public void init(int score)
    {
    		scoreLabel.setText(Integer.toString(score));
    }
    @FXML
    public void restart(MouseEvent event) throws IOException 
    {
    		Main.startGame();
    }
}
