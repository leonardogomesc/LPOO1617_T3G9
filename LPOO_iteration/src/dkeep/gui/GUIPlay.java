package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

import dkeep.logic.Game;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class GUIPlay {

	private JFrame frmDungeonKeep;
	private JTextField OgreInfo;
	private int gameState;
	private int levelState;
	private JLabel lblOut;
	private JTextArea txtBoard;
	int[] info;
	JButton btnUp;
	JButton btnDown;
	JButton btnLeft;
	JButton btnRight;
	
	private Game game;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIPlay window = new GUIPlay();
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
	public GUIPlay() {
		this.gameState=-1;
		this.levelState=0;
		this.game=new Game();initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDungeonKeep = new JFrame();
		frmDungeonKeep.setTitle("Dungeon Keep \u00AE");
		frmDungeonKeep.setResizable(false);
		frmDungeonKeep.setBounds(100, 100, 529, 419);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblNumberOfOgres.setBounds(30, 20, 127, 14);
		frmDungeonKeep.getContentPane().add(lblNumberOfOgres);
		
		JLabel lblGuardType = new JLabel("Guard Personality");
		lblGuardType.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblGuardType.setBounds(30, 47, 127, 14);
		frmDungeonKeep.getContentPane().add(lblGuardType);
		
		OgreInfo = new JTextField();
		OgreInfo.setToolTipText("Choose a number between 1 and 5");
		OgreInfo.setFont(new Font("Courier New", Font.BOLD, 11));
		OgreInfo.setBounds(167, 16, 86, 20);
		frmDungeonKeep.getContentPane().add(OgreInfo);
		OgreInfo.setColumns(10);
		
		JComboBox<String> GuardInfo = new JComboBox<String>();
		GuardInfo.setToolTipText("Choose the personality of the guard");
		GuardInfo.setFont(new Font("Courier New", Font.BOLD, 11));
		GuardInfo.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Rookie", "Drunken", "Suspicious"}));
		GuardInfo.setBounds(167, 44, 86, 20);
		frmDungeonKeep.getContentPane().add(GuardInfo);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnNewGame.setBounds(322, 75, 153, 23);
		frmDungeonKeep.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnExit.setBounds(322, 319, 153, 23);
		frmDungeonKeep.getContentPane().add(btnExit);
		
		btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnUp.setBounds(363, 149, 73, 23);
		frmDungeonKeep.getContentPane().add(btnUp);
		
		btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnDown.setBounds(363, 212, 73, 23);
		frmDungeonKeep.getContentPane().add(btnDown);
		
		btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnLeft.setBounds(322, 181, 73, 23);
		frmDungeonKeep.getContentPane().add(btnLeft);
		
		btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnRight.setBounds(402, 181, 73, 23);
		frmDungeonKeep.getContentPane().add(btnRight);
		
		lblOut = new JLabel("");
		lblOut.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblOut.setBounds(20, 352, 270, 27);
		frmDungeonKeep.getContentPane().add(lblOut);
		
		txtBoard = new JTextArea();
		txtBoard.setEditable(false);
		txtBoard.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtBoard.setBounds(20, 75, 270, 270);
		frmDungeonKeep.getContentPane().add(txtBoard);
		
		
		btnNewGame.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				game=new Game();
				int oInf=0, gInfo=-1;
				
				try
				{
					oInf=Integer.parseInt(OgreInfo.getText());
				} catch(Exception e1) {
					lblOut.setText("Invalid choices... Try again! ;)");
				}
				switch(GuardInfo.getSelectedItem().toString())
				{
				case "Rookie":
					gInfo=0;
					break;
				case "Drunken":
					gInfo=1;
					break;
				case "Suspicious":
					gInfo=2;
					break;
				}
				if(((oInf>0)&&(oInf<=5)) && (-1!=gInfo))
				{
					info=new int[] {gInfo,oInf};
					lblOut.setText("Use the buttons to move! Good luck! ;)");
					btnUp.setEnabled(true);
					btnDown.setEnabled(true);
					btnLeft.setEnabled(true);
					btnRight.setEnabled(true);
					try {
						gameState=game.nextLevel(info);
						txtBoard.setText(game.getMap());
					} catch (IOException e1) {
						lblOut.setText(".map file not found!");
						return;
					}
				}
				else
					lblOut.setText("Invalid choices... Try again! ;)");
					
			}
		});
		
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		btnUp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				levelState=game.moveHero('w');
				txtBoard.setText(game.getMap());
				if(!checkWinLoss())
					reset();
			}
		});
		
		btnDown.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				levelState=game.moveHero('s');
				txtBoard.setText(game.getMap());
				if(!checkWinLoss())
					reset();
			}
		});
		
		btnLeft.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				levelState=game.moveHero('a');
				txtBoard.setText(game.getMap());
				if(!checkWinLoss())					
					reset();
			}
		});
		
		btnRight.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				levelState=game.moveHero('d');
				txtBoard.setText(game.getMap());
				if(!checkWinLoss())
					reset();
			}
		});	
		
	}

	private void reset()
	{
		btnUp.setEnabled(false);
		btnDown.setEnabled(false);
		btnLeft.setEnabled(false);
		btnRight.setEnabled(false);
	}
	private boolean checkWinLoss()
	{
		if(Game.WIN==levelState){
			try {
				gameState=game.nextLevel(info);
				txtBoard.setText(game.getMap());
			} catch (IOException e1) {
				lblOut.setText(".map file not found!");
				return false;
			}

			if(0==gameState)
			{
				lblOut.setText("You won!!!! ;)");
				txtBoard.setText("");
				return false;

			}
		}
		if(Game.LOSS==levelState)
		{
			lblOut.setText("You lost... :( Better luck next time!");
			txtBoard.setText("");
			return false;
		}			

		return true;
	}
}
