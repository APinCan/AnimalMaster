package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class FileIO {
	
	
	public static void save(User user, String fileName) throws IOException {
		//csv 파일에 다 저장
		
			String path="./file/"+fileName;
		
			BufferedWriter bw = new BufferedWriter(new FileWriter(path,true));
			String win = user.getWin();		
			bw.write(win+"\n");
			//이긴 애들 리턴
			
			
			ArrayList<Animal> animal = user.getCage();
			Iterator it = animal.iterator();
			while(it.hasNext()) {
				Animal ani = (Animal) it.next();
				String input = ani.getName()+","+ani.getHp()+","+ani.getPower()+","+ani.getPower()+","+ani.getArmor()+","+ani.getEvasion();
				bw.write(input+"\n");
			}

			
			bw.close();
		}

		
	
	
	public static String[] listFile() {
		File dir = new File("./file");
		File[] fileList = dir.listFiles();
		
		String[] output = new String[fileList.length];
		for(int i=0;i<fileList.length;i++) {
			output[i]=fileList[i].getName();
		}
		
		return output;
		//폴더 안의 파일 리스트 목록 
		
	}
	
	
	public static User load(String fileName) throws IOException {
		
		User user = new User();
		String path = "./file/"+fileName;
	
		
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			String line = br.readLine();
			String[] ar = line.split(",");
			//npc의 갯수대로
			

			for(int i=0;i<5;i++) {
				char at = ar[0].charAt(0);
				user.setWin(i,at-'0');
			}
			
			line = null;			
			while((line=br.readLine())!=null) {
				String[] arr = line.split(",");
				Animal a = new Animal();
				//name,hp,power,armor,evasion
				a.setName(arr[0]);
				a.setHp(Integer.parseInt(arr[1]));
				a.setPower(Integer.parseInt(arr[2]));
				a.setArmor(Integer.parseInt(arr[3]));
				a.setEvasion(Integer.parseInt(arr[4]));
				
				user.getCage().add(a);
				
			}

			
			br.close();
			
		
		
		return user;
	}

	
	
}
