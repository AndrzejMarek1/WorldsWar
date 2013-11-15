package pl.nartenlener;

import java.awt.image.ImageObserver;

public interface Stage extends ImageObserver{
	public static final int SZEROKOSC = 800;
	public static final int WYSOKOSC = 600;
	public static final int SZYBKOSC = 50;
	public static final int ILOSC_POTWOROW = 10;
	public static final int ODSTEP_Y = 20;
	public static final int PLAYER_SPEED = 4;
	public static final int BULLET_SPEED = 10;
	public static final int BOMB_SPEED = 5;
	public SpriteCache getSpriteCache();
	public void addActor(Actor a);

}
