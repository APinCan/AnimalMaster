package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class StartView extends JFrame {
	private JPanel contentPane;
	JPanel buttonPanel = new JPanel();
	JLabel titleAnimalMaster = new JLabel("Animal Master");
	JButton btnStartGame = new JButton("START");
	JButton btnLoadGame = new JButton("LOAD");
	
	ImageIcon icon;
	TrainingCityView trainingCityView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		StartView frame = new StartView();

	}
	
	//초기화면  설정
	private void initTitle() {
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);

				setOpaque(false);
				super.paintComponent(g);
			}
		};
	}

	/**
	 * Create the frame.
	 */
	public StartView() {
		String path=System.getProperty("user.dir");
		String imagePath=path+"/src/background.jpg";
		icon=new ImageIcon(imagePath);
		Image img=icon.getImage();
		
		//초기화면
		initTitle();
		
		//frame정의
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(img.getWidth(null),img.getHeight(null)); //배경 이미지에 맞춰서 프레임의 크기 설정
		setSize(1280,640); //현재 배경이미지 크기
		setLocationRelativeTo(null); //프레임을 윈도우 가운데에 배치
		setContentPane(contentPane);
		contentPane.setLayout(null); //절대경로로 레이아웃배치
		
		//초기화면에 버튼이 들어가는 패널 정의
		buttonPanel.setBounds(0, 0, 1200, 600);
		buttonPanel.setBackground(new Color(255, 0, 0, 0));
		buttonPanel.setLayout(null);
		contentPane.add(buttonPanel);

		//초기화면에 나타나는 Animal Master 타이틀 정의
		titleAnimalMaster.setFont(new Font("굴림", Font.PLAIN, 25));
		titleAnimalMaster.setBounds(565, 149, 196, 61);
		buttonPanel.add(titleAnimalMaster);
		
		//gameStart버튼 정의
		btnStartGame.setBounds(584, 289, 164, 30);
		buttonPanel.add(btnStartGame);
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStartListener(e);
			}
		});
		
		//gameLoad버튼 정의
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoadListener(e);
			}
		});
		btnLoadGame.setBounds(584, 366, 164, 30);
		buttonPanel.add(btnLoadGame);
		
		setVisible(true);
	}
	

	
	
	/*
	 * 버튼 리스너 달기
	 */
	private void btnStartListener(ActionEvent e) {
		trainingCityView = new TrainingCityView();
		setContentPane(trainingCityView);
		setSize(trainingCityView.getBackgroundImageX(),trainingCityView.getbackgroundImageY());
		setLocationRelativeTo(null);
	}

	private void btnLoadListener(ActionEvent e) {
		
	}
}
