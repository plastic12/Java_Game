package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;


public class Circle extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			Circle circle = new Circle(300, 300, 300);
			circle.setStrokeType(StrokeType.OUTSIDE);
			circle.setStroke(Color.BLACK);
			circle.setFill(Color.WHITE);
			
			Arc arc1 = new Arc(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius(), 0.0f, 90.0f);
			arc1.setFill(Color.BLACK);
			arc1.setType(ArcType.ROUND);
			
			
			Arc arc2 = new Arc(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius(), 180.0f, 90.0f);
			arc2.setFill(Color.BLACK);
			arc2.setType(ArcType.ROUND);
			
			root.getChildren().addAll(circle, arc1, arc2);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
