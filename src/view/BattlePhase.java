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

public class BattlePhase extends JPanel implements View {
	private int viewX=575;
	private int viewY=440;
	
	StartView startView;
	GeneralView generalView;
	Controler controller = Controler.getInstance();
	//다른 맵에서 배틀페이즈를 띄웠는데 도망가기시에 보스페이즈로가는 문제 발생
	String prevMap="";
	
	User user=null;
	Hunter hunter=null;
	int userAnimalIndex=0;
	int hunterAnimalIndex=0;
	Animal myAnimal;
	Animal yourAnimal;
	
	ImageIcon icon;
	Clip clip;
	
	ImageIcon menu1ImageIcon;
	ImageIcon menu2ImageIcon;
	ImageIcon menu3ImageIcon;
	ImageIcon menu4ImageIcon;
	ImageIcon ticImageIcon;
	Image menu1Image;
	Image menu2Image;
	Image menu3Image;
	Image menu4Image;
	Image ticImage;
	String menu1Path;
	String menu2Path;
	String menu3Path;
	String menu4Path;
	String ticPath;
	JLabel menu1Label;
	JLabel menu2Label;
	JLabel menu3Label;
	JLabel menu4Label;
	JLabel ticLabel;
	
	//배틀페이즈 user, enemy 레이블
	JLabel userLabel = new JLabel();
	JLabel yourLabel = new JLabel();
	
	public BattlePhase() {
		setMenu();
		
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LEFT);
		this.getActionMap().put(LEFT, left);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), RIGHT);
		this.getActionMap().put(RIGHT, right);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), ENTER);
		this.getActionMap().put(ENTER, enter);
	}
	
	private static final String LEFT="Left";
	private static final String RIGHT="Right";
	private static final String ENTER="Enter";
	
	private Action left= new AbstractAction(LEFT) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int locationX = ticLabel.getX()-125;
			int locationY = ticLabel.getY();
			
			limitBoundary(locationX, locationY);

			
			System.out.println("x :"+ticLabel.getX()+", y : "+ticLabel.getY());
		}
	};
	
	private Action right=new AbstractAction(RIGHT) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int locationX = ticLabel.getX()+125;
			int locationY = ticLabel.getY();
			
			limitBoundary(locationX, locationY);

			
			System.out.println("x :"+ticLabel.getX()+", y : "+ticLabel.getY());
		}
	};
	
	private Action enter=new AbstractAction(ENTER) {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(ticLabel.getX()==40) {
				System.out.println("attack");
				attackAnimal();
			}
			else if(ticLabel.getX()==165) {
				System.out.println("catch!");
				catchAnimal();
			}
			else if(ticLabel.getX()==290) {
				System.out.println("change");
				changeAnimal();
			}
			else if(ticLabel.getX()==415) {
				runFromBattle();
			}
		}
	};
	
	private void limitBoundary(int x, int y) {
		if(x<40) {
			ticLabel.setLocation(40, y);
		}
		else if(x>415) {
			ticLabel.setLocation(415, y);
		}
		else {
			ticLabel.setLocation(x,y);
		}
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
	
	public void setMenu() {
		
		setLayout(null);
		
		menu1Path=path+"/src/Image/Attack.jpg";
		menu1ImageIcon=new ImageIcon(menu1Path);
		menu1Image=menu1ImageIcon.getImage();
		menu1Label=new JLabel(menu1ImageIcon);
		menu1Label.setEnabled(true);
		Dimension size1 =  menu1Label.getPreferredSize();
		menu1Label.setLocation(80, 290);
		menu1Label.setSize(size1.width, size1.height);
		this.add(menu1Label);
		menu1Label.setVisible(true);
		
		menu2Path=path+"/src/Image/Defense.jpg";
		menu2ImageIcon=new ImageIcon(menu2Path);
		menu2Image=menu2ImageIcon.getImage();
		menu2Label=new JLabel(menu2ImageIcon);
		menu2Label.setEnabled(true);
		Dimension size2 =  menu2Label.getPreferredSize();
		menu2Label.setLocation(200, 290);
		menu2Label.setSize(size2.width, size2.height);
		this.add(menu2Label);
		menu2Label.setVisible(true);
		
		menu3Path=path+"/src/Image/Change.jpg";
		menu3ImageIcon=new ImageIcon(menu3Path);
		menu3Image=menu3ImageIcon.getImage();
		menu3Label=new JLabel(menu3ImageIcon);
		menu3Label.setEnabled(true);
		Dimension size3 =  menu3Label.getPreferredSize();
		menu3Label.setLocation(325, 290);
		menu3Label.setSize(size3.width, size3.height);
		this.add(menu3Label);
		menu3Label.setVisible(true);
		
		menu4Path=path+"/src/Image/Run.jpg";
		menu4ImageIcon=new ImageIcon(menu4Path);
		menu4Image=menu4ImageIcon.getImage();
		menu4Label=new JLabel(menu4ImageIcon);
		menu4Label.setEnabled(true);
		Dimension size4 =  menu4Label.getPreferredSize();
		menu4Label.setLocation(450, 290);
		menu4Label.setSize(size4.width, size4.height);
		this.add(menu4Label);
		menu4Label.setVisible(true);
		
		ticPath=path+"/src/Image/Tic.jpg";
		ticImageIcon=new ImageIcon(ticPath);
		ticImage=ticImageIcon.getImage();
		ticLabel=new JLabel(ticImageIcon);
		ticLabel.setEnabled(true);
		Dimension tic =  ticLabel.getPreferredSize();
		ticLabel.setLocation(40, 300);
		ticLabel.setSize(tic.width, tic.height);
		this.add(ticLabel);
		ticLabel.setVisible(true);
	}
	
	public void moveToBossPhase() {
		startView.moveBattlePhase(hunter);
	}
	
	public void setMapCharacter(User user, Animal animal, String prevMap) {
		this.setMap("BattlePhase");
		//가져온 애니멀이 어떤동물인가 알아보기
		this.prevMap=prevMap;
		
		System.out.println("Log : prevMap : "+this.prevMap);
		
		setYourAnimal(animal);
		setMyAnimal(user);
	}
	
	public void setMapCharacter(User user, Hunter player) {
		this.setMap("BattlePhase");
		this.prevMap="BossPhase";
		this.user=user;
		this.hunter=player;
		
		System.out.println("Log : prevMap : "+this.prevMap);
		
		Animal firstAni=player.getCage().get(0);
		
		setYourAnimal(firstAni);
		setMyAnimal(user);
	}
	
	public void setStartView(StartView startView) {
		this.startView=startView;
	}
	
	private void setYourAnimal(Animal animal) {
		System.out.println("Log : setYourAnimal");
		//애니멀이 무엇인가?
		String animalImagePath=animal.getImagePath();
		ImageIcon yourAnimalIcon = new ImageIcon(animalImagePath);
		yourAnimal=animal;
		
		yourLabel.setIcon(yourAnimalIcon);
		yourLabel.setLocation(150,20);
		yourLabel.setSize(yourAnimalIcon.getIconWidth(), yourAnimalIcon.getIconHeight());
		
		this.add(yourLabel);
		yourLabel.setVisible(true);
	}
	
	private void setMyAnimal(User user) {
		System.out.println("Log : setMyAnimal");
		this.user = user;
	
		Animal currentUserAnimal = user.getCage().get(userAnimalIndex);
		myAnimal = currentUserAnimal;
		String animalImagePath = currentUserAnimal.getImagePath();
		
		System.out.println("Log : currentUserAnimalLocation : "+animalImagePath);
		
		ImageIcon myAnimalIcon = new ImageIcon(animalImagePath);
		userLabel.setIcon(myAnimalIcon);
		userLabel.setLocation(10,50);
		userLabel.setSize(myAnimalIcon.getIconWidth(), myAnimalIcon.getIconHeight());
		
		this.add(userLabel);
		userLabel.setVisible(true);
		/*if(animal=="Jellyfish") {
		Animal currentUserAnimal = user.getCage().get(0);
		String animalImagePath = currentUserAnimal.getImagePath();
		
		ImageIcon myAnimalIcon = new ImageIcon(animalImagePath);
		userLabel.setIcon(myAnimalIcon);
		userLabel.setLocation(10,50);
		userLabel.setSize(myAnimalIcon.getIconWidth(), myAnimalIcon.getIconHeight());
		
		this.add(userLabel);
		userLabel.setVisible(true);
		}
		else if(animal == "Shark") {
			Animal currentUserAnimal = user.getCage().get(1);
			String animalImagePath = currentUserAnimal.getImagePath();
			
			ImageIcon myAnimalIcon = new ImageIcon(animalImagePath);
			userLabel.setIcon(myAnimalIcon);
			userLabel.setLocation(10,50);
			userLabel.setSize(myAnimalIcon.getIconWidth(), myAnimalIcon.getIconHeight());
			
			this.add(userLabel);
			userLabel.setVisible(true);
		}
		else if(animal == "Lion") {
			Animal currentUserAnimal = user.getCage().get(2);
			String animalImagePath = currentUserAnimal.getImagePath();
			
			ImageIcon myAnimalIcon = new ImageIcon(animalImagePath);
			userLabel.setIcon(myAnimalIcon);
			userLabel.setLocation(10,50);
			userLabel.setSize(myAnimalIcon.getIconWidth(), myAnimalIcon.getIconHeight());
			
			this.add(userLabel);
			userLabel.setVisible(true);
		}*/
	}
	
	/*
	 *  battle scenario
	 */

	
	/*
	 * key listener sets
	 */
	private void changeAnimal() {
		userAnimalIndex++;
		System.out.println("Log : users cageSize() : "+user.getCage().size());
		
		if(user.getCage().size()<=userAnimalIndex) {
			userAnimalIndex=0;
		}
		
		System.out.println("Log : userAnimalIndex : "+userAnimalIndex);
		
		setMyAnimal(user);
	}
	
	private void runFromBattle() {
		clip.close();
		this.userAnimalIndex=0;
		
		startView.moveNextMap(prevMap);
	}
	
	private void catchAnimal() {
		//catch
		if(controller.catchAnimal(yourAnimal)){
			System.out.println("잡기 성공!");
			user.cage.add(yourAnimal);
		}
		//not catch
		else {
			System.out.println("잡기 실패!");
		}
	}
	
	private void attackAnimal() {
		controller.calcHP(myAnimal, yourAnimal);
		
		System.out.println(myAnimal.getHp());
		System.out.println(yourAnimal.getHp());
		
//		if(hunter==null) {
//			
//		}
//		
//		
//		
//		if(yourAnimal.getHp()==0) {
//			hunterAnimalIndex++;
//			try {
//				Animal hunterAnimal=hunter.getCage().get(hunterAnimalIndex);
//			} catch(Exception e) {
//				System.out.println("Hunter has no animal");
//			}
//		}
//		else if(myAnimal.getHp()==0) {
//			runFromBattle();
//		}
	}
}