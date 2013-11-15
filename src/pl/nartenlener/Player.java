package pl.nartenlener;

public class Player extends Actor {
	protected int vx;
	protected int vy;

	public Player(Stage stage) {
		super(stage);
		String adresStatku = "D:\\Eclipse\\Dokumenty\\WojnaSwiatow\\grafika\\statek.png";
		setSpriteName(adresStatku);
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
