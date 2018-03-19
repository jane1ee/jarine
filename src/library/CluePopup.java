package library;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CluePopup extends JFrame {
	// 필드 : 배경
	JScrollPane scrollPane;
	Image bgImg;
	JPanel background;
	// 필드 : 마우스
	Image mouseImg;
	Cursor mouse;
	
	public CluePopup() {
		// 마우스 커서
		Toolkit tk = Toolkit.getDefaultToolkit();
		mouseImg = new ImageIcon("img/cursor.png").getImage();
		Point point = new Point(0, 0);
		mouse = tk.createCustomCursor(mouseImg, point, "wonder");
		setCursor(mouse);
		
		// 힌트 팝업창 배경화면 : 별 헤는 밤 : '별'의 갯수(13개)
		bgImg = new ImageIcon("img/clue.png").getImage();
		background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(bgImg, 0, 0, 542, 80, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);
		// X 버튼 없애기
		setUndecorated(true);
		new ClueThread().start();
	}

	

	class ClueThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(5000);  // milliseconds
				// 5초 정도 뒤에 '별 헤는 밤 '창 사라짐
				setVisible(false);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
