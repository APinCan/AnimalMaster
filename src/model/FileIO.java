package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
	
	
	public static void save(User user, String fileName) {
		try {
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(user.getWin()+"\n");
			//뭘 더 기록할지 고민
			
			bw.close();
			fw.close();
			
			
		} catch (IOException e) {
			throw new IllegalArgumentException("save 되지 않았습니다.");
		}
	}
	
	public static void load(User user, String fileName) {
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			
			String win = br.readLine();
			for(int i=0;i<win.length();i++) {
				user.setWin(i, win.charAt(i)-'0');
			}
			
			//추가할 것
			
			br.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("파일을 발견하지 못했습니다.");
		} catch (IOException e) {
			throw new IllegalArgumentException("파일을 불러오지 못했습니다.");
		}
	}

	
	
}
