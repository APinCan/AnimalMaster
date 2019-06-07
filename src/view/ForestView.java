package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ForestView extends JPanel implements View {
	private int viewX=768;
	private int viewY=704;
	private String mapName;
	ImageIcon icon;

	public ForestView() {
		
	}
	
	public ForestView(String mapName) {
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
	public void setMapSize(String mapName) {
		// TODO Auto-generated method stub
		String imagePath=path+"/src/Image/"+mapName+".jpg";
		icon=new ImageIcon(imagePath);
		
		setSize(viewX, viewY);
	}

	@Override
	public void playBackgroundMusic() {
		// TODO Auto-generated method stub
		
	}

}
