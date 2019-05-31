package model;

public class User extends Hunter {
	public int[] win;
	
	public User() {
		win = new int[5];
	}
	
	public void setWin(int id, int win) {
		this.win[id] = win; //hunter[id]에게 이겼을 시 1설정 
	}
	
	public String getWin() {
		String output = "";
		int i=0;
		while(true) {
			output+=Integer.toString(this.win[i]);
			i++;
			if(i==win.length-1) break;
			output+=",";
		}
		return output;
	}
	
}
