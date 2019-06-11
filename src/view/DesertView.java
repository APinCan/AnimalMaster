package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DesertView extends JPanel implements View {
	private int viewX=736;
	private int viewY=496;
	private String mapName;
	ImageIcon icon;
	
	
	public DesertView() {
		
	}
	
	public DesertView(String mapName) {
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
		// TODO Auto-generated method stub
		String imagePath=path+"/src/Image/"+mapName+".jpg";
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
