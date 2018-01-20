package game.model;

import game.Main;
import game.util.MatrixHelper;
import game.view.GameController;
import javafx.scene.shape.Line;

public class Bullet 
{
	private Line render;
	private Point p1;
	private Point p2;
	private double[][] affineM;
	public static final double velocity=100;
	public static final double length=10;
	public Bullet()
	{}
	public Bullet(double x1,double y1,double x2,double y2)
	{
		p1=new Point(x1,y1);
		double distance=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
		p2=new Point(x1+(x2-x1)*length/distance,y1+(y2-y1)*length/distance);
		render=new Line();
		render.setLayoutX(0);
		render.setLayoutY(0);
		render.startXProperty().bind(p1.getXProperty());
		render.startYProperty().bind(p1.getYProperty());
		render.endXProperty().bind(p2.getXProperty());
		render.endYProperty().bind(p2.getYProperty());
		affineM=MatrixHelper.getAffineTransformation(x1, y1, x2, y2);
	}
	public Line getLine()
	{
		return render;
	}
	public void move()
	{
		double deltaX=(p2.getX()-p1.getX())/length*velocity/Main.FPS;
		double deltaY=(p2.getY()-p1.getY())/length*velocity/Main.FPS;
		p1.setX(p1.getX()+deltaX);
		p2.setX(p2.getX()+deltaX);
		p1.setY(p1.getY()+deltaY);
		p2.setY(p2.getY()+deltaY);
	}
	public boolean inBound()
	{
		return p1.getX()>0&&p1.getX()<GameController.XBOUND&&p1.getY()>0&&p1.getY()<GameController.YBOUND&&
				p2.getX()>0&&p2.getX()<GameController.XBOUND&&p2.getY()>0&&p2.getY()<GameController.YBOUND;
	}
}
