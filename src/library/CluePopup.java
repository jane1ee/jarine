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
		
		// 문제1의 힌트 발견 팝업 : 배경화면
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
				Thread.sleep(900);  // milliseconds
				// 0.9초 정도 뒤에 '이건 뭐지 '창 사라짐
				setVisible(false);
				// '별 헤는 밤' 힌트 출력
				PoetHint poet = new PoetHint();
				poet.setLocation(300, 130);
				poet.setSize(798, 532);
				poet.setResizable(false);
				poet.setVisible(true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
