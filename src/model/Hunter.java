package model;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Hunter {
	
	public int id; //npc_hunter���� ����
	public ArrayList<Animal> cage;
	private Animal attacker;
	
	
	public Hunter() {
		cage = new ArrayList<Animal>();
		cage.add(new Mouse());
	}	
	
	public void setAttacker(int index) {

		this.attacker = this.cage.get(index);
		//���� �����ϴ� ���� ����
	}
	
	public ArrayList<Animal> getCage(){
		return this.cage;

	}
	


}