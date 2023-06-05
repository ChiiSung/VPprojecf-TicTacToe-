package vpProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JButton;

public class window {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window window = new window();
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
	public window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 699);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel settingLb = new JLabel("Settings");
		settingLb.setFont(new Font("Times New Roman", Font.BOLD, 36));
		settingLb.setHorizontalAlignment(SwingConstants.CENTER);
		settingLb.setBounds(143, 10, 134, 44);
		frame.getContentPane().add(settingLb);
		
		JLabel generalLb = new JLabel("General");
		generalLb.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		generalLb.setBounds(10, 53, 94, 30);
		frame.getContentPane().add(generalLb);
		
		JPanel gamemodePl = new JPanel();
		gamemodePl.setBackground(Color.WHITE);
		gamemodePl.setBounds(10, 79, 416, 66);
		frame.getContentPane().add(gamemodePl);
		gamemodePl.setLayout(null);
		
		JComboBox gamemodeCb = new JComboBox();
		gamemodeCb.setBounds(260, 24, 124, 21);
		gamemodePl.add(gamemodeCb);
		
		JPanel gamemodeLb = new JPanel();
		gamemodeLb.setBounds(10, 10, 163, 46);
		gamemodePl.add(gamemodeLb);
		
		JPanel BoardPl = new JPanel();
		BoardPl.setBackground(Color.WHITE);
		BoardPl.setBounds(10, 155, 416, 66);
		frame.getContentPane().add(BoardPl);
		BoardPl.setLayout(null);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(260, 23, 124, 21);
		BoardPl.add(comboBox_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 10, 163, 46);
		BoardPl.add(panel_2);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(10, 231, 416, 66);
		frame.getContentPane().add(panel_1_1);
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBounds(260, 23, 124, 21);
		panel_1_1.add(comboBox_1_1);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setBounds(10, 10, 163, 46);
		panel_1_1.add(panel_2_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Match Info");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 307, 94, 30);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 336, 416, 66);
		frame.getContentPane().add(panel_3);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBounds(10, 10, 163, 46);
		panel_3.add(panel_2_1_1);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("New toggle button");
		tglbtnNewToggleButton.setBounds(291, 21, 115, 21);
		panel_3.add(tglbtnNewToggleButton);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(10, 412, 416, 66);
		frame.getContentPane().add(panel_4);
		
		JPanel panel_2_1_2 = new JPanel();
		panel_2_1_2.setBounds(10, 10, 163, 46);
		panel_4.add(panel_2_1_2);
		
		JToggleButton tglbtnNewToggleButton_1 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1.setBounds(291, 23, 115, 21);
		panel_4.add(tglbtnNewToggleButton_1);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBackground(Color.WHITE);
		panel_3_1.setBounds(10, 488, 416, 66);
		frame.getContentPane().add(panel_3_1);
		
		JPanel panel_2_1_1_1 = new JPanel();
		panel_2_1_1_1.setBounds(10, 10, 163, 46);
		panel_3_1.add(panel_2_1_1_1);
		
		JToggleButton tglbtnNewToggleButton_2 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_2.setBounds(291, 21, 115, 21);
		panel_3_1.add(tglbtnNewToggleButton_2);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(143, 610, 161, 42);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(10, 10, 40, 33);
		frame.getContentPane().add(btnNewButton_1);
		
		JPanel panel_3_1_1 = new JPanel();
		panel_3_1_1.setLayout(null);
		panel_3_1_1.setBackground(Color.WHITE);
		panel_3_1_1.setBounds(10, 564, 416, 66);
		frame.getContentPane().add(panel_3_1_1);
		
		JPanel panel_2_1_1_1_1 = new JPanel();
		panel_2_1_1_1_1.setBounds(10, 10, 163, 46);
		panel_3_1_1.add(panel_2_1_1_1_1);
		
		JToggleButton tglbtnNewToggleButton_2_1 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_2_1.setBounds(291, 21, 115, 21);
		panel_3_1_1.add(tglbtnNewToggleButton_2_1);
	}
}
