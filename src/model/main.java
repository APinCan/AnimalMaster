package model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class main {

	public static void main(String[] args) throws SQLException {

		
		User user = new User();
		user.setWin(0,0);
		user.setWin(1,1);
		
	
		DAO dao = new DAO();
		dao.save(user, 0);
		Map<Integer, String> list = dao.printList();
		System.out.println(list.keySet());
		dao.load(1);
		
	}

}