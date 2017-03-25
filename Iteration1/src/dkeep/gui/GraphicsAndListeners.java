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
			wall = ImageIO.read(new File("src/dkeep/gui/images/wall.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stunnedOgre = ImageIO.read(new File("src/dkeep/gui/images/ogre.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lever = ImageIO.read(new File("src/dkeep/gui/images/lever.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			key = ImageIO.read(new File("src/dkeep/gui/images/key.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			door = ImageIO.read(new File("src/dkeep/gui/images/door.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			hero = ImageIO.read(new File ("src/dkeep/gui/images/hero.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			closedDoor = ImageIO.read(new File("src/dkeep/gui/images/closedDoor.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ogre = ImageIO.read(new File("src/dkeep/gui/images/ogre.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		try {
			bat = ImageIO.read(new File("src/dkeep/gui/images/bat.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			guard = ImageIO.read(new File("src/dkeep/gui/images/guard.png"));
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
		
			if(w.game.getMap().getMap()[0].length < w.game.getMap().getMap().length){
				size=450/w.game.getMap().getMap().length;
			}
			else{
				size=450/w.game.getMap().getMap()[0].length;
			}
			map=w.game.getMap().getMap();
		
		paintImages(g, map);
		
		}
		}
		else if(level!=null)
		{
			if(level.getMap().getMap()[0].length < level.getMap().getMap().length){
				size=450/level.getMap().getMap().length;
			}
			else{
				size=450/level.getMap().getMap()[0].length;
			}
			map=level.getMap().getMap();
		
			paintImages(g, map);
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

private void paintImages(Graphics g, char map[][]){
	   int c=0;
	
	   if(level!=null){
		   c=2;
		   
	   }
	   else if(w!=null){
		   c=w.currentGame;
	   }
	   
	   super.paintComponent(g);
	   
	   x=0;
	   y=0;
	   
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
			else if(map[i][i2]=='k' && c==2){
				g.drawImage(key,x,y,size,size,null);
			}
			else if(map[i][i2]=='k' && c==1){
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
		
		char map[][]=new char[0][0];
		int pos0;
		int pos1;
		
		if(level!=null){
			
			if(level.getMap().getMap()[0].length < level.getMap().getMap().length){
				size=450/level.getMap().getMap().length;
			}
			else{
				size=450/level.getMap().getMap()[0].length;
			}
			map=level.getMap().getMap();
			
		pos0=e.getY() / size;	
		pos1=e.getX() / size;
		
		switch(level.option){
		case "hero":
			if(level.checkBox.isSelected()){
			map[pos0][pos1]='A';
			}
			else{
				map[pos0][pos1]='H';
			}
			break;
		case "ogre":
			map[pos0][pos1]='O';
			break;
		case "bat":
			map[pos0][pos1]='*';
			break;
		case "wall":
			map[pos0][pos1]='X';
			break;
		case "door":
			map[pos0][pos1]='I';
			break;
		case "key":
			map[pos0][pos1]='k';
			break;
		case "eraseCell":
			map[pos0][pos1]=' ';
			break;
		}
		
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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
