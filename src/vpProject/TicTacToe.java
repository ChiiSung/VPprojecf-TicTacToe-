package vpProject;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import javax.sound.sampled.*;
import javax.swing.*;

public class TicTacToe{
	static GamePlayPage gamePlayPage;
	static GameSetting gameSetting;
	static BGM bgm;
	ImageIcon boardIcon = new ImageIcon("icon/TicTacToe.png");
	ImageIcon playIcon = IconClass.createIcon("icon/play-button.png",25,25);
	ImageIcon settingIcon = IconClass.createIcon("icon/settings.png",30,30);
	
	static JFrame frame = new JFrame();
	static JPanel  main = new JPanel();
	JButton play = new JButton(), setting = new JButton();
	JLabel title = new JLabel();
	
	static int playerId = 1;
	static boolean matchTimer, boardInfo, playerCounter, backgroundMusic;
	static int gamemode, board, difficulty;
	
	TicTacToe(){
		try {
			readDB();
			bgm = new BGM();
			bgm.initialize();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		frameSetUp();
		componentSetUp();
		frame.add(main, BorderLayout.CENTER);
	}
	
	public void frameSetUp() {
		frame.setTitle("TicTacToe");
		frame.setIconImage(boardIcon.getImage());
		frame.setBounds(700,300,330,394);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void componentSetUp() {
		main.setVisible(true);
		main.setLayout(null);
		
		title.setText("Tic-Tac-Toe");
		title.setFont(new Font("TimesRoman", Font.BOLD, 36));
		title.setHorizontalAlignment(JTextField.CENTER);
		title.setSize(frame.getWidth(),frame.getHeight()/4);
		//Play button
		play.setText("Play");
		play.setFont(new Font("TimesRoman", Font.BOLD, 24));
		play.setIcon(playIcon);
		play.setBounds((frame.getWidth()-200)/2,48+frame.getHeight()/4,200,50);
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SoundEffect.buttonSound();
				buildGamePlay();
			}
		});
		//Setting button
		setting = new JButton("Setting");
		setting.setFont(new Font("TimesRoman", Font.BOLD, 24));
		setting.setIcon(settingIcon);
		setting.setBounds((frame.getWidth()-200)/2,118+frame.getHeight()/4,200,50);
		setting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SoundEffect.buttonSound();
				gameSetting = new GameSetting();
				frame.setSize(450,550);
				main.setVisible(false);
				frame.add(gameSetting);
				gameSetting.setVisible(true);
			}
		});
		main.add(title);
		main.add(play);
		main.add(setting);
	}
	
	public void readDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe","root","");
	
		String sql = "SELECT * FROM setting WHERE setting.player_id = '" + TicTacToe.playerId +"';";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			matchTimer = rs.getBoolean(5);
			boardInfo = rs.getBoolean(6);
			playerCounter = rs.getBoolean(7);
			backgroundMusic = rs.getBoolean(9);
			gamemode = rs.getInt(8);
			board = rs.getInt(3);
			difficulty = rs.getInt(4);
		}
		
		rs.close();
		stmt.close();
		con.close();
	}
	
	static public void buildGamePlay() {
		gamePlayPage = new GamePlayPage();
		frame.setSize(450,550);
		main.setVisible(false);
		frame.add(gamePlayPage);
		gamePlayPage.setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TicTacToe();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

class BGM extends Thread{
	private final String pathToClip = "bgm/星间旅行.wav";
    private boolean soundLoaded;
    private Clip clip;
    
   
    public void initialize() {
	    try {
	       File file = new File(pathToClip);
	       AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
	       
	       clip = AudioSystem.getClip();
	       clip.open(audioIn);
	       clip.loop(Clip.LOOP_CONTINUOUSLY);
	       soundLoaded = true;
	    }catch (UnsupportedAudioFileException e) {
	       soundLoaded = false; 
	       e.printStackTrace();
	    }catch (IOException e) {
	         soundLoaded = false; 
	         e.printStackTrace();
	    }catch (LineUnavailableException e) {
	       soundLoaded = false; 
	       e.printStackTrace();
	    }
	    if(TicTacToe.backgroundMusic) {
			clip.start();
		}else {
			clip.stop();
		}
    }
    
    public void pauseBgm() {
    	clip.stop();
    }
    
    public void resumeBgm() {
    	clip.start();
    }
}
