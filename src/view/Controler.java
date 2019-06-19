package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import model.*;

public class Controler {
	private Random ran;
	private Hunter[] npc;
	
	private Controler() {
		ran = new Random();
		npc = new Hunter[5];
		for (int i = 0; i < 5; i++) {
			npc[i] = new Hunter();
			npc[i].id=i;
			npc[i].cage = new ArrayList<Animal>();
			Animal ani = new Mouse();
			ani.setMaxHp(ani.getHp() + i * 10);
			ani.setHp(ani.getMaxHp());
			ani.setArmor(ani.getArmor() + i * 10);
			ani.setEvasion(ani.getEvasion() + i * 10);
			ani.setPower(ani.getPower() + i * 10);
			npc[i].cage.add(ani);
			
			ani = new Deer();
			ani.setMaxHp(ani.getHp() + i * 10);
			ani.setHp(ani.getMaxHp());
			ani.setArmor(ani.getArmor() + i * 10);
			ani.setEvasion(ani.getEvasion() + i * 10);
			ani.setPower(ani.getPower() + i * 10);
			npc[i].cage.add(ani);
			
			ani = new Fox();
			ani.setMaxHp(ani.getHp() + i * 10);
			ani.setHp(ani.getMaxHp());
			ani.setArmor(ani.getArmor() + i * 10);
			ani.setEvasion(ani.getEvasion() + i * 10);
			ani.setPower(ani.getPower() + i * 10);
			npc[i].cage.add(ani);
			
			ani = new Dog();
			ani.setMaxHp(ani.getHp() + i * 10);
			ani.setHp(ani.getMaxHp());
			ani.setArmor(ani.getArmor() + i * 10);
			ani.setEvasion(ani.getEvasion() + i * 10);
			ani.setPower(ani.getPower() + i * 10);
			npc[i].cage.add(ani);
		}
	}

	private static class Singleton {
		public static final Controler INSTANCE = new Controler();
	}

	public static Controler getInstance() {
		return Singleton.INSTANCE;
	}

	private Animal randAnimal(int map) {
		Animal ani=null;
		int per = ran.nextInt(10);
		switch(map) {
		case 0 :
			if(per < 5)
				ani = new Jellyfish();
			else if(per < 9)
				ani = new Deer();
			else
				ani = new Shark();
			break;
		case 1 :
			if(per < 5)
				ani = new Fox();
			else if(per < 9)
				ani = new Wolf();
			else
				ani = new Lion();
			break;
		case 2 :			
			if(per < 5)
				ani = new Mouse();
			else if(per < 9)
				ani = new Dog();	
			break;
		default :
			ani = null;
			break;
		}
		return ani;
	}

	public Animal meetAnimal(int map) {
		if (ran.nextInt(10) < 3)
			return randAnimal(map);
		return null;
	}

	public void calcHP(Animal ani1, Animal ani2) {
		int t1 = ani1.getPropertyType(), t2 = ani2.getPropertyType();
		double adv1, adv2;
		if (t1 == t2) {
			adv1 = 1;
			adv2 = 1;
		} else if (t1 < t2) {
			if (t1 == 0 && t2 == 2) {
				adv1 = 0.5;
				adv2 = 2;
			} else {
				adv1 = 2;
				adv2 = 0.5;
			}
		} else {
			if (t1 == 2 && t2 == 0) {
				adv1 = 2;
				adv2 = 0.5;
			} else {
				adv1 = 0.5;
				adv2 = 2;
			}
		}
		if (!(ran.nextInt(100) < ani1.getEvasion())) {
			double attack = (ani2.getPower() - ani1.getArmor()) * adv1;
			if (attack > 0) {
				ani1.setHp(ani1.getHp() - (int) attack);
				if (ani1.getHp() < 0)
					ani1.setHp(0);
			}
		}

		if (!(ran.nextInt(100) < ani2.getEvasion())) {
			double attack = (ani1.getPower() - ani2.getArmor()) * adv2;
			if (attack > 0) {
				ani2.setHp(ani2.getHp() - (int) attack);
				if (ani2.getHp() < 0)
					ani2.setHp(0);
			}
		}
		System.out.println(ani1.getHp()+" "+ani2.getHp());
	}

	public boolean catchAnimal(Animal ani) {
		int hp = ani.getHp();
		int per;
		if (hp > (ani.getMaxHp() * 0.5)) {
			per = 0;
		} else if (hp > (ani.getMaxHp() * 0.3)) {
			per = 40;
		} else if (hp > (ani.getMaxHp() * 10)) {
			per = 60;
		} else {
			per = 80;
		}
		if(ran.nextInt(100) < per)
			return true;
		return false;
	}

	public void winBattle(Animal winner, Animal loser) {
		double plusPower = 0.05 * loser.getPower();
		double plusMaxHp = 0.05 * loser.getMaxHp();
		double plusArmor = 0.05 * loser.getArmor();
		double plusEvasion = 0.05 * loser.getEvasion();
		int power = winner.getPower() + (int) plusPower;
		int MaxHp = winner.getMaxHp() + (int) plusMaxHp;
		int armor = winner.getArmor() + (int) plusArmor;
		int Evasion = winner.getEvasion() + (int) plusEvasion;

		winner.setPower(power);
		winner.setMaxHp(MaxHp);
		winner.setArmor(armor);
		winner.setEvasion(Evasion);

		winner.setHp(winner.getMaxHp());
		loser.setHp(loser.getMaxHp());
	}

	public void saveGame(User user, int saveNum) throws SQLException {
		DAO db = new DAO();
		db.save(user, saveNum);
	} 

	public User loadGame(int loadNum) throws SQLException {
		DAO db = new DAO();
		User user = db.load(loadNum);
		return user;
	}
	public Hunter getNPC(int npcNum) {
		return npc[npcNum];
	}
}
