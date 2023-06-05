package vpProject;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JPanel{
	JButton play, setting;

	MainPage(){
		
		play = new JButton("Play");
		play.setBounds(200,100,100,50);
		
		setting = new JButton("Setting");
		setting.setBounds(200,200,100,50);
		
		
		add(play);
		add(setting);
		setBackground(Color.lightGray);
		setVisible(true);
		setLayout(null);
	}
	
}
