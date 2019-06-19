package model;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Hunter {
	
	public int id; //npc_hunter����援뜹����占쏙옙 ����占쎌����占쏙옙
	public ArrayList<Animal> cage;
	private Animal attacker;
	
	public Hunter() {
		cage = new ArrayList<Animal>();
		cage.add(new Shark());
		cage.add(new Dog());
		cage.add(new Jellyfish());
		//cage.add(new Fox());
		cage.add(new Lion());
		cage.add(new Deer());
	}	
	
	public void setAttacker(int index) {

		this.attacker = this.cage.get(index);
		//����占쎌����占쏙옙 占썩�ㅿ옙占썹�곗������占썲����占쏙옙 ��占쏙옙源�占쏙옙 ����占썲����占쏙옙
	}
	
	public ArrayList<Animal> getCage(){
		return this.cage;

	}

}


