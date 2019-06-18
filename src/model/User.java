package model;

import java.util.ArrayList;

public class User extends Hunter {
	public int[] win;
	
	public User() {
		win = new int[5];
	}
	
	public void setWin(int id, int win) {
		this.win[id] = win;  
	}
	

	public int[] getWin() {
		return this.win;
	}
	
	public void addCage(Animal animal) {
		this.cage.add(animal);
	}
}

