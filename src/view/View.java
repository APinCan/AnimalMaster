package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public interface View {
	//�⺻���� ��Ű���� ���͸�
	public static final String path=System.getProperty("user.dir");
	//���� ũ�⸦ �����ָ鼭 �Է¹��� mapName�� jpg�̹����� ���
	public abstract void setMapSize(String mapName);
	//���� �׷��ִ�? �޼���
	public abstract void paintComponent(Graphics g);
	//����̹�����  Xũ�⸦ ������
	public abstract int getBackgroundImageX();
	//��� �̹����� Yũ�⸦ ������
	public abstract int getBackgroundImageY();
}
