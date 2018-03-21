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
import javax.sound.sampled.FloatControl;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;


public class BreakLibrary extends JFrame {
	// 배경
	JScrollPane scrollPane;
	Image bgImg;
	JPanel background;
	// 마우스
	Image mouseImg;
	Cursor mouse;
	// 문제1 : starry starry night  숫자 암호 : 답) starry
	ImageIcon book;
	JButton bookBtn;
	// 문제2 : 별 헤는 밤에는 별이 몇 개 나올까?(제목포함)
	ImageIcon starFrame;
	JButton starBtn;
	// 문제2 힌트
	ImageIcon starHint;
	JButton starPoetBtn;
	// 문제 발견 팝업
	JFrame event;
	JLabel eventLabel;
	Image eventImg;
	//  단서  발견 팝업
	JFrame clue;
	JLabel clueLabel;
	Image clueImg;
	// 거울 버튼
	ImageIcon mirror;
	JButton mirrorBtn;
	// 힌트 곰돌이
	ImageIcon hintBear;
	JButton hintBtn;
	// 힌트 카운트
	int hintCnt = 0;
	// 거울 클릭 카운트 : 12번 누르면 버튼 사라지고 깨짐
	int mirrorCnt = 0;
//	// 정답 카운트 : passCnt = 3이면 Library 탈출
//	int passCnt = 0;
	
	
	public BreakLibrary() {
		// 마우스 커서
		Toolkit tk = Toolkit.getDefaultToolkit();
		// 이미지 설정
		mouseImg = new ImageIcon("img/cursor.png").getImage();
		Point point = new Point(0, 0);
		mouse = tk.createCustomCursor(mouseImg, point, "wonder");
		setCursor(mouse);
		
		
		// 배경화면
		bgImg = new ImageIcon("img/library.png").getImage();
		background = new JPanel() {
			public void paintComponent(Graphics g) {
				// 이미지 사이즈 가져오기
				Dimension sizing = getSize();
				g.drawImage(bgImg, 0, 0, (int)sizing.getWidth(), (int)sizing.getHeight(), null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);
		
		
		// 배경음악, 반복재생
		LibraryBGM("bgm/Under_Cover.wav", true);
		
		// 패널 레이아웃 설정
		background.setLayout(null);
		
		
		
		
		//  문제 1 :책
		book = new ImageIcon("img/book.png");
		bookBtn = new JButton(book);
		bookBtn.setBounds(73, 312, book.getIconWidth(), book.getIconHeight());
		bookBtn.setBorderPainted(false);
		bookBtn.addMouseListener(new OnOffMouse());
		bookBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 문제 발견 팝업
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
			}
		});

		
		
		//  문제 2 : 액자(별이 빛나는 밤) 클릭시 출력
		 starFrame = new ImageIcon("img/frame.png");
		starBtn = new JButton(starFrame);
		starBtn.setBounds(918, 228, starFrame.getIconWidth(), starFrame.getIconHeight());
		starBtn.setBorderPainted(false);
		starBtn.addMouseListener(new OnOffMouse());
		starBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 문제 출력
				StarCount starCnt = new StarCount();

			}
		});
		
		//  문제 2 힌트 : 액자(하늘 바람 별) : 시 이미지 출력
		starHint = new ImageIcon("img/hintFrame.png");
		starPoetBtn = new JButton(starHint);
		starPoetBtn.setBounds(970, 320, starHint.getIconWidth(), starHint.getIconHeight());
		starPoetBtn.setBorderPainted(false);
		starPoetBtn.addMouseListener(new OnOffMouse());
		starPoetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 힌트 발견 팝업 : 배경화면
				clue = new JFrame();
				// 위치, 크기 설정
				clue.setBounds(650, 510, 542, 80);
				// 창 크기 조절 : 불가능
				clue.setResizable(false);
				// X창 없애기
				clue.setUndecorated(true);
				// 이미지
				clueLabel  = new JLabel();
				clueImg = new ImageIcon("img/clue.png").getImage();
				clueLabel.setIcon(new ImageIcon(clueImg));
				clueLabel.setLocation(0, 0);
				clue.add(clueLabel);
				
				// 보여지도록 하기
				clue.setVisible(true);
				// 쓰레드 : 단서 문구 사라지고 힌트 출력
				new PoetThread().start();
			}
		});
		
		
		
		// 이벤트 1 : 힌트 곰돌이 버튼
		hintBear = new ImageIcon("img/hintBear.PNG");
		hintBtn = new JButton(hintBear);
		hintBtn.setBounds(1123, 502, hintBear.getIconWidth(), hintBear.getIconHeight());
		hintBtn.setBorderPainted(false);
		hintBtn.addMouseListener(new OnOffMouse());
		hintBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				while(hintCnt != 3) {
					int needHint = JOptionPane.showConfirmDialog(background, "힌트가 필요하십니까? 힌트는 3번 주어집니다");
					if(needHint == 0 && hintCnt < 4) {
						++hintCnt;
						if(hintCnt < 4) {
							JOptionPane.showMessageDialog(background, "힌트를 사용합니다. 남은 힌트는 " + (3 - hintCnt) + "개 입니다.");
							int hint1 = JOptionPane.showConfirmDialog(background, "책장의 문제입니까?");
							if (hint1 == 0) {
								JOptionPane.showMessageDialog(background, "간단한 숫자 암호입니다.\n"
										+ "순서대로 알파벳을 써보세요.");
								break;
							} else if (hint1 == 1) {
								int hint2 = JOptionPane.showConfirmDialog(background, "액자의 문제입니까?");
								if(hint2 == 0) {
									JOptionPane.showMessageDialog(background, "별을 헤어보세요.");
									break;
								} else if( hint2 == 1 ) {
									JOptionPane.showMessageDialog(background, "중요한 것은 거울 속에 있어요.\n"
											+ "시침이 한 바퀴 돌면 나타나요.");
								} else {
									JOptionPane.showMessageDialog(background, "오늘 발표 기대하고 있겠습니다~!");
									break;
								}
							} else {
								JOptionPane.showMessageDialog(background, "오늘 발표 기대하고 있겠습니다~!");
								break;
							}
						}
					} else {
						JOptionPane.showMessageDialog(background, "오늘 발표 기대하고 있겠습니다~!");
						break;
					}
				}
				
				if(hintCnt == 3) {
					JOptionPane.showMessageDialog(background, "힌트를 모두 사용하셨습니다!");
					JOptionPane.showMessageDialog(background, "오늘 발표 기대하고 있겠습니다~!");
				}
			}
		});
		
		
		//  이벤트 2 : 거울 버튼 이미지, 버튼, 위치
		mirror = new ImageIcon("img/inthemirror.png");
		mirrorBtn = new JButton(mirror);
		mirrorBtn.setBounds(426, 281, mirror.getIconWidth(), mirror.getIconHeight());
		mirrorBtn.setBorderPainted(false);
		mirrorBtn.addMouseListener(new OnOffMouse());
		mirrorBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				while(mirrorCnt != 12) {
					++mirrorCnt;

					// 힌트 발견 팝업 : 배경화면
					clue = new JFrame();
					// 위치, 크기 설정
					clue.setBounds(650, 510, 542, 80);
					// 창 크기 조절 : 불가능
					clue.setResizable(false);
					// X창 없애기
					clue.setUndecorated(true);
					// 이미지
					clueLabel  = new JLabel();
					clueImg = new ImageIcon("img/clue.png").getImage();
					clueLabel.setIcon(new ImageIcon(clueImg));
					clueLabel.setLocation(0, 0);
					clue.add(clueLabel);
					
					// 보여지도록 하기
					clue.setVisible(true);
					// 시간 제한 쓰레드
					new MirrorThread().start();
					
				}
				if(mirrorCnt == 12) {
					JOptionPane.showMessageDialog(background, "!!!");
				}
			}
		});
		
		
		// 버튼 프레임에 추가
		background.add(bookBtn);
		background.add(starBtn);
		background.add(starPoetBtn);
		background.add(mirrorBtn);
		background.add(hintBtn);
	}

	
	// 배경음악 메소드 : 음량 -10 작게, 무한반복
	public static void  LibraryBGM(String file, boolean loop) {
		try {
			AudioInputStream ais =
					AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			FloatControl gainControl = 
				    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//				gainControl.setValue(-1	0.0f);
			clip.start();
			if(loop) {
				clip.loop(-1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 마우스 온-오프 커서 바꾸기
	public class OnOffMouse implements MouseListener {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// 버튼 마우스 오프 : 커서 원래대로 돌아오기
			Toolkit tk = Toolkit.getDefaultToolkit();
			mouseImg = new ImageIcon("img/cursor.png").getImage();
			Point point = new Point(0, 0);
			mouse = tk.createCustomCursor(mouseImg, point, "wonder");
			setCursor(mouse);
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// 버튼 마우스 온 : 커서 변경
			Toolkit tk = Toolkit.getDefaultToolkit();
			mouseImg = new ImageIcon("img/check.png").getImage();
			Point point = new Point(20, 20);
			mouse = tk.createCustomCursor(mouseImg, point, "find");
			setCursor(mouse);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	

	class BookThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(1000);  // milliseconds
				// 1초 뒤 발견문구 사라짐
				event.setVisible(false);
				// 문제 출력
				LetterFromBook lfb = new LetterFromBook();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	class PoetThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(1000);  // milliseconds
				// 1초 뒤 단서 발견 창 사라짐
				clue.setVisible(false);
				// '별 헤는 밤' 힌트 출력
				PoetFrame poet = new PoetFrame();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	class MirrorThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(1000);  // milliseconds
				// 1초 뒤 단서 발견 창 사라짐
				clue.setVisible(false);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
