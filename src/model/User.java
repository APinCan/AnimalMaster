package model;

import java.util.ArrayList;

public class User extends Hunter {
	public int[] win;
	
	public User() {
		win = new int[5];
	}
	
	public void setWin(int id, int win) {
		this.win[id] = win; //hunter[id]쩔징째횚 �횑째책�쨩 쩍횄 1쩌쨀횁짚 
	}

	public int[] getWin() {
		return this.win;
	}
	
}
