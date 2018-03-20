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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import library.StarCount.CloseThread;
import library.StarCount.StarThread;

public class Letter extends JFrame {
	// JFrame
	JFrame letter;
	// JLabel
	JLabel letterLabel;
	// 문제 발견, 문제 이미지
	Image letterImg;
	// 답 입력 텍스트 필드
	JTextField letterField;
	// 마우스
	Image mouseImg;
	Cursor mouse;
	// 정답 결과 리턴 필드
	boolean fromLetter;
	
	public Letter() {
		// null layout
		setLayout(null);
		
		// 문제1 힌트
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
		
		// 정답 입력
		letterField = new JTextField();
		letterField.setBounds(210, 300, 200, 30);
		letterField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputSubject = letterField.getText();
				if(inputSubject.equals("별")) {
					letterField.setText("정답입니다.");
					letterField.setEditable(false);
					new CloseThread().start();
				} else {
					fromLetter = false;
					letterField.setText("다시 생각해보세요.");
				}
			}
		});
		
		letter.add(letterField);
		
		
		// 진입하면 마우스 커서 변경
		letter.addMouseListener(new MouseListener() {
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
				letter.setCursor(mouse);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		

		letter.add(letterLabel);
		// X 버튼 없애기
		setUndecorated(true);
		letter.setVisible(true);
		
		
		// 마우스 커서
		Toolkit tk = Toolkit.getDefaultToolkit();
		mouseImg = new ImageIcon("img/cursor.png").getImage();
		Point point = new Point(0, 0);
		mouse = tk.createCustomCursor(mouseImg, point, "wonder");
		letter.setCursor(mouse);

	}

	
	
	// LetterFromBook에 값을 리턴
	public boolean trueAnswer() {
		return fromLetter;
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
