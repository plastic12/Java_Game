package game.view;

import java.io.*;
import java.net.URL;
import java.util.*;
import game.Main;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class LeaderboardController implements Initializable{

    @FXML
    private Label thirdScore;

    @FXML
    private Label firstScore;

    @FXML
    private Label menu;

    @FXML
    private Label secondScore;

    private static ArrayList<Integer> score = new ArrayList<Integer>();
		
    private static File scoreSource;
    
    @FXML
    void backMenu(MouseEvent event) throws IOException {
    	Main.menu();
    }

    public static void addScore(int a) {
    	score.add(a);
    	Collections.sort(score);
    	Collections.reverse(score);
    	
		try {
			PrintWriter writer = new PrintWriter(scoreSource);
			for (int i = 0; i < score.size(); i++) {			
				writer.println(score.get(i));
	    	}	     
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		    	     		    		
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub   
		
    	//read score in file
		try {
			//read previous score
			score.clear();
			URL location = LeaderboardController.class.getProtectionDomain().getCodeSource().getLocation();
			scoreSource = new File(location.getFile() + "score.txt");
			if (!scoreSource.exists())
				scoreSource.createNewFile();
			Scanner sc = new Scanner(scoreSource);		
			while (sc.hasNextLine() && sc.hasNextInt()) {
				score.add(sc.nextInt());
				sc.nextLine();
			}				
		
			Collections.sort(score);
	    	Collections.reverse(score);
			
			if (score.size()>=1) {
				firstScore.textProperty().bind(Bindings.concat("1. ").concat(score.get(0)));
				if (score.size()>=2) {
					secondScore.textProperty().bind(Bindings.concat("2. ").concat(score.get(1)));
					if (score.size() >=3)
						thirdScore.textProperty().bind(Bindings.concat("3. ").concat(score.get(2)));
				}					    
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
