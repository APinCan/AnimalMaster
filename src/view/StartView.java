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
	Clip clip; //�������������� �ʿ�

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		StartView frame = new StartView();

	}
	
	//�ʱ�ȭ��  ����
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
		
		//�ʱ�ȭ��
		initTitle();
		//����������
		playBackgroundMusic();
		//�޴������
		createMenu();
		
		//frame����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(img.getWidth(null),img.getHeight(null)); //��� �̹����� ���缭 �������� ũ�� ����
		setSize(1280,640); //���� ����̹��� ũ��
		setLocationRelativeTo(null); //�������� ������ ����� ��ġ
		setContentPane(contentPane);
		contentPane.setLayout(null); //�����η� ���̾ƿ���ġ
		
		//�ʱ�ȭ�鿡 ��ư�� ���� �г� ����
		buttonPanel.setBounds(0, 0, 1200, 600);
		buttonPanel.setBackground(new Color(255, 0, 0, 0));
		buttonPanel.setLayout(null);
		contentPane.add(buttonPanel);

		//�ʱ�ȭ�鿡 ��Ÿ���� Animal Master Ÿ��Ʋ ����
		titleAnimalMaster.setFont(new Font("����", Font.PLAIN, 25));
		titleAnimalMaster.setBounds(565, 149, 196, 61);
		buttonPanel.add(titleAnimalMaster);
		
		//gameStart��ư ����
		btnStartGame.setBounds(584, 289, 164, 30);
		buttonPanel.add(btnStartGame);
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStartListener(e);
			}
		});
		
		//gameLoad��ư ����
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoadListener(e);
			}
		});
		btnLoadGame.setBounds(584, 366, 164, 30);
		buttonPanel.add(btnLoadGame);
		
		setVisible(true);
	}
	
	//����������
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
	
	//�޴������
	void createMenu() {
		JMenuBar mb = new JMenuBar(); // �޴��� ����
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
		setJMenuBar(mb); // �޴��ٸ� �����ӿ� ����
	}
	
	/*
	 * ��ư ������ �ޱ�
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
	
	//�׼� �̺�Ʈ �ޱ�
	private void menuActionListener(ActionEvent e) {
		String message = e.getActionCommand(); 
		switch(message) { // �޴� �������� ���� ����
			case "SAVE":
				//���̺��Ҷ� ó���� ��
				break;
			case "EXIT" :
				System.exit(0);
		}
		
		contentPane.repaint();
	}
}
