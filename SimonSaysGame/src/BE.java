import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.util.Duration;

//Key Frames- Does things for a given time, given an event; For our purposes lights and dissables buttons for a certain amount of time
//Timelines- basically runs through the keyframes 

public class BE 
{
	private static double littime = .45;
	private static double betweentime = .5;

	public static void showSequence(SimonBuilder npc, ArrayList<Button> butts) 
	{
		npc.setToggler(true); //changes that boolean back in simonbuilder
		
		for (int i = 1; i <= npc.getPallet().size(); i++) 
		{
			Color current = npc.getPallet().get(i - 1);
			Timeline x;
			
			if (i == npc.getPallet().size()) 
			{
				x = new Timeline(new KeyFrame(Duration.seconds(i * betweentime), e -> 
				{
					for (Button button : butts) 
					{
						//this part enables and disables buttons making it look like a blink 
						if (button.getId().equals("b" + current.toString())) 
						{
							button.setDisable(false);
						}
						else 
						{
							button.setDisable(true);
						}
					}
				}), new KeyFrame(Duration.seconds((i * betweentime) + littime), e -> 
				{
					for (Button button : butts) 
					{
						button.setDisable(true);
					}
				}), new KeyFrame(Duration.seconds((i * betweentime) + (2 * littime)), e -> 
				{
					for (Button button : butts) 
					{
						button.setDisable(false);
					}
					npc.setToggler(false);
				}));
				x.play();
			} 
			else 
			{
				x = new Timeline(new KeyFrame(Duration.seconds(i * betweentime), e -> 
				{
					for (Button button : butts) 
					{
						if (button.getId().equals("b" + current.toString())) 
						{
							button.setDisable(false);
						}
						else 
						{
							button.setDisable(true);
						}
					}
				}), new KeyFrame(Duration.seconds((i * betweentime) + littime), e -> 
				{
					for (Button button : butts) 
					{
						button.setDisable(true);
					}
				}));
				x.play();
			}
		}
	}
	
	public static List<String> scoreWriter(SimonBuilder simone, String playername, Label shown) throws FileNotFoundException, IOException 
	{
		scoresheetmaker scoresheet = new scoresheetmaker(new File("simon.csv"));
		List<String> scores = new ArrayList<String>();
		StringBuilder lossscreen = new StringBuilder();

		scores.addAll(scoresheet.getData());
		scores.add(playername);
		scores.add(simone.getScore() + "");
		scoresheet.writer(scores);
		lossscreen.append("		YOU LOST \n  	      Final Score: " + simone.getScore() + " \n	       High Scores: \r\n");
		
		int i = 0;
		for (String entry : scores) 
		{
			lossscreen.append(entry);
			if (i % 2 == 0) 
			{
				lossscreen.append(" - ");
			}
			else 
			{
				lossscreen.append("\n");
			}
			i++;
		}
		shown.setText(lossscreen.toString());
		return scores;
	}
}