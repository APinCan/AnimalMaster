package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BeachView extends JPanel implements View {
	private int viewX=640;
	private int viewY=960;
	private String mapName;
	ImageIcon icon;
	
	public BeachView() {
		
	}
	
	public BeachView(String mapName) {
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
