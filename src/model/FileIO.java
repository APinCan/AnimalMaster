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
			//�� �� ������� ���
			
			bw.close();
			fw.close();
			
			
		} catch (IOException e) {
			throw new IllegalArgumentException("save ���� �ʾҽ��ϴ�.");
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
			
			//�߰��� ��
			
			br.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("������ �߰����� ���߽��ϴ�.");
		} catch (IOException e) {
			throw new IllegalArgumentException("������ �ҷ����� ���߽��ϴ�.");
		}
	}

	
	
}
