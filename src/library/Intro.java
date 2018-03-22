package library;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Intro extends  JFrame {
	// 배경
	JScrollPane scrollPane;
	JPanel background;
    Image gifBg;
    Toolkit tk;
    // 이름 입력창
    JFrame namefield;
    JLabel nameLabel;
    Image nameImg;
    JTextField nameInput;
	
	public Intro() {
		IntroFrame();	// 메소드 실행
	}
	

	
	public void IntroFrame() {			// 여기부터 소스 복붙
	      tk = Toolkit.getDefaultToolkit();
	      gifBg = tk.getImage("Img/yourname.gif");

	      background = new JPanel() {
	         public void paint(Graphics g) {
	            if (gifBg == null)
	               return;

	            g.drawImage(gifBg, 0, 0, this);
	            setOpaque(false);
	            super.paintComponent(g);
	         }

	      };
	      
		this.add(background);
		scrollPane = new JScrollPane(background);
		this.add(scrollPane);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1280, 800);
		this.setLocation(300, 130);
		
		this.setLocationRelativeTo(null);
		this.setTitle("Insert your name");
		this.setIconImage(new ImageIcon("img/favicon.jpg").getImage());
		this.setResizable(false);
		this.setVisible(true);
		  
		
		namefield = new JFrame();
		namefield.setBounds(570, 456, 713, 185);
		namefield.setResizable(false);
		namefield.setUndecorated(true);
		namefield.setOpacity((float) 0.7);
		nameLabel = new JLabel();
		nameImg = new ImageIcon("img/namefield.png").getImage();
		nameLabel.setIcon(new ImageIcon(nameImg));
		nameLabel.setLocation(0, 0);
		Font commonFont = new Font("맑은 고딕", Font.BOLD, 30);
		nameInput = new JTextField();
		nameInput.setBounds(260, 118, 200, 50);
		nameInput.setFont(commonFont);
		nameInput.setBackground(Color.BLACK);
		nameInput.setForeground(Color.white);
		nameInput.setOpaque(isOpaque());
		nameInput.setBorder(null);
		nameInput.setHorizontalAlignment(JTextField.CENTER);
		namefield.add(nameInput);
		namefield.add(nameLabel);
		namefield.setIconImage(new ImageIcon("img/favicon.jpg").getImage());
		namefield.setVisible(true);
	   }
	
	public static void main(String[] args) {
		Intro inputName = new Intro();
	}
}
