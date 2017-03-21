package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dkeep.logic.Game;
import dkeep.logic.Guard;
import dkeep.logic.Hero;
import dkeep.logic.Map;
import dkeep.logic.Ogre;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window2 implements KeyListener {

	private JFrame frame;
	private JTextField textField;
	private Game game1;
	private Game game2;
	private int currentGame;
	JButton btnDown;
	JButton btnUp;
	JButton btnL;
	JButton btnR;
	JTextArea textArea;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window2 window = new Window2();
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
	public Window2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "name_10500935374081");
		panel.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setBounds(24, 25, 235, 222);
		textArea.setColumns(3);
		panel.add(textArea);
		
	    btnDown = new JButton("D");
		btnUp = new JButton("U");
		btnL = new JButton("L");
		btnR = new JButton("R");
		
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMove("d",textArea,btnDown,btnUp,btnL,btnR);
			}
		});
		
		btnL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMove("a",textArea,btnDown,btnUp,btnL,btnR);
			}
		});
		
		
		btnUp.setEnabled(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMove("w",textArea,btnDown,btnUp,btnL,btnR);
			}
		});
		btnUp.setBounds(317, 72, 65, 23);
		panel.add(btnUp);
		
		btnDown.setEnabled(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextMove("s",textArea,btnDown,btnUp,btnL,btnR);
			}
		});
		btnDown.setBounds(317, 140, 65, 23);
		panel.add(btnDown);
		
		
		btnL.setEnabled(false);
		btnL.setBounds(281, 106, 65, 23);
		panel.add(btnL);
		
		
		btnR.setEnabled(false);
		btnR.setBounds(356, 106, 65, 23);
		panel.add(btnR);
		
		JPanel panel_1 = new JPanel();
		
		panel.setVisible(true);
		panel_1.setVisible(false);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_1.setVisible(true);
				
			}
		});
		btnSettings.setBounds(293, 198, 128, 23);
		panel.add(btnSettings);
		
		JComboBox comboBox = new JComboBox();
		
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
				
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnL.setEnabled(true);
				btnR.setEnabled(true);
				currentGame=1;
				
				textArea.setText(to_String(game1.getMap()));
				}
			}
		});
		btnNewGame.setBounds(281, 23, 140, 23);
		panel.add(btnNewGame);
		
		
		frame.getContentPane().add(panel_1, "name_10567897553764");
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Number of Ogres");
		label.setBounds(31, 25, 82, 14);
		panel_1.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(171, 22, 35, 20);
		panel_1.add(textField);
		
		JLabel label_1 = new JLabel("Guard Personality");
		label_1.setBounds(31, 66, 109, 14);
		panel_1.add(label_1);
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Rookie", "Drunken", "Suspicious"}));
		comboBox.setBounds(171, 63, 131, 20);
		panel_1.add(comboBox);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panel_1.setVisible(false);
			}
		});
		btnBack.setBounds(31, 148, 128, 23);
		panel_1.add(btnBack);
		
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

public void nextMove(String m,JTextArea text,JButton btnNewButton,JButton btnNewButton_1,JButton btnNewButton_2,JButton btnNewButton_3){
	if(currentGame==1){
		if(game1.losscheck()==0 && game1.wincheck()==0){
			
			game1.getHero().HeroMove(game1.getMap(), m);
			game1.getGuard().GuardMove(game1.getMap());
			
			
			if(game1.losscheck()==1){
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
			
			game2.getHero().HeroMove(game2.getMap(), m);
			game2.OgreMove();
			
			
			if(game2.losscheckkeep()==1){
				btnNewButton.setEnabled(false);
				btnNewButton_1.setEnabled(false);
				btnNewButton_2.setEnabled(false);
				btnNewButton_3.setEnabled(false);
			}
			
			
			text.setText(to_String(game2.getMap()));

			if(game2.wincheck()==1){
				btnNewButton.setEnabled(false);
				btnNewButton_1.setEnabled(false);
				btnNewButton_2.setEnabled(false);
				btnNewButton_3.setEnabled(false);
			}
			
		}
	}	
}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	nextMove("d",textArea,btnDown,btnUp,btnL,btnR);
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	nextMove("d",textArea,btnDown,btnUp,btnL,btnR);
}

@Override
public void keyTyped(KeyEvent e) {
	nextMove("d",textArea,btnDown,btnUp,btnL,btnR);
}
}
