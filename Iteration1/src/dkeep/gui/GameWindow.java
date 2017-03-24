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
	
	Game game1, game2;
	int currentGame;
	

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
					guardType=1;
					break;
				case 2:
					guardType=2;
					break;
				case 3:
					guardType=3;
					break;
				default:
					guardType=-1;
					break;
				}
				
				if(guardType!=-1 && numberOfOgres!=-1){
				
				game1=newGame(guardType, numberOfOgres)[0];
				game2=newGame(guardType, numberOfOgres)[1];
				
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
	
	
public Game[] newGame(int guardType, int numberOfOgres){
		
		//Game 1	
		
		int hero[]={1,1};
		int doors[][]={{5,0},{6,0}};
		int key[]={8,7};
		int guard[][]={{1,8},{1,7},{2,7},{3,7},{4,7},{5,7},{5,6},{5,5},{5,4},{5,3},{5,2},{5,1},{6,1},{6,2},{6,3},{6,4},{6,5},{6,6},{6,7},{6,8},{5,8},{4,8},{3,8},{2,8}};

		  char map[][]={{'X','X','X','X','X','X','X','X','X','X'},
						{'X','H',' ',' ','I',' ','X',' ','G','X'},
						{'X','X','X',' ','X','X','X',' ',' ','X'},
						{'X',' ','I',' ','I',' ','X',' ',' ','X'},
						{'X','X','X',' ','X','X','X',' ',' ','X'},
						{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
						{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
						{'X','X','X',' ','X','X','X','X',' ','X'},
						{'X',' ','I',' ','I',' ','X','k',' ','X'},
						{'X','X','X','X','X','X','X','X','X','X'}};

		Guard g=new Guard(guard, guardType);
		Hero h=new Hero(hero, 0);

		Map m=new Map(map,doors,key,1);
		
		Game game1=new Game(m,h,g);
		
		
		
		//Game 2
		
		int hero2[]={7,1};
		int doors2[][]={{1,0}};
		int key2[]={1,7};
		
		char map2[][]= {{'X','X','X','X','X','X','X','X','X'},
						{'I',' ',' ',' ','O',' ',' ','k','X'},
						{'X',' ',' ',' ','*',' ',' ',' ','X'},
						{'X',' ',' ',' ',' ',' ',' ',' ','X'},
						{'X',' ',' ',' ',' ',' ',' ',' ','X'},
						{'X',' ',' ',' ',' ',' ',' ',' ','X'},
						{'X',' ',' ',' ',' ',' ',' ',' ','X'},
						{'X','A',' ',' ',' ',' ',' ',' ','X'},
						{'X','X','X','X','X','X','X','X','X'}};


		Hero h2=new Hero(hero2, 1);
			
		Ogre ogrearray[]=new Ogre[numberOfOgres];
		
		for(int i=0;i<numberOfOgres;i++){
			ogrearray[i]=new Ogre(new int[]{1,4},new int[]{2,4});
		}

		Map m2=new Map(map2,doors2,key2,2);
		
		Game game2=new Game(m2,h2,ogrearray);
		
		Game gamearray[]={game1,game2};
		
		return gamearray;
	}
	
	
	public void nextMove(String m){
		if(currentGame==1){
			if(game1.losscheck()==0 && game1.wincheck()==0){
				textArea_1.setText("You can play now");
				
				game1.getHero().HeroMove(game1.getMap(), m);
				game1.getGuard().GuardMove(game1.getMap());
				
				
				if(game1.losscheck()==1){
					textArea_1.setText("You lost!");
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnRight.setEnabled(false);
					btnLeft.setEnabled(false);
				}
				
				//textArea.setText(game1.getMap().to_String());

				if(game1.wincheck()==1){
					currentGame=2;
					//textArea.setText(game2.getMap().to_String());
				}
				
			}
		}
		else if(currentGame==2){
			if(game2.losscheckkeep()==0 && game2.wincheck()==0){
				textArea_1.setText("You can play now");
				
				game2.getHero().HeroMove(game2.getMap(), m);
				game2.OgreMove();
				
				
				if(game2.losscheckkeep()==1){
					textArea_1.setText("You lost!");
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnRight.setEnabled(false);
					btnLeft.setEnabled(false);
				}
				
				
				//textArea.setText(game2.getMap().to_String());

				if(game2.wincheck()==1){
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
