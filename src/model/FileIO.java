package model;

import java.io.BufferedReader;
import model.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

public class FileIO {
	
	
	public static void save(User user, String fileName) throws IOException {
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
