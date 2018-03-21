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

import library.BreakLibrary.LetterThread;


public class Letter extends JFrame {
	// JFrame
	JFrame book, letter;
	// JLabel
	JLabel bookLabel, letterLabel;
	// 문제 발견, 문제 이미지
	Image bookImg, letterImg;
	// 편지 버튼
	ImageIcon letterBtnImg;
	JButton letterBtn;
	// 답 입력 텍스트 필드
	JTextField letterField;
	// 마우스
	Toolkit tk;
	Image mouseImg;
	Cursor mouse;
	Point point;
	// 정답 결과 리턴 필드
	LetterThread letterFlag;
	

	
	public Letter(LetterThread letterFlag) {
		this();
		this.letterFlag = letterFlag;
	}
	
	public Letter() {
		// null layout
		setLayout(null);
		
		// 문제1 인트로 : 책 배경
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
		letterBtnImg = new ImageIcon("img/letter.png");
		letterBtn = new JButton(letterBtnImg);
		letterBtn.setBounds(190, 125, 190, 316);
		letterBtn.setBorderPainted(false);
		letterBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StarryThread().start();
			}
		});
		letterBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// 버튼 마우스 오프 : 커서 원래대로 돌아오기
				tk = Toolkit.getDefaultToolkit();
				mouseImg = new ImageIcon("img/cursor.png").getImage();
				point = new Point(0, 0);
				mouse = tk.createCustomCursor(mouseImg, point, "wonder");
				book.setCursor(mouse);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// 버튼 마우스 온 : 커서 변경
				tk = Toolkit.getDefaultToolkit();
				mouseImg = new ImageIcon("img/check.png").getImage();
				point = new Point(20, 20);
				mouse = tk.createCustomCursor(mouseImg, point, "find");
				book.setCursor(mouse);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				book.dispose();
			}
		});
		
		
		book.add(letterBtn);
		book.add(bookLabel);
		
		// X 버튼 없애기
		setUndecorated(true);
		book.setVisible(true);
		
		
		
		// 문제1
		letter = new JFrame();
		// 위치, 크기 설정
		letter.setBounds(650, 350, 700, 385);
		// 창 크기 조절 : 불가능
		letter.setResizable(false);
		// X창 없애기
		letter.setUndecorated(true);
		// 이미지
		letterLabel  = new JLabel();
		letterImg = new ImageIcon("img/letterImg.png").getImage();
		letterLabel.setIcon(new ImageIcon(letterImg));
		letterLabel.setLocation(0, 0);
		letterLabel.addMouseListener(new MouseListener() {
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
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				letter.dispose();
			}
		});
		
		// 정답 입력
		letterField = new JTextField();
		letterField.setBounds(265, 285, 150, 30);
		letterField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputSubject = letterField.getText();
				if(inputSubject.equals("starry")) {
					letterField.setText("정답입니다.");
					letterField.setEditable(false);
					new CloseThread().start();
					letterFlag.letterMethod(true);
				} else {
					letterFlag.letterMethod(false);
					letterField.setText("다시 생각해보세요.");
				}
			}
		});
		
		
		letter.add(letterField);
		letter.add(letterLabel);
		// X 버튼 없애기
		setUndecorated(true);
		

		// 마우스 커서
		tk = Toolkit.getDefaultToolkit();
		mouseImg = new ImageIcon("img/cursor.png").getImage();
		point = new Point(0, 0);
		mouse = tk.createCustomCursor(mouseImg, point, "wonder");
		book.setCursor(mouse);
		letter.setCursor(mouse);
	}
	
	
	class StarryThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(1);  // milliseconds
				// 1초 뒤 문제 출력
				letter.setVisible(true);
				// 책 배경 창 닫기
				book.dispose();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	


	class CloseThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(3000);  // milliseconds
				// 정답이면 3초 뒤 창 닫기
				letter.dispose();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
