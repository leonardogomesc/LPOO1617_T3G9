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

	private static final int MIN_SIZE = 8;
	private static final int MAX_SIZE = 15;
	
	
	
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
		this.setBounds(100, 100, 564, 431);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		contentPane.setLayout(null);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(299, 363, 98, 26);
		this.getContentPane().add(btnCancel);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(54, 363, 98, 26);
		this.getContentPane().add(btnSave);

		JButton btnTest = new JButton("Test");
		btnTest.setBounds(172, 363, 98, 26);
		this.getContentPane().add(btnTest);

		JLabel lblSize = new JLabel("Size:");
		lblSize.setBounds(48, 11, 27, 16);
		this.getContentPane().add(lblSize);

		textX = new JTextField();
		textX.setBounds(99, 9, 53, 20);
		this.getContentPane().add(textX);
		textX.setColumns(10);
		textX.getDocument().addDocumentListener(this);
		//textX.getDocument().putProperty("name", "height");
		textY = new JTextField();
		textY.setText("");
		textY.setBounds(172, 9, 54, 20);
		this.getContentPane().add(textY);
		//textX.getDocument().putProperty("name", "length");
		textY.setColumns(10);
		textY.getDocument().addDocumentListener(this);
		JLabel lblX = new JLabel("x");
		lblX.setBounds(159, 11, 26, 16);
		this.getContentPane().add(lblX);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 38, 340, 270);
		contentPane.add(panel);

		panelTools = new JPanel();
		panelTools.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelTools.setBounds(353, 11, 195, 339);
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

		JButton btnDoor = new JButton("Door");
		btnDoor.setBounds(28, 117, 98, 26);
		panelTools.add(btnDoor);

		JButton btnWall = new JButton("Wall");
		btnWall.setBounds(28, 156, 98, 26);
		panelTools.add(btnWall);
		
				dungeon = new JPanel();
				dungeon.setBounds(29, 253, 143, 63);
				panelTools.add(dungeon);
				dungeon.setVisible(false);
				dungeon.setLayout(null);
				
						JButton btnGuard = new JButton("Guard");
						btnGuard.setBounds(0, 0, 94, 26);
						dungeon.add(btnGuard);
						
								JButton btnLever = new JButton("Lever");
								btnLever.setBounds(0, 37, 94, 26);
								dungeon.add(btnLever);

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

		JButton btnKey = new JButton("Key");
		btnKey.setBounds(0, 72, 95, 26);
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		keep.add(btnKey);

		JLabel lblAdd = new JLabel("Add:");
		lblAdd.setBounds(28, 89, 55, 16);
		panelTools.add(lblAdd);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(54, 319, 98, 23);
		contentPane.add(btnClear);
		
		JButton btnEraseCell = new JButton("Erase Cell");
		btnEraseCell.setBounds(172, 319, 98, 23);
		contentPane.add(btnEraseCell);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==rdbtnKeep)
		{
			keep.setVisible(true);
			dungeon.setVisible(false);
		}
		else
			if(e.getSource()==rdbtnDungeon)
			{
				keep.setVisible(false);
				dungeon.setVisible(true);
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
			try
			{
				this.larg=Integer.parseInt(textX.getText());
				if((larg>MIN_SIZE) && (larg < MAX_SIZE) && (comp!=0)) panelTools.setVisible(true);
				else if(comp!=0) larg=0;
			} 
			catch(Exception e0) {textX.setText("");}
		} else if((e.getDocument()==textY.getDocument())) {
			try
			{
				this.comp=Integer.parseInt(textY.getText());
				if((comp>MIN_SIZE) && (comp < MAX_SIZE) && (larg!=0)) panelTools.setVisible(true);
				else if(larg!=0) comp=0;
			} 
			catch(Exception e1) {textY.setText("");}
		}
	}
}