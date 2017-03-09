package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Label;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

public class Window {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
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
	public Window() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(10, 25, 118, 14);
		frame.getContentPane().add(lblNumberOfOgres);
		
		textField = new JTextField();
		textField.setBounds(138, 22, 35, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(10, 58, 109, 14);
		frame.getContentPane().add(lblGuardPersonality);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Rookie", "Suspicious", "Drunken"}));
		comboBox.setBounds(138, 55, 131, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Up");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton.setBounds(442, 234, 73, 20);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Right");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(485, 265, 73, 20);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Left");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(395, 265, 73, 20);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Down");
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.setBounds(442, 296, 73, 20);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(437, 401, 89, 23);
		frame.getContentPane().add(btnExit);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(true);
				btnNewButton_1.setEnabled(true);
				btnNewButton_2.setEnabled(true);
				btnNewButton_3.setEnabled(true);
			}
		});
		btnNewGame.setBounds(414, 121, 130, 20);
		frame.getContentPane().add(btnNewGame);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setBounds(29, 119, 329, 292);
		frame.getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(29, 432, 329, 28);
		frame.getContentPane().add(lblNewLabel);
	}
}
