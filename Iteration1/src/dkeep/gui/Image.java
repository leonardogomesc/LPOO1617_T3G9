package dkeep.gui;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
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
	private Window2 w;
	private int x1 = 0, y1 = 0, x2 = 0, y2 = 0; 

	
	public Image(Window2 window) { 
		addMouseListener(this); 
		addMouseMotionListener(this); 
		addKeyListener(this); 
		w=window;
	} 

	@Override
	protected void paintComponent(Graphics g) {
		/*super.paintComponent(g);
		g.drawImage(image, 0, 0, this); */
	}



	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){ 
		case KeyEvent.VK_LEFT: w.nextMove("a"); break; 
		case KeyEvent.VK_RIGHT: w.nextMove("d"); break;  
		case KeyEvent.VK_UP:  w.nextMove("w"); break; 
		case KeyEvent.VK_DOWN:  w.nextMove("s"); break; 
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	/*	x2 = x1 = e.getX();  
		y2 = y1 = e.getY(); 
		repaint();*/
		System.out.println("MousePressed"+e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		/*x2 = e.getX();  
		y2 = e.getY();  
		repaint();*/
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
