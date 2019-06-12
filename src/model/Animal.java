package model;

import java.util.concurrent.ThreadLocalRandom;

public class Animal {
	
	private int typeid;
	private int hp;
	private int power; //공격력
	private int armor; //방어력
	private int evasion; //회피율 1~100 30
	
	public Animal() {
		this.typeid = 0;
		this.hp = 100;
		this.power = 10;
		this.armor = 10;
		this.evasion = 10;
	}
	
	public Animal(int hp,int power, int armor, int evasion) {
	public Animal(String name, int hp,int power, int armor, int evasion) {
		this.hp = hp;
		this.power = power;
		this.armor = armor;
		this.evasion = evasion;
	}
	
	public int gettypeid() {
		return typeid;
	}

	public void settypeid(int typeid) {
		this.typeid = typeid;
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
	

}




