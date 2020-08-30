import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Cmech extends JPanel implements ActionListener, KeyListener
{
	private int WIDTHB = 500, HEIGHTB = 700, space, cwidth = 55, cheight = 75, move = 20, count = 1;
	
	private double speed;
	
	//batmobile
	private Rectangle car;
	
	//arraylist of opponent cars
	private ArrayList <Rectangle> opcars;
	
	//misc
	private Random rand;
	Timer t;
	
	//photo shit
	BufferedImage bg, road, bm, cm, go;

	private boolean goboo = false; 
	
	public Cmech() 
	{
		car = new Rectangle(WIDTHB/2-90, HEIGHTB-100, cwidth, cheight);
		
		opcars = new ArrayList<Rectangle> ();
		
		rand = new Random();
		
		t = new Timer(20, this);  //this refers to ActionListeners
		
		//hardcode hatred of images
		//background
		try 
		{
			bg = ImageIO.read(Cmech.class.getResource("/images/sand.png"));
		} catch (IOException e) { e.printStackTrace(); }
		
		//road
		try 
		{
			road = ImageIO.read(Cmech.class.getResource("/images/road.png"));
		} catch (IOException e) { e.printStackTrace(); }
			 
		//batmobile
		try 
		{
			bm = ImageIO.read(Cmech.class.getResource("/images/batmobile.png"));
		} catch (IOException e) { e.printStackTrace(); }
		
		//coppers
		try 
		{
			cm = ImageIO.read(Cmech.class.getResource("/images/copmobile.png"));
		} catch (IOException e) { e.printStackTrace(); }
		
		try 
		{
			go = ImageIO.read(Cmech.class.getResource("/images/go.png"));
		} catch (IOException e) { e.printStackTrace(); }
		
		space = 300;
		speed = 2.0;
		
		addKeyListener(this);
		setFocusable(true);
		
		addopcars(true);
		addopcars(true);
		addopcars(true);
		addopcars(true);	
		
		t.start();
	}
	
	public void addopcars(boolean first) 
	{
		int positionx = rand.nextInt() % 2;
		int x = 0;
		int y = 0;
		int Width = cwidth - 7;
		int Height = cheight + 10;
		
		if(positionx == 0) 
		{
			x = WIDTHB/2-90;
		}
		else 
		{
			x = WIDTHB/2+10;
		}
		if(first) 
		{
			opcars.add(new Rectangle(x, y-100-(opcars.size()*space), Width, Height));
		}
		else 
		{
			opcars.add(new Rectangle(x, opcars.get(opcars.size()-1).y-300, Width, Height));
		}
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		//background
		//g.drawImage();
		g.drawImage(bg, 0, 0, null);
		
		//road
		g.drawImage(road, 87, -100, null);
		
		//batmobile
		//g.setColor(Color.BLACK);
		//g.fillRect(car.x, car.y, car.width, car.height);
		g.drawImage(bm, car.x - 5, car.y - 5,null);
		
		//opponent cars
		//g.setColor(Color.RED);
		for(Rectangle rect: opcars) 
		{
			//g.fillRect(rect.x, rect.y, rect.width, rect.height);
			g.drawImage(cm, rect.x - 22, rect.y - 10,null);
		}
		
		if(goboo)
			{
				//implement game over class, try to make a thing that shows leaderboard if have time 
				g.drawImage(go, -50, -50, null);
			}	
	}

	public void actionPerformed(ActionEvent e) 
	{
		Rectangle rect;
		count++;
		
		for(int i = 0; i < opcars.size(); i++) 
		{
			rect = opcars.get(i);
			if(count % 500 == 0) 
			{
				speed = speed + 0.25;
				if(move < 10) 
				{
					move+=10;
				}
			}
			rect.y += speed;
		}
		
		//collisions 
		for(Rectangle r: opcars) 
		{
			if(r.intersects(car))
			{
				//implement game over class, try to make a thing that shows leaderboard if have time 
				car.y = r.y+cheight;
				goboo = true;
			}
		}
		
		//waste removal
		for(int i = 0; i < opcars.size(); i++) 
		{
			rect = opcars.get(i);
			if(rect.y + rect.height > HEIGHTB) 
			{
				opcars.remove(rect);
				addopcars(false);
			}
		}
		
		repaint();
	}
	
	//directional movement
	public void moveUp() 
	{
		if(car.y-move < 0) 
		{
			//nothing
		}
		else 
		{
			car.y -= move;
		}
	}
	
	public void moveDown() 
	{
		if(car.y+move+car.height > HEIGHTB - 1) 
		{
			//nothing
		}
		else 
		{
			car.y += move;
		}
	}
	
	public void moveRight() 
	{
		if(car.x+move > WIDTHB/2 + 10) 
		{
			//nothing
		}
		else 
		{
			car.x += move;
		}
	}
	
	public void moveLeft() 
	{
		if(car.x-move < (WIDTHB/2 - 90)) 
		{
			//nothing
		}
		else 
		{
			car.x -= move;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{		
		int key = e.getKeyCode();
		switch(key) 
		{
		case KeyEvent.VK_UP:
			moveUp();
			break;
		case KeyEvent.VK_DOWN:
			moveDown();
			break;
		case KeyEvent.VK_RIGHT:
			moveRight();
			break;
		case KeyEvent.VK_LEFT:
			moveLeft();
			break;
		default:
			break;			
		}
	}
}
