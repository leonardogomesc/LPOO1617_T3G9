package dkeep.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dkeep.logic.Game;
import dkeep.logic.Hero;
import dkeep.logic.Map;
import dkeep.logic.Ogre;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class LevelName extends JFrame 
implements DocumentListener{

	private JPanel contentPane;
	private JTextField NameInput;
	private JLabel lblStatus;
	private JButton btnSave;
	private JButton btnCancel;
	private LevelEditor newLevel;
	private String levelName;

	/**
	 * Create the frame.
	 */
	public LevelName(LevelEditor newLevel) {
		this.newLevel=newLevel;
		InitFrame();
		InitNameInput();
		InitBtns();
		InitLabels(); }
	
	private void InitFrame(){
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 230, 152);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); }
	
	private void InitLabels(){
		JLabel lblmap = new JLabel(".map");
		lblmap.setBounds(178, 40, 46, 14);
		contentPane.add(lblmap);
		
		JLabel lblLevelName = new JLabel("Level name:");
		lblLevelName.setBounds(10, 9, 89, 14);
		contentPane.add(lblLevelName);
		
		lblStatus = new JLabel("");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setForeground(Color.BLACK);
		lblStatus.setBounds(10, 57, 166, 14);
		contentPane.add(lblStatus); }
	
	private void InitBtns(){
		InitBtnSave();
		InitBtnCancel(); }
	
	private void InitBtnSave(){
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ogre[] ogres={new Ogre(newLevel.ogrePos, newLevel.batPos)};
				Map map=new Map(newLevel.board, newLevel.doorPos, newLevel.keyPos, newLevel.basher+1);
				Game newGame=new Game(map, new Hero(newLevel.heroPos, newLevel.basher),	ogres );
				newGame.SaveLevelFile(levelName); 
				setVisible(false); 
				} });
		btnSave.setEnabled(false);
		btnSave.setBounds(10, 82, 89, 23);
		contentPane.add(btnSave); }
	
	private void InitBtnCancel(){
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {setVisible(false); }
		});
		btnCancel.setBounds(109, 82, 89, 23);
		contentPane.add(btnCancel); }
	
	private void InitNameInput(){
		NameInput = new JTextField();
		NameInput.setBounds(10, 37, 166, 20);
		contentPane.add(NameInput);
		NameInput.setColumns(10);
		NameInput.getDocument().addDocumentListener(this); }

	@Override
	public void insertUpdate(DocumentEvent e) {validName(e); }

	@Override
	public void removeUpdate(DocumentEvent e) {validName(e); }

	@Override
	public void changedUpdate(DocumentEvent e) {validName(e); }
	
	private void validName(DocumentEvent e){
		if((e.getDocument()==NameInput.getDocument()))
		{   File f = new File("src/dkeep/logic/levels/"+NameInput.getText()+".map");
			if(f.exists() && !f.isDirectory()) {
				lblStatus.setText("Sorry, name taken... :(");
				btnSave.setEnabled(false);}
			else {
				lblStatus.setText("");
				btnSave.setEnabled(true);
				levelName=NameInput.getText(); } } }
}
