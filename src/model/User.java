package model;

public class User extends Hunter {
	public int[] win;
	
	public User() {
		win = new int[5];
	}
	
	public void setWin(int id, int win) {
		this.win[id] = win; //hunter[id]���� �̰��� �� 1���� 
	}
	
	public String getWin() {
		String str="";
		for(int i : win) {
			str+=Integer.toString(i);
		}
		return str;
	}
	
}
