package model;

public class Mouse extends Animal{
	
	
	public Mouse() {
		super();
		settypeid(1);
	}
	
	public Mouse(int hp,int power, int armor, int evasion) {
		super(hp,power,armor,evasion);
	}
}
