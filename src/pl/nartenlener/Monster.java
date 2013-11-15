package pl.nartenlener;

public class Monster extends Actor{
	protected int vX;

	public Monster(Stage stage) {
		super(stage);
		
		String adres = "D:\\Eclipse\\Dokumenty\\WojnaSwiatow\\grafika\\Potwor.png";
		setSpriteName(adres);
	}
	
	public void collision(Actor a)
	{
		if (a instanceof Bullet || a instanceof Bomb)
			remove();
	}
	
	public void act()
	{
		x = x + vX;
		if (x < 0 || x > Stage.SZEROKOSC)
		{
			vX = -vX;
		}
	}

	public int getvX() {
		return vX;
	}

	public void setvX(int vX) {
		this.vX = vX;
	}

}
