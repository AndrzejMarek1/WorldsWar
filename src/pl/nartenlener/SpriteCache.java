package pl.nartenlener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteCache {
	public HashMap sprites;
	
	public SpriteCache()
	{
		sprites = new HashMap();
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

}
