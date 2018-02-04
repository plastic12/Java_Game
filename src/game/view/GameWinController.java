package game.view;

import java.io.IOException;

import game.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

public class GameWinController {

	@FXML
	private Label scoreLabel;
	@FXML
	private Label noteLabel;
    @FXML
    public void menu(MouseEvent event) throws IOException 
    {
    		Main.menu();
    }
    public void init(int score)
    {
		if(!Main.win)
		{
			Main.win=true;
			Main.writeConfig();
			noteLabel.setVisible(true);
		}
    		scoreLabel.setText(Integer.toString(score));
    }
    @FXML
    public void restart(MouseEvent event) throws IOException 
    {
    		Main.startGame();
    }
	
    @FXML
    void mouseIn(MouseEvent e) throws IOException {
    	Label temp = (Label) e.getSource();
    	temp.setEffect(new DropShadow());
    }
    
    @FXML
    void mouseOut(MouseEvent e) throws IOException {
    	Label temp = (Label) e.getSource();
    	temp.setEffect(null);
    }


}
