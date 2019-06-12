package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import model.*;

public class GameSaveView extends JFrame {
	DAO dao = new DAO();
	Map<Integer, String> loadList = dao.printList();
	
	private JPanel contentPane;
	JButton button_1 = new JButton("Save1");
	JButton button_2 = new JButton("Save2");
    JButton button_3 = new JButton();
    JButton button_4 = new JButton();
    JLabel label_1 = new JLabel("Animal Master Game1");
    JLabel label_2 = new JLabel("Animal Master Game2");
	
	ImageIcon icon;
	Image img;
	String path;
	
	ImageIcon button;
	Image b_img;
	String b_path;

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
        
        //save2
        contentPane.add(button_2);
        button_2.setBounds(450, 150, 100, 40);
        button_2.setBackground(Color.white);
        //load1
        contentPane.add(button_3);
        button_3.setBounds(100, 10, 100, 40);
        button_3.setBackground(Color.white);
        
        //load2
        contentPane.add(button_4);
        button_4.setBounds(100, 150, 100, 40);
        button_4.setBackground(Color.white);
     
        //game1
        contentPane.add(label_1);
        label_1.setBounds(265, 10, 200, 40);     

        //game2
        contentPane.add(label_2);
        label_2.setBounds(265, 150, 200, 40);

        setSize(img.getWidth(null),img.getHeight(null));
        setResizable(false);
        setVisible(true);
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



}