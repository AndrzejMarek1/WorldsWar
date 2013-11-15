package pl.nartenlener;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; // obs³uga zdarzeñ
import java.awt.image.BufferStrategy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class wojnaSwiatow extends Canvas implements Stage, KeyListener{
	public long usedTime;
	public BufferStrategy strategia;
	private SpriteCache spriteCache;
	private ArrayList actors;
	
	private Player player;
	
	public wojnaSwiatow() {
		spriteCache = new SpriteCache();		
		JFrame okno = new JFrame(".: Wojna Œwiatów :.");
		JPanel panel = (JPanel) okno.getContentPane();
		setBounds(0, 0, Stage.SZEROKOSC, Stage.WYSOKOSC);
		panel.setPreferredSize(new Dimension(Stage.SZEROKOSC, Stage.WYSOKOSC));
		panel.setLayout(null);
		panel.add(this);
		
		okno.setBounds(0, 0, Stage.SZEROKOSC, Stage.WYSOKOSC);
		okno.setVisible(true);
		okno.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0); // zamykanie programu wraz z oknem
			}
		});
		okno.setResizable(false);
		
		//zastosowanie podwójnego buforowanie elminuje migotanie potwora zwi¹zane z odœwie¿aniem panelu
		createBufferStrategy(2);
		strategia = getBufferStrategy();
		requestFocus();
		
		addKeyListener(this); // inicjacja listenera klawiatury
	}
	
	public void initWorld()
	{
		actors = new ArrayList();
		for (int i = 0; i < Stage.ILOSC_POTWOROW; i++)
		{
			Monster m = new Monster(this);
			m.setX((int) (Math.random()*Stage.SZEROKOSC));
			m.setY(i * Stage.ODSTEP_Y);
			m.setvX((int) ((Math.random()*3)+1));
			actors.add(m);
		}
		
		player = new Player(this);
		player.setX(Stage.SZEROKOSC/2);
		player.setY(Stage.WYSOKOSC - 2*player.getHeight());
	}
	
	public void paintWindow() //rysowanie obiektów na panelu
	{
		Graphics2D g = (Graphics2D) strategia.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight()); // czyszczenie okna
		for (int i = 0; i < actors.size(); i++)
		{
			Actor m = (Actor) actors.get(i);
			m.paint(g);
		}
		
		player.paint(g);
		
		g.setColor(Color.WHITE);
		if (usedTime > 0) // Wyœwiatlanie Stringa z FPS
		{
			g.drawString(String.valueOf(1000/usedTime) + " fps", 5, WYSOKOSC-50);
		}
		else
		{
			g.drawString("--- fps", 5, WYSOKOSC-50);
		}
		strategia.show();
	}
	
	public void updateWorld() // Odswiezanie swiata
	{
		int i = 0;
		while (i < actors.size())
		{
			Actor m = (Actor) actors.get(i);
			if (m.isMarkedForRemoval())
			{
				actors.remove(i);
			} 
			else
			{
				m.act();
				i++;
			}
		}
		player.act();
	}
	
	public void checkCollisions()
	{
		Rectangle playerBounds = player.getBounds();
		for (int i = 0; i < actors.size(); i++)
		{
			Actor a1 = (Actor) actors.get(i);
			Rectangle r1 = a1.getBounds();
			if (r1.intersects(playerBounds)) {
				player.collision(a1);
				a1.collision(player);
			}
			for (int j = i+1; j < actors.size(); j++) 
			{
				Actor a2 = (Actor)actors.get(j);
				Rectangle r2 = a2.getBounds();
				if (r1.intersects(r2)) 
				{
					a1.collision(a2);
					a2.collision(a1);
				}
			}
		}
	}
	
	public SpriteCache getSpriteCache()
	{
		return spriteCache;
	}
	
	public void game()
	{
		usedTime = 1000;
		initWorld();
		
		while (isVisible()) // pêtla rysuj¹ca nowe potwory
		{
			long startTime = System.currentTimeMillis();
			updateWorld();
			checkCollisions();
			paintWindow();
			usedTime = System.currentTimeMillis()-startTime;
			
			try {
				Thread.sleep(Stage.SZYBKOSC);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		wojnaSwiatow inv = new wojnaSwiatow();
		inv.game();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void addActor(Actor a) {
		actors.add(a);
	}

}
