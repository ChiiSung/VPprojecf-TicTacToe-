package vpProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class winning_frame extends JFrame{
	
	winning_frame(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screensize = toolkit.getScreenSize();
		
		ImageIcon trophicIcon = new ImageIcon("icon/tropfi.gif");
		
		JLabel header = new JLabel();
		header.setText("Congratulation");
		header.setHorizontalAlignment(JTextField.CENTER);
		header.setFont(new Font("TimesRoman", Font.BOLD, 36));
		
		JLabel center = new JLabel();
		center.setIcon(trophicIcon);
		center.setHorizontalAlignment(JLabel.CENTER);
		
		int x = (screensize.width-trophicIcon.getIconWidth()-100)/2;
		int y = (screensize.height-trophicIcon.getIconHeight()-36)/2;
		
		setSize(trophicIcon.getIconWidth()+100,trophicIcon.getIconHeight() + 36);
		setLocation(x,y);
		setVisible(true);
		setResizable(true);
		setLayout(new BorderLayout());
		addWindowFocusListener (new WindowAdapter() {
			public void windowLostFocus(WindowEvent e){
				SoundEffect.stopBgm();
			}
		});
		
		add(header,BorderLayout.NORTH);
		add(center,BorderLayout.CENTER);
		
		SoundEffect.winSound();
	}

}
