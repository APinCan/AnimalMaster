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
	
	User user;
	Hunter hunter;
	
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
	
	//동물
	ImageIcon myAnimalIcon;
	JLabel yourAnimalLabel = new JLabel();
	
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
			}
			else if(ticLabel.getX()==165) {
				System.out.println("defense");
			}
			else if(ticLabel.getX()==290) {
				System.out.println("change");
			}
			else if(ticLabel.getX()==415) {
				//startView.backToBossPhase("BossPhase");
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
		startView.moveBattlePhase(user, hunter);
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