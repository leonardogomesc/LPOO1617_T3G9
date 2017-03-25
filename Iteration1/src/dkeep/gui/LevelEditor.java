package dkeep.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dkeep.logic.Game;
import dkeep.logic.Hero;
import dkeep.logic.Map;
import dkeep.logic.Ogre;

public class LevelEditor extends JFrame 
implements ActionListener, DocumentListener{

	private JPanel contentPane;
	private final ButtonGroup LevelType = new ButtonGroup();
	private JTextField textX;
	private JTextField textY;
	private JPanel GraphicPanel;
	private JPanel panelTools;
	private JPanel inputPanel;
	private static final int MIN_SIZE = 8;
	private static final int MAX_SIZE = 15;
	
	int testing;
	
	Game game;
	
	//Map
	char board[][]=new char[1][1];
	int doorPos[][]=new int[1][2];
	int keyPos[]=new int[2];
	
	//Ogre
	int ogrePos[]=new int[2];
	int batPos[]=new int[2];
	
	//Hero
	int heroPos[]=new int[2];
	int basher;
	
	
	JCheckBox checkBox;
	
	String option="";
	JLabel lblOutput;
	
	
	
	private int comp=0,larg=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelEditor frame = new LevelEditor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LevelEditor() {
		testing=0;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		this.setResizable(false);
		this.setBounds(100, 100, 673, 539);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		lblOutput = new JLabel("");
		lblOutput.setBounds(10, 13, 450, 15);
		contentPane.add(lblOutput);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { setVisible(false);} });
		btnCancel.setBounds(462, 465, 195, 23);
		this.getContentPane().add(btnCancel);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(462, 431, 95, 23);
		this.getContentPane().add(btnSave);

		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int b;
				if(checkBox.isSelected()){
					b=1;
				}
				else{
					b=0;
				}
				
				Ogre o=new Ogre(ogrePos,batPos);
				Ogre o2[]={o};
				Hero h=new Hero(heroPos,b);
				Map m= new Map(board,doorPos,keyPos,2);
				
				game=new Game(m,h,o2);
				
				inputPanel.repaint();
				testing=1;
				inputPanel.requestFocusInWindow();
				lblOutput.setText("You can play now");
				
			}
		});
		btnTest.setBounds(562, 431, 95, 23);
		this.getContentPane().add(btnTest);

		JLabel lblSize = new JLabel("Size:");
		lblSize.setBounds(479, 13, 27, 16);
		this.getContentPane().add(lblSize);

		textX = new JTextField();
		textX.setBounds(530, 11, 53, 20);
		this.getContentPane().add(textX);
		textX.setColumns(10);
		textX.getDocument().addDocumentListener(this);
		//textX.getDocument().putProperty("name", "height");
		textY = new JTextField();
		textY.setText("");
		textY.setBounds(603, 11, 54, 20);
		this.getContentPane().add(textY);
		//textX.getDocument().putProperty("name", "length");
		textY.setColumns(10);
		textY.getDocument().addDocumentListener(this);
		JLabel lblX = new JLabel("x");
		lblX.setBounds(590, 13, 26, 16);
		this.getContentPane().add(lblX);

		inputPanel = new GraphicsAndListeners(this);
		inputPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		inputPanel.setBounds(10, 38, 450, 450);
		contentPane.add(inputPanel);

		panelTools = new JPanel();
		panelTools.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelTools.setBounds(462, 38, 195, 339);
		contentPane.add(panelTools);
		panelTools.setVisible(false);
		panelTools.setLayout(null);
		JButton btnHero = new JButton("Hero");
		btnHero.setBounds(10, 140, 98, 26);
		panelTools.add(btnHero);
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place the hero:");
				option="hero";
			}
		});

		JButton btnDoor = new JButton("Door");
		btnDoor.setBounds(28, 44, 98, 26);
		panelTools.add(btnDoor);
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place doors:");
				option="door";
			}
		});

		JButton btnWall = new JButton("Wall");
		btnWall.setBounds(28, 92, 98, 26);
		panelTools.add(btnWall);
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place walls:");
				option="wall";
			}
		});

		JLabel lblAdd = new JLabel("Add:");
		lblAdd.setBounds(26, 11, 55, 16);
		panelTools.add(lblAdd);
		
		checkBox = new JCheckBox("Basher");
		checkBox.setBounds(123, 141, 66, 24);
		panelTools.add(checkBox);
		
		JButton button = new JButton("Ogre");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place Ogre:");
				option="ogre";
			}
		});
		button.setBounds(31, 290, 95, 26);
		panelTools.add(button);
		
		JButton button_1 = new JButton("Key");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place key:");
				option="key";
			}
		});
		button_1.setBounds(31, 191, 95, 26);
		panelTools.add(button_1);
		
		JButton btnBat = new JButton("Bat");
		btnBat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place bat:");
				option="bat";
			}
		});
		btnBat.setBounds(31, 241, 95, 26);
		panelTools.add(btnBat);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<board.length;i++){
					for(int j=0;j<board[i].length;j++){
						board[i][j]=' ';
					}
				}
				testing=0;
				inputPanel.repaint();
				
			}
		});
		btnClear.setBounds(462, 388, 95, 23);
		contentPane.add(btnClear);
		
		JButton btnEraseCell = new JButton("Erase Cell");
		btnEraseCell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to Erase Cell:");
				option="eraseCell";
			}
		});
		btnEraseCell.setBounds(562, 388, 95, 23);
		contentPane.add(btnEraseCell);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		validDimensions(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		validDimensions(e);			
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		validDimensions(e);
	}
	
	private void validDimensions(DocumentEvent e)
	{
		if((e.getDocument()==textX.getDocument()))
		{
			try { this.larg=Integer.parseInt(this.textX.getText()); } 
			catch(Exception e0) { 
				this.textX.setText("");
				this.panelTools.setVisible(false);}
		} else if((e.getDocument()==textY.getDocument())) {
			try	{ this.comp=Integer.parseInt(this.textY.getText()); } 
			catch(Exception e1) {
				this.textY.setText("");
				this.panelTools.setVisible(false);}
		}

		if((this.comp<MIN_SIZE) || (this.comp>MAX_SIZE)||(this.larg<MIN_SIZE)||(this.larg>MAX_SIZE))
			this.panelTools.setVisible(false);
		else {
			this.panelTools.setVisible(true); 
			board= new char[comp][larg];
			testing=0;
			fillWalls();}
		
	}
	

	private void fillWalls(){
		for(int i=0;i<board.length;++i){
			board[i][0]='X';
			board[i][board[0].length-1]='X';}
		for(int i=0;i<board[0].length;++i) {board[0][i]='X';}
		for(int i=0;i<board[0].length;++i) {board[board.length-1][i]='X';}
		this.inputPanel.repaint();
	}
	
	public void nextMove(String m){

			if(game.losscheckkeep()==0 && game.wincheck()==0){
				lblOutput.setText("You can play now");
				
				game.getHero().HeroMove(game.getMap(), m);
				game.OgreMove();
				
				
				if(game.losscheckkeep()==1){
					lblOutput.setText("You lost!");
					testing=0;
				}
				

				if(game.wincheck()==1){
					lblOutput.setText("You won!");
					testing=0;
				}
				
			}
			
		inputPanel.repaint();
		
	}
}