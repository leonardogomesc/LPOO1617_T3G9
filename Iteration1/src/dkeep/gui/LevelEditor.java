package dkeep.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import dkeep.logic.Map;

public class LevelEditor extends JFrame 
implements ActionListener, DocumentListener{

	private JPanel contentPane;
	private final ButtonGroup LevelType = new ButtonGroup();
	private JRadioButton rdbtnKeep;
	private JRadioButton rdbtnDungeon;
	private JTextField textX;
	private JTextField textY;
	private JPanel keep;
	private JPanel dungeon;
	private JPanel GraphicPanel;
	private JPanel panelTools;
	private JPanel inputPanel;
	private static final int MIN_SIZE = 8;
	private static final int MAX_SIZE = 15;
	private Map map;
	private char board[][]=new char[1][1];
	private int doorPos[][];
	private int keyPos[];
	private int mapType; 
	
	private String option="";
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
		btnSave.setBounds(462, 431, 95, 23);
		this.getContentPane().add(btnSave);

		JButton btnTest = new JButton("Test");
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

		rdbtnKeep = new JRadioButton("Keep");
		rdbtnKeep.setBounds(54, 32, 54, 24);
		panelTools.add(rdbtnKeep);

		LevelType.add(rdbtnKeep);

		JLabel lblLeveltype = new JLabel("LevelType:");
		lblLeveltype.setBounds(28, 11, 60, 16);
		panelTools.add(lblLeveltype);

		rdbtnDungeon = new JRadioButton("Dungeon");
		rdbtnDungeon.setBounds(54, 57, 75, 24);
		panelTools.add(rdbtnDungeon);
		LevelType.add(rdbtnDungeon);

		rdbtnDungeon.addActionListener(this);
		rdbtnKeep.addActionListener(this);
		JButton btnHero = new JButton("Hero");
		btnHero.setBounds(28, 194, 98, 26);
		panelTools.add(btnHero);
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place the hero:");
				option="hero";
			}
		});

		JButton btnDoor = new JButton("Door");
		btnDoor.setBounds(28, 117, 98, 26);
		panelTools.add(btnDoor);
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place doors:");
				option="door";
			}
		});

		JButton btnWall = new JButton("Wall");
		btnWall.setBounds(28, 156, 98, 26);
		panelTools.add(btnWall);
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place walls:");
				option="wall";
			}
		});
		dungeon = new JPanel();
		dungeon.setBounds(29, 253, 143, 63);
		panelTools.add(dungeon);
		dungeon.setVisible(false);
		dungeon.setLayout(null);

		JButton btnGuard = new JButton("Guard");
		btnGuard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click each cell of the guard's path (should end where it starts):");
				option="guard";
			}
		});
		btnGuard.setBounds(0, 0, 94, 26);
		dungeon.add(btnGuard);

		JButton btnLever = new JButton("Lever");
		btnLever.setBounds(0, 37, 94, 26);
		dungeon.add(btnLever);
		btnLever.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place the lever:");
				option="lever";
			}
		});

		keep = new JPanel();
		keep.setBounds(28, 220, 101, 107);
		panelTools.add(keep);
		keep.setVisible(false);
		keep.setLayout(null);

		JCheckBox chckbxBasher = new JCheckBox("Basher");
		chckbxBasher.setBounds(24, 2, 66, 24);
		keep.add(chckbxBasher);

		JButton btnOgre = new JButton("Ogre");
		btnOgre.setBounds(0, 34, 95, 26);
		keep.add(btnOgre);
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place the ogre (and then again for its basher):");
				option="ogre";
			}
		});

		JButton btnKey = new JButton("Key");
		btnKey.setBounds(0, 72, 95, 26);
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place the key:");
				option="key";
			}
		});
		keep.add(btnKey);

		JLabel lblAdd = new JLabel("Add:");
		lblAdd.setBounds(28, 89, 55, 16);
		panelTools.add(lblAdd);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(462, 388, 95, 23);
		contentPane.add(btnClear);
		
		JButton btnEraseCell = new JButton("Erase Cell");
		btnEraseCell.setBounds(562, 388, 95, 23);
		contentPane.add(btnEraseCell);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==rdbtnKeep)
		{
			this.mapType=2;
			keep.setVisible(true);
			dungeon.setVisible(false);
			lblOutput.setText("");
			option="";
		}
		else
			if(e.getSource()==rdbtnDungeon)
			{
				this.mapType=1;
				keep.setVisible(false);
				dungeon.setVisible(true);
				lblOutput.setText("");
				option="";
			}
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
			fillWalls();}
		
	}
	
	public Map getMap()
	{
		this.map=new Map(board,doorPos, keyPos, mapType);
		return this.map;
	}
	private void fillWalls(){
		for(int i=0;i<board.length;++i){
			board[i][0]='X';
			board[i][board[0].length-1]='X';}
		for(int i=0;i<board[0].length;++i) {board[0][i]='X';}
		for(int i=0;i<board[0].length;++i) {board[board.length-1][i]='X';}
		this.inputPanel.repaint();
	}
}