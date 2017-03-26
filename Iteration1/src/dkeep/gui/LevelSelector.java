package dkeep.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LevelSelector extends JFrame 
implements DocumentListener{

	private JPanel contentPane;
	private JTextField txtFilename;
	private JButton btnOpen;
	private JButton btnCancel;
	private String levelName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelSelector frame = new LevelSelector();
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
	public LevelSelector() {
		InitFrame();
		InitLbl();
		InitTxtBox();
		InitBtnOpen();
		InitBtnCancel(); }

	private void InitFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 208, 142);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);}

	private void InitLbl(){
		JLabel lblWriteTheFile = new JLabel("Write the file name:");
		lblWriteTheFile.setBounds(10, 11, 137, 14);
		contentPane.add(lblWriteTheFile); }

	private void InitBtnCancel(){
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {setVisible(false); }
		});
		btnCancel.setBounds(101, 67, 80, 23);
		contentPane.add(btnCancel); }

	private void InitBtnOpen(){
		btnOpen = new JButton("Open");
		btnOpen.setEnabled(false);
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		btnOpen.setBounds(10, 67, 80, 23);
		contentPane.add(btnOpen);
	}

	private void InitTxtBox(){
		txtFilename = new JTextField();
		txtFilename.setBounds(10, 36, 171, 20);
		txtFilename.setText("");
		contentPane.add(txtFilename);
		txtFilename.setColumns(10);
		txtFilename.getDocument().addDocumentListener(this);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {validName(e); }

	@Override
	public void removeUpdate(DocumentEvent e) {validName(e); }

	@Override
	public void changedUpdate(DocumentEvent e) {validName(e); }
	
	private void validName(DocumentEvent e){
		if((e.getDocument()==txtFilename.getDocument()))
		{   File f = new File("src/dkeep/logic/levels/"+txtFilename.getText()+".map");
		if(f.exists() && !f.isDirectory()) {btnOpen.setEnabled(true); }
		else {
			btnOpen.setEnabled(false);
			levelName=txtFilename.getText(); } } }
}
