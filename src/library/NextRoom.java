package library;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NextRoom extends  JFrame {
	// 배경
	JScrollPane scrollPane;
	JPanel background;
    Image gifBg;
    Toolkit tk;
    
	
	public NextRoom() {
		OutroFrame();	// 메소드 실행
	}
	

	
	public void OutroFrame() {			// 여기부터 소스 복붙
	      tk = Toolkit.getDefaultToolkit();
	      gifBg = tk.getImage("Img/opendoor.gif");

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
	      this.setTitle("to the Next Room");
	      this.setIconImage(new ImageIcon("img/favicon.jpg").getImage());
	      this.setResizable(false);
	      this.setVisible(true);

	   }
}
