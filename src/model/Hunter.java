package model;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Hunter {
	
	public int id; //npc_hunter占쎈굶占쎌�� 占쎈�쏙옙��
	public ArrayList<Animal> cage;
	private Animal attacker;
	
	public Hunter() {
		cage = new ArrayList<Animal>();
		//cage.add(new Mouse());//
		cage.add(new Shark());
	}	
	
	public void setAttacker(int index) {

		this.attacker = this.cage.get(index);
		//占쎌�쏙옙�� �⑤��爰쏙옙釉�占쎈�� 雅��깆�� 占쎄�占쎌��
	}
	
	public ArrayList<Animal> getCage(){
		return this.cage;

	}

}


