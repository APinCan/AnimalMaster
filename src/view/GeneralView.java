package view;

import java.awt.Graphics;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GeneralView extends JPanel implements View {
	private int viewX;
	private int viewY;
	private String mapName;
	ImageIcon icon;
	Clip clip;
	
	public GeneralView() {
		
	}
	
	public GeneralView(String mapName) {
		setMap(mapName);
	}
	
	@Override
	public void setMapSize(String mapName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(icon.getImage(), 0, 0, null);
		
		setOpaque(false);
		super.paintComponent(g);
	}

	@Override
	public int getBackgroundImageX() {
		// TODO Auto-generated method stub
		return viewX;
	}

	@Override
	public int getBackgroundImageY() {
		// TODO Auto-generated method stub
		return viewY;
	}
	
	@Override
	public void playBackgroundMusic() {
		String musicName="";
		
		switch(mapName) {
		case "ForestView":
//			musicName;
			break;
		case "DesertView":
			break;
		case "BeachView":
			break;
		case "TrainingCityView":
			musicName="TrainingCityWAV.wav";
			break;
		case "BattlePhase":
			musicName="BattlePhaseDOSWAV.wav";
			break;
		}
		
		String mediaPath=path+"\\src\\Music\\"+musicName;
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(mediaPath).getAbsoluteFile());
			clip= AudioSystem.getClip();
			clip.open(audioIn);
			clip.loop(3);
			clip.start();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("No Music!");
		}
	}

	public void setMap(String mapName) {
		this.mapName=mapName;
		String imagePath=path+"/src/Image/"+mapName+".jpg";
		icon = new ImageIcon(imagePath);
		
		if(mapName=="ForestView") {
			this.viewX=768;
			this.viewY=704;
		}
		else if(mapName=="DesertView"){
			this.viewX=736;
			this.viewY=496;
		}
		else if(mapName=="BeachView") {
			this.viewX=640;
			this.viewY=960;
		}
		else if(mapName=="TrainingCityView") {
			this.viewX=640;
			this.viewY=320;
		}
		else if(mapName=="BattlePhase") {
			this.viewX=556;
			this.viewY=371;
		}
		
		playBackgroundMusic();
		//맵의 사이즈 설정
//		setSize(this.viewX, this.viewY);
	}
}
