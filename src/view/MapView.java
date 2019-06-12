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

public abstract class MapView extends JPanel {
	public static final String LEFT="Left";
	public static final String RIGHT="Right";
	public static final String UP="Up";
	public static final String DOWN="Down";
	public static final String path=System.getProperty("user.dir");
	
	protected int viewX=0;
	protected int viewY=0;
	protected String mapName;
	protected ImageIcon icon;
	protected Clip clip;
	
	//캐릭터관련
	JLabel charLabel;
	
	private Action left= new AbstractAction(LEFT) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int locationX = charLabel.getX();
			int locationY = charLabel.getY();
			
			limitViewBoundary(locationX, locationY);
			moveNextView();
			
			System.out.println("x :"+charLabel.getX()+", y : "+charLabel.getY());
		}
	};
	
	private Action right=new AbstractAction(RIGHT) {
		@Override
		public void actionPerformed(ActionEvent e) {
			int locationX = charLabel.getX();
			int locationY = charLabel.getY();
			
			limitViewBoundary(locationX, locationY);
			moveNextView();
			
			System.out.println("x :"+charLabel.getX()+", y : "+charLabel.getY());
		}
	};
	
	private Action up = new AbstractAction(UP) {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int locationX = charLabel.getX();
			int locationY = charLabel.getY();
			
			limitViewBoundary(locationX, locationY);
			moveNextView();
			
			System.out.println("x :"+charLabel.getX()+", y : "+charLabel.getY());
		}
	};
	
	private Action down = new AbstractAction(DOWN) {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int locationX = charLabel.getX();
			int locationY = charLabel.getY();
			
			limitViewBoundary(locationX, locationY);
			moveNextView();
			
			System.out.println("x :"+charLabel.getX()+", y : "+charLabel.getY());
		}	
	};
	
	public MapView() {
		setKeyListener();
		this.setLayout(null);
		
		//캐릭터추가
		String charPath=path+"/src/Image/Hunter.jpg";
		ImageIcon characterImageIcon=new ImageIcon(charPath);
		Image charImage = characterImageIcon.getImage();
		
		charLabel = new JLabel(characterImageIcon);
		charLabel.setEnabled(true);
		
		Dimension size = charLabel.getPreferredSize();
		charLabel.setLocation(50, 50);
		charLabel.setSize(size.width, size.height);
		
		this.add(charLabel);
		charLabel.setVisible(true);
	}
	
	private void setKeyListener() {
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LEFT);
		this.getActionMap().put(LEFT, left);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), RIGHT);
		this.getActionMap().put(RIGHT, right);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UP);
		this.getActionMap().put(UP, up);
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN);
		this.getActionMap().put(DOWN,  down);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(icon.getImage(), 0, 0, null);
		
		setOpaque(false);
		super.paintComponent(g);
	}
	
	public int getBackgroundImageX() {
		return this.viewX;
	}
	
	public int getBackgroundImageY() {
		return this.viewY;
	}
	
	public void playBackgroundMusic() {
		try {
			clip.close();
		} catch(NullPointerException e) {
			System.out.println("설정된 bgm이 없음");
		}
		
		String musicName="";
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
	
	private void limitViewBoundary(int x, int y) {
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
	
	protected abstract void moveNextView();
}
