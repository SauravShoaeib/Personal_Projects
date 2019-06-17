import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.paint.Color;

public class SimonBuilder 
{
	private int score;
	private boolean toggler;
	private Color[] palleta = { Color.HOTPINK, Color.BLUE, Color.DARKGREEN, Color.YELLOW };
	private ArrayList<Color> pallet = new ArrayList<Color>();
	private Random r = new Random();
	@SuppressWarnings("rawtypes")
	private Iterator cycles; // runs through the list the colors

	public SimonBuilder() 
	{
		this.score = 0;
		this.toggler = false;
		this.colorAdder();
	}

	public int getScore() 
	{
		return score;
	}

	public boolean isToggler() 
	{
		return toggler;
	}

	public ArrayList<Color> getPallet() 
	{
		return pallet;
	}

	public void setToggler(boolean toggler) 
	{
		this.toggler = toggler;
	}

	public Color colorAdder() 
	{
		Color color = palleta[r.nextInt(palleta.length)];
		pallet.add(color);
		return color;
	}
	
	//note to self: boolean doesn't work cause gets stuck after first pattern
	public int newMove(Color lit) 
	{
		if (!toggler) 
		{
			if (cycles == null) 
			{
				cycles = pallet.iterator(); 
			}
			if (cycles.hasNext()) 
			{
				if (cycles.next().equals(lit)) 
				{
					if (!cycles.hasNext()) 
					{
						score++;
						this.colorAdder(); 
						cycles = pallet.iterator(); 
						return 2; 
					}
				} 
				else 
				{
					return 0; 
				}
			}
		}
		return 69; 
	}
}