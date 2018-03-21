package library;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class LightOff extends JFrame {
	// 배경
	JScrollPane scrollPane;
	// textarea 배경
	Image textboxBg = new ImageIcon("img/textbox.png").getImage();
	// 이야기 진행 textarea : 클릭시 string 배열 출력.
	JTextArea storyConsol = new JTextArea() {
		public void paintComponent(Graphics g) {
			Dimension sizing = getSize();
			g.drawImage(textboxBg, 0, 0, (int)sizing.getWidth(), (int)sizing.getHeight(), null);
			setOpaque(false);
			super.paintComponent(g);
		}
	};
	String[] startStory = new String[5];
	{
		startStory[0] = "\n     먼지가 자욱한 방……."
								+ "\n     서재인가….";
		startStory[1] = "\n      앗, 문이 잠겼어…!";
		startStory[2] = "\n     ……너무 어둡다.";
		startStory[3] = "\n     벽에 스위치가 있다!";
		startStory[4] = "\n     스위치를 눌러 불을 켜보자.";
	}
	
	// 클릭할 때마다 카운트 : 한 줄씩 출력
	int clickCnt = 0;
	// 스위치 클릭시 이벤트
	int switchClick = 0;
	// 마우스
	Toolkit tk;
	Image mouseImg;
	Cursor mouse;
	Point point;
	
	
	public LightOff() {
		// 마우스 커서
		tk = Toolkit.getDefaultToolkit();
		mouseImg = new ImageIcon("img/cursor.png").getImage();
		point = new Point(0, 0);
		mouse = tk.createCustomCursor(mouseImg, point, "wonder");
		setCursor(mouse);
		
		// 처음 : 불 꺼진 배경화면
		Image bgImg = new ImageIcon("img/lightoff.PNG").getImage();
		
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				// 사이즈 가져오기
				Dimension sizing = getSize();
				// 이미지, 위치, 사이즈
				g.drawImage(bgImg, 0, 0, (int)sizing.getWidth(), (int)sizing.getHeight(), null);
				// 투명하게? : 아니
				setOpaque(false);
				// 배경 그리기
				super.paintComponent(g);
			}
		};
		
		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);


		// 배경음악, 반복재생
		LibraryBGM("bgm/Wood_Door_Open_Close.wav");

		// 패널 레이아웃 설정
		background.setLayout(null);
		
		
		
		// 텍스트 에어리어 이벤트 : 스위치 버튼 클릭 유도
		storyConsol.setBounds(280, 480, 742, 232);
		TextAction textClick = new TextAction();
		storyConsol.addMouseListener(textClick);
		storyConsol.setEditable(false);
		// 폰트 설정
		Font commonFont = new Font("맑은 고딕", Font.BOLD, 30);
		storyConsol.setForeground(Color.white);
		storyConsol.setFont(commonFont);
		storyConsol.setOpaque(true);
		
		storyConsol.setText(startStory[0]);
		background.add(storyConsol);
		
		
		
		//  스위치 버튼 이미지 설정, 버튼 생성, 위치 지정
		ImageIcon blackSwitch = new ImageIcon("img/offedswitch.png");
		JButton switchBtn = new JButton(blackSwitch);
		switchBtn.setBounds(832, 228, blackSwitch.getIconWidth(), blackSwitch.getIconHeight());
		switchBtn.setBorderPainted(false);
		// 스위치버튼 클릭 이벤트
		switchBtn.addMouseListener(new SwitchAction());
		
		// 버튼 프레임에 추가
		background.add(switchBtn);
		
	}
	
	class TextAction implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			clickCnt++;
			switch(clickCnt) {
			case 1 :
				storyConsol.setText(startStory[1]);
				break;
			case 2 :
				storyConsol.setText(startStory[2]);
				break;
			case 3 :
				storyConsol.setText(startStory[3]);
				break;
			case 4 :
				storyConsol.setText(startStory[4]);
				break;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
		
	}
	
	
	// 스위치 클릭시 마우스 이벤트
	class SwitchAction implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			BreakLibrary breakLibrary = new BreakLibrary();
			breakLibrary.setTitle("서재");
			breakLibrary.setLocation(300, 130);
			breakLibrary.setSize(1280, 800);
			breakLibrary.setResizable(false);
			breakLibrary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			breakLibrary.setIconImage(new ImageIcon("img/favicon.jpg").getImage());
			breakLibrary.setVisible(true);
			// 창닫기
			dispose();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// 버튼 마우스 오프 : 커서 원래대로 돌아오기
			mouseImg = new ImageIcon("img/cursor.png").getImage();
			point = new Point(0, 0);
			mouse = tk.createCustomCursor(mouseImg, point, "wonder");
			setCursor(mouse);
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// 버튼 마우스 온 : 커서 변경
			mouseImg = new ImageIcon("img/check.png").getImage();
			point = new Point(20, 20);
			mouse = tk.createCustomCursor(mouseImg, point, "find");
			setCursor(mouse);
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}

	// 배경음악 메소드 : 나무문
	public static void  LibraryBGM(String file) {
		try {
			AudioInputStream ais =
					AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
