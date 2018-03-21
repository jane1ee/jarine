package library;

import java.awt.Cursor;
import java.awt.Graphics;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class StarCount extends JFrame {
	// JFrame
	JFrame event, star;
	// JLabel
	JLabel eventLabel, starLabel;
	// 문제 발견, 문제 이미지
	Image eventImg, starImg;
	// 답 입력 텍스트 필드
	JTextField starField;
	// 마우스
	Image mouseImg;
	Cursor mouse;
	// 정답이면 +1
	int passCnt;
	
	public StarCount() {
		// null layout
		setLayout(null);
		
		// 문제1 발견 팝업
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
		new StarThread().start();

		
		// 마우스 커서
		Toolkit tk = Toolkit.getDefaultToolkit();
		mouseImg = new ImageIcon("img/cursor.png").getImage();
		Point point = new Point(0, 0);
		mouse = tk.createCustomCursor(mouseImg, point, "wonder");
		event.setCursor(mouse);
		
		
		// 문제1 힌트
		star = new JFrame();
		// 위치, 크기 설정
		star.setBounds(650, 350, 599, 400);
		// 창 크기 조절 : 불가능
		star.setResizable(false);
		// X창 없애기
		star.setUndecorated(true);
		// 이미지
		starLabel  = new JLabel();
		starImg = new ImageIcon("img/starCount.png").getImage();
		starLabel.setIcon(new ImageIcon(starImg));
		starLabel.setLocation(0, 0);
		
		
		// 정답 입력
		starField = new JTextField();
		starField.setBounds(200, 335, 200, 30);
		starField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputStar = starField.getText();
				if(inputStar.equals("13")) {
					starField.setText("정답입니다.");
					starField.setEditable(false);
					new CloseThread().start();
				} else {
					starField.setText("다시 생각해보세요.");
				}
			}
		});
		
		star.add(starField);

		
		// 진입하면 마우스 커서 변경
		star.addMouseListener(new MouseListener() {
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
				star.setCursor(mouse);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		

		star.add(starLabel);
		// X 버튼 없애기
		setUndecorated(true);
		
	}
	
	public StarCount(int passCnt) {
		this.passCnt = passCnt;
	}
	
	
	public int getPassCnt() {
		return passCnt;
	}

	public void setPassCnt(int passCnt) {
		this.passCnt = passCnt;
	}


	class StarThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(1000);  // milliseconds
				// 1초 뒤 발견문구 사라짐
				event.setVisible(false);
				// 문제 출력
				star.setVisible(true);
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
				setPassCnt(1);
				System.out.println(getPassCnt());
				star.setVisible(false);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
