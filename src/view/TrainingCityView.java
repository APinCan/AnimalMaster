package view;

import java.awt.Graphics;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import javafx.embed.swing.JFXPanel;

public class TrainingCityView extends JPanel {
	ImageIcon icon;
	Image img;
	private int viewX;
	private int viewY;
	private int[] mapArray; //맵의 크기를 제한
	String path;

	//생성자 부분은 다른뷰들하고 공통되는부분
	public TrainingCityView() {
		new JFXPanel();
		path=System.getProperty("user.dir");
		String imagePath=path+"/src/trainingCity.jpg";
		icon=new ImageIcon(imagePath);
		img=icon.getImage();
		
		this.viewX=640;
		this.viewY=320;
		setSize(640, 320);
	}
	
	//이 부분도 다른뷰들하고 공통되는 부분
	public void paintComponent(Graphics g) {
		g.drawImage(icon.getImage(), 0, 0, null);
		
		setOpaque(false);
		super.paintComponent(g);
	}
	
	public int getbackgroundImageY() {
		return img.getHeight(null);
	}
	
	public int getBackgroundImageX() {
		return img.getWidth(null);
	}
	
}
