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
	
	public void attack(Animal target) {
		this.attacker.물기();
		int damage = this.attacker.getPower()/2 - target.getArmor()/4;
		if(target.evade()) return;
		else target.setHp(target.getHp()-damage); //상대방이 회피 실패했을 경우에만 데미지 들어감 
	}
	
	

}


