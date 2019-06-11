package model;

import java.util.ArrayList;

public class User extends Hunter {
	public int[] win;
	
	public User() {
		win = new int[5];
	}
	
	public void setWin(int id, int win) {
		this.win[id] = win; //hunter[id]에게 이겼을 시 1설정 
	}
	
	public int[] getWin() {
		return this.win;
	}
	
	
	
}
