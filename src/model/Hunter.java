package model;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Hunter {
	public int id; //npc_hunter���� ����
	public ArrayList<Animal> cage;
	public Animal attacker;
	
	public Hunter() {
		cage = new ArrayList<Animal>();
		cage.add(new Mouse());
	}	
	
	public void setAttacker(Animal a) {
		this.attacker = a;
		//���� �����ϴ� ���� ����
	}
	
	public void attack(Animal target) {
		this.attacker.����();
		int damage = this.attacker.getPower()/2 - target.getArmor()/4;
		if(target.evade()) return;
		else target.setHp(target.getHp()-damage); //������ ȸ�� �������� ��쿡�� ������ �� 
	}
	
	public boolean run() {
		//����
		return true;
	}
	
	public ArrayList<Animal> getCage(){
		return this.cage;
	}
	
	

}


