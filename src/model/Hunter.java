package model;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Hunter {
	
	public int id; //npc_hunter�뱾�쓽 �닽�옄
	public ArrayList<Animal> cage;
	private Animal attacker;
	
	public Hunter() {
		cage = new ArrayList<Animal>();
		cage.add(new Mouse());
	}	
	
	public void setAttacker(int index) {

		this.attacker = this.cage.get(index);
		//�쁽�옱 怨듦꺽�븯�뒗 二쇱옄 �꽕�젙
	}
	
	public ArrayList<Animal> getCage(){
		return this.cage;

	}

}


