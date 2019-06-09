package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Font;

public class StartView extends JFrame{
	private JPanel contentPane;
	JPanel buttonPanel = new JPanel();
	JLabel titleAnimalMaster = new JLabel("Animal Master");
	JButton btnStartGame = new JButton("START");
	JButton btnLoadGame = new JButton("LOAD");
	JPanel characterPanel;
	JLabel charLabel;
	
	ImageIcon icon;
	TrainingCityView trainingCityView;
	String path;
	Clip clip; //�������������� �ʿ�
	
	//ĳ���Ͷ����� �߰�
//	ImageIcon characterImageIcon;
//	Image charImage;
//	String charPath;
//	private static final String LEFT="Left";
//	private static final String RIGHT="Right";
//	private static final String UP="Up";
//	private static final String DOWN="Down";
//	
//	private Action left= new AbstractAction(LEFT) {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			charLabel.setLocation(charLabel.getX()-5, charLabel.getY());
//		}
//	};
//	
//	private Action right=new AbstractAction(RIGHT) {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			charLabel.setLocation(charLabel.getX()+5, charLabel.getY());
//		}
//	};
//	
//	private Action up = new AbstractAction(UP) {
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			// TODO Auto-generated method stub
//			charLabel.setLocation(charLabel.getX(),charLabel.getY()-5);
//		}
//	};
//	
//	private Action down = new AbstractAction(DOWN) {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			// TODO Auto-generated method stub
//			charLabel.setLocation(charLabel.getX(), charLabel.getY()+5);
//		}	
//	};
	

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
		//ĳ���ͼ���
//		setCharacter();
		
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
		
		characterPanel = new JPanel();
		characterPanel.setBounds(0, 0, 1252, 570);
		characterPanel.setBackground(new Color(255, 0, 0, 0));
		contentPane.add(characterPanel);
		
		//ĳ�����߰�
//		charLabel=new JLabel(characterImageIcon);
//		charLabel.setEnabled(true);
//		
//		Dimension size =  charLabel.getPreferredSize();
//		charLabel.setLocation(50, 50);
//		charLabel.setSize(size.width, size.height);
//		contentPane.add(charLabel);
		
		setVisible(true);		
//		contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LEFT);
//		contentPane.getActionMap().put(LEFT, left);
//		contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), RIGHT);
//		contentPane.getActionMap().put(RIGHT, right);
//		contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UP);
//		contentPane.getActionMap().put(UP, up);
//		contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN);
//		contentPane.getActionMap().put(DOWN,  down);
	}
	
//	public void setCharacter() {
//		charPath=path+"/src/Image/Hunter.jpg";
//		characterImageIcon=new ImageIcon(charPath);
//		charImage=characterImageIcon.getImage();
//	}
	
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
		clip.close();
		GeneralView trainingCityView =new GeneralView("TrainingCityView");
		setContentPane(trainingCityView);
		setSize(trainingCityView.getBackgroundImageX(),trainingCityView.getBackgroundImageY());

		trainingCityView.requestFocus();
		
		setLocationRelativeTo(null);
	}

	private void btnLoadListener(ActionEvent e) {
		
	}
	
	//�޴� �̺�Ʈ �ޱ�
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
