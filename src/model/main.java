package model;

public class main {

	public static void main(String[] args) {
		User user = new User();
		user.setWin(0,0);
		user.setWin(1,1);
		
		System.out.println(user.getWin());
		FileIO.save(user, "memory3.txt");
		user.setWin(4,1);
		FileIO.load(user, "memory3.txt");
	
		System.out.println(user.getWin());
	
		
	}

}
