package pl.nartenlener;

import java.awt.event.KeyEvent;

public class Player extends Actor{
	protected int vx;
	protected int vy;
	private boolean up, down, left, right;
	private static int clusterBombs = 5; //domyœlna wartoœæ bomb

	public Player(Stage stage) {
		super(stage);
		String adresStatku = "grafika\\statek.png";
		setSpriteName(adresStatku);
	}
	
	public void fireCluster()
	{
		if (clusterBombs == 0)
			return;
		
		clusterBombs--;
		stage.addActor( new Bomb(stage, Bomb.UP_LEFT, x,y));
		stage.addActor( new Bomb(stage, Bomb.UP,x,y));
		stage.addActor( new Bomb(stage, Bomb.UP_RIGHT,x,y));
		stage.addActor( new Bomb(stage, Bomb.LEFT,x,y));
		stage.addActor( new Bomb(stage, Bomb.RIGHT,x,y));
		stage.addActor( new Bomb(stage, Bomb.DOWN_LEFT,x,y));
		stage.addActor( new Bomb(stage, Bomb.DOWN,x,y));
		stage.addActor( new Bomb(stage, Bomb.DOWN_RIGHT,x,y));
	}
	
	public void act()
	{
		super.act();
		x += vx;
		y += vy;
		
		if (x < 0 || x > Stage.SZEROKOSC)
		{
			vx = -vx;
		}
		
		if (y < 0 || y > Stage.WYSOKOSC)
		{
			vy = -vy;
		}
	}
	
	protected void updateSpeed()
	{
		vx = 0;
		vy = 0;
		
		if (down) vy = Stage.PLAYER_SPEED;
		if (up) vy = -Stage.PLAYER_SPEED;
		if (left) vx = -Stage.PLAYER_SPEED;
		if (right) vx = Stage.PLAYER_SPEED;
	}
	
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN : down = false; break;
		case KeyEvent.VK_UP : up = false; break;
		case KeyEvent.VK_LEFT : left = false; break;
		case KeyEvent.VK_RIGHT : right = false; break;
		}
		updateSpeed();
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP : up = true; break;
		case KeyEvent.VK_LEFT : left = true; break;
		case KeyEvent.VK_RIGHT : right = true; break;
		case KeyEvent.VK_DOWN : down = true;break;
		case KeyEvent.VK_SPACE :fire(); break;
		case KeyEvent.VK_B : fireCluster(); break;
		}
		updateSpeed();
	}
	
	public void fire()
	{
		Bullet b = new Bullet(stage);
		b.setX(x);
		b.setY(y - b.getHeight());
		stage.addActor(b);
	}
	
	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}
	
}
