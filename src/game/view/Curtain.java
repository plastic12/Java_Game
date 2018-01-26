package game.view;

import java.io.IOException;

import game.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Curtain 
{
	private Rectangle curtain;
	private Label dialog;
	private GameController controller;
	public static final int COUNT=300;
	public Curtain(GameController controller)
	{
		this.controller=controller;
		curtain=new Rectangle();
		curtain.setTranslateX(0);
		curtain.setTranslateY(0);
		curtain.setLayoutX(0);
		curtain.setLayoutY(0);
		dialog=new Label();
		dialog.setLayoutX(0);
		dialog.setLayoutY(0);
		dialog.setTextFill(Color.WHITE);
	}
	public void bind(Pane scene)
	{
		curtain.widthProperty().bind(scene.widthProperty());
		curtain.heightProperty().bind(scene.heightProperty());
	}
	public void setText(String text)
	{
		dialog.setText(text);
	}
	private void close(Pane scene)
	{
		scene.getChildren().add(curtain);
		scene.getChildren().add(dialog);
	}
	private void open(Pane scene)
	{
		scene.getChildren().remove(dialog);
		scene.getChildren().remove(curtain);
	}
	public void start(Pane scene)
	{
		close(scene);
		CurtainHandler c=new CurtainHandler();
		Timeline temp = new Timeline(
				new KeyFrame(Duration.millis(1000/Main.FPS), c));
		c.setPane(scene);
		c.setTimeline(temp);
		temp.setCycleCount(Timeline.INDEFINITE);
		temp.play();
	}
	public class CurtainHandler implements EventHandler<ActionEvent>
	{
		private int counter=0;
		private Pane scene;
		private Timeline t;
		@Override
		public void handle(ActionEvent event) {
			counter++;
			if(counter>COUNT)
			{
				open(scene);
				controller.globalPlay();
				t.pause();
			}
		}
		public void setPane(Pane scene) {this.scene=scene;}
		public void setTimeline(Timeline t) {this.t=t;}
	}
}
