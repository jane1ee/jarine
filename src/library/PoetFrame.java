package library;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PoetFrame extends JFrame {
	// JFrame
	JFrame poet;
	// JLabel
	JLabel poetLabel;
	// 단서 발견, 단서 이미지
	Image poetImg;
	
	public PoetFrame() {
		// 마우스 커서
		Toolkit tk = Toolkit.getDefaultToolkit();
		// 마우스 이미지
		Image mouseImg = new ImageIcon("img/cursor.png").getImage();
		Point point = new Point(0, 0);
		Cursor mouse = tk.createCustomCursor(mouseImg, point, "wonder");
		setCursor(mouse);
		
		
		// 문제1 힌트
		poet = new JFrame();
		// 위치, 크기 설정
		poet.setBounds(150, 100, 800, 534);
		// 창 크기 조절 : 불가능
		poet.setResizable(false);
		// X창 없애기
		poet.setUndecorated(true);
		// 이미지
		poetLabel  = new JLabel();
		poetImg = new ImageIcon("img/poet.png").getImage();
		poetLabel.setIcon(new ImageIcon(poetImg));
		poetLabel.setLocation(0, 0);
		
		// 클릭하면 힌트1 창 닫기
		poetLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				poet.setVisible(false);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				poet.setVisible(false);
			}
		});
		
		poet.add(poetLabel);
		poet.setVisible(true);
	}

}
