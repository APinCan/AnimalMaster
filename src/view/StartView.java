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
	ImageIcon icon;
	TrainingCityView trainingCityView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		StartView frame = new StartView();
		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StartView frame = new StartView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public StartView() {
		String path=System.getProperty("user.dir");
		String imagePath=path+"/src/background.jpg";
		icon=new ImageIcon(imagePath);
		Image img=icon.getImage();
		
		//�ʱ�ȭ��
		initTitle();
		
		//frame����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(img.getWidth(null),img.getHeight(null)); //��� �̹����� ���缭 �������� ũ�� ����
		setSize(1280,640); //���� ����̹��� ũ��
		setLocationRelativeTo(null); //�������� ������ ����� ��ġ
		setContentPane(contentPane);
		contentPane.setLayout(null); //�����η� ���̾ƿ���ġ
		
		//�ʱ�ȭ�鿡 ��ư�� ���� �г� ����
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 0, 1200, 600);
		buttonPanel.setBackground(new Color(255, 0, 0, 0));
		contentPane.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		//�ʱ�ȭ�鿡 ��Ÿ���� Animal Master Ÿ��Ʋ ����
		JLabel titleAnimalMaster = new JLabel("Animal Master");
		titleAnimalMaster.setFont(new Font("����", Font.PLAIN, 25));
		titleAnimalMaster.setBounds(565, 149, 196, 61);
		buttonPanel.add(titleAnimalMaster);
		
		//gameStart��ư ����
		JButton btnStartGame = new JButton("START");
		btnStartGame.setBounds(584, 289, 164, 30);
		buttonPanel.add(btnStartGame);
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trainingCityView = new TrainingCityView();
				setContentPane(trainingCityView);
				setSize(trainingCityView.getBackgroundImageX(),trainingCityView.getbackgroundImageY());
				setLocationRelativeTo(null);
			}
		});
		
		//gameLoad��ư ����
		JButton btnLoadGame = new JButton("LOAD");
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLoadGame.setBounds(584, 366, 164, 30);
		buttonPanel.add(btnLoadGame);
		
		setVisible(true);
	}
	
	private void initTitle() {
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
			
				g.drawImage(icon.getImage(), 0, 0, null);
				
//			     Approach 2: Scale image to size of component
//                Dimension d = getSize();
//                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
//                 Approach 3: Fix the image position in the scroll pane
//                 Point p = scrollPane.getViewport().getViewPosition();
//                 g.drawImage(icon.getImage(), p.x, p.y, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
	}
}
