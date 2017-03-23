package dkeep.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Image extends JPanel
implements MouseListener, MouseMotionListener, KeyListener {

	private BufferedImage image;
	private int x1 = 0, y1 = 0, x2 = 0, y2 = 0; 

	public Image() { 
		addMouseListener(this); 
		addMouseMotionListener(this); 
		addKeyListener(this);  
	} 

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this); S
	}



	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){ 
		case KeyEvent.VK_LEFT: x1--; x2--; repaint(); break; 
		case KeyEvent.VK_RIGHT: x1++; x2++; repaint(); break;  
		case KeyEvent.VK_UP: y1--; y2--; repaint(); break; 
		case KeyEvent.VK_DOWN: y1++; y2++; repaint(); break; 
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x2 = x1 = e.getX();  
		y2 = y1 = e.getY(); 
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();  
		y2 = e.getY();  
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
