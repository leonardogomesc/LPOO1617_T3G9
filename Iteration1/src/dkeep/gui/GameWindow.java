package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dkeep.logic.Game;
import dkeep.logic.Guard;
import dkeep.logic.Hero;
import dkeep.logic.Map;
import dkeep.logic.Ogre;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;

public class GameWindow {

	private JFrame frame;
	private JTextField textField;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnRight;
	private JButton btnLeft;
	private JLabel textArea_1;
	private JPanel panel;
	
	Game game;
	int currentGame;
	private int info[];
	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new GraphicsAndListeners(this);
		panel.setBounds(30, 127, 450, 450);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.requestFocusInWindow();
		
		
		btnUp = new JButton("Up");
		btnDown = new JButton("Down");
		btnRight = new JButton("Right");
		btnLeft = new JButton("Left");
		JButton btnExit = new JButton("Exit");
		JButton btnNewGame = new JButton("New Game");
		JButton btnLevelEditor = new JButton("Level Editor");
		textArea_1 = new JLabel("You can start a new Game!");
		textField = new JTextField();
		JComboBox comboBox = new JComboBox();
		
		btnUp.setEnabled(false);
		btnDown.setEnabled(false);
		btnRight.setEnabled(false);
		btnLeft.setEnabled(false);
		
		btnUp.setFocusable(false);
		btnDown.setFocusable(false);
		btnRight.setFocusable(false);
		btnLeft.setFocusable(false);
		btnExit.setFocusable(false);
		btnNewGame.setFocusable(false);
		btnLevelEditor.setFocusable(false);
		
		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMove("w");
			}
		});
		btnUp.setBounds(615, 193, 80, 30);
		frame.getContentPane().add(btnUp);
		
	
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMove("s");
			}
		});
		btnDown.setBounds(620, 282, 80, 30);
		frame.getContentPane().add(btnDown);
		
	
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMove("d");
			}
		});
		btnRight.setBounds(674, 238, 80, 30);
		frame.getContentPane().add(btnRight);
		
		
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMove("a");
			}
		});
		btnLeft.setBounds(560, 238, 80, 30);
		frame.getContentPane().add(btnLeft);
		
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(620, 495, 89, 23);
		frame.getContentPane().add(btnExit);
		
		
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numberOfOgres=-1;
				int guardType=-1;
				
				switch(textField.getText()){
				case "1":
					numberOfOgres=1;
					break;
				case "2":
					numberOfOgres=2;
					break;
				case "3":
					numberOfOgres=3;
					break;
				case "4":
					numberOfOgres=4;
					break;
				case "5":
					numberOfOgres=5;
					break;
				default:
					numberOfOgres=-1;
					break;
				}
				
				
				switch(comboBox.getSelectedIndex()){
				case 0:
					guardType=-1;
					break;
				case 1:
					guardType=0;
					break;
				case 2:
					guardType=1;
					break;
				case 3:
					guardType=2;
					break;
				default:
					guardType=-1;
					break;
				}
				
				if(guardType!=-1 && numberOfOgres!=-1){
					
			    info=new int[] {guardType,numberOfOgres};
					
				game=new Game();
				
				try {
					game.nextLevel(info);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnRight.setEnabled(true);
				btnLeft.setEnabled(true);
				
				currentGame=1;
				
			//	textArea.setText(game1.getMap().to_String());
				
				
				textArea_1.setText("You can play now");
				panel.repaint();
				}
				else if(guardType==-1 && numberOfOgres==-1){
					textArea_1.setText("Invalid Number of Ogres and Type of Guard");
				}
				else if(guardType==-1){
					textArea_1.setText("Invalid Type of Guard");
				}
				else if(numberOfOgres==-1){
					textArea_1.setText("Invalid Number of Ogres");
				}
				
				panel.requestFocusInWindow();
			
		}
		});
		btnNewGame.setBounds(591, 72, 131, 23);
		frame.getContentPane().add(btnNewGame);
		
		
		btnLevelEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LevelEditor newLevel = new LevelEditor();
				newLevel.setVisible(true);
			}
		});
		btnLevelEditor.setBounds(589, 430, 149, 23);
		frame.getContentPane().add(btnLevelEditor);
		
		JLabel label = new JLabel("Number of Ogres");
		label.setBounds(39, 31, 118, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Guard Personality");
		label_1.setBounds(39, 76, 109, 14);
		frame.getContentPane().add(label_1);
		
		
		textField.setColumns(10);
		textField.setBounds(177, 28, 35, 20);
		frame.getContentPane().add(textField);
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Rookie", "Drunken", "Suspicious"}));
		comboBox.setBounds(178, 73, 131, 20);
		frame.getContentPane().add(comboBox);
		
		
		textArea_1.setBounds(30, 610, 500, 23);
		frame.getContentPane().add(textArea_1);
	}
	
	
	public void nextMove(String m){
		int gameState;
		
		if(currentGame==1){
			if(game.losscheck()==0 && game.wincheck()==0){
				textArea_1.setText("You can play now");
				
				game.getHero().HeroMove(game.getMap(), m);
				game.getGuard().GuardMove(game.getMap());
				
				
				if(game.losscheck()==1){
					textArea_1.setText("You lost!");
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnRight.setEnabled(false);
					btnLeft.setEnabled(false);
				}
				
				//textArea.setText(game1.getMap().to_String());

				if(game.wincheck()==1){
					
					try {
						gameState=game.nextLevel(info);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					currentGame=2;
					//textArea.setText(game2.getMap().to_String());
				}
				
			}
		}
		else if(currentGame==2){
			if(game.losscheckkeep()==0 && game.wincheck()==0){
				textArea_1.setText("You can play now");
				
				game.getHero().HeroMove(game.getMap(), m);
				game.OgreMove();
				
				
				if(game.losscheckkeep()==1){
					textArea_1.setText("You lost!");
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnRight.setEnabled(false);
					btnLeft.setEnabled(false);
				}
				
				
				//textArea.setText(game2.getMap().to_String());

				if(game.wincheck()==1){
					textArea_1.setText("You won!");
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnRight.setEnabled(false);
					btnLeft.setEnabled(false);
				}
				
			}
		}	
		panel.repaint();
		
	}
}
