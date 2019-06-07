package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Font;

public class StartView extends JFrame {
	private JPanel contentPane;
	JPanel buttonPanel = new JPanel();
	JLabel titleAnimalMaster = new JLabel("Animal Master");
	JButton btnStartGame = new JButton("START");
	JButton btnLoadGame = new JButton("LOAD");
	
	ImageIcon icon;
	TrainingCityView trainingCityView;
	String path;
	Clip clip; //배경음악재생위해 필요

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
		setTitle("Animal Mater!");
	}

	/**
	 * Create the frame.
	 */
	public StartView() {
		path=System.getProperty("user.dir");
		String imagePath=path+"/src/Image/background.jpg";
		icon=new ImageIcon(imagePath);
		Image img=icon.getImage();
		
		//초기화면
		initTitle();
		//배경음악재생
		playBackgroundMusic();
		//메뉴만들기
		createMenu();
		
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
	
	//배경음악재생
	public void playBackgroundMusic() {
		String mediaPath=path+"\\src\\Music\\openingWAV.wav";
		try {
			AudioInputStream audioIn= AudioSystem.getAudioInputStream(new File(mediaPath).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.loop(3);
			clip.start();
		} catch(Exception e) {
			System.out.println("Error!");
		}
	}
	
	//메뉴만들기
	void createMenu() {
		JMenuBar mb = new JMenuBar(); // 메뉴바 생성
		JMenuItem [] menuItem = new JMenuItem [2];
		String[] itemTitle = {"SAVE", "EXIT"};
		JMenu screenMenu = new JMenu("Menu");

		for(int i=0; i<menuItem.length; i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]); 
			menuItem[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					menuActionListener(e);
				}		
			}); 
			screenMenu.add(menuItem[i]);
		}
		
		mb.add(screenMenu); 
		setJMenuBar(mb); // 메뉴바를 프레임에 부착
	}
	
	/*
	 * 버튼 리스너 달기
	 */
	private void btnStartListener(ActionEvent e) {
//		trainingCityView = new TrainingCityView();
//		setContentPane(trainingCityView);
//		setSize(trainingCityView.getBackgroundImageX(),trainingCityView.getbackgroundImageY());
//		setLocationRelativeTo(null);
		clip.close();
		GeneralView trainingCityView =new GeneralView("TrainingCityView");
		setContentPane(trainingCityView);
		setSize(trainingCityView.getBackgroundImageX(),trainingCityView.getBackgroundImageY());
		setLocationRelativeTo(null);
	}

	private void btnLoadListener(ActionEvent e) {
		
	}
	
	//액션 이벤트 달기
	private void menuActionListener(ActionEvent e) {
		String message = e.getActionCommand(); 
		switch(message) { // 메뉴 아이템의 종류 구분
			case "SAVE":
				//세이브할때 처리할 것
				break;
			case "EXIT" :
				System.exit(0);
		}
		
		contentPane.repaint();
	}
}
