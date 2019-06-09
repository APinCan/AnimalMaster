package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

public class GeneralView extends JPanel implements View{
	private int viewX=0;
	private int viewY=0;
	private String mapName;
	ImageIcon icon;
	Clip clip;
	
	
	//캐릭터이동관련
	ImageIcon characterImageIcon;
	Image charImage;
	String charPath;
	JLabel charLabel;
	
	private static final String LEFT="Left";
	private static final String RIGHT="Right";
	private static final String UP="Up";
	private static final String DOWN="Down";
	
	private Action left= new AbstractAction(LEFT) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int locationX = charLabel.getX()-5;
			int locationY = charLabel.getY();
			
			limitBoundary(locationX, locationY);
		}
	};
	
	private Action right=new AbstractAction(RIGHT) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int locationX = charLabel.getX()+5;
			int locationY = charLabel.getY();
			
			limitBoundary(locationX, locationY);
		}
	};
	
	private Action up = new AbstractAction(UP) {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int locationX= charLabel.getX();
			int locationY=charLabel.getY()-5;
			
			limitBoundary(locationX, locationY);
		}
	};
	
	private Action down = new AbstractAction(DOWN) {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int locationX = charLabel.getX();
			int locationY = charLabel.getY()+5;
			
			limitBoundary(locationX, locationY);
		}	
	};
	
	
	public GeneralView() {
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LEFT);
		this.getActionMap().put(LEFT, left);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), RIGHT);
		this.getActionMap().put(RIGHT, right);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UP);
		this.getActionMap().put(UP, up);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN);
		this.getActionMap().put(DOWN,  down);
		
		this.setLayout(null);
		
		//캐릭터 추가
		charPath=path+"/src/Image/Hunter.jpg";
		characterImageIcon=new ImageIcon(charPath);
		charImage=characterImageIcon.getImage();
		
		charLabel=new JLabel(characterImageIcon);
		charLabel.setEnabled(true);
		
		Dimension size =  charLabel.getPreferredSize();
		charLabel.setLocation(50, 50);
		charLabel.setSize(size.width, size.height);

		this.add(charLabel);
		
		charLabel.setVisible(true);
	}
	
	public GeneralView(String mapName) {
		this();
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
	
	//맵의 boundary를 설정해 그 밖으로 못나가게 함
	private void limitBoundary(int x, int y) {
		if(x<0) {
			charLabel.setLocation(0, y);
		}
		else if(x>viewX) {
			charLabel.setLocation(viewX, y);
		}
		else if(y<0) {
			charLabel.setLocation(x, 0);
		}
		else if(y>viewY) {
			charLabel.setLocation(x, viewY);
		}
		else {
			charLabel.setLocation(x,y);
		}
	}
}
