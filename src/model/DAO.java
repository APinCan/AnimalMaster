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
			System.out.println("db에 연결되었습니다.");
			
		} catch (Exception e) {
			System.out.println("DB에 연결되지 않았습니다.");
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
			System.out.println("table 생성 에러");
			e.printStackTrace();
		}
				
	}
	
	public void save(User user, int buttonIndex) {
		
		
		removeAnimal(buttonIndex);
		//새로 동물 정보를 업데이트 하기 위해 이전의 동물 정보들 삭제 (참조무결성 때문에 참조하는 테이블 컬럼 먼저 삭제x)
		
		//미리 insert할 곳의 값을 지워놓음 
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
				System.out.println("User 정보 insert 실패");
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
		//buttonIndex에 해당하는 userid의 동물 정보들을 삭제 
		String query = "DELETE FROM animal WHERE `userid` = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, buttonIndex);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("이전의 동물 정보가 삭제되지 않았습니다.");
			e.printStackTrace();
		}
		
	}
	
	/*
	public int getUserId() {
		int value=0;
		//가장 마지막에 집어넣은 userid 가져오기 
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
		//세이브 파일 목록에서 번호 선택하면 그 번호 파일정보 로드
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
			}//승패정보 저장
			
			
						
		} catch (SQLException e) {
			System.out.println("User win load 실패");
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
				System.out.println("user animal 정보 load 실패");
			}
		
		return user;
	}
	
	public void closeDB() throws SQLException {
		this.conn.close();
		this.rs.close();
		this.pstmt.close();
	}
	
	
	public Animal returnAnimal(int typeid) {
		//typeid에 맞는 동물들을 생성해 리턴
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
		//목록에서 선택할 수 있도록 맵으로 저장해서 가져옴 
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