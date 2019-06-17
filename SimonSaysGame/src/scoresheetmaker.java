import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class scoresheetmaker 
{
	private File sheet;
	private ArrayList<String> input = new ArrayList<String>();
	private int columns;
	private String heading = null;
	
	public scoresheetmaker(File sheet) throws FileNotFoundException, IOException
	{
		String line;
		this.sheet = sheet;
		
		try (BufferedReader r = new BufferedReader(new FileReader(sheet))) 
		{
			while ((line = r.readLine()) != null) 
			{
				this.input.add(line);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		if (!input.isEmpty()) 
		{
			heading = input.get(0);
			input.remove(0);
		}
		
	}

	//gets prev scores
	public List<String> getData() 
	{
		ArrayList<String> old = new ArrayList<String>();
		
		for (String line : input) 
		{
			String[] split = line.replaceAll("^\"", "").split(",");
			
			for (String word : split)
			{
				old.add(word);
			}
		}
		return old;
	}
	
	//adds new scores
	public void writer(List<String> wordz) 
	{
		PrintWriter pewpew = null;
		int counter = 0;
		
		try 
		{
			pewpew = new PrintWriter(sheet);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		StringBuilder x = new StringBuilder();
		x.append(heading + "\r\n");

		for (String string : wordz) 
		{
			x.append(string);
			counter++;
			
			if (counter >= columns) 
			{
				x.append("\r\n");
				counter = 0;
			} 
		}
		pewpew.write(x.toString());
		pewpew.close();
	}
}