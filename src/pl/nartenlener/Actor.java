package pl.nartenlener;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Actor {
	protected int x,y;
	protected int width, height;
	protected String spriteName;
	protected Stage stage;
	protected SpriteCache spriteCache;
	protected int currentFrame;
	protected boolean markedForRemoval;
	
	
	public Actor(Stage stage)
	{
		this.stage = stage;
		spriteCache = stage.getSpriteCache();
		currentFrame = 0;
	}

	public void paint(Graphics2D g)
	{
		g.drawImage(spriteCache.getSprite(spriteName), x, y, stage);
	}
	
	public void remove()
	{
		markedForRemoval = true;
	}
	
	public boolean isMarkedForRemoval()
	{
		return markedForRemoval;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getSpriteName() {
		return spriteName;
	}

	public void setSpriteName(String spriteName) {
		this.spriteName = spriteName;
		BufferedImage image = spriteCache.getSprite(spriteName);
		height = image.getHeight();
		width = image.getWidth();
	}
		
	public void act() { }
}
