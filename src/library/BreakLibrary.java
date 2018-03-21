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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class BreakLibrary extends JFrame {
	// 배경
	JScrollPane scrollPane;
	Image bgImg;
	// 마우스
	Image mouseImg;
	Cursor mouse;
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
		JPanel background = new JPanel() {
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
		
		
		//  문제 1 : 액자(별) 버튼 이미지 // 버튼 // 위치 // 경계선 해제 // 이벤트
		ImageIcon mdFrame = new ImageIcon("img/frame.png");
		JButton frameBtn = new JButton(mdFrame);
		frameBtn.setBounds(918, 228, mdFrame.getIconWidth(), mdFrame.getIconHeight());
		frameBtn.setBorderPainted(false);
		frameBtn.addMouseListener(new OnOffMouse());
		frameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 문제 1 출력
				StarCount starCnt = new StarCount();
				//정답이면 정답 카운트 passCnt 1 증가
			}
		});
		
		//  문제 1 힌트 : 액자(하늘 바람 별) : 시 이미지 출력
		ImageIcon hintFrame = new ImageIcon("img/hintFrame.png");
		JButton poetBtn = new JButton(hintFrame);
		poetBtn.setBounds(970, 320, hintFrame.getIconWidth(), hintFrame.getIconHeight());
		poetBtn.setBorderPainted(false);
		poetBtn.addMouseListener(new OnOffMouse());
		poetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClueFrame clue = new ClueFrame();
			}
		});
		
		
		
		//  문제 2 :책
		ImageIcon book = new ImageIcon("img/book.png");
		JButton bookBtn = new JButton(book);
		bookBtn.setBounds(73, 312, book.getIconWidth(), book.getIconHeight());
		bookBtn.setBorderPainted(false);
		bookBtn.addMouseListener(new OnOffMouse());
		bookBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 문제 2 출력
				LetterFromBook lfb =  new LetterFromBook();
			}
		});

		
		
		// 이벤트 1 : 힌트 곰돌이 버튼
		ImageIcon hintBear = new ImageIcon("img/hintBear.PNG");
		JButton hintBtn = new JButton(hintBear);
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
		ImageIcon mirror = new ImageIcon("img/inthemirror.png");
		JButton mirrorBtn = new JButton(mirror);
		mirrorBtn.setBounds(426, 281, mirror.getIconWidth(), mirror.getIconHeight());
		mirrorBtn.setBorderPainted(false);
		mirrorBtn.addMouseListener(new OnOffMouse());
		mirrorBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				while(mirrorCnt != 12) {
					++mirrorCnt;
				}
				if(mirrorCnt == 12) {
					
				}
			}
		});
		
		
		// 버튼 프레임에 추가
		background.add(hintBtn);
		background.add(frameBtn);
		background.add(poetBtn);
		background.add(mirrorBtn);
		background.add(bookBtn);
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
}
