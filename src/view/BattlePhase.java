package view;

import java.awt.Graphics;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Animal;
import model.Hunter;
import model.User;

public class BattlePhase extends JPanel implements View {
	private int viewX=575;
	private int viewY=440;
	ImageIcon icon;
	Clip clip;
	
	//동물
	ImageIcon myAnimalIcon;
	JLabel yourAnimalLabel = new JLabel();
	
	public BattlePhase() {
		
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(icon.getImage(), 0, 0, null);
		
		setOpaque(false);
		super.paintComponent(g);
	}
	
	@Override
	public void setMap(String mapName) {
		String imagePath=path+"/src/Image/"+"BattlePhase"+".jpg";
		icon=new ImageIcon(imagePath);
		
		setSize(viewX, viewY);
		playBackgroundMusic();
	}

	@Override
	public int getBackgroundImageX() {
		// TODO Auto-generated method stub
		return this.viewX;
	}

	@Override
	public int getBackgroundImageY() {
		// TODO Auto-generated method stub
		return this.viewY;
	}

	@Override
	public void playBackgroundMusic() {
		// TODO Auto-generated method stub
		String mediaPath=path+"\\src\\Music\\"+"BattlePhaseDOSWAV.wav";
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(mediaPath).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.loop(5);
			clip.start();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("No Music!");
		}
	}
	
	public void setMapCharacter(User user, Animal animal) {
		this.setMap("BattlePhase");
		//가져온 애니멀이 어떤동물인가 알아보기
	}
	
	public void setMapCharacter(User user, Hunter player) {
		this.setMap("BattlePhase");
	}
	
	
	public void setYourAnimal(Animal animal) {
		//애니멀이 무엇인가?
//		String animal = animal.name;
//		String imagePath= path+"/src/Image/"+aniaml+".gif";
		
//		ImageIcon yourAnimalIcon = new ImageIcon(imagePath);
//		yourAnimalLabel.setIcon(yourAnimalIcon);
//		this.add(yourAnimalLabel);
//		setLocation();
	}
}