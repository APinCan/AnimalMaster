package model;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Hunter {
	
	public int id; //npc_hunter들의 숫자
	public ArrayList<Animal> cage;
	private Animal attacker;
	
	
	public Hunter() {
		cage = new ArrayList<Animal>();
		cage.add(new Mouse());
	}	
	
	public void setAttacker(int index) {

		this.attacker = this.cage.get(index);
		//현재 공격하는 주자 설정
	}
	
	public ArrayList<Animal> getCage(){
		return this.cage;

	}
	


}