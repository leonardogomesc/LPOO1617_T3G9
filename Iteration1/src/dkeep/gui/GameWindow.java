package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameWindow {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
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
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 784, 561);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnUp = new JButton("Up");
		JButton btnDown = new JButton("Down");
		JButton btnRight = new JButton("Right");
		JButton btnLeft = new JButton("Left");
		JButton btnExit = new JButton("Exit");
		JButton btnNewGame = new JButton("New Game");
		JButton btnLevelEditor = new JButton("Level Editor");
		
		textField = new JTextField();
		
		JComboBox comboBox = new JComboBox();
		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUp.setBounds(615, 193, 80, 30);
		panel.add(btnUp);
		
	
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDown.setBounds(620, 282, 80, 30);
		panel.add(btnDown);
		
	
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRight.setBounds(674, 238, 80, 30);
		panel.add(btnRight);
		
		
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLeft.setBounds(560, 238, 80, 30);
		panel.add(btnLeft);
		
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExit.setBounds(620, 495, 89, 23);
		panel.add(btnExit);
		
		
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewGame.setBounds(591, 72, 131, 23);
		panel.add(btnNewGame);
		
		
		btnLevelEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLevelEditor.setBounds(589, 430, 149, 23);
		panel.add(btnLevelEditor);
		
		JLabel label = new JLabel("Number of Ogres");
		label.setBounds(39, 31, 118, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Guard Personality");
		label_1.setBounds(39, 76, 109, 14);
		panel.add(label_1);
		
		
		textField.setColumns(10);
		textField.setBounds(177, 28, 35, 20);
		panel.add(textField);
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Rookie", "Drunken", "Suspicious"}));
		comboBox.setBounds(178, 73, 131, 20);
		panel.add(comboBox);
	}
}
