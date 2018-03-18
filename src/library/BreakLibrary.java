package library;

import java.awt.Dimension;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BreakLibrary extends JFrame {
	JScrollPane scrollPane;
	ImageIcon bgImg;
	// 힌트 카운트
	int hintCnt = 0;
	
	
	public BreakLibrary() {
		// 배경화면
		bgImg = new ImageIcon("img/library.PNG");
		
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension sizing = getSize();
				g.drawImage(bgImg.getImage(), 0, 0, (int)sizing.getWidth(), (int)sizing.getHeight(), null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		// 배경음악, 반복재생
		LibraryBGM("bgm/Under_Cover.wav", true);
		
		// 패널 레이아웃 설정
		background.setLayout(null);

		
		// 힌트주는 곰돌이 버튼 이미지 설정, 버튼 생성, 위치 지정
		ImageIcon hintBear = new ImageIcon("img/hintBear.PNG");
		JButton hintBtn = new JButton(hintBear);
		hintBtn.setBounds(1123, 502, hintBear.getIconWidth(), hintBear.getIconHeight());
		// 버튼 경계선 해제 및 힌트 이벤트
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
									JOptionPane.showMessageDialog(background, "힌트2");
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
				
				if(3 <= hintCnt) {
					JOptionPane.showMessageDialog(background, "힌트를 모두 사용하셨습니다!");
					JOptionPane.showMessageDialog(background, "오늘 발표 기대하고 있겠습니다~!");
				}
			}
		});
		
		//  스위치 버튼 이미지 설정, 버튼 생성, 위치 지정
		ImageIcon blackSwitch = new ImageIcon("img/switch.PNG");
		JButton switchBtn = new JButton(blackSwitch);
		switchBtn.setBounds(833, 228, blackSwitch.getIconWidth(), blackSwitch.getIconHeight());
		switchBtn.setBorderPainted(false);
		
		
		//  거울 버튼 이미지 설정, 버튼 생성, 위치 지정
		ImageIcon mirror = new ImageIcon("img/inthemirror.PNG");
		JButton mirrorBtn = new JButton(mirror);
		mirrorBtn.setBounds(426, 281, mirror.getIconWidth(), mirror.getIconHeight());
		mirrorBtn.setBorderPainted(false);
		
		
		//  책 버튼 이미지 설정, 버튼 생성, 위치 지정
		ImageIcon book = new ImageIcon("img/book.PNG");
		JButton bookBtn = new JButton(book);
		bookBtn.setBounds(73, 300, book.getIconWidth(), book.getIconHeight());
		bookBtn.setBorderPainted(false);
		
		
		// 버튼 프레임에 추가
		background.add(hintBtn);
		background.add(switchBtn);
		background.add(mirrorBtn);
		background.add(bookBtn);
		
		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);
		
	}

	
	// 배경음악 메소드
	public static void  LibraryBGM(String file, boolean loop) {
		try {
			AudioInputStream ais =
					AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			if(loop) {
				clip.loop(-1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
