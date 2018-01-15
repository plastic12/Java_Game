package game;
import javax.swing.*;
import sun.audio.*;
import java.awt.event.*;
import java.io.*;
public class Sound 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		FileInputStream f=new FileInputStream(new File("liquid.wav"));
		JFrame frame = new JFrame();
		frame.setSize(200,200);
		JButton button = new JButton("Click me");
		frame.add(button);
		button.addActionListener(new AL());
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static class AL implements ActionListener
	{
		public final void actionPerformed(ActionEvent e){
			music();
		}
	}

	public static void music(){
		AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		ContinuousAudioDataStream loop = null;
		try{
			BGM = new AudioStream(new FileInputStream("liquid.wav"));
			MD = BGM.getData();
			loop = new ContinuousAudioDataStream(MD);
		}catch(IOException error){
			System.out.print("file not found");
			System.out.println(error.getMessage());
		}
		MGP.start(loop);
	}
}
