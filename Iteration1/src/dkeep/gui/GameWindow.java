package dkeep.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dkeep.logic.Game;

public class GameWindow 
implements ActionListener{

	private JFrame frame;
	private JTextField textField;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnRight;
	private JButton btnLeft;
	private JButton btnExit;
	private JButton btnNewGame;
	private JButton btnLevelEditor;
	private JLabel LabelOut;
	private JLabel lblOgreNumber;
	private JPanel gamePanel;
	private JLabel lblGuardPersonality;
	private JComboBox<String> comboBox;
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
					window.frame.setVisible(true); } 
				catch (Exception e) { e.printStackTrace(); }
			} }); }

	/**
	 * Create the application.
	 */
	public GameWindow() {initialize(); }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		InitFrame();
		InitComboBox();
		InitPanel();
		InitBtns();
		InitLabels();
		InitTextField(); }

	//Methods called by initialize()
		
	private void InitLabels(){
		LabelOut = new JLabel("You can start a new Game!");
		LabelOut.setBounds(30, 610, 500, 23);
		frame.getContentPane().add(LabelOut);

		lblOgreNumber = new JLabel("Number of Ogres");
		lblOgreNumber.setBounds(39, 31, 118, 14);
		frame.getContentPane().add(lblOgreNumber);

		lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(39, 76, 109, 14);
		frame.getContentPane().add(lblGuardPersonality); }
	
	private void InitTextField(){
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(177, 28, 35, 20);
		frame.getContentPane().add(textField); 
		gamePanel.requestFocusInWindow(); }
	
	private void InitComboBox(){
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Rookie", "Drunken", "Suspicious"}));
		comboBox.setBounds(178, 73, 131, 20);
		comboBox.setFocusable(false);
		frame.getContentPane().add(comboBox); }
	
	private void InitFrame(){
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);}

	private void InitPanel(){
		gamePanel = new GraphicsAndListeners(this);
		gamePanel.setBounds(30, 127, 450, 450);
		frame.getContentPane().add(gamePanel);
		gamePanel.setLayout(null);
		gamePanel.requestFocusInWindow(); 
	}
	
	private void InitBtns(){
		InitBtnUp();
		InitBtnDown();
		InitBtnLeft();
		InitBtnRight();
		InitBtnExit();
		InitBtnNewGame();
		InitBtnLevelEditor(); }
	
	//methods called by InitBtns()
	private void InitBtnUp(){
		btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.setFocusable(false);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMove("w"); } });
		btnUp.setBounds(615, 193, 80, 30);
		frame.getContentPane().add(btnUp); }
	
	private void InitBtnDown(){
		btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.setFocusable(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMove("s"); } });
		btnDown.setBounds(620, 282, 80, 30);
		frame.getContentPane().add(btnDown); }
	
	private void InitBtnLeft(){
		btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.setFocusable(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMove("a"); } });
		btnLeft.setBounds(560, 238, 80, 30);
		frame.getContentPane().add(btnLeft); }
	
	private void InitBtnRight(){
		btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.setFocusable(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextMove("d"); } });
		btnRight.setBounds(674, 238, 80, 30);
		frame.getContentPane().add(btnRight); }
	
	private void InitBtnExit(){
		btnExit = new JButton("Exit");
		btnExit.setFocusable(false);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); } });
		btnExit.setBounds(620, 495, 89, 23);
		frame.getContentPane().add(btnExit); }
	
	private void InitBtnLevelEditor(){
		btnLevelEditor = new JButton("Level Editor");
		btnLevelEditor.setFocusable(false);
		btnLevelEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LevelEditor newLevel = new LevelEditor();
				newLevel.setVisible(true); } });
		btnLevelEditor.setBounds(589, 430, 149, 23);
		frame.getContentPane().add(btnLevelEditor); }

	private void InitBtnNewGame(){
		btnNewGame = new JButton("New Game");
		btnNewGame.setFocusable(false);
		btnNewGame.addActionListener(this);
		
		btnNewGame.setBounds(591, 72, 131, 23);
		frame.getContentPane().add(btnNewGame); }
	
	private int getOgreInfo(){
		switch(textField.getText()){
		case "1": return 1;
		case "2": return 2;
		case "3": return 3;
		case "4": return 4;
		case "5": return 5;}
		return -1;}
	
	private int getGuardInfo(){
		switch(comboBox.getSelectedIndex()){
		case 1: return 0;
		case 2: return 1;
		case 3: return 2;}
		return -1; }
	
	private void EnableMovement(boolean value){
		btnUp.setEnabled(value);
		btnDown.setEnabled(value);
		btnRight.setEnabled(value);
		btnLeft.setEnabled(value); }
	
	//Auxiliary methods
	public void nextMove(String m){
		int gameState;

		if(currentGame==1){
			if(game.losscheck()==0 && game.wincheck()==0){
				LabelOut.setText("You can play now");

				game.getHero().HeroMove(game.getMap(), m);
				game.getGuard().GuardMove(game.getMap());


				if(game.losscheck()==1){
					LabelOut.setText("You lost!");
					EnableMovement(false);
				}


				if(game.wincheck()==1){
					try {gameState=game.nextLevel(info); } 
					catch (IOException e) {e.printStackTrace(); }

					currentGame=2; } } }
		else if(currentGame==2){
			if(game.losscheckkeep()==0 && game.wincheck()==0){
				LabelOut.setText("You can play now");
				game.getHero().HeroMove(game.getMap(), m);
				game.OgreMove();
			if(game.losscheckkeep()==1){
				LabelOut.setText("You lost!");
				EnableMovement(false); }
			if(game.wincheck()==1){
				LabelOut.setText("You won!");
				EnableMovement(false); } } }
		gamePanel.repaint(); }

	

	@Override
	public void actionPerformed(ActionEvent e) {
		int numberOfOgres=getOgreInfo();
		int guardType=getGuardInfo();
		if(guardType!=-1 && numberOfOgres!=-1){
			info=new int[] {guardType,numberOfOgres};
			game=new Game();

			try {game.nextLevel(info); }
			catch (IOException e1) {e1.printStackTrace(); }
			
			EnableMovement(true);
			currentGame=game.getMap().getMapType();
			LabelOut.setText("You can play now");
			gamePanel.repaint(); }
		else if(guardType==-1 && numberOfOgres==-1){LabelOut.setText("Invalid Number of Ogres and Type of Guard"); }
		else if(guardType==-1){LabelOut.setText("Invalid Type of Guard"); }
		else if(numberOfOgres==-1){LabelOut.setText("Invalid Number of Ogres"); } 
		gamePanel.requestFocusInWindow(); }
}
