package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs; 
	
	String dbURL="jdbc:mysql://localhost:3306/javaproject?characterEncoding=UTF-8&serverTimezone=UTC";
	String dbID="root";
	String dbPassword="jimin1306";
	
	public DAO() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			System.out.println("db�� ����Ǿ����ϴ�.");
			
		} catch (Exception e) {
			System.out.println("DB�� ������� �ʾҽ��ϴ�.");
			e.printStackTrace();
		}
		create();
	}
	
	public void create() {
		String query = "CREATE TABLE IF NOT EXISTS user(" + 
				"userid INT NOT NULL," + 
				"npc1 INT NOT NULL," + 
				"npc2 INT NOT NULL," + 
				"npc3 INT NOT NULL," + 
				"npc4 INT NOT NULL," + 
				"npc5 INT NOT NULL," + 
				"PRIMARY KEY (userid));";
		try {
			pstmt = conn.prepareStatement(query);		
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		query = "CREATE TABLE IF NOT EXISTS animal("+
				"userid INT,"+
				"typeid INT,"+
				"animalid INT AUTO_INCREMENT,"+
				"hp INT,"+
				"power INT,"+
				"armor INT,"+
				"evasion INT,"+
				"PRIMARY KEY (animalid),"+
				"INDEX userid_idx(userid ASC) VISIBLE,"+
				"CONSTRAINT userid FOREIGN KEY(userid) REFERENCES user(userid) ON DELETE NO ACTION ON UPDATE NO ACTION);";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("table ���� ����");
			e.printStackTrace();
		}
				
	}
	
	public void save(User user, int buttonIndex) {
		
		
		removeAnimal(buttonIndex);
		//���� ���� ������ ������Ʈ �ϱ� ���� ������ ���� ������ ���� (�������Ἲ ������ �����ϴ� ���̺� �÷� ���� ����x)
		
		//�̸� insert�� ���� ���� �������� 
		String query = "DELETE FROM user WHERE `userid` = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, buttonIndex);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
		query = "INSERT INTO user (userid,npc1,npc2,npc3,npc4,npc5) values (?,?,?,?,?,?)";
			
		//String query = "insert into user (npc1,npc2,npc3,npc4,npc5,date) values (?,?,?,?,?,?)";
		int win[] = user.getWin();
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, buttonIndex);
				for(int i=0;i<5;i++) {
					pstmt.setInt(i+2, win[i]);  
				}
				pstmt.executeUpdate();
			} catch (SQLException e1) {
				System.out.println("User ���� insert ����");
			}
			
		
	
		
		query = "insert into animal (userid,typeid,hp,power,armor,evasion) values (?,?,?,?,?,?)";		
		ArrayList<Animal> animalArr = user.getCage();		
		int size = animalArr.size();
		int i=0;
		while(size>0) {
			try {
				Animal a = animalArr.get(i);
				pstmt = conn.prepareStatement(query);		
				pstmt.setInt(1, buttonIndex);
				pstmt.setInt(2, a.gettypeid());
				pstmt.setInt(3, a.getHp());
				pstmt.setInt(4, a.getPower());
				pstmt.setInt(5, a.getArmor());
				pstmt.setInt(6, a.getEvasion());
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
		
			}
			i++;
			size--;
		 }
		
	}
	
	public void removeAnimal(int buttonIndex) {
		//buttonIndex�� �ش��ϴ� userid�� ���� �������� ���� 
		String query = "DELETE FROM animal WHERE `userid` = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, buttonIndex);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("������ ���� ������ �������� �ʾҽ��ϴ�.");
			e.printStackTrace();
		}
		
	}
	
	/*
	public int getUserId() {
		int value=0;
		//���� �������� ������� userid �������� 
		String query = "SELECT LAST_INSERT_ID() FROM user";
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();			
			while(rs.next()) {
				value = rs.getInt(1);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	*/
	public User load(int index) {
		//���̺� ���� ��Ͽ��� ��ȣ �����ϸ� �� ��ȣ �������� �ε�
		User user = new User();
		String query = "SELECT * FROM user where userid=?";
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, index);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				for(int i=0;i<5;i++) {
					user.setWin(i,rs.getInt(i+2));
					System.out.println(rs.getInt(i+2));
				}
			}//�������� ����
			
			
						
		} catch (SQLException e) {
			System.out.println("User win load ����");
		}
		
		
		query = "SELECT * FROM animal where userid=?";
		
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, index);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					int typeid = rs.getInt("typeid");
					Animal ani = returnAnimal(typeid);					
										
					ani.setHp(rs.getInt(4));
					ani.setPower(rs.getInt(5));
					ani.setArmor(rs.getInt(6));
					ani.setEvasion(rs.getInt(7));
					user.getCage().add(ani);
					System.out.println(ani.getPower());
				}
			} catch (SQLException e) {
				System.out.println("user animal ���� load ����");
			}
		
		return user;
	}
	
	public void closeDB() throws SQLException {
		this.conn.close();
		this.rs.close();
		this.pstmt.close();
	}
	
	
	public Animal returnAnimal(int typeid) {
		//typeid�� �´� �������� ������ ����
		switch (typeid) {
		case 1:
			Animal sh = new Shark();
			return sh;
		case 2:
			Animal de = new Deer();
			return de;
		case 3:
			Animal jell = new Jellyfish();
			return jell;
		case 4:
			Animal lion = new Lion();
			return lion;
		case 5:
			Animal wolf = new Wolf();
			return wolf;
		case 6:
			Animal fox = new Fox();
			return fox;
		case 7:
			Animal bear = new Bear();
			return bear;
		case 8:
			Animal dog = new Dog();
			return dog;
		case 9:
			Animal mouse = new Mouse();
			return mouse;			
		}
		
		return null;
	}
	
	public Map printList() {
		//��Ͽ��� ������ �� �ֵ��� ������ �����ؼ� ������ 
		String query = "SELECT userid,date_format(date,'%Y%m%d%H') FROM user";
		Map<Integer,String> list = new HashMap<Integer,String>();
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.put(rs.getInt(1), rs.getString(2));
			}			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
		
	}
	


}