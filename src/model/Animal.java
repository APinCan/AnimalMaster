package model;

import java.util.concurrent.ThreadLocalRandom;

public class Animal {
	
	String path=System.getProperty("user.dir");
	
	private int typeid;
	private int hp; //
	private int maxHp; //동물의 체력
	private int power; //공격력
	private int armor; //방어력
	private int evasion; //회피율 1~100 30
	public int actionType; //이번턴에 취할 행동 
	public int propertyType; //동물의 속성  0:물  1:사막  2:숲
	public String imagePath;
	
	
	public Animal() {
		this.typeid = 0;
		this.hp = 100;
		this.power = 10;
		this.armor = 10;
		this.evasion = 10;
	}
	
	public String getImagePath() {
		return this.imagePath;
	}
	
	public int getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(int propertyType) {
		this.propertyType = propertyType;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public Animal(int propertyType, int hp,int power, int armor, int evasion) {
		this.propertyType = propertyType;
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
