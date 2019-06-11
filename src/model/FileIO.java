package model;

import java.io.BufferedReader;
import model.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Iterator;


public class FileIO {
	
	
	public static void save(User user, String fileName) throws IOException {
		try {
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
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
		

			
			String animalName = "./animal.csv";		
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(animalName,true));
		
			
			
			ArrayList<Animal> cage = user.getCage();
			
			Iterator<Animal> it = cage.iterator();
			while(it.hasNext()) {
				Animal a = it.next();
				
				String name = a.toString();				
				String hp = Integer.toString(a.getHp());
				String power = Integer.toString(a.getPower());
				String armor = Integer.toString(a.getArmor());
				String evasion = Integer.toString(a.getEvasion());
				
				String input = name+","+hp+","+power+","+armor+","+evasion+"\r\n";
				System.out.println(input);
				
				writer.write(input);
				
				
			}
			writer.close();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
		
	}
	
	public static User load(String fileName) throws IOException {
		User user = new User();
		
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
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
			

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("파일을 발견하지 못했습니다.");
		} catch (IOException e) {
			throw new IllegalArgumentException("파일을 불러오지 못했습니다.");
		}
		
		
		String csvFileName = "./animal.csv";
		BufferedReader br = new BufferedReader(new FileReader(csvFileName));
		String line = null;
		while((line=br.readLine())!=null) {
			String[] arr = line.split(",");
			//name, hp, power, armor, evasion
			Animal a = new Animal();
			a.setName(arr[0]);
			a.setHp(Integer.parseInt(arr[1]));
			a.setPower(Integer.parseInt(arr[2]));
			a.setArmor(Integer.parseInt(arr[3]));
			a.setEvasion(Integer.parseInt(arr[4]));
			
			user.getCage().add(a);
		}
		

		
		

		return user;
	}
	
	

	
	
}
