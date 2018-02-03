package game.view;

import java.io.IOException;

import game.Main;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;

public class OptionController 
{
	@FXML
	CheckBox music;
	@FXML
	CheckBox dialog;
	@FXML
    void backMenu(MouseEvent event) throws IOException {
    	Main.menu();
    }
	
}
