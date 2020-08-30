import javax.swing.JFrame;

@SuppressWarnings("serial")
public class BatChase extends JFrame
{	
	public static void main(String[] args) 
	{
		JFrame app = new JFrame();
		Cmech c = new Cmech();	
		
		app.add(c);
		
		//Default work
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(500, 720);
		app.setVisible(true);
		app.setResizable(false);
		app.setTitle("BatChase");	
	}	
}
