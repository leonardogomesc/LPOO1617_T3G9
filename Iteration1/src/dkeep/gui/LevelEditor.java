package dkeep.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

	//Components
	private JPanel contentPane;
	private JTextField textX;
	private JTextField textY;
	private JPanel panelTools;
	private JPanel inputPanel;
	private JButton btnCancel;
	private JButton btnSave;
	private JButton btnTest;
	private JButton btnHero;
	private JButton btnDoor;
	private JButton btnWall;
	private JButton btnOgre;
	private JButton btnKey;
	private JButton btnBat;
	private JButton btnClear;
	private JButton btnEraseCell;
	
	private static final int MIN_SIZE = 8;
	private static final int MAX_SIZE = 15;
	
	int testing=0;
	private LevelName newName=null;
	Game game=null;
	
	//Map
	char board[][]=new char[1][1];
	int doorPos[][]=new int[1][2];
	int keyPos[]=new int[2];
	
	//Ogre
	int ogrePos[]=new int[2];
	int batPos[]=new int[2];
	int numberOfOgres;
	
	//Hero
	int heroPos[]=new int[2];
	int basher;
	
	int keyPlaced=0;
	int heroPlaced=0;
	int ogrePlaced=0;
	int batPlaced=0;
	int doorPlaced=0;
	
	
	JCheckBox checkBox;
	
	String option="";
	JLabel lblOutput;
	
	
	
	private int comp=0,larg=0;
	JTextField textField;

	/**
	 * Create the frame.
	 */
	public LevelEditor() {
		InitContentPane();
		InitFrame();
		InitInputPanel_PanelTools();
		InitLblOutput();
		initEditorBtns();
		InitLbls();
		InitTextX();
		InitTextY();		
		InitOptionBtns(); }
	
	/**
	 * Functions called by class constructor
	 */
	private void InitContentPane(){
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	private void InitFrame(){
		this.setResizable(false);
		this.setBounds(100, 100, 673, 539);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.getContentPane().setLayout(null);
	}
	
	private void InitLblOutput(){
		lblOutput = new JLabel("");
		lblOutput.setBounds(10, 13, 450, 15);
		contentPane.add(lblOutput);
	}
	
	
	private void InitBtnCancel(){
		btnCancel = new JButton("Exit");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				setVisible(false); 
				if(newName!=null) {newName.setVisible(false);}} });
		btnCancel.setBounds(462, 465, 195, 23);
		this.getContentPane().add(btnCancel);
	}
	private void InitBtnSave(){
		newName = new LevelName(this);
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(playable()==0){
				if(checkBox.isSelected()){basher=1; }
				else{basher=0; }
				newName .setVisible(true); } } });
		btnSave.setBounds(462, 431, 95, 23);
		this.getContentPane().add(btnSave);
	}
	private void InitBtnTest(){
		btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(testing==1){
					testing=0;
					btnTest.setText("Test"); }
				else if(testing==0){
				if(playable()==0){
					if(game!=null){
						for(int i=0;i<game.getOgre().length;i++){game.getOgre()[i].OgreErase(game.getMap()); }
						game.getMap().getMap()[ogrePos[0]][ogrePos[1]]='O';
						game.getMap().getMap()[batPos[0]][batPos[1]]='*'; }
				
				if(checkBox.isSelected()){basher=1; }
				else{basher=0; }
						
				Ogre o[]=new Ogre[Integer.parseInt(textField.getText())];
				for(int i=0;i<Integer.parseInt(textField.getText());i++){
					o[i]=new Ogre(new int[]{ogrePos[0],ogrePos[1]},new int[]{batPos[0],batPos[1]}); }
				
				Hero h=new Hero(heroPos,basher);
				Map m= new Map(board,doorPos,keyPos,2);
				
				game=new Game(m,h,o);
				
				testing=1;
				inputPanel.repaint();
				btnTest.setText("Stop Test");
				inputPanel.requestFocusInWindow();
				lblOutput.setText("You can play now"); }  } } });
		btnTest.setBounds(562, 431, 95, 23);
		this.getContentPane().add(btnTest);
	}
	private void InitBtnClear(){
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<board.length;i++){
					for(int j=0;j<board[i].length;j++){
						board[i][j]=' '; } }
				testing=0; inputPanel.repaint(); } });
		btnClear.setBounds(462, 388, 95, 23);
		contentPane.add(btnClear);
	}
	private void InitBtnEraseCell(){
		btnEraseCell = new JButton("Erase Cell");
		btnEraseCell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to Erase Cell:");
				option="eraseCell";
			}
		});
		btnEraseCell.setBounds(562, 388, 95, 23);
		contentPane.add(btnEraseCell); }
	
	private void initEditorBtns(){
		InitBtnCancel();
		InitBtnSave();
		InitBtnTest();
		InitBtnClear();
		InitBtnEraseCell(); }
	
	
	private void InitLbls(){
		JLabel lblSize = new JLabel("Size:");
		lblSize.setBounds(479, 13, 27, 16);
		this.getContentPane().add(lblSize);
		
		JLabel lblX = new JLabel("x");
		lblX.setBounds(590, 13, 26, 16);
		this.getContentPane().add(lblX);
		
		JLabel lblAdd = new JLabel("Add:");
		lblAdd.setBounds(26, 11, 55, 16);
		panelTools.add(lblAdd);
	}
	
	private void InitTextX(){
		textX = new JTextField();
		textX.setBounds(530, 11, 53, 20);
		this.getContentPane().add(textX);
		textX.setColumns(10);
		textX.getDocument().addDocumentListener(this);
	}
	private void InitTextY(){
		textY = new JTextField();
		textY.setText("");
		textY.setBounds(603, 11, 54, 20);
		this.getContentPane().add(textY);
		textY.setColumns(10);
		textY.getDocument().addDocumentListener(this);
	}
	
	private void InitInputPanel_PanelTools(){
		inputPanel = new GraphicsAndListeners(this);
		//inputPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		inputPanel.setBounds(10, 38, 450, 450);
		contentPane.add(inputPanel);
		panelTools = new JPanel();
		panelTools.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelTools.setBounds(462, 38, 195, 339);
		contentPane.add(panelTools);
		panelTools.setVisible(false);
		panelTools.setLayout(null);
	}
	
	
	private void InitBtnHero(){
		btnHero = new JButton("Hero");
		btnHero.setBounds(10, 140, 98, 26);
		panelTools.add(btnHero);
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place the hero:");
				option="hero"; } });
		checkBox = new JCheckBox("Sword");
		checkBox.setBounds(123, 141, 66, 24);
		panelTools.add(checkBox);
	}
	private void InitBtnDoor(){
		btnDoor = new JButton("Door");
		btnDoor.setBounds(28, 44, 98, 26);
		panelTools.add(btnDoor);
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place doors:");
				option="door";
			}
		});
	}
	private void InitBtnWall(){
		btnWall = new JButton("Wall");
		btnWall.setBounds(28, 92, 98, 26);
		panelTools.add(btnWall);
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place walls:");
				option="wall";
			}
		});
	}
	private void InitBtnOgre(){
		btnOgre = new JButton("Ogre");
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place Ogre:");
				option="ogre"; } });
		btnOgre.setBounds(28, 272, 95, 26);
		panelTools.add(btnOgre);
		textField = new JTextField();
		textField.setBounds(152, 308, 37, 20);
		panelTools.add(textField);
		textField.setColumns(10);
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres:");
		lblNumberOfOgres.setBounds(10, 309, 130, 14);
		panelTools.add(lblNumberOfOgres); }
	
	private void InitBtnKey(){
		btnKey = new JButton("Key");
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place key:");
				option="key";
			}
		});
		btnKey.setBounds(28, 198, 95, 26);
		panelTools.add(btnKey);
	}
	private void InitBtnBat(){
		btnBat = new JButton("Bat");
		btnBat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOutput.setText("Click to place bat:");
				option="bat";
			}
		});
		btnBat.setBounds(28, 235, 95, 26);
		panelTools.add(btnBat);
	}
	
	private void InitOptionBtns(){
		InitBtnHero();
		InitBtnDoor();
		InitBtnWall();
		InitBtnOgre();
		InitBtnKey();
		InitBtnBat();}
	
	
	/**
	 * Listeners
	 */
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
			for(int i=0;i<board.length;i++){
				for(int i2=0;i2<board[i].length;i2++){
					board[i][i2]=' ';
					
				}
			}
			testing=0;
			fillWalls();}
		
	}
	
	/**
	 * auxiliary methods
	 */
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
					btnTest.setText("Test");
				}
			
				if(game.wincheck()==1){
					lblOutput.setText("You won!");
					testing=0;
					btnTest.setText("Test");
				}
				
			}
			
		inputPanel.repaint();
		
	}
	
	public int playable(){
		int result=0;
		int i;
		int j;
		
		if(!(keyPlaced==1 && heroPlaced==1 && ogrePlaced==1 && batPlaced==1 && doorPlaced==1) && result==0){
			result=1;
		}
		
		if((Integer.parseInt(textField.getText())>5 || Integer.parseInt(textField.getText())<1) && result==0){
			result=1;
		}
		
		for(i=0;i<board[0].length;i++){
			if(!(board[0][i]=='I' || board[0][i]=='X')&&result==0){
				
				result=1;
			}
		}
		
		for(i=0;i<board[board.length-1].length;i++){
			if(!(board[board.length-1][i]=='I' || board[board.length-1][i]=='X')&&result==0){
				result=1;
			}
		}
		
		for(i=0;i<board.length;i++){
			if(!(board[i][0]=='I' || board[i][0]=='X')&&result==0){
				result=1;
			}
		}
		
		for(i=0;i<board.length;i++){
			if(!(board[i][board[i].length-1]=='I' || board[i][board[i].length-1]=='X')&&result==0){
				result=1;
			}
		}
		
		
		int arrayToCheck[][]={keyPos,ogrePos,batPos,heroPos};
		
		for(i=0;i<arrayToCheck.length;i++){
			for(j=0;j<arrayToCheck.length;j++){
				if(i!=j){
					if((arrayToCheck[i][0]==arrayToCheck[j][0]) && (arrayToCheck[i][1]==arrayToCheck[j][1])&& result==0){
						result=1;
					}
				}
			}
		}
		
		for(i=0;i<arrayToCheck.length;i++){
			if((arrayToCheck[i][0]==doorPos[0][0]) && (arrayToCheck[i][1]==doorPos[0][1])&& result==0){
				result=1;
			}
		}
		
		if(!((batPos[0]==ogrePos[0]+1 && batPos[1]==ogrePos[1])||
		(batPos[0]==ogrePos[0]-1 && batPos[1]==ogrePos[1])||
		(batPos[0]==ogrePos[0] && batPos[1]==ogrePos[1]+1)||
		(batPos[0]==ogrePos[0] && batPos[1]==ogrePos[1]-1)) && result==0){
			
			result=1;
		}
		
		return result;
	}
}