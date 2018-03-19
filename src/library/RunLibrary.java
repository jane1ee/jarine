package library;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class RunLibrary {
	public static void main(String[] args) {
		LightOff lightOff = new LightOff();
		lightOff.setTitle("서재");
		lightOff.setLocation(300, 130);
		lightOff.setSize(1280, 800);
		lightOff.setResizable(false);
		lightOff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lightOff.setIconImage(new ImageIcon("img/favicon.jpg").getImage());
		lightOff.setVisible(true);
	}
}
