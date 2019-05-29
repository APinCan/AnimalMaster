package model;

import java.util.concurrent.ThreadLocalRandom;

public class Animal {
	private int hp;
	private int power; //공격력
	private int armor; //방어력
	private int evasion; //회피율 1~100 30
	
	public Animal() {
		this.hp = 100;
		this.power = 10;
		this.armor = 10;
		this.evasion = 10;
	}
	
	public Animal(int hp,int power, int armor, int evasion) {
		this.hp = hp;
		this.power = power;
		this.armor = armor;
		this.evasion = evasion;
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getEvasion() {
		return evasion;
	}

	public void setEvasion(int evasion) {
		this.evasion = evasion;
	}
	
	public boolean evade() {
		//회피 선택시 랜덤으로 회피 성공
		double percent = this.getEvasion()/100;
		boolean result = percent > ThreadLocalRandom.current().nextDouble(0,1);
		//회피율 변수가 랜덤으로 뽑은 double 변수와 비교해서 그보다 작은지를 확인
		//percent가 0.8일 시 0.8이 넘는 수는 false, 그 이하는 true
		//true --> 회피 성공 false-->회피 실패
		return result;
		
	}

	public void 물기() {}

}
