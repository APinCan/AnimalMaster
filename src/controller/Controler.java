package controler;

import java.util.Random;

import model.*;

public class Controler {
	private Random ran;
	
	private Controler() {
		System.out.println("controler instance");
		ran = new Random();		
	}
	
	private static class Singleton{	// 싱글톤을 위한 클래스
		public static final Controler INSTANCE = new Controler();
	}
	
	public static Controler getInstance() {
		return Singleton.INSTANCE;
	}
	
	// 어떤 animal을 만날 것인지는 추후 구현 예정 (Animal 의 개수와 저장방식 상의 필요)
	private Animal randAnimal() {
		return null;
	}

	public Animal meetAnimal() {
		System.out.println("in meetAnimal");
		if(ran.nextInt(10) < 3)
			return randAnimal();
		return null;
	}
	
	// Animal의 행동에 대한 변수 수정후 수정예정
	public void calcHP(Animal ani1, Animal ani2) {
		System.out.println("in calcHP");
	}
	
	//Animal의 MAX_HP에 대한 정보확인 후 수정예정
	public boolean catchAnimal(Animal ani) {
		System.out.println("in catchAnimal");
		int hp = ani.getHp();
		int phase;
		
		return true;
	}
	
	//Animal의 MAX_HP에 대한 정보확인 후 수정예정
	public void winBattle(Animal winner, Animal loser) {
	}
	
	
	//view 에서 어떤식으로 save, load 할지 확인 후 수정예정
	public boolean saveGame(int saveNum) {
		return true;
	}
	
	public boolean loadGame(int loadNum) {
		return true;
	}
}