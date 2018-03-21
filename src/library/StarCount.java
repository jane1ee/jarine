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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import library.BreakLibrary.StarThread;

public class StarCount extends JFrame {
	// JFrame
	JFrame star;
	// JLabel
	JLabel starLabel;
	// 문제 발견, 문제 이미지
	Image starImg;
	// 답 입력 텍스트 필드
	JTextField starField;
	// 마우스
	Image mouseImg;
	Cursor mouse;
	// 쓰레드로 정답 넘기기
	StarThread starFlag;


	public StarCount(StarThread starFlag){
		this();
		this.starFlag = starFlag;
	}
	
	public StarCount() {
		// null layout
		setLayout(null);

		// 마우스 커서
		Toolkit tk = Toolkit.getDefaultToolkit();
		mouseImg = new ImageIcon("img/cursor.png").getImage();
		Point point = new Point(0, 0);
		mouse = tk.createCustomCursor(mouseImg, point, "wonder");
		setCursor(mouse);

		// 문제1 힌트
		star = new JFrame();
		// 위치, 크기 설정
		star.setBounds(650, 350, 599, 400);
		// 창 크기 조절 : 불가능
		star.setResizable(false);
		// X창 없애기
		star.setUndecorated(true);
		// 이미지
		starLabel = new JLabel();
		starImg = new ImageIcon("img/starCount.png").getImage();
		starLabel.setIcon(new ImageIcon(starImg));
		starLabel.setLocation(0, 0);

		// 정답 입력
		starField = new JTextField();
		starField.setBounds(230, 335, 150, 30);
		starField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputStar = starField.getText();
				if (inputStar.equals("13")) {
					starField.setText("정답입니다.");
					starField.setEditable(false);
					new CloseThread().start();
					starFlag.starMethod(true);
					
				} else {
					starFlag.starMethod(false);
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
		star.setVisible(true);
	}


	

	class CloseThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(2000); // milliseconds
				// 정답이면 2초 뒤 창 닫기
				star.dispose();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
