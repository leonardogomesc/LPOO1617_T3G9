package dkeep.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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

public class GraphicsAndListeners extends JPanel
implements MouseListener, MouseMotionListener, KeyListener {

	private BufferedImage wall;
	private BufferedImage door;
	private BufferedImage closedDoor;
	private BufferedImage key;
	private BufferedImage hero;
	private BufferedImage ogre;
	private BufferedImage bat;
	private BufferedImage guard;
	private BufferedImage stunnedOgre;
	private BufferedImage lever;
	
	private LevelEditor level;
	private GameWindow w;
	private int x, y,size; 

	
	public GraphicsAndListeners(Object window) { 
		addMouseListener(this); 
		addMouseMotionListener(this); 
		addKeyListener(this);
		if(window instanceof GameWindow){
		w=(GameWindow)window;
		level=null;
		}else if(window instanceof LevelEditor){
			w=null;
			level=(LevelEditor)window;
		}
		x=0;
		y=0;
		
		try {
			wall = ImageIO.read(new File("C:/Users/Leonardo/Desktop/Images/wall.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stunnedOgre = ImageIO.read(new File("C:/Users/Leonardo/Desktop/Images/ogre.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lever = ImageIO.read(new File("C:/Users/Leonardo/Desktop/Images/lever.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			key = ImageIO.read(new File("C:/Users/Leonardo/Desktop/Images/key.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			door = ImageIO.read(new File("C:/Users/Leonardo/Desktop/Images/door.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			hero = ImageIO.read(new File ("C:/Users/Leonardo/Desktop/Images/hero.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closedDoor = ImageIO.read(new File("C:/Users/Leonardo/Desktop/Images/closedDoor.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ogre = ImageIO.read(new File("C:/Users/Leonardo/Desktop/Images/ogre.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bat = ImageIO.read(new File("C:/Users/Leonardo/Desktop/Images/bat.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			guard = ImageIO.read(new File("C:/Users/Leonardo/Desktop/Images/guard.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

	@Override
	protected void paintComponent(Graphics g) {
		char map[][]=new char[0][0];
		if(w!=null)
		{
		if(w.currentGame==1 || w.currentGame==2){
		
		if(w.currentGame==1){
			if(w.game1.getMap().getMap()[0].length < w.game1.getMap().getMap().length){
				size=450/w.game1.getMap().getMap().length;
			}
			else{
				size=450/w.game1.getMap().getMap()[0].length;
			}
			map=w.game1.getMap().getMap();
		}
		else if(w.currentGame==2){
			if(w.game2.getMap().getMap()[0].length < w.game2.getMap().getMap().length){
				size=450/w.game2.getMap().getMap().length;
			}
			else{
				size=450/w.game2.getMap().getMap()[0].length;
			}
			map=w.game2.getMap().getMap();
		}
		}else 
		{
			if(level.getMap().getMap()[0].length < level.getMap().getMap().length){
				size=450/level.getMap().getMap().length;
			}
			else{
				size=450/level.getMap().getMap()[0].length;
			}
			map=level.getMap().getMap();
		}
		super.paintComponent(g);
		
		for(int i=0;i<map.length;i++){
			for(int i2=0;i2<map[i].length;i2++){
				if(map[i][i2]=='X'){
					g.drawImage(wall,x,y,size,size,null);
				}
				else if(map[i][i2]=='S'){
					g.drawImage(door,x,y,size,size,null);
				}
				else if(map[i][i2]=='I'){
					g.drawImage(closedDoor,x,y,size,size,null);
				}
				else if(map[i][i2]=='H'||map[i][i2]=='A'||map[i][i2]=='K'){
					g.drawImage(hero,x,y,size,size,null);
				}
				else if(map[i][i2]=='k' && w.currentGame==2){
					g.drawImage(key,x,y,size,size,null);
				}
				else if(map[i][i2]=='k' && w.currentGame==1){
					g.drawImage(lever,x,y,size,size,null);
				}
				else if(map[i][i2]=='G'||map[i][i2]=='g'){
					g.drawImage(guard,x,y,size,size,null);
				}
				else if(map[i][i2]=='O'){
					g.drawImage(ogre,x,y,size,size,null);
				}
				else if(map[i][i2]=='8'){
					g.drawImage(stunnedOgre,x,y,size,size,null);
				}
				else if(map[i][i2]=='*'){
					g.drawImage(bat,x,y,size,size,null);
				}
				x=x+size;
			}
			x=0;
			y=y+size;
		}
		
		x=0;
		y=0;
		}
	
		/*
	Graphics2D g2=(Graphics2D) g;
		
		super.paintComponent(g2);
		g2.drawImage(door,0,0,70,70,null);
		g2.drawImage(closedDoor,70,0,70,70,null);
		g2.drawImage(key,140,0,70,70,null);
		g2.drawImage(hero,210,0,70,70,null);
		g2.drawImage(ogre,280,0,70,70,null);
		g2.drawImage(bat,350,0,70,70,null);
		g2.drawImage(wall,0,70,70,70,null);
		g2.drawImage(guard,70,70,70,70,null);
		g.drawImage(lever,140,70,70,70,null);*/
	}



	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){ 
		case KeyEvent.VK_LEFT: w.nextMove("a"); break; 
		case KeyEvent.VK_RIGHT: w.nextMove("d"); break;  
		case KeyEvent.VK_UP:  w.nextMove("w");break; 
		case KeyEvent.VK_DOWN:  w.nextMove("s");break; 
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	/*	x2 = x1 = e.getX();  
		y2 = y1 = e.getY(); 
		repaint();*/
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
