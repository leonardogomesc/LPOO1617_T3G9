package dkeep.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import dkeep.logic.Game;
import dkeep.logic.Guard;
import dkeep.logic.Hero;
import dkeep.logic.Map;
import dkeep.logic.Ogre;


public class Window {

	private JFrame frmDungeonKeep;
	private Game game1;
	private Game game2;
    private int currentGame;
    
    JButton btnNewButton;
	JButton btnNewButton_1;
	JButton btnNewButton_2 ;
	JButton btnNewButton_3;
	JLabel lblNewLabel;
	JTextArea text;
    
	
	/**
	 * Launch the application.
	 */
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmDungeonKeep.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmDungeonKeep = new JFrame();
		frmDungeonKeep.setTitle("Dungeon Keep \u00AE");
		frmDungeonKeep.setResizable(false);
		frmDungeonKeep.setBounds(100, 100, 600, 500);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(10, 25, 118, 14);
		frmDungeonKeep.getContentPane().add(lblNumberOfOgres);
		
		JTextField textField = new JTextField();
		textField.setBounds(138, 22, 35, 20);
		frmDungeonKeep.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(10, 58, 109, 14);
		frmDungeonKeep.getContentPane().add(lblGuardPersonality);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Rookie", "Drunken", "Suspicious"}));
		comboBox.setBounds(138, 55, 131, 20);
		frmDungeonKeep.getContentPane().add(comboBox);
		
		
		text = new JTextArea();
		text.setEditable(false);
		text.setFont(new Font("Courier New", Font.PLAIN, 13));
		text.setBounds(29, 119, 329, 292);
		frmDungeonKeep.getContentPane().add(text);
		
		
		lblNewLabel = new JLabel("You can start a new Game!");
		lblNewLabel.setBounds(29, 432, 329, 28);
		frmDungeonKeep.getContentPane().add(lblNewLabel);
		
		
		btnNewButton = new JButton("Up");
		btnNewButton_1 = new JButton("Right");
		btnNewButton_2 = new JButton("Left");
		btnNewButton_3 = new JButton("Down");
		
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextMove("w");
			}
		});
		btnNewButton.setBounds(442, 234, 73, 20);
		frmDungeonKeep.getContentPane().add(btnNewButton);
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMove("d");
			}
		});
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(485, 265, 73, 20);
		frmDungeonKeep.getContentPane().add(btnNewButton_1);
		
		
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMove("a");
			}
		});
		btnNewButton_2.setBounds(395, 265, 73, 20);
		frmDungeonKeep.getContentPane().add(btnNewButton_2);
		
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMove("s");
			}
		});
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.setBounds(442, 296, 73, 20);
		frmDungeonKeep.getContentPane().add(btnNewButton_3);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(437, 401, 89, 23);
		frmDungeonKeep.getContentPane().add(btnExit);
		
		
		JButton btnNewGame = new JButton("New Game");
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
				
				btnNewButton.setEnabled(true);
				btnNewButton_1.setEnabled(true);
				btnNewButton_2.setEnabled(true);
				btnNewButton_3.setEnabled(true);
				currentGame=1;
				
				text.setText(to_String(game1.getMap()));
				lblNewLabel.setText("You can play now");
				}
				else if(guardType==-1 && numberOfOgres==-1){
					lblNewLabel.setText("Invalid Number of Ogres and Type of Guard");
				}
				else if(guardType==-1){
					lblNewLabel.setText("Invalid Type of Guard");
				}
				else if(numberOfOgres==-1){
					lblNewLabel.setText("Invalid Number of Ogres");
				}
			}
		});
		btnNewGame.setBounds(414, 121, 130, 20);
		frmDungeonKeep.getContentPane().add(btnNewGame);
		
	}
	
	
	public String to_String(Map m){
		String result="";	
	
		char map[][]=m.getMap();
			
		for(int i1=0;i1<map.length;i1++){
			for(int i2=0;i2<map[i1].length;i2++){
				result=result+map[i1][i2]+" ";
			}
			result=result+"\n";
		}
		
		return result;
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
			lblNewLabel.setText("You can play now");
			
			game1.getHero().HeroMove(game1.getMap(), m);
			game1.getGuard().GuardMove(game1.getMap());
			
			
			if(game1.losscheck()==1){
				lblNewLabel.setText("You lost!");
				btnNewButton.setEnabled(false);
				btnNewButton_1.setEnabled(false);
				btnNewButton_2.setEnabled(false);
				btnNewButton_3.setEnabled(false);
			}
			
			text.setText(to_String(game1.getMap()));

			if(game1.wincheck()==1){
				currentGame=2;
				text.setText(to_String(game2.getMap()));
			}
			
		}
	}
	else if(currentGame==2){
		if(game2.losscheckkeep()==0 && game2.wincheck()==0){
			lblNewLabel.setText("You can play now");
			
			game2.getHero().HeroMove(game2.getMap(), m);
			game2.OgreMove();
			
			
			if(game2.losscheckkeep()==1){
				lblNewLabel.setText("You lost!");
				btnNewButton.setEnabled(false);
				btnNewButton_1.setEnabled(false);
				btnNewButton_2.setEnabled(false);
				btnNewButton_3.setEnabled(false);
			}
			
			
			text.setText(to_String(game2.getMap()));

			if(game2.wincheck()==1){
				lblNewLabel.setText("You won!");
				btnNewButton.setEnabled(false);
				btnNewButton_1.setEnabled(false);
				btnNewButton_2.setEnabled(false);
				btnNewButton_3.setEnabled(false);
			}
			
		}
	}	
}
}
