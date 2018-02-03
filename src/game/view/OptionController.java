package game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import game.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;

public class OptionController implements Initializable
{
	@FXML
	CheckBox music;
	@FXML
	CheckBox dialog;
	@FXML
	CheckBox color;
	@FXML
    void backMenu(MouseEvent event) throws IOException 
	{
		Main.music=music.isSelected();
		Main.curtain=dialog.isSelected();
		Main.color=color.isSelected();
		Main.writeConfig();
    		Main.menu();
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		music.setSelected(Main.music);
		dialog.setSelected(Main.curtain);
		if(Main.win)
		{
			color.setSelected(Main.color);
		}
		else
		{
			color.setVisible(false);
			Main.color=false;
		}
	}
	
	
}
