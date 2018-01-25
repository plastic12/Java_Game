package game.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class LeaderboardController {

    @FXML
    private Label thirdScore;

    @FXML
    private Label firstScore;

    @FXML
    private Label menu;

    @FXML
    private Label secondScore;

    @FXML
    void backMenu(MouseEvent event) throws IOException {
    	Main.menu();
    }

    void init() {
    	//read file
    	//sort
    	//change label
    }
}
