package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BattlePhase extends JPanel implements View {
	private int viewX=556;
	private int viewY=371;
	private String mapName;
	ImageIcon icon;
	
	public BattlePhase() {
		
	}
	
	public BattlePhase(String mapName) {
		this.mapName=mapName;
		setMapSize(mapName);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(icon.getImage(), 0, 0, null);
		
		setOpaque(false);
		super.paintComponent(g);
	}
	
	@Override
	public void setMapSize(String mapName) {
		String imagePath=path+"/src/"+mapName+".jpg";
		icon=new ImageIcon(imagePath);
		
		setSize(viewX, viewY);
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
		
	}
}
