package game.view;

import java.io.IOException;

import game.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    public void startGame(MouseEvent event) throws IOException {
    	Main.startGame();
    }
    
}
