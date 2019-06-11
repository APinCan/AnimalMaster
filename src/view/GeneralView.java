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
	
	StartView startView;
	TrainingCityView trainingcityView;
	
	//캐릭터이동관련
	ImageIcon characterImageIcon;
	Image charImage;
	String charPath;
	JLabel charLabel;
	
	/*
	 * 키보드 리스너 binding
	 */
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
			moveNextView();
			
			System.out.println("x :"+charLabel.getX()+", y : "+charLabel.getY());
		}
	};
	
	private Action right=new AbstractAction(RIGHT) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int locationX = charLabel.getX()+5;
			int locationY = charLabel.getY();
			
			limitBoundary(locationX, locationY);
			moveNextView();
			
			System.out.println("x :"+charLabel.getX()+", y : "+charLabel.getY());
		}
	};
	
	private Action up = new AbstractAction(UP) {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int locationX= charLabel.getX();
			int locationY=charLabel.getY()-5;
			
			limitBoundary(locationX, locationY);
			moveNextView();
			
			System.out.println("x :"+charLabel.getX()+", y : "+charLabel.getY());
		}
	};
	
	private Action down = new AbstractAction(DOWN) {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int locationX = charLabel.getX();
			int locationY = charLabel.getY()+5;
			
			limitBoundary(locationX, locationY);
			moveNextView();
			
			System.out.println("x :"+charLabel.getX()+", y : "+charLabel.getY());
		}	
	};
	
	/*
	 * 뷰생성
	 */
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
			this.viewX=785;
			this.viewY=770;
			charLabel.setLocation(225, 100);
		}
		else if(mapName=="DesertView"){
			this.viewX=755;
			this.viewY=565;
			charLabel.setLocation(180, 265);
		}
		else if(mapName=="BeachView") {
			this.viewX=655;
			this.viewY=975;
			charLabel.setLocation(180, 60);
		}
		else if(mapName=="TrainingCityView") {
			this.viewX=660;
			this.viewY=390;
			charLabel.setLocation(5, 120);
		}
		else if(mapName=="BattlePhase") {
			this.viewX=575;
			this.viewY=440;
			charLabel.setLocation(1000, 1000);
		}
		
		playBackgroundMusic();
		//맵의 사이즈 설정
//		setSize(this.viewX, this.viewY);
	}
	
	public void setStartView(StartView startView) {
		this.startView=startView;
	}
	
	//맵의 boundary를 설정해 그 밖으로 못나가게 함
	private void limitBoundary(int x, int y) {
		if(x<0) {
			charLabel.setLocation(0, y);
		}
		else if(x>viewX-50) {
			charLabel.setLocation(viewX-50, y);
		}
		else if(y<0) {
			charLabel.setLocation(x, 0);
		}
		else if(y>viewY-110) {
			charLabel.setLocation(x, viewY-110);
		}
		else {
			charLabel.setLocation(x,y);
		}
	}
	
	public void moveNextView() {
		if(mapName=="TrainingCityView") {
			moveToOtherView();
		}
		else {
			moveToTrainingCityView();
		}
		
	}
	
	public void moveToOtherView() {
		int currentLocationX = charLabel.getX();
		int currentLocationY = charLabel.getY();
		
		//왼쪽
		if(currentLocationX==0 && currentLocationY==120) {
			startView.moveNextMap("ForestView");
		}
		//아래
		else if(currentLocationX==250 && currentLocationY==280) {
			startView.moveNextMap("BeachView");
		}
		//오른쪽
		else if(currentLocationX >= 600 && currentLocationY == 120) {
			startView.moveNextMap("DesertView");
		}
		//위에
		else if(currentLocationX == 300 && currentLocationY==0) {
			//보스만나러
		}	
	}
	
	public void moveToTrainingCityView() {
		int locationX=charLabel.getX();
		int locationY=charLabel.getY();
		
		if(mapName=="ForestView") {
			//현재 위치가 x=225, y=0이라면 마을로
			if(locationX == 225 && locationY==0) {
				startView.moveNextMap("TrainingCityView");
			}
		}
		else if(mapName=="BeachView") {
			if((locationX >=130 && locationX<=550) && locationY==0) {
				startView.moveNextMap("TrainingCityView");
			}
			//x>=130 && x<= 550 ,y=이라면 마을로
		}
		else if(mapName=="DesertView") {
			//x=65 && x<=85 y>=390 && y<=410 라면 마을로
			if((locationX>=65 && locationX<=85) && (locationY>=390 && locationY<=410)) {
				startView.moveNextMap("TrainingCityView");
			}
		}
	}
}