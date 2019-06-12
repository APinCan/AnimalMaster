package controler;

import java.util.Random;

import model.*;

public class Controler {
	private Random ran;
	
	private Controler() {
		System.out.println("controler instance");
		ran = new Random();		
	}
	
	private static class Singleton{	// �̱����� ���� Ŭ����
		public static final Controler INSTANCE = new Controler();
	}
	
	public static Controler getInstance() {
		return Singleton.INSTANCE;
	}
	
	// � animal�� ���� �������� ���� ���� ���� (Animal �� ������ ������ ���� �ʿ�)
	private Animal randAnimal() {
		return null;
	}

	public Animal meetAnimal() {
		System.out.println("in meetAnimal");
		if(ran.nextInt(10) < 3)
			return randAnimal();
		return null;
	}
	
	// Animal�� �ൿ�� ���� ���� ������ ��������
	public void calcHP(Animal ani1, Animal ani2) {
		System.out.println("in calcHP");
	}
	
	//Animal�� MAX_HP�� ���� ����Ȯ�� �� ��������
	public boolean catchAnimal(Animal ani) {
		System.out.println("in catchAnimal");
		int hp = ani.getHp();
		int phase;
		
		return true;
	}
	
	//Animal�� MAX_HP�� ���� ����Ȯ�� �� ��������
	public void winBattle(Animal winner, Animal loser) {
	}
	
	
	//view ���� ������� save, load ���� Ȯ�� �� ��������
	public boolean saveGame(int saveNum) {
		return true;
	}
	
	public boolean loadGame(int loadNum) {
		return true;
	}
}