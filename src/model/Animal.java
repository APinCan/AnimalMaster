package model;

import java.util.concurrent.ThreadLocalRandom;

public class Animal {
	private int hp;
	private int power; //���ݷ�
	private int armor; //����
	private int evasion; //ȸ���� 1~100 30
	
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
		//ȸ�� ���ý� �������� ȸ�� ����
		double percent = this.getEvasion()/100;
		boolean result = percent > ThreadLocalRandom.current().nextDouble(0,1);
		//ȸ���� ������ �������� ���� double ������ ���ؼ� �׺��� �������� Ȯ��
		//percent�� 0.8�� �� 0.8�� �Ѵ� ���� false, �� ���ϴ� true
		//true --> ȸ�� ���� false-->ȸ�� ����
		return result;
		
	}

	public void ����() {}

}
