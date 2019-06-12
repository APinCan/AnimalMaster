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

import model.Animal;
import model.Hunter;
import model.User;

public class GeneralView extends JPanel implements View{
	public static final String path=System.getProperty("user.dir");
	
	private int viewX=0;
	private int viewY=0;
	private String mapName;
	ImageIcon icon;
	Clip clip;
	StartView startView;
	
	//모델
	User user;
	Hunter hunter;
	
	//罹먮┃�꽣�씠�룞愿��젴
	ImageIcon characterImageIcon;
	ImageIcon npc1ImageIcon;
	ImageIcon npc2ImageIcon;
	ImageIcon npc3ImageIcon;
	ImageIcon npc4ImageIcon;
	ImageIcon bossImageIcon;
	
	Image charImage;
	Image npc1Image;
	Image npc2Image;
	Image npc3Image;
	Image npc4Image;
	Image bossImage;
	
	String charPath;
	String npc1Path;
	String npc2Path;
	String npc3Path;
	String npc4Path;
	String bossPath;
	
	JLabel charLabel;
	JLabel npc1Label;
	JLabel npc2Label;
	JLabel npc3Label;
	JLabel npc4Label;
	JLabel bossLabel;
	
	/*
	키보드 이벤트 binding
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
	 * �뀎怨쀯옙占쏙옙占�
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
		
		//筌�占썹뵳占쏙옙占� �빊占썲첎占�
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
		try {
			clip.close();
		} catch(NullPointerException e) {
			System.out.println("占썬끉占쏙옙占쏙옙 bgm占쏙옙 占쏙옙占쎈벉占쏙옙占쏙옙");
		}

		String musicName="";
		
		switch(mapName) {
		case "ForestView":
			musicName="ForestWAV.wav";
			break;
		case "DesertView":
			musicName="DesertWAV.wav";
			break;
		case "BeachView":
			musicName="BeachWAV.wav";
			break;
		case "TrainingCityView":
			musicName="TrainingCityWAV.wav";
			break;
		case "BossPhase":
			//占싼덈┛占쏙옙 �뇡占썸묾占쏙옙占쏙옙占�
			break;
//		case "BattlePhase":
//			musicName="BattlePhaseDOSWAV.wav";
//			break;
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
//			charLabel.setLocation(225, 100);
		}
		else if(mapName=="DesertView"){
			this.viewX=755;
			this.viewY=565;
//			charLabel.setLocation(180, 265);
		}
		else if(mapName=="BeachView") {
			this.viewX=655;
			this.viewY=975;
//			charLabel.setLocation(180, 60);
		}
		else if(mapName=="TrainingCityView") {
			this.viewX=660;
			this.viewY=390;
			charLabel.setLocation(5, 120);
		}
		else if(mapName=="BossPhase") {
			this.viewX=640;
			this.viewY=1280;
			//占싼덈┛ 占쏙옙占쏙옙占쏙옙占쏙옙
//			charLabel.setLocation(305,855);
		}
//		else if(mapName=="BattlePhase") {
//			this.viewX=575;
//			this.viewY=440;
//			charLabel.setLocation(1000, 1000);
//			npc1Label.setLocation(1000, 1000);
//			npc2Label.setLocation(1000, 1000);
//			npc3Label.setLocation(1000, 1000);
//			npc4Label.setLocation(1000, 1000);
//			bossLabel.setLocation(1000,1000);
//		}
		
		playBackgroundMusic();
		//筌띾벊占쏙옙 占싼딉옙�똻占� 占썬끉占쏙옙
//		setSize(this.viewX, this.viewY);
	}
	
	public void setStartView(StartView startView) {
		this.startView=startView;
	}
	
	//筌띾벊占쏙옙 boundary�몴占� 占썬끉占쏙옙占쏙옙 域뱄옙 獄쏉옙占쎌눖占� 筌륁궠占쏙옙揶쏉옙野껓옙 占쏙옙
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
		else if(mapName == "BossPhase") {
			moveToBossPhase();
		}
		else {
			moveToTrainingCityView();
		}
	}
	
	public void moveToOtherView() {
		int currentLocationX = charLabel.getX();
		int currentLocationY = charLabel.getY();
		
		//占쎌눘�걹
		if(currentLocationX==0 && currentLocationY==120) {
//			clip.stop();
			startView.moveNextMap("ForestView");
			charLabel.setLocation(225, 100);
//			playBackgroundMusic();
		}
		//占쏙옙占쏙옙
		else if(currentLocationX==250 && currentLocationY==280) {
			startView.moveNextMap("BeachView");
			charLabel.setLocation(180, 60);
		}
		//占썬끇�뀲筌잞옙
		else if(currentLocationX >= 600 && currentLocationY == 120) {
			startView.moveNextMap("DesertView");
			charLabel.setLocation(180, 265);
		}
		//占쏙옙占쏙옙
		else if(currentLocationX == 250 && currentLocationY==0) {
			//癰귣똻占썬끇占쏙옙占쏙옙占�
			startView.moveNextMap("BossPhase");
			charLabel.setLocation(305,855);
			setNPCHunter();
		}	
	}
	
	public void moveToTrainingCityView() {
		int locationX=charLabel.getX();
		int locationY=charLabel.getY();
		
		if(mapName=="ForestView") {
			//占쏙옙占쏙옙 占쏙옙燁삼옙揶쏉옙 x=225, y=0占쎈�占쎌눖�늺 筌랃옙占쏙옙嚥∽옙
			if(locationX == 225 && locationY==0) {
//				clip.stop();
				startView.moveNextMap("TrainingCityView");
				charLabel.setLocation(10, 120);
//				playBackgroundMusic();
			}
		}
		else if(mapName=="BeachView") {
			if((locationX >=130 && locationX<=550) && locationY==0) {
				startView.moveNextMap("TrainingCityView");
				charLabel.setLocation(250, 270);
				
			}
			//x>=130 && x<= 550 ,y=占쎈�占쎌눖�늺 筌랃옙占쏙옙嚥∽옙
		}
		else if(mapName=="DesertView") {
			//x=65 && x<=85 y>=390 && y<=410 占쎌눖�늺 筌랃옙占쏙옙嚥∽옙
			if((locationX>=65 && locationX<=85) && (locationY>=390 && locationY<=410)) {
				startView.moveNextMap("TrainingCityView");
				charLabel.setLocation(590, 120);
			}
		}
//		else if(mapName=="BossPhase") {
//			//占썬끉占쏙옙 占쎈챶占쏙옙占쎈�占쏙옙占쏙옙占쎄퀡占� 揶쏉옙占쏙옙 �굜占쏙옙占쏙옙占쏙옙占�
//			if((locationX>=285 && locationX<=340) && locationY==860) {
//				startView.moveNextMap("TrainingCityView");
//				charLabel.setLocation(250, 10);
//			}
//		}
	}
	
	public void moveToBossPhase() {
		int currentLocationX = charLabel.getX();
		int currentLocationY = charLabel.getY();
		if(mapName=="BossPhase") {
			if(currentLocationX==285 && currentLocationY==750) {
				startView.moveBattlePhase(user, hunter);;
			}
			//占쏙옙占쏙옙
			else if(currentLocationX==365 && currentLocationY==460) {
				startView.moveBattlePhase(user, hunter);
			}
			//占썬끇�뀲筌잞옙
			else if(currentLocationX ==410 && currentLocationY == 400) {
				startView.moveBattlePhase(user, hunter);
			}
			//占쏙옙占쏙옙
			else if(currentLocationX == 285 && currentLocationY==260) {
				//癰귣똻占썬끇占쏙옙占쏙옙占�
				startView.moveBattlePhase(user, hunter);
			}	
			else if(currentLocationX == 285 && currentLocationY==120) {
				//癰귣똻占썬끇占쏙옙占쏙옙占�
				startView.moveBattlePhase(user, hunter);
			}	
			else if((currentLocationX>=285 && currentLocationX<=340) && currentLocationY==860){
				startView.moveNextMap("TrainingCityView");
				charLabel.setLocation(250, 10);
				npc1Label.setVisible(false);
				npc2Label.setVisible(false);
				npc3Label.setVisible(false);
				npc4Label.setVisible(false);
				bossLabel.setVisible(false);
			}
		}
		
	}
	
	public void setNPCHunter() {
		npc1Path=path+"/src/Image/Npc1.jpg";
		npc1ImageIcon=new ImageIcon(npc1Path);
		npc1Image=npc1ImageIcon.getImage();
		npc1Label=new JLabel(npc1ImageIcon);
		npc1Label.setEnabled(true);
		Dimension size1 =  npc1Label.getPreferredSize();
		npc1Label.setLocation(285, 705);
		npc1Label.setSize(size1.width, size1.height);
		this.add(npc1Label);
		npc1Label.setVisible(true);
		
		npc2Path=path+"/src/Image/Npc2.jpg";
		npc2ImageIcon=new ImageIcon(npc2Path);
		npc2Image=npc2ImageIcon.getImage();
		npc2Label=new JLabel(npc2ImageIcon);
		npc2Label.setEnabled(true);
		Dimension size2 =  npc2Label.getPreferredSize();
		npc2Label.setLocation(395, 450);
		npc2Label.setSize(size2.width, size2.height);
		this.add(npc2Label);
		npc2Label.setVisible(true);
		
		npc3Path=path+"/src/Image/Npc3.jpg";
		npc3ImageIcon=new ImageIcon(npc3Path);
		npc3Image=npc3ImageIcon.getImage();
		npc3Label=new JLabel(npc3ImageIcon);
		npc3Label.setEnabled(true);
		Dimension size3 =  npc3Label.getPreferredSize();
		npc3Label.setLocation(410,350);
		npc3Label.setSize(size3.width, size3.height);
		this.add(npc3Label);
		npc3Label.setVisible(true);
		
		npc4Path=path+"/src/Image/Npc4.jpg";
		npc4ImageIcon=new ImageIcon(npc4Path);
		npc4Image=npc4ImageIcon.getImage();
		npc4Label=new JLabel(npc4ImageIcon);
		npc4Label.setEnabled(true);
		Dimension size4 =  npc4Label.getPreferredSize();
		npc4Label.setLocation(285,220);
		npc4Label.setSize(size4.width, size4.height);
		this.add(npc4Label);
		npc4Label.setVisible(true);
		
		bossPath=path+"/src/Image/Boss.jpg";
		bossImageIcon=new ImageIcon(bossPath);
		bossImage=bossImageIcon.getImage();
		bossLabel=new JLabel(bossImageIcon);
		bossLabel.setEnabled(true);
		Dimension size =  bossLabel.getPreferredSize();
		bossLabel.setLocation(285, 80);
		bossLabel.setSize(size.width, size.height);
		this.add(bossLabel);
		bossLabel.setVisible(true);
	}
	
	//占쏙옙占쏙옙筌롳옙 筌랃옙占싼됵옙占쏙쭪占� 占쏙옙占쎈챷占쏙옙 -> 筌랃옙 占쎈챷占썬끋占쎈똻占쏙옙 占쏙옙占쏙옙 占쎄퀡占쏙옙占쏙옙 占썬끋占쏙옙占쏙옙�놂옙餓ο옙占쎌눖占쏙옙
	private class meetAnimalThread extends Thread{
		Controler con = Controler.getInstance();
		public void run() {
			Animal popAnimal = null;
			while(true) {
				popAnimal = con.meetAnimal();
				if(popAnimal != null) {
					break;
				}
				try {
					Thread.sleep(1000); //1�룯占� 占쏙옙疫뀐옙
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 占싼덈┛占쏙옙 筌띾벊占쏙옙占쏙옙 popAnimal 占쏙옙 筌랃옙占쏙옙 占쏙옙占쏙옙筌롳옙
			startView.moveBattlePhase(user, popAnimal);
		}
	}
}
