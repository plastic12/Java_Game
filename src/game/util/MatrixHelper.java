package game.util;

public final class MatrixHelper 
{
	public static double[][] getAffineTransformation(double x1,double y1,double x2,double y2)
	{
		double distance=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
		double cosine=(x2-x1)/distance;
		double sine=(y2-y1)/distance;
		
		double affineM[][]=new double[3][3];
		affineM[0][0]=cosine;
		affineM[0][1]=sine;
		affineM[0][2]=-x1*cosine-y1*sine;
		affineM[1][0]=-sine;
		affineM[1][1]=cosine;
		affineM[1][2]=x1*sine-y1*cosine;
		affineM[2][0]=0;
		affineM[2][1]=0;
		affineM[2][2]=1;
		return affineM;
	}
}
