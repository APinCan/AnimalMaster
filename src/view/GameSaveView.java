package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class GameSaveView extends JFrame {

	private JPanel contentPane;
	JButton button_1 = new JButton("Save1");
	JButton button_2 = new JButton("Save2");
    JButton button_3 = new JButton("Load1");
    JButton button_4 = new JButton("Load2");
    JLabel label_1 = new JLabel("Animal Master Game1");
    JLabel label_2 = new JLabel("Animal Master Game2");
	
	ImageIcon icon;
	Image img;
	String path;
	
	ImageIcon button;
	Image b_img;
	String b_path;
	
	StartView startView;
	Controler controller=Controler.getInstance();

	public void showSaveView() {
		this.setVisible(true);
	}
	
	public GameSaveView() {
		path=System.getProperty("user.dir");
		String imagePath=path+"/src/Image/GameSaveView.jpg";
		icon=new ImageIcon(imagePath);
		img=icon.getImage();
		
		setTitle("Save");
        // 주의, 여기서 setDefaultCloseOperation() 정의를 하지 말아야 한다
        // 정의하게 되면 새 창을 닫으면 모든 창과 프로그램이 동시에 꺼진다
        
       initTitle();
       contentPane.setLayout(null);
        setContentPane(contentPane);
       
        //save1
        contentPane.add(button_1);
        button_1.setBounds(450, 10, 100, 40);
        button_1.setBackground(Color.white);
        button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSaveCurrentInfo(e);
			}
        	
        });
        
        //save2
        contentPane.add(button_2);
        button_2.setBounds(450, 150, 100, 40);
        button_2.setBackground(Color.white);
        button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSaveCurrentInfo(e);
			}
        	
        });
        
        //load1
        contentPane.add(button_3);
        button_3.setBounds(100, 10, 100, 40);
        button_3.setBackground(Color.white);
        button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnLoadSavedInfo(e);
			}
        	
        });
        
        //load2
        contentPane.add(button_4);
        button_4.setBounds(100, 150, 100, 40);
        button_4.setBackground(Color.white);
        button_4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnLoadSavedInfo(e);
			}
        	
        });
     
        //game1
        contentPane.add(label_1);
        label_1.setBounds(265, 10, 200, 40);     

        //game2
        contentPane.add(label_2);
        label_2.setBounds(265, 150, 200, 40);

        setSize(img.getWidth(null),img.getHeight(null));
        setResizable(false);
//        setVisible(true);
    	setLocationRelativeTo(null);
	}
	
	private void initTitle() {
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
	}
	
	public void setStartView(StartView startView) {
		this.startView=startView;
	}
	
	/*
	 * listener implementation
	 */
	
	//game save
	private void btnSaveCurrentInfo(ActionEvent e){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		
		String currentTime = dateFormat.format(time);
		String printTime = "Time : "+currentTime;
		
		//save1
		if(e.getSource().equals(button_1)) {
			//current information save
			label_1.setText(printTime);
			
				try {
					controller.saveGame(startView.getUser(), 1);
				} catch (SQLException e1) {
					System.out.println("SAVE FAIL 1");
				}
		
				
			
		}
		//save2
		else {
			//current information save
			label_2.setText(printTime);
			try {
				controller.saveGame(startView.getUser(), 2);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("SAVE FAIL 2");
			}
		}
		
		this.dispose();
	}
	
	//game load
	private void btnLoadSavedInfo(ActionEvent e) {
		//load1
		if(e.getSource().equals(button_3)) {
			//load saved information
			startView.moveNextMap("TrainingCityView");

			try {
				startView.loadUser(controller.loadGame(1));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("LOAD FAIL 1");
			}
		}
		//load2
		else {
			//load saved information
			startView.moveNextMap("TrainingCityView");
			
			try {
				startView.loadUser(controller.loadGame(2));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("LOAD FAIL 2");
			}
		}
		
		this.dispose();
	}
}