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
	int currentMapInteger;
	ImageIcon icon;
	Clip clip;
	StartView startView;
	meetAnimalThread thread = new meetAnimalThread();
	
	//嶺�占쏙옙�우����占�
	User user;
	Hunter hunter;
	
	//癲ワ옙���뱄옙占썲���������뀐옙占쏙옙占쏙옙轅⑨옙占쏙옙占썲������占쏙옙占쏙옙������占쎈�占쏙옙占쏙옙占쏙옙������
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
	���э옙�놂옙������占� ����占쏙옙繹�������占� binding
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
	 * 占쏙옙占쏙옙���������⑼옙恝�����������������������������������뀐옙占썲��占�
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
		
		//�꿔����占쏙옙占쎈����������獄�紐�占썩�⑥�����������������������뀐옙占썲��占� 占쏙옙占쏙옙������������占쏙옙���������뀐옙占썲��占�
		charPath=path+"/src/Image/hunter.gif";
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
			System.out.println("���������쇽옙������������������������������������������ bgm������������������ ��������������������������������怨ㅼ��������������������������������������");
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
			//����������占쎄낀����������������������������占� 占쏙옙占쏙옙���⑼옙�쎌��������筌�占썹낼������������������������������������������占쏙옙��占�
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
			currentMapInteger=2;
//			charLabel.setLocation(225, 100);
		}
		else if(mapName=="DesertView"){
			this.viewX=755;
			this.viewY=565;
			currentMapInteger=1;
//			charLabel.setLocation(180, 265);
		}
		else if(mapName=="BeachView") {
			this.viewX=655;
			this.viewY=975;
			currentMapInteger=0;
//			charLabel.setLocation(180, 60);
		}
		else if(mapName=="TrainingCityView") {
			this.viewX=660;
			this.viewY=390;
			charLabel.setLocation(5, 120);
		}
		else if(mapName=="BossPhase") {
			this.viewX=640;
			this.viewY=970;
			//����������占쎄낀����������占� ������������������������������������������������������������������������
		}
		
		playBackgroundMusic();
		//�꿔��������占쏙옙��������������������占� ����������占쎄낀�����������뀐옙占쏙옙占썲����占쏙������뀐옙占썲��占� ���������쇽옙������������������������
//		setSize(this.viewX, this.viewY);
	}
	
	public void setStartView(StartView startView) {
		this.startView=startView;
	}
	
	//�꿔��������占쏙옙��������������������占� boundary占쏙옙占쏙옙占쏙옙������占쏙옙��占� ���������쇽옙������������������������������������������ ��������占썲��������占� ������������������������������������������占쏙옙��占� �꿔����占쏙옙���몌옙������������������������占쏙옙������������������占쎌�몄�������� ������������������
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
		
		//������������������占쏙옙占쏙옙�댐옙
		if(currentLocationX==0 && currentLocationY==120) {
//			clip.stop();
			startView.moveNextMap("ForestView");
			charLabel.setLocation(225, 100);
			thread.start();
//			playBackgroundMusic();
		}
		//������������������������������������
		else if(currentLocationX==250 && currentLocationY==280) {
			startView.moveNextMap("BeachView");
			charLabel.setLocation(180, 60);
			thread.start();
		}
		//���������쇽옙������占쏙옙占쏙옙�����쇔������������������
		else if(currentLocationX >= 600 && currentLocationY == 120) {
			startView.moveNextMap("DesertView");
			charLabel.setLocation(180, 265);
			thread.start();
		}
		//������������������������������������
		else if(currentLocationX == 250 && currentLocationY==0) {
			//����占쏙옙占썲����占쏙���������占썲������������������������������������������������占쏙옙��占�
			startView.moveNextMap("BossPhase");
			charLabel.setLocation(305,855);
			setNPCHunter();
		}	
	}
	
	public void moveToTrainingCityView() {
		int locationX=charLabel.getX();
		int locationY=charLabel.getY();
		
		if(mapName=="ForestView") {
			//������������������������������������ ����������������������������占쎄���������占쏙옙������������ x=225, y=0������������占쏙옙占쎈�������������������뀐옙占쏙옙占썲������ �꿔�������������������������������������몃����占쏙옙������
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
			//x>=130 && x<= 550 ,y=������������占쏙옙占쎈�������������������뀐옙占쏙옙占썲������ �꿔�������������������������������������몃����占쏙옙������
		}
		else if(mapName=="DesertView") {
			//x=65 && x<=85 y>=390 && y<=410 ������������������占쏙옙占쏙옙������ �꿔�������������������������������������몃����占쏙옙������
			if((locationX>=65 && locationX<=85) && (locationY>=390 && locationY<=410)) {
				startView.moveNextMap("TrainingCityView");
				charLabel.setLocation(590, 120);
			}
		}
	}
	
	public void backToBossPhase() {
		int currentLocationX = charLabel.getX();
		int currentLocationY = charLabel.getY();
		if(mapName=="BattlePhase") {
			if(currentLocationX==285 && currentLocationY==750) {
				charLabel.setLocation(285,745);
			}
			//������������������������������������
			else if(currentLocationX==365 && currentLocationY==460) {
				charLabel.setLocation(360,460);
			}
			//���������쇽옙������占쏙옙占쏙옙�����쇔������������������
			else if(currentLocationX ==410 && currentLocationY == 400) {
				charLabel.setLocation(405,400);
			}
			//������������������������������������
			else if(currentLocationX == 285 && currentLocationY==260) {
				//����占쏙옙占썲����占쏙���������占썲������������������������������������������������占쏙옙��占�
				charLabel.setLocation(285,255);
			}	
			else if(currentLocationX == 285 && currentLocationY==120) {
				//����占쏙옙占썲����占쏙���������占썲������������������������������������������������占쏙옙��占�
				charLabel.setLocation(285,115);
			}	
		}
	}
	
	public void moveToBossPhase() {
		int currentLocationX = charLabel.getX();
		int currentLocationY = charLabel.getY();
		if(mapName=="BossPhase") {
			if(currentLocationX==285 && currentLocationY==750) {
				startView.moveBattlePhase(hunter);
			}
			//������������������������������������
			else if(currentLocationX==365 && currentLocationY==460) {
				startView.moveBattlePhase(hunter);
			}
			//���������쇽옙������占쏙옙占쏙옙�����쇔������������������
			else if(currentLocationX ==410 && currentLocationY == 400) {
				startView.moveBattlePhase(hunter);
			}
			//������������������������������������
			else if(currentLocationX == 285 && currentLocationY==260) {
				//����占쏙옙占썲����占쏙���������占썲������������������������������������������������占쏙옙��占�
				startView.moveBattlePhase(hunter);
			}	
			else if(currentLocationX == 285 && currentLocationY==120) {
				//����占쏙옙占썲����占쏙���������占썲������������������������������������������������占쏙옙��占�
				startView.moveBattlePhase(hunter);
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
	
	//�������������������������������������꿔�����γ��占쎈�����占� �꿔��������������������������占쎄낀��占쏙옙������������������鶯ㅿ옙占쏙옙������占쏙옙��占� ������������������������������嶺�占썹�뤄옙������������������ -> �꿔���������������� ������������嶺�占썹�뤄옙���������쇽옙����������������������占쏙�����������������占� ������������������������������������ ���������������⑼옙�쎌����������������������������������占� ���������쇽옙������������������������������������������占쏙옙占쏙옙������������嚥�占썲���몌옙占썲������������������������������������������
	private class meetAnimalThread extends Thread{
		Controler con = Controler.getInstance();
		public void run() {
			Animal popAnimal = null;
			while(true) {
				popAnimal = con.meetAnimal(currentMapInteger);
				if(popAnimal != null) {
					break;
				}
				try {
					Thread.sleep(1000); //1占쏙옙占쏙옙塋�占썲������占쏙옙��占� ���������������������э옙������������占�
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// ����������占쎄낀����������������������������占� �꿔��������占쏙옙��������������������������������������占� popAnimal ������������������ �꿔���������������������������������� �������������������������������������꿔�����γ��占쎈�����占�
			clip.close();
			startView.moveBattlePhase(popAnimal);
		}
	}
	
	public String getCurrentMap() {
		return this.mapName;
	}
}
