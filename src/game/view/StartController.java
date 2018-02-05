package game.view;

import java.io.IOException;

import game.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class StartController {

    @FXML
    private Label Leaderboard;

    @FXML
    private Label startBtn;

    @FXML
    private Label GameName;

    @FXML
    private Pane StartPane;

    @FXML
    //start game
    public void startGame(MouseEvent event) throws IOException {
    	Main.startGame();
    }
    
   @FXML
   //leaderboard
    public void goLeaderboard(MouseEvent e) throws IOException {
    	Main.viewLeaderboard();
    }
   @FXML
   //option panel
   public void goOption(MouseEvent e)throws IOException{
	   Main.option();
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
    @FXML
    //exit button
    public void exitBtn(MouseEvent e)
    {
    		Platform.exit();
    }
}
