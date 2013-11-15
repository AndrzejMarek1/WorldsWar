package pl.nartenlener;

import java.awt.Canvas;
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

import java.util.HashMap;

public class wojnaSwiatow extends Canvas{
	public static final int SZEROKOSC = 800;
	public static final int WYSOKOSC = 600;
	public static final int SZYBKOSC = 60;
	public long usedTime;
	public HashMap sprites;
	public int pozX, pozY, vX;
	public BufferStrategy strategia;
	
	public wojnaSwiatow() {
		
		pozX = SZEROKOSC/2;
		pozY = WYSOKOSC/2;
		vX = 2; //szybkoœæ odbijania siê potworka
		
		sprites = new HashMap();
		
		JFrame okno = new JFrame(".: Wojna Œwiatów :.");
		JPanel panel = (JPanel) okno.getContentPane();
		setBounds(0, 0, SZEROKOSC, WYSOKOSC);
		panel.setPreferredSize(new Dimension(SZEROKOSC, WYSOKOSC));
		panel.setLayout(null);
		panel.add(this);
		
		okno.setBounds(0, 0, SZEROKOSC, WYSOKOSC);
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
	
	public void paintWindow() //rysowanie obiektów na panelu
	{
		String adres = "D:\\Eclipse\\Dokumenty\\WojnaSwiatow\\grafika\\Potwor.png";
		
		Graphics g = strategia.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight()); // czyszczenie okna
		g.drawImage(getSprite(adres), pozX, pozY,this); //rysowanie obrazka
		
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
	
	public BufferedImage loadImage(String sciezka)
	{
		File imgFile = new File(sciezka);
		try {
			//url = this.getClass().getResource("D://Eclipse//Dokumenty//WojnaSwiatow//grafika//Potwor.gif");
			return ImageIO.read(imgFile);
		} catch (Exception e) {
			System.out.println("Przy otwieraniu " + sciezka + " jako " + imgFile.getPath());
			System.out.println("Wyst¹pi³ b³¹d: " + e.getClass().getName() + " " + e.getMessage());
			System.exit(0);
			return null;
		}
	}
	
	public BufferedImage getSprite(String sciezka) 
	{
		BufferedImage img = (BufferedImage) sprites.get(sciezka);
		if (img == null)
		{
			img = loadImage(sciezka);
			sprites.put(sciezka, img);
		}
		
		return img;
	}
	
	public void updateWorld() // Odswiezanie swiata
	{
		pozX = pozX + vX;
		if (pozX < 0 || pozX > SZEROKOSC)
		{
			vX = -vX;
		}
	}
	
	public void game()
	{
		usedTime = 1000;
		
		while (isVisible()) // pêtla rysuj¹ca nowe potwory
		{
			long startTime = System.currentTimeMillis();
			updateWorld();
			paintWindow();
			usedTime = System.currentTimeMillis()-startTime;
			
			try {
				Thread.sleep(SZYBKOSC);
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
