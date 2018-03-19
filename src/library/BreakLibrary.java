package library;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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
	JScrollPane scrollPane;
	Image bgImg;
	// 힌트 카운트
	int hintCnt = 0;
	// 정답 카운트
	int passCnt = 0;
	
	public BreakLibrary() {
		// 배경화면
		bgImg = new ImageIcon("img/library.png").getImage();
		
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension sizing = getSize();
				g.drawImage(bgImg, 0, 0, (int)sizing.getWidth(), (int)sizing.getHeight(), null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);
		
		
		// 배경음악, 반복재생
		LibraryBGM("bgm/a_quiet_thought.wav", true);
		
		// 패널 레이아웃 설정
		background.setLayout(null);
		
		
		//  문제 1 : 액자(별) 버튼 이미지, 버튼, 위치, 경계선 해제, 이벤트
		ImageIcon mdFrame = new ImageIcon("img/frame.png");
		JButton frameBtn = new JButton(mdFrame);
		frameBtn.setBounds(918, 228, mdFrame.getIconWidth(), mdFrame.getIconHeight());
		frameBtn.setBorderPainted(false);
		frameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame starry = new JFrame("Starry Starry Night");
				starry.setBounds(300, 30, 450, 850);
				JTextArea starryNight = new JTextArea(10, 30);
				starryNight.setEditable(false);
				
				// 폰트 설정
				Font poetFont = new Font("맑은 고딕", Font.PLAIN, 11);
				
				String starSub = "별 헤는 밤\n\n";
				String starPoet = "계절이 지나가는 하늘에는 \n"
						+ "가을로 가득 차 있습니다.\n\n"
						+ "나는 아무 걱정도 없이\n 가을 속의 별들을 다 헤일 듯합니다.\n\n"
						+ "가슴 속에 하나 둘 새겨지는 별을\n이제 다 못 헤는 것은\n"
						+ "쉬이 아침이 오는 까닭이요,\n내일 밤이 남은 까닭이요,\n"
						+ "아직 나의 청춘이 다하지 않은 까닭입니다.\n\n"
						+ "별 하나에 추억과\n별 하나에 사랑과\n별 하나에 쓸쓸함과\n"
						+ "별 하나에 동경과\n별 하나에 시와\n별 하나에 어머니, 어머니,\n\n"
						+ "어머님, 나는 별 하나에 아름다운 말 한마디씩 불러봅니다.\n"
						+ "…\n\n"
						+ "이네들은 너무나 멀리 있습니다.\n별이 아슬히 멀 듯이,\n\n"
						+ "어머님,\n그리고 당신은 멀리 북간도에 계십니다.\n\n"
						+ "나는 무엇인지 그리워\n이 많은 별빛이 나린 언덕 위에\n"
						+ "내 이름자를 써보고,\n흙으로 덮어 버리었습니다.\n\n"
						+ "딴은 밤을 새워 우는 벌레는\n부끄러운 이름을 슬퍼하는 까닭입니다.\n\n"
						+ "그러나 겨울이 지나고 나의 별에도 봄이 오면\n무덤 위에 파란 잔디가 피어나듯이\n"
						+ "내 이름자 묻힌 언덕 위에도\n자랑처럼 풀이 무성할 게외다.";
				starryNight.setForeground(Color.black);
				starryNight.append(starSub);
				starryNight.append(starPoet);
				starryNight.setFont(poetFont);
				
				starry.add(starryNight);
				starry.setVisible(true);
			}
		});
		

		//  문제 1 힌트 : 액자(하늘 바람 별)
		ImageIcon hintFrame = new ImageIcon("img/hintFrame.png");
		JButton poetBtn = new JButton(hintFrame);
		poetBtn.setBounds(950, 328, hintFrame.getIconWidth(), hintFrame.getIconHeight());
		poetBtn.setBorderPainted(false);
		poetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame hintPoet = new JFrame("시");
				hintPoet.setBounds(300, 30, 450, 850);
//				JTextArea starryNight = new JTextArea(10, 30);
//				starryNight.setEditable(false);
			}
		});
		
		
		
		//  문제 2 :책
		ImageIcon book = new ImageIcon("img/book.png");
		JButton bookBtn = new JButton(book);
		bookBtn.setBounds(73, 300, book.getIconWidth(), book.getIconHeight());
		bookBtn.setBorderPainted(false);

		
		// 이벤트 1 : 힌트 곰돌이 버튼
		ImageIcon hintBear = new ImageIcon("img/hintBear.PNG");
		JButton hintBtn = new JButton(hintBear);
		hintBtn.setBounds(1123, 502, hintBear.getIconWidth(), hintBear.getIconHeight());
		hintBtn.setBorderPainted(false);
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
								JOptionPane.showMessageDialog(background, "힌트힌트1");
								break;
							} else if (hint1 == 1) {
								int hint2 = JOptionPane.showConfirmDialog(background, "액자의 문제입니까?");
								if(hint2 == 0) {
									JOptionPane.showMessageDialog(background, "별을 헤어보세요.");
									break;
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
				gainControl.setValue(-10.0f);
			clip.start();
			if(loop) {
				clip.loop(-1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
