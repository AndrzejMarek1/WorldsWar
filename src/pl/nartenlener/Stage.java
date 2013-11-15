package pl.nartenlener;

import java.awt.image.ImageObserver;

public interface Stage extends ImageObserver{
	public static final int SZEROKOSC = 800;
	public static final int WYSOKOSC = 600;
	public static final int SZYBKOSC = 50;
	public static final int ILOSC_POTWOROW = 10;
	public static final int ODSTEP_Y = 20;
	public SpriteCache getSpriteCache();

}
