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
	JFrame event, book, letter;
	// JLabel
	JLabel eventLabel, bookLabel, letterLabel;
	// 문제 발견, 문제 이미지
	Image eventImg, bookImg;
	// 편지이미지와 버튼
	ImageIcon letterImg;
	JButton letterBtn;
	// 답 입력 텍스트 필드
	JTextField letterField;
	// 마우스
	Image mouseImg;
	Cursor mouse;
	// 정답 결과 리턴 필드
	boolean starry;
	
	public LetterFromBook() {
		// null layout
		setLayout(null);
		
		// 문제2 발견 팝업
		event = new JFrame();
		// 위치, 크기 설정
		event.setBounds(650, 510, 542, 81);
		// 창 크기 조절 : 불가능
		event.setResizable(false);
		// X창 없애기
		event.setUndecorated(true);
		// 이미지
		eventLabel  = new JLabel();
		eventImg = new ImageIcon("img/event.png").getImage();
		eventLabel.setIcon(new ImageIcon(eventImg));
		eventLabel.setLocation(0, 0);
		event.add(eventLabel);
		// 출력
		event.setVisible(true);
		// 시간 제한 쓰레드
		new BookThread().start();

		
		// 마우스 커서
		Toolkit tk = Toolkit.getDefaultToolkit();
		mouseImg = new ImageIcon("img/cursor.png").getImage();
		Point point = new Point(0, 0);
		mouse = tk.createCustomCursor(mouseImg, point, "wonder");
		event.setCursor(mouse);
		
		
		// 문제2 : 책 배경
		book = new JFrame();
		// 위치, 크기 설정
		book.setBounds(450, 250, 800, 550);
		// 창 크기 조절 : 불가능
		book.setResizable(false);
		// X창 없애기
		book.setUndecorated(true);
		// 이미지
		bookLabel  = new JLabel();
		bookImg = new ImageIcon("img/blankBook.png").getImage();
		bookLabel.setIcon(new ImageIcon(bookImg));
		bookLabel.setLocation(-100, -200);
		
		
		// 편지 버튼
		letterImg = new ImageIcon("img/letter.png");
		letterBtn = new JButton(letterImg);
		letterBtn.setBounds(190, 125, 190, 316);
		letterBtn.setBorderPainted(false);
		letterBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 문제 2 출력
				Letter letter = new Letter();
				if(letter.trueAnswer()) {
					starry = true;
				}
			}
		});
		book.add(letterBtn);
		
		
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
		

		book.add(bookLabel);
		// X 버튼 없애기
		setUndecorated(true);
	}

	

	class BookThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(1000);  // milliseconds
				// 1초 뒤 발견문구 사라짐
				event.setVisible(false);
				// 문제 출력
				book.setVisible(true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// BreakLibrary에 값을 리턴
	public boolean trueAnswer() {
		
		return starry;
	}
	
	
	class CloseThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(3000);  // milliseconds
				// 정답이면 3초 뒤 창 닫기
				letter.setVisible(false);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
