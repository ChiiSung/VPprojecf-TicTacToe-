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
	ImageIcon returnIcon = IconClass.createIcon("icon/return.png",25,25);
	ImageIcon playerIcon = IconClass.createIcon("icon/human.png",25,25);
	ImageIcon deleteIcon = IconClass.createIcon("icon/dustbin.png",20,20);
	ImageIcon addPlayerIcon = IconClass.createIcon("icon/addPlayer.png",25,25);
	
	static JFrame frame = new JFrame();
	static JPanel  main = new JPanel();
	JPanel playerPl;
	JButton play, setting, playerSettingBt;
	JLabel title;
	
	static int playerId = 1;
	static boolean matchTimer, boardInfo, playerCounter, backgroundMusic;
	static int gamemode, board, difficulty;
	String playername[] = new String[3], time[] = new String[3];
	int player_id[] = new int[3];
	
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
		
		playerSettingBt = new JButton(playername[playerId-1]);
		playerSettingBt.setBounds(10, 300, 100, 38);
		playerSettingBt.setIcon(playerIcon);
		playerSettingBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundEffect.buttonSound();
				main.setVisible(false);
				playerSetting();
				try {
					readPlayer();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		main.add(playerSettingBt);
		
		title = new JLabel("Tic-Tac-Toe"); 
		title.setFont(new Font("TimesRoman", Font.BOLD, 36));
		title.setHorizontalAlignment(JTextField.CENTER);
		title.setSize(frame.getWidth(),frame.getHeight()/3);
		//Play button
		play = new JButton("Play");
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
		//check the last time played player
		String sql = "SELECT * FROM lastPlayer";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			playerId = rs.getInt(2);
		}
		
		readPlayer();
		
		//read the setting for the player
		sql = "SELECT * FROM setting WHERE setting.player_id = '" + playerId +"';";
		rs = stmt.executeQuery(sql);
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
	
	public void readPlayer() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe","root","");
		
		//read the name and play time for all player
		String sql = "SELECT * FROM player";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		playername = new String[3];
		time = new String[3];
		int i = 0;
		while(rs.next()) {
			if(rs.getInt(1)-1 != i) {
				i++;
			}
			player_id[i] = rs.getInt(1);
			playername[i] = rs.getString(2);
			time[i] = String.valueOf(rs.getTime(3));
			i++;
		}
				
	}
	
	static public void buildGamePlay() {
		gamePlayPage = new GamePlayPage();
		frame.setSize(450,550);
		main.setVisible(false);
		frame.add(gamePlayPage);
		gamePlayPage.setVisible(true);
	}
	
	public void playerSetting() {
		frame.setLayout(null);
		playerPl = new JPanel();
		playerPl.setBounds(10, 10, 296, 337);
		playerPl.setLayout(null);
		playerPl.setBackground(Color.white);
		frame.add(playerPl, BorderLayout.CENTER);
		
		JLabel gameplaytimeLp = new JLabel("Game Play Time");
		gameplaytimeLp.setHorizontalAlignment(SwingConstants.CENTER);
		gameplaytimeLp.setBounds(155, 35, 110, 13);
		playerPl.add(gameplaytimeLp);
		
		JLabel PlayerNameLb = new JLabel("Player's Name");
		PlayerNameLb.setHorizontalAlignment(SwingConstants.CENTER);
		PlayerNameLb.setBounds(20, 35, 90, 13);
		playerPl.add(PlayerNameLb);
		
		JButton returnBt = new JButton();
		returnBt.setIcon(returnIcon);
		returnBt.setBounds(10, 10, 31, 25);
		playerPl.add(returnBt);
		returnBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundEffect.buttonSound();
				frame.setLayout(new BorderLayout());
				main.setVisible(true);
				playerPl.setVisible(false);
			}
		});
		
		JPanel playerPl1 = new JPanel();
		playerPl1.setBounds(10, 53, 276, 68);
		playerPl.add(playerPl1);
		playerPl1.setLayout(null);
		player(playerPl1,0);
		
		JPanel playerPl2 = new JPanel();
		playerPl2.setBounds(10, 155, 276, 68);
		playerPl.add(playerPl2);
		playerPl2.setLayout(null);
		player(playerPl2,1);
		
		JPanel playerPl3 = new JPanel();
		playerPl3.setBounds(10, 257, 276, 68);
		playerPl.add(playerPl3);
		playerPl3.setLayout(null);
		player(playerPl3,2);
	}
	
	public void player(JPanel player, int x) {
		if(playername[x] != null) {
			player.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent arg0) {
					playerId = x+1;
					try {
						updatePlayer();
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
				}
				public void mouseEntered(MouseEvent arg0) {
					player.setBackground(Color.lightGray);
					player.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
				public void mouseExited(MouseEvent arg0) {
					player.setBackground(main.getBackground());
					player.setBorder(null);
				}
				public void mousePressed(MouseEvent arg0) {
				}
				public void mouseReleased(MouseEvent arg0) {
				}
			});
			JLabel player1Lb = new JLabel(playername[x]);
			player1Lb.setBounds(10, 10, 129, 48);
			player.add(player1Lb);
			
			JLabel playtime1Lb = new JLabel(time[x]);
			playtime1Lb.setHorizontalAlignment(SwingConstants.CENTER);
			playtime1Lb.setBounds(137, 10, 129, 48);
			player.add(playtime1Lb);
			
			JButton delete = new JButton();
			delete.setBounds(246, 25, 20, 20);
			delete.setIcon(deleteIcon);
			delete.setActionCommand(String.valueOf(x));
			delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SoundEffect.buttonSound();
					int y = Integer.valueOf(delete.getActionCommand()) + 1;
					int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure?","WARNING", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(dialogButton == JOptionPane.YES_OPTION) {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe","root","");
							
							String sql = "DELETE FROM setting WHERE player_id = " + y + ";" ;
							Statement stmt = con.createStatement();
							stmt.executeUpdate(sql);
							
							sql = "DELETE FROM player_match WHERE player_id = " + y + ";";
							stmt.executeUpdate(sql);
							
							sql = "UPDATE lastPlayer SET player_id = (SELECT player_id FROM player LIMIT 1)";
							stmt.executeUpdate(sql);
							
							sql = "DELETE FROM player WHERE player_id = "+ y + ";";
							stmt.executeUpdate(sql);
							
							sql = "SELECT player_id FROM player LIMIT 1";
							ResultSet rs = stmt.executeQuery(sql);
							while(rs.next()) {
								playerId = rs.getInt(1);
							}
							
							rs.close();
							con.close();
							stmt.close();
							updatePlayer();
						} catch (ClassNotFoundException | SQLException exp) {
							exp.printStackTrace();
						}
					}else if(dialogButton == JOptionPane.NO_OPTION) {
						
					}
				}
			});
			player.add(delete);
		}else {
			JLabel icon = new JLabel();
			icon.setIcon(addPlayerIcon);
			icon.setBounds(124, 10, 84, 48);
			player.add(icon);
			player.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent arg0) {
					String userNewName = JOptionPane.showInputDialog("Name");
					if(userNewName != null) {
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe","root","");
							
							String sql = "INSERT INTO player VALUES(+" + (x+1) + ",'" + userNewName + "'," + "0" + ")";
							Statement stmt = con.createStatement();
							stmt.executeUpdate(sql);
							playerId = x+1;
							readPlayer();
							updatePlayer();
							
							con.close();
							stmt.close();
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
					}
				}
				public void mouseEntered(MouseEvent arg0) {
					player.setBackground(Color.lightGray);
					player.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
				public void mouseExited(MouseEvent arg0) {
					player.setBackground(main.getBackground());
					player.setBorder(null);
				}
				public void mousePressed(MouseEvent arg0) {
				}
				public void mouseReleased(MouseEvent arg0) {
				}
			});
		}
	}
	
	public void updatePlayer() throws ClassNotFoundException, SQLException {
		SoundEffect.buttonSound();
		frame.setLayout(new BorderLayout());
		main.setVisible(true);
		playerPl.setVisible(false);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe","root","");
		
		String sql = "UPDATE lastplayer SET player_id = "
				+ playerId + " WHERE lastRecord = 1 ;";
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		playerSettingBt.setText(playername[playerId-1]);
		readDB();
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
