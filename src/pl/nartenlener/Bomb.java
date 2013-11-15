package pl.nartenlener;

public class Bomb extends Actor{
	public static final int UP_LEFT = 0;
	public static final int UP = 1;
	public static final int UP_RIGHT = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	public static final int DOWN_LEFT = 5;
	public static final int DOWN = 6;
	public static final int DOWN_RIGHT = 7;
	
	protected int vx;
	protected int vy;
	
	public Bomb(Stage stage, int heading, int x, int y) {
		super(stage);
		this.x = x;
		this.y = y;
		String sprite = "";
		
		switch (heading)
		{
		case UP_LEFT : vx = -Stage.BOMB_SPEED; vy = -Stage.BOMB_SPEED; sprite = "grafika\\lu.png"; break;
		case UP : vx = 0; vy = -Stage.BOMB_SPEED; sprite = "grafika\\u.png"; break;
		case UP_RIGHT : vx = Stage.BOMB_SPEED; vy = -Stage.BOMB_SPEED; sprite = "grafika\\ru.png"; break;
		case LEFT : vx = -Stage.BOMB_SPEED; vy = 0; sprite = "grafika\\l.png"; break;
		case RIGHT : vx = Stage.BOMB_SPEED; vy = 0; sprite = "grafika\\r.png"; break;
		case DOWN_LEFT : vx = -Stage.BOMB_SPEED; vy = Stage.BOMB_SPEED; sprite = "grafika\\ld.png"; break;
		case DOWN : vx = 0; vy = Stage.BOMB_SPEED; sprite = "grafika\\d.png"; break;
		case DOWN_RIGHT : vx = Stage.BOMB_SPEED; vy = Stage.BOMB_SPEED; sprite = "grafika\\rd.png"; break;
		}
		
		setSpriteName(sprite);
	}
	
	public void act()
	{
		super.act();
		y += vy;
		x += vx;
		if (y < 0 || y > Stage.WYSOKOSC || x < 0 || x > Stage.SZEROKOSC)
		{
			remove();
		}
	}
}
