package library;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import library.StarCount.CloseThread;
import library.StarCount.StarThread;

public class LetterFromBook extends JFrame {
	// JFrame
	JFrame book, letter;
	// JLabel
	JLabel bookLabell;
	// 문제 발견, 문제 이미지
	Image bookImg;
	// 마우스
	Image mouseImg;
	Cursor mouse;
	// 정답 결과 리턴 필드
	boolean starry;
	
	public LetterFromBook() {
		// null layout
		setLayout(null);
		
		
		// 마우스 커서
		Toolkit tk = Toolkit.getDefaultToolkit();
		mouseImg = new ImageIcon("img/cursor.png").getImage();
		Point point = new Point(0, 0);
		mouse = tk.createCustomCursor(mouseImg, point, "wonder");
		setCursor(mouse);
		
		
		// 문제2 : 책 배경
		book = new JFrame();
		// 위치, 크기 설정
		book.setBounds(450, 250, 800, 550);
		// 창 크기 조절 : 불가능
		book.setResizable(false);
		// X창 없애기
		book.setUndecorated(true);
		// 이미지
		JLabel bookLabel  = new JLabel();
		bookImg = new ImageIcon("img/blankBook.png").getImage();
		bookLabel.setIcon(new ImageIcon(bookImg));
		bookLabel.setLocation(-100, -200);
		
		
		// 편지 버튼
		ImageIcon letterImg = new ImageIcon("img/letter.png");
		JButton letterBtn = new JButton(letterImg);
		letterBtn.setBounds(190, 125, 190, 316);
		letterBtn.setBorderPainted(false);
		letterBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 문제 2 출력
				Letter letter = new Letter();
				new CloseThread().start();
			}
		});
		
		
		// 진입하면 마우스 커서 변경
		book.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// 마우스 커서
				Toolkit tk = Toolkit.getDefaultToolkit();
				mouseImg = new ImageIcon("img/cursor.png").getImage();
				Point point = new Point(0, 0);
				mouse = tk.createCustomCursor(mouseImg, point, "wonder");
				book.setCursor(mouse);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		
		book.add(letterBtn);
		book.add(bookLabel);
		
		// X 버튼 없애기
		setUndecorated(true);
		book.setVisible(true);
	}
	
	
	
	class CloseThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(5000);  // milliseconds
				// 5초 뒤 창 닫기
				book.setVisible(false);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
