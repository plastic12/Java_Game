package game.view;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class PauseController 
{
	@FXML 
	private Label pauseLabel;
	@FXML
	private Rectangle layer;
	
	public void bindGamePane(AnchorPane gamePane)
	{
		layer.widthProperty().bind(gamePane.widthProperty());
		layer.heightProperty().bind(gamePane.heightProperty());
		pauseLabel.translateXProperty().bind((gamePane.widthProperty().add(pauseLabel.widthProperty().negate())).divide(2));
		System.out.println(pauseLabel.getTranslateX());
		pauseLabel.translateYProperty().bind((gamePane.heightProperty().add(pauseLabel.heightProperty().negate())).divide(2));
	}
}
