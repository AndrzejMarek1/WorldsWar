package pl.nartenlener;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; // obs³uga zdarzeñ
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;

import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.net.*;
import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;

public class wojnaSwiatow extends Canvas implements Stage{
	public long usedTime;
	public BufferStrategy strategia;
	private SpriteCache spriteCache;
	private ArrayList actors;
	
	private Actor player;
	
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
		for (int i = 0; i < actors.size(); i++)
		{
			Actor m = (Actor)actors.get(i);
			m.act();
		}
		
		player.act();
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

}
