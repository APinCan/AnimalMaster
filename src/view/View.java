package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public interface View {
	//기본적인 패키지의 디렉터리
	public static final String path=System.getProperty("user.dir");
	//맵의 크기를 정해주면서 입력받은 mapName의 jpg이미지를 등록
	public abstract void setMapSize(String mapName);
	//맵을 그려주는? 메서드
	public abstract void paintComponent(Graphics g);
	//배경이미지의  X크기를 가져옴
	public abstract int getBackgroundImageX();
	//배경 이미지의 Y크기를 가져옴
	public abstract int getBackgroundImageY();
}
