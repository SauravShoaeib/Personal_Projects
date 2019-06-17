import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class runningman extends Application  
{	
	Stage current;
	Scene s1,s2;
    private static String name;

    public static void main(String[] args) 
    {
        Application.launch(args);
    }
    
	@Override
    public void start(Stage primaryStage) throws Exception 
	{
		current = primaryStage;
    	    	
		primaryStage.setTitle("Saurav (And Daniel) Says");
		SimonBuilder npc = new SimonBuilder();

		// adding background glowwy shit
		DropShadow first = new DropShadow();
		first.setColor(Color.HOTPINK);
		first.setWidth(50);
		first.setHeight(50);

		DropShadow second = new DropShadow();
		second.setColor(Color.BLUE);
		second.setWidth(50);
		second.setHeight(50);

		DropShadow third = new DropShadow();
		third.setColor(Color.DARKGREEN);
		third.setWidth(50);
		third.setHeight(50);
		
		DropShadow fourth = new DropShadow();
		fourth.setColor(Color.YELLOW);
		fourth.setWidth(50);
		fourth.setHeight(50);
		
		//making butts and setting cute stuff
		Button one = new Button("Pink");
		one.setId("b" + Color.HOTPINK.toString()); // sets id
		one.setEffect(first); //sets surrounding glow
		one.setDisable(true); //enables and disables
		one.setStyle("-fx-background-color: linear-gradient(#ff69b4, #ffb6c1);\r\n" + 
				"    -fx-background-radius: 50;\r\n" + 
				"    -fx-text-fill: white;" +
				"    -fx-padding: 5;"); // makes it super pweeeetyyyyy C:
		
		Button two = new Button("Blue");
		two.setId("b" + Color.BLUE.toString());
		two.setEffect(second);
		two.setDisable(true);
		two.setStyle("-fx-background-color: linear-gradient(#0000FF, #00BFFF);\r\n" + 
				"    -fx-background-radius: 50;\r\n" + 
				"    -fx-text-fill: white;" +
				"    -fx-padding: 5;");
		
		Button three = new Button("Green");
		three.setId("b" + Color.DARKGREEN.toString());
		three.setEffect(third);
		three.setDisable(true);
		three.setStyle("-fx-background-color: linear-gradient(#006400, #7FFF00);\r\n" + 
				"    -fx-background-radius: 50;\r\n" + 
				"    -fx-text-fill: white;" +
				"    -fx-padding: 5;");
		
		Button four = new Button("Yellow");
		four.setId("b" + Color.YELLOW.toString());
		four.setEffect(fourth);
		four.setDisable(true);
		four.setStyle("-fx-background-color: linear-gradient(#FFD700, #FFFF00);\r\n" + 
				"    -fx-background-radius: 50;\r\n" + 
				"    -fx-text-fill: white;" +
				"    -fx-padding: 5;");
		
		Button start = new Button("START");
		start.setStyle("-fx-background-color: linear-gradient(#000000, #FFC0CB);\r\n"+
				"-fx-background-insets: 0,1,4,5,6;" +
				"-fx-background-radius: 9,8,5,4,3;" +
				"-fx-padding: 15 30 15 30;" +
				"-fx-font-size: 18px;" +
				"-fx-font-weight: bold;" +
				"-fx-text-fill: white;"); 
				
		ArrayList<Button> buttz = new ArrayList<Button>();
		buttz.add(one);
		buttz.add(two);
		buttz.add(three);
		buttz.add(four);
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//First Screen Name
		VBox op = new VBox(100);
		
// Second Screen Game		
		VBox map = new VBox(100);
		
		VBox toppy = new VBox(100);
		HBox fourbutts = new HBox(100);
		VBox twobutts = new VBox(100);
		VBox butttwos = new VBox(100);
		
		fourbutts.setPrefHeight(100);
		butttwos.setPrefWidth(100);
		twobutts.setPrefWidth(100);
		butttwos.setPrefHeight(100);
		
		one.setMaxHeight(0);
		two.setMaxHeight(Double.MAX_VALUE);
		three.setMaxHeight(Double.MAX_VALUE);
		four.setMaxHeight(Double.MAX_VALUE);
		one.setMaxWidth(Double.MAX_VALUE);
		two.setMaxWidth(Double.MAX_VALUE);
		three.setMaxWidth(Double.MAX_VALUE);
		four.setMaxWidth(Double.MAX_VALUE);
		
		op.getChildren().add(start);
		toppy.getChildren().add(start);
		toppy.setAlignment(Pos.CENTER);
		twobutts.setAlignment(Pos.CENTER);
		twobutts.getChildren().add(one);
		twobutts.getChildren().add(two);
		butttwos.setAlignment(Pos.CENTER);
		butttwos.getChildren().add(three);
		butttwos.getChildren().add(four);
		fourbutts.getChildren().add(twobutts);
		fourbutts.getChildren().add(butttwos);
		fourbutts.setAlignment(Pos.CENTER);
		
		Label scoreview = new Label();
		Label nf = new Label ("Name: ");
		HBox namefield = new HBox();
		TextField n = new TextField();
		namefield.getChildren().addAll(n);
		
		Button c = new Button("Let's Play");
		Button gg = new Button("Quit");
		
		c.setOnAction(e->
		{
			name = (n.getText());
			current.setScene(s2);
		});
		
		gg.setOnAction(e -> 
		{
			System.out.println("See ya quitter");
			System.exit(0);
		});
		
		start.setOnAction(new EventHandler<ActionEvent>() 
		{
			
			@Override
			public void handle(ActionEvent event) 
			{
				start.setVisible(false);
				BE.showSequence(npc, buttz);
				one.setOnAction(new EventHandler<ActionEvent>() 
				{
					@Override
					public void handle(ActionEvent event) 
					{
						int state = npc.newMove(Color.HOTPINK);
						if (state == 0) 
						{
							one.setVisible(false);
							two.setVisible(false);
							three.setVisible(false);
							four.setVisible(false);

							try 
							{
								BE.scoreWriter(npc, name, scoreview);
							}
							catch (FileNotFoundException e) 
							{
								e.printStackTrace();
							} 
							catch (IOException e) 
							{
								e.printStackTrace();
							}
							for (Button button : buttz) 
							{
								button.setDisable(true);
							}
						}
						if (state == 2) 
						{
							scoreview.setText(getran() + "\n\n\n\nScore: " + npc.getScore());

							for (Button button : buttz) 
							{
								button.setDisable(true);
							}
							BE.showSequence(npc, buttz);
						}
					}
				});
				two.setOnAction(new EventHandler<ActionEvent>() 
				{
					@Override
					public void handle(ActionEvent event) 
					{
						int state = npc.newMove(Color.BLUE);
						if (state == 0) 
						{
							one.setVisible(false);
							two.setVisible(false);
							three.setVisible(false);
							four.setVisible(false);

							try 
							{
								BE.scoreWriter(npc, name, scoreview);

							} 
							catch (FileNotFoundException e) 
							{
								e.printStackTrace();
							} 
							catch (IOException e) 
							{
								e.printStackTrace();
							}
							for (Button button : buttz) 
							{
								button.setDisable(true);
							}
						}
						if (state == 2) 
						{
							scoreview.setText(getran() + "\n\n\n\nScore: " + npc.getScore());

							for (Button button : buttz) 
							{
								button.setDisable(true);
							}
							BE.showSequence(npc, buttz);
						}
					}
				});
				three.setOnAction(new EventHandler<ActionEvent>() 
				{
					@Override
					public void handle(ActionEvent event) 
					{
						int state = npc.newMove(Color.DARKGREEN);
						if (state == 0) 
						{
							one.setVisible(false);
							two.setVisible(false);
							three.setVisible(false);
							four.setVisible(false);

							try 
							{
								BE.scoreWriter(npc, name, scoreview);

							} 
							catch (FileNotFoundException e) 
							{
								e.printStackTrace();
							} catch (IOException e) 
							{
								e.printStackTrace();
							}
							for (Button button : buttz) 
							{
								button.setDisable(true);
							}
						}
						if (state == 2) 
						{
							scoreview.setText(getran() + "\n\n\n\nScore: " + npc.getScore());

							for (Button button : buttz) 
							{
								button.setDisable(true);
							}
							BE.showSequence(npc, buttz);
						}
					}
				});
				four.setOnAction(new EventHandler<ActionEvent>() 
				{
					@Override
					public void handle(ActionEvent event) 
					{
						int state = npc.newMove(Color.YELLOW);
						if (state == 0) 
						{
							one.setVisible(false);
							two.setVisible(false);
							three.setVisible(false);
							four.setVisible(false);

							try 
							{
								BE.scoreWriter(npc, name, scoreview);

							} 
							catch (FileNotFoundException e) 
							{
								e.printStackTrace();
							} 
							catch (IOException e) 
							{
								e.printStackTrace();
							}
							for (Button button : buttz) 
							{
								button.setDisable(true);
							}
						}
						if (state == 2) 
						{
							scoreview.setText(getran() + "\n\n\n\nScore: " + npc.getScore());

							for (Button button : buttz) 
							{
								button.setDisable(true);
							}
							BE.showSequence(npc, buttz);
						}
					}
				});
			}
		});
		
		op.getChildren().addAll(nf,n,c,gg);
		op.setAlignment(Pos.CENTER);
		
		map.getChildren().add(scoreview);
		map.getChildren().add(start);
		map.getChildren().add(fourbutts);
		map.setAlignment(Pos.CENTER);
		
		//setting scenes
		s1 = new Scene(op,800,500);
        s2 = new Scene(map, 800, 500);
        
        //css shit
        s1.getStylesheets().add("homepg.css"); // CSS
        s2.getStylesheets().add("background.css"); // CSS

        //ooo cool icon
        Image applicationIcon = new Image(("images.jpg")); // ICON
        
 
        current.getIcons().add(applicationIcon);
        current.setScene(s1);
        current.show();
        current.setHeight(600);
        current.setWidth(600);
    }
	
	public static String getran() 
	{
		Random r = new Random();
		return rando[r.nextInt(rando.length)];
	}
	
	public static String [] rando  = {"You got this!", "Great memory!","Wow, you're smart!", "You can do it!", "Great job!"};
}