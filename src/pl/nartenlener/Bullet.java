package pl.nartenlener;

public class Bullet extends Actor{

	public Bullet(Stage stage) {
		super(stage);
		String adresLasera = "D:\\Eclipse\\Dokumenty\\WojnaSwiatow\\grafika\\laser.png";
		setSpriteName(adresLasera);
	}
	
	public void act()
	{
		super.act();
		y -= Stage.BULLET_SPEED;
		if (y < 0)
			remove();
	}

}
