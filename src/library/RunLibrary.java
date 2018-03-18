package library;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class RunLibrary {
	public static void main(String[] args) {
		BreakLibrary breakLibrary = new BreakLibrary();
		breakLibrary.setTitle("서재");
		breakLibrary.setLocation(300, 130);
		breakLibrary.setSize(1280, 800);
		breakLibrary.setResizable(false);
		breakLibrary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		breakLibrary.setIconImage(new ImageIcon("img/favicon.jpg").getImage());
		breakLibrary.setVisible(true);
	}
}
