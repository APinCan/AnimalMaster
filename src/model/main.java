package model;

import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		User user = new User();
		user.setWin(0,0);
		user.setWin(1,1);
		
		System.out.println(user.getWin());
		FileIO.save(user, "memory1.txt");
		user.setWin(4,1);
		
		
		user = null;
		user = FileIO.load("memory1.txt");
	
		System.out.println(user.getCage());
		Animal a = user.getCage().get(0);
		a.setHp(10);
		a.setArmor(10);
		a.setEvasion(50);
		a.setPower(15);
		
		
		System.out.println(a.getArmor());
		FileIO.save(user, "memory1.txt");
		
		User u = new User();
		u = FileIO.load("memory1.txt");
		System.out.println(u.getCage());
		
	}

}
