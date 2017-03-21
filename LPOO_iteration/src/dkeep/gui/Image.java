package dkeep.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Image extends JPanel{

	private BufferedImage image;
	

	public Image(String imgName) throws IOException
	{            
		this.image = ImageIO.read(new File("src/dkeep/gui/images/"+imgName));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
	}
	
}
